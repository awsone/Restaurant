package com.yc.dao;



public class PredemineDao {
	/**
	 * 添加预定信息
	 * @param reserver
	 * @param dno
	 * @param rtel
	 * @param rtime
	 * @param dintime
	 * @param remark
	 * @return
	 */
	public int addPredemine(String reserver,String dno,String rtel,String dintime,String remark){
		DBHelper db=new DBHelper();
		String sql="insert into reserve values(seq_reserve_rid.nextval,?,?,?,sysdate,to_date(?,'yyyy-MM-dd-HH24'),?)";
		
		return db.update(sql, reserver,dno,rtel,dintime,remark);
		
	}

}
