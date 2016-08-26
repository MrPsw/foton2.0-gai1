package cn.com.foton.allot;

import java.util.List;


import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewDebug.FlagToString;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.com.foton.R;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.Dalog_prompt;
import cn.com.foton.Dialog.Dalog_prompt.NegativeButtonOnclik;
import cn.com.foton.Dialog.Dalog_prompt.PositiveButtonOnclik;
import cn.com.foton.JPushBase.allotBase;
import cn.com.foton.Dialog.IOSdialog;

import cn.com.foton.R.id;
import cn.com.foton.R.layout;
import cn.com.foton.R.menu;
import cn.com.foton.Util.LogUtil;
import cn.com.foton.Util.Toast_Utils2;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.allotJPush;
import cn.com.foton.Util.myHttp;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.View.RefreshLayout;
import cn.com.foton.View.RefreshLayout.OnLoadListener;
import cn.com.foton.Wait.WaitActivity;
import cn.com.foton.Will.Will_tab1_Activity;
import cn.com.foton.adapter.List_adapter;
import cn.com.foton.adapter.allot_List_adapter;
import cn.com.foton.adapter.pouplistadapter;
import cn.com.foton.adapter.wait_List_adapter2;

import cn.com.foton.base.GsonBase;
import cn.com.foton.base.ManJsonBase;
import cn.com.foton.base.Xiaoshoubase;
import cn.com.foton.base.Xiaoshoubase.Msg;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;

import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.overtime.OvertimeActivity;
import cn.com.foton.data.App_url;
/**
 * 未分配线索
 * @author Administrator
 *
 */
public class AllotActivity extends FragmentActivity implements OnClickListener{
	
	String TAG="AllotActivity";
	private PullToRefreshLayout mSwipeLayout;
	private wait_List_adapter2 adapter;
	private List<Msg> msgbase;
	private static List<FcmCustomer> lists=null;
	private Msg basest;
	int page=2;
	
	
	Handler h = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:

				String jsonstring=(String) msg.obj;
				Log.e("handleMessage: ",jsonstring);
				
				String jsonString = jsonT.GetjsonAll(jsonstring);
				
				if(jsonString==null){
					return;
				}
					Gson g=new Gson();
					GsonBase base = g.fromJson(jsonString, GsonBase.class);
					lists = base.msg.fcmCustomer;
					adapter = new wait_List_adapter2(AllotActivity.this,lists,false,"1");
					lV.setAdapter(adapter);
					page=2;

					dialog.close();
					adapter.notifyDataSetChanged();
					mSwipeLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
					break;
			case 2:
				String jsonstring3=(String) msg.obj;
			
				String jsonString3= jsonT.Getjson(jsonstring3);
					Gson g3=new Gson();
					Xiaoshoubase base3 = g3.fromJson(jsonString3, Xiaoshoubase.class);
					msgbase = base3.msg;
					 pouplistadapter p=new pouplistadapter(AllotActivity.this,msgbase);
				     pouplv.setAdapter(p);
				     //显示PopupWindow  
//				      popupwindow.showAtLocation(lV, Gravity.BOTTOM, 0,0);
				     popupwindow.showAtLocation(lV, Gravity.CENTER, 0,0);
				
				break;
				
			case 3:
				String jsonstr=(String) msg.obj;
				System.out.println(jsonstr);
				myHttp http=new myHttp(body, App_url.ALLCLUE_URL1, h);
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
					
				}
				System.out.println(jsonString4);
					Gson g4=new Gson();
					GsonBase base4 = g4.fromJson(jsonString4, GsonBase.class);
					
		
					List<FcmCustomer> lists2 = base4.msg.fcmCustomer;
					if(lists.size()>0){
						page=page+1;
					}
					for (int i = 0; i < lists2.size(); i++) {
						lists.add(lists2.get(i));
					}
					System.out.println(lists.size());
					adapter.setccbSize(lists.size());
					adapter.notifyDataSetChanged();
						// 千万别忘了告诉控件加载完毕了哦！
					mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				break;
				
		
				
			}
		};
	};
	private userBase user;
	private RequestBody body;
	private ListView lV;
	private RequestBody body3;

	private PopupWindow popupwindow;
	private ListView pouplv;
	private RequestBody bodys;
	private Button bt;
	private IOSdialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allot);
		
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("未分配线索");
		
		lV = (ListView) findViewById(R.id.listView1);
		Button bt=(Button) findViewById(R.id.bt_fp);
	
		
					mSwipeLayout = (PullToRefreshLayout)findViewById(R.id.id_swipe_ly);

					
					mSwipeLayout.setOnRefreshListener(new  cn.com.foton.View.PullToRefreshLayout.OnRefreshListener() {
						
						@Override
						public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
							// TODO Auto-generated method stub
							//dialog=new IOSdialog(WaitActivity.this, "获取数据");
							
							myHttp http=new myHttp(body, App_url.ALLCLUE_URL1, h);
							http.start();
						}
						
						@Override
						public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
							// TODO Auto-generated method stub
							
						new myHttp2(GetBODY(page), App_url.ALLCLUE_URL1, h, 4, AllotActivity.this).start();
						}
					});
				
					
					
				
		
