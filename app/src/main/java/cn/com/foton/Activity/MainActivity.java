package cn.com.foton.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import cn.com.foton.allot.AllotActivity;
import cn.com.foton.baiduMap.baiduMap;
import cn.com.foton.base.Main;
import cn.com.foton.base.gradeBase;
import cn.com.foton.base.gradeBase.msgs;
import cn.com.foton.base.userBase;
import cn.com.foton.base.weatherBase.result.data.realtime;
import cn.com.foton.data.App_data;
import cn.com.foton.data.App_url;
import cn.com.foton.data.DB_data;
import cn.com.foton.data.static_data;
import cn.com.foton.data.weatherData;
import cn.com.foton.overtime.OvertimeActivity;
import cn.com.foton.weather.weather;
import cn.jpush.android.api.JPushInterface;

/**
 * ï¿½ï¿½ï¿½Û¾ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
 *
 * @author Administrator
 *
 */
public class MainActivity extends Activity {
	Intent intent = new Intent();
	// viewpagerÍ¼Æ¬ï¿½ï¿½Ô´

	private ArrayList<View> lview;

	private SimpleAdapter sim_adapter;

	private weather w;

	private RequestBody body;

	private ArrayList<RequestBody> listbody;

	private RequestBody bodys;
	private ImageView imagweather;

	String codeo="";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		gson = new Gson();
		// ï¿½æ±¾ï¿½ï¿½ï¿½ï¿½

		user = UserUtils.getUserBase(this);
		baiduMap map=new baiduMap(h, this);
		//w = new weather(h,this);
//		   w.Getweather("ï¿½ï¿½ï¿½ï¿½");


		body = new FormEncodingBuilder().add("fcmReportForm.fcmUserId", user.getFcmUserId())
				.add("fcmReportForm.fcmPositionId", user.getFcmPositionId())
				.add("fcmReportForm.fcmCompanyCode", user.getFcmCompanyCode())
				.add("fcmReportForm.fcmDealerCode", user.getFcmDealerCode()).build();
		new myHttp2(body, App_url.URLmain, h, 2, MainActivity.this).start();

		init();
		InitView();
	}

	private void init() {
		// TODO Auto-generated method stub
		ImageView iv = (ImageView) findViewById(R.id.action2_image);
		week = (TextView) findViewById(R.id.CustomerSexwait);
		temperature = (TextView) findViewById(R.id.CustomerNamewait);
		System.out.println(JPushInterface.getConnectionState(this));

		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, SettingActivity.class);
				startActivity(i);

			}
		});

//		String code[] = { "1801", "1803", "1804", "1805","1802" };


		if(user.getFcmCompanyCode()=="2450"){
				codeo="1218";
		}else{
				codeo="1216";

		}

		String code[]={"1219", "1220", "1217","1102","1106",codeo};
		String companycode[]={ "9999", "9999", "9999",user.getFcmCompanyCode(),user.getFcmCompanyCode(),user.getFcmCompanyCode()};
		listbody = new ArrayList<>();
		for (int i = 0; i < code.length; i++) {
			// ä¸‹æ‹‰æ¡†æ•°æ?
			bodys = new FormEncodingBuilder()
					.add("fcmCode.codeType", code[i])
					.add("fcmCode.companyCode",companycode[i])
					.add("fcmCode.isAddress", "false")
					.build();
			listbody.add(bodys);
	}
		for (int i = 0; i < listbody.size(); i++) {
			myHttp2 http = new myHttp2(listbody.get(i), App_url.URL, h, 3 + i, MainActivity.this);
			http.start();
		}
		//ï¿½Â½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//		String a2[]={"1107","1102","8018","1115","1106"};
		String students[]=static_data.STUDENT;
//		for (int j = 0; j < a2.length; j++) {
//		DB_data.insterB(a2[j],students[j],user.getFcmCompanyCode());
//		
//	}

		//ÒâÏò³µÐÍ
		String code1=SharedPreferencesUtils.querysharep2(MainActivity.this,user.getFcmCompanyCode());
		DB_data.insterB(code1,"Stuent3",user.getFcmCompanyCode());






