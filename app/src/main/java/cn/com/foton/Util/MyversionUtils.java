package cn.com.foton.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class MyversionUtils {
	/**
	 * 
	 * 获取当前程序的版本号  
	 */  
	public static String getVersionName(Context t) throws Exception{  
	    //获取packagemanager的实例   
	    PackageManager packageManager = t.getPackageManager();  
	    //getPackageName()是你当前类的包名，0代表是获取版本信息  
	    PackageInfo packInfo = packageManager.getPackageInfo(t.getPackageName(), 0);  
	    return packInfo.versionName;   
	}  
}
