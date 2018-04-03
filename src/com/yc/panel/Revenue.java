package com.yc.panel;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.yc.dao.Dao;
import com.yc.util.CreatePicture;

public class Revenue extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public static JFreeChart chart;
	public Dao dao;

	public Revenue(Composite parent, int style) {
		super(parent, style);
		setSize(1440,832);

		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(245, 255, 250));
		sashForm.setTouchEnabled(true);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite.setLayout(null);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage("W:\\C盘文本文档\\图片\\lovewallpaper\\191237-106.jpg"));
		btnNewButton.setBounds(0, 0, 1440, 50);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		lblNewLabel.setBounds(24, 56, 168, 27);
		lblNewLabel.setText("餐厅营业概况");

		Label lblRestaurantBusinessOverview = new Label(composite, SWT.NONE);
		lblRestaurantBusinessOverview.setBounds(70, 89, 200, 17);
		lblRestaurantBusinessOverview.setText("Restaurant Business OverView");

		SashForm sashForm_1 = new SashForm(sashForm, SWT.BORDER);
		sashForm_1.setTouchEnabled(true);
		create();
		//System.out.println(chart);


		SashForm sashForm_11 = new SashForm(sashForm_1, SWT.NONE);
		sashForm_11.setTouchEnabled(true);

		Composite composite_1 = new Composite(sashForm_11, SWT.NONE);
		composite_1.setTouchEnabled(true);

		Label label_18 = new Label(composite_1, SWT.NONE);
		label_18.setFont(SWTResourceManager.getFont("幼圆", 11, SWT.NORMAL));
		label_18.setText("当日营业额");
		label_18.setBounds(10, 21, 97, 22);

		Label label_19 = new Label(composite_1, SWT.NONE);
		label_19.setText("营业额：");
		label_19.setBounds(35, 117, 48, 29);

		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setText("");
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(240, 128, 128));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(107, 110, 80, 30);

		Label label_21 = new Label(composite_1, SWT.NONE);
		label_21.setText("元");
		label_21.setBounds(212, 117, 20, 17);

		Label label_22 = new Label(composite_1, SWT.NONE);
		label_22.setText("成交笔数:");
		label_22.setBounds(35, 210, 61, 17);

		Label label_23 = new Label(composite_1, SWT.NONE);
		label_23.setText("笔");
		label_23.setAlignment(SWT.CENTER);
		label_23.setBounds(202, 210, 30, 30);

		Label lblNewLabel_8 = new Label(composite_1, SWT.NONE);
		lblNewLabel_8.setText("");
		lblNewLabel_8.setForeground(SWTResourceManager.getColor(240, 128, 128));
		lblNewLabel_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		lblNewLabel_8.setAlignment(SWT.CENTER);
		lblNewLabel_8.setBounds(107, 203, 80, 30);

		Label label_25 = new Label(composite_1, SWT.NONE);
		label_25.setText("平均消费额：");
		label_25.setBounds(35, 290, 72, 17);

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setText("");
		label_4.setForeground(SWTResourceManager.getColor(240, 128, 128));
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_4.setAlignment(SWT.CENTER);
		label_4.setBounds(107, 283, 80, 30);

		Label label_27 = new Label(composite_1, SWT.NONE);
		label_27.setText("元");
		label_27.setAlignment(SWT.CENTER);
		label_27.setBounds(202, 283, 30, 30);

		SashForm sashForm_7 = new SashForm(sashForm_11, SWT.VERTICAL);
		sashForm_7.setTouchEnabled(true);

		Composite composite_11 = new Composite(sashForm_7, SWT.NONE);
		composite_11.setTouchEnabled(true);

		Composite composite_12 = new Composite(sashForm_7, SWT.NONE);
		composite_12.setTouchEnabled(true);

		SashForm sashForm_3 = new SashForm(sashForm_1, SWT.NONE);
		sashForm_3.setTouchEnabled(true);

		Composite composite_3 = new Composite(sashForm_3, SWT.EMBEDDED);
		composite_3.setTouchEnabled(true);

		Label lblNewLabel_4 = new Label(composite_3, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_4.setAlignment(SWT.CENTER);
		lblNewLabel_4.setBounds(26, 32, 80, 30);
		lblNewLabel_4.setText("客流量：");

		Label lblNewLabel_5 = new Label(composite_3, SWT.NONE);
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(255, 165, 0));
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_5.setAlignment(SWT.CENTER);
		lblNewLabel_5.setBounds(123, 30, 80, 30);

		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(240, 34, 30, 30);
		label_1.setText("人");

		Label label_6 = new Label(composite_3, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_6.setText("人均消费：");
		label_6.setAlignment(SWT.CENTER);
		label_6.setBounds(26, 81, 80, 30);

		Label label_7 = new Label(composite_3, SWT.NONE);
		label_7.setForeground(SWTResourceManager.getColor(255, 165, 0));
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_7.setAlignment(SWT.CENTER);
		label_7.setBounds(123, 79, 80, 30);

		Label label_8 = new Label(composite_3, SWT.NONE);
		label_8.setText("元");
		label_8.setAlignment(SWT.CENTER);
		label_8.setBounds(240, 83, 30, 30);

		Button btnNewButton_4 = new Button(composite_3, SWT.NONE);
		btnNewButton_4.setImage(SWTResourceManager.getImage("res\\picture\\day_Person.jpg"));
		btnNewButton_4.setBounds(24, 117, 346, 239);

		SashForm sashForm_2 = new SashForm(sashForm, SWT.BORDER);
		sashForm_2.setTouchEnabled(true);

		SashForm sashForm_6 = new SashForm(sashForm_2, SWT.VERTICAL);

		Composite composite_8 = new Composite(sashForm_6, SWT.NONE);

		Label label_10 = new Label(composite_8, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.NORMAL));
		label_10.setText("菜品销量数据：");
		label_10.setBounds(10, 10, 112, 21);

		Composite composite_9 = new Composite(sashForm_6, SWT.NONE);

		Label label_11 = new Label(composite_9, SWT.NONE);
		label_11.setText("畅销菜品");
		label_11.setBounds(50, 29, 61, 17);

		Label lblNewLabel_7 = new Label(composite_9, SWT.BORDER);
		lblNewLabel_7.setAlignment(SWT.CENTER);
		lblNewLabel_7.setBounds(129, 14, 110, 110);

		Label label_13 = new Label(composite_9, SWT.BORDER);
		label_13.setAlignment(SWT.CENTER);
		label_13.setBounds(306, 14, 110, 110);

		Label label_14 = new Label(composite_9, SWT.BORDER);
		label_14.setAlignment(SWT.CENTER);
		label_14.setBounds(494, 14, 110, 110);

		Composite composite_10 = new Composite(sashForm_6, SWT.NONE);

		Label label_12 = new Label(composite_10, SWT.NONE);
		label_12.setText("滞销菜品");
		label_12.setBounds(50, 30, 61, 17);

		Label label_15 = new Label(composite_10, SWT.BORDER);
		label_15.setAlignment(SWT.CENTER);
		label_15.setBounds(129, 16, 110, 110);

		Label label_16 = new Label(composite_10, SWT.BORDER);
		label_16.setAlignment(SWT.CENTER);
		label_16.setBounds(306, 16, 110, 110);

		Label label_17 = new Label(composite_10, SWT.BORDER);
		label_17.setAlignment(SWT.CENTER);
		label_17.setBounds(496, 16, 110, 110);
		sashForm_6.setWeights(new int[] {40, 136, 136});

		SashForm sashForm_4 = new SashForm(sashForm_2, SWT.BORDER);
		sashForm_4.setTouchEnabled(true);

		Composite composite_4 = new Composite(sashForm_4, SWT.NONE);
		composite_4.setTouchEnabled(true);

		Label label_3 = new Label(composite_4, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("幼圆", 11, SWT.NORMAL));
		label_3.setBounds(10, 10, 84, 25);
		label_3.setText("支付方式");

		Button btnNewButton_3 = new Button(composite_4, SWT.NONE);
		btnNewButton_3.setImage(SWTResourceManager.getImage("res/picture/bar_Chart.jpg"));

		btnNewButton_3.setBounds(15, 80, 380, 220);

		Composite composite_6 = new Composite(sashForm_4, SWT.NONE);
		composite_6.setLayout(null);

		Label lblNewLabel_3 = new Label(composite_6, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.NORMAL));
		lblNewLabel_3.setBounds(10, 10, 120, 25);
		lblNewLabel_3.setText("营业占比分析");

		Label lblNewLabe = new Label(composite_6, SWT.NONE);
		lblNewLabe.setBounds(10, 80, 350, 213);

		SashForm sashForm_5 = new SashForm(sashForm_3, SWT.BORDER | SWT.SMOOTH | SWT.VERTICAL);
		sashForm_5.setTouchEnabled(true);

		Composite composite_5 = new Composite(sashForm_5, SWT.NONE);
		composite_5.setTouchEnabled(true);

		Label lblNewLabel_6 = new Label(composite_5, SWT.NONE);
		lblNewLabel_6.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.NORMAL));
		lblNewLabel_6.setBounds(10, 22, 116, 24);
		lblNewLabel_6.setText("用餐高峰期分析：");

		Label lblNewLabel_1 = new Label(composite_9, SWT.NONE);
		lblNewLabel_1.setBounds(11, 10, 25, 49);

		Composite composite_7 = new Composite(sashForm_5, SWT.EMBEDDED);
		composite_7.setTouchEnabled(true);
		sashForm_3.setWeights(new int[] {368, 348});

		Label label = new Label(composite_10, SWT.NONE);
		label.setBounds(11, 16, 25, 49);
		new Day_Consume().createChart();
		ChartPanel chartPanel = new Day_Consume().createChart();
		new Day_Consume().createChart();
		new Day_Person().createChart();
		new CreatePicture().readDB2Image();
		new Name_Money().createChart();          //创建饼图



		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite comp3=new Composite(composite_7,SWT.EMBEDDED);

		java.awt.Frame frame3=SWT_AWT.new_Frame(comp3);
		ChartPanel chartPane3 = new Day_Consume().createChart();
		frame3.add(chartPane3);

		composite_12.setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite comp12=new Composite(composite_12,SWT.EMBEDDED);

		java.awt.Frame frame12=SWT_AWT.new_Frame(comp12);
		ChartPanel chartPane12 = new Day_Consume().createChart();
		frame12.add(chartPane12);

		Composite composite_2 = new Composite(sashForm_7, SWT.NONE);
		sashForm_7.setWeights(new int[] {87, 268, 18});




		Label label_9 = new Label(sashForm_5, SWT.NONE);
		label_9.setTouchEnabled(true);
		sashForm_5.setWeights(new int[] {111, 230, 20});




		sashForm_4.setWeights(new int[] {456, 407});
		sashForm_2.setWeights(new int[] {637, 792});

		dao=Dao.getInstance();
		//System.out.println((dao.sumMoney()).toString());
		double avgOrder=(Double.parseDouble((String) dao.sumMoney()))/(Double.parseDouble(dao.sumCount()));
		//TimeSeriesChart.main(null);
		lblNewLabel_5.setText(dao.sumPerson());
		double avgPerson=(Double.parseDouble((String) dao.sumMoney()))/(Double.parseDouble(dao.sumPerson()));
		label_7.setText(String.format("%.1f",avgPerson));
		sashForm.setWeights(new int[] {112, 384, 330});
		lblNewLabel_8.setText("");
		lblNewLabel_2.setText((dao.sumMoney()).toString());  //总金额
		lblNewLabel_8.setText((dao.sumCount()).toString());   //总单数
		//System.out.println(dao.sumCount());
		label_4.setText(String.format("%.1f",avgOrder));
		//	label_15.setImage("res/picture/1.png");
		label_15.setImage(SWTResourceManager.getImage("res/picture/1.png"));
		label_16.setImage(SWTResourceManager.getImage("res/picture/2.png"));
		label_17.setImage(SWTResourceManager.getImage("res/picture/3.png"));
		label.setImage(SWTResourceManager.getImage("res/picture/low_副本.png"));
		lblNewLabe.setImage(SWTResourceManager.getImage("res/picture/Name_Money.jpg"));
		lblNewLabel_7.setImage(SWTResourceManager.getImage("res/picture/20.png"));
		label_13.setImage(SWTResourceManager.getImage("res/picture/21.png"));
		label_14.setImage(SWTResourceManager.getImage("res/picture/22.png"));
		lblNewLabel_1.setImage(SWTResourceManager.getImage("res/picture/hot_副本.png"));


		Composite composite_13 = new Composite(sashForm_11, SWT.NONE);
		sashForm_11.setWeights(new int[] {250, 398, 20});
		sashForm_1.setWeights(new int[] {652, 754});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private static void create(){
		String mobilebrands[] = {
				"IPhone 5s",   
				"SamSung Grand",   
				"MotoG",            
				"Nokia Lumia" 
		};

		/* Create MySQL Database Connection */
		Connection connect = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			connect = DriverManager.getConnection( 
					"jdbc:mysql://localhost:3306/jfreechart" ,     
					"root",     
					"0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DefaultPieDataset dataset = null;
		try {
			Statement statement = connect.createStatement( );
			ResultSet resultSet = statement.executeQuery("select * from tb_jfreechart" );
			dataset = new DefaultPieDataset( );
			while( resultSet.next( ) ) 
			{
				dataset.setValue( 
						resultSet.getString( "brandname" ) ,
						Double.parseDouble( resultSet.getString( "datavalue" )));
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chart = ChartFactory.createPieChart(
				"Mobile Sales",  // chart title           
				dataset,         // data           
				true,            // include legend          
				true,           
				false );

		int width = 457; /* Width of the image */
		int height = 286; /* Height of the image */ 
		File pieChart = new File( "Pie_Chart3.jpg" );
		try {
			ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}