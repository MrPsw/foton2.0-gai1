package cn.com.foton.weather;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.zip.Inflater;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import cn.com.foton.baiduMap.baiduMap;
import cn.com.foton.baiduMap.baiduMap.weathers;
import cn.com.foton.base.weatherBase;
import cn.com.foton.base.weatherBase.result.data;
import cn.com.foton.base.weatherBase.result.data.realtime;

/**
 * 天气预报
 * @author Administrator
 *
 */



public class weather {
	String TAG="weather";
Handler h;
	public weather(Handler hs,Context ct) {
		// TODO Auto-generated constructor stub
		h=hs;
		
	}
	 realtime realtimes;
	private String citycode;
public  void Getweather(String city){

try {
	citycode = URLEncoder.encode(city,"utf8");
	Log.e(TAG,citycode);
} catch (UnsupportedEncodingException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

final String	path = "http://op.juhe.cn/onebox/weather/query?cityname="+citycode+"&key=0dc4192f68eaf31f2f1967b956a3c43b";

	new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//对象
			OkHttpClient okhttp=new OkHttpClient();
			//创建请求
			Request request=new Request.Builder().url(path).build();
			Call call = okhttp.newCall(request);
			try {
				 Response response = call.execute();
				 if(response.isSuccessful()){}
				String text=response.body().string();
				Gson gson = new Gson();
				weatherBase u = gson.fromJson(text, weatherBase.class);
				data result = u.result.data;
				 realtimes = result.realtime;
				String s=realtimes.date;
				
				Message m=new Message();
				m.what=1;
				m.obj=realtimes;
				h.sendMessage(m);
				
			} catch (Exception e) {
				// TODO: handle exception

			}
		}
	}).start();

}



}
