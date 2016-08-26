package cn.com.foton.Wait;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.R;
import cn.com.foton.Util.DateUtil;
import cn.com.foton.Util.LogUtil2;
import cn.com.foton.Util.TimeUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp3;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.adapter.fragment1_List_adapter;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.fragment.ParticularActivity;


/**
 * Created by Misszhijiansha on 2016/6/10.
 */
public class SimepFragment2 extends Fragment implements AdapterView.OnItemClickListener{

    public static final String BUNDLE_TITELE="title";
    userBase user;
    private PullToRefreshLayout mSwipeLayout;
    private int page=2;
    private static List<GsonBase.Msg.FcmCustomer> lists,lists2;
    private fragment1_List_adapter adapter;
    private ListView lV;
    boolean isAdd=false;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        user = UserUtils.getUserBase(getActivity());
       View view=inflater.inflate(R.layout.layout2,null);
        initView(view);
        initHttp();

      return view;
    }

    private RequestBody GetBody(int number) {

        RequestBody bodyST;
        String date= TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_DATE);
        String date2= DateUtil.GetMONTH_1();

        LogUtil2.e(date+""+date2,true);
        return  bodyST = new FormEncodingBuilder()
                .add("fcmCustomer.fcmUserId", user.getFcmUserId())
                .add("fcmCustomer.fcmPositionId", user.getFcmPositionId())
                .add("fcmCustomer.fcmCompanyCode", user.getFcmCompanyCode())
                .add("fcmCustomer.fcmDealerCode", user.getFcmDealerCode())
                .add("fcmCustomer.fcmPlanResult","18021006")

                .add("fcmCustomer.fcmPageNumber", "10")

                .add("fcmCustomer.fcmResultDateBegin", date2)
                .add("fcmCustomer.fcmResultDateEnd",date)

                .add("fcmCustomer.fcmPageSize", ""+number)

                .build();



    }
    private void initHttp() {
        new myHttp3(GetBody(1), App_url.ALL,h,0).start();
    }

    private void initView(View view) {

        lV = (ListView)view.findViewById(R.id.listView1);
        lV.setOnItemClickListener(this);
        mSwipeLayout = (PullToRefreshLayout)view.findViewById(R.id.id_swipe_ly);

        mSwipeLayout.setOnRefreshListener(new  cn.com.foton.View.PullToRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                // TODO Auto-generated method stub
                //dialog=new IOSdialog(WaitActivity.this, "获取数据");

                new myHttp3(GetBody(1), App_url.ALL,h,0).start();

                isAdd=false;
            }
            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                // TODO Auto-generated method stub
                isAdd=true;
                new myHttp3(GetBody(page), App_url.ALL,h,0).start();

            }
        });

    }

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String jsonstring=(String) msg.obj;
                    String jsonString = jsonT.GetjsonAll(jsonstring);
                    Log.e("今日已跟进结果：",jsonString);
                    Gson g=new Gson();
                    GsonBase base = g.fromJson(jsonString, GsonBase.class);
                    if(isAdd){
                        lists2 = base.msg.fcmCustomer;
                        lists.addAll(lists2);

                    }else{
                        lists = base.msg.fcmCustomer ;
                    }


                    adapter = new fragment1_List_adapter(getActivity(),lists);


                    lV.setAdapter(adapter);
                    page=2;

                    adapter.notifyDataSetChanged();
                    // 千万别忘了告诉控件刷新完毕了哦！
                    mSwipeLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//				mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    public static SimepFragment2 newInstance(String title){
        Bundle bundle =new Bundle();
        bundle.putString(BUNDLE_TITELE,title);

        SimepFragment2 fragment=new SimepFragment2();
        fragment.setArguments(bundle);

        return fragment;
    }

    public static List<GsonBase.Msg.FcmCustomer> getlist(){


        return lists;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in=new Intent(getActivity(), ParticularActivity.class);
        in.putExtra("position", position+"");
        in.putExtra("id","8");

        getActivity().startActivity(in);
    }
}
