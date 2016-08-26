package cn.com.foton.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.com.foton.R;
import cn.com.foton.Util.StringUtils;
import cn.com.foton.Wait.SimepFragment1;
import cn.com.foton.Wait.SimepFragment2;
import cn.com.foton.Wait.SimepFragment3;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.data.DB_data;

public class ParticularActivity extends Activity implements OnClickListener{

	private TextView fcmBusinessId,fcmCustomerName,fcmCustomerMobile,fcmChangeType,fcmIntentionSeries,fcmCustomerSaleManId,fcmCustomerCreateDate;
	private List<FcmCustomer> list;
	private TextView fcmCustomerSex;
	private TextView fcmProvinceCode;
	private TextView fcmAddress;
	private TextView fcmCustomerLevel;
	private TextView fcmInfoWay;
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private TextView fcmCustomerQQ;
	private TextView fcmCustomerWechat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frafgment_allot);
		
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("ÏßË÷ÏêÇé");
		bt1 = (Button) findViewById(R.id.boda);
		 bt2=(Button) findViewById(R.id.sms);
		 bt3=(Button) findViewById(R.id.button3);
		 bt1.setVisibility(View.GONE);
		 bt2.setVisibility(View.GONE);
		 bt3.setVisibility(View.GONE);
	
		Intent intent=getIntent();
		String StringE=intent.getStringExtra("position");
		String id=intent.getStringExtra("id");
		if(id.equals("1")){
			list = fragment1.getlist();
		}else if(id.equals("2")){
			list = fragment2.getlist();
		}else if(id.equals("3")){
			list = fragment3.getlist();
		}else if(id.equals("4")){
			list = fragment4.getlist();
		}else if(id.equals("5")){
			list = fragment5.getlist();
		}else if(id.equals("6")){
			list = fragment6.getlist();
		}else if(id.equals("7")){
			list = SimepFragment1.getlist();
		}
		else if(id.equals("8")){
			list = SimepFragment2.getlist();
		}
		else if(id.equals("9")){
			list = SimepFragment3.getlist();
		}
		
		int s=Integer.parseInt(StringE);


		final FcmCustomer base = list.get(s);
		

		
//		fcmBusinessId = (TextView) findViewById(R.id.fcmBusinessId);
//		fcmCustomerName = (TextView) findViewById(R.id.fcmCustomerName);
//		fcmCustomerMobile = (TextView) findViewById(R.id.fcmCustomerMobile);
//		fcmChangeType = (TextView) findViewById(R.id.fcmChangeType);
//		fcmCustomerCreateDate = (TextView) findViewById(R.id.fcmCustomerCreateDate);
//		fcmIntentionSeries = (TextView) findViewById(R.id.fcmIntentionSeries);
//		fcmCustomerSaleManId = (TextView) findViewById(R.id.fcmCustomerSaleManId);
		
		
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
		
		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.action2_image:
			finish();
			break;

		default:
			break;
		}
	}


}
