package cn.com.foton.Will;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;

import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.JPushBase.allotBase;
import cn.com.foton.R;
import cn.com.foton.Util.FailtJPush2;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.TiemUtils;
import cn.com.foton.Util.ToastUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.data.DB_data;
import cn.com.foton.tiem.TimeSelector;

public class Plan_Activity extends Activity implements OnClickListener {
	private static final String TAG = "Plan_Activity";

	private ArrayList<String> list1, list2, list3, list4, list6, list7, list8;

	private Spinner sp1;

	private LinearLayout li3;
	private LinearLayout li4;
	private Button sp6;
	private Button sp7;
	private Button sp8;
	private Spinner sp2;
	private Spinner sp3;
	public String sp1text, sp2text, sp3text, sp4text, sp6text="", sp7text="", sp8text="";
	private userBase user;
	private LinearLayout li1;
	private LinearLayout li2;



	/**
	 * 下一步计划时间按钮
	 */
	private Button b ,b2;
	/**
	 * 提交按钮
	 */
	private Button submit;
	private GsonBase.Msg.FcmCustomer base;
	/**
	 * 线索状态数组
	 */
 String XS[]={"已成交","已废弃","已跟进","已战败","已战败","已进店","已跟进","已废弃"};
	int endArr1[]={2,7,15,30,60};
	int endArr2[]={3,3,3,3,3};
	String starttime1=TiemUtils.getTiem();

	String endtime1=TiemUtils.getNextDay(endArr1[0]);
  	int  postion;


	private IOSdialog dialog;


	private LinearLayout li12;

	private String fcmPlanId;
	private String fcmCustomerId;
	private String fcmBusinessId;
	private String positionId;
	private String userId;
	private String companyCode;
	private String dealerCode;
	private String fcmRevisitId;
	/**
	 * 意向购车时间
	 */
	private LinearLayout yxgccx;
	/**
	 * 意向车系
	 */
	private LinearLayout yxgcTiem;

	private LinearLayout jindian;
	private TextView jindianyix;

	int index=0;
	private Button b3;
	Context context=Plan_Activity.this;
	private TextView yuanyin;

