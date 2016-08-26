package cn.com.foton.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class MyversionUtils {
	/**
	 * 
	 * ��ȡ��ǰ����İ汾��  
	 */  
	public static String getVersionName(Context t) throws Exception{  
	    //��ȡpackagemanager��ʵ��   
	    PackageManager packageManager = t.getPackageManager();  
	    //getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ  
	    PackageInfo packInfo = packageManager.getPackageInfo(t.getPackageName(), 0);  
	    return packInfo.versionName;   
	}  
}
