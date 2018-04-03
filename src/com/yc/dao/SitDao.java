package com.yc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SitDao {
	/**
	 * 查询总桌子数
	 * @return
	 */
	public  Map<String ,Object> getAllTable(){
		DBHelper db=new DBHelper();
		String sql="select count(*) from  dintable";
		System.out.println(db.find(sql).get("count(*)"));
		return db.find(sql);
	}
	/**
	 * 查询用餐桌子数
	 * @return
	 */
	public  Map<String ,Object> getEatTable(){
		DBHelper db=new DBHelper();
		String sql="select count(*) 用餐数 from  dintable where dstate='用餐' ";
		return db.find(sql);
	}
	/**
	 * 查询空闲桌子数
	 * @return
	 */
	public  Map<String ,Object> getEmptyTable(){
		DBHelper db=new DBHelper();
		String sql="select count(*) 空闲数 from  dintable where dstate='空闲' ";
		return db.find(sql);
	}
	
	public  List<Map<String, Object>> getEmptyTableId(){
		DBHelper db=new DBHelper();
		String sql="select dno  from  dintable where dstate='空闲' ";
		return db.finds(sql);
	}
	
	/**
	 * 查询预定桌子数
	 * @return
	 */
	public  Map<String ,Object> getOrderTable(){
		DBHelper db=new DBHelper();
		String sql="select count(*) 预定数 from  dintable where dstate='预定' ";
		return db.find(sql);
	}
	
	public List<Map<String,Object>> getAll(){
		DBHelper db=new DBHelper();
		String sql="select * from  dintable order by dno asc";
		return db.finds(sql);
	}
	public int updateState(String dno ,String newState){
		DBHelper db=new DBHelper();
		String sql="update dintable  set dstate=? where dno=?";
		return db.update(sql,newState ,dno);
		
	}
	public Map<String,Object> getDType(String dno){
		DBHelper db=new DBHelper();
		String sql="select dtype from dintable where dno=?";
		return db.find(sql, dno);
	}
	public List<Map<String, Object>> getSitID(String dtype){
		DBHelper db=new DBHelper();

		String sql="select dno  from  dintable where dstate='空闲' and dtype>=?";
		return db.finds(sql, dtype);

	}
	public String[] getSitId(String dtype){
		int count=0;
		System.out.println(dtype);
		List<Map<String,Object>> list=getSitID(dtype);
		String [] id=new String[list.size()];
		for(Map<String, Object> map:list){
			id[count++]=String.valueOf(map.get("dno"));
			System.out.println(map);
		}
		return id;
	}
	
	public String countdesk(){
		String sql="select count(*) from dintable";
		return  (new DatabaseOperatorDao().selectOnlyValue(sql)).toString();
	}
	
	
	public List<Map<String, Object>> getSitID(int dtype){
		System.out.println("diii"+dtype);
		DBHelper db=new DBHelper();
		String sql="select dno  from  dintable where dstate='空闲' and dtype>=? order by dno";
		return  db.finds(sql, dtype);

	}
}
