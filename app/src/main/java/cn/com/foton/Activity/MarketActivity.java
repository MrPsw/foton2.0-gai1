package cn.com.foton.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.foton.AddClue.AddClueActivity;
import cn.com.foton.Allclue.AllClueActivity;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.R;
import cn.com.foton.SettingActivity;
import cn.com.foton.The_report.The_reportActivity;
import cn.com.foton.Util.LogUtil;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.ImageCycleView;
import cn.com.foton.Wait.WaitActivity;
import cn.com.foton.Wait.WaitActivity2;
import cn.com.foton.Will.Will_Activity;
import cn.com.foton.adapter.GridAdaptter;
import cn.com.foton.baiduMap.baiduMap;
import cn.com.foton.base.Main;
import cn.com.foton.base.gradeBase;
import cn.com.foton.base.gradeBase.msgs;
import cn.com.foton.base.userBase;
import cn.com.foton.base.weatherBase.result.data.realtime;
import cn.com.foton.data.App_data;
import cn.com.foton.data.App_url;
import cn.com.foton.data.DB_data;
import cn.com.foton.data.weatherData;
import cn.com.foton.overtime.OvertimeActivity;
import cn.com.foton.weather.weather;
import cn.jpush.android.api.JPushInterface;

public class MarketActivity extends Activity {
	private ArrayList<View> lview;
	private SimpleAdapter sim_adapter;
	private weather w;
	private TextView week;
	private TextView temperature;
	private ArrayList<RequestBody> listbody;
	private RequestBody bodys;
	private ImageView imagweather;
	private String codeo="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market);
		   user = UserUtils.getUserBase(this);
		
		
		   baiduMap map=new baiduMap(h, this);	
//		 w = new weather(h,this);
//		   w.Getweather("北京");
		init();
		InitView();

		   body = new FormEncodingBuilder()
			        .add("fcmReportForm.fcmUserId", user.getFcmUserId())
			        .add("fcmReportForm.fcmPositionId", user.getFcmPositionId())
			        .add("fcmReportForm.fcmCompanyCode", user.getFcmCompanyCode())
			        .add("fcmReportForm.fcmDealerCode", user.getFcmDealerCode())
			        .build();
		   new myHttp2(body, App_url.URLmain, h, 2,MarketActivity.this).start();
		   
		

		   
		   
	}
	private void init() {
		// TODO Auto-generated method stub
		ImageView iv=(ImageView) findViewById(R.id.action2_image);
		week = (TextView) findViewById(R.id.CustomerSexwait);
		imagweather=(ImageView)findViewById(R.id.imagweather);
		temperature = (TextView) findViewById(R.id.CustomerNamewait);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MarketActivity.this, SettingActivity.class);
				startActivity(i);
				
			}
		});


		if(user.getFcmCompanyCode()=="2450"){
			codeo="1218";
		}else{
			codeo="1216";

		}

		String code[]={"1219", "1220", "1217","1102","1106",codeo};
		String companycode[]={ "9999", "9999", "9999",user.getFcmCompanyCode(),user.getFcmCompanyCode(),user.getFcmCompanyCode()};
		listbody = new ArrayList<>();
		for (int i = 0; i < code.length; i++) {
			// 涓嬫媺妗嗘暟鎹?
			bodys = new FormEncodingBuilder()
					.add("fcmCode.codeType", code[i])
					.add("fcmCode.companyCode",companycode[i])
					.add("fcmCode.isAddress", "false")
					.build();
			listbody.add(bodys);
		}
		for (int i = 0; i < listbody.size(); i++) {
			myHttp2 http = new myHttp2(listbody.get(i), App_url.URL, h, 3 + i, MarketActivity.this);
			http.start();
		}

		//意向车型
		String code1=SharedPreferencesUtils.querysharep2(MarketActivity.this,user.getFcmCompanyCode());
		DB_data.insterB(code1,"Stuent3",user.getFcmCompanyCode());
		 
	}

	private void InitView() {
		
		
		//设置Title
		TextView tv=(TextView) findViewById(R.id.action_text);
		ImageView actionimg=(ImageView) findViewById(R.id.action2_image);
		tv.setText("首页");
		actionimg.setImageResource(R.drawable.caidan);

		

		
		ImageCycleView mImageCycleView = (ImageCycleView) findViewById(R.id.f1_viewapger);
		mImageCycleView.setCycleDelayed(2000);//设置自动轮播循环时间
		List<ImageCycleView.ImageInfo> list=new ArrayList<ImageCycleView.ImageInfo>();

		//res图片资源
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu1,"",""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu2,"",""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu3,"",""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu4,"",""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu5,"",""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu6,"",""));


		mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
			@Override
			public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

				//本地图片
				ImageView imageView=new ImageView(MarketActivity.this);
				imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
				return imageView;





			}
		});

		gV = (GridView) findViewById(R.id.gridView1);
	

		gV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(MarketActivity.this,""+(position+1), 0).show();
				Intent in;
				switch (position) {
				case 0:
					in=new Intent(MarketActivity.this,Will_Activity.class);
					in.putExtra("MarketActivity", "2");
					startActivity(in);

				
					break;

				case 1:
					 in=new Intent(MarketActivity.this,OvertimeActivity.class);
					in.putExtra("MarketActivity", "2");
					startActivity(in);
					break;
					
				case 2:
					in=new Intent(MarketActivity.this,WaitActivity.class);
					in.putExtra("MarketActivity", "2");
					startActivity(in);
					break;
				case 3:
					in=new Intent(MarketActivity.this,AllClueActivity.class);
				
					startActivity(in);
					break;
				case 4:
					in=new Intent(MarketActivity.this,WaitActivity2.class);

					startActivity(in);

					break;
				case 5:
					in=new Intent(MarketActivity.this,The_reportActivity.class);

					startActivity(in);

					break;
					case 6:
						in=new Intent(MarketActivity.this,AddClueActivity.class);

						startActivity(in);
						break;
			
				}
				
			}
		});

	}
