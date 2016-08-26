package cn.com.foton.Util;

import java.io.IOException;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.data.static_data;

public class myHttp extends Thread{
	RequestBody body;
	private String text;
	 String URL;
	 Handler hs;
	public myHttp(RequestBody bodys,String URLS,Handler h) {
		// TODO Auto-generated constructor stub
		body=bodys;
		URL=URLS;
		hs=h;
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
						msg.what=1;
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
	
				Log.e("cn.com.foton", "«Î«Û ß∞‹");
				
			}
		});
		
		
		
		
	}
	

}
