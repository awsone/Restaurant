package com.yc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 数据库连接
 * @author 大白猫😀😀😀小地瓜
 *
 */
public class JDBC {
	private static final String classname="oracle.jdbc.driver.OracleDriver";  //  数据库驱动
	private static final String url="jdbc:oracle:thin:@localhost:1521:orcl";     //路径
	private static final String username="Scott";   //用户名
	private static final String password="tigger";   //密码
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();  //创建对象的连接


	static{
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

		try {
			Class.forName(classname).newInstance();
			System.out.println("加载数据库驱动成功！！");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn=threadLocal.get();               //从线程中获取数据库连接
		if(conn==null){
			try {
				conn=DriverManager.getConnection(url,username,password);
				if(conn!=null){
	//System.out.println("成功的与Oracel数据库建立连接！！！");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return conn;
	}


	/**
	 * 是否关闭数据库连接
	 * @return
	 */
	public static boolean closeConnection(){
		boolean isClosed=true;
		Connection conn=threadLocal.get();
		threadLocal.set(null);
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				isClosed=false;
				e.printStackTrace();
			}
		}
		return isClosed;
	}

	public static void main(String[] args) {

		JDBC jdbc=new JDBC();
		jdbc.getConnection();
	}

}
