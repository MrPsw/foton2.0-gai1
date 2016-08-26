package cn.com.foton.Will;


/**
 * 今日待办任务
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.R;
import cn.com.foton.Util.LogUtil;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.adapter.wait_List_adapter3;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
public class Will_tab1_Activity extends Activity implements OnRefreshListener{

	private wait_List_adapter3 adapter;
	private PullToRefreshLayout mSwipeLayout;
	private int  page=2;
	private static List<FcmCustomer> list=null;
	Handler h = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				dialog.close();
				String jsonstring=(String) msg.obj;
				LogUtil.e(this,jsonstring);
				String jsonString = jsonT.GetjsonAll(jsonstring);
				if(jsonString==null){
					return;
				}
					Gson g=new Gson();
				GsonBase base = g.fromJson(jsonString, GsonBase.class);
					 	list= base.msg.fcmCustomer;
					 	adapter = new wait_List_adapter3(Will_tab1_Activity.this,list,false);
					 	lv.setAdapter(adapter);
						adapter.notifyDataSetChanged();
						page=2;
						// 千万别忘了告诉控件刷新完毕了哦！
						mSwipeLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
				
				break;

			case 4:
				String jsonstring4=(String) msg.obj;
				String jsonString4 = jsonT.GetjsonAll(jsonstring4);
				if(jsonString4==null){
					return ;
				}else{
					page=page+1;
				}
				System.out.println(jsonString4);
				Gson g4=new Gson();
				GsonBase base4 = g4.fromJson(jsonString4, GsonBase.class);
				List<GsonBase.Msg.FcmCustomer> lists2 = base4.msg.fcmCustomer;
				 
					for (int i = 0; i < lists2.size(); i++) {
						list.add(lists2.get(i));
					}
					
					adapter.notifyDataSetChanged();
						// 千万别忘了告诉控件加载完毕了哦！
					mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				break;
			}
		};
	};
	private ListView lv;
	private RequestBody body;
	private IOSdialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_will_tab1_);
		lv = init();
		
		
		body=GetBody(1);
		dialog=new IOSdialog(Will_tab1_Activity.this, "获取数据");
			//获取到今日待办的数据
			new myHttp2(body,App_url.QueryNeedTask, h, 1,Will_tab1_Activity.this).start();
			
			
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					Intent i=new Intent(Will_tab1_Activity.this,Particular_tab1Activity.class);
					i.putExtra("position", position+"");
					i.putExtra("Activityid", "1");
					startActivity(i);
				}
			});
		
	}



	private RequestBody GetBody(int page) {
		userBase user = UserUtils.getUserBase(this);
		RequestBody bodyt = new FormEncodingBuilder()
				
		        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
		        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
		        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
		        .add("fcmCustomer.fcmCustomerIsToday","true") 
		        .add("fcmCustomer.fcmPageNumber", "10")
		        .add("fcmCustomer.fcmPageSize", page+"")
			    .build();
		System.out.println("fcmCustomer.fcmUserId="+user.getFcmUserId());
		System.out.println("fcmCustomer.fcmPositionId="+user.getFcmPositionId());
		System.out.println("fcmCustomer.fcmCompanyCode="+user.getFcmCompanyCode());
		System.out.println("fcmCustomer.fcmDealerCode="+user.getFcmDealerCode());
		System.out.println("fcmCustomer.fcmCustomerIsToday="+"true");
		System.out.println("fcmCustomer.fcmPageNumber="+"10");
		System.out.println("fcmCustomer.fcmPageSize="+ page+"");
		System.out.println();
		return bodyt;
	}



	public ListView init() {
		ListView lv = (ListView) findViewById(R.id.list_tab1);

		mSwipeLayout = (PullToRefreshLayout) findViewById(R.id.id_swipe_ly);

		mSwipeLayout.setOnRefreshListener(new  cn.com.foton.View.PullToRefreshLayout.OnRefreshListener() {
			


			@Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				//dialog=new IOSdialog(WaitActivity.this, "获取数据");
				
				new myHttp2(body,App_url.QueryNeedTask, h, 1,Will_tab1_Activity.this).start();
			}
			
			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				
				new myHttp2(GetBody(page),App_url.QueryNeedTask, h, 4,Will_tab1_Activity.this).start();
			}
		});
		
		return lv;
	}



	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		dialog=new IOSdialog(Will_tab1_Activity.this, "获取数据");
		//获取到今日待办的数据
		new myHttp2(body,App_url.QueryNeedTask, h, 1,Will_tab1_Activity.this).start();
	}
	
	public static List<GsonBase.Msg.FcmCustomer> getlist(){
		
		
		return list;
		
	};
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		new myHttp2(GetBody(1),App_url.QueryNeedTask, h, 1,Will_tab1_Activity.this).start();
		super.onResume();
	}
}