Handler h=new Handler(){
	
	private String listvlues;
	public void handleMessage(android.os.Message msg) {
		switch (msg.what) {
		case 2:
			String msgs=(String) msg.obj;
			String jsonstring = jsonT.GetjsonAlls(msgs);
			Gson gson=new Gson();
			Main gb = gson.fromJson(jsonstring, Main.class);
			List<String>  texts=new ArrayList<>();
			String texts1[]=new String[7];


			texts1[0]=gb.fcmNeedTaskNumber + "";
			texts1[1]=gb.fcmFollowUpOTNumber + "";
			texts1[2]=gb.fcmFollowUpNumber + "";
			texts1[3]=gb.fcmAllBusinessNumber + "";
			texts1[4]= gb.fcmCloseLoopNumber + "";
			texts1[5]="";
			texts1[6]="";
			GridAdaptter ga=new GridAdaptter(MarketActivity.this, App_data.geGridViewData2(texts1));
			gV.setAdapter(ga);
			break;

		case 1: 
			realtime s=	(realtime) msg.obj;
			System.out.println(s.city_name+"city_name");
			
			String st[]={"周日","星期一","星期二","星期三","星期四","星期五","星期六"};
			temperature.setText(s.weather.temperature+"℃"+"  |  "+s.weather.info);
			week.setText(s.date+"   "+st[s.week]);
			
			Map<String, Integer> imagedata = weatherData.getImage();
			
			imagweather.setImageResource(imagedata.get(s.weather.info));
			break;
			case 3:
				//更改的基础数据
				LogUtil.e(MarketActivity.this,"基础数据更新"+msg);
				ArrayList<msgs> msg3 = getJSON(msg);
				for (int i = 0; i < msg3.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msg3.get(i);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MarketActivity.this, "listvlues", listvlues);

				}
				listvlues = null;
				break;

			case 4:
				LogUtil.e(MarketActivity.this,"基础数据更新2"+msg);
				ArrayList<msgs> msgs2 = getJSON(msg);
				for (int i = 0; i < msgs2.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs2.get(i);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MarketActivity.this, "listvlues3", listvlues);

				}
				listvlues = null;
				break;
			case 5:
				LogUtil.e(MarketActivity.this,"基础数据更新3"+msg);
				ArrayList<msgs> msgs3 = getJSON(msg);
				for (int i = 0; i < msgs3.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs3.get(i);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MarketActivity.this, "listvlues4", listvlues);

				}
				listvlues = null;
				break;
			case 6:
				LogUtil.e(MarketActivity.this,"基础数据更新4"+msg);
				ArrayList<msgs> msgs4 = getJSON(msg);
				for (int i = 0; i < msgs4.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs4.get(i);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MarketActivity.this, "listvlues5", listvlues);

				}
				listvlues = null;
				break;
			case 7:
				LogUtil.e(MarketActivity.this,"基础数据更新5"+msg);
				String json7 = (String) msg.obj;
				LogUtil.e(this,json7);
				String jsonString = jsonT.GetjsonAll(json7);
				Gson gson7 = new Gson();
				gradeBase bean7 = gson7.fromJson(jsonString, gradeBase.class);
				ArrayList<msgs> msg2 = bean7.msg;
				for (int i = 0; i < msg2.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msg2.get(i);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MarketActivity.this, "listvlues6", listvlues);
				}
				listvlues = null;
				break;

			case 8:
				LogUtil.e(MarketActivity.this,"基础数据更新6"+msg);
				String json8 = (String) msg.obj;
				LogUtil.e(this,json8);
				String jsonString8 = jsonT.GetjsonAll(json8);

				Gson gson3 = new Gson();
				gradeBase bean8 = gson3.fromJson(jsonString8, gradeBase.class);
				ArrayList<msgs> msg8 = bean8.msg;
				for (int i = 0; i < msg8.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msg8.get(i);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MarketActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MarketActivity.this, "listvlues7", listvlues);
				}
				listvlues = null;
				break;
		}
		
	};
};

public ArrayList<msgs> getJSON(Message msg) {
	String jsonstring=(String) msg.obj;
	String jsonString = jsonT.GetjsonAll(jsonstring);
		Gson gson=new Gson();
		gradeBase gb = gson.fromJson(jsonString, gradeBase.class);
		ArrayList<msgs> msgs = gb.msg;
	return msgs;
};
private GridView gV;
long mExitTime = 0;
private RequestBody body;
private userBase user;
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
    if (keyCode == KeyEvent.KEYCODE_BACK) {
    
		if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

        } else {
                finish();
        }
        return true;
}

	return super.onKeyDown(keyCode, event);
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	System.out.println("-----onresume");
	JPushInterface.onResume(this);
	new myHttp2(body, App_url.URLmain, h, 2,MarketActivity.this).start();
}

@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	
	JPushInterface.onPause(this);
}
}