//		bt.setVisibility(View.GONE);
		bt.setOnClickListener(this);
		
		body=GetBODY(1);
		
		
		body3 = new FormEncodingBuilder()
		        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
		        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
		        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
		        .build();	
		
		dialog=new IOSdialog(AllotActivity.this, "获取数据");
		myHttp http=new myHttp(body, App_url.ALLCLUE_URL1, h);
		http.start();
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
//						Intent i=new Intent(AllotActivity.this,FgActivity.class);
//						i.putExtra("position", position+"");
//						i.putExtra("Activityid", "1");
//						startActivity(i);
//						
//						
//					}
//				});
				
			}
		});
	}

	private RequestBody GetBODY(int number) {
		user = UserUtils.getUserBase(this);
		
		RequestBody  bodyt = new FormEncodingBuilder()
		        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
		        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
		        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
		        .add("fcmCustomer.fcmPageNumber", "10")
		        .add("fcmCustomer.fcmPageSize", number+"")
			       
		        .build();
		
		
		
		return bodyt;
	}

//	@Override
//	public void onRefresh() {
//		// TODO Auto-generated method stub
//		dialog=new IOSdialog(AllotActivity.this, "获取数据");
//		myHttp http=new myHttp(body, App_url.ALLCLUE_URL1, h);
//		http.start();
//	}

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
				Toast.makeText(AllotActivity.this, "请先选择需要分配的客户", 0).show();
				return;
			}
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
	
	public void fenpei() {
		boolean[] cb = adapter.getcheck();
		
		for (int i = 0; i < cb.length; i++) {
			if(cb[i]==true){


				FcmCustomer base = lists.get(i);
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
				        .add("fcmCustomer.fcmToUserId", basest.fcmUserId) 
				        .build();
				
				
				myHttp2 http=new myHttp2(bodys, App_url.ALLCLUE_URL3, h, 3,AllotActivity.this);
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
				allotJPush.allothttp(AllotActivity.this, allotbase,h);
			
		
				
				
			}
			
			}
		}
	 public void initmPopupWindowView() {  
   	  
	        // // 获取自定义布局文件pop.xml的视图  
	        View customView = getLayoutInflater().inflate(R.layout.popview_ite,  
	                null, false);  
	        //customView.setBackgroundResource(R.drawable.poup_bg);
	  
	        // 创建PopupWindow实例,200,150分别是宽度和高度  
	        popupwindow = new PopupWindow(customView,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,false);  
	       // popupwindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.poup_bg)); 
	        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]  
	        //popupwindow.setAnimationStyle(R.style.AnimationFade); 
	        popupwindow.setContentView(customView);  
	        bt = (Button) customView.findViewById(R.id.quxiao);
	        pouplv = (ListView) customView.findViewById(R.id.listView_popview);
	        myHttp2 http=new myHttp2(body3, App_url.URL3, h, 2,AllotActivity.this);
	        http.start();
	   
	        popupwindow.setFocusable(true);  
	        // 设置popWindow的显示和消失动画
	        //popupwindow.setAnimationStyle(R.style.mypopwindow_anim_style);
	  
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
				
				basest = msgbase.get(position); 
				System.out.println(basest.fcmUserName+"选中了"+basest.fcmUserId+basest.fcmPositionId);
				
				
//				new AlertDialog.Builder(AllotActivity.this).setTitle("是否确定选择该顾问").setMessage(""+basest.fcmRealName)
//				.setPositiveButton("确定",new  android.content.DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						 fenpei();
//					}
//				}).setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						
//					}
//				}).show();
				new Dalog_prompt(AllotActivity.this, "是否确定分配给该顾问")
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
						Toast.makeText(AllotActivity.this, "取消", 0).show();
					
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
	 public static List<FcmCustomer> getlist(){
			
			
			return lists;
			
		};
		 public void backgroundAlpha(float bgAlpha)  
		    {  
//		        WindowManager.LayoutParams lp = getWindow().getAttributes();  
//		        lp.alpha = bgAlpha; //0.0-1.0  
//		                getWindow().setAttributes(lp);  
		                
		    } 
		 
		 @Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			myHttp http=new myHttp(body, App_url.ALLCLUE_URL1, h);
			http.start();
		}
}
