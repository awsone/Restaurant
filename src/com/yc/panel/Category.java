package com.yc.panel;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.dao.Dao;
import com.yc.dao.JDBC;
import com.yc.util.Validate;

import org.eclipse.swt.events.MouseAdapter;

public class Category extends Composite {
	private Text categorytext;
	private Table table;
	public Dao dao;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Category(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(255, 250, 240));
		setLayout(null);
		setSize(1440,832);


		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(274, 190, 80, 30);
		lblNewLabel.setText("\u83DC\u7CFB\u540D\u79F0\uFF1A");

		categorytext = new Text(this, SWT.BORDER);
		categorytext.setBounds(413, 190, 246, 30);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("1");
	//			int num=Integer.parseInt(numtext.getText().trim());       //获取菜系编号
				String dishName=categorytext.getText().trim();         //获取菜系名
	//			String isnum=num+"";    //将int型转化为String型
				if(dishName.equals("")){
					JOptionPane.showMessageDialog(null, "请输入菜系名！！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				System.out.println("2");

				if (dishName.length() > 10) {// 查看菜系名称的长度是否超过了10个汉字
					JOptionPane.showMessageDialog(null, "菜系名称最多只能为10个汉字！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				dao=Dao.getInstance();

				if (dao.sSortByName(dishName).size() > 0) {// 查看该菜系名称是否已经存在
					JOptionPane.showMessageDialog(null, "该菜系已经存在！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				int row=table.getItemCount();// 获得当前拥有菜系名称的个数
			//	numtext.setText("");

				categorytext.setText("");

			//	dao.insertSort(num, dishName);   //要求将菜系名自己增加，因此去掉名字
				dao.insertSort(dishName);

				JDBC.closeConnection();
				TableItem item = null;
				String row1=row+"";
				item = new TableItem(table, SWT.NONE);
				item.setText(new String[]{row1,0+"",dishName});
				table.removeAll();    //刷新，先删除所有的数据

				insertSortTable();

			}
		});
		btnNewButton.setBounds(788, 187, 80, 30);
		btnNewButton.setText("\u6DFB\u52A0");

		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dao=Dao.getInstance();
				int selectedRow = table.getSelectionIndex();// 获得选中的菜系
				if (selectedRow == -1) {// 未选中任何菜系
					JOptionPane.showMessageDialog(null, "请选择要删除的菜系！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					TableItem t = table.getItem(table.getSelectionIndex());  //获取选中的行
					String sortName=t.getText(2);      //被删除的菜系名称
					//	int index=table.getSelectionIndex();      //选中行餐桌的角标
					String infos[]=new String[]{"确定要删除的菜系："," 菜系号： "+t.getText(1),"  菜系名： "+t.getText(2).toString()};

					int isOk=JOptionPane.showConfirmDialog(null, infos,"友情提示",JOptionPane.YES_NO_CANCEL_OPTION);

					if(isOk==0){
						System.out.println("7");

						table.remove(selectedRow);
						System.out.println("8");

						dao.dSortByName(sortName);
						System.out.println("9");

						table.removeAll();    //刷新，先删除所有的数据
						System.out.println("10");

						insertSortTable();
						//			int rowCount=table.getItemCount();   //获取删除后拥有的餐桌数

					}
					//		System.out.println(index);   
					//		table.remove(index);
					JDBC.closeConnection();// 关闭数据库连接

				}
			}
		});
		btnNewButton_1.setBounds(948, 187, 80, 30);
		btnNewButton_1.setText("\u5220\u9664");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {  
				if (e.button == 3) {
					System.out.println("点击了鼠标的右键！！");
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
							
								TableItem t = table.getItem(table.getSelectionIndex());  //获取选中的行
								System.out.println(t);
								String sortName=t.getText(2);      //被删除的菜系名称
								System.out.println("10011");

								if (parent != null) {  
									table.remove(table.getSelectionIndices());  
								} 
								dao.dSortByName(sortName);
								table.removeAll();    //刷新，先删除所有的数据
								insertSortTable();
							JDBC.closeConnection();// 关闭数据库连接
															

						}
					});  
				}  
			}
		});
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});


		table.setBounds(380, 364, 600, 300);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);




		

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setText("                     序号");
		tblclmnNewColumn.setWidth(200);

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(200);
		tblclmnNewColumn_1.setText("菜系编号");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(195);
		tblclmnNewColumn_2.setText("菜系种类");
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.setBounds(0, 0, 1440, 50);
		insertSortTable();
		btnNewButton_2.setImage(SWTResourceManager.getImage("res/picture/191237-106.jpg"));


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}


	public void insertSortTable(){
		int index=table.getItemCount(); //获取表格的行数
		;
		Connection conn = JDBC.getConnection();
		String ss="select* from sort order by sid";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(ss);
			while(rs.next()){
				//	String index1=(index++)+"";
				String a=rs.getString("sid");
				String b=rs.getString("sname");
				//		String c=rs.getString("dstate");
				TableItem item=new TableItem(table,SWT.NONE);
				item.setText(new String[]{(index++)+"",a,b});
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
