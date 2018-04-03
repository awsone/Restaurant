package com.yc.panel;



import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.dao.ConsumerDao;
import com.yc.dao.DBHelper;
import com.yc.dao.GoodTypeDao;
import com.yc.dao.MenuDao;
import com.yc.dao.SitDao;
import com.yc.dao.TempItemDao;
import com.yc.panel.Sit.ShowData;
import com.yc.util.ScaleImage;
import com.yc.util.SetInfo;

public class MenuInfo extends Composite {
	private Table table;
	private Table table_1;
	double count=0;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	double pay = 0,keep =0;
	String mname;
	Label lblNewLabel_2;
	Label lblNewLabel ;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MenuInfo(Composite parent, int style) {
		super(parent, style);
		setTouchEnabled(true);
		setLayout(new FillLayout(SWT.VERTICAL));
		setSize(1440,832);
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setTouchEnabled(true);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		//composite_1.setLayout(new StackLayout());

		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_3 = new SashForm(composite_2, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);

		Composite composite_6 = new Composite(sashForm_3, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));

		Tree tree = new Tree(composite_6, SWT.BORDER);

		Composite composite_7 = new Composite(sashForm_3, SWT.NONE);
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		//随机图片
		lblNewLabel = new Label(composite_7, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);


		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		lblNewLabel.setBounds(59, 279, 125, 36);
		lblNewLabel.setText("今日特价：");

		/*Label lblNewLabel_1 = new Label(composite_7, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_1.setBounds(10, 66, 115, 115);*/

