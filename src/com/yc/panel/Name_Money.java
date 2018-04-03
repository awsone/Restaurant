package com.yc.panel;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.eclipse.swt.widgets.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

import com.yc.dao.Dao;
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
public class Name_Money {
	public Name_Money() {
	}

	public DefaultPieDataset createDataset() {
		Dao dao=Dao.getInstance();
		Vector vector=dao.menu_Money();
		//System.out.println(dao.menu_Money());
		//System.out.println(vector.size());
		String name[]=new String[vector.size()];
		String money[]=new String[vector.size()];

		if(vector != null && vector.size()>0){//如果vector中存入了数据，转化为数组  
			//		Object arr=new String[vector.size()];//创建一个和vector长度一样的数组  
			for(int i=0;i<vector.size();i++){

				name[i]=(String) ((Vector) vector.get(i)).get(1);
			//	System.out.println(((Vector) vector.get(i)).get(1));
				money[i]= ((Vector) vector.get(i)).get(2)+"";
				/*Vector<Object> tmp=(Vector<Object>) vector.get(i);
						for(int j=0;j<tmp.size();j++){
							String str=(String) tmp.get(j);
							System.out.println(str);

						}*/
			}
		} 
		DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(name,money);
		return dataset;
	}

	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createPieChart("每个商品营业占比统计图", createDataset(),true,true,false);
	
		int width = 350; /* Width of the image */
		int height = 220; /* Height of the image */ 
		File pieChart = new File( "res/picture/Name_Money.jpg" );
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
		ChartUtils.setPieRender(chart.getPlot());
		/**
		 * 可以注释测试
		 */
		// plot.setSimpleLabels(true);//简单标签,不绘制线条
		// plot.setLabelGenerator(null);//不显示数字
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 220);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				ChartPanel chartPanel = new Name_Money().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
