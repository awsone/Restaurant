package com.yc.panel;





import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.yc.bean.Employee;
import com.yc.dao.Dao;
import com.yc.dao.EmployeeDao;
import com.yc.dao.JDBC;
import com.yc.util.SetInfo;

import org.eclipse.swt.custom.SashForm;

public class EmployeeModify extends Composite {
	private Text positiontext;
	private Text numtext;
	private Text text_4;
	private Text passwordtext;
	private Table table;
	public Dao dao;
	private Text ispassword;
	public static boolean sex=true;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EmployeeModify(Composite parent, int style) {

		super(parent, style);
		setBackground(SWTResourceManager.getColor(255, 250, 240));
		setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		setLayout(null);
		setSize(1440,832);

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(326, 121, 80, 30);
		lblNewLabel.setText("\u59D3\u540D\uFF1A");

		Text nametext = new Text(this, SWT.BORDER);
		nametext.setBounds(458, 118, 150, 30);

		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label.setText("\u7F16\u53F7\uFF1A");
		label.setAlignment(SWT.CENTER);
		label.setBounds(790, 121, 80, 30);
		//label.setVisible(false);
		//label.setEnabled(false);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label_1.setText("\u804C\u4F4D\uFF1A");
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(326, 214, 80, 30);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label_2.setText("\u6027\u522B\uFF1A");
		label_2.setAlignment(SWT.CENTER);
		label_2.setBounds(790, 214, 80, 30);

		positiontext = new Text(this, SWT.BORDER);
		positiontext.setBounds(458, 211, 150, 30);

		numtext = new Text(this, SWT.BORDER);
		numtext.setBounds(932, 118, 150, 30);
		//numtext.setVisible(false);
		numtext.setEnabled(false);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label_4.setText("\u767B\u9646\u5BC6\u7801\uFF1A");
		label_4.setAlignment(SWT.CENTER);
		label_4.setBounds(790, 300, 80, 30);

		Label label_5 = new Label(this, SWT.CENTER);
		label_5.setBackground(SWTResourceManager.getColor(255, 250, 250));
		label_5.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_5.setAlignment(SWT.CENTER);
		label_5.setBounds(312, 400, 80, 30);

		Button maleRadioButton = new Button(this, SWT.RADIO);
		maleRadioButton.setForeground(SWTResourceManager.getColor(255, 250, 240));
		maleRadioButton.getSelection();
		maleRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		maleRadioButton.setBounds(960, 207, 30, 30);
		maleRadioButton.setText("男");
		maleRadioButton.setSelection(true);

		Button femaleRadioButton = new Button(this, SWT.RADIO);
		femaleRadioButton.setForeground(SWTResourceManager.getColor(255, 250, 240));
		femaleRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sex=false;
			}
		});
		femaleRadioButton.setText("女");
		femaleRadioButton.setBounds(1028, 207, 30, 30);

		text_4 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text_4.setBounds(458, 397, 150, 30);

		passwordtext = new Text(this, SWT.BORDER | SWT.PASSWORD);
		passwordtext.setBounds(932, 297, 150, 30);

		table = new Table(this, SWT.MULTI | SWT.FULL_SELECTION | SWT.CHECK); 
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				if (e.button == 3) {
					Menu menu = new Menu(table);  
					table.setMenu(menu);  
					MenuItem item = new MenuItem(menu, SWT.PUSH);  
					item.setText("删除");  
					item.addListener(SWT.Selection, new Listener() {  
						@Override
						public void handleEvent(Event event) {  
							TableItem item1 = (TableItem) table  
									.getSelection()[0];  
							Table parent = item1.getParent();  


							dao=Dao.getInstance();
							int selectedRow = table.getSelectionIndex();
							int row=table.getItemCount(); //获取表格的行数
							TableItem t = table.getItem(table.getSelectionIndex());  //获取选中的行
							String user=t.getText(1);      //被删除的用户名称
							table.remove(selectedRow);
							dao.dUserByName(user);
							table.removeAll();    //刷新，先删除所有的数据
							insertUserTable();

						}
					});  
				}  
			}
		});
		table.setBounds(300, 601, 840, 174);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(140);
		tblclmnNewColumn.setText("\u5E8F\u53F7");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(140);
		tblclmnNewColumn_1.setText("\u59D3\u540D");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(140);
		tblclmnNewColumn_2.setText("\u7F16\u53F7");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(140);
		tblclmnNewColumn_3.setText("\u804C\u4F4D");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(140);
		tblclmnNewColumn_4.setText("\u6027\u522B");

		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(140);
		tblclmnNewColumn_5.setText("\u89D2\u8272\u6743\u9650");

		Combo rolecombo = new Combo(this, SWT.NONE);
		rolecombo.setItems(new String[] {"用户", "管理员"});
		rolecombo.setBounds(458, 297, 150, 25);
		rolecombo.setText("员工");

		Button btnNewButton = new Button(this, SWT.BORDER);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
		});
		btnNewButton.setForeground(SWTResourceManager.getColor(240, 128, 128));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int row=table.getItemCount(); //获取表格的行数
				EmployeeDao employeedao=new EmployeeDao();
				String eno="";  //获取编号文本框
				String ename=nametext.getText().trim();   //姓名
				String esex;
				if(sex==true){
					esex="男";   //姓名
				}else{				
					esex="女";   //姓名
				}
				String eres=positiontext.getText().trim();  //职位
				String epwd=passwordtext.getText().trim();   //密码
				String ispwd=text_4.getText().trim();  //获取确认密码
				String admin=rolecombo.getText().trim();   //角色
				if(ispwd.equals(ispwd)){
					if((ename.equals("")) ||(esex.equals("")) ||
							(eres.equals("")) || (epwd.equals(""))){
						MessageDialog.openError(getShell(), "友情提示！", "请将带*号的内容填写完整！！");

						return;
					}
				}else{
					MessageDialog.openError(getShell(), "友情提示！", "两次输入密码不一致，请重新输入！！");

					return;
				}
				int role=0;
				if(admin.equals("管理员")){
					role=1;
				}else if(admin.equals("用户")){
					role=0;
				}
				Employee emp=new Employee();
				emp.setEname(ename);
				emp.setId(eno);
				emp.setPosition(eres);
				emp.setRole(role);
				emp.setPassword(epwd);
				emp.setSex(esex);
				employeedao.insertEmployee(emp);
				//信息提示框
				MessageDialog.openInformation(null, "友情提示", "数据添加成功！！");
				//	JOptionPane.showMessageDialog(null,"数据添加成功！！", "信息提示框",JOptionPane.INFORMATION_MESSAGE );
				JDBC.closeConnection();
				TableItem item = null;
				String row1=row+1+"";
				item = new TableItem(table, SWT.NONE);
				item.setText(new String[]{row1,ename,eno,eres,esex,role+""});
				table.removeAll();
				insertUserTable();
				nametext.setText("");
				numtext.setText("");
				positiontext.setText("");
				passwordtext.setText("");
				ispassword.setText("");
				text_4.setText("");
			}
		});
		btnNewButton.setBounds(350, 515, 80, 27);
		btnNewButton.setText("\u6DFB\u52A0");

		Button btnNewButton_1 = new Button(this, SWT.BORDER);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
		});
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dao=Dao.getInstance();
				int selectedRow = table.getSelectionIndex();
				//	System.out.println("1");
				int row=table.getItemCount(); //获取表格的行数
				TableItem t = table.getItem(table.getSelectionIndex());  //获取选中的行
				//	System.out.println(t);
				String user=t.getText(1);      //被删除的用户名称
				boolean i=MessageDialog.openConfirm(null, "友情提示","确定要删除用户“" +user+ "”?");

				if (i == true) {
					table.remove(selectedRow);
					dao.dUserByName(user);
					table.removeAll();    //刷新，先删除所有的数据
					insertUserTable();
					MessageDialog.openInformation(null, "友情提示", "删除用户成功！");
					nametext.setText("");
					numtext.setText("");
					positiontext.setText("");
					passwordtext.setText("");
					ispassword.setText("");

				}

			}
		});
		btnNewButton_1.setBounds(565, 515, 80, 27);
		btnNewButton_1.setText("\u5220\u9664");

		Button btnNewButton_2 = new Button(this, SWT.BORDER);
		//	if(isAdmin(SetInfo.ename)){}
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				dao=Dao.getInstance();
				int selectedRow = table.getSelectionIndex();   //获取选择的行下标
				if(selectedRow==-1){
					MessageDialog.openInformation(null, "友情提示", "请选择要修改的人员！");
					return;
				}
				numtext.setEnabled(true);
				ispassword.setEnabled(true);
				int row=table.getItemCount(); //获取表格的行数

				TableItem t = table.getItem(table.getSelectionIndex());  //获取选中的行


				String ename=t.getText(1);   //姓名

				String infos[]=new String[]{"确定要修改的账号："," 帐户名： "+ename};

				//int isOk=JOptionPane.showConfirmDialog(null, infos,"友情提示",JOptionPane.YES_NO_CANCEL_OPTION);

				boolean isOk=MessageDialog.openConfirm(null, "友情提示","确定要修改的账号："+" 帐户名： "+ename);


				//	System.out.println("225");

				if(isOk==true){
					nametext.setText(ename);
					positiontext.setText(t.getText(3));
					numtext.setText(t.getText(2));
					passwordtext.setText("");
					text_4.setText("");  
					nametext.setEditable(false);
					//		System.out.println("226");

				}

			}
		});
		btnNewButton_2.setBounds(790, 515, 80, 27);
		btnNewButton_2.setText("修改");

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBounds(326, 300, 80, 30);
		lblNewLabel_1.setText("角色：");

		Button btnNewButton_3 = new Button(this, SWT.BORDER);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String eno2=nametext.getText();
				int eno22=Integer.parseInt(numtext.getText().trim());  //获取编号文本框
				int admin2=0;
				String esex2;
				if(sex==true){
					esex2="男";   //姓名
				}else{				
					esex2="女";   //姓名
				}
				if(rolecombo.getText().trim().equals("管理员")){
					admin2=1;
				}else{
					admin2=0;
				}
				//String esex2=sexcombo.getText().trim();  //性别
				String eres2=positiontext.getText().trim();  //职位
				String epwd2=passwordtext.getText().trim();   //密码
				String ispwd2=text_4.getText().trim();  //获取确认密码
				//	int admin2=Integer.parseInt(rolecombo.getText().trim());   //角色
				dao.updateEmployeeByName(eno22,eno2,esex2,eres2,epwd2,admin2);


				table.removeAll();    //刷新，先删除所有的数据


				insertUserTable();

				JDBC.closeConnection();
				nametext.setEditable(true);
				nametext.setText("");
				positiontext.setText("");
				passwordtext.setText("");
				text_4.setText("");


			}
		});
		btnNewButton_3.setBounds(989, 515, 80, 27);
		btnNewButton_3.setText("完成");



		//	femaleRadioButton.is

		Label label_3 = new Label(this, SWT.CENTER);
		label_3.setText("原始密码：");
		label_3.setBackground(SWTResourceManager.getColor(255, 250, 250));
		label_3.setAlignment(SWT.CENTER);
		label_3.setBounds(790, 400, 80, 30);
		//	label_3.setVisible(false);       //设置原始密码框不可见

		ispassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		ispassword.setBounds(932, 397, 150, 30);
		ispassword.setEnabled(false);

		Label lblNewLabel_2 = new Label(this, SWT.BORDER);
		lblNewLabel_2.setBounds(0, 0, 1440, 50);
		//	ispassword.setVisible(false);

		Group group=new Group(parent, style);
		insertUserTable();
		lblNewLabel_2.setImage(SWTResourceManager.getImage("res/picture/191237-106.jpg"));





	}



	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}



	/**
	 * 判断员工编号是否已经存在
	 * @return
	 */
	private boolean idHad(){
		return false;}


	public void insertUserTable(){

		int index=0;
		Connection conn = JDBC.getConnection();
		String ss="select* from employer";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(ss);
			while(rs.next()){
				//	String index1=(index++)+"";
				String a=rs.getString("ename");
				String b=rs.getString("eno");
				String c=rs.getString("eres");
				String d=rs.getString("esex");
				String e=rs.getString("admin");
				TableItem item=new TableItem(table,SWT.NONE);

				if(e.equals("0")){
					item.setText(new String[]{(index++)+"",a,b,c,d,"员工"});
				}else if(e.equals("1")){
					item.setText(new String[]{(index++)+"",a,b,c,d,"管理员"});

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {			
				conn.close();

				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}
}