		lblNewLabel_2 = new Label(composite_7, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_2.setBounds(43, 92, 150, 150);
		formToolkit.adapt(lblNewLabel_2, true, true);
		
		Label lblNewLabel_1 = new Label(composite_7, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_1.setBounds(10, 10, 200, 30);
		formToolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("今日特价");
		sashForm_3.setWeights(new int[] {410, 264});

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_2 = new SashForm(composite_3, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);

		Composite composite_4 = new Composite(sashForm_2, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_4 = new SashForm(composite_4, SWT.NONE);

		Composite composite_8 = new Composite(sashForm_4, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_12 = new Composite(composite_8, SWT.NONE);
		composite_12.setLayout(new FillLayout(SWT.HORIZONTAL));

		table_1 = new Table(composite_12, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBackground(SWTResourceManager.getColor(255, 255, 255));

		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(150);
		tblclmnNewColumn_3.setText("    图片");

		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(200);
		tblclmnNewColumn_5.setText("菜品名称");

		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("价格");

		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_7.setWidth(334);
		tblclmnNewColumn_7.setText("描述");

		//添加选菜表表游标
		TableCursor tableCursor = new TableCursor(table_1, SWT.NONE);

		Composite composite_9 = new Composite(sashForm_4, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_5 = new SashForm(composite_9, SWT.NONE);
		sashForm_5.setOrientation(SWT.VERTICAL);

		Composite composite_10 = new Composite(sashForm_5, SWT.NONE);
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_10, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(104);
		tblclmnNewColumn.setText("  菜品名称");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(60);
		tblclmnNewColumn_1.setText("单价");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(60);
		tblclmnNewColumn_2.setText("数量");

		//添加菜单表游标		
		TableCursor tableCursor_1 = new TableCursor(table, SWT.NONE);	
		Menu menu_1 = new Menu(tableCursor_1);
		tableCursor_1.setMenu(menu_1);		
		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);

		menuItem_1.setText("删除");

		TableColumn tblclmnNewColumn_8 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_8.setResizable(false);

		//tblclmnNewColumn_8.setWidth(0);

		Composite composite_11 = new Composite(sashForm_5, SWT.NONE);
		composite_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label lblNewLabel_3 = new Label(composite_11, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblNewLabel_3.setBounds(37, 8, 83, 38);
		lblNewLabel_3.setText("操作员：");

		Label lblNewLabel_4 = new Label(composite_11, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_4.setBounds(128, 10, 148, 41);
		lblNewLabel_4.setText(SetInfo.ename);
		sashForm_5.setWeights(new int[] {449, 60});
		sashForm_4.setWeights(new int[] {483, 242});

		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_6 = new SashForm(composite_5, SWT.NONE);

		Composite composite = new Composite(sashForm_6, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_7 = new SashForm(composite, SWT.NONE);

		Composite composite_14 = new Composite(sashForm_7, SWT.NONE);
		composite_14.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label lblNewLabel_5 = new Label(composite_14, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_5.setBounds(26, 10, 90, 24);
		lblNewLabel_5.setText("总金额：");

		Label lblNewLabel_8 = new Label(composite_14, SWT.NONE);
		lblNewLabel_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.BOLD));
		lblNewLabel_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_8.setBounds(54, 90, 109, 65);

		Composite composite_16 = new Composite(sashForm_7, SWT.NONE);
		composite_16.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		composite_16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		Label lblNewLabel_6 = new Label(composite_16, SWT.NONE);
		lblNewLabel_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_6.setBounds(146, 63, 300, 30);
		formToolkit.adapt(lblNewLabel_6, true, true);
		lblNewLabel_6.setText("联系方式：    0734-8355998");
		
		Label label = new Label(composite_16, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setText("地址：          常胜西路28号");
		label.setBounds(146, 132, 300, 30);
		formToolkit.adapt(label, true, true);

		Composite composite_15 = new Composite(sashForm_7, SWT.NONE);
		composite_15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		sashForm_7.setWeights(new int[] {188, 656, 1});

		Composite composite_13 = new Composite(sashForm_6, SWT.NONE);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label lblNewLabel_10 = new Label(composite_13, SWT.NONE);
		lblNewLabel_10.setFont(SWTResourceManager.getFont("华文中宋", 20, SWT.NORMAL));
		lblNewLabel_10.setAlignment(SWT.CENTER);
		lblNewLabel_10.setText("确认");
		sashForm_6.setWeights(new int[] {441, 174});
		sashForm_2.setWeights(new int[] {407, 129});
		sashForm_1.setWeights(new int[] {167, 808});
		showTodaySp();
		//在树中显示菜系


		GoodTypeDao gty= new GoodTypeDao();
		List<Map<String,Object>> sortInfo=gty.findSort();
		if(sortInfo!=null&&sortInfo.size()>0){
			TreeItem ti=null;
			for(Map<String, Object> map:sortInfo) {
				ti=new TreeItem(tree, SWT.NONE);
				ti.setText(String.valueOf(map.get("sname")));

			}
		}



		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti=(TreeItem) e.item;
				String sname=ti.getText().trim();
				DBHelper db=new DBHelper();
				String sql="select sid from sort where sname=?";
				Map<String, Object> map=db.find(sql,sname);
				int sid=Integer.parseInt(String.valueOf(map.get("sid")));
				GoodTypeDao gtd=new GoodTypeDao();
				List<Map<String,Object>> list=gtd.findMenu(sid);
				table_1.removeAll();
				if(list!=null&&!list.isEmpty()){
					TableItem tai;
					for(Map<String,Object> map2:list){
						tai=new TableItem(table_1, SWT.NONE);
						tai.setText(new String[]{"",String.valueOf(map2.get("mname")),
								String.valueOf(map2.get("mprice")),
								String.valueOf(map2.get("mmemo"))});
						Object obj=map2.get("photo");
						if(obj!=null){
							byte[] bt=(byte[]) obj;
							tai.setImage(ScaleImage.byteToImage(bt,50,50));
						}
					}

				}
			}
		});
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
		});


		



		


		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				TableItem ti=tableCursor.getRow();
				//						table.getColumn(0).setText(ti.getText(2));
				//						table.getColumn(1).setText(ti.getText(3));
				TableItem ti1= new TableItem(table, SWT.NONE);
				if(ti.getText(1).toString().equals(SetInfo.mname)){
					double te=Double.parseDouble(ti.getText(2))*0.7;
					te=(double)Math.round(te * 100) / 100;
					ti1.setText(new String []{ti.getText(1),String.valueOf(te),"1"});
				}else{
					ti1.setText(new String []{ti.getText(1),ti.getText(2),"1"});
				}

				count+=Double.parseDouble(ti1.getText(1));
				lblNewLabel_8.setText(String.valueOf(count));
				SetInfo.count=count;
			}
		});




