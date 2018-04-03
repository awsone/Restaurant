package com.yc.panel;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;



import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseWheelListener;

import com.yc.dao.ConsumerDao;
import com.yc.dao.SitDao;
import com.yc.util.SetInfo;

public class Sit extends Composite {
	public Map<String,ShowData> map = new  HashMap<String,ShowData>();
	protected Shell shell;
	Label lblNewLabel_14;
	Label lblNewLabel_11;
	Label lblNewLabel_10;
	Label lblNewLabel_6;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	
	public void createTable(Composite composite){
		SitDao sitDao=new SitDao();
		//获得所有的餐桌信息。
		SetInfo.lists=sitDao.getAll();
		int width = composite.getSize().x;
		//System.out.println(width);
		int cols = 0;
		int rows = 0;
		ShowData showData;
		for (int i = 0; i<SetInfo.lists.size(); i++, cols++){

			showData = new ShowData(cols*220+20, rows*140+20, composite,String.valueOf(SetInfo.lists.get(i).get("dno")) );
			showData.create(SetInfo.lists.get(i).get("dstate").toString(),SetInfo.lists.get(i).get("dtype").toString());
			map.put(SetInfo.lists.get(i).get("dno").toString(), showData);
			if((i+1)%5==0){
				rows++;
				cols=-1;
			}
		
		}
	}

