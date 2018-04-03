package com.yc.dao;

import java.util.List;
import java.util.Map;

public class ConsumerDao {
	public DatabaseOperatorDao dod=new DatabaseOperatorDao();
	
	/**
	 * 添加消费表
	 * @param dno
	 * @param money
	 * @param ctime
	 * @param operator
	 * @return
	 */
	public int addConsumer(String dno,String operator){
		DBHelper db=new DBHelper();
		String sql="insert into consumer values(seq_consumer_cid.nextval,?,0,sysdate,?,'未结账')";
		return db.update(sql, dno,operator);
		
	}
	public Map<String ,Object > getCid(String dno){
		DBHelper db=new DBHelper();
		String sql="select cid from consumer where dno=? and cstate='未结账'";
		return db.find(sql, dno);
	}
	public List<Map<String ,Object >> getConsumer(){
		DBHelper db=new DBHelper();
		String sql="select cid,dno from consumer where cstate='未结账'";
		return db.finds(sql);
	}
	/**
	 * 添加消费详细表
	 * @param consumer_num
	 * @param dish_id
	 * @param amount
	 * @param price
	 * @return
	 */
	public int addOrderItem(String consumer_num,String dish_id,String amount,String price){
		DBHelper db=new DBHelper();
		System.out.println(Integer.parseInt(consumer_num));
		System.out.println(Integer.parseInt(amount));
		System.out.println(Integer.parseInt(amount));
		System.out.println(Double.parseDouble(price));

		String sql="insert into orderItem(orderItem_id,consumer_num,dish_id,amount,price) values(seq_order_id.nextval,"+Integer.parseInt(consumer_num)+","+Integer.parseInt(dish_id)+","+Integer.parseInt(amount)+","+Double.parseDouble(price)+")";
	//System.out.println(dod.longHaul(sql));
		//return dod.longHaul(sql);
		
		return db.update(sql);
		
	}
	public int updateOrderItemState(String comsumerId){
		DBHelper db=new DBHelper();
		String sql="update consumer set cstate='已结账'where cid=?";
		return db.update(sql, comsumerId);
	}
	
}
