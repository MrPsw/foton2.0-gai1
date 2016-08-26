package cn.com.foton.viewhoder;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

	protected Context context;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	int LayoutId;
	public BaseAdapter(Context ct,List<T> mDatas,int LayoutId) {
		// TODO Auto-generated constructor stub
		this.context =ct;
		mInflater=LayoutInflater.from(ct);
		this.mDatas=mDatas;
		this.LayoutId=LayoutId;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public  View getView(int position, View convertView, ViewGroup parent){
		ViewHoder viewhoder=ViewHoder.get(context, convertView, parent,LayoutId, position);
		
		convert(viewhoder, getItem(position));
		return viewhoder.getConvertView();
	}
		// TODO Auto-generated method stub

	public abstract void convert(ViewHoder hoder,T t);
}
