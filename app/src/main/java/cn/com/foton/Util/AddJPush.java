package cn.com.foton.Util;

import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.com.foton.LoginActivity;
import cn.com.foton.Defeat.FollowupActivity;
import cn.com.foton.JPushBase.allotBase;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;

public class AddJPush {
	
	public static void allothttp(Context c,allotBase b){
		try {
			userBase user = UserUtils.getUserBase(c);
			JSONObject jsonObject = new JSONObject();
			JSONObject datat = new JSONObject();
			datat.put("CustomerId",b.CustomerId);
			System.out.println(b.CustomerId);
			datat.put("BusinessId",b.BusinessId);
			System.out.println(b.BusinessId);
			datat.put("FromUserId",b.FromUserId);
			System.out.println(b.FromUserId);
			datat.put("ToUserId",b.ToUserId);
			System.out.println(b.ToUserId);
			datat.put("Message","你有一条新销售线索");
			datat.put("Title","福田经销商");
			datat.put("CustomerName",b.CustomerName);
			datat.put("Phone",b.Phone);
			datat.put("CarType",b.CarType);
			datat.put("BuyTime",b.BuyTime);
			datat.put("FailReason",b.FailReason);

			jsonObject.put("Data", datat);
			System.out.println(user.getFcmUserId());
			jsonObject.put("UserId", user.getFcmUserId() + "_ft");
			jsonObject.put("Token", "AFTTOKENCARPOWERVIC");
			// 最后返回jsonObject
			// 或者
			final String json = jsonObject.toString();
			
			new JPushHttp(json,App_url.JPush_add, h, 7, c).start();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Handler h=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
	};
}
