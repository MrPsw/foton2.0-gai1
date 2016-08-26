package cn.com.foton.allot;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.SMS.MSMutils;
import cn.com.foton.R;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.StringUtils;
import cn.com.foton.Wait.WaitActivity;
import cn.com.foton.Will.HistoryActivity;
import cn.com.foton.Will.Plan_Activity;
import cn.com.foton.base.GsonBase;
import cn.com.foton.data.DB_data;
import cn.com.foton.data.static_data;
import cn.com.foton.overtime.OvertimeActivity;

public class FgActivity extends Activity implements OnClickListener{

	private TextView fcmBusinessId;
	private TextView fcmCustomerName;
	private TextView fcmCustomerSex;
	private TextView fcmCustomerMobile;
	private TextView fcmProvinceCode;
	private TextView fcmAddress;
	private TextView fcmCustomerCreateDate;
	private TextView fcmIntentionSeries;
	private TextView fcmChangeType;
	private TextView fcmCustomerLevel;
	private TextView fcmInfoWay;
	private List<GsonBase.Msg.FcmCustomer> list;

	private Button bt1,bt2,bt3;
	private TextView fcmCustomerNote;
	private TextView fcmCustomerQQ;
	private TextView fcmCustomerWechat;
	private TextView fcmLeadStatus;
	String fcmModelSerie="";
	private Button bt4;
	Context context=FgActivity.this;
	private static GsonBase.Msg.FcmCustomer base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frafgment_allot);
		
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("线索详情");
		
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
		fcmCustomerNote = (TextView) findViewById(R.id.fcmCustomerNote);
		fcmLeadStatus = (TextView) findViewById(R.id.fcmLeadStatus);
		
		bt1 = (Button) findViewById(R.id.boda);
		 bt2=(Button) findViewById(R.id.sms);
		 bt3=(Button) findViewById(R.id.button3);
		bt4=(Button) findViewById(R.id.button4);
		bt4.setVisibility(View.VISIBLE);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);

		Intent intent=getIntent();
		String StringE=intent.getStringExtra("position");
		String id = intent.getStringExtra("Activityid");
		String mainid = SharedPreferencesUtils.querysharep2(FgActivity.this, "userid");
		
		if(id.equals("1")){
		list=AllotActivity.getlist();
		bt1.setVisibility(View.GONE);
		bt2.setVisibility(View.GONE);
		bt3.setVisibility(View.GONE);
			
		}else if(id.equals("2")){
		
			
		list = WaitActivity.getlist();
		
	
		}else if(id.equals("3")){
		
			
			
			list=OvertimeActivity.getlist();
		}
		
		int s=Integer.parseInt(StringE);
		base = list.get(s);
		
		
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
				
			
				if(base.fcmIntentionSeries.equals("")){
					
				}else{
				 fcmModelSerie=DB_data.getcodename("Stuent3","codeName","code",base.fcmIntentionSeries);
				}
				fcmLeadStatus.setText(base.fcmLeadStatus+"");


				fcmIntentionSeries.setText(fcmModelSerie+"");
				String fcmChangeTypes = DB_data.getcodename("Stuent2","codeName","code",base.fcmChangeType);
				fcmChangeType.setText(fcmChangeTypes+"");
				fcmCustomerNote.setText(base.fcmCustomerNote.toString());
			}
		});
		fcmBusinessId.setText(base.fcmBusinessId+"");
		fcmCustomerName.setText(base.fcmCustomerName+"");
	
		fcmCustomerMobile.setText(base.fcmCustomerMobile+"");
		fcmCustomerQQ.setText(base.fcmCustomerQQ+"");
		fcmCustomerWechat.setText(base.fcmCustomerWechat+"");
		
		fcmAddress.setText(base.fcmAddress+"");

		
		
	bt3.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent in=new Intent(FgActivity.this, PlanActivity.class);
			
			startActivity(in);
			
		}
	});
		
	
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.action2_image:
			finish();
			break;


			case R.id.button3:

				Intent intent=new Intent(context, Plan_Activity.class);
				intent.putExtra("fcmRevisitId",base.fcmRevisitId);
				startActivity(intent);

				break;


			case R.id.senSMS:
				String fcmDealerName = SharedPreferencesUtils.querysharep(context,static_data.FCMDEALERNAME);
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
				Intent intent4=new Intent(context,HistoryActivity.class);
				intent4.putExtra("fcmCustomerId",base.fcmCustomerId);
				intent4.putExtra("fcmBusinessId",base.fcmBusinessId);
				startActivity(intent4);
				break;
		}
	}
public static  GsonBase.Msg.FcmCustomer getBase(){
	return base;

}

	
}
