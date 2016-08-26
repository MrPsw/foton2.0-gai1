package cn.com.foton.overtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.Dalog_prompt;
import cn.com.foton.Dialog.Dalog_prompt.NegativeButtonOnclik;
import cn.com.foton.Dialog.Dalog_prompt.PositiveButtonOnclik;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.JPushBase.allotBase;
import cn.com.foton.R;
import cn.com.foton.Util.LogUtil;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.allotJPush;
import cn.com.foton.Util.myHttp;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.adapter.pouplistadapter;
import cn.com.foton.adapter.wait_List_adapter2;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.base.ManJsonBase;
import cn.com.foton.base.Xiaoshoubase;
import cn.com.foton.base.Xiaoshoubase.Msg;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;

/**
 * 超时未跟进
 * 
 * @author Administrator
 *
 */
public class OvertimeActivity extends Activity implements OnRefreshListener, OnClickListener {
	
	private wait_List_adapter2 adapter;
	private List<Msg> msgbase;
	private Msg basest;
//	private static final String URLS = "http://61.233.8.200/appCustomerManager/customerQueryFollowUp.html";
	
	private int page=2;
	private static List<FcmCustomer>  lists;
	Handler h = new Handler() {

	

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				String jsonstring = (String) msg.obj;
				LogUtil.e("ssd",jsonstring);
				String jsonString = jsonT.GetjsonAll(jsonstring);
				Gson g = new Gson();
				GsonBase base = g.fromJson(jsonString, GsonBase.class);
				lists = base.msg.fcmCustomer;
				adapter = new wait_List_adapter2(OvertimeActivity.this, lists, true,"3");
				lV.setAdapter(adapter);
				dialog.close();
				adapter.notifyDataSetChanged();
				page=2;
				// 千万别忘了告诉控件加载完毕了哦！
				mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				
				break;

			case 2:
				String jsonstring3 = (String) msg.obj;
				String jsonString3 = jsonT.Getjson(jsonstring3);
				Gson g3 = new Gson();
				Xiaoshoubase base3 = g3.fromJson(jsonString3, Xiaoshoubase.class);
				msgbase = base3.msg;
				pouplistadapter p = new pouplistadapter(OvertimeActivity.this, msgbase);
				pouplv.setAdapter(p);
				// 显示PopupWindow
				popupwindow.showAtLocation(lV, Gravity.CENTER, 0, 0);

				break;

			case 3:
				String jsonstr = (String) msg.obj;
				System.out.println(jsonstr);
				myHttp http = new myHttp(body, App_url.QueryNeedTask, h);
				http.start();

				adapter.notifyDataSetChanged();

				break;
				
			case 4:
				String jsonstring4=(String) msg.obj;
				String jsonString4 = jsonT.GetjsonAll(jsonstring4);
				if(jsonString4==null){
					// 千万别忘了告诉控件加载完毕了哦！
					mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
					return ;
				}else{
					page=page+1;
				}
					Gson g4=new Gson();
					GsonBase base4 = g4.fromJson(jsonString4, GsonBase.class);


					List<FcmCustomer> lists2 = base4.msg.fcmCustomer;
					for (int i = 0; i < lists2.size(); i++) {
						lists.add(lists2.get(i));
					}
					
					adapter.setccbSize(lists.size());
					adapter.notifyDataSetChanged();
					// 千万别忘了告诉控件加载完毕了哦！
					mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				break;
			}
		};
	};
	private RequestBody body;
	private ListView lV;
	private userBase user;
	private RequestBody bodys;
	private PopupWindow popupwindow;
	private RequestBody body3;
	private ListView pouplv;
	private Button bt;
	private IOSdialog dialog;
	private PullToRefreshLayout mSwipeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overtime);

		// 判断上一个actiivty进行控制
		Intent intent = getIntent();
		final String mainid = intent.getStringExtra("MainActivity");

		ImageView image = (ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV = (TextView) findViewById(R.id.action_text);
		TV.setText("超时未跟进线索");
		Button bt = (Button) findViewById(R.id.bt_fp);
		bt.setText("重新分配");
		String id = SharedPreferencesUtils.querysharep2(OvertimeActivity.this, "userid");
		if(id.equals("1")){
			bt.setVisibility(View.INVISIBLE);
		}
		
		mSwipeLayout = (PullToRefreshLayout)findViewById(R.id.id_swipe_ly);
		
		mSwipeLayout.setOnRefreshListener(new  cn.com.foton.View.PullToRefreshLayout.OnRefreshListener() {
			
			@Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				//dialog=new IOSdialog(WaitActivity.this, "获取数据");
				
				myHttp http=new myHttp(body, App_url.QueryNeedTask, h);
				http.start();
			}
			
			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				
			new myHttp2(GetBady(page), App_url.QueryNeedTask, h, 4, OvertimeActivity.this).start();
			}
		});

		user = UserUtils.getUserBase(this);
		body=GetBady(1);

		body3 = new FormEncodingBuilder().add("fcmCustomer.fcmUserId", user.getFcmUserId())
				.add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
				.add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
				.add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode()).build();

		dialog=new IOSdialog(OvertimeActivity.this, "获取数据");
		myHttp http = new myHttp(body, App_url.QueryNeedTask, h);
		http.start();

		lV = (ListView) findViewById(R.id.listView1);

		bt.setOnClickListener(this);

		lV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				// TODO Auto-generated method stub
