package cn.com.foton.adapter;

import java.util.ArrayList;
import java.util.List;



import cn.com.foton.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class pagerAdapter extends PagerAdapter{
	List<Drawable> list=null;
	
	Context mcontext;
	public pagerAdapter(Context c) {
		// TODO Auto-generated constructor stub
		mcontext=c;
		list=new ArrayList<Drawable>();
		list.add(mcontext.getResources().getDrawable(R.drawable.zhu_3));
		list.add(mcontext.getResources().getDrawable(R.drawable.zhu_4));
		list.add(mcontext.getResources().getDrawable(R.drawable.zhu_5));
	}
	@Override//个数
	public int getCount() {
		// TODO Auto-generated method stub
		
		return list.size();
	}

	@Override//是否把对象作为view
	public boolean isViewFromObject(View view, Object obj) {
		// TODO Auto-generated method stub
		
		return view==(View)obj;
	}
	@Override//初始化一个item
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		View v=LayoutInflater.from(mcontext).inflate(R.layout.viewpager_item,null);
		int size=list.size();
		ImageView iv=(ImageView) v.findViewById(R.id.imageView1);
		
	
		iv.setImageDrawable(list.get(position));
		((ViewPager)container).addView(v);
		
		return v;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
@Override
public void destroyItem(View container, int position, Object object) {
	// TODO Auto-generated method stub
	((ViewPager)container).removeView((View)object);
}

}
