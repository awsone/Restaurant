package com.yc.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.panel.*;


import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;



public class SetInfo {
	//堆栈式布局
	public static StackLayout sl=new StackLayout();
	public static MenuInfo menu;
	public static Sit sit;
	public static Category cg;
	public static EmployeeModify epm;
	public static MenuModify mem;
	public static Predetemine pdm;
	public static TableManage tmg;
	public static String ename;
	public static Revenue rev;        //营业额


	public static List<TableItem> list;//存放定的菜
	public static OrderManager orderManager;
	public static double count;      //花费
	public static double keep;//找零
	public static double pay;//实付
	public static String dno;            //餐桌编号
	public static Composite composite;
	public static String seq_consumer_id;                 //消费单号ID
	public static Map<String,Composite> map=new HashMap<String,Composite>();
	public static List<Map<String,Object>> lists;
	public static List<Map<String,Object>> orderItem;
	public static String mname;  //今日特价菜
	public static int amount; //订单菜的总数
	public static int tableState=0;  //0表示空闲状态  1表示用餐状态  2表示预定状态
	public static Map<String,Object> tempConsumer=new HashMap<String,Object>();















}
