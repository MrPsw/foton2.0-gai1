package cn.com.foton.allot;

import android.support.v7.app.ActionBarActivity;
import android.text.method.DateTimeKeyListener;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.R.id;
import cn.com.foton.R.layout;
import cn.com.foton.R.menu;
import cn.com.foton.Activity.MainActivity;
import cn.com.foton.Activity.MarketActivity;
import cn.com.foton.Util.DateTimePickDialogUtil;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.TiemUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.base.gradeBase;
import cn.com.foton.base.gradeBase.msgs;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.data.static_data;
import cn.com.foton.tiem.TimeSelector;

public class PlanActivity extends Activity implements OnClickListener {
	

	private TextView startDateTime;
	RequestBody bodys;
	private Spinner sp;
	private ArrayList<String> list;
	String listvlues=null;
	
	String tiem;
	String fangshi;
	private userBase user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan);
		startDateTime = (TextView) findViewById(R.id.tex);
		bt = (Button) findViewById(R.id.but);
		bt.setText(TiemUtils.getTiemYMD(TiemUtils.getTiem()));
		
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("跟进计划");
		
		Button tijiao=(Button) findViewById(R.id.tijiao);
//				bodys = new FormEncodingBuilder()
//		        .add("fcmCode.codeType","1801")
//		        .add("fcmCode.isAddress","false")
//		        .add("fcmCode.companyCode","1100")
//		        .build();
		
			user = UserUtils.getUserBase(this);
			sp = (Spinner) findViewById(R.id.spinner_Plan);
//		myHttp2 http=new myHttp2(bodys,App_url.URL,H, 1);
//		http.start();
			getsprinner();	
				bt.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				 TimeSelector timeSelector = new TimeSelector(PlanActivity.this, new TimeSelector.ResultHandler() {
			            @Override
			            public void handle(String time) {
			            	time=TiemUtils.getTiemYMD(time);
			            	bt.setText(time);
			            }
			        },TiemUtils.getTiem(), "2029-12-1 12:00");
				  
				  timeSelector.show();
			}
		});			
				getsprinner();	
				tijiao.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					tiem=bt.getText().toString().trim();
					tiem=TiemUtils.getTiemYMD(tiem);
					
					FcmCustomer base = FgActivity.getBase();
					
					String fcmCustomerId=base.fcmCustomerId;
					String fcmBusinessId=base.fcmBusinessId;
					String UserId = user.getFcmUserId();
					String PositionId = user.getFcmPositionId();
					String CompanyCode=user.getFcmCompanyCode();
					String DealerCode=user.getFcmDealerCode();
					bodys = new FormEncodingBuilder()
					        .add("fcmVisit.fcmUserId", UserId)
					        .add("fcmVisit.fcmPositionId", PositionId)
					        .add("fcmVisit.fcmCompanyCode", CompanyCode)
					        .add("fcmVisit.fcmDealerCode", DealerCode)
					        .add("fcmVisit.fcmCustomerId",fcmCustomerId+"")
					        .add("fcmVisit.fcmBusinessId",fcmBusinessId+"")
					        .add("fcmVisit.fcmPlanType", fangshi) 
					        .add("fcmVisit.fcmPlanDate", tiem) 
					        .build();
					
					
					myHttp2 http=new myHttp2(bodys, App_url.URLS,H, 2,PlanActivity.this);
					http.start();
					
						
					}
				});		
		
	}


	public void getsprinner() {
		list=new ArrayList<>();
		String listvlue = SharedPreferencesUtils.querysharep(this,"listvlues");
		String[] li = listvlue.split("'");
		for (int i = 1; i < li.length; i++) {
			String vlue = SharedPreferencesUtils.querysharep(this, li[i]);
			list.add(vlue);
		}
		
		setSpinner(sp, list);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				String s = list.get(position);
				String vluesss = SharedPreferencesUtils.querysharep(PlanActivity.this, s);
				System.out.println(vluesss+"_____________被选中"+s);
				fangshi=vluesss;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
Handler H=new Handler(){


	public void handleMessage(android.os.Message msg) {
		switch (msg.what) {
		case 1:
//			String jsonstring=(String) msg.obj;
//			String jsonString = jsonT.GetjsonAll(jsonstring);
//				Gson gson=new Gson();
//				gradeBase gb = gson.fromJson(jsonString, gradeBase.class);
//				ArrayList<msgs> msgs = gb.msg;
//				for (int i = 0; i < msgs.size(); i++) {
//					cn.com.foton.base.gradeBase.msgs base = msgs.get(i);
//					SharedPreferencesUtils.addsharep(PlanActivity.this,base.code , base.codeName);
//					SharedPreferencesUtils.addsharep(PlanActivity.this,base.codeName , base.code);
//					listvlues=listvlues+"'"+base.code;
//					SharedPreferencesUtils.addsharep(PlanActivity.this, "listvlues", listvlues);
//					
//				}
//				getsprinner();	
//				
//				
//			break;

		case 2:
			String jsonstr=(String) msg.obj;
			System.out.println(jsonstr);
			if(jsonstr.contains("success:true")){
		
		String id=SharedPreferencesUtils.querysharep2(PlanActivity.this, "userid");
		if(id.equals("1")){
			Intent i=new Intent(PlanActivity.this,MarketActivity.class);
			startActivity(i);
			finish();
		}else if(id.equals("2")){
			Intent i=new Intent(PlanActivity.this,MainActivity.class);
			startActivity(i);
			finish();
		}
			
		
			}
			
			break;
		}
		
	};
	
	
};
private Button bt;

	
private void setSpinner(Spinner sp, ArrayList<String> list) {

	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
	// 设置样式
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	sp.setAdapter(adapter);

}


@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	

	case R.id.action2_image:
		finish();
		break;
	}
}


}
