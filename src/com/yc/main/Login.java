package com.yc.main;


import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;



import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.dao.EmployeeDao;
import com.yc.panel.Primary1;
import com.yc.panel.Register;
import com.yc.util.RegeditUtil;
import com.yc.util.SetCenter;
import com.yc.util.SetInfo;


public class Login {

	protected Shell lshell;
	protected  Display display;
	private RegeditUtil regeditUtil=new RegeditUtil();

	private Text text_1;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		lshell.open();
		lshell.layout();
		while (!lshell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		lshell = new Shell();
		lshell.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\logo.png"));
		lshell.setSize(720, 490);
		lshell.setText("味道先生餐饮系统");
		SetCenter.setCenter(lshell);
		lshell.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(lshell, SWT.NONE);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\login11.jpg"));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		composite_1.setBounds(170, 111, 339, 202);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 33, 65, 29);
		lblNewLabel.setText("账号：");

		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblNewLabel_1.setBounds(21, 86, 54, 29);
		lblNewLabel_1.setText("密码：");

		text_1 = new Text(composite_1, SWT.PASSWORD|SWT.BORDER);
		
		
		
		text_1.setBounds(81, 83, 213, 25);

		Button btnCheckButton = new Button(composite_1, SWT.CHECK);

		btnCheckButton.setBounds(81, 116, 106, 24);
		btnCheckButton.setText("记住密码");
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\dl.jpg"));
//btnNewButton.setBackgroundImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\dl.jpg"));

		btnNewButton.setBounds(129, 155, 100, 37);

		Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setBounds(81, 30, 213, 32);

		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setBounds(215, 120, 56, 25);
		EmployeeDao eDao=new EmployeeDao();
		List<Map<String,Object>> list=eDao.getAllEmployee();
		for(Map<String,Object> map:list){
			combo.add(String.valueOf(map.get("ename")));
		}
		lblNewLabel_2.setBackground(lblNewLabel_2.getParent().getBackground());
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setText("注册");
		//注册的事件监听
		lblNewLabel_2.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Register register=new Register(lshell, SWT.APPLICATION_MODAL|SWT.DIALOG_TRIM);
				register.open();
			}
		});
		Map<String ,String> adminInfos=regeditUtil.get();
		if(adminInfos!=null&&!adminInfos.isEmpty()){
			Set<String> keys=adminInfos.keySet();
			for(String key:keys){
				combo.add(key);
			}
			combo.select(0);
			//在密码框中显示对应账号的密码
			text_1.setText(adminInfos.get(combo.getText()));
			//勾上记住密码的框
			//	remember.setSelection(true);

		}
		//当账号框中的值发生改变时，密码框中的内容跟着改变
		combo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				String key=combo.getText().trim();
				if(adminInfos!=null&&!adminInfos.isEmpty()&&adminInfos.containsKey(key)){
					text_1.setText(adminInfos.get(key));
					//	remember.setSelection(true);
				}else{
					text_1.setText("");
					//取消勾选
					//remember.setSelection(false);
				}
			}
		});
		//登录事件监听

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ename=combo.getText().trim();
				String epwd=text_1.getText().trim();

				if((ename==null||"".equals(ename))||(epwd==null||"".equals(epwd))){
					MessageDialog.openInformation(lshell, "友情提示", "用户名或密码不能为空");
					return;
				}
				//访问数据库
				EmployeeDao employeDao=new EmployeeDao();

				Map<String,Object> map=employeDao.adminLogin(ename, epwd);
				if( map!=null&&!map.isEmpty() ){//
					Primary1 primary1=new Primary1();
					if(btnCheckButton.getSelection()){
						//如果用户勾选了记住密码？
						regeditUtil.addRecord(ename, epwd);
					}else{//如果没有选中，则从注册表中移除
						regeditUtil.remove(ename);


					}
					SetInfo.ename=ename;
					lshell.dispose();
					primary1.open();


				}
			}
		});

		//composite.setBackgroundImage(new Image(display,Login.class.getClassLoader().getResourceAsStream("Image/bkg.png")));
	}
}

