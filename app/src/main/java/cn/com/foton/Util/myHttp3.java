package cn.com.foton.Util;

import java.io.IOException;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import cn.com.foton.data.static_data;

public class myHttp3 extends Thread{
	RequestBody body;
	private String text;
	 String URL;
	 Handler hs;
	 int what;
	 /**
	  * 
	  * @param bodys 请求参数
	  * @param URLS	接口
	  * @param h	hanlder
	  * @param whats 
	  * @param ct  上下文
	  * @param fs	操作类型  1请求数据   2保存数据到服务器 3分配操作  4， 
	  */
	 Context context;
	public myHttp3(RequestBody bodys,String URLS,Handler h,int whats) {
		// TODO Auto-generated constructor stub
		body=bodys;
		URL=URLS;
		hs=h;
		what=whats;

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		OkHttpClient okhttp=new OkHttpClient();
		
		
		Request request = new Request.Builder()
        .url(URL)
        .post(body)
        .build();
		final Response response;
		okhttp.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				// TODO Auto-generated method stub
				try {
				
					if (response.isSuccessful()) {
						text = response.body().string();
						Message msg=new Message();
						msg.what=what;
						msg.obj=text;
						hs.sendMessage(msg);
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub


				Log.e("cn.com.foton", "请求失败");
				
			}
		});
		
		
		
		
	}
	

}
