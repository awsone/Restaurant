package com.yc.bean;

public class Employee {
	private String id;             //编号
	private String ename;
	private String sex;
	private String position;   //职位
	private String password;  //密码
	private int role;             //角色：1代表管理员，0代表普通员工；
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int admin) {
		this.role = admin;
	}
	

}
