package cn.com.foton.Will;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.List;

import cn.com.SMS.MSMutils;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.R;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.StringUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.ResultHistory;
import cn.com.foton.base.ResultHistory.msg;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.data.DB_data;
import cn.com.foton.data.static_data;

public class Particular_tab1Activity extends Activity implements OnClickListener{

	//private TextView fcmBusinessId,fcmCustomerName,fcmCustomerMobile,fcmChangeType,fcmModelSeries,fcmCustomerNote,fcmCustomerCreateDate;
	private List<GsonBase.Msg.FcmCustomer> list;
	private List<msg> list2=null;
	private List<msg> list3=null;
	private msg basss=null;
	String fcmModelSerie=null;

	private TextView fcmBusinessId,fcmCustomerName,fcmCustomerMobile,fcmChangeType,fcmIntentionSeries,fcmCustomerSaleManId,fcmCustomerCreateDate;
	//private List<FcmCustomer> list;
	private TextView fcmCustomerSex;
	private TextView fcmProvinceCode;
	private TextView fcmAddress;
	private TextView fcmCustomerLevel;
	private TextView fcmInfoWay;
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	private TextView fcmCustomerQQ;
	private TextView fcmCustomerWechat;
	Context thiscontext=Particular_tab1Activity.this;
	private static GsonBase.Msg.FcmCustomer base;
	Handler h=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:

				String jsonstring=(String) msg.obj;
				if (!jsonstring.contains("没有此计划")) {
					System.out.println("" + jsonstring);
					jsonstring = jsonT.Getjson(jsonstring);
					Gson g = new Gson();
					ResultHistory base = g.fromJson(jsonstring, ResultHistory.class);
					list2 = base.msg;
					list3 = new ArrayList<>();
					for (int i = 0; i < list2.size(); i++) {

						if (list2.get(i).fcmPlanIsover.equals("1")) {

							list3.add(list2.get(i));
						} else {
							basss = list2.get(i);
						}

					}
				}
				break;

