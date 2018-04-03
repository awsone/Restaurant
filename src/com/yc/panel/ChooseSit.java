package com.yc.panel;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class ChooseSit extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ChooseSit(Composite parent, int style) {
		super(parent, style);
		setSize(1440,832);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(195, 203, 141, 112);
		btnNewButton.setText("现场选座");
		
		Button button = new Button(this, SWT.NONE);
		button.setText("预定选座");
		button.setBounds(434, 203, 141, 112);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
