package cn.com.foton.Defeat;


import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.com.foton.LoginActivity;
import cn.com.foton.R;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.Dalog_prompt;
import cn.com.foton.Dialog.Dalog_prompt.NegativeButtonOnclik;
import cn.com.foton.Dialog.Dalog_prompt.PositiveButtonOnclik;
import cn.com.foton.JPushBase.allotBase;
import cn.com.foton.R.id;
import cn.com.foton.R.layout;
import cn.com.foton.R.menu;
import cn.com.foton.Util.JPushHttp;
import cn.com.foton.Util.MyversionUtils;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.StringUtils;
import cn.com.foton.Util.ToastUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.allotJPush;
import cn.com.foton.Util.myHttp;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.adapter.Defeat_List_adapter4;
import cn.com.foton.adapter.fragment_adapter;
import cn.com.foton.adapter.pouplistadapter;
import cn.com.foton.allot.AllotActivity;

import cn.com.foton.base.Fail.MsgM;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.base.Fail;
import cn.com.foton.base.Xiaoshoubase;
import cn.com.foton.base.Xiaoshoubase.Msg;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_data;
import cn.com.foton.data.App_url;
import cn.com.foton.data.DB_data;
import cn.com.foton.data.static_data;
import cn.com.foton.overtime.OvertimeActivity;

public class FollowupActivity extends Activity implements OnClickListener{

