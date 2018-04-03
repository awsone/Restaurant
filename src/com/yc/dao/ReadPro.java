package com.yc.dao;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPro extends Properties{
	private static ReadPro instance=new ReadPro();
	private ReadPro(){
		InputStream is=null;
		try {
			is=this.getClass().getClassLoader().getResourceAsStream("db.properties");			
			this.load(is);

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public static ReadPro getInstance(){
		return instance;
	}

}
