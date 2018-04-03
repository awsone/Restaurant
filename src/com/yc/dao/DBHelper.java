package com.yc.dao;




import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DBHelper {	
	static {
		
		JDBC.getConnection();

		
		/*try {
			Class.forName(ReadPro.getInstance().getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * 获取数据库连接的方法
	 * @return 返回数据库连接
	 */
	private Connection getconnection(){
		Connection con=null;
		try {
			con=DriverManager.getConnection(ReadPro.getInstance().getProperty("url"),ReadPro.getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}
	private void setValue(PreparedStatement pstmt,Object ...params){
		if (params != null&& params.length>0){    
			for (int i = 0; i < params.length; i++){    
				try {
					pstmt.setObject(i + 1, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}    
			}    
		}  

	}
	/**
	 * 关闭资源的方法
	 * @param rs 要关闭的结果集
	 * @param pstmt 要关闭的预编译语句
	 * @param con 要关闭的连接
	 */
	private void clossAll(ResultSet rs,PreparedStatement pstmt,Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取表的列名
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public  String[] getColNames(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd;

		rsmd = rs.getMetaData();
		int colCount=rsmd.getColumnCount();
		String colNames[]=new String[colCount];
		for(int i=0;i<colCount;i++){
			colNames[i]=rsmd.getColumnName(i+1).toLowerCase();
		}

		//获取结果集中的列数
		//将列名存储进colname数组
		return colNames;
	}

	/**
	 * 执行更新的方法
	 * @param sql 要执行更新的SQL语句
	 * @param params 更新语句中对应占位符？的值，一定要按照？的顺序给定
	 * @return 返回SQL语句影响行数
	 */

	public int update(String sql,Object ... params ){
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		//创建连接
		con=JDBC.getConnection();

		try {
			//创建预编译执行语句块
			pstmt=con.prepareStatement(sql);
			//给预编译执行语句中的占位符？赋值
			if(params!=null&& params.length>0){
				for(int i=0,len=params.length;i<len;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			//执行预编译语句获取结果
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * 返回一个结果集
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet  getResultSet(String sql,Object ... params){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBC.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			rs=pstmt.executeQuery();
			//获取结果集中的列名
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public Map<String,Object>  find (String sql,Object ... params){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Map<String,Object> map=null;

		try{
			con=JDBC.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			rs=pstmt.executeQuery();
			//获取结果集中的列名
			String[] colNames=this.getColNames(rs);
			Object obj="";
			Blob blob=null;
			if(rs.next()){
				map=new HashMap<String,Object>();
				for(String colName:colNames){
					obj=rs.getObject(colName);
					if(obj!=null && "BLOB".equals(obj.getClass().getSimpleName())){
						blob=(Blob)obj;
						byte[] bt=null;
						InputStream is=null;
						try {
							is=blob.getBinaryStream();
							bt=new byte[(int) blob.length()];
							is.read(bt);
						} catch (IOException e) {
							e.printStackTrace();
						}finally{
							if(is!=null){
								try {
									is.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

						map.put(colName, bt);
					}else{
						map.put(colName, obj);
					}
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			this.clossAll(rs, pstmt, con);
		}
		return map;
	}

	/**
	 * 查询操作   
	 * @param sql 要执行的查询语句
	 * @param params 查询语句中对应的占位符？的值
	 * @return  list集合
	 * 
	 */
	public List<Map<String,Object>> finds(String sql,Object ...params){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		try {
			con=JDBC.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			//获取结果集
			rs=pstmt.executeQuery();
			//获取结果集中的列名
			String []colNames=this.getColNames(rs);
			Object obj="";
			Blob blob=null;
			while(rs.next()){//每次迭代就是一行数据 将这行数据存放进一个map中，以列名为键，以对应列的值为值
				Map<String,Object> map=new HashMap<String ,Object>();
				for(String colName:colNames){//循环所有列，通过列名取出对应的值
					obj=rs.getObject(colName);
					if(obj!=null && "BLOB".equals(obj.getClass().getSimpleName())){
						blob=(Blob)obj;
						byte[] bt=null;
						InputStream is=null;
						try {
							is=blob.getBinaryStream();
							bt=new byte[(int) blob.length()];
							is.read(bt);
						} catch (IOException e) {
							e.printStackTrace();
						}finally{
							if(is!=null){
								try {
									is.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

						map.put(colName, bt);
					}else{
						map.put(colName, obj);
					}

				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.clossAll(rs, pstmt, con);
		}
		return list;
	}

	public   Object get(String sql,Class<?> c,Object ...params){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Object obj=null;

		try{
			con=JDBC.getConnection();//获取连接
			pstmt=con.prepareStatement(sql);//创建预编译执行语句块
			this.setValue(pstmt, params);//给预编译执行语句跨的占位符？赋值
			rs=pstmt.executeQuery();//执行预编译语句块 获取结果集
			//处理结果，将结果集的每一行数据变成一个一个的对象
			//获取给定类中是所有setter方法
			Method []methods=c.getDeclaredMethods();//获取所有方法

			List<Method> setters=new ArrayList<Method>();//存放所有的setter方法
			Map<String ,String> typeNames=new HashMap<String ,String>();//存放对应方法的类型
			for(Method method:methods){
				if(method.getName().startsWith("set")){
					setters.add(method);
					Class<?>[] cls=method.getParameterTypes();
					if(cls!=null){
						//以当前方法的方法名为键，以当前方法的第一个形参的参数类参数为值
						typeNames.put(method.getName(), cls[0].getSimpleName());//取出这个方法的第一个形参的参数类型名称
					}
				}
			}
			//获取所有列的列名
			String []colNames=this.getColNames(rs);
			String methodName=null;//当前循环的这个列对应的方法的方法名 
			String currName=null;//当前方法的方法名
			String typeName=null;//当前方法的第一个形参的参数类型名称
			//循环结果集中的所有数据，将其转换为对象
			//循环所有列的列名和方法 来匹配对应的方法
			if(rs.next()){

				obj=c.newInstance();
				//实例化一个对应的对象new Object() 此时会调用对象的无参构造方法
				for(String colName:colNames){
					methodName="set"+colName;
					for(Method method:methods){
						currName=method.getName();
						if(methodName.equalsIgnoreCase(currName)){//如果能找到对应的方法，那么接下来应该激活这个方法把这个值注入到对应的属性中
							//obj.setDeptno(10)-->obj.setDeptno(rs.getInt(colName));
							//激活obj这个对象的method 这个方法，这个方法的参数为rs.getXxx(colName);
							//问题： 当我们激活这方法时，给定的参数必须跟这个方法所需的参数类型一致，否则会激活失败

							//即：我们现在需要解决的是如何获取这个方法的第一个形参的参数类型
							typeName=typeNames.get(currName);
							if(typeName!=null){
								if("int".equals(typeName)||"Integer".equals(typeName)){
									method.invoke(obj, rs.getInt(colName));
								}else if("double".equals(typeName)||"Double".equals(typeName)){
									method.invoke(obj, rs.getDouble(colName));
								}else if("float".equals(typeName)||"Float".equals(typeName)){
									method.invoke(obj, rs.getFloat(colName));
								}else {//这里后期还要加判断
									method.invoke(obj, rs.getString(colName));

								}
							}
						}
					}
				}

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		finally{
			this.clossAll(rs, pstmt, con);
		}

		return obj;


	}

	/**
	 * 基于实体类的查询  返回多条结果
	 * @param sql 要执行的SQL语句
	 * @param c  给定的类
	 * @param params  查询语句中对应的占位符？的值
	 * @return list集合
	 */
	public  <T> List<T> gets(String sql,Class<?> c,Object ...params){
		List<T> list =new ArrayList<T>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBC.getConnection();//获取连接
			pstmt=con.prepareStatement(sql);//创建预编译执行语句块
			this.setValue(pstmt, params);//给预编译执行语句块的占位符？赋值
			rs=pstmt.executeQuery();//执行预编译语句块 获取结果集
			//处理结果，将结果集的每一行数据变成一个一个的对象
			//获取给定类中是所有setter方法
			Method []methods=c.getDeclaredMethods();//获取所有方法
			List<Method> setters=new ArrayList<Method>();//存放所有的setter方法
			Map<String ,String> typeNames=new HashMap<String ,String>();//存放对应方法的类型
			for(Method method:methods){
				if(method.getName().startsWith("set")){
					setters.add(method);
					Class<?>[] cls=method.getParameterTypes();
					if(cls!=null){
						//以当前方法的方法名为键，以当前方法的第一个形参的参数类参数为值
						typeNames.put(method.getName(), cls[0].getSimpleName());//取出这个方法的第一个形参的参数类型名称
					}
				}
			}
			//获取所有列的列名
			String []colNames=this.getColNames(rs);
			T obj=null;
			String methodName=null;//当前循环的这个列对应的方法的方法名 
			String currName=null;//当前方法的方法名
			String typeName=null;//当前方法的第一个形参的参数类型名称
			Blob blob=null;
			//循环结果集中的所有数据，将其转换为对象
			//循环所有列的列名和方法 来匹配对应的方法
			while(rs.next()){
				obj=(T)c.newInstance();
				//实例化一个对应的对象new Object() 此时会调用对象的无参构造方法
				for(String colName:colNames){
					methodName="set"+colName;
					for(Method method:methods){
						currName=method.getName();
						if(methodName.equalsIgnoreCase(currName)){//如果能找到对应的方法，那么接下来应该激活这个方法把这个值注入到对应的属性中
							//obj.setDeptno(10)-->obj.setDeptno(rs.getInt(colName));
							//激活obj这个对象的method 这个方法，这个方法的参数为rs.getXxx(colName);
							//问题： 当我们激活这方法时，给定的参数必须跟这个方法所需的参数类型一致，否则会激活失败
							//即：我们现在需要解决的是如何获取这个方法的第一个形参的参数类型
							typeName=typeNames.get(currName);
							if(typeName!=null){
								if("int".equals(typeName)||"Integer".equals(typeName)){
									method.invoke(obj, rs.getInt(colName));
								}else if("double".equals(typeName)||"Double".equals(typeName)){
									method.invoke(obj, rs.getDouble(colName));
								}else if("float".equals(typeName)||"Float".equals(typeName)){
									method.invoke(obj, rs.getFloat(colName));
								}else if("BLOB".equals(typeName)){
									blob=(Blob)obj;
									byte[] bt=null;
									InputStream is=null;
									try {
										is=blob.getBinaryStream();
										bt=new byte[(int) blob.length()];
										is.read(bt);
									} catch (IOException e) {
										e.printStackTrace();
									}finally{
										if(is!=null){
											try {
												is.close();
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
									method.invoke(obj, bt);

								}
								else {//这里后期还要加判断
									method.invoke(obj, rs.getString(colName));
								}
							}
						}
					}
				}
				list.add(obj);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		finally{
			this.clossAll(rs, pstmt, con);
		}

		return list;

	}


}