	private Button bt1;
	private Button bt2;
	private TextView fcmBusinessId;
	private TextView fcmCustomerName;
	private TextView fcmCustomerMobile;
	private TextView fcmChangeType;
	private TextView fcmCustomerCreateDate;
	private TextView fcmModelSeries;
	private TextView fcmCustomerSaleManId;
	private LinearLayout lI;
	private Button fp;
	private RequestBody body;
	private RequestBody body3;
	private List<MsgM> msgbase;
	private MsgM basest;
	private MsgM base;
	private userBase user;
	private LinearLayout li2;
	private TextView fcmNote;
	private TextView fcmCustomerLevel;
	private TextView fcmInfoWay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_followup);
		li2 = (LinearLayout) findViewById(R.id.LinearLayout1);
		
		initactionbar();
		
		
		int item=1;
		bt1 = (Button) findViewById(R.id.b1_foll);
		bt2 = (Button) findViewById(R.id.b2_foll);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		
	
	}

	private void initactionbar() {
		Intent intent=getIntent();
		String StringE=intent.getStringExtra("position");
		int s=Integer.parseInt(StringE);
		List<MsgM> list = DefeatActivity.getlist();

		base = list.get(s);
		
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("战败审核");
		
	
		user = UserUtils.getUserBase(this);
		body = new FormEncodingBuilder()
		        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
		        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
		        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
		        .add("fcmCustomer.fcmCustomerId", base.fcmCustomerId)
		        .add("fcmCustomer.fcmBusinessId", base.fcmBusinessId)
		        .add("fcmCustomer.fcmDefeatId",base.fcmDefeatId)
		        .build();
		
		body3 = new FormEncodingBuilder()
		        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
		        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
		        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
		        .build();	
		
	
		
		lI = (LinearLayout) findViewById(R.id.liebiao);
		fcmBusinessId = (TextView) findViewById(R.id.fcmBusinessId);
		fcmCustomerName = (TextView) findViewById(R.id.fcmCustomerName);
		fcmCustomerMobile = (TextView) findViewById(R.id.fcmCustomerMobile);
		fcmChangeType = (TextView) findViewById(R.id.fcmChangeType);
		fcmCustomerCreateDate = (TextView) findViewById(R.id.fcmCustomerCreateDate);
		fcmModelSeries = (TextView) findViewById(R.id.fcmModelSeries);
		fcmCustomerLevel = (TextView) findViewById(R.id.fcmCustomerLevel);
		fcmInfoWay = (TextView) findViewById(R.id.fcmInfoWay);
		fcmCustomerSaleManId = (TextView) findViewById(R.id.fcmCustomerSaleManId);
		fcmNote = (TextView) findViewById(R.id.fcmNote);
		
		
		
		fcmBusinessId.setText(base.fcmBusinessId.toString());
		fcmCustomerName.setText(base.fcmCustomerName.toString());
		fcmCustomerMobile.setText(base.fcmCustomerMobile.toString());
		String fcmChangeTypes=DB_data.getcodename("Stuent2","codeName","code",base.fcmChangeType);
		System.out.println(fcmChangeTypes+"");
		fcmChangeType.setText(fcmChangeTypes+"");
		String fcmCreateDate=StringUtils.getData(base.fcmCustomerCreateDate+"");
		
		String fcmInfoWays = DB_data.getcodename("Stuent4", "codeName", "code", base.fcmInfoWay);
		fcmInfoWay.setText(fcmInfoWays!=""?fcmInfoWays:"");
		
		String fcmCustomerLevels = DB_data.getcodename("Stuent5", "codeName", "code", base.fcmCustomerLevel);
		fcmCustomerLevel.setText(fcmCustomerLevels!=""?fcmCustomerLevels:"");
		
		fcmCustomerCreateDate.setText(fcmCreateDate);
		
		String fcmModelSerie="";
		if(base.fcmModelSeries.equals("")){
			
		}else{
		 fcmModelSerie=DB_data.getcodename("Stuent3","codeName","code",base.fcmModelSeries);
		}
		fcmModelSeries.setText(fcmModelSerie+"");
		fcmCustomerSaleManId.setText(base.fcmCustomerSaleManName+"");
		
		String t=base.fcmDefeatCause;
		String cause="";
		String cause2="";
		String listvlue2 = SharedPreferencesUtils.querysharep(this, "listvlues4");
		String[] li2 = listvlue2.split("'");
		String []ts=t.split(",");
		
		
		for (int i = 0; i < ts.length; i++) {
			if(listvlue2.indexOf(ts[i])==-1){
				cause2=ts[i];
			}
			for (int j = 0; j < li2.length; j++) {
				
				if(ts[i].contains(li2[j])){
				String ca= SharedPreferencesUtils.querysharep(this, li2[j]);
					if(cause==""){
						cause=cause+""+ca;
					}else{
						cause=cause+","+ca;
					}
					
					}
			}
			
			
		}
		//String ca= SharedPreferencesUtils.querysharep(this, cause2);
		fcmNote.setText(cause+cause2);
	}
	private myHttp2 http;
	private PopupWindow popupwindow;
	private Button bt;
	private ListView pouplv;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b1_foll:
			setimagetext(bt1);
			setnoimagetext(bt2);
			http = new myHttp2(body, App_url.Follow, h, 1,FollowupActivity.this);
			http.start(); 
		
			break;
		case R.id.b2_foll:
			setimagetext(bt2);
			setnoimagetext(bt1);
			 if (popupwindow != null&&popupwindow.isShowing()) {  
				 backgroundAlpha(1f);
	                popupwindow.dismiss();  
	                return;  
	            } else {  
	            	backgroundAlpha(0.5f);
	                initmPopupWindowView();  
	            	
	                
	            } 
	
			break;
		case R.id.action2_image:
			finish();
			
			break;
	
		}
		
		
	}


	private void setimagetext(Button bt) {
		// TODO Auto-generated method stub
		bt.setBackgroundColor(this.getResources().getColor(R.color.dianjile));
		bt.setTextColor(this.getResources().getColor(android.R.color.white));
	}

	private void setnoimagetext(Button bt) {
		// TODO Auto-generated method stub
		bt.setBackgroundColor(this.getResources().getColor(R.color.weidianji));
		bt.setTextColor(this.getResources().getColor(android.R.color.black));
	}

	private Msg basest2;
	private List<Msg> msgbase2;
	Handler h=new Handler(){


		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				String jsonstr=(String) msg.obj;
				System.out.println(jsonstr);
				if(jsonstr.contains("success:true")){
					finish();
				}
				break;

			case 2:
				String jsonstring3=(String) msg.obj;
				
				String jsonString3= jsonT.Getjson(jsonstring3);
					Gson g3=new Gson();
					Xiaoshoubase base3 = g3.fromJson(jsonString3, Xiaoshoubase.class);
					msgbase2 = base3.msg;
					 pouplistadapter p=new pouplistadapter(FollowupActivity.this,msgbase2);
				     pouplv.setAdapter(p);
				     //显示PopupWindow  
			      popupwindow.showAtLocation(li2, Gravity.BOTTOM, 0,0);
//				     popupwindow.showAtLocation(li2, Gravity.CENTER, 0,0);
				break;
			case 3:
				String jsonstr2=(String) msg.obj;
				System.out.println(jsonstr2);
				if(jsonstr2.contains("分配成功")){
					allotJPush.allothttp(FollowupActivity.this, allotbase,h);
					
					finish();
				}else{
					
				}
				
				break;	
			case 4:
			String jso=(String) msg.obj;
			Toast.makeText(FollowupActivity.this, jso,0).show();
				break;
				
			}
		}
	};
	private RequestBody bodys;
	private allotBase allotbase;

	
	public void fenpei() {
				String fcmCustomerId=base.fcmCustomerId;
				String fcmBusinessId=base.fcmBusinessId;
				String UserId = user.getFcmUserId();
				String PositionId = user.getFcmPositionId();
				String CompanyCode=user.getFcmCompanyCode();
				String DealerCode=user.getFcmDealerCode();
				bodys = new FormEncodingBuilder()
				        .add("fcmCustomer.fcmUserId", UserId)
				        .add("fcmCustomer.fcmPositionId", PositionId)
				        .add("fcmCustomer.fcmCompanyCode", CompanyCode)
				        .add("fcmCustomer.fcmDealerCode", DealerCode)
				        .add("fcmCustomer.fcmCustomerId",fcmCustomerId+"")
				        .add("fcmCustomer.fcmBusinessId",fcmBusinessId+"")
				        .add("fcmCustomer.fcmToUserId", basest2.fcmUserId) 
				     
				        .add("fcmCustomer.fcmDefeatId",base.fcmDefeatId)
				        .build();
				allotbase = new allotBase();
				allotbase.CustomerId=fcmCustomerId;
				allotbase.BusinessId=fcmBusinessId;
				allotbase.FromUserId=UserId;
				allotbase.ToUserId=basest2.fcmUserId;
				allotbase.CustomerName=base.fcmCustomerName;
				allotbase.Phone=base.fcmCustomerMobile;
				allotbase.CarType=base.fcmModelSeries;
				allotbase.BuyTime=base.fcmChangeType;
				allotbase.FailReason=base.fcmDefeatCause;
		
				
				
				
			new myHttp2(bodys,App_url.Follow, h, 3,FollowupActivity.this).start();
		

		}
	 public void initmPopupWindowView() {  
	   	  
	        // // 获取自定义布局文件pop.xml的视图  
	        View customView = getLayoutInflater().inflate(R.layout.popview_ite,  
	                null, false);  
	        // 创建PopupWindow实例,200,150分别是宽度和高度  
	        popupwindow = new PopupWindow(customView,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);  
	        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]  
	        //popupwindow.setAnimationStyle(R.style.AnimationFade); 
	        popupwindow.setContentView(customView);  
	        bt = (Button) customView.findViewById(R.id.quxiao);
	        pouplv = (ListView) customView.findViewById(R.id.listView_popview);
	        myHttp2 http=new myHttp2(body3, App_url.URL3, h, 2,FollowupActivity.this);
	        http.start();
	     
	      popupwindow.setFocusable(true);  
	      if (popupwindow != null && popupwindow.isShowing()) {  
	    	  backgroundAlpha(1f);
	          popupwindow.dismiss();  
	          popupwindow = null;  
	      }  
	      bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 backgroundAlpha(1f);
					popupwindow.dismiss();  
			        popupwindow = null;  
				}
			});
	      popupwindow.setWidth(LayoutParams.FILL_PARENT); 
