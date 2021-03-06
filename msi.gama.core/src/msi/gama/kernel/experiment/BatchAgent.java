/*********************************************************************************************
 *
 * 'BatchAgent.java, in plugin msi.gama.core, is part of the source code of the GAMA modeling and simulation platform.
 * (c) 2007-2016 UMI 209 UMMISCO IRD/UPMC & Partners
 *
 * Visit https://github.com/gama-platform/gama for license information and developers contact.
 * 
 *
 **********************************************************************************************/
package msi.gama.kernel.experiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jfree.data.statistics.Statistics;

import msi.gama.common.interfaces.IGui;
import msi.gama.common.interfaces.IKeyword;
import msi.gama.kernel.batch.IExploration;
import msi.gama.kernel.experiment.IParameter.Batch;
import msi.gama.kernel.simulation.SimulationAgent;
import msi.gama.kernel.simulation.SimulationPopulation;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.population.IPopulation;
import msi.gama.outputs.FileOutput;
import msi.gama.precompiler.GamlAnnotations.experiment;
import msi.gama.runtime.GAMA;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.expressions.IExpression;
import msi.gaml.expressions.IExpressionFactory;
import msi.gaml.operators.Cast;
import msi.gaml.types.IType;
import msi.gaml.variables.IVariable;

/**
 * Written by drogoul Modified on 28 mai 2011
 *
 * @todo Description
 *
 */

@experiment (IKeyword.BATCH)
@SuppressWarnings ({ "unchecked", "rawtypes" })
public class BatchAgent extends ExperimentAgent {

	final IExpression stopCondition;
	private int runNumber;
	ParametersSet currentSolution;
	ParametersSet lastSolution;
	private Double lastFitness;
	private Double[] seeds;
	final List<Double> fitnessValues = new ArrayList<>();

	public BatchAgent(final IPopulation p) throws GamaRuntimeException {
		super(p);
		final IScope scope = getSpecies().getExperimentScope();
		final IExpression expr = getSpecies().getFacet(IKeyword.REPEAT);
		int innerLoopRepeat = 1;
		if (expr != null && expr.isConst()) {
			innerLoopRepeat = Cast.asInt(scope, expr.value(scope));
		}
		setSeeds(new Double[innerLoopRepeat]);

		if (getSpecies().hasFacet(IKeyword.UNTIL)) {
			stopCondition = getSpecies().getFacet(IKeyword.UNTIL);
		} else {
			stopCondition = defaultStopCondition();
		}

	}

	protected IExpression defaultStopCondition() {
		return IExpressionFactory.FALSE_EXPR;
	}

	@Override
	public void schedule(final IScope scope) {
		super.schedule(scope);
		// Necessary to run it here, as if the seed has been fixed in the
		// experiment, it is now defined and initialized
		if (getSpecies().keepsSeed()) {
			for (int i = 0; i < seeds.length; i++) {
				getSeeds()[i] = getScope().getRandom().between(0d, Long.MAX_VALUE);
			}
		}

	}

	@Override
	public Object _init_(final IScope scope) {
		getSpecies().getExplorationAlgorithm().initializeFor(scope, this);
		// Fix for issue #2088
		// We call super _init_ here, but the result of automaticallyCreateFirstSimulation() will prevent from creating
		// a first simulation (which we dont want as it should be the task of the exploration algorithm)
		super._init_(scope);
		return this;
	}

	@Override
	protected boolean automaticallyCreateFirstSimulation() {
		return false;
	}

	@SuppressWarnings ("null")
	@Override
	public void reset() {
		// We first save the results of the various simulations
		final SimulationPopulation pop = getSimulationPopulation();
		final boolean hasSimulations = pop != null && !pop.isEmpty();
		try {
			if (hasSimulations) {
				for (final IAgent sim : pop.toArray()) {
					memorizeFitnessAndCloseSimulation(sim);
				}
				pop.clear();
			}

		} catch (final GamaRuntimeException e) {
			e.addContext("in saving the results of the batch");
			GAMA.reportError(getScope(), e, true);
		}
		// We save the clock value first (to address Issue #1592)
		final int cycle = clock.getCycle();
		final long totalDuration = clock.getTotalDuration();
		final long lastDuration = clock.getDuration();

		super.reset();
		clock.setCycle(cycle);
		clock.setTotalDuration(totalDuration);
		clock.setLastDuration(lastDuration);
	}