//		bodys = new FormEncodingBuilder()
//		        .add("fcmCode.codeType","1802")
//		        .add("fcmCode.isAddress","false")
//		        .add("fcmCode.companyCode", user.getFcmCompanyCode())
//		        .build();
//		myHttp2 http=new myHttp2(bodys,App_url.URL,h,7,MainActivity.this);
//		http.start();
	}

	private void InitView() {

		// ï¿½ï¿½ï¿½ï¿½Title
		TextView tv = (TextView) findViewById(R.id.action_text);
		ImageView actionimg = (ImageView) findViewById(R.id.action2_image);
		imagweather=(ImageView)findViewById(R.id.imagweather);
		tv.setText("Ê×Ò³");
		actionimg.setImageResource(R.drawable.caidan);



		ImageCycleView mImageCycleView = (ImageCycleView) findViewById(R.id.f1_viewapger);
		mImageCycleView.setCycleDelayed(2000);// ï¿½ï¿½ï¿½ï¿½ï¿½Ô¶ï¿½ï¿½Ö²ï¿½Ñ­ï¿½ï¿½Ê±ï¿½ï¿½
		List<ImageCycleView.ImageInfo> list = new ArrayList<ImageCycleView.ImageInfo>();

		// resÍ¼Æ¬ï¿½ï¿½Ô´
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu1, "", ""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu2, "", ""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu3, "", ""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu4, "", ""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu5, "", ""));
		list.add(new ImageCycleView.ImageInfo(R.drawable.zhu6, "", ""));



		mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
			@Override
			public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

				//æœ¬åœ°å›¾ç‰‡
				ImageView imageView = new ImageView(MainActivity.this);
				imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
				return imageView;

			}
		});
		gV = (GridView) findViewById(R.id.gridView1);

		gV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				switch (position) {
				case 0:
					intent.setClass(MainActivity.this, AllotActivity.class);
					intent.putExtra("MainActivity", "1");
					startActivity(intent);
					break;

				case 1:
					intent.setClass(MainActivity.this, Will_Activity.class);
					startActivity(intent);
					break;
				case 2:
					intent.setClass(MainActivity.this, OvertimeActivity.class);
					intent.putExtra("MainActivity", "1");
					startActivity(intent);
					break;
				case 3:

					intent.setClass(MainActivity.this, WaitActivity.class);
					intent.putExtra("MainActivity", "1");
					startActivity(intent);

					break;
				case 4:
					intent.setClass(MainActivity.this, AllClueActivity.class);
					startActivity(intent);
					break;
				case 5:
					//intent.setClass(MainActivity.this, DefeatActivity.class);
					intent.setClass(MainActivity.this, WaitActivity2.class);
					startActivity(intent);
					break;
				case 6:
					intent.setClass(MainActivity.this, The_reportActivity.class);
					startActivity(intent);
					break;
				case 7:
//					intent.setClass(MainActivity.this, AddClueActivity.class);
//					startActivity(intent);
					Toast.makeText(MainActivity.this,"¸Ã¹¦ÄÜÄ£¿éÔÝÍ£¿ª·Å",Toast.LENGTH_LONG).show();
					break;

				}

			}
		});

	}

	Handler h = new Handler() {
		private String listvlues;
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:
				realtime s = (realtime) msg.obj;
				System.out.println(s.city_name + "city_name");

				String st[]={"ÖÜÈÕ","ÐÇÆÚÒ»","ÐÇÆÚ¶þ","ÐÇÆÚÈý","ÐÇÆÚËÄ","ÐÇÆÚÎå","ÐÇÆÚÁù"};
				temperature.setText(s.weather.temperature+"¡æ"+"  |  "+s.weather.info);
				week.setText(s.date + "   " + st[s.week]);
				Map<String, Integer> imagedata = weatherData.getImage();

				imagweather.setImageResource(imagedata.get(s.weather.info));

				break;

			case 2:
				String msgs = (String) msg.obj;
				String jsonstring = jsonT.GetjsonAlls(msgs);
				Log.e("ï¿½ï¿½Ò³Í³ï¿½ï¿½",jsonstring);

				Main gb = gson.fromJson(jsonstring, Main.class);
				List<String> texts = new ArrayList<>();
				String texts1[] = new String[8];

				texts1[0] = gb.fcmDisNumber + "";
				texts1[1] = gb.fcmNeedTaskNumber + "";
				texts1[2] = gb.fcmFollowUpOTNumber + "";
				texts1[3] = gb.fcmFollowUpNumber + "";
				texts1[4] = gb.fcmAllBusinessNumber + "";
				texts1[5] = gb.fcmCloseLoopNumber + "";
				texts1[6] = "";
				texts1[7] = "";
				GridAdaptter ga = new GridAdaptter(MainActivity.this, App_data.geGridViewData(texts1));
				gV.setAdapter(ga);
				break;

			case 3:
				//¸ü¸ÄµÄ»ù´¡Êý¾Ý
				LogUtil.e(MainActivity.this,"»ù´¡Êý¾Ý¸üÐÂ"+msg);
				ArrayList<msgs> msg3 = getJSON(msg);
				for (int i = 0; i < msg3.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msg3.get(i);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MainActivity.this, "listvlues", listvlues);

				}
				listvlues = null;
				break;

			case 4:
				LogUtil.e(MainActivity.this,"»ù´¡Êý¾Ý¸üÐÂ2"+msg);
				ArrayList<msgs> msgs2 = getJSON(msg);
				for (int i = 0; i < msgs2.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs2.get(i);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MainActivity.this, "listvlues3", listvlues);

				}
				listvlues = null;
				break;
			case 5:
				LogUtil.e(MainActivity.this,"»ù´¡Êý¾Ý¸üÐÂ3"+msg);
				ArrayList<msgs> msgs3 = getJSON(msg);
				for (int i = 0; i < msgs3.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs3.get(i);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MainActivity.this, "listvlues4", listvlues);

				}
				listvlues = null;
				break;
			case 6:
				LogUtil.e(MainActivity.this,"»ù´¡Êý¾Ý¸üÐÂ4"+msg);
				ArrayList<msgs> msgs4 = getJSON(msg);
				for (int i = 0; i < msgs4.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msgs4.get(i);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MainActivity.this, "listvlues5", listvlues);

				}
				listvlues = null;
				break;
			case 7:
				LogUtil.e(MainActivity.this,"»ù´¡Êý¾Ý¸üÐÂ5"+msg);
				String json7 = (String) msg.obj;
				LogUtil.e(this,json7);
				String jsonString = jsonT.GetjsonAll(json7);
				Gson gson = new Gson();
				gradeBase bean7 = gson.fromJson(jsonString, gradeBase.class);
				ArrayList<msgs> msg2 = bean7.msg;
				for (int i = 0; i < msg2.size(); i++) {
					cn.com.foton.base.gradeBase.msgs base = msg2.get(i);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.code, base.codeName);
					SharedPreferencesUtils.addsharep(MainActivity.this, base.codeName, base.code);
					listvlues = listvlues + "'" + base.code;
					SharedPreferencesUtils.addsharep(MainActivity.this, "listvlues6", listvlues);
				}
				listvlues = null;
				break;

				 case 8:
					 LogUtil.e(MainActivity.this,"»ù´¡Êý¾Ý¸üÐÂ6"+msg);
					 String json8 = (String) msg.obj;
					 LogUtil.e(this,json8);
					 String jsonString8 = jsonT.GetjsonAll(json8);

					 Gson gson3 = new Gson();
					 gradeBase bean8 = gson3.fromJson(jsonString8, gradeBase.class);
					 ArrayList<msgs> msg8 = bean8.msg;
					 for (int i = 0; i < msg8.size(); i++) {
						 cn.com.foton.base.gradeBase.msgs base = msg8.get(i);
						 SharedPreferencesUtils.addsharep(MainActivity.this, base.code, base.codeName);
						 SharedPreferencesUtils.addsharep(MainActivity.this, base.codeName, base.code);
						 listvlues = listvlues + "'" + base.code;
						 SharedPreferencesUtils.addsharep(MainActivity.this, "listvlues7", listvlues);
					 }
					 listvlues = null;
				break;

			}

		};
	};
	public ArrayList<msgs> getJSON(Message msg) {
		String jsonstring=(String) msg.obj;
		LogUtil.e(this,jsonstring);
		String jsonString = jsonT.GetjsonAll(jsonstring);
			Gson gson=new Gson();
			gradeBase gb = gson.fromJson(jsonString, gradeBase.class);
			ArrayList<msgs> msgs = gb.msg;
		return msgs;
	};
	private TextView week;

	private TextView temperature;

	private GridView gV;

	long mExitTime = 0;

	private Gson gson;

	private userBase user;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if ((System.currentTimeMillis() - mExitTime) > 2000) {

				Toast.makeText(this, "ÔÙ°´Ò»´Î·µ»ØÍË³ö³ÌÐò", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();

			} else {
				finish();
				// JPushInterface.stopPush(this);

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
		new myHttp2(body, App_url.URLmain, h, 2, MainActivity.this).start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		JPushInterface.onPause(this);
	}

}