			default:
				break;
			}
		}
	};
	private RequestBody body;
	private ExpandableListView lv;
	private msg baset;
	private static msg baset2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frafgment_allot);
		
		
	

		
		initView();
		initBody();
		new myHttp2(body, App_url.resultHistory, h, 1,Particular_tab1Activity.this).start();

		
	}

	private void initBody() {
		userBase user = UserUtils.getUserBase(this);
		// TODO Auto-generated method stub
		body = new FormEncodingBuilder()
		        .add("fcmVisit.fcmUserId", user.getFcmUserId())
		        .add("fcmVisit.fcmPositionId", user.getFcmPositionId())
		        .add("fcmVisit.fcmPositionId", user.getFcmCompanyCode())
		        .add("fcmVisit.fcmDealerCode", user.getFcmDealerCode())
		        .add("fcmVisit.fcmCustomerId",base.fcmCustomerId) 
		        .add("fcmVisit.fcmBusinessId", base.fcmBusinessId)
		        .build();
		 System.out.println("fcmVisit.fcmUserId"+user.getFcmUserId());
	}

	public void initView() {

		bt1 = (Button) findViewById(R.id.boda);
		bt2=(Button) findViewById(R.id.sms);
		bt3=(Button) findViewById(R.id.button3);
		bt4=(Button) findViewById(R.id.button4);
		bt4.setVisibility(View.VISIBLE);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);



		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("线索详情");
		
		Intent intent=getIntent();
		String StringE=intent.getStringExtra("position");
		String id=intent.getStringExtra("Activityid");
		if(id.equals("1")){
			list=null;
			list=Will_tab1_Activity.getlist();
		}else if(id.equals("2")){
			//list = fragment2.getlist();
			list=null;
			list=Wil_tab2_Activity.getlist();
		}
		
		int s=Integer.parseInt(StringE);
		

		base = list.get(s);



		fcmBusinessId = (TextView) findViewById(R.id.fcmBusinessId);
		fcmCustomerName = (TextView) findViewById(R.id.fcmCustomerName);
		fcmCustomerSex = (TextView) findViewById(R.id.fcmCustomerSex);
		fcmCustomerMobile = (TextView) findViewById(R.id.fcmCustomerMobile);
		fcmProvinceCode = (TextView) findViewById(R.id.fcmProvinceCode);
		fcmAddress = (TextView) findViewById(R.id.fcmAddress);
		fcmCustomerCreateDate = (TextView) findViewById(R.id.fcmCustomerCreateDate);
		fcmIntentionSeries = (TextView) findViewById(R.id.fcmModelSeries);
		fcmChangeType = (TextView) findViewById(R.id.fcmChangeType);
		fcmCustomerLevel = (TextView) findViewById(R.id.fcmCustomerLevel);

		fcmInfoWay = (TextView) findViewById(R.id.fcmInfoWay);
		fcmCustomerQQ = (TextView) findViewById(R.id.fcmCustomerQQ);
		fcmCustomerWechat = (TextView) findViewById(R.id.fcmCustomerWechat);

		LinearLayout ll=(LinearLayout) findViewById(R.id.View_gone);

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String fcmCustomerSexs=DB_data.getcodename("Stuent1","codeName","code",base.fcmCustomerSex);
				fcmCustomerSex.setText(fcmCustomerSexs+"");
				String fcmProvinceCodes =DB_data.getcodename("table2","name","code",base.fcmProvinceCode);
				String fcmCityCode=DB_data.getcodename("table3","name","code",base.fcmCityCode);
				String fcmTownCode=DB_data.getcodename("table4","name","code",base.fcmTownCode);
				fcmProvinceCode.setText(fcmProvinceCodes+""+fcmCityCode+fcmTownCode);
				String fcmCreateDate=StringUtils.getData(base.fcmCreateDate+"");
				fcmCustomerCreateDate.setText(fcmCreateDate+"");
				String fcmInfoWays =DB_data.getcodename("Stuent4","codeName","code",base.fcmInfoWay);
				fcmInfoWay.setText(fcmInfoWays+"");
				String fcmCustomerLevels=DB_data.getcodename("Stuent5","codeName","code",base.fcmCustomerLevel);
				fcmCustomerLevel.setText(fcmCustomerLevels+"");

				String fcmModelSerie="";
				if(base.fcmIntentionSeries.equals("")){

				}else{
					fcmModelSerie=DB_data.getcodename("Stuent3","codeName","code",base.fcmIntentionSeries);
				}

				fcmIntentionSeries.setText(fcmModelSerie+"");
				String fcmChangeTypes = DB_data.getcodename("Stuent2","codeName","code",base.fcmChangeType);
				fcmChangeType.setText(fcmChangeTypes+"");

			}
		});
		fcmBusinessId.setText(base.fcmBusinessId+"");
		fcmCustomerName.setText(base.fcmCustomerName+"");

		fcmCustomerMobile.setText(base.fcmCustomerMobile+"");
		fcmCustomerQQ.setText(base.fcmCustomerQQ+"");
		fcmCustomerWechat.setText(base.fcmCustomerWechat+"");


		fcmAddress.setText(base.fcmAddress+"");

		((TextView)findViewById(R.id.fcmLeadStatus)).setText(base.fcmLeadStatus);
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.action2_image:
			finish();
			break;

		case R.id.button3:

			Intent intent=new Intent(Particular_tab1Activity.this, Plan_Activity.class);
			intent.putExtra("fcmRevisitId",base.fcmRevisitId);
			startActivity(intent);

			break;
			
			
		case R.id.senSMS:
			String fcmDealerName = SharedPreferencesUtils.querysharep(Particular_tab1Activity.this,static_data.FCMDEALERNAME);
			MSMutils.sendSmsWithBody(this,base.fcmCustomerMobile, base.fcmCustomerName+"先生/女士，您好！感谢您关注"+fcmModelSerie+"（车型），欢迎您到店看车试驾!"+fcmDealerName+"将竭诚为您服务。电话：  ,地址:  。");
			//MSMutils.sendSmsWithBody(this,base.fcmCustomerMobile, "你好？..........");
			break;  
		case R.id.boda:
		      //用intent启动拨打电话  
			Intent intent2=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+base.fcmCustomerMobile));
			startActivity(intent2);
			break;
			case R.id.button4:
				//用intent启动拨打电话
				Intent intent4=new Intent(thiscontext,HistoryActivity.class);
				intent4.putExtra("fcmCustomerId",base.fcmCustomerId);
				intent4.putExtra("fcmBusinessId",base.fcmBusinessId);
				startActivity(intent4);
				break;
	
		}
	}
	public static GsonBase.Msg.FcmCustomer getBase(){
		return base;

	}
	public static msg getBase2(){
		return baset2;

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new myHttp2(body, App_url.resultHistory, h, 1,Particular_tab1Activity.this).start();
	}

}
