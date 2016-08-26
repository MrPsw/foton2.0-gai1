package cn.com.foton.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.com.foton.R;
import cn.com.foton.base.Xiaoshoubase.Msg;

public class pouplistadapter extends BaseAdapter{
	Context ct;
	List<Msg> list;
public pouplistadapter(Context c,List<Msg> msgbase) {
	// TODO Auto-generated constructor stub
	ct=c;
	list=msgbase;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		convertView	=LayoutInflater.from(ct).inflate(R.layout.poup_item, null);
		Msg base = list.get(position);
		TextView tv=(TextView) convertView.findViewById(R.id.pouptext);
		tv.setText(base.fcmRealName+"");
		return convertView;
	}

}
