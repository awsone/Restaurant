package com.yc.panel;


import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

//import com.yc.goods.dao.GoodsInfoDao;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;


import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.dao.EmployeeDao;
import com.yc.util.SetCenter;
import com.yc.util.SetInfo;

public class Primary1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Primary1 window = new Primary1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage("res/picture/logo.png"));
		shell.setSize(1440,936);
		new SetCenter().setCenter(shell);
		String tempStr="当前登录-"+SetInfo.ename;
		shell.setText(tempStr);

		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(255, 245, 238));
		composite.setBackgroundImage(null);

		Button btnNewButton_5 = new Button(composite, SWT.NONE);
		btnNewButton_5.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192657.jpg"));
		btnNewButton_5.setEnabled(false);

		btnNewButton_5.setBounds(580, 10, 120, 40);

		Button button = new Button(composite, SWT.NONE);
		button.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192615.jpg"));
		button.setBounds(20, 10, 120, 40);

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192714.jpg"));
		button_1.setBounds(160, 10, 120, 40);

		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192708.jpg"));
		button_2.setBounds(300, 10, 120, 40);

		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192702.jpg"));

		button_3.setEnabled(false);
		button_3.setBounds(440, 10, 120, 40);

		Button button_4 = new Button(composite, SWT.NONE);
		button_4.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192636.jpg"));

		button_4.setEnabled(false);
		button_4.setBounds(720, 10, 120, 40);

		Button button_5 = new Button(composite, SWT.NONE);
		button_5.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192646.jpg"));
		button_5.setEnabled(false);
		button_5.setBounds(860, 10, 120, 40);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331192651.jpg"));
		btnNewButton.setSelection(true);
		btnNewButton.setBounds(1154, 10, 120, 40);
		
		Button button_6 = new Button(composite, SWT.NONE);
		button_6.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331193048.jpg"));
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_6.setSelection(true);
		button_6.setBounds(1006, 10, 120, 40);
		button_6.setEnabled(false);
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(SetInfo.sl);
		SetInfo.composite=composite_1;
		sashForm.setWeights(new int[] {64, 677});
	//	MenuInfo menuInfo = new MenuInfo(composite_1, SWT.NONE);
	//	menuInfo.setSize(1424, 836);
		SetInfo.menu=new MenuInfo(composite_1, SWT.NONE);
		SetInfo.sit=new Sit(composite_1, SWT.NONE);
		SetInfo.epm=new EmployeeModify(composite_1, SWT.NONE);
		SetInfo.mem=new MenuModify(composite_1, SWT.NONE);
		SetInfo.pdm=new Predetemine(composite_1, SWT.NONE);
		SetInfo.cg=new Category(composite_1, SWT.NONE);
		SetInfo.orderManager=new OrderManager(composite_1, SWT.NONE);
		SetInfo.rev=new Revenue(composite_1, SWT.NONE);
		SetInfo.tmg=new TableManage(composite_1, SWT.NONE);

		SetInfo.sl.topControl=SetInfo.sit;
		EmployeeDao employeeDao =new EmployeeDao();
		//如果是管理员 将按钮设置为可见
		if(employeeDao.isAdmin(SetInfo.ename)){
			button_3.setEnabled(true);
			button_4.setEnabled(true);
			button_5.setEnabled(true);
			btnNewButton_5.setEnabled(true);
			button_6.setEnabled(true);

		}

		
		
		

		//订单管理
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.sl.topControl=SetInfo.rev;
				composite_1.layout();
			}
		});

		//订单管理
		button_6.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						SetInfo.sl.topControl=SetInfo.tmg;
						composite_1.layout();
					}
				});

		
		
		//订单管理
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.orderManager.setTable();
				SetInfo.sl.topControl=SetInfo.orderManager;
				composite_1.layout();
			}
		});
		//订餐按钮
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.menu.showTodaySp();
				SetInfo.sl.topControl=SetInfo.menu;
				composite_1.layout();
			}
		});
		//员工管理
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.sl.topControl=SetInfo.epm;
				composite_1.layout();
			}
		});
		//菜单管理
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.sl.topControl=SetInfo.mem;
				composite_1.layout();
			}
		});

		//预定
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.sl.topControl=SetInfo.pdm;
				composite_1.layout();
			}
		});
		//菜系管理
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.sl.topControl=SetInfo.cg;
				composite_1.layout();
			}
		});


		//返回首页
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetInfo.sit.showInfomation();
				SetInfo.sl.topControl=SetInfo.sit;
				composite_1.layout();
			}
		});

	}
}
