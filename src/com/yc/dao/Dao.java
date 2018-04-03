package com.yc.dao;

import java.util.Vector;

import com.yc.dao.*;
import com.yc.util.SetInfo;


public class Dao {
	public DatabaseOperatorDao dod=new DatabaseOperatorDao();
	private static Dao dao;

	static {
		dao = new Dao();
	}

	public static Dao getInstance() {
		return dao;
	}


	//用户

	public boolean updateEmployeeByName(int no,String name,String sex,String res,String pwd,int dmin) {
		return dod.longHaul("update employer set eno=" + no + ",esex='" + sex + "',epwd='" + pwd + "',eres='" + res + "',admin=" + dmin + " where ename='" + name + "'");
	}


	/*	public boolean isAdmin(String name){
		String sql="slect *from employer where";
	}
	 */	
	public boolean dUserByName(String name) {
		//System.out.println("10");

		String sql = "delete from employer where ename='" + name + "'";
		//e	System.out.println("11");

		return dod.longHaul(sql);
	}

	// Desk
	public Vector sDesk() {
		return dod.selectSomeRecord("select * from dintable");
	}

	public Vector sDeskByNum(int num) {
		//System.out.println("你好");
		//	return 1;
		return dod.selectOneRecord("select * from dintable where dno="+num+"");

	}

	//插入数据库
	public boolean insertDesk(String seating,String dstate) {
		//System.out.println("21");
		String state="空闲";
		String sql = "insert into dintable values(seq_desk_cid.nextval ,'" + seating
				+ "','"+state+"')";
		//System.out.println("20");
		return dod.longHaul(sql);

	}

	public boolean dDeskByNum(int num) {
		String sql = "delete from dintable where dno=" + num + "";
		return dod.longHaul(sql);
	}

	// 菜系名操作
	public Vector sSortName() {
		return dod.selectSomeValue("select sname from sort");
	}

	public Vector sSortById(String id) {
		return dod.selectOneRecord("select * from sort where sid=" + id);
	}

	public Vector sSortByName(String name) {
		return dod.selectSomeRecord("select * from sort where sname='" + name + "'");
	}
	//插入数据库
	public boolean insertSort(String name) {
		String sql = "insert into sort values(null,'" + name + "')";
		return dod.longHaul(sql);
	}

	public boolean dSortByName(String name) {

		String sql = "delete from sort where sname='" + name + "'";

		return dod.longHaul(sql);
	}



	//营业额查询

	//营业总金额
	public Object sumMoney(){
		String sql="select sum(money) from consumer";
		return  (dod.selectOnlyValue(sql)).toString();
	}

	//总笔数
	public String sumCount(){
		String sql="select count(*) from consumer";
		return  (dod.selectOnlyValue(sql)).toString();
	}

	//平均消费
	public String avgSum() {
		String sql="select avg(money) from consumer";
		return  (dod.selectOnlyValue(sql)).toString();
	}

	//
	public String sumPerson() {
		String sql="select sum(sumperson) from(select distinct(day1),sum(money) totalmoney,sum(dtype) sumperson from(select cid,money,to_char(ctime,'DD') day1,dtype from( select c.cid,c.dno,c.money,c.ctime,d.dtype from consumer c,dintable d  where c.dno = d.dno order by c.cid)) group by day1)";
		return  (dod.selectOnlyValue(sql)).toString();

	}

	//具体菜名，对应的销售金额
	public Vector menu_Money(){

		String sql="select m.mname,menusum from (select distinct(dish_id),sum(price*amount) menusum from orderItem o,menu m where o.dish_id=m.mid  group by dish_id order by menusum desc) a,menu  m where a.dish_id=m.mid";
		return dod.selectSomeRecord(sql);
	}

	public int addPredemine(String reserver,String dno,String rtel,String dintime,String remark){
		DBHelper db=new DBHelper();
		String sql="insert into reserve values(seq_reserve_rid.nextval,?,?,?,sysdate,to_date(?,'yyyy-MM-dd-HH24'),?)";
		
		return db.update(sql, reserver,dno,rtel,dintime,remark);
		
	}


	//订单点的菜的总数
	public String amount(){
		String sql="select count(*) from(select cid,dish_id from(select c.cid,o.dish_id from orderItem o,consumer c where o.consumer_num=c.cid) d,menu m where d.dish_id=m.mid) where cid=" + SetInfo.seq_consumer_id + "";
		return String.valueOf(dod.selectOnlyValue(sql));
	}



	///打印小票
	public Vector printTicket(int cid){

		String sql="select distinct(名称),单价，sum(数量) 数量,sum(单价*数量) 小计 from(select cid,m.mname 名称,m.mprice 单价,d.amount 数量,dish_id from(select c.cid,o.dish_id,o.amount from orderItem o,consumer c where o.consumer_num=c.cid) d,menu m where d.dish_id=m.mid and cid="+cid+")  group by 名称，单价";
		return dod.selectSomeRecord(sql);
	}



	//根据订单号查找餐桌号
	public String findDesk(String cid){
		String sql="select dno from consumer where cid='"+cid+"'";
		return String.valueOf(dod.selectOnlyValue(sql));

	}

}
