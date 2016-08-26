package cn.com.foton.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.com.foton.R;
import cn.com.foton.base.fragment_base;

public class fragment_adapter extends BaseAdapter{
	Context ct;
	List<fragment_base> lists;
	private viewHolder vh;
public fragment_adapter(Context context,List<fragment_base> list){
	// TODO Auto-generated constructor stub
	ct=context;
	lists=list;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		vh = new viewHolder();
		LayoutInflater inflate = LayoutInflater.from(ct);
		if(convertView==null){
			convertView=inflate.inflate(R.layout.fragment_allot_item,null);
		vh.tv1=(TextView) convertView.findViewById(R.id.fg_1);
		vh.tv2=(TextView) convertView.findViewById(R.id.fg_2);
		convertView.setTag(vh);
		}else{
			vh=(viewHolder) convertView.getTag();
			}
		fragment_base base=lists.get(position);
		vh.tv1.setText(base.getName());
		vh.tv2.setText(base.getText());
		
		return convertView;
	}
	
	class viewHolder{
		TextView tv1;
		TextView tv2;
	}

}
