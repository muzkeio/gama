/**
 * Created by drogoul, 4 mars 2012
 * 
 */
package msi.gama.lang.gaml.ui;

import java.util.*;
import java.util.List;
import msi.gama.common.interfaces.IGui;
import msi.gama.common.util.GuiUtils;
import msi.gama.kernel.model.IModel;
import msi.gama.lang.gaml.resource.GamlResource;
import msi.gama.lang.gaml.validation.*;
import msi.gama.runtime.GAMA;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.*;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import com.google.inject.Inject;

/**
 * The class GamlEditor.
 * 
 * @author drogoul
 * @since 4 mars 2012
 * 
 */
public class GamlEditor extends XtextEditor implements IGamlBuilderListener {

	// Copied from SwtGui. See how to factorize this.
	public static Image run = AbstractUIPlugin.imageDescriptorFromPlugin(IGui.PLUGIN_ID, "/icons/menu_play.png")
		.createImage();
	public static Image reload = AbstractUIPlugin.imageDescriptorFromPlugin(IGui.PLUGIN_ID, "/icons/menu_reload.png")
		.createImage();
	public static final Color COLOR_ERROR = new Color(Display.getDefault(), 0xF4, 0x00, 0x15);
	public static final Color COLOR_OK = new Color(Display.getDefault(), 0x55, 0x8E, 0x1B);
	public static final Color COLOR_WARNING = new Color(Display.getDefault(), 0xFD, 0xA6, 0x00);
	public static final Color COLOR_TEXT = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	private static final int INITIAL_BUTTONS = 20;
	private static Font labelFont;
	Composite toolbar, top, parent;
	Button[] buttons = new Button[INITIAL_BUTTONS];
	Label status;
	List<String> completeNamesOfExperiments = new ArrayList();
	List<String> abbreviatedNamesOfExperiments = new ArrayList();
	boolean wasOK = true, inited = false;

	@Inject
	private GamlJavaValidator validator;

	// @Inject
	// private DescriptionUtils descriptionUtils;

	// @Inject
	// IResourceDescriptions index;

	static {
		FontData fd = Display.getDefault().getSystemFont().getFontData()[0];
		fd.setStyle(SWT.BOLD);
		labelFont = new Font(Display.getDefault(), fd);
	}

	@Override
	public void dispose() {
		if ( getDocument() != null ) {
			getDocument().readOnly(new IUnitOfWork.Void<XtextResource>() {

				@Override
				public void process(final XtextResource state) throws Exception {
					((GamlResource) state).removeListener();
				}

			});
		}

		if ( buttons != null ) {
			for ( Button b : buttons ) {
				if ( b != null && !b.isDisposed() ) {
					b.dispose();
				}
			}
			buttons = null;
		}

		if ( top != null && !top.isDisposed() ) {
			top.dispose();
			top = null;
		}
		if ( toolbar != null && !toolbar.isDisposed() ) {
			toolbar.dispose();
			toolbar = null;
		}
		if ( status != null && !status.isDisposed() ) {
			status.dispose();
			status = null;
		}
		super.dispose();
	}

