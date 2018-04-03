package com.yc.panel;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.ibm.icu.text.SimpleDateFormat;
import com.yc.dao.TempItemDao;
import com.yc.printer.Test;
import com.yc.util.SetCenter;
import com.yc.util.SetInfo;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Check extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Check(Shell parent, int style) {
		super(parent, style);
		setText("消费详单");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
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

		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		shell.setSize(500, 800);
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		SetCenter.setCenter(shell);

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Sitka Banner", 16, SWT.BOLD));
		lblNewLabel.setText("欢迎光临味道先生餐饮");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("    菜名");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("单价");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("件数");
		
		Composite composite_5 = new Composite(sashForm_1, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		sashForm_1.setWeights(new int[] {50, 400, 38});
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_2, SWT.NONE);
		sashForm_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		sashForm_2.setOrientation(SWT.VERTICAL);
		
		Composite composite_6 = new Composite(sashForm_2, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_1 = new Label(composite_6, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(new Date()); 
		lblNewLabel_1.setText(s);
		
		Composite composite_7 = new Composite(sashForm_2, SWT.NONE);
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(composite_7, SWT.NONE);
		sashForm_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		Composite composite_11 = new Composite(sashForm_3, SWT.NONE);
		composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_2 = new Label(composite_11, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_2.setText("总金额：");
		
		Composite composite_12 = new Composite(sashForm_3, SWT.NONE);
		composite_12.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_3 = new Label(composite_12, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_3.setText(String.valueOf(SetInfo.count));
		sashForm_3.setWeights(new int[] {1, 1});
		
		Composite composite_8 = new Composite(sashForm_2, SWT.NONE);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		SashForm sashForm_4 = new SashForm(composite_8, SWT.NONE);
		sashForm_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		sashForm_4.setBounds(0, 0, 494, 58);
		
		Composite composite_13 = new Composite(sashForm_4, SWT.NONE);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite_13, SWT.NONE);
		label.setText("实收：");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label.setAlignment(SWT.CENTER);
		
		Composite composite_14 = new Composite(sashForm_4, SWT.NONE);
		composite_14.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_1 = new Label(composite_14, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_1.setAlignment(SWT.CENTER);
		label_1.setText(String.valueOf(SetInfo.pay));
		sashForm_4.setWeights(new int[] {1, 1});
		
		Composite composite_9 = new Composite(sashForm_2, SWT.NONE);
		composite_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_5 = new SashForm(composite_9, SWT.NONE);
		sashForm_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		Composite composite_15 = new Composite(sashForm_5, SWT.NONE);
		composite_15.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_2 = new Label(composite_15, SWT.NONE);
		label_2.setText("找零:");
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_2.setAlignment(SWT.CENTER);
		
		Composite composite_16 = new Composite(sashForm_5, SWT.NONE);
		composite_16.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_3 = new Label(composite_16, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_3.setAlignment(SWT.CENTER);
		label_3.setText(String.valueOf(SetInfo.keep));
		sashForm_5.setWeights(new int[] {1, 1});
		
		Composite composite_10 = new Composite(sashForm_2, SWT.NONE);
		composite_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_6 = new SashForm(composite_10, SWT.NONE);
		sashForm_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		Composite composite_17 = new Composite(sashForm_6, SWT.NONE);
		composite_17.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_4 = new Label(composite_17, SWT.NONE);
		label_4.setText("操作员：");
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_4.setAlignment(SWT.CENTER);
		
		Composite composite_18 = new Composite(sashForm_6, SWT.NONE);
		composite_18.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_5 = new Label(composite_18, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_5.setAlignment(SWT.CENTER);
		label_5.setText(SetInfo.ename);
		sashForm_6.setWeights(new int[] {1, 1});
		sashForm_2.setWeights(new int[] {1, 1, 1, 1, 1});
		
		Composite composite_19 = new Composite(sashForm, SWT.NONE);
		composite_19.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button button = new Button(composite_19, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Test().main(null);
			}
		});
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		button.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		button.setText("打印");
		sashForm.setWeights(new int[] {50, 400, 304, 60});	
		

		TempItemDao tDao=new TempItemDao();
		List<Map<String,Object>> Items=tDao.getTempItem(SetInfo.orderManager.tempId);

		for(Map<String,Object > map:Items){
			
			
			
				TableItem ti1= new TableItem(table, SWT.NONE);
				//System.out.println(ti.getText(0).toString()+ti.getText(1).toString()+ti.getText(2).toString());
				ti1.setText(new String[]{String.valueOf(map.get("tempname")),String.valueOf(map.get("tempprice")),String.valueOf(map.get("tempsum"))});
			}
		}
}
