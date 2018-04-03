package com.yc.printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.yc.dao.Dao;
import com.yc.util.SetInfo;


public class Test {
	public static Dao dao;
	public static void main(String[] args) {
		dao=Dao.getInstance();
		List<GoodsInfo> goods=new ArrayList<GoodsInfo>();

		Vector vector=dao.printTicket(Integer.parseInt(SetInfo.seq_consumer_id));
	//Vector vector=dao.printTicket(1);

		//System.out.println(vector);
		if(vector != null && vector.size()>0){//如果vector中存入了数据，转化为数组  
			for(int i=0;i<vector.size();i++){
				goods.add(new GoodsInfo((((Vector) vector.get(i)).get(1)).toString(),((Vector) vector.get(i)).get(2)+"", ((Vector) vector.get(i)).get(3)+"",((Vector) vector.get(i)).get(4)+""));
				for(int j=0;j<((Vector) vector.get(i)).size();j++){

				}
			}
		}
		String deskNum1=dao.findDesk(SetInfo.seq_consumer_id);
		
		
		
		
        SalesTicket stk=new SalesTicket(goods,SetInfo.ename,SetInfo.seq_consumer_id,dao.amount(),String.valueOf(SetInfo.count),String.valueOf(SetInfo.pay),String.valueOf(SetInfo.keep),deskNum1);
		YcPrinter p=new YcPrinter(stk);
		p.printer();

	}

}