//				LinearLayout li=(LinearLayout) view.findViewById(R.id.item_onclik);
//				li.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						Intent i = new Intent(OvertimeActivity.this, FgActivity.class);
//						i.putExtra("position", position + "");
//						i.putExtra("MainActivity",mainid );
//						i.putExtra("Activityid", "3");
//						startActivity(i);
//					}
//				});
				
				
			}
		});
	}



	private RequestBody GetBady(int  number) {
		
		RequestBody bodyt;
		return bodyt = new FormEncodingBuilder().add("fcmCustomer.fcmUserId", user.getFcmUserId())
				.add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
				.add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
				.add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
				.add("fcmCustomer.fcmPageNumber", "10")
				.add("fcmCustomer.fcmPageSize", ""+number)
				.add("fcmCustomer.fcmIsTimeOver", "true")
				.build();
	}

	public static List<FcmCustomer> getlist() {

		return lists;

	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_fp:
	
			boolean b=false;
			boolean[] cb = adapter.getcheck();
			for (int i = 0; i < cb.length; i++) {
				if(cb[i]){
					b=true;
				}
			}
		
			if(!b){
				Toast.makeText(OvertimeActivity.this, "请先选择需要分配的客户", 0).show();
				return;
			}

			if (popupwindow != null && popupwindow.isShowing()) {
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

	public void fenpei() {
		boolean[] cb = adapter.getcheck();
		// String ck="";
		if(cb.length<1){
			
			if (popupwindow != null && popupwindow.isShowing()) {
				backgroundAlpha(1f);
				popupwindow.dismiss();
				return;
			}
			
		}else{
		
		
		for (int i = 0; i < cb.length; i++) {
			if (cb[i] == true) {
				// ck=ck+""+i;

				FcmCustomer base = lists.get(i);
				String fcmCustomerId = base.fcmCustomerId;
				String fcmBusinessId = base.fcmBusinessId;
				String UserId = user.getFcmUserId();
				String PositionId = user.getFcmPositionId();
				String CompanyCode = user.getFcmCompanyCode();
				String DealerCode = user.getFcmDealerCode();
				bodys = new FormEncodingBuilder().add("fcmCustomer.fcmUserId", UserId)
						.add("fcmCustomer.fcmPositionId", PositionId).add("fcmCustomer.fcmCompanyCode", CompanyCode)
						.add("fcmCustomer.fcmDealerCode", DealerCode)
						.add("fcmCustomer.fcmCustomerId", fcmCustomerId + "")
						.add("fcmCustomer.fcmBusinessId", fcmBusinessId + "")
						.add("fcmCustomer.fcmToUserId", basest.fcmUserId).build();

				myHttp2 http = new myHttp2(bodys, App_url.ALLCLUE_URL3, h, 3,OvertimeActivity.this);
				http.start();
				
				allotBase allotbase = new allotBase();
				allotbase.CustomerId=fcmCustomerId;
				allotbase.BusinessId=fcmBusinessId;
				allotbase.FromUserId=UserId;
				allotbase.ToUserId=basest.fcmUserId;
				allotbase.CustomerName=base.fcmCustomerName;
				allotbase.Phone=base.fcmCustomerMobile;
				allotbase.CarType=base.fcmIntentionSeries;
				allotbase.BuyTime=base.fcmChangeType;
				allotbase.FailReason="";
				allotJPush.allothttp(OvertimeActivity.this, allotbase,h);
			}
		  }
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		dialog=new IOSdialog(OvertimeActivity.this, "获取数据");
		myHttp http = new myHttp(body, App_url.QueryNeedTask, h);
		http.start();

	}

	public void initmPopupWindowView() {

		// // 获取自定义布局文件pop.xml的视图
		View customView = getLayoutInflater().inflate(R.layout.popview_ite, null, false);
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupwindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		// 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
		// popupwindow.setAnimationStyle(R.style.AnimationFade);
		popupwindow.setContentView(customView);
		bt = (Button) customView.findViewById(R.id.quxiao);
		pouplv = (ListView) customView.findViewById(R.id.listView_popview);
		myHttp2 http = new myHttp2(body3,App_url.URL3, h, 2,OvertimeActivity.this);
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

		pouplv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				basest = msgbase.get(position);
				System.out.println(basest.fcmUserName + "选中了" + basest.fcmUserId + basest.fcmPositionId);
				new Dalog_prompt(OvertimeActivity.this, "是否确定分配给该顾问")
				.setMessage(""+basest.fcmRealName)
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
					
						Toast.makeText(OvertimeActivity.this, "取消", 0).show();
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
				
				popupwindow.setFocusable(false);
				backgroundAlpha(1f);
				popupwindow.dismiss();
				return true;
			}
		});

		pouplv.setOnKeyListener(new OnKeyListener() {

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
	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myHttp http = new myHttp(body, App_url.QueryNeedTask, h);
		http.start();
	
	}
}
