package cn.com.foton.Util;

import android.content.Context;
import android.content.SharedPreferences;
import cn.com.foton.data.static_data;

public class SharedPreferencesUtils {
public SharedPreferencesUtils(String name){
	// TODO Auto-generated constructor stub
}	
	
public static void addsharep(Context c,String KEY,String value){
	SharedPreferences settings =c.getSharedPreferences(static_data.SETTING,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //���Ȼ�ȡһ�� SharedPreferences ����  
	settings.edit()  
	.putString(KEY, value)  
	.commit(); 
}
public static String querysharep(Context c,String KEY){
	
    SharedPreferences settings = c.getSharedPreferences(static_data.SETTING,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //��ȡһ�� SharedPreferences ����  
  	String value = settings.getString(KEY, "");
	return value;
}
public static void addsharep2(Context c,String KEY,String value){
	SharedPreferences settings =c.getSharedPreferences(static_data.SETTING2,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //���Ȼ�ȡһ�� SharedPreferences ����  
	settings.edit()  
	.putString(KEY, value)  
	.commit(); 
}
public static String querysharep2(Context c,String KEY){
	
    SharedPreferences settings = c.getSharedPreferences(static_data.SETTING2,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //��ȡһ�� SharedPreferences ����  
  	String value = settings.getString(KEY, "");
	return value;
}

}