	public void memorizeFitnessAndCloseSimulation(final IAgent sim) {
		final IExpression fitness = getSpecies().getExplorationAlgorithm().getFitnessExpression();
		final FileOutput output = getSpecies().getLog();
		double lastFitnessValue = 0;
		if (fitness != null) {
			lastFitnessValue = Cast.asFloat(sim.getScope(), fitness.value(sim.getScope()));
			fitnessValues.add(lastFitnessValue);
		}
		if (output != null) {
			getSpecies().getLog().doRefreshWriteAndClose(currentSolution, lastFitnessValue);
		}
		sim.dispose();
	}

	/**
	 *
	 * Method step()
	 * 
	 * @see msi.gama.metamodel.agent.GamlAgent#step(msi.gama.runtime.IScope) This method, called once by the front
	 *      controller, actually serves as "launching" the batch process (entirely piloted by the exploration algorithm)
	 */
	@Override
	public boolean step(final IScope scope) {
		// We run the exloration algorithm. The future steps will be called by the exploration algorithm through the
		// launchSimulationsWithSolution() method
		getSpecies().getExplorationAlgorithm().run(scope);
		// Once the algorithm has finished exploring the solutions, the agent is
		// killed.
		scope.getGui().getStatus(scope).informStatus(endStatus());
		dispose();
		GAMA.getGui().updateExperimentState(scope, IGui.FINISHED);
		return true;
	}

	protected String endStatus() {
		return "Batch over. " + runNumber + " runs, " + runNumber * seeds.length + " simulations.";
	}

	public int getRunNumber() {
		return this.runNumber;
	}