	/**
	 * 1正常2成交3战败4废弃
	 */
	int caseid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_);

		fcmRevisitId=getIntent().getStringExtra("fcmRevisitId");


		user = UserUtils.getUserBase(this);
		base = Particular_tab1Activity.getBase();
		


		fcmCustomerId = base.fcmCustomerId;
		fcmBusinessId = base.fcmBusinessId;
		userId = user.getFcmUserId();
		positionId = user.getFcmPositionId();
		companyCode = user.getFcmCompanyCode();
		dealerCode = user.getFcmDealerCode();



		((TextView)findViewById(R.id.Tiem)).setText(base.fcmPlanDateBegin+"至"+base.fcmPlanDateEnd);
		((TextView)findViewById(R.id.fangshi)).setText(base.fcmRevisitType);


		b = (Button) findViewById(R.id.tiem);
		b2 = (Button) findViewById(R.id.endtiem);
		b3 = (Button) findViewById(R.id.yxtiem);
		b.setText(TiemUtils.getTiemYMD(TiemUtils.getTiem()));
		b2.setText(TiemUtils.getTiemYMD(TiemUtils.getTiem()));
		inactionbar();
		init();

	}

	public RequestBody getBoay(String Note, String level, String model,String CustomerNote,String FollowupNote) {

		

		RequestBody bodt = new FormEncodingBuilder().add("fcmVisit.fcmUserId", userId)
				.add("fcmVisit.fcmPositionId", positionId).add("fcmVisit.fcmCompanyCode", companyCode)
				.add("fcmVisit.fcmDealerCode", dealerCode).add("fcmVisit.fcmCustomerId", fcmCustomerId + "")
				.add("fcmVisit.fcmBusinessId", fcmBusinessId + "").add("fcmVisit.fcmPlanId",fcmRevisitId)
				.add("fcmVisit.fcmResult", sp1text).add("fcmVisit.fcmLeadLevel", level + "")
				.add("fcmVisit.fcmAttentionModel", model + "")
				.add("fcmVisit.fcmFollowupNote",FollowupNote+"")
				.add("fcmCustomerNote", CustomerNote)
				.add("fcmVisit.fcmNote", Note)
				.build();

		
		return bodt;
	}
	public RequestBody getBoay2(String Note) {

		//备注
		String fcmFollowupNote = et2.getText().toString();
		RequestBody bodt = new FormEncodingBuilder()
				.add("fcmVisit.fcmUserId", userId)
				.add("fcmVisit.fcmCompanyCode", companyCode)
				.add("fcmVisit.fcmDealerCode", dealerCode)
				.add("fcmVisit.fcmInfoWay", base.fcmInfoWay)
				.add("fcmVisit.fcmRevisitCount", base.fcmRevisitCount)
				.add("fcmVisit.fcmCustomerId", fcmCustomerId + "")
				.add("fcmVisit.fcmBusinessId", fcmBusinessId + "")
				.add("fcmVisit.fcmResult", sp1text)
				.add("fcmVisit.fcmCustomerName",base.fcmCustomerName)
				.add("fcmVisit.fcmMobile", base.fcmCustomerMobile)
				.add("fcmVisit.fcmTel", "")
				.add("fcmVisit.fcmRevisitId",base.fcmRevisitId)
				.add("fcmVisit.fcmIntentionSeries",sp3text)
				.add("fcmVisit.fcmResultNote",fcmFollowupNote)
				.add("fcmVisit.fcmRevisitGroup",base.fcmRevisitGroup)
				.add("fcmVisit.fcmResultBuyReason",sp7text)
				.build();
		
		return bodt;
	}

	public RequestBody getBoay3() {
			//备注
		String fcmFollowupNote = et2.getText().toString();
		Log.e(TAG, fcmRevisitId);

		String tiemstrat = TiemUtils.getTiemYMD(b.getText().toString());
		String endstrat = TiemUtils.getTiemYMD(b2.getText().toString());
		RequestBody bodt = new FormEncodingBuilder()
				.add("fcmVisit.fcmUserId", userId)
				.add("fcmVisit.fcmCompanyCode", companyCode)
				.add("fcmVisit.fcmDealerCode", dealerCode)
				.add("fcmVisit.fcmInfoWay", base.fcmInfoWay)
				.add("fcmVisit.fcmRevisitCount", base.fcmRevisitCount)
				.add("fcmVisit.fcmCustomerId", fcmCustomerId + "")
				.add("fcmVisit.fcmBusinessId", fcmBusinessId + "")
				.add("fcmVisit.fcmResult", sp1text)
				.add("fcmVisit.fcmNextPlanDateBegin",tiemstrat)
				.add("fcmVisit.fcmNextPlanDateEnd",endstrat)
				.add("fcmVisit.fcmCustomerName",base.fcmCustomerName)
				.add("fcmVisit.fcmMobile", base.fcmCustomerMobile)
				.add("fcmVisit.fcmTel", "")
				.add("fcmVisit.fcmRevisitId",base.fcmRevisitId)
				.add("fcmVisit.fcmIntentionSeries",sp3text)
				.add("fcmVisit.fcmResultNote",fcmFollowupNote)
				.add("fcmVisit.fcmResultSureBuy",sp2text)
				.add("fcmVisit.fcmRevisitGroup",base.fcmRevisitGroup)

				.build();

		
		return bodt;
	}



	private void inactionbar() {
		// TODO Auto-generated method stub
		ImageView image = (ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV = (TextView) findViewById(R.id.action_text);
		TV.setText("计划及进展");
		
	
	}

	private void init() {
		submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(this);

		sp1 = (Spinner) findViewById(R.id.spinner1);
		sp2 = (Spinner) findViewById(R.id.spinner2);
		sp3 = (Spinner)	findViewById(R.id.spinner3);



		sp6 = (Button) findViewById(R.id.spinner6);
		sp7 = (Button) findViewById(R.id.spinner7);
		sp8 = (Button) findViewById(R.id.spinner8);

		et2 = (EditText) findViewById(R.id.editText2);
		yuanyin=(TextView)findViewById(R.id.yuanyin);


		sp6.setOnClickListener(this);
		sp7.setOnClickListener(this);
		sp8.setOnClickListener(this);



		yxgccx= (LinearLayout) findViewById(R.id.yxgccx);
		yxgcTiem = (LinearLayout) findViewById(R.id.yxgcTiem);
		jindian = (LinearLayout) findViewById(R.id.jindian);
		jindianyix = (TextView) findViewById(R.id.jindianyix);

		li1 = (LinearLayout) findViewById(R.id.zhengc);
		li12 = (LinearLayout) findViewById(R.id.zhengc2);
		li2 = (LinearLayout) findViewById(R.id.yijiaoche);
		li3 = (LinearLayout) findViewById(R.id.zhanbai);
		li4 = (LinearLayout) findViewById(R.id.wuxiao);


		list1 = getsprinner(sp1, "listvlues7");

		Getstudent2("Stuent2", sp2);
		Getstudent3("Stuent3", sp3);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(position==2||position==6||position==5){
					caseid=1;
					li2.setVisibility(View.GONE);
					li3.setVisibility(View.GONE);
					li4.setVisibility(View.GONE);
					yxgcTiem.setVisibility(View.VISIBLE);
					li12.setVisibility(View.VISIBLE);
					jindian.setVisibility(View.GONE);
					if(position==6||position==5){

						if(position==5){
							jindian.setVisibility(View.VISIBLE);
							jindianyix.setText("实际进店时间：");
						}else{
							jindian.setVisibility(View.VISIBLE);
							jindianyix.setText("意向进店时间：");
						}

					}
				}else if(position==0||position==1||position==3||position==7||position==4){

					yxgcTiem.setVisibility(View.GONE);
					li12.setVisibility(View.GONE);
					li2.setVisibility(View.GONE);
					li3.setVisibility(View.GONE);
					li4.setVisibility(View.GONE);
					if(position==0){
						li2.setVisibility(View.VISIBLE);
						caseid=2;
					}else if(position==3||position==4){
					li3.setVisibility(View.VISIBLE);
						caseid=3;
					}else if(position==7){
						caseid=4;
						li4.setVisibility(View.VISIBLE);
					}
				}else{
					li4.setVisibility(View.GONE);
					li2.setVisibility(View.GONE);
					li12.setVisibility(View.VISIBLE);
					li3.setVisibility(View.GONE);
				}

				 // 线索状态
				((TextView)findViewById(R.id.xszt)).setText(XS[position]);
				//结果类型
				String s = list1.get(position);
				sp1text = SharedPreferencesUtils.querysharep(Plan_Activity.this, s);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});



		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				sp2text = code2.get(position) + "";
				System.out.println(sp2text + "_______意向购车时间sp2______被选中");
				postion=position;
						starttime1=TiemUtils.getTiem();
						endtime1=TiemUtils.getNextDay(endArr1[position]);

				//TiemUtils.getNextDay()
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		sp3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				sp3text = code3.get(position) + "";
				System.out.println(sp3text + "______意向车型sp3______被选中");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});



		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TimeSelector timeSelector = new TimeSelector(Plan_Activity.this, new TimeSelector.ResultHandler() {
					@Override
					public void handle(String time) {
						b.setText(time);

					}
				}, starttime1,endtime1);

				timeSelector.show();
			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				if(b.getText().toString().contains(":")){
				TimeSelector timeSelector = new TimeSelector(Plan_Activity.this, new TimeSelector.ResultHandler() {
					@Override
					public void handle(String time) {
						b2.setText(time);

					}
				},b.getText().toString(),TiemUtils.addDate(b.getText().toString(),endArr2[postion]-1));

				timeSelector.show();

			}else {

				ToastUtils.showToast(context,"请先选择开始时间");

			}
			}
		});
		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String jindian	=jindianyix.getText().toString();

				String startT=TiemUtils.getTiem();
				String endT="2019-7-8 10:20";
				if(jindian.equals("实际进店时间：")){
					startT="2010-7-8 10:20";
					endT=TiemUtils.getTiem();
				}

					TimeSelector timeSelector = new TimeSelector(Plan_Activity.this, new TimeSelector.ResultHandler() {
						@Override
						public void handle(String time) {
							b3.setText(time);

						}
					},startT,endT);

					timeSelector.show();


			}
		});

	}

	public ArrayList<String> getsprinner(Spinner sp, String listvlues) {
		list1 = new ArrayList<>();
		String listvlue = SharedPreferencesUtils.querysharep(this, listvlues);
		String[] li = listvlue.split("'");
		for (int i = 1; i < li.length; i++) {
			String vlue = SharedPreferencesUtils.querysharep(this, li[i]);
			list1.add(vlue);
		}

		setSpinner(sp, list1);

		return list1;
	}

	private void setSpinner(Spinner sp, ArrayList<String> list) {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		// 设置样式
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp.setAdapter(adapter);
		String fcmChangeTypes=DB_data.getcodename("Stuent2","codeName","code",base.fcmChangeType);
		String fcmModelSerie=DB_data.getcodename("Stuent3","codeName","code",base.fcmIntentionSeries);
		if(sp==sp2){

		
		System.out.println(fcmChangeTypes);
		index=list.indexOf(fcmChangeTypes);
		sp.setSelection(index, true);
		//int index=ArrayListUtils.getIndex(list, s);
		System.out.println(index+"下标");
			if(index!=0){
				endtime1=TiemUtils.getNextDay(endArr1[index]);
			}
			}
	
		if(sp==sp3){
		
	
		
			if(fcmModelSerie!=null){
				int index=list.indexOf(fcmModelSerie);
				sp.setSelection(index, true);
	
			}
	
		}
		

	}

	boolean isd = false;

	Handler H = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:



				break;
			case 2:
				String jsonstring2 = (String) msg.obj;
				System.out.println(jsonstring2);

				dialog.close();
				if (jsonstring2.contains("success:true")) {
					isd = true;
					finish();
				}

				break;

			case 3:
				String jsonstring3 = (String) msg.obj;
				System.out.println(jsonstring3);

				dialog.close();
				if (jsonstring3.contains("success:true")) {
					Intent i=new Intent(Plan_Activity.this,Will_Activity.class);
					startActivity(i);
					finish();
				}

				break;

			}

		};
	};


	private ArrayList<String> code2;
	private ArrayList<String> Codename2;
	private ArrayList<String> Codename3;
	private ArrayList<String> code3;

	private EditText et2;

	private TextView tv;

	private void Getstudent2(String table, Spinner sp) {
		Cursor c = DB_data.getstudnt(table);

		Codename2 = new ArrayList<>();
		code2 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			Codename2.add(f2);
			code2.add(f1);
		}
		c.close();
		setSpinner(sp, Codename2);
	}

	private void Getstudent3(String table, Spinner sp) {
		Cursor c = DB_data.getstudnt(table);

		Codename3 = new ArrayList<>();
		code3 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			Codename3.add(f2);
			code3.add(f1);
		}
		c.close();
		setSpinner(sp, Codename3);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.action2_image:
			finish();
			break;
		case R.id.submit:

		
			if (caseid==1) {
				
				dialog=new IOSdialog(Plan_Activity.this, "正在提交");



				if(sp2text==""||sp2text==null){
					if(base.fcmChangeType==""||base.fcmChangeType==null){
						sp2text=code2.get(0);
					}else{
						sp2text=base.fcmChangeType;		
					}
				}
				if(sp3text==""||sp3text==null){
					if(base.fcmIntentionSeries==""||base.fcmIntentionSeries==null){
						sp3text=code3.get(0);
					}else{
						sp3text=base.fcmIntentionSeries;		
					}
				}
				
				RequestBody body = getBoay3();
				 new myHttp2(body, App_url.URLSP1, H, 2, Plan_Activity.this).start();

			} else if (caseid==2) {
				dialog=new IOSdialog(Plan_Activity.this, "正在提交");
					if(sp7text==""||sp7text==null){
					Toast.makeText(Plan_Activity.this, "请选择选择原因", Toast.LENGTH_LONG).show();
					dialog.close();
					}else{
					RequestBody body = getBoay2(sp7text);
					new myHttp2(body, App_url.URLSP1, H, 3, Plan_Activity.this).start();
					}

			} else if (caseid==3) {
					dialog=new IOSdialog(Plan_Activity.this, "正在提交");
					if(sp7text==""||sp7text==null){
					Toast.makeText(Plan_Activity.this, "请选择选择原因", Toast.LENGTH_LONG).show();
					dialog.close();
					}else{
					RequestBody body = getBoay2(sp7text);
					new myHttp2(body, App_url.URLSP1, H, 3, Plan_Activity.this).start();

				
				String fcmCustomerId = base.fcmCustomerId;
				String fcmBusinessId = base.fcmBusinessId;
				String UserId = user.getFcmUserId();
				String PositionId = user.getFcmPositionId();
				String CompanyCode = user.getFcmCompanyCode();
				String DealerCode = user.getFcmDealerCode();

				

				
				allotBase allotbase = new allotBase();
				allotbase.CustomerId=fcmCustomerId;
				allotbase.BusinessId=fcmBusinessId;
				allotbase.FromUserId=UserId;
				allotbase.ToUserId=base.fcmUpUserId;
				allotbase.CustomerName=base.fcmCustomerName;
				allotbase.Phone=base.fcmCustomerMobile;
				allotbase.CarType=base.fcmIntentionSeries;
				allotbase.BuyTime=base.fcmChangeType;
				allotbase.FailReason="";
				FailtJPush2.allothttp(Plan_Activity.this, allotbase,h);
				}
			} else if (caseid==4) {
//				Log.e(TAG, "sdsdsdsdsdsds");
//				dialog=new IOSdialog(Plan_Activity.this, "获取数据");
//
//
//				System.out.println("失效显示");
//				if((sp8text==""||sp8text==null)){
//				Toast.makeText(Plan_Activity.this, "请选择选择原因或输入原因",Toast.LENGTH_LONG).show();
//				dialog.close();
//				}else{
////				RequestBody body = getBoay2(sp8text,et3.getText().toString());
////				myHttp2 http = new myHttp2(body, App_url.URLSP1, H, 3, Plan_Activity.this);
////				http.start();
//				}
			}
			break;
			
		case R.id.spinner6:
			String listvlue1 = SharedPreferencesUtils.querysharep(this, "listvlues");
			String[] li = listvlue1.split("'");
			String SA1[]=new String[li.length-1];
			for (int i = 1; i < li.length; i++) {
				String vlue = SharedPreferencesUtils.querysharep(this, li[i]);
				SA1[i-1]=vlue;
			}
		System.out.println(SA1.length);
			showDialog5(SA1,6);
			break;
		case R.id.spinner7:
			String listvlue2 = SharedPreferencesUtils.querysharep(this, "listvlues3");
			String[] li2 = listvlue2.split("'");
			String SA2[]=new String[li2.length-1];
			for (int i = 1; i < li2.length; i++) {
				String vlue = SharedPreferencesUtils.querysharep(this, li2[i]);
				SA2[i-1]=vlue;
			}
			showDialog5(SA2,7);
			break;
		case R.id.spinner8:
			String listvlue3 = SharedPreferencesUtils.querysharep(this, "listvlues4");
			String[] li3 = listvlue3.split("'");
			String SA3[]=new String[li3.length-1];
			for (int i = 1; i < li3.length; i++) {
				String vlue = SharedPreferencesUtils.querysharep(this,li3[i]);
				SA3[i-1]=vlue;
			}
			showDialog5(SA3,8);
			break;

		}
	}
	 public void showDialog5(final String[] multiChoiceItems,final int t){   
	        //定义复选框选项   

	      //复选框默认值：false=未选;true=选中 ,各自对应items[i] 
	        final boolean[] defaultSelectedStatus = new boolean[multiChoiceItems.length];
	        for (int i = 0; i < defaultSelectedStatus.length; i++) {
	        	defaultSelectedStatus[i]=false;
			}
	          
	        final StringBuilder sb = new StringBuilder(); 
	        final StringBuilder sb2 = new StringBuilder(); 
	        //创建对话框   
	        new AlertDialog.Builder(Plan_Activity.this)   
	        .setTitle("选择原因")//设置对话框标题   
	        .setMultiChoiceItems(multiChoiceItems, defaultSelectedStatus, new OnMultiChoiceClickListener(){   
	            @Override  
	            public void onClick(DialogInterface dialog, int which,   
	                    boolean isChecked) {   
	            //来回重复选择取消，得相应去改变item对应的bool值，点击确定时，根据这个bool[],得到选择的内容  
	            defaultSelectedStatus[which] = isChecked; 
	            }   
	        })  //设置对话框[肯定]按钮   
	        .setPositiveButton("确定",new DialogInterface.OnClickListener() { 
	@Override
	public void onClick(DialogInterface dialog, int which) { 
	for(int i=0;i<defaultSelectedStatus.length;i++) { 
	if(defaultSelectedStatus[i]) {
	
	sb.append(multiChoiceItems[i]+","); 
	sb2.append(multiChoiceItems[i]+"\n");
	} 
	} 
	switch (t) {
	case 6:
		sp6text=sb.toString();
		String a[]=sp6text.split(",");
		String s="";
		for (int j = 0; j < a.length; j++) {
			if(j<a.length-1){
				s=s+SharedPreferencesUtils.querysharep(Plan_Activity.this,a[j])+",";	
			}else{
				s=s+SharedPreferencesUtils.querysharep(Plan_Activity.this,a[j]);	
			}
			
		}
		sp6text=s;
	
		System.out.println(sp6text);
		break;

	case 7:
		sp7text=sb.toString();
		String a2[]=sp7text.split(",");
		String s2="";
		for (int j = 0; j < a2.length; j++) {
			
			if(j<a2.length-1){
				s2=s2+SharedPreferencesUtils.querysharep(Plan_Activity.this,a2[j])+",";
			
			}else{
				s2=s2+SharedPreferencesUtils.querysharep(Plan_Activity.this,a2[j]);
			}
			
		}
		sp7text=s2;

		System.out.println(sp7text);
		break;
	case 8:
		sp8text=sb.toString();
		String a3[]=sp8text.split(",");
		String s3="";
		for (int j = 0; j < a3.length; j++) {
			
			if(j<a3.length-1){
				s3=s3+SharedPreferencesUtils.querysharep(Plan_Activity.this,a3[j])+",";	
			}else{
				s3=s3+SharedPreferencesUtils.querysharep(Plan_Activity.this,a3[j]);	
			}
		
		}
		sp8text=s3;
		break;
	}
	// TODO Auto-generated method stub 
	yuanyin.setText(sb2.toString());


	} 
	}).setNegativeButton("取消", null)//设置对话框[否定]按钮   
	        .show();   
	    }   
	 
	 Handler h=new Handler(){
		 public void handleMessage(android.os.Message msg) {

		 };
	 };


}
