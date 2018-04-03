package com.yc.dao;


import java.util.List;
import java.util.Map;

public class MenuDao {
	/**
	 * 添加菜
	 * @param mid
	 * @param mname
	 * @param sid
	 * @param mprice
	 * @param mmemo
	 * @param photo
	 * @return
	 */
	public int addMenu(String mname,String sid,String mprice,String mmemo ,byte[]photo){
		DBHelper db=new DBHelper();
		String sql="insert into menu values (seq_menu_id.nextval,?,?,?,?,?)";
		return db.update(sql, sid,mname,mprice,mmemo,photo);
		
	}
	public Map<String ,Object> findMenu(String mname){
		DBHelper db=new DBHelper();
		String sql="select * from menu where mname=?";
		return db.find(sql, mname);
		
	}
	
	public List<Map<String,Object>> getXiaoChao(){
		DBHelper db=new DBHelper();
		String sql="select m.photo,m.mname,m.mprice,m.mmemo from menu  m join sort s on m.sid=s.sid  where s.sname='炖炒类' ";
		return db.finds(sql);
	}

}
