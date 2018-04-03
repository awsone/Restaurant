package com.yc.panel;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.yc.dao.JDBC;
import com.yc.util.ChartUtils;

/**
 * 

 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 * 
 *       </p>
 */
public class Day_Consume {
	JFreeChart chart;
	public Day_Consume() {
	}

	/**
	 * 创建数据集合
	 * 
	 * @return
	 */
	public TimeSeriesCollection createDataset() {
		TimeSeriesCollection dataset = null;
		String[] categories = {"营业消费表"};
		int a=0;
		int i=0;
		Connection conn = JDBC.getConnection();// 获得数据库连接

		try {
			Statement statement = conn.createStatement( );
			String sql="select distinct(day1),sum(money) totalmoney from(select cid,money,to_char(ctime,'DD') day1,dtype from(select c.cid,c.dno,c.money,c.ctime,d.dtype from consumer c,dintable d where c.dno = d.dno order by c.cid)) group by day1";
			ResultSet resultSet = statement.executeQuery(sql);
			dataset = new TimeSeriesCollection();
			Vector<Object[]> dateValues = new Vector<Object[]>();;
			Set<Object> datebc=new HashSet<>();

			while(resultSet.next( )) 
			{

				String date = resultSet.getString( "day1" ) +"";
				String totalmoney= resultSet.getString( "totalmoney" );

				Object[] dateValue = {date,totalmoney};

				dateValues.add(dateValue);

				int date1 = Integer.parseInt(date);
				datebc.add(date1);


				//}
			}

			for (int i1 = 1; i1 < 32; i1++) {
				if(!datebc.contains(i1)){
					String date = i1 +"";
				
					Object[] dateValue = { date, 0 };

					dateValues.add(dateValue);
				}
				     //如果没有则执行删除
			}

			TimeSeries timeSeries = ChartUtils.createTimeseries(categories[0],dateValues);
			//	TimeSeries timeSeries1= ChartUtils.
			dataset.addSeries(timeSeries);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dataset;


	}

	public static boolean useList(Set<Object> datebc, int i1) {
		return Arrays.asList(datebc).contains(i1);
	}
	public ChartPanel createChart() {
		
		
		// 2：创建Chart[创建不同图形]
		TimeSeriesCollection dataset = createDataset();
		chart = ChartFactory.createTimeSeriesChart(
				"", "", "", dataset);
		
		int width = 340; /* Width of the image */
		int height = 250; /* Height of the image */ 
		File pieChart = new File( "res/picture/day_consume.jpg" );
		try {
			ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
	
		//	 ChartUtilities.writeChartAsPNG(out, chart, weight, height);   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setTimeSeriesRender(chart.getPlot(), true, true);
		// 5:对其他部分进行渲染
		XYPlot xyplot = (XYPlot) chart.getPlot();
		ChartUtils.setXY_XAixs(xyplot);
		ChartUtils.setXY_YAixs(xyplot);
		// 日期X坐标轴
		DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
		domainAxis.setAutoTickUnitSelection(false);
		DateTickUnit dateTickUnit = null;

		if (dataset.getItemCount(0) < 32) {
			//刻度单位月,半年为间隔
			dateTickUnit = new DateTickUnit(DateTickUnitType.DAY,6, new SimpleDateFormat("DD")); // 第二个参数是时间轴间距
		} else {// 数据过多,不显示数据
			XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
			xyRenderer.setBaseItemLabelsVisible(false);
			dateTickUnit = new DateTickUnit(DateTickUnitType.DAY , 1, new SimpleDateFormat("MM")); // 第二个参数是时间轴间距
		}
		// 设置时间单位
		domainAxis.setTickUnit(dateTickUnit);
		ChartUtils.setLegendEmptyBorder(chart);
		// 设置图例位置
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	public static void main(String[] args) {
		System.out.println("运行");
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(340, 250);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				ChartPanel chartPanel = new Day_Consume().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
