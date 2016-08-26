package cn.com.foton.Allclue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import cn.com.foton.R;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.View.ViewPagerIndicator;
import cn.com.foton.fragment.ParticularActivity;
import cn.com.foton.fragment.fragment1;
import cn.com.foton.fragment.fragment2;
import cn.com.foton.fragment.fragment3;
import cn.com.foton.fragment.fragment4;
import cn.com.foton.fragment.fragment5;
import cn.com.foton.fragment.fragment6;



public class AllClueActivity extends FragmentActivity implements OnClickListener{


	private LayoutInflater inflater;
	private ViewPagerIndicator mIndicator;
	private List<Fragment> mTabContents = new ArrayList<Fragment>();
	private FragmentPagerAdapter mAdapter;
	private ViewPager mViewPager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_clue);
		inflater=LayoutInflater.from(this);
		ImageView image=(ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV=(TextView) findViewById(R.id.action_text);
		TV.setText("全部线索查询");
		
		

	
		initView();
		
		initDatas();

		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager,0);
		
	
	}
	private void initDatas()
	{		String id = SharedPreferencesUtils.querysharep2(AllClueActivity.this, "userid");
		
		if(id.equals("1")){

			 List<String> mDatas = Arrays.asList("已跟进","已进店","已成交","已战败","已废弃");
			 mIndicator.setTabItemTitles(mDatas);
			 	mTabContents.add(new fragment2());
				mTabContents.add(new fragment3());
				mTabContents.add(new fragment4());
				mTabContents.add(new fragment5());
				mTabContents.add(new fragment6());
		}else{
			 List<String> mDatas = Arrays.asList("已下发","已跟进","已进店","已成交","已战败","已废弃");
			 mIndicator.setTabItemTitles(mDatas);
			
				mTabContents.add(new fragment1());
				mTabContents.add(new fragment2());
				mTabContents.add(new fragment3());
				mTabContents.add(new fragment4());
				mTabContents.add(new fragment5());
				mTabContents.add(new fragment6());
					
		}
	
		
		
	

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mTabContents.size();
			}

			@Override
			public Fragment getItem(int position)
			{
				return mTabContents.get(position);
			}
		};
	}

	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_vp);
		mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		System.gc();
	}

	
}
