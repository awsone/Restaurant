package com.yc.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class ScaleImage {
	public static Image ScaleToImage(File f1,Label label){
		return ScaleToImage( f1,label.getSize().x,label.getSize().y);
	}

	private static Image ScaleToImage(File f1, int width, int height) {
		InputStream is=null;
		try {
			is=new FileInputStream(f1);
			//缩略图片
			ImageData imagedata=new ImageData(is);
			imagedata=imagedata.scaledTo(width, height);
			return new Image(null,imagedata);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	public static Image byteToImage(byte[] bt, int width,int height){
		InputStream is=null;
		try{
			is=new ByteArrayInputStream(bt);
			ImageData imageData=new ImageData(is);
			imageData=imageData.scaledTo(width, height);
			
			return new Image(null,imageData);
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
