package cn.com.foton.Util;

import java.io.IOException;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.Will.Will_tab1_Activity;
import cn.com.foton.data.static_data;

public class JPushHttp extends Thread{
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	RequestBody body;
	private String text;
	 String URL;
	 Handler hs;
	 int what;
	 /**
	  * 
	  * @param bodys �������
	  * @param URLS	�ӿ�
	  * @param h	hanlder
	  * @param whats 
	  * @param ct  ������
	  * @param fs	��������  1��������   2�������ݵ������� 3�������  4�� 
	  */
	 Context context;
	public JPushHttp(String json,String URLS,Handler h,int whats,Context ct) {
		// TODO Auto-generated constructor stub
		body= RequestBody.create(JSON, json);
		URL=URLS;
		hs=h;
		what=whats;
		context=ct;
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
			
			private IOSdialog dialog;

			@Override
			public void onResponse(Response response) throws IOException {
				// TODO Auto-generated method stub
				try {
					if(what==404){
						return;
					}
					if (response.isSuccessful()) {
						text = response.body().string();
						Log.e("cn.com.foton", "������"+text);
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
//				 Looper.prepare();
//				Toast.showToast(context,  "����ʧ�ܣ������������+1", 3);
//	          //  Toast.makeText(context, "����ʧ�ܣ������������", 1).show();
//	         	Looper.loop();
//	            Looper.loop();// ����loop�е�ѭ�����鿴��Ϣ����
//				dialog=new IOSdialog((Activity)context, "����ʧ��");
//				Looper.loop();
				if(what==404){
					return;
				}
	        	Message msg=new Message();
				msg.what=what;
				msg.obj=text;
				H.sendMessage(msg);
				Log.e("cn.com.foton", "����ʧ��");
		
			
				
			}
		});
		
		
		
		
	}
	Handler H=new Handler(){
		public void handleMessage(android.os.Message msg) {
			Toast.showToast(context,  "����ʧ�ܣ������������", 3);
			
		};
	};

}
