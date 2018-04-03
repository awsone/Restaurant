package com.yc.panel;




import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;



import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.TableCursor;

import com.yc.dao.DBHelper;
import com.yc.dao.DBToExcel;
import com.yc.dao.MenuDao;
import com.yc.util.ScaleImage;

public class MenuModify extends Composite {
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private String filePath;
	private Table table;




	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MenuModify(Composite parent, int style) {
		super(parent, style);
		setSize(1440,832);
		setForeground(SWTResourceManager.getColor(255, 0, 0));
		setBackground(SWTResourceManager.getColor(255, 250, 240));

		Label label = new Label(this, SWT.CENTER);
		label.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label.setText("\u83DC\u540D\uFF1A");
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setBounds(240, 86, 70, 30);

		Label label_1 = new Label(this, SWT.CENTER);
		label_1.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label_1.setText("\u83DC\u7CFB\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(240, 261, 70, 30);

		Label label_2 = new Label(this, SWT.CENTER);
		label_2.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label_2.setText("\u4EF7\u683C\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_2.setBounds(240, 166, 70, 30);

		Label label_3 = new Label(this, SWT.CENTER);
		label_3.setEnabled(true);
		label_3.setTouchEnabled(true);
		label_3.setBackground(SWTResourceManager.getColor(255, 250, 240));
		label_3.setText("\u63CF\u8FF0\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_3.setBounds(240, 369, 70, 30);

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(376, 87, 180, 30);

		text_2 = new Text(this, SWT.BORDER);
		text_2.setEnabled(true);
		text_2.setBounds(376, 262, 180, 30);

		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(376, 167, 180, 30);

		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(376, 356, 487, 78);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});

		btnNewButton.setForeground(SWTResourceManager.getColor(255, 255, 240));
		btnNewButton.setBounds(1059, 290, 80, 27);
		btnNewButton.setText("修改图片");

		Label lblNewLabel_1 = new Label(this, SWT.NONE);

		lblNewLabel_1.setBounds(1022, 90, 150, 150);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(MenuModify.class,"/Image/zanwu.jpg"));
		Button btnNewButton_1 = new Button(this, SWT.NONE);

		btnNewButton_1.setBounds(555, 495, 80, 27);
		btnNewButton_1.setText("添加");

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(255, 250, 240));

		lblNewLabel_2.setBounds(726, 197, 168, 20);
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
	
		btnNewButton_2.setBounds(750, 495, 80, 27);
		btnNewButton_2.setText("修改");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(333, 561, 774, 185);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(130);
		tblclmnNewColumn.setText("            图片");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(80);
		tblclmnNewColumn_1.setText("编号");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(120);
		tblclmnNewColumn_2.setText("菜名");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(110);
		tblclmnNewColumn_3.setText("菜系");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(80);
		tblclmnNewColumn_4.setText("价格");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(250);
		tblclmnNewColumn_6.setText("描述");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 1440, 50);
	lblNewLabel.setImage(SWTResourceManager.getImage("res/picture/191237-106.jpg"));

		//选择图片事件监听
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog  dialog =new FileDialog(parent.getShell());
				dialog.setText("图像选择");
				dialog.setFilterExtensions(new String []{"*.jpg","*.gif","*.png"});
				filePath=dialog.open();
				if(filePath!=null&&!"".equals(filePath)){
					File file =new File(filePath);
					if(file.exists()){
						InputStream is=null;
						try {
							is=new FileInputStream(file);
							//缩略图片
							ImageData imagedata=new ImageData(is);
							imagedata=imagedata.scaledTo(100, 100);
							Image image =new Image(Display.getDefault(),imagedata);
							lblNewLabel_1.setImage(image);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}finally{
							if(is!=null){
								try {
									is.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				}
			}
		});
		//菜单管理添加按钮的事件监听
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			Integer count=0;
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				String mname  =text_1.getText().trim();
				String sid    =text_2.getText().trim();
				String mprice =text_3.getText().trim();
				String mmemo  =text_4.getText().trim();
				if(mname==null||"".equals(mname)||sid==null||"".equals(sid)||mprice==null||"".equals(mprice)){
					lblNewLabel_2.setText("请填写相应信息！！！");
					return;
				}
				byte [] photo=null;
				if(filePath!=null&&!"".equals(filePath)){
					File file=new File(filePath);
					if(file.exists()){
						InputStream is=null;
						try {
							is =new FileInputStream(file);
							photo=new byte[is.available()];
							is.read(photo);
						} catch (Exception e1) {
							e1.printStackTrace();
						}finally{
							if(is!=null){
								try {
									is.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				}
				//访问数据库
				MenuDao menuDao=new MenuDao();
				int result=menuDao.addMenu( mname, sid, mprice, mmemo, photo);
				if(result>0){//添加成功后将所有text置空
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					lblNewLabel_1.setImage(SWTResourceManager.getImage(MenuModify.class,"/Image/zanwu.jpg"));
					

					TableItem ti=new TableItem(table,SWT.NONE);
					MenuDao dao=new MenuDao();
					Map <String,Object> map=dao.findMenu(mname);
					if(map!=null&& !map.isEmpty()){
						ti.setText(new String []{ "",map.get("mid").toString(),map.get("mname").toString(),
						map.get("sid").toString(),map.get("mprice").toString(),map.get("mmemo").toString()});	
						//在表格中显示图片
						Object obj=map.get("photo");
						if(obj!=null){
							byte[]bt=(byte[])obj;
							ti.setImage(ScaleImage.byteToImage(bt,50,50));
						}
					}
					
				}
			}
		});
		//修改菜谱的事件监听
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBHelper db=new DBHelper();
				DBToExcel dBToexcel=new DBToExcel();
				String sql="select * from menu";
				ResultSet rs=db.getResultSet(sql);
				String[] colnames;
				try {
					colnames = db.getColNames(rs);
					Vector columnName=StringToVector(colnames);
					dBToexcel.WriteExcel(rs, "F:\\java学习\\menu.xls", "菜谱", columnName);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public Vector StringToVector(String[] colnames){
		Vector vec=new Vector();
		for(String colname:colnames){ 
			vec.addElement(colname);
		}
		return vec;
		
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
