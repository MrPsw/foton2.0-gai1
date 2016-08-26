package cn.com.foton.Defeat;

import java.util.List;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.Util.Url_Log;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.adapter.Defeat_List_adapter4;
import cn.com.foton.adapter.defeat_List_adapter;
import cn.com.foton.adapter.wait_List_adapter2;
import cn.com.foton.allot.AllotActivity;
import cn.com.foton.base.Fail;
import cn.com.foton.base.Fail.MsgM;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.data.static_data;




public class DefeatActivity extends Activity implements OnClickListener{
	int page=2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_defeat);
		
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("战败审核");
		
		
			body = GetBody(1);
		
			dialog = new IOSdialog(this, "获取数据");
			new myHttp2(body, App_url.QueryFail, h, 1,DefeatActivity.this).start();
		
			lv = (ListView) findViewById(R.id.listV);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent i=new Intent(DefeatActivity.this,FollowupActivity.class);
				i.putExtra("position", position+"");
				startActivity(i);
				//Toast.makeText(DefeatActivity.this, ""+position, 0).show();
			}
		});
		
		mSwipeLayout = (PullToRefreshLayout)findViewById(R.id.id_swipe_ly);

		mSwipeLayout.setOnRefreshListener(new  cn.com.foton.View.PullToRefreshLayout.OnRefreshListener() {
			
			@Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				//dialog=new IOSdialog(WaitActivity.this, "获取数据");
				
				myHttp http=new myHttp(body,App_url.QueryFail, h);
				http.start();
			}
			
			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				
			new myHttp2(GetBody(page),App_url.QueryFail, h, 4,DefeatActivity.this).start();
			}
		});	
		
	}

	private RequestBody GetBody(int page) {
		userBase user = UserUtils.getUserBase(this);
		RequestBody body = new FormEncodingBuilder()
		        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
		        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
		        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
		        .add("fcmCustomer.fcmPageNumber", "10")
		        .add("fcmCustomer.fcmPageSize",page+"")
			    .build();
		return body;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.action2_image:
			finish();
			break;

		
		}
	}
	static List<MsgM> lists;
	Defeat_List_adapter4 adapter ;
	Fail base;
	Handler h=new Handler(){
	public void handleMessage(android.os.Message msg) {
	
	
		
		switch (msg.what) {
		case 1:
			String jsonstring=(String) msg.obj;
			System.out.println(jsonstring);
			String jsonString = jsonT.GetjsonAll(jsonstring);
			System.out.println(jsonString);
			if(!jsonString.contains("没有与条件相关数据")){
				Gson g=new Gson();
				base = g.fromJson(jsonString, Fail.class);
				lists = base.msg;
				if(lists.size()>0){
				adapter = new Defeat_List_adapter4(DefeatActivity.this,lists,false);
				lv.setAdapter(adapter);
				
				}
				dialog.close();
				adapter.notifyDataSetChanged();
				page=2;
				mSwipeLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//				dialog.dismiss();
//				adapter.notifyDataSetChanged();
//				mSwipeLayout.setRefreshing(false);
			}
			dialog.close();
			break;
		case 4:
			String jsonstring4=(String) msg.obj;
			String jsonString4 = jsonT.GetjsonAll(jsonstring4);
			if(jsonString4==null||jsonString4.contains("没有与条件相关数据")){
				// 千万别忘了告诉控件加载完毕了哦！
				mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				return ;
			}else{
				page=page+1;
				Gson g4=new Gson();
				Fail base4 = g4.fromJson(jsonString4,Fail.class);
				
	
				List<MsgM> lists2 = base4.msg;
				for (int i = 0; i < lists2.size(); i++) {
					lists.add(lists2.get(i));
				}
				adapter.notifyDataSetChanged();
			}
			System.out.println(jsonString4);
		
					// 千万别忘了告诉控件加载完毕了哦！
				mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			break;
		}
		
	};	
	};
	private ListView lv;
	private RequestBody body;
	private PullToRefreshLayout mSwipeLayout;
	private IOSdialog dialog;

	public static List<MsgM> getlist(){
		
		
		return lists;
		
	};

	
}