	@Override
	public void createPartControl(final Composite parent) {
		this.parent = parent;
		GridLayout layout = new GridLayout(1, true);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		parent.setLayout(layout);

		top = new Composite(parent, SWT.None);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
		top.setLayoutData(data);
		layout = new GridLayout(2, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 10;
		layout.marginHeight = 5;
		top.setLayout(layout);

		toolbar = new Composite(top, SWT.None);
		data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 15;
		toolbar.setLayoutData(data);
		layout = new GridLayout(INITIAL_BUTTONS + 1, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;

		toolbar.setLayout(layout);

		status = new Label(toolbar, SWT.NONE);
		data = new GridData(SWT.FILL, SWT.CENTER, true, true);
		data.minimumHeight = SWT.DEFAULT;
		status.setLayoutData(data);
		status.setForeground(COLOR_TEXT);

		for ( int i = 0; i < INITIAL_BUTTONS; i++ ) {
			buttons[i] = new Button(toolbar, SWT.PUSH);
			data = new GridData(SWT.LEFT, SWT.FILL, false, true);
			buttons[i].setLayoutData(data);
			buttons[i].setText("Experiment " + i);
			buttons[i].addSelectionListener(listener);
			hideButton(buttons[i]);
		}

		// Asking the editor to fill the rest
		Composite parent2 = new Composite(parent, SWT.BORDER);
		data = new GridData(SWT.FILL, SWT.FILL, true, true);
		parent2.setLayoutData(data);
		parent2.setLayout(new FillLayout());
		super.createPartControl(parent2);
		getDocument().readOnly(new IUnitOfWork.Void<XtextResource>() {

			@Override
			public void process(final XtextResource state) throws Exception {

				((GamlResource) state).setListener(GamlEditor.this);

			}
		});
	}

	private final SelectionListener listener = new SelectionAdapter() {

		@Override
		public void widgetSelected(final SelectionEvent evt) {
			GamlEditor.this.performSave(true, null);
			String name = ((Button) evt.widget).getText();
			int i = abbreviatedNamesOfExperiments.indexOf(name);
			if ( i == -1 ) { return; }
			name = completeNamesOfExperiments.get(i);
			IModel model = getDocument().readOnly(new IUnitOfWork<IModel, XtextResource>() {

				@Override
				public IModel exec(final XtextResource state) throws Exception {
					return validator.build((GamlResource) state);
				}

			});
			if ( model == null ) { return; }
			GuiUtils.openSimulationPerspective();
			GAMA.newExperiment(name, model);
		}

	};

	private void enableButton(final int index, final String text) {
		if ( text == null ) { return; }
		((GridData) buttons[index].getLayoutData()).exclude = false;
		buttons[index].setVisible(true);
		buttons[index].setText(text);
		buttons[index].pack();
	}

	private void hideButton(final Button b) {
		((GridData) b.getLayoutData()).exclude = true;
		b.setVisible(false);
	}

	private void setStatus(final Color c, final String text) {
		top.setBackground(c);
		status.setBackground(c);
		toolbar.setBackground(c);
		status.setText(text);
	}

	private void updateToolbar(final boolean ok) {

		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if ( toolbar == null || toolbar.isDisposed() ) { return; }
				for ( Button b : buttons ) {
					if ( b.isVisible() ) {
						hideButton(b);
					}
				}
				if ( ok ) {
					// TODO Have a button before the title to allow inserting a "default" experiment
					int size = abbreviatedNamesOfExperiments.size();
					if ( size == 0 ) { // 1 && abbreviatedNamesOfExperiments.contains(IKeyword.DEFAULT) ) {
						setStatus(COLOR_WARNING, "Model is functional, but no experiments have been defined.");
					} else {
						setStatus(COLOR_OK, size == 1 ? "Run experiment:" : "Choose an experiment:");
					}
					int i = 0;
					// if ( abbreviatedNamesOfExperiments.size() > 1 ) {
					// abbreviatedNamesOfExperiments.remove(IKeyword.DEFAULT);
					// completeNamesOfExperiments.remove(IKeyword.DEFAULT);
					// }
					for ( String e : abbreviatedNamesOfExperiments ) {
						enableButton(i++, e);
					}
				} else {
					setStatus(COLOR_ERROR, "Error(s) detected. Impossible to run any experiment");
				}

				toolbar.layout(true);
				// toolbar.update();
			}
		});

	}

	private void updateExperiments(final Set<String> newExperiments, final boolean withErrors) {
		if ( withErrors == true && wasOK == false ) { return; }
		// GuiUtils.debug("New set of experiments:" + newExperiments);
		Set<String> oldNames = new LinkedHashSet(completeNamesOfExperiments);
		if ( inited && wasOK && !withErrors && oldNames.equals(newExperiments) ) { return; }
		inited = true;
		wasOK = !withErrors;
		completeNamesOfExperiments = new ArrayList(newExperiments);
		buildAbbreviations();
		updateToolbar(wasOK);
	}

	private void buildAbbreviations() {
		// Very simple method used here
		int size = completeNamesOfExperiments.size();
		abbreviatedNamesOfExperiments.clear();
		if ( size > 6 ) {
			// We remove "Experiment".
			for ( String s : completeNamesOfExperiments ) {
				int i = s.indexOf(' ');
				if ( i != -1 ) {
					abbreviatedNamesOfExperiments.add(s.substring(i));
				}
			}
		} else if ( size > 4 ) {
			// We replace "Experiment" by "Exp."
			for ( String s : completeNamesOfExperiments ) {
				abbreviatedNamesOfExperiments.add(s.replaceFirst("Experiment ", "Exp."));
			}
		} else {
			// We copy the names as it is
			abbreviatedNamesOfExperiments.addAll(completeNamesOfExperiments);
		}
	}

	/**
	 * @see msi.gama.common.interfaces.IGamlBuilder.Listener#validationEnded(boolean)
	 */
	@Override
	public void validationEnded(final Set<String> newExperiments, final boolean withErrors) {
		updateExperiments(newExperiments, withErrors);
	}

	public static class GamaSourceViewerConfiguration extends XtextSourceViewerConfiguration {

		@Override
		public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
			return super.getTextHover(sourceViewer, contentType);
		}

	}

	public GamlJavaValidator getValidator() {
		return validator;
	}

}
