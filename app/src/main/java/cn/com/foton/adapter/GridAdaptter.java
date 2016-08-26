package cn.com.foton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.foton.R;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.base.GridviewBase;


public class GridAdaptter extends BaseAdapter{
	Context c;
	private ViewHoder vh;
	private List<GridviewBase> list;
	public GridAdaptter(Context t ,List<GridviewBase> lists) {
		// TODO Auto-generated constructor stub
		c=t;
		list=lists;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(c);
		vh = new ViewHoder(); 
		
		if(convertView==null){
			convertView	=inflater.inflate(R.layout.gridview_item, null);
			
			vh.yuan=(ImageView) convertView.findViewById(R.id.image);
			vh.number=(TextView) convertView.findViewById(R.id.text1);
			vh.bv=(ImageView) convertView.findViewById(R.id.image2);
			vh.text=(TextView) convertView.findViewById(R.id.text);
			convertView.setTag(vh);
		}else{
		vh=(ViewHoder) convertView.getTag();
		}
		GridviewBase gb=list.get(position);
		vh.yuan.setImageResource(gb.getBj());
		vh.number.setText(gb.getNumber());
		
		vh.text.setText(gb.getText());
		
		String userid=SharedPreferencesUtils.querysharep2(c,"userid");
		if(userid.equals("1")){
			if(position==5){
				vh.bv.setImageResource(R.drawable.biao);
			}else if(position==6){
				vh.bv.setImageResource(R.drawable.crate);
			}
		}else{
		
		if(position==6){
			vh.bv.setImageResource(R.drawable.biao);
		}else if(position==7){
			vh.bv.setImageResource(R.drawable.crate);
		}
		}
		return convertView;
	}
	class ViewHoder{
		ImageView yuan;
		TextView number;
		ImageView bv;
		TextView text;
	}

}
