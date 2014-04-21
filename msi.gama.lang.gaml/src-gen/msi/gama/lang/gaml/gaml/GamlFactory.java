/**
 */
package msi.gama.lang.gaml.gaml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see msi.gama.lang.gaml.gaml.GamlPackage
 * @generated
 */
public interface GamlFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GamlFactory eINSTANCE = msi.gama.lang.gaml.gaml.impl.GamlFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Entry</em>'.
   * @generated
   */
  Entry createEntry();

  /**
   * Returns a new object of class '<em>String Evaluator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Evaluator</em>'.
   * @generated
   */
  StringEvaluator createStringEvaluator();

  /**
   * Returns a new object of class '<em>Action Editor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Action Editor</em>'.
   * @generated
   */
  ActionEditor createActionEditor();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  Model createModel();

  /**
   * Returns a new object of class '<em>Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Block</em>'.
   * @generated
   */
  Block createBlock();

  /**
   * Returns a new object of class '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import</em>'.
   * @generated
   */
  Import createImport();

  /**
   * Returns a new object of class '<em>Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Statement</em>'.
   * @generated
   */
  Statement createStatement();

  /**
   * Returns a new object of class '<em>SGlobal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SGlobal</em>'.
   * @generated
   */
  S_Global createS_Global();

  /**
   * Returns a new object of class '<em>SEntities</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SEntities</em>'.
   * @generated
   */
  S_Entities createS_Entities();

  /**
   * Returns a new object of class '<em>SEnvironment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SEnvironment</em>'.
   * @generated
   */
  S_Environment createS_Environment();

  /**
   * Returns a new object of class '<em>SSpecies</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SSpecies</em>'.
   * @generated
   */
  S_Species createS_Species();

  /**
   * Returns a new object of class '<em>SExperiment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SExperiment</em>'.
   * @generated
   */
  S_Experiment createS_Experiment();

  /**
   * Returns a new object of class '<em>SDo</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SDo</em>'.
   * @generated
   */
  S_Do createS_Do();

  /**
   * Returns a new object of class '<em>SLoop</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SLoop</em>'.
   * @generated
   */
  S_Loop createS_Loop();

  /**
   * Returns a new object of class '<em>SIf</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SIf</em>'.
   * @generated
   */
  S_If createS_If();

  /**
   * Returns a new object of class '<em>SOther</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SOther</em>'.
   * @generated
   */
  S_Other createS_Other();

  /**
   * Returns a new object of class '<em>SReturn</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SReturn</em>'.
   * @generated
   */
  S_Return createS_Return();

  /**
   * Returns a new object of class '<em>SDeclaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SDeclaration</em>'.
   * @generated
   */
  S_Declaration createS_Declaration();

  /**
   * Returns a new object of class '<em>SReflex</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SReflex</em>'.
   * @generated
   */
  S_Reflex createS_Reflex();

  /**
   * Returns a new object of class '<em>SDefinition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SDefinition</em>'.
   * @generated
   */
  S_Definition createS_Definition();

  /**
   * Returns a new object of class '<em>SAssignment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SAssignment</em>'.
   * @generated
   */
  S_Assignment createS_Assignment();

  /**
   * Returns a new object of class '<em>SDirect Assignment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SDirect Assignment</em>'.
   * @generated
   */
  S_DirectAssignment createS_DirectAssignment();

  /**
   * Returns a new object of class '<em>SSet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SSet</em>'.
   * @generated
   */
  S_Set createS_Set();

  /**
   * Returns a new object of class '<em>SEquations</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SEquations</em>'.
   * @generated
   */
  S_Equations createS_Equations();

  /**
   * Returns a new object of class '<em>SSolve</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SSolve</em>'.
   * @generated
   */
  S_Solve createS_Solve();

  /**
   * Returns a new object of class '<em>SMonitor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SMonitor</em>'.
   * @generated
   */
  S_Monitor createS_Monitor();

  /**
   * Returns a new object of class '<em>SDisplay</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SDisplay</em>'.
   * @generated
   */
  S_Display createS_Display();

  /**
   * Returns a new object of class '<em>species Or Grid Display Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>species Or Grid Display Statement</em>'.
   * @generated
   */
  speciesOrGridDisplayStatement createspeciesOrGridDisplayStatement();

  /**
   * Returns a new object of class '<em>Parameters</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameters</em>'.
   * @generated
   */
  Parameters createParameters();

  /**
   * Returns a new object of class '<em>Action Arguments</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Action Arguments</em>'.
   * @generated
   */
  ActionArguments createActionArguments();

  /**
   * Returns a new object of class '<em>Argument Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Argument Definition</em>'.
   * @generated
   */
  ArgumentDefinition createArgumentDefinition();

  /**
   * Returns a new object of class '<em>Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Facet</em>'.
   * @generated
   */
  Facet createFacet();

  /**
   * Returns a new object of class '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression</em>'.
   * @generated
   */
  Expression createExpression();

  /**
   * Returns a new object of class '<em>Argument Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Argument Pair</em>'.
   * @generated
   */
  ArgumentPair createArgumentPair();

  /**
   * Returns a new object of class '<em>Function</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Function</em>'.
   * @generated
   */
  Function createFunction();

  /**
   * Returns a new object of class '<em>Expression List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression List</em>'.
   * @generated
   */
  ExpressionList createExpressionList();

  /**
   * Returns a new object of class '<em>Variable Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable Ref</em>'.
   * @generated
   */
  VariableRef createVariableRef();

  /**
   * Returns a new object of class '<em>Species Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Species Ref</em>'.
   * @generated
   */
  SpeciesRef createSpeciesRef();

  /**
   * Returns a new object of class '<em>Type Info</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Info</em>'.
   * @generated
   */
  TypeInfo createTypeInfo();

  /**
   * Returns a new object of class '<em>Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Definition</em>'.
   * @generated
   */
  GamlDefinition createGamlDefinition();

  /**
   * Returns a new object of class '<em>Equation Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Equation Definition</em>'.
   * @generated
   */
  EquationDefinition createEquationDefinition();

  /**
   * Returns a new object of class '<em>Type Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Definition</em>'.
   * @generated
   */
  TypeDefinition createTypeDefinition();

  /**
   * Returns a new object of class '<em>Var Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Var Definition</em>'.
   * @generated
   */
  VarDefinition createVarDefinition();

  /**
   * Returns a new object of class '<em>Action Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Action Definition</em>'.
   * @generated
   */
  ActionDefinition createActionDefinition();

  /**
   * Returns a new object of class '<em>Unit Fake Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unit Fake Definition</em>'.
   * @generated
   */
  UnitFakeDefinition createUnitFakeDefinition();

  /**
   * Returns a new object of class '<em>Type Fake Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Fake Definition</em>'.
   * @generated
   */
  TypeFakeDefinition createTypeFakeDefinition();

  /**
   * Returns a new object of class '<em>Action Fake Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Action Fake Definition</em>'.
   * @generated
   */
  ActionFakeDefinition createActionFakeDefinition();

  /**
   * Returns a new object of class '<em>Skill Fake Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Skill Fake Definition</em>'.
   * @generated
   */
  SkillFakeDefinition createSkillFakeDefinition();

  /**
   * Returns a new object of class '<em>Var Fake Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Var Fake Definition</em>'.
   * @generated
   */
  VarFakeDefinition createVarFakeDefinition();

  /**
   * Returns a new object of class '<em>Equation Fake Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Equation Fake Definition</em>'.
   * @generated
   */
  EquationFakeDefinition createEquationFakeDefinition();

  /**
   * Returns a new object of class '<em>Terminal Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Terminal Expression</em>'.
   * @generated
   */
  TerminalExpression createTerminalExpression();

  /**
   * Returns a new object of class '<em>SAction</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SAction</em>'.
   * @generated
   */
  S_Action createS_Action();

  /**
   * Returns a new object of class '<em>SVar</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>SVar</em>'.
   * @generated
   */
  S_Var createS_Var();

  /**
   * Returns a new object of class '<em>Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Pair</em>'.
   * @generated
   */
  Pair createPair();

  /**
   * Returns a new object of class '<em>If</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>If</em>'.
   * @generated
   */
  If createIf();

  /**
   * Returns a new object of class '<em>Cast</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Cast</em>'.
   * @generated
   */
  Cast createCast();

  /**
   * Returns a new object of class '<em>Binary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Binary</em>'.
   * @generated
   */
  Binary createBinary();

  /**
   * Returns a new object of class '<em>Unit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unit</em>'.
   * @generated
   */
  Unit createUnit();

  /**
   * Returns a new object of class '<em>Unary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unary</em>'.
   * @generated
   */
  Unary createUnary();

  /**
   * Returns a new object of class '<em>Access</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Access</em>'.
   * @generated
   */
  Access createAccess();

  /**
   * Returns a new object of class '<em>Array</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Array</em>'.
   * @generated
   */
  Array createArray();

  /**
   * Returns a new object of class '<em>Point</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Point</em>'.
   * @generated
   */
  Point createPoint();

  /**
   * Returns a new object of class '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter</em>'.
   * @generated
   */
  Parameter createParameter();

  /**
   * Returns a new object of class '<em>Unit Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unit Name</em>'.
   * @generated
   */
  UnitName createUnitName();

  /**
   * Returns a new object of class '<em>Type Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Ref</em>'.
   * @generated
   */
  TypeRef createTypeRef();

  /**
   * Returns a new object of class '<em>Skill Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Skill Ref</em>'.
   * @generated
   */
  SkillRef createSkillRef();

  /**
   * Returns a new object of class '<em>Action Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Action Ref</em>'.
   * @generated
   */
  ActionRef createActionRef();

  /**
   * Returns a new object of class '<em>Equation Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Equation Ref</em>'.
   * @generated
   */
  EquationRef createEquationRef();

  /**
   * Returns a new object of class '<em>Int Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Int Literal</em>'.
   * @generated
   */
  IntLiteral createIntLiteral();

  /**
   * Returns a new object of class '<em>Double Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Double Literal</em>'.
   * @generated
   */
  DoubleLiteral createDoubleLiteral();

  /**
   * Returns a new object of class '<em>Color Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Color Literal</em>'.
   * @generated
   */
  ColorLiteral createColorLiteral();

  /**
   * Returns a new object of class '<em>String Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Literal</em>'.
   * @generated
   */
  StringLiteral createStringLiteral();

  /**
   * Returns a new object of class '<em>Boolean Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Literal</em>'.
   * @generated
   */
  BooleanLiteral createBooleanLiteral();

  /**
   * Returns a new object of class '<em>Reserved Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Reserved Literal</em>'.
   * @generated
   */
  ReservedLiteral createReservedLiteral();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  GamlPackage getGamlPackage();

} //GamlFactory
