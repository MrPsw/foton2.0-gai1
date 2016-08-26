package cn.com.foton.jpush;


import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.JPushBase.MsgListBanben;
import cn.com.foton.JPushBase.MsgListBanben.Data2;
import cn.com.foton.JPushBase.MsgListBanben.Data2.Data;
import cn.com.foton.R.layout;
import cn.com.foton.Util.JPushHttp;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.Wait.WaitActivity;
import cn.com.foton.base.userBase;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.data.App_url;

public class MsmListActivity extends Activity {
	int page=2;

	private userBase user;
	  boolean b=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msm_list);
		 user= UserUtils.getUserBase(this);
		 
		// 设置Title
			TextView tv = (TextView) findViewById(R.id.action_text);
			ImageView actionimg = (ImageView) findViewById(R.id.action2_image);
		
			
			
			
			tv.setText("消息通知");
			actionimg.setImageResource(R.drawable.back);
			actionimg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		 
		 
		lv = (ListView) findViewById(R.id.listView1);
		
		mSwipeLayout = (PullToRefreshLayout)findViewById(R.id.id_swipe_ly);
		
		mSwipeLayout.setOnRefreshListener(new  cn.com.foton.View.PullToRefreshLayout.OnRefreshListener() {
			
			@Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				//dialog=new IOSdialog(WaitActivity.this, "获取数据");
				json=http(1);
				if(json!=null){
				 new JPushHttp(json,App_url.JPush_smsList, h, 1,MsmListActivity.this).start();
				}
			}
			
			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				json=http(page);
				if(json!=null){
				new JPushHttp(json, App_url.JPush_smsList, h, 2, MsmListActivity.this).start();
				}
			}
		});
		
		
		json=http(1);
		if(json!=null){
			 new JPushHttp(json, App_url.JPush_smsList, h, 1, this).start();
		}
		
	}
	private String http(int index) {
	
		try {
			JSONObject jsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("UserId",user.getFcmUserId());
            data.put("PageIndex",index);
            data.put("PageSize",10);
           
            jsonObject.put("Data",data);
            jsonObject.put("UserId",user.getFcmUserId()+"_ft");
            jsonObject.put("Token","AFTTOKENCARPOWERVIC");
            json = jsonObject.toString();
            //返回json
            System.out.println(json);
           
            
          
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		return json;
	}
	List<Data> list;
	Handler h=new Handler(){
	
		private msgList_adapter adapter;

		public void handleMessage(android.os.Message msg) {
	
			switch (msg.what) {
			case 1:
				String json=(String) msg.obj;
				System.out.println(json);
				//Toast.makeText(MsmListActivity.this, json, 0).show();
				Gson gson=new Gson();
				MsgListBanben msgList = gson.fromJson(json, MsgListBanben.class);
				Data2 data = msgList.Data;
				list = data.Data;
				adapter = new msgList_adapter(MsmListActivity.this,list);
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				page=2;
				mSwipeLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
				break;

			case 2:
				String json2=(String) msg.obj;
			
				
				if(json2==null||json2.contains("[]")){
					// 千万别忘了告诉控件加载完毕了哦！
					mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
					return ;
				}else{
					page=page+1;
				}
				System.out.println(json2);
				//Toast.makeText(MsmListActivity.this, json2, 0).show();
				Gson gson2=new Gson();
				MsgListBanben msgList2 = gson2.fromJson(json2, MsgListBanben.class);
				Data2 data2 = msgList2.Data;
				List<Data> list2 = data2.Data;
		
				for (int i = 0; i < list2.size(); i++) {
					list.add(list2.get(i));
				}
				
				
				adapter.notifyDataSetChanged();
					// 千万别忘了告诉控件加载完毕了哦！
				mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				break;
			}
			
		};
		
	};
	private ListView lv;
	private PullToRefreshLayout mSwipeLayout;
	private String json;

	
}
