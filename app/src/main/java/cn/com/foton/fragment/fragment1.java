package cn.com.foton.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import cn.com.foton.Util.myHttp;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.adapter.fragment1_List_adapter;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;

public class fragment1 extends Fragment implements OnItemClickListener,OnRefreshListener{
	
	private String fcmuserid;
	private String fcmpositionid;
	private String fcmcompanycode;
	private String fcmdealercode;
	private ListView lv;
	private static  List<FcmCustomer> lists;
	int page=2;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		body=GetBoay(1);
		
		
	}
	private RequestBody GetBoay(int page) {
		userBase user = UserUtils.getUserBase(getActivity());
		RequestBody bodyt = new FormEncodingBuilder()
		        .add("fcmCustomer.fcmUserId", user.getFcmUserId())
		        .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
		        .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
		        .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
		        .add("fcmCustomer.fcmPageNumber", "10")
				.add("fcmCustomer.fcmPlanResult","18021010")
		        .add("fcmCustomer.fcmPageSize",page+"" )
			       
		        .build();
		return bodyt;
	}
	
	

	View v;
	private PullToRefreshLayout mSwipeLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		  
		
	
	        v=inflater.inflate(R.layout.fragment1, null);  
	        
	        mSwipeLayout = (PullToRefreshLayout)v.findViewById(R.id.id_swipe_ly);
	    	
			mSwipeLayout.setOnRefreshListener(new  cn.com.foton.View.PullToRefreshLayout.OnRefreshListener() {
				
				@Override
				public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
					// TODO Auto-generated method stub
					//dialog=new IOSdialog(WaitActivity.this, "��ȡ����");
					
					myHttp http=new myHttp(body, App_url.ALL, h);
					http.start();
				}
				
				@Override
				public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
					// TODO Auto-generated method stub
					
				new myHttp2(GetBoay(page), App_url.ALL, h, 4,getActivity()).start();
				}
			});

	   
//		dialog=new IOSdialog(getActivity(), "��ȡ����");
		myHttp http=new myHttp(body, App_url.ALL, h);
		http.start();
	
	lv = (ListView)v.findViewById(R.id.list_fragment1);
	lv.setOnItemClickListener(this);
		return v;
	}
	Handler h=new Handler(){


	private fragment1_List_adapter adapter;

	public void handleMessage(android.os.Message msg) {
		switch (msg.what) {
		case 1:
		String jsonstring=(String) msg.obj;
		String jsonString = jsonT.GetjsonAll(jsonstring);

			LogUtil.e(getActivity(),jsonString);
		if(jsonString==null){
			return ;
		}
			Gson g=new Gson();
			GsonBase base = g.fromJson(jsonString, GsonBase.class);
			lists = base.msg.fcmCustomer;
			adapter = new fragment1_List_adapter(getActivity(),lists);
			lv.setAdapter(adapter);
			//dialog.close();
			adapter.notifyDataSetChanged();
			page=2;
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
				
	
				List<FcmCustomer> lists2 = base4.msg.fcmCustomer;
				for (int i = 0; i < lists2.size(); i++) {
					lists.add(lists2.get(i));
				}
				System.out.println(lists.size());
				
				adapter.notifyDataSetChanged();
					// ǧ������˸��߿ؼ����������Ŷ��
				mSwipeLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			break;
		}
	};
	
};
	private RequestBody body;
	private IOSdialog dialog;
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "������"+position, 0).show();
		
		
		Intent in=new Intent(getActivity(), ParticularActivity.class);
		in.putExtra("position", position+"");
		in.putExtra("id","1");
		
		getActivity().startActivity(in);
		
		
		
		
//		
	}
	public static List<FcmCustomer> getlist(){
		
		
		return lists;
		
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		dialog=new IOSdialog(getActivity(), "��ȡ����");
		myHttp http=new myHttp(body, App_url.ALL, h);
		http.start();
	};
	
	

}
