package cn.com.foton.adapter;

import cn.com.foton.R;
import cn.com.foton.allot.FgActivity;


import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;


public class allot_List_adapter extends BaseAdapter {
	Context tc;
	int list;
	boolean ccb[];
	private ViewHolder holder;
	private static HashMap<Integer, Boolean> isSelected;

	public allot_List_adapter(Context t) {
		// TODO Auto-generated constructor stub
		tc = t;
		
		
	}

	

	public void setcount(int n) {
		list = n;
		ccb=new boolean[list];
	
	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		
		LayoutInflater inflater = LayoutInflater.from(tc);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_allot_item, null);
			holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
			
			
			holder.imgbt= (ImageButton) convertView  
                    .findViewById(R.id.imageButton1); 
			convertView.setTag(holder);
		} else {
			// È¡³öholder
			holder = (ViewHolder) convertView.getTag();
		}
		holder.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				  if(isChecked){
					          ccb[position] = true;
				                }else{
			                     ccb[position] = false;
					            }
						}
		});
		 if(ccb[position]== true){
			 				holder.cb.setChecked(true);
			 				
			            }else{
			            	holder.cb.setChecked(false);
		            }  
		 
		 
		 holder.imgbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					Intent i=new Intent(tc,FgActivity.class);
					tc.startActivity(i);
		
			}
		});
		 
		return convertView;
	}

	class ViewHolder {
		TextView tvName; 
		CheckBox cb;
		ImageButton imgbt;
	}

public boolean[] getcheck(){
	
	
	return ccb;
}


}
