package com.yc.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.Employee;

public class EmployeeDao {
	JDBC connection = new JDBC();
	Connection conn = null;



	//像employee表中插入数据
	public void insertEmployee(Employee employee){
		conn=JDBC.getConnection();
		if(conn==null){
			System.out.println("数据库连接失败！！");
		}

		try {
			//定义查询数据库的SQL语句
			PreparedStatement statement=conn.prepareStatement("insert into employer values(seq_employer_id.nextval,?,?,?,?,?)");
		//	statement.setString(1,employee.getId());			
			statement.setString(1,employee.getEname());			
			statement.setString(2,employee.getSex());
			statement.setString(3,employee.getPosition());
			statement.setString(4,employee.getPassword());
			statement.setInt(5,employee.getRole());			
			statement.executeUpdate();	        //执行插入操作

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	//查询员工信息表中查询所有数据的方法
	public List selectEmployee(){
		List list=new ArrayList<Employee>();
		conn=connection.getConnection();


		try {
			Statement statement=conn.createStatement();
			ResultSet rest=statement.executeQuery("select * from employer");    //执行查询语句获得查询结果集
			while(rest.next()){
				Employee emp=new Employee();
				emp.setId(rest.getString(1));
				emp.setEname(rest.getString(2));
				emp.setSex(rest.getString(3));
				emp.setPosition(rest.getString(4));
				emp.setPassword(rest.getString(5));
				emp.setRole(rest.getInt(6));			
				list.add(emp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

		return list;
	}
	
	
	public Map<String,Object> adminLogin(String ename,String epwd){
		DBHelper dbhelper=new DBHelper();
		String sql="select ename,epwd from employer where ename=? and epwd=?";
		//System.out.println(dbhelper.find(sql, ename,epwd).size()); 
		return dbhelper.find(sql, ename,epwd);
	}
	//查询当前登录的员工
	public boolean isAdmin(String ename){
		DBHelper dbhelper=new DBHelper();
		String sql="select admin from employer where ename=? ";
		Map<String,Object> map=dbhelper.find(sql, ename);
	//	System.out.println(map.get("admin"));
		if(map.get("admin").equals(0)){	
			return false;

		}
		return true;

	}
	//查询所有员工
	public List<Map<String, Object>> find() {
		DBHelper db=new DBHelper();
		String sql="select * from employer";
		return db.finds(sql);	
	}

	//查询单个员工
	public List<Map<String, Object>> findEmploye(String ename) {
		DBHelper db=new DBHelper();
		String sql="select * from employer where ename=?";
		return db.finds(sql,ename);	
	}

	//删除员工
	public List<Map<String, Object>> deleteEmploye(Integer eno) {
		DBHelper db=new DBHelper();
		String sql="delete * from employer where eno?";
		return db.finds(sql,eno);	
	}
	
	public List<Map<String,Object>> getAllEmployee(){
		DBHelper dbhelper=new DBHelper();
		String sql="select  ename from employer ";
		return dbhelper.finds(sql);
	}



}

