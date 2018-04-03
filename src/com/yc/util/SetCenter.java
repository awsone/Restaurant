package com.yc.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.widgets.Shell;


/**
 * 该类用于将窗口置于中央
 * @author 大白猫😀😀😀小地瓜
 *
 */
public class SetCenter {


	public static void setCenter(Shell shell){
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();  
		int sHeight=dem.height;  
		int sWidth=dem.width;  
		int fHeight=shell.getSize().y;  
		int fWidth=shell.getSize().x;  
		shell.setLocation((sWidth-fWidth)/2, (sHeight-fHeight)/2);
	}
}
