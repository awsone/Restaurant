package com.yc.util;



import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class RegeditUtil {
	/**
	 * 从注册表中添加记录
	 * @param key
	 * @param value
	 */
	public void addRecord(String key,String value){
		Preferences pre =Preferences.userNodeForPackage(RegeditUtil.class);
		pre.put(key, value);
		try {
			pre.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 获取注册表中指定路径下的所有值
	 * @return
	 */
	public Map<String,String> get(){
		Preferences pre =Preferences.userNodeForPackage(RegeditUtil.class);
		Map<String,String> map=new HashMap<String,String>();
		
		try {
			String[] keys = pre.keys();
			if(keys!=null&&keys.length>0){
				for(String key:keys){
					map.put(key, pre.get(key, null));
				}
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据键从注册表中移除值
	 * @param key
	 */
	public void remove(String key){
		Preferences pre =Preferences.userNodeForPackage(RegeditUtil.class);
		pre.remove(key);

	}


}
