package com.yc.panel;


import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.util.SetCenter;

public class Register extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Register(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		SetCenter.setCenter(shell);
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(465, 314);
		shell.setText("注册");
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLocation(0, 0);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] {1});
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(72, 31, 61, 30);
		lblNewLabel_1.setText("员工姓名：");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(199, 28, 140, 30);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(72, 88, 61, 30);
		lblNewLabel_2.setText("员工性别：");
		
		Button btnRadioButton = new Button(shell, SWT.RADIO);
		
		btnRadioButton.setBounds(193, 88, 38, 17);
		btnRadioButton.setText("男");
		
		Button btnRadioButton_1 = new Button(shell, SWT.RADIO);
		btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRadioButton_1.setBounds(260, 88, 46, 17);
		btnRadioButton_1.setText("女");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(72, 139, 61, 17);
		lblNewLabel_3.setText("员工职位：");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(72, 187, 61, 17);
		lblNewLabel_4.setText("登录密码：");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(199, 126, 140, 30);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(199, 184, 140, 30);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(180, 254, 80, 27);
		btnNewButton.setText("创建");

	}
}
