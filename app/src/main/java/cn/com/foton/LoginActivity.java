package cn.com.foton;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import cn.com.foton.Activity.MainActivity;
import cn.com.foton.Activity.MarketActivity;
import cn.com.foton.Activity.updateBase;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.UP.Up;
import cn.com.foton.Util.JPushHttp;
import cn.com.foton.Util.MyversionUtils;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.base.gradeBase;
import cn.com.foton.base.gradeBase.msgs;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.data.static_data;
import cn.jpush.android.api.JPushInterface;
/**
 * ��½����
 * @author Administrator
 *
 */
public class LoginActivity extends Activity implements OnClickListener {
	private EditText et1;
	private EditText et2;
	private Button bt;
	private String name;
	private String pass;
	private String sha1;
	boolean istrue;
	int user;
//	private RequestBody bodys;
	public static final String NAME = "NAME";  
	public static final String PASSWORD = "PASSWORD";  
	public static final String SETTING_INFOS = "SETTINGInfos";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);

		
		uP();
		init();
		
		
	}
	private void uP() {
		// TODO Auto-generated method stub
		  JSONObject jsonObject = new JSONObject();
			JSONObject datat = new JSONObject();
		
			try {
				String s=MyversionUtils.getVersionName(this);
				
				Log.e("cn.com.foton", "�汾�ţ�"+s);
				datat.put("Version",s);
				datat.put("MobileType",0);
				jsonObject.put("Data", datat);
				jsonObject.put("UserId","513871739021252058_ft");
				jsonObject.put("Token", "AFTTOKENCARPOWERVIC");
				// ��󷵻�jsonObject
				// ����
				final String json = jsonObject.toString();
				
				new JPushHttp(json, App_url.JPush_Update, h, 7, LoginActivity.this).start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	   
	}
	private void getUser() {
		// TODO Auto-generated method stub
	      SharedPreferences settings = getSharedPreferences(SETTING_INFOS,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //��ȡһ�� SharedPreferences ����  
	      //ȡ�������NAME��ȡ�����ֶ�����ֵ���������򴴽�Ĭ��Ϊ��  
	        String name = settings.getString(NAME, ""); //ȡ������� NAME  
	        String password = settings.getString(PASSWORD, ""); //ȡ������� PASSWORD  
	        //Set value  
	        et1.setText(name); //��ȡ�������û������� field_name  
	        et2.setText(password); //��ȡ���������븳�� filed_pass  
	}
	private void rquesthttp() {
		
		name = et1.getText().toString().trim();
		pass = et2.getText().toString().trim();
		
		
		 cn.com.foton.Util.EncrySHA sha=new cn.com.foton.Util.EncrySHA();
		 try {
			sha1=sha.hexString(sha.eccryptSHA1(pass));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	System.out.println("����:"+pass);
	        System.out.println("����:"+sha1);
		
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
		
				// ��������
				OkHttpClient okhttp = new OkHttpClient();

			
				
				// new call
				String result = null;
				RequestBody body = new FormEncodingBuilder()
		        .add("appUser.fcmUserName", name)
		       .add("appUser.fcmPassWord", sha1).build();
				

		     
		        Request request = new Request.Builder()
		                .url(App_url.LONG_URL)
		                .post(body)
		                .build();
		      

				

				okhttp.newCall(request).enqueue(new Callback() {
					
					@Override
					public void onResponse(Response response) throws IOException {
						// TODO Auto-generated method stub
						if (response.isSuccessful()) {
							// The call was successful.print it to the log
							String text = response.body().string();
//							System.out.println(text);
							Message msg=new Message();
							msg.obj=text;
							msg.what=1;
							h.sendMessage(msg);
							
						}
					}
					
					@Override
					public void onFailure(Request arg0, IOException arg1) {
						// TODO Auto-generated method stub

					}
				});
				
			
			}
		}).start();
	}
	private void init() {
		et1=(EditText)findViewById(R.id.edit_user);
		et2=(EditText)findViewById(R.id.edit_pass);
		cb = (CheckBox) findViewById(R.id.checkBox1);
		TextView tv=(TextView) findViewById(R.id.versions);
		String versions = "δ֪";
		try {
			versions = MyversionUtils.getVersionName(LoginActivity.this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tv.setText("�汾��:V"+versions+"");
		getUser();
		
		bt=(Button)findViewById(R.id.login);
		bt.setOnClickListener(this);



		String code_type[]={"1216","1216","1505","1205","1205","1205","1205","1205","1205","1205"};
		String company_code[]={"1100","2600","2450","2290","2230","2601","6120","2220","1100","2600"};
		//��Ӳ�ͬ��ҵ���Ķ�Ӧ��ϵ
		for(int i=0;i<code_type.length;i++){
			SharedPreferencesUtils.addsharep2(LoginActivity.this,company_code[i],code_type[i]);
		}

	        
	     
		 
	}
	
	@Override
	public void onClick(View v) {
//		ProgressDialog p = ProgressDialog.createDialog(this);
//		p.showdialog();
		SHOWdialong();
		if(cb.isChecked()){
		SharedPreferences settings = getSharedPreferences(SETTING_INFOS,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //���Ȼ�ȡһ�� SharedPreferences ����  
		settings.edit()  
		.putString(NAME, et1.getText().toString().trim())  
		.putString(PASSWORD, et2.getText().toString().trim())  
		.commit();  
		}
		// TODO Auto-generated method stub
		rquesthttp();
		
//		for (int i = 0; i < listbody.size(); i++) {
////			myHttp2 http=new myHttp2(listbody.get(i),App_url.URL,h, 2+i,LoginActivity.this);
////			http.start();
//		}
		
		
	}
Handler h=new Handler(){
	private String listvlues;

	public void handleMessage(Message msg) {
		
		if(msg.what==1){
		
		String jsonString=(String) msg.obj;
		Log.e("��½����", jsonString);
		//showToast(jsonString);
		try {
		if(jsonString.contains("success:true")){
			jsonString=jsonString.replace("{success:true, msg:'","");
			jsonString=jsonString.replace("'}","");
			
			Log.e("TAG", jsonString);
			istrue=true;
			//����json����
			JSONObject root=new JSONObject(jsonString);
			
			SharedPreferences settings = getSharedPreferences(static_data.SETTING,Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE); //���Ȼ�ȡһ�� SharedPreferences ����  
			settings.edit()  
			.putString(static_data.FCMUSERID, root.getString("fcmUserId"))  
			.putString(static_data.FCMUSERTYPE,root.getString("fcmUserType"))  
			.putString(static_data.FCMDEALERCODE,root.getString("fcmDealerCode")) 
			.putString(static_data.FCMDEALERNAME,root.getString("fcmDealerName")) 
			.putString(static_data.FCMDEALERSHORTNAME,root.getString("fcmDealerShortName")) 
			.putString(static_data.FCMDEALERINDEPT,root.getString("fcmDealerInDept")) 
			.putString(static_data.FCMDEALERINAREA,root.getString("fcmDealerInArea")) 
			.putString(static_data.FCMCOMPANYCODE,root.getString("fcmCompanyCode")) 
			.putString(static_data.FCMPOSITIONID,root.getString("fcmPositionId")) 
			.commit(); 
			userBase ub=new userBase();
			ub.setFcmUserId(root.getString("fcmUserId"));
			ub.setFcmUserType(root.getString("fcmUserType"));
			ub.setFcmDealerCode(root.getString("fcmDealerCode"));
			ub.setFcmDealerName(root.getString("fcmDealerName"));
			ub.setFcmDealerShortName(root.getString("fcmDealerShortName"));
			ub.setFcmDealerInDept(root.getString("fcmDealerInDept"));
			ub.setFcmDealerInArea(root.getString("fcmDealerInArea"));
			ub.setFcmCompanyCode(root.getString("fcmCompanyCode"));
			ub.setFcmPositionId(root.getString("fcmPositionId"));
			ub.setFcmUpUserId(root.getString("fcmUpUserId")+"");
			
			
			String fcmDealerInDept=root.getString("fcmPositionId");
			alertDialog.close();
//			alertDialog.dismiss();
			//showToast(fcmDealerInDept);
			//showToast(ub.getFcmDealerName());
			if(fcmDealerInDept.contains("90010002")){
				SharedPreferencesUtils.addsharep2(LoginActivity.this, "userid","1");
				//showToast("�����˺�");
				JPush.initJPush(LoginActivity.this, ub.getFcmUserId());
				  
				System.out.println(ub.getFcmUserId());
				
				Intent i2=new Intent(LoginActivity.this, MarketActivity.class);
				startActivity(i2);
				finish();	
			}else if(fcmDealerInDept.contains("90010001")){
				//showToast("�����˺�");
				JPush.initJPush(LoginActivity.this, ub.getFcmUserId());
				SharedPreferencesUtils.addsharep2(LoginActivity.this, "userid","2");
				Intent i=new Intent(LoginActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			
				System.out.println(ub.getFcmUserId());
			}
			
		

			
			
		}else{
			showToast("��˶��û�������");
			alertDialog.close();
		}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		}
		switch (msg.what) {
		case 2:
			ArrayList<msgs> msgs = getJSON(msg);
				for (int i = 0; i < msgs.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs.get(i);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.code , base.codeName);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.codeName , base.code);
					listvlues=listvlues+"'"+base.code;
					SharedPreferencesUtils.addsharep(LoginActivity.this, "listvlues", listvlues);
					
				}
				listvlues=null;
			break;

		case 3:
			ArrayList<msgs> msgs2 = getJSON(msg);
				for (int i = 0; i < msgs2.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs2.get(i);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.code , base.codeName);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.codeName , base.code);
					listvlues=listvlues+"'"+base.code;
					SharedPreferencesUtils.addsharep(LoginActivity.this, "listvlues3", listvlues);
			
				}
				listvlues=null;
			break;
		case 4:
			ArrayList<msgs> msgs3 = getJSON(msg);
				for (int i = 0; i < msgs3.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs3.get(i);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.code , base.codeName);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.codeName , base.code);
					listvlues=listvlues+"'"+base.code;
					SharedPreferencesUtils.addsharep(LoginActivity.this, "listvlues4", listvlues);
					
				}
				listvlues=null;
			break;
		case 5:
			ArrayList<msgs> msgs4 = getJSON(msg);
				for (int i = 0; i < msgs4.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs4.get(i);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.code , base.codeName);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.codeName , base.code);
					listvlues=listvlues+"'"+base.code;
					SharedPreferencesUtils.addsharep(LoginActivity.this, "listvlues5", listvlues);
					
				}
				listvlues=null;
			break;
		case 6:
			String jsonstring=(String) msg.obj;
			String jsonString = jsonT.GetjsonAll(jsonstring);
				Gson gson=new Gson();
				gradeBase gb = gson.fromJson(jsonString, gradeBase.class);
				ArrayList<msgs> msg2 = gb.msg;
				for (int i = 0; i < msg2.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msg2.get(i);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.code , base.codeName);
					SharedPreferencesUtils.addsharep(LoginActivity.this,base.codeName , base.code);
					listvlues=listvlues+"'"+base.code;
					SharedPreferencesUtils.addsharep(LoginActivity.this, "listvlues6", listvlues);
					}
				listvlues=null;
			break;
		case 7:
			Gson gsont=new Gson();
			String jsonstringr=(String) msg.obj;
			updateBase update=gsont.fromJson(jsonstringr, updateBase.class);
			if(update.Code==0&&update.Data==1){
				Up up=new Up(LoginActivity.this);
			}
			break;
	
			
		}
	}

	public ArrayList<msgs> getJSON(Message msg) {
		String jsonstring=(String) msg.obj;
		String jsonString = jsonT.GetjsonAll(jsonstring);
			Gson gson=new Gson();
			gradeBase gb = gson.fromJson(jsonString, gradeBase.class);
			ArrayList<msgs> msgs = gb.msg;
		return msgs;
	};
	
};
	

private cn.com.foton.Dialog.IOSdialog alertDialog;
private CheckBox cb;
public void showToast(String text){
	Toast.makeText(LoginActivity.this,text,Toast.LENGTH_LONG).show();
}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	
	
}

private void SHOWdialong() {
	// TODO Auto-generated method stub
	


     	alertDialog=new IOSdialog(LoginActivity.this, "���ڵ�¼");

}

@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	System.out.println("-----onresume");
	JPushInterface.onResume(this);
	 
}

@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	
	JPushInterface.onPause(this);
}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
    if (keyCode == KeyEvent.KEYCODE_BACK) {
    	
	
        return true;
    }

	return super.onKeyDown(keyCode, event);
}

}
