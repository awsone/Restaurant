package com.yc.panel;



import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;

import com.yc.util.ChartUtils;
import com.yc.util.Serie;



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
public class PaymentStyle {
	public PaymentStyle() {
	}

	public DefaultCategoryDataset createDataset() {
		// 标注类别
		String[] categories = { "支付宝", "微信支付", "网上银行", "现金结算", "其他方式"};
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		series.add(new Serie("门店一", new Double[] { 49.9, 71.5, 106.4, 129.2, 144.0 }));
		series.add(new Serie("门店二", new Double[] { 83.6, 78.8, 98.5, 93.4, 106.0}));
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}

	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createStackedBarChart("本月结算方式份额", "", "发生金额（万元）", createDataset());
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setStackBarRender(chart.getCategoryPlot());
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		int width = 380; /* Width of the image */
		int height = 220; /* Height of the image */ 
		File barChart = new File( "res/picture/bar_Chart.jpg" );
		try {
			ChartUtilities.saveChartAsJPEG( barChart , chart , width , height );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chartPanel;

	}

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				ChartPanel chartPanel = new PaymentStyle().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
