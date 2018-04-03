package com.yc.dao;

import java.util.List;
import java.util.Map;


public class GoodTypeDao {
	//获取所有菜品分类信息
	public List<Map<String, Object>> findSort() {
		DBHelper db=new DBHelper();
		String sql="select sid,sname from sort";
		return db.finds(sql);	
	}

	//获取所有菜品信息
	public List<Map<String, Object>> findMenu(Integer sid) {
		DBHelper db=new DBHelper();
		if(sid!=null){
			String sql="select mid,mname,mprice,mmemo,photo from menu m,sort s where m.sid=s.sid and m.sid=?";
			return db.finds(sql,sid);
		}else{
			String sql="select mid,mname,mprice,mmemo,photo from menu m,sort s where m.sid=s.sid";
			return db.finds(sql);
		}
	}

	//添加菜品信息
	public int addGoods(Integer mid,String mname,Double price,String mmemo,byte[] photo) {
		DBHelper db=new DBHelper();
		String sql="insert into goodsInfo values(seq_goodsInfo_gid,?,?,?,?,?)";
		return db.update(sql,mid,mname,price,mmemo,photo);
	}

	//删除菜品信息
	public int deleteGoods(String mname) {
		DBHelper db=new DBHelper();
		String sql="delete * from menu where mname=?";
		return db.update(sql,mname);
	}
	
	//查询菜品信息
	public int selectGoods(String mname) {
		DBHelper db=new DBHelper();
		String sql="select * from menu where mname like '%'+？+'%'";
		return db.update(sql,mname);
	}
}
