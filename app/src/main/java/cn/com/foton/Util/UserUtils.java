package cn.com.foton.Util;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import android.content.Context;
import android.content.SharedPreferences;
import cn.com.foton.base.userBase;
import cn.com.foton.data.static_data;

public class UserUtils {
public static userBase getUserBase(Context t){
	SharedPreferences settings =t.getSharedPreferences(static_data.SETTING,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //获取一个 SharedPreferences 对象  
  
	
	
	String fcmuserid = settings.getString(static_data.FCMUSERID, "");
    String fcmpositionid = settings.getString(static_data.FCMPOSITIONID, "");
    String fcmcompanycode = settings.getString(static_data.FCMCOMPANYCODE, "");
    String fcmdealercode = settings.getString(static_data.FCMDEALERCODE, "");
	userBase base=new userBase();
  	base.setFcmUserId(fcmuserid);
  	base.setFcmPositionId(fcmpositionid);
  	base.setFcmCompanyCode(fcmcompanycode);
  	base.setFcmDealerCode(fcmdealercode);
	return base;
}
}
