package com.yc.panel;



import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.dao.Dao;
import com.yc.dao.SitDao;
import com.yc.panel.Sit.ShowData;
import com.yc.util.SetInfo;


public class Predetemine extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private int count=0;
	private Text text_3;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Predetemine(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(255, 250, 240));
		setSize(1440,832);



		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel.setBounds(488, 155, 98, 27);
		lblNewLabel.setText("顾客姓名：");

		text = new Text(this, SWT.BORDER);
		text.setBounds(674, 154, 200, 30);

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(496, 263, 90, 24);
		lblNewLabel_1.setText("顾客电话：");

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(674, 262, 200, 30);

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_2.setBounds(496, 377, 90, 24);
		lblNewLabel_2.setText("预约时间：");






		DateTime dateTime = new DateTime(this, SWT.BORDER);
		dateTime.setBounds(674, 377, 153, 27);

		Combo combo = new Combo(this, SWT.NONE);
		combo.setItems(new String[] {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"});
		combo.setBounds(833, 379, 48, 84);
		combo.setText("1");

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_3.setBounds(496, 657, 90, 24);
		lblNewLabel_3.setText("备注：");

		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(653, 642, 282, 75);

		Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setBounds(653, 788, 114, 34);
		btnNewButton.setText("预订");

		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_4.setBounds(488, 576, 90, 24);
		lblNewLabel_4.setText("预约桌号：");

		SitDao sitDao=new SitDao();

		Combo combo_1 = new Combo(parent, SWT.DROP_DOWN);
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_5.setBounds(256, 460, 193, 27);
		
		Combo combo_2 = new Combo(this, SWT.READ_ONLY);
		combo_2.setBounds(674, 575, 200, 25);
		
		Label lblNewLabel_7 = new Label(this, SWT.NONE);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(255, 250, 240));
		lblNewLabel_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_7.setBounds(496, 478, 90, 24);
		lblNewLabel_7.setText("预约人数：");
		//combo_2.add("14");
		
		
		Label lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setImage(SWTResourceManager.getImage("W:\\eclipse workspace-64\\RestaurantManageSystem\\res\\picture\\191237-106.jpg"));
		lblNewLabel_6.setBounds(0, 0, 1440, 50);
	
		text_3 = new Text(this, SWT.BORDER);
		
		text_3.setBounds(674, 477, 200, 30);
		Date date=new Date(System.currentTimeMillis());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currentTime=format.format(date);

		text_3.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(!text_3.getText().equals("")&&text_3.getText()!=null){
					//SitDao sDao=new SitDao();
				//	System.out.println("hahahaha");
					combo_2.removeAll();
					
					for(Map<String ,Object >map:sitDao.getSitID(Integer.valueOf(text_3.getText()))){
					//	System.out.println(map);
						combo_2.add(map.get("dno").toString());
					}
//				}
			}
			}
		});
		
		//预定事件
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String reserver=text.getText().trim();
				String rtel=text_1.getText().trim();
				String dintime=dateTime.getYear()+"/"+dateTime.getMonth()+"/"+dateTime.getDay()+"/"+combo.getText();
				String remark=text_2.getText().trim();
				String dno=combo_2.getText();//餐桌号

				Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String currentTime=format.format(date);
				Calendar c=Calendar.getInstance();
				int year=(Calendar.YEAR);
				int month=(Calendar.MONTH);
				int  day=(Calendar.DATE);
				int hour=(Calendar.HOUR);
//				if(Integer.valueOf(dateTime.getYear())>year||(Integer.valueOf(dateTime.getMonth())+1)>month||(Integer.valueOf(dateTime.getDay()))>day||Integer.valueOf(combo.getText())>hour){
//					MessageDialog.openError(getShell(), "提示", "很抱歉，你不能预约以前的时间...");
//
//				}


				if(reserver==null||reserver.equals("")||rtel==null||"".equals(rtel)||dintime==null||"".equals(dintime)){
					lblNewLabel_5.setText("请填写相应信息...");
					return ;
				}
				Dao predao=Dao.getInstance();
				int result =predao.addPredemine(reserver, dno, rtel, dintime, remark);
				if(result>0){
					int result1 = sitDao.updateState(dno, "预定");
					SitDao sitDao =new SitDao();
					if(result1==0){
						MessageDialog.openError(getShell(), "提示", "很抱歉，预定失败...");
					}
					text.setText("");
					text_1.setText("");
					text_2.setText("");
					combo.setText("");
					combo_2.setText("");

				}
				Map<String,ShowData> map = SetInfo.sit.map;
				ShowData showDataObject = map.get(combo_2.getText().trim());
				showDataObject.setBackgroundColor("预定");
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
