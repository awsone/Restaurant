package com.yc.dao;


import java.io.File;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DBToExcel {
//	public void ToExcel(){
//		try{
//		WritableWorkbook wwb = null;
//
//		// 创建可写入的Excel工作簿
//		String fileName = "D://book.xls";
//		File file=new File(fileName);
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		//以fileName为文件名来创建一个Workbook
//		wwb = Workbook.createWorkbook(file);
//
//		// 创建工作表
//		WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
//
//		//查询数据库中所有的数据
//		List<stuentity> list= StuService.getAllByDb();
//		//要插入到的Excel表格的行号，默认从0开始
//		Label labelId= new Label(0, 0, "编号(id)");//表示第
//		Label labelName= new Label(1, 0, "姓名(name)");
//		Label labelSex= new Label(2, 0, "性别(sex)");
//		Label labelNum= new Label(3, 0, "薪水(num)");
//
//		ws.addCell(labelId);
//		ws.addCell(labelName);
//		ws.addCell(labelSex);
//		ws.addCell(labelNum);
//		for (int i = 0; i < list.size(); i++) {
//
//			Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
//			Label labelName_i= new Label(1, i+1, list.get(i).getName());
//			Label labelSex_i= new Label(2, i+1, list.get(i).getSex());
//			Label labelNum_i= new Label(3, i+1, list.get(i).getNum()+"");
//			
//			
//			ws.addCell(labelId_i);
//			ws.addCell(labelName_i);
//			ws.addCell(labelSex_i);
//			ws.addCell(labelNum_i);
//		}
//
//		//写进文档
//		wwb.write();
//		// 关闭Excel工作簿对象
//		wwb.close();
//
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
//}

/**
 * 
 * @param rs
 * @param filePath    工作簿的路径
 * @param sheetName  工作簿的名字
 * @param columnName 列名
 */
	 public void WriteExcel(ResultSet rs, String filePath, String sheetName, Vector columnName) {  
	        WritableWorkbook workbook = null;  
	        WritableSheet sheet = null;
	        int rowNum = 1; // 从第一行开始写入  
	        try {  
	            workbook = Workbook.createWorkbook(new File(filePath)); // 创建Excel文件  
	            sheet = workbook.createSheet(sheetName, 0); // 创建名为 sheetName 的工作簿    
	              
	            this.writeCol(sheet, columnName, 0); // 首先将列名写入  
	            // 将结果集写入  
	            while(rs.next()) {  
	                Vector col = new Vector(); // 用以保存一行数据  
	                  
	                for(int i = 0; i < columnName.size(); i++) { // 将一行内容保存在col中  
	                    col.add(rs.getString(i));  
	                }  
	                // 写入Excel  
	                this.writeCol(sheet, col, rowNum++);  
	            }  
	              
	        }catch(Exception e) {  
	            e.printStackTrace();  
	        }  
	        finally {  
	            try {  
	                // 关闭  
	                workbook.write();  
	                workbook.close();  
	                rs.close();  
	            }catch(Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	    /*** 
	     * 将数组写入工作簿  
	     * @param sheet 要写入的工作簿 
	     * @param col 要写入的数据数组 
	     * @param rowNum 要写入哪一行 
	     */  
	    private void writeCol(WritableSheet sheet, Vector col, int rowNum) 
	    
	    
	    
	    {  
	        int size = col.size(); // 获取集合大小  
	          
	        for(int i = 0; i < size; i++) { // 写入每一列  
	            Label label = new Label(i, rowNum, col.get(i).toString());   
	            try {
					sheet.addCell(label);
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        }  
	    }  
}


