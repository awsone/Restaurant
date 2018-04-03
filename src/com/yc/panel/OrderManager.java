package com.yc.panel;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.ibm.icu.text.SimpleDateFormat;


import com.yc.dao.ConsumerDao;
import com.yc.dao.MenuDao;
import com.yc.dao.SitDao;
import com.yc.dao.TempItemDao;
import com.yc.panel.Sit.ShowData;
import com.yc.util.SetInfo;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class OrderManager extends Composite {
	private Table table;
	private Table table_1;
	private Text text;
	double pay=0,keep=0;
	int tempId=0;
	Label label_1 ;
	double count=0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OrderManager(Composite parent, int style) {
		super(parent, style);
		setSize(1440,832);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite, SWT.NONE);

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("  订单号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("桌号");

		TableCursor tableCursor = new TableCursor(table, SWT.NONE);

		Menu menu_1 = new Menu(tableCursor);
		tableCursor.setMenu(menu_1);

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);

		menuItem_1.setText("查看");

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		table_1 = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table_1, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("   名称");

		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("单价");

		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("数量");

		TableCursor tableCursor_1 = new TableCursor(table_1, SWT.NONE);

		Menu menu = new Menu(tableCursor_1);
		tableCursor_1.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("删除");

		Composite composite_8 = new Composite(sashForm_1, SWT.NONE);

		Button btnNewButton_1 = new Button(composite_8, SWT.NONE);

	/*	btnNewButton_1.setBounds(33, 86, 114, 34);
		btnNewButton_1.setText("刷新");*/
		sashForm_1.setWeights(new int[] {258, 381, 214});

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_2 = new SashForm(composite_1, SWT.NONE);

		Composite composite_4 = new Composite(sashForm_2, SWT.NONE);
		composite_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label = new Label(composite_4, SWT.NONE);
		label.setText("总金额：");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label.setBounds(32, 25, 90, 24);

		Label label_1 = new Label(composite_4, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.BOLD));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_1.setBounds(74, 52, 109, 65);

		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label_2 = new Label(composite_5, SWT.NONE);
		label_2.setText("实收：");
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_2.setBounds(21, 25, 90, 24);

		text = new Text(composite_5, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.BOLD));
		text.setBounds(57, 55, 155, 60);

		Composite composite_6 = new Composite(sashForm_2, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label_3 = new Label(composite_6, SWT.NONE);
		label_3.setText("找零：");
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_3.setBounds(10, 25, 90, 24);

		Label label_4 = new Label(composite_6, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Arial", 14, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_4.setBounds(44, 51, 125, 66);

		Composite composite_7 = new Composite(sashForm_2, SWT.NONE);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button btnNewButton = new Button(composite_7, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});

		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		btnNewButton.setText("结账");

		sashForm.setWeights(new int[] {443, 116});
		
		

		/*//刷新事件
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				table.removeAll();
				table_1.removeAll();
				ConsumerDao cDao=new ConsumerDao();
				
				List<Map<String, Object> > list=cDao.getConsumer();
				System.out.println(list.size());
				TableItem taItem;
				for(Map<String, Object>  map:list){
					taItem=new TableItem(table, SWT.NONE);
					taItem.setText(new String []{String.valueOf(map.get("cid")),String.valueOf(map.get("dno"))});
				}


				//				
				//				TableItem ti;
				//				for(TableItem tableIt:SetInfo.list){
				//					ti=new TableItem(table_1, SWT.NONE);
				//					ti.setText(new String []{tableIt.getText(0),tableIt.getText(1),tableIt.getText(2)});
				//				}
				label_1.setText(String.valueOf(SetInfo.count));
			}
		});
*/


		

		//结账
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(text.getText()==null||text.getText().isEmpty()){
					MessageDialog.openError(getShell(), "友情提示！", "请输入正确收银...");
					return;
				}else{
					pay=Double.parseDouble(text.getText());
					if(pay<count){
						MessageDialog.openError(getShell(), "友情提示！", "请输入正确收银...");
						return;
					}else{
						keep=pay-count;
						label_4.setText(String.valueOf(keep));
					}


//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String ctime = sdf.format(new Date()); 
//					DBHelper db =new DBHelper();
//					String sql="insert into consumer values(seq_consumer_cid.nextval,?,?,?,?)";

					//					db.update(sql,"1",count,ctime,SetInfo.mname);
					SetInfo.pay=pay;
					SetInfo.keep=keep;
					ConsumerDao cDao=new ConsumerDao();
					MenuDao mDao=new MenuDao();
					System.out.println("结账");
					for(TableItem ti:table_1.getItems()){
						String dish_id=String.valueOf(mDao.findMenu(ti.getText(0).trim()).get("mid"));
						int result=cDao.addOrderItem(SetInfo.seq_consumer_id,dish_id,"1",ti.getText(1));
						if(result==0){
							MessageDialog.openError(getShell(), "错误提示", "消费项目插入失败!!!");
							return;
						}
					}
					//将订单状态改为已结账
					cDao.updateOrderItemState(table.getItem(table.getSelectionIndex()).getText(0));
					//将该桌子状态改为空闲
					SitDao sitDao=new SitDao();
					sitDao.updateState(table.getItem(table.getSelectionIndex()).getText(1), "空闲");
					Check ck=new Check(getShell(), SWT.APPLICATION_MODAL|SWT.DIALOG_TRIM);
					ck.open();
					SetInfo.sl.topControl=SetInfo.sit;
					SetInfo.composite.layout();
					Map<String,ShowData> map = SetInfo.sit.map;
					ShowData showDataObject = map.get(SetInfo.tempConsumer.get("dno").toString());
					showDataObject.setBackgroundColor("空闲");
					

				}
				SetInfo.count=count;	
			}
		});
		//查看订单
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			//	double count = 0;
				table_1.removeAll();
				TempItemDao tDao=new TempItemDao();
				List<Map<String,Object>> Items=tDao.getTempItem(Integer.valueOf(tableCursor.getRow().getText(0)));
				System.out.println(Items.size());
				//				for(TableItem ti:SetInfo.list){
				////					System.out.println(SetInfo.list.size());
				////					System.out.println(SetInfo.seq_consumer_id);
				////						System.out.println(ti.getText(3));
				////						System.out.println(tableCursor.getRow().getText(0));
				//					if(ti.getText(3).equals(tableCursor.getRow().getText(0))){
				//						TableItem ti1= new TableItem(table_1, SWT.NONE);
				//						//System.out.println(ti.getText(0).toString()+ti.getText(1).toString()+ti.getText(2).toString());
				//						ti1.setText(new String[]{ti.getText(0).toString(),ti.getText(1).toString(),ti.getText(2).toString()});
				//					}
				//					
				//					
				//				}
				
				
				

				tempId=Integer.valueOf(tableCursor.getRow().getText(0));
				for(Map<String,Object > map:Items){
					System.out.println(map);

					System.out.println(tableCursor.getRow().getText(0));
					if(String.valueOf(map.get("consumerid")).equals(tableCursor.getRow().getText(0))){
						TableItem ti1= new TableItem(table_1, SWT.NONE);
						//System.out.println(ti.getText(0).toString()+ti.getText(1).toString()+ti.getText(2).toString());
						ti1.setText(new String[]{String.valueOf(map.get("tempname")),String.valueOf(map.get("tempprice")),String.valueOf(map.get("tempsum"))});
					}
				}
				TableItem [] items2=table_1.getItems();
				for(TableItem iiItem:items2){
					count+=Double.valueOf(iiItem.getText(1));	
				}
				System.out.println(count);
				System.out.println("8888");
				

				label_1.setText(String.valueOf(count));
			}
		});
		
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti=tableCursor_1.getRow();
				System.out.println(ti.getText(1));
				count=count-Double.valueOf(ti.getText(1));
				label_1.setText(String.valueOf(count));
				ti.dispose();
			}
		});


	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setTable(){
		table.removeAll();
		table_1.removeAll();
		ConsumerDao cDao=new ConsumerDao();

		List<Map<String, Object> > list=cDao.getConsumer();
		//System.out.println(list.size());
		TableItem taItem;
		for(Map<String, Object>  map:list){
			taItem=new TableItem(table, SWT.NONE);
			taItem.setText(new String []{String.valueOf(map.get("cid")),String.valueOf(map.get("dno"))});
		}


		//				
		//				TableItem ti;
		//				for(TableItem tableIt:SetInfo.list){
		//					ti=new TableItem(table_1, SWT.NONE);
		//					ti.setText(new String []{tableIt.getText(0),tableIt.getText(1),tableIt.getText(2)});
		//				}
		//label_1.setText(String.valueOf(count));
	}
	
	
	
}
