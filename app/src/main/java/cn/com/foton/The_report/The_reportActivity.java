package cn.com.foton.The_report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.R.id;
import cn.com.foton.R.layout;
import cn.com.foton.R.menu;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.TiemUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.allot.AllotActivity;
import cn.com.foton.allot.PlanActivity;
import cn.com.foton.base.Statement;
import cn.com.foton.base.Xiaoshoubase;
import cn.com.foton.base.Xiaoshoubase.Msg;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.tiem.TimeSelector;
import cn.com.foton.tiem.UserUtils2;
import cn.com.foton.tiem.Year_MonthUtils;
import cn.com.foton.tiem.Year_MonthUtils.ResultHandler;

public class The_reportActivity extends Activity {

	ArrayList<String> users;
	private userBase user;
	private RequestBody body;
	private Handler h=new Handler(){
		private cn.com.foton.base.Statement.Msg bases;

		public void handleMessage(android.os.Message msg) {
		switch (msg.what) {
		case 1:
			String jsonstring3=(String) msg.obj;
			
			String jsonString3= jsonT.Getjson(jsonstring3);
			System.out.println(jsonString3);
			
				Gson g3=new Gson();
				Xiaoshoubase base3 = g3.fromJson(jsonString3, Xiaoshoubase.class);
				List<Msg> msgbase = base3.msg;
				users=new ArrayList<>();
				users.add("所有顾问");
				SharedPreferencesUtils.addsharep2(The_reportActivity.this, "所有顾问","");
				for (int i = 1; i < msgbase.size(); i++) {
				users.add(msgbase.get(i).fcmRealName);
				
		
				SharedPreferencesUtils.addsharep2(The_reportActivity.this, msgbase.get(i).fcmRealName,msgbase.get(i).fcmUserId);
				}
							
					
				
			break;

		case 2:
			String jsonstring1=(String) msg.obj;
			
			String jsonString1= jsonT.Getjson(jsonstring1);
			if(jsonString1==null){
				if(dalog!=null){
					dalog.close();
				}
			
				Toast.makeText(The_reportActivity.this,"查询失败", 0).show();
			return ;	
			}
			System.out.println(jsonString1);
			Gson g=new Gson();
			Statement base= g.fromJson(jsonString1, Statement.class);
			List<cn.com.foton.base.Statement.Msg> s = base.msg;
			bases=s.get(0);
			fcmCustomerNum.setText(bases.fcmCustomerNum+"");
			fcmCustomerEffectiveNum.setText(bases.fcmCustomerEffectiveNum+"");
			fcmCustomerEffectiveRate.setText(bases.fcmCustomerEffectiveRate+"");
			fcmCustomerDealNum.setText(bases.fcmCustomerDealNum+"");
			fcmCustomerDealRate.setText(bases.fcmCustomerDealRate+"");
			fcmCustomerFailNum.setText(bases.fcmCustomerFailNum+"");
			fcmCustomerFailRate.setText(bases.fcmCustomerFailRate+"");
			fcmCustomerOverTimeNum.setText(bases.fcmCustomerOverTimeNum+"");
			fcmCustomerOverTimeRate.setText(bases.fcmCustomerOverTimeNum+"");
			if(dalog!=null){
			dalog.close();
			}
			Toast.makeText(The_reportActivity.this,"查询成功", 0).show();
			break;
		}
		};
		
	};
	private Button bt3;
	private Button bt1;
	private RequestBody body1;
	private int index;
	private Button bt2;
	private String key;
	private TextView fcmCustomerNum;
	private TextView fcmCustomerEffectiveNum;
	private TextView fcmCustomerEffectiveRate;
	private TextView fcmCustomerDealNum;
	private TextView fcmCustomerDealRate;
	private TextView fcmCustomerFailNum;
	private TextView fcmCustomerFailRate;
	private TextView fcmCustomerOverTimeNum;
	private TextView fcmCustomerOverTimeRate;
	private String str;
	private IOSdialog dalog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_the_report);
		actinbar();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
		str = sdf.format(curDate);  
			bt1 = (Button) findViewById(R.id.button1);
			bt2 = (Button) findViewById(R.id.button2);
			bt3 = (Button) findViewById(R.id.button3);
			LinearLayout li=(LinearLayout) findViewById(R.id.Lis);
			String id = SharedPreferencesUtils.querysharep2(this, "userid");
			if(id.equals("1")){
				li.setVisibility(View.GONE);
			}
			
			bt1.setText(str);
			bt2.setText(TiemUtils.getTiemYM_(str));
			fcmCustomerNum=(TextView)findViewById(R.id.fcmCustomerNum);
			fcmCustomerEffectiveNum=(TextView)findViewById(R.id.fcmCustomerEffectiveNum);
			fcmCustomerEffectiveRate=(TextView)findViewById(R.id.fcmCustomerEffectiveRate);
			fcmCustomerDealNum=(TextView)findViewById(R.id.fcmCustomerDealNum);
			fcmCustomerDealRate=(TextView)findViewById(R.id.fcmCustomerDealRate);
			fcmCustomerFailNum=(TextView)findViewById(R.id.fcmCustomerFailNum);
			fcmCustomerFailRate=(TextView)findViewById(R.id.fcmCustomerFailRate);
			fcmCustomerOverTimeNum=(TextView)findViewById(R.id.fcmCustomerOverTimeNum);
			fcmCustomerOverTimeRate=(TextView)findViewById(R.id.fcmCustomerOverTimeRate);
			
			user = UserUtils.getUserBase(this);
		
			RequestBody bodyt = new FormEncodingBuilder()
			        .add("fcmReportForm.fcmUserId", user.getFcmUserId())
			        .add("fcmReportForm.fcmPositionId", user.getFcmPositionId())
			        .add("fcmReportForm.fcmCompanyCode", user.getFcmCompanyCode())
			        .add("fcmReportForm.fcmDealerCode", user.getFcmDealerCode())
			        .add("fcmReportForm.fcmFromTime",str)
			        .add("fcmReportForm.fcmToTime",TiemUtils.getTiemYM_(str))
			        .add("fcmReportForm.fcmSalesManId","")
			        .build();
			  myHttp2 http2=new myHttp2(bodyt, App_url.statement, h, 2,The_reportActivity.this);
		        http2.start();
			
			body= new FormEncodingBuilder()
			        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
			        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
			        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
			        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
			        .build();	
			
		
			
			
			   myHttp2 http=new myHttp2(body, App_url.URL3, h, 1,The_reportActivity.this);
		        http.start();
			bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   
				// TODO Auto-generated method stub
				Year_MonthUtils t=new Year_MonthUtils(The_reportActivity.this,str, new ResultHandler() {
					
					@Override
					public void handle(String time) {
						// TODO Auto-generated method stub
						//Toast.makeText(The_reportActivity.this, ""+time, 0).show();
						bt1.setText(time);
					}
				});
			}
		});	
			bt2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Year_MonthUtils t=new Year_MonthUtils(The_reportActivity.this,TiemUtils.getTiemYM_(str), new ResultHandler() {
						
						@Override
						public void handle(String time) {
							// TODO Auto-generated method stub
							bt2.setText(time);
							//Toast.makeText(The_reportActivity.this, ""+time, 0).show();
							
						}
					});
				
				}
			});	
			bt3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					UserUtils2 t=new UserUtils2(The_reportActivity.this, users, new cn.com.foton.tiem.UserUtils2.ResultHandler() {
					
				
						@Override
						public void handle(String time) {
							// TODO Auto-generated method stub
							bt3.setText(time);
							key = SharedPreferencesUtils.querysharep2(The_reportActivity.this,time);
							System.out.println(time+key);
					
							
						}
					});	
				}  
			});
	}


	private void actinbar() {
		// TODO Auto-generated method stub
		TextView tv=(TextView) findViewById(R.id.action_text);
		ImageView actionimg=(ImageView) findViewById(R.id.action2_image);
		tv.setText("报表分析");
		actionimg.setImageResource(R.drawable.back);
		
		actionimg.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	}
	public void query(View v){
		body1 = new FormEncodingBuilder()
		        .add("fcmReportForm.fcmUserId", user.getFcmUserId())
		        .add("fcmReportForm.fcmPositionId", user.getFcmPositionId())
		        .add("fcmReportForm.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmReportForm.fcmDealerCode", user.getFcmDealerCode())
		        .add("fcmReportForm.fcmFromTime",bt1.getText().toString().trim())
		        .add("fcmReportForm.fcmToTime", bt2.getText().toString().trim())
		        .add("fcmReportForm.fcmSalesManId",key+"")
		        .build();
		dalog = new IOSdialog(The_reportActivity.this, "查询中");
		  myHttp2 http=new myHttp2(body1, App_url.statement, h, 2,The_reportActivity.this);
	        http.start();
	}
	
}
