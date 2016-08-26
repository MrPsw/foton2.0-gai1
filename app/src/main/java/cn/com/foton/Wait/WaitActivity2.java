package cn.com.foton.Wait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dialog.Dalog_prompt;
import cn.com.foton.Dialog.Dalog_prompt.NegativeButtonOnclik;
import cn.com.foton.Dialog.Dalog_prompt.PositiveButtonOnclik;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.JPushBase.allotBase;
import cn.com.foton.R;
import cn.com.foton.R.drawable;
import cn.com.foton.R.id;
import cn.com.foton.R.layout;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.TimeUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.allotJPush;
import cn.com.foton.Util.myHttp;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.View.PullToRefreshLayout;
import cn.com.foton.adapter.pouplistadapter;
import cn.com.foton.adapter.wait_List_adapter2;
import cn.com.foton.base.GsonBase;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.base.Xiaoshoubase;
import cn.com.foton.base.Xiaoshoubase.Msg;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;

import static cn.com.foton.R.layout.activity_wait;

/**
 * 主页
 * 今日已跟进线索
 * @author Administrator
 *
 */
public class WaitActivity2 extends AppCompatActivity implements  OnClickListener{

	private String mTitle;
	public static final String BUNDLE_TITELE="title";
	private List<View> viewList = new ArrayList<>();        //视图列表
	private String[] title = {"tab1", "tab2", "tab3"};      //Tab标题列表

	private TabLayout tabLayout = null;
	private ViewPager viewPager = null;     //ViewPager


	private TabLayout tab_FindFragment_title;                            //定义TabLayout
	private ViewPager vp_FindFragment_pager;                             //定义viewPager
	private MyAdapter fAdapter;                               //定义adapter
	private List<Fragment> list_fragment;                                //定义要装fragment的列表
	private List<String> list_title;                                     //tab名称列表
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waitlayout);
		initActionbar();
		initTAB();
	}

	private void initActionbar() {
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("本月累计闭环线索");
	}

	private void initTAB() {


		tab_FindFragment_title = (TabLayout)findViewById(R.id.tab_FindFragment_title);
		vp_FindFragment_pager = (ViewPager)findViewById(R.id.vp_FindFragment_pager);
		//将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
		list_title = new ArrayList<>();
		list_title.add("已成交");
		list_title.add("已战败");
		list_title.add("已废弃");


		//将fragment装进列表中
		list_fragment = new ArrayList<>();
		//初始化各fragment


		Fragment f1=new SimepFragment1();
		Fragment f2=new SimepFragment2();
		Fragment f3=new SimepFragment3();
		list_fragment.add(f1);
		list_fragment.add(f2);
		list_fragment.add(f3);



		//为TabLayout添加tab名称
		tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
		tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
		tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));


		fAdapter = new MyAdapter(getSupportFragmentManager(),list_fragment,list_title);

		//viewpager加载adapter
		vp_FindFragment_pager.setAdapter(fAdapter);
		//tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
		//TabLayout加载viewpager
		tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
		//设置TabLayout的模式
		tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.action2_image:
		finish();
		break;
		}
	}
}


    


