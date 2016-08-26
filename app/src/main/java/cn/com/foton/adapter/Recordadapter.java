package cn.com.foton.adapter;


import java.util.List;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.com.foton.R;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Will.Particular_tab1Activity;
import cn.com.foton.base.ResultHistory.msg;
import cn.com.foton.base.TodayBase.FcmCustomer;
import cn.com.foton.data.DB_data;

public class Recordadapter extends BaseAdapter{

	private ViewHolder vh;
	private List<msg> list2;
	public Recordadapter(List<msg> list2) {
		// TODO Auto-generated constructor stub
		this.list2=list2;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list2.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list2.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		
	

		
		vh = new ViewHolder();
		if(convertView==null){
			convertView=inflater.inflate(R.layout.recor_list_item, null);
			vh.li=(LinearLayout) convertView.findViewById(R.id.unfold);
			vh.fcmPlanDate=(TextView) convertView.findViewById(R.id.fcmPlanDate);
			vh.fcmPlanType=(TextView) convertView.findViewById(R.id.fcmPlanType);
			vh.fcmResult=(TextView) convertView.findViewById(R.id.fcmResult);
			vh.fcmResult2=(TextView) convertView.findViewById(R.id.fcmResult2);
			vh.fcmFollowupNote=(TextView) convertView.findViewById(R.id.fcmFollowupNote);
			
			convertView.setTag(vh);
			}else{
			vh=(ViewHolder) convertView.getTag();
			}
			msg base = list2.get(position);
			
			
			if(base.fcmPlanIsover.equals("0")){
				
			}else if(base.fcmPlanIsover.equals("1")){
			vh.fcmPlanDate.setText(base.fcmPlanDate);
			//vh.fcmPlanType.setText(fcmPlanType+"");
			
			String fcmModelSerie2 = "";
		
			System.out.println(base.fcmAttentionModel);
			
			
			String fcmPlanType=SharedPreferencesUtils.querysharep(parent.getContext(),base.fcmResult);
			
			vh.fcmPlanType.setText(fcmPlanType+"");
			
			
			String fcmLeadLevel = DB_data.getcodename("Stuent5", "codeName", "code", base.fcmLeadLevel);
			vh.fcmResult.setText(fcmLeadLevel);
			fcmModelSerie2 = DB_data.getcodename("Stuent3", "codeName", "code", base.fcmAttentionModel);
			vh.fcmResult2.setText(fcmModelSerie2);
			vh.fcmFollowupNote.setText(base.fcmFollowupNote);
			vh.li.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(vh.fcmFollowupNote.isShown()){
					vh.fcmFollowupNote.setVisibility(View.GONE);
					}else{
					vh.fcmFollowupNote.setVisibility(View.VISIBLE);
					}
				}
			});
			
		

		
		
			}
		return convertView;
	}
	class ViewHolder {
		public LinearLayout li;
		public TextView fcmResult2;
		TextView fcmPlanDate; 
		TextView fcmPlanType; 
		TextView fcmResult; 
		TextView fcmFollowupNote;
	}

}
