package com.yangbang.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

public class SharedPreferencesUtil {
	
	public static String SharedPreferenceName = "miwo";
	
	private static SharedPreferences sp;
	private static String user[] = new String[2];
	public static void setSharedPreferences(SharedPreferences sp_01) {
		sp = sp_01;
	}
	
	public static String getSharedPreferences(String settingName,String defalut){
		
		String string = sp.getString(settingName,defalut);
		return string;
	}

    public static Set<String> getSetSharedPreferences(String settingName,Set<String> defalut){
        Set<String> string = sp.getStringSet(settingName,defalut);
        return string;
    }
	
	public static long getLongSharedPreferences(String settingName){
		
		long id = sp.getLong(settingName,0);
		return id;
	}
	public static SharedPreferences getSharedPreferences() {
		
		return sp;
	}
	
	public static boolean cleanSharePreferences() {
		Editor edit = sp.edit();
		edit.clear();
		return edit.commit();
	}

	public static boolean isBugHappen() {
//		String mark = sp.getString("Bug", "0");
		return sp.getBoolean("Bug", false);
	}
	
	public static void setBugHappen() {
		
		Editor edit = sp.edit();
		edit.putBoolean("Bug", true);
		edit.commit();
	}


	
	public static void setBugNotHappen() {
		
		Editor edit = sp.edit();
		edit.putBoolean("Bug",false);
		edit.commit();
	}
	public static String getUpLoadString() {
		String data = "";
		if(isBugHappen()) {
			
			data = sp.getString("BugString", "没有存储Bug");
			Editor edit = sp.edit();
			edit.putBoolean("Bug", false);
			edit.commit();
		}else {
			
		}
		return data;
	}
	public static void putUpLoadString(String string) {
		
		Editor edit = sp.edit();
		edit.putString("BugString", string);
		edit.putBoolean("Bug", true);
		edit.commit();
	}
	
	public static void commitResult(String name,int value){
		Editor editor = sp.edit();
		editor.putInt(name,value);
		editor.commit();
	};
	
	public static void commitResult(String name,boolean value){
		Editor editor = sp.edit();
		editor.putBoolean(name,value);
		editor.commit();
	};
	
	public static void commitResult(String name,String value){
		Editor editor = sp.edit();
		editor.putString(name,value);
		editor.commit();
	};

    public static void commitResult(String name,Set<String> values){

        Editor editor = sp.edit();
        editor.putStringSet(name,values);
        editor.commit();
    };
	
	public static void commitResult(String name,long value){
		Editor editor = sp.edit();
		editor.putLong(name, value);
		editor.commit();
	};
	

	
	public static String[] getUserName() {
		
		String id = sp.getString("id", null);
		if(id == null) {
			
			String name = sp.getString("name", null);
			if(name == null) {
				
				return null;
			}else {
				user[1] = 2+"";
				user[0] = name;
				return user;
			}
		}else {
			user[1] = 1+"";
			user[0] = id;
			return user;
		}
	}
	
	public static String[] getUserNameIgnoreEmpty() {
		
		String id = sp.getString("id", null);
		if(id == null) {
			
			String name = sp.getString("name", null);
			if(name == null) {
				
				user[0] = "";
				user[1] = "";
				return user;
			}else {
				user[0] = name;
				user[1] = 2+"";
				return user;
			}
		}else {
			user[0] = id;
			user[1] = 1+"";
			return user;
		}
	}

	
}