		//删除事件
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti=tableCursor_1.getRow();
				count=count-Integer.parseInt(ti.getText(1));
				lblNewLabel_8.setText(String.valueOf(count));
				ti.dispose();
				//				table.remove(table.getSelectionIndex());
				//				table.getSelectionIndex().dispose();
				//				DefaultTableModel dtm=new DefaultTableModel();
				//				dtm.removeRow(table.getSelectionIndex());
				//				table.pack();
				//			    table.setHeaderVisible(true);
				//		        table.setBounds(x, y, width, height);
			}
		});


		//确认
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(count!=0){

					ConsumerDao cDao=new ConsumerDao();
					int result=	cDao.addConsumer(SetInfo.tempConsumer.get("dno").toString(),SetInfo.ename);
					if(result==0){
						MessageDialog.openError(getShell(), "ERROR提示", "消费表创建失败。。。");
						return ;
					}
					SetInfo.seq_consumer_id=(String.valueOf(cDao.getCid(SetInfo.tempConsumer.get("dno").toString()).get("cid")));

					TableItem tItem[]=table.getItems();
					//					List<TableItem> list=new ArrayList<TableItem>();
					TempItemDao tDao=new TempItemDao();
					int result1=0;
					for(TableItem ti1: tItem){
							System.out.println("菜名："+ti1.getText(0)+"单价："+ti1.getText(1)+"数量："+ti1.getText(2)+"编号"+SetInfo.seq_consumer_id);
						
							result1=tDao.addTempItem(ti1.getText(0),ti1.getText(1),ti1.getText(2),Integer.valueOf((SetInfo.seq_consumer_id)));
						if(result1==0){
							MessageDialog.openError(getShell(), "错误提示", "临时表存储有误");
						}
						//						ti1.setText(new String[]{ti1.getText(0),ti1.getText(1),ti1.getText(2),SetInfo.seq_consumer_id});
						//						list.add(ti1);
						//						SetInfo.list=list;	
					}
				}else{
					MessageDialog.openError(getShell(), "友情提示！", "先点菜吧...");
				}
				table.removeAll();
				SitDao sitDao=new SitDao();
				SetInfo.sl.topControl=SetInfo.sit;
				SetInfo.composite.layout();
				Map<String,ShowData> map = SetInfo.sit.map;
				ShowData showDataObject = map.get(SetInfo.tempConsumer.get("dno").toString());
				SetInfo.sit.showInfomation();
				showDataObject.setBackgroundColor("用餐");
			}

		});
	}
	
	public void showTodaySp(){
		String sql="select mid from menu where sid !=5001";
		DBHelper dbHelper=new DBHelper();
		List<Map<String, Object>> list=dbHelper.finds(sql);
		Integer [] ids=new Integer[list.size()];
		int count=0;
		if(list!=null&&!list.isEmpty()){
			Integer sid;
			for(Map<String, Object> map:list) {
				sid =Integer.valueOf(map.get("mid").toString());
				ids[count++]=sid;
			}

		}
		//				int ran;
		//				ran=(int) (Math.random() * ids.length);
		//				
		Random rd=new Random();
		int ran=rd.nextInt(ids.length);


		int mid=ids[ran];
		//				System.out.println(ids.length);
		String find="select mname,photo ,mprice from menu where mid=?";
		Map<String, Object> map =dbHelper.find(find,mid);
		byte[] bt=(byte[]) map.get("photo");
		lblNewLabel_2.setImage(ScaleImage.byteToImage(bt, 115, 115));
		lblNewLabel.setText(map.get("mname").toString());
		mname=map.get("mname").toString();
		SetInfo.mname=mname;


	}

	public void showMenu(){
		MenuDao mDao=new MenuDao();
		List<Map<String,Object>> list=mDao.getXiaoChao();
		table_1.removeAll();
		if(list!=null&&!list.isEmpty()){
			TableItem tai;
			for(Map<String,Object> map2:list){
				tai=new TableItem(table_1, SWT.NONE);
				tai.setText(new String[]{"",String.valueOf(map2.get("mname")),
						String.valueOf(map2.get("mprice")),
						String.valueOf(map2.get("mmemo"))});
				Object obj=map2.get("photo");
				if(obj!=null){
					byte[] bt=(byte[]) obj;
					tai.setImage(ScaleImage.byteToImage(bt,50,50));
				}

			}
		}
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