	public Double launchSimulationsWithSolution(final ParametersSet sol) throws GamaRuntimeException {
		// We first reset the currentSolution and the fitness values
		final SimulationPopulation pop = getSimulationPopulation();
		if (pop == null) // interrupted
			return 0d;
		currentSolution = new ParametersSet(sol);
		fitnessValues.clear();
		runNumber = runNumber + 1;
		// The values present in the solution are passed to the parameters of
		// the experiment
		for (final Map.Entry<String, Object> entry : sol.entrySet()) {
			final IParameter p = getSpecies().getExplorableParameters().get(entry.getKey());
			if (p != null) {
				p.setValue(getScope(), entry.getValue());
			}
		}

		// We update the parameters (parameter to explore)
		getScope().getGui().showParameterView(getScope(), getSpecies());

		// We then create a number of simulations with the same solution

		int numberOfCores = pop.getMaxNumberOfConcurrentSimulations();
		if (numberOfCores == 0)
			numberOfCores = 1;
		int repeatIndex = 0;
		while (repeatIndex < getSeeds().length) {
			for (int coreIndex = 0; coreIndex < numberOfCores; coreIndex++) {
				setSeed(getSeeds()[repeatIndex]);
				createSimulation(currentSolution, true);
				repeatIndex++;
				if (repeatIndex == getSeeds().length)
					break;
			}
			int i = 0;
			while (pop.hasScheduledSimulations()) {
				// We step all the simulations
				pop.step(getScope());
				// String cycles = "";
				// We evaluate their stopCondition and unschedule the ones who
				// return true
				for (final IAgent sim : pop.toArray()) {
					final SimulationAgent agent = (SimulationAgent) sim;
					// cycles += " " + simulation.getClock().getCycle();
					// test the condition first in case it is paused
					final boolean stopConditionMet =
							Cast.asBool(sim.getScope(), sim.getScope().evaluate(stopCondition, sim).getValue());
					final boolean mustStop = stopConditionMet || agent.dead() || agent.getScope().isPaused();
					if (mustStop) {
						pop.unscheduleSimulation(agent);
						if (!getSpecies().keepsSimulations())
							memorizeFitnessAndCloseSimulation(agent);
					}
				}
				// We inform the status line

				getScope().getGui().getStatus(getScope())
						.setStatus("Run " + runNumber + " | " + repeatIndex + "/" + seeds.length
								+ " simulations (using " + pop.getNumberOfActiveThreads() + " threads)",
								"small.batch" + i / 5);
				if (++i == 20) {
					i = 0;
				}
				// We then verify that the front scheduler has not been paused
				while (getSpecies().getController().getScheduler().paused && !dead) {
					try {
						Thread.sleep(100);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

		// When the simulations are finished, we give a chance to the outputs of
		// the experiment and the experiment
		// agent itself to "step" once, effectively emulating what the front
		// scheduler should do. The simulations are
		// still "alive" at this stage (even if they are not scheduled anymore),
		// which allows to retrieve information from them
		super.step(getScope());

		// If the agent is dead, we return immediately
		if (dead) { return 0.0; }
		// We reset the experiment agent to erase traces of the current
		// simulations if any
		this.reset();

		// We then return the combination (average, min or max) of the different
		// fitness values computed by the
		// different simulation.
		final short fitnessCombination = getSpecies().getExplorationAlgorithm().getCombination();
		lastSolution = currentSolution;
		lastFitness = fitnessCombination == IExploration.C_MAX ? Collections.max(fitnessValues)
				: fitnessCombination == IExploration.C_MIN ? Collections.min(fitnessValues)
						: Statistics.calculateMean(fitnessValues);

		// we update the best solution found so far
		getSpecies().getExplorationAlgorithm().updateBestFitness(lastSolution, lastFitness);

		// At last, we update the parameters (last fitness and best fitness)
		getScope().getGui().showParameterView(getScope(), getSpecies());

		return lastFitness;

	}

	public List<IParameter.Batch> getParametersToExplore() {
		final List<IParameter.Batch> result = new ArrayList(getSpecies().getExplorableParameters().values());
		return result;
	}

	@Override
	public List<? extends IParameter.Batch> getDefaultParameters() {
		final List<IParameter.Batch> params = (List<Batch>) super.getDefaultParameters();
		for (final IVariable v : getModel().getVars()) {
			if (v.isParameter() && !getSpecies().getExplorableParameters().containsKey(v.getName())) {
				final ExperimentParameter p = new ExperimentParameter(getScope(), v);
				if (p.canBeExplored()) {
					p.setEditable(false);
					p.setCategory(IExperimentPlan.EXPLORABLE_CATEGORY_NAME);
					params.add(p);
				}
			}
		}

		addSpecificParameters(params);
		return params;
	}

	public void addSpecificParameters(final List<IParameter.Batch> params) {
		params.add(new ParameterAdapter("Stop condition", IExperimentPlan.BATCH_CATEGORY_NAME, IType.STRING) {

			@Override
			public Object value() {
				return stopCondition != null ? stopCondition.serialize(false) : "none";
			}

		});

		params.add(new ParameterAdapter("Best fitness", IExperimentPlan.BATCH_CATEGORY_NAME, "", IType.STRING) {

			@Override
			public String getUnitLabel(final IScope scope) {
				final IExploration algo = getSpecies().getExplorationAlgorithm();
				if (algo == null) { return ""; }
				final ParametersSet params = algo.getBestSolution();
				if (params == null) { return ""; }
				return "with " + params;
			}

			@Override
			public String value() {
				final IExploration algo = getSpecies().getExplorationAlgorithm();
				if (algo == null) { return "-"; }
				final Double best = algo.getBestFitness();
				if (best == null) { return "-"; }
				return best.toString();
			}

		});

		params.add(new ParameterAdapter("Last fitness", IExperimentPlan.BATCH_CATEGORY_NAME, "", IType.STRING) {

			@Override
			public String getUnitLabel(final IScope scope) {
				if (lastSolution == null) { return ""; }
				return "with " + lastSolution.toString();
			}

			@Override
			public String value() {
				if (lastFitness == null) { return "-"; }
				return lastFitness.toString();
			}

		});

		params.add(new ParameterAdapter("Parameter space", IExperimentPlan.BATCH_CATEGORY_NAME, "", IType.STRING) {

			@Override
			public String value() {
				final Map<String, IParameter.Batch> params = getSpecies().getExplorableParameters();
				if (params.isEmpty()) { return "1"; }
				String result = "";
				int dim = 1;
				for (final Map.Entry<String, IParameter.Batch> entry : params.entrySet()) {
					result += entry.getKey() + " (";
					final int entryDim = getExplorationDimension(entry.getValue());
					dim = dim * entryDim;
					result += String.valueOf(entryDim) + ") * ";
				}
				result = result.substring(0, result.length() - 2);
				result += " = " + dim;
				return result;
			}

			int getExplorationDimension(final IParameter.Batch p) {
				if (p.getAmongValue(getScope()) != null) { return p.getAmongValue(getScope()).size(); }
				return (int) ((p.getMaxValue(getScope()).doubleValue() - p.getMinValue(getScope()).doubleValue())
						/ p.getStepValue(getScope()).doubleValue()) + 1;
			}

		});

		getSpecies().getExplorationAlgorithm().addParametersTo(params, this);
	}

	public Double[] getSeeds() {
		return seeds;
	}

	public void setSeeds(final Double[] seeds) {
		this.seeds = seeds;
	}

	@Override
	public void closeSimulations() {
		// We interrupt the simulation scope directly (as it cannot be
		// interrupted by the global scheduler)
		if (getSimulation() != null) {
			getSimulation().getScope().setInterrupted();
		}
	}

}
