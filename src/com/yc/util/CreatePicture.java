package com.yc.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.yc.dao.JDBC;
/**
 * @author Administrator 测试写入数据库以及从数据库中读取
 */
public class CreatePicture {

	
	//查找最受欢迎的
	public static void readTopImage() {
		String targetPath =null;
		int i=20;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String path="res/picture/";
		String pictureStyle=".png";

		try {
			conn = JDBC.getConnection();
			String sql = "select photo from(select* from(select distinct(dish_id),sum(price*amount) menusum from orderItem o,menu m where o.dish_id=m.mid  group by dish_id order by menusum desc) where rownum<=3),menu where dish_id=mid";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {      	
				targetPath = path+i+""+pictureStyle;
				InputStream in = rs.getBinaryStream("photo");
				ImageUtil.readBin2Image(in, targetPath);
				i++;
				ImageUtil.zoomImage(targetPath,targetPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC.closeConnection();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	
	
	// 读取数据库中图片
	public static void readDB2Image() {
		String targetPath =null;
		int i=1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String path="res/picture/";
		String pictureStyle=".png";

		try {
			conn = JDBC.getConnection();
			String sql = "select photo from(select* from(select distinct(dish_id),sum(price*amount) menusum from orderItem o,menu m where o.dish_id=m.mid  group by dish_id order by menusum) where rownum<=3),menu where dish_id=mid";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {      	
				targetPath = path+i+""+pictureStyle;
				InputStream in = rs.getBinaryStream("photo");
				ImageUtil.readBin2Image(in, targetPath);
				i++;
				ImageUtil.zoomImage(targetPath,targetPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC.closeConnection();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	//测试
	public static void main(String[] args) {
		//readImage2DB();
		readDB2Image();
		readTopImage();
	}
}


/**
 * 
 */


class ImageUtil {



	/**
	 * 改变图片尺寸
	 * @param srcFileName 源图片路径
	 * @param tagFileName 目的图片路径
	 * @param width 修改后的宽度S
	 * @param height 修改后的高度
	 */
	public static void zoomImage(String srcFileName,String tagFileName){  
		
		try {
			BufferedImage bi = ImageIO.read(new File(srcFileName));
			BufferedImage tag=new BufferedImage(110,110, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(bi, 0, 0, 110, 110, null);
			ImageIO.write(tag, "jpg", new File(tagFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 读取本地图片获取输入流
	public static FileInputStream readImage(String path) throws IOException {
		return new FileInputStream(new File(path));
	}
	// 读取表中图片获取输出流
	public static void readBin2Image(InputStream in, String targetPath) {
		File file = new File(targetPath);
		String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
		if (!file.exists()) {
			new File(path).mkdir();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

