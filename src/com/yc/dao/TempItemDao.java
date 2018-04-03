package com.yc.dao;

import java.util.List;
import java.util.Map;

public class TempItemDao {
	public int  addTempItem(String tempName,String tempPrice ,String tempSum ,int consumerId){
		DBHelper db=new DBHelper();
		String sql="insert into tempItem values(?,?,?,?)";
		return db.update(sql, tempName,tempPrice,tempSum,consumerId);
	}
	public List<Map<String,Object>> getTempItem(int consumerId){
		DBHelper db=new DBHelper();
      String sql="select * from tempItem where consumerId=?";
      return db.finds(sql, consumerId);
	}
}