	public Sit(Composite parent, int style) {
		super(parent, style);
		setSize(1440,832);
		setLayout(new FillLayout(SWT.HORIZONTAL));



		SashForm sashForm = new SashForm(this, SWT.NONE);



		Composite composite = new Composite(sashForm, SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331194150.jpg"));

		Label lblNewLabel_12 = new Label(composite, SWT.NONE);
		lblNewLabel_12.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.BOLD));
		lblNewLabel_12.setBounds(149, 747, 246, 33);
		Date date=new Date(System.currentTimeMillis());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm EEE");
		lblNewLabel_12.setText(format.format(date));
		Label lblNewLabel_13 = new Label(composite, SWT.NONE);
		lblNewLabel_13.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.BOLD));
		lblNewLabel_13.setBounds(10, 747, 115, 33);
		lblNewLabel_13.setText("当 前 时 间：");






		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\QQ图片20180331194150.jpg"));
		composite_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));

		Composite composite_17 = new Composite(composite_1, SWT.NONE);
		composite_17.setBackground(SWTResourceManager.getColor(210, 105, 30));
		composite_17.setBounds(10, 25, 120, 120);

		Label lblNewLabel_5 = new Label(composite_17, SWT.NONE);
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(210, 105, 30));
		lblNewLabel_5.setAlignment(SWT.CENTER);
		lblNewLabel_5.setBounds(10, 79, 81, 31);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_5.setText("全部餐桌");

		lblNewLabel_6 = new Label(composite_17, SWT.NONE);
		lblNewLabel_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(210, 105, 30));
		lblNewLabel_6.setAlignment(SWT.CENTER);
		lblNewLabel_6.setBounds(35, 29, 30, 23);
		lblNewLabel_6.setText((new SitDao().countdesk())+"");

		Composite composite_18 = new Composite(composite_1, SWT.NONE);
		composite_18.setBackground(SWTResourceManager.getColor(72, 209, 204));
		composite_18.setBounds(179, 25, 120, 120);

		Label lblNewLabel_7 = new Label(composite_18, SWT.NONE);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(72, 209, 204));
		lblNewLabel_7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_7.setAlignment(SWT.CENTER);
		lblNewLabel_7.setBounds(10, 79, 100, 31);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_7.setText("可用餐桌");

		lblNewLabel_10 = new Label(composite_18, SWT.NONE);
		lblNewLabel_10.setBackground(SWTResourceManager.getColor(72, 209, 204));
		lblNewLabel_10.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_10.setAlignment(SWT.CENTER);
		lblNewLabel_10.setBounds(35, 25, 41, 27);
	///	lblNewLabel_10.setText("15");

		Composite composite_19 = new Composite(composite_1, SWT.NONE);
		composite_19.setBackground(SWTResourceManager.getColor(50, 205, 50));
		composite_19.setBounds(10, 229, 120, 120);

		Label lblNewLabel_9 = new Label(composite_19, SWT.NONE);
		lblNewLabel_9.setBackground(SWTResourceManager.getColor(50, 205, 50));
		lblNewLabel_9.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_9.setAlignment(SWT.CENTER);
		lblNewLabel_9.setBounds(10, 79, 81, 31);
		lblNewLabel_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_9.setText("在用餐桌");

		lblNewLabel_11 = new Label(composite_19, SWT.NONE);
		lblNewLabel_11.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_11.setBackground(SWTResourceManager.getColor(50, 205, 50));
		lblNewLabel_11.setAlignment(SWT.CENTER);
		lblNewLabel_11.setBounds(29, 26, 35, 23);
		lblNewLabel_11.setText("0");

		Composite composite_20 = new Composite(composite_1, SWT.NONE);
		composite_20.setBackground(SWTResourceManager.getColor(255, 182, 193));
		composite_20.setBounds(179, 229, 120, 120);

		 lblNewLabel_14 = new Label(composite_20, SWT.NONE);
		lblNewLabel_14.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_14.setBackground(SWTResourceManager.getColor(255, 182, 193));
		lblNewLabel_14.setAlignment(SWT.CENTER);
		lblNewLabel_14.setBounds(44, 20, 33, 31);
		lblNewLabel_14.setText("0");

		Label lblNewLabel_8 = new Label(composite_20, SWT.NONE);
		lblNewLabel_8.setForeground(SWTResourceManager.getColor(255, 255, 240));
		lblNewLabel_8.setBackground(SWTResourceManager.getColor(255, 182, 193));
		lblNewLabel_8.setAlignment(SWT.CENTER);
		lblNewLabel_8.setBounds(10, 79, 100, 31);
		lblNewLabel_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_8.setText("预定餐桌");
		createTable(composite);
		sashForm.setWeights(new int[] {742, 215});
		////////////////////////////////////
		/*btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				SitDao sitDao=new SitDao();
				Map<String,Object> map= sitDao.getAllTable();
				lblNewLabel_6.setText(String.valueOf(map.get("总桌数")));
				map=sitDao.getEmptyTable();
				lblNewLabel_10.setText(String.valueOf(map.get("空闲数")));
				map=sitDao.getEatTable();
				lblNewLabel_11.setText(String.valueOf(map.get("用餐数")));
				map=sitDao.getOrderTable();
				lblNewLabel_14.setText(String.valueOf(map.get("预定数")));
				createTable(composite);
			
			}
		});*/
		
		
		/*//获得所有的餐桌信息。
		SetInfo.lists=sitDao.getAll();
		int width = composite.getSize().x;
		//System.out.println(width);
		int cols = 0;
		int rows = 0;
		ShowData showData;
		for (int i = 0; i<SetInfo.lists.size(); i++, cols++){

			showData = new ShowData(cols*220+20, rows*140+20, composite,String.valueOf(SetInfo.lists.get(i).get("dno")) );
			showData.create();
			map.put("t_" + i, showData);
			
			
			

			if((i+1)%5==0){
				rows++;
				cols=-1;
			}
	
		}
*/
	}

	public void showInfomation(){
		SitDao sitDao=new SitDao();
		Map<String,Object> map= sitDao.getAllTable();
		lblNewLabel_6.setText(String.valueOf(map.get("count(*)")));
		map=sitDao.getEmptyTable();
		lblNewLabel_10.setText(String.valueOf(map.get("空闲数")));
		map=sitDao.getEatTable();
		lblNewLabel_11.setText(String.valueOf(map.get("用餐数")));
		map=sitDao.getOrderTable();
		lblNewLabel_14.setText(String.valueOf(map.get("预定数")));
	}
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	class ShowData{
		private Composite shell;
		private Composite composite;
		private Label label_1;
		private Label label_2;
		private Label label_3;

		private int x;
		private int y;
		private String nid;
		/**
		 * 
		 * @param x
		 * @param y
		 * @param shell  父窗体
		 * @param nid
		 */
		public ShowData(int x, int y, Composite shell, String nid){
			this.x = x;
			this.y = y;
			this.shell = shell;
			this.nid = nid;
		}
		/**
		 * 设置背景颜色
		 */
		public  void setBackgroundColor(String dsdate){
			
			if(dsdate.equals("用餐")){//用餐状态。
				composite.setBackground(SWTResourceManager.getColor(244, 164, 96));
				label_2.setBackground(label_2.getParent().getBackground());
				label_1.setBackground(label_1.getParent().getBackground());
				label_3.setBackground(label_3.getParent().getBackground());

			}else if(dsdate.equals("空闲")){//空闲状态
				composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));

				label_2.setBackground(label_2.getParent().getBackground());
				label_1.setBackground(label_1.getParent().getBackground());
				label_3.setBackground(label_3.getParent().getBackground());
			}else if(dsdate.equals("预定")){//预定状态
				composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				label_2.setBackground(label_2.getParent().getBackground());
				label_1.setBackground(label_1.getParent().getBackground());
				label_3.setBackground(label_3.getParent().getBackground());
			}
			label_2.setText(dsdate);


		}
		
		
		public void create(String dstate,String dtype ){
			composite= new Composite(shell, SWT.NONE);
			composite.setBounds(x, y, 120, 110);
			composite.setBackground(SWTResourceManager.getColor(244, 164, 96));
			label_1 = new Label(composite, SWT.NONE);
			label_1.setAlignment(SWT.CENTER);
			label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
			label_1.setBounds(0, 10, 61, 17);	
			label_1.setText(nid);
			label_1.setBackground(label_1.getParent().getBackground());

			label_2 = new Label(composite, SWT.NONE);
			label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
			label_2.setAlignment(SWT.CENTER);
			label_2.setBounds(59, 49, 61, 17);
			label_2.setText(dstate);
		//	label_2.setBackground(label_2.getParent().getBackground());

			label_3 = new Label(composite, SWT.NONE);
			label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
			label_3.setAlignment(SWT.CENTER);
			label_3.setBounds(36, 87, 70, 17);
			label_3.setText("可供"+dtype+"人");

			setBackgroundColor(dstate);

			composite.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
				}
			});

			Menu menu = new Menu(composite);
			composite.setMenu(menu);

			MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
			mntmNewItem.setText("开桌");
			//开桌事件
			mntmNewItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					//判断该桌是否是空闲
					if(label_2.getText().equals("用餐")){
						MessageDialog.openInformation(getShell(), "温馨提示", "抱歉，该桌正在用餐呢。emmmm...");
						return;
					}else if(label_2.getText().equals("预定")){

						//		MessageDialog.openQuestion(parent, title, message)
						Boolean b=MessageDialog.openQuestion(getShell(), "温馨提示", "是否为预定客户开桌？");
						//System.out.println(b);
						if(!b){
							return;
						}else{
						//	System.out.println("hahah");
							SitDao sitDao=new SitDao();
							Date date=new Date(System.currentTimeMillis());
							DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							SetInfo.tempConsumer.put("dno", nid);
							SetInfo.menu.showMenu();
							SetInfo.menu.showTodaySp();
							SetInfo.sl.topControl=SetInfo.menu;
							SetInfo.composite.layout();
							
						}
					}else{

						SitDao sitDao=new SitDao();
						Date date=new Date(System.currentTimeMillis());
						DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						SetInfo.tempConsumer.put("dno", nid);
						SetInfo.menu.showMenu();
						SetInfo.menu.showTodaySp();
						SetInfo.sl.topControl=SetInfo.menu;
						SetInfo.composite.layout();
					}
				}
			});

		}
	}
}


