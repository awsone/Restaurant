package com.yc.panel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.dao.Dao;
import com.yc.dao.JDBC;
import com.yc.util.Validate;

import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;


/**
 * 用于餐桌的增删
 * @author 大白猫😀😀😀小地瓜
 *
 */
public class TableManage extends Composite {
	private Text seattext;
	private Table table;
	private Dao dao=Dao.getInstance();


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TableManage(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(255, 250, 240));
		setSize(1440,832);
		setLayout(new FormLayout());

		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 50);
		fd_composite.right = new FormAttachment(0, 1440);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\191237-106.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 250, 240));
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(composite, 121);
		fd_label.left = new FormAttachment(0, 536);
		label.setLayoutData(fd_label);
		label.setText("座位数：");
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);

		seattext = new Text(this, SWT.BORDER);
		fd_label.right = new FormAttachment(100, -824);
		FormData fd_seattext = new FormData();
		fd_seattext.top = new FormAttachment(label, -11, SWT.TOP);
		fd_seattext.bottom = new FormAttachment(label, 23);
		fd_seattext.left = new FormAttachment(label, 99);
		fd_seattext.right = new FormAttachment(100, -583);
		seattext.setLayoutData(fd_seattext);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 337);
		fd_table.left = new FormAttachment(0, 304);
		fd_table.right = new FormAttachment(100, -314);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(200);
		tblclmnNewColumn.setText("               序号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(200);
		tblclmnNewColumn_1.setText("台号");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(200);
		tblclmnNewColumn_2.setText("座位数");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(196);
		tblclmnNewColumn_3.setText("状态");

		TableCursor tableCursor = new TableCursor(table, SWT.NONE);


		Button btnNewButton = new Button(this, SWT.NONE);
		fd_table.bottom = new FormAttachment(btnNewButton, -19);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0, 440);
		fd_btnNewButton.bottom = new FormAttachment(100, -31);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {


				System.out.println("1");
				int row=table.getItemCount(); //获取表格的行数

			//	int num=Integer.parseInt(numtext.getText().trim());       //获取台号，去掉首尾空格
				String seat=seattext.getText().trim();         //获取座位号
			//	String isnum=num+"";    //将int型转化为String型
				String state="t";
				System.out.println("2");


				if(seat.equals("")){
					MessageDialog.openError(getShell(), "友情提示！", "请输入台号和座位号！！");


					return;
				}
				System.out.println("3");
				
				if (!Validate.execute("[1-9]{1}([0-9]{0,1})", seat)) {// 验证座位数是否在1——19之间
					String[] infos = { "座位数输入错误！", "座位数必须在 1——99 之间！" };
					//	JOptionPane.showMessageDialog(null, infos, "友情提示",
					//			JOptionPane.INFORMATION_MESSAGE);
					MessageDialog.openError(getShell(), "友情提示！",  "座位数输入错误！"+ "座位数必须在 1——99 之间！");



					seattext.forceFocus();              // 为座位数文本框请求获得焦点
					return;
				}
				//从数据库查询是否有该编号
				new JDBC().getConnection();
			

				seattext.setText("");

				dao.insertDesk(seat,state);

				JDBC.closeConnection();

				TableItem item = null;
				String row1=row+1+"";
				item = new TableItem(table, SWT.NONE);
				//item.setText(new String[]{row1,isnum,seat,"T"});

				table.removeAll();

				insertTable();



			}
		});
		btnNewButton.setText("添加");

		Button btnNewButton_1 = new Button(this, SWT.NONE);
		fd_btnNewButton.right = new FormAttachment(100, -920);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_1.left = new FormAttachment(btnNewButton, 140);
		fd_btnNewButton_1.right = new FormAttachment(0, 740);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int selectedRow = table.getSelectionIndex();// 获得选中的餐台
				if (selectedRow == -1) {// 未选中任何餐台
					MessageDialog.openError(getShell(), "友情提示！",  "请选择要删除的餐台！");


				} else {
					TableItem t = table.getItem(table.getSelectionIndex());  //获取选中的行
					System.out.println(t);
					int deskNum=Integer.parseInt(t.getText(1));      //被删除的餐桌号
					System.out.println(deskNum);


					int index=table.getSelectionIndex();      //选中行餐桌的角标


					String infos[]=new String[]{};
					//int isOk=JOptionPane.showConfirmDialog(null, infos,"友情提示",JOptionPane.YES_NO_CANCEL_OPTION);


					boolean isOK=MessageDialog.openConfirm(getShell(), "友情提示！", "确定要删除的台号："+" 台号： "+deskNum+"  座位数： "+t.getText(2).toString());



					if(isOK==true){


						table.remove(selectedRow);
						System.out.println("8");

						dao.dDeskByNum(deskNum);
						System.out.println("9");

						table.removeAll();    //刷新，先删除所有的数据
						System.out.println("10");

						insertTable();




					}
				}
			}
		});
		btnNewButton_1.setText("删除");

		Button btnNewButton_2 = new Button(this, SWT.NONE);
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_2.left = new FormAttachment(btnNewButton_1, 147);
		fd_btnNewButton_2.right = new FormAttachment(100, -473);
		btnNewButton_2.setLayoutData(fd_btnNewButton_2);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
				//	System.exit(0);

			}
		});
		btnNewButton_2.setText("退出");

		insertTable();

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void insertTable(){
		int index=0;
		Connection conn = JDBC.getConnection();
		String ss="select* from dintable";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(ss);
			while(rs.next()){
				//	String index1=(index++)+"";
				String a=rs.getString("dno");
				String b=rs.getString("dtype");
				String c=rs.getString("dstate");
				TableItem item=new TableItem(table,SWT.NONE);
				item.setText(new String[]{(index++)+"",a,b,c});
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