//	      //显示PopupWindow  
//	      popupwindow.showAsDropDown(customView);
	    
	      pouplv.setOnItemClickListener(new OnItemClickListener() {

		

		



			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				basest2 = msgbase2.get(position);
				System.out.println(basest2.fcmUserName+"选中了"+basest2.fcmUserId+basest2.fcmPositionId);
				new Dalog_prompt(FollowupActivity.this, "是否确定分配给该顾问")
				.setMessage(""+basest2.fcmRealName)
				.setNegativeButton("确定", new NegativeButtonOnclik() {
					
					@Override
					public void cancel() {
						// TODO Auto-generated method stub
						fenpei();
						
					}
				}).setPositiveButton("取消", new PositiveButtonOnclik() {
					
					@Override
					public void confirm() {
						// TODO Auto-generated method stub
						Toast.makeText(FollowupActivity.this, "取消", 0).show();
					
					}
				});
				 
				 
				 backgroundAlpha(1f);
				popupwindow.dismiss(); 

			}
		});
	      popupwindow.getContentView().setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				 backgroundAlpha(1f);
				popupwindow.setFocusable(false);  
				popupwindow.dismiss(); 
				return true;
			}
		});
	      
	      pouplv.setOnKeyListener(new OnKeyListener(){  
	    	  
	          @Override  
	          public boolean onKey(View v, int keyCode, KeyEvent event) {  
	              // TODO Auto-generated method stub  
	        	  backgroundAlpha(1f);
	        	  popupwindow.dismiss();  
	              return false;  
	          }  
	            
	      });   
	  
	    }  
	 public void backgroundAlpha(float bgAlpha)  
	    {  
//	        WindowManager.LayoutParams lp = getWindow().getAttributes();  
//	        lp.alpha = bgAlpha; //0.0-1.0  
//	                getWindow().setAttributes(lp);  
	    } 

}
