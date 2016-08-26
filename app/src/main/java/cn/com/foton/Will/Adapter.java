package cn.com.foton.Will;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.com.foton.R;
import cn.com.foton.Util.SharedPreferencesUtils;

import cn.com.foton.base.HistoryBase;
import cn.com.foton.base.ResultHistory.msg;
import cn.com.foton.data.DB_data;

public class Adapter extends BaseExpandableListAdapter{

	
	private Context context;
	private List<HistoryBase.msgBean> list2;
	private ViewHolder vh;
	public Adapter(Context ct, List<HistoryBase.msgBean> list) {
		// TODO Auto-generated constructor stub
		this.context=ct;
		this.list2=list;
	
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list2.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return list2.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return list2.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
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
			HistoryBase.msgBean base = list2.get(groupPosition);
			 if(base.getFcmPlanIsover().equals("1")){
			vh.fcmPlanDate.setText(base.getFcmResultDate());
			//vh.fcmPlanType.setText(fcmPlanType+"");
			
			String fcmModelSerie2 = null;
		
			System.out.println(base.getFcmIntentionSeries()+"");
			
			
			String fcmPlanType=SharedPreferencesUtils.querysharep2(parent.getContext(),base.getFcmResult());
			
			vh.fcmPlanType.setText(fcmPlanType+"");
			
			
			String fcmLeadLevel = DB_data.getcodename("Stuent5", "codeName", "code", base.getFcmLeadLevel());
			vh.fcmResult.setText(fcmLeadLevel);
			fcmModelSerie2 = DB_data.getcodename("Stuent3", "codeName", "code", base.getFcmAttentionModel());
			vh.fcmResult2.setText(fcmModelSerie2);
			//vh.fcmFollowupNote.setText(base.fcmFollowupNote);
		
			}
		return  convertView;
	}
	class ViewHolder {
		public LinearLayout li;
		public TextView fcmResult2;
		TextView fcmPlanDate; 
		TextView fcmPlanType; 
		TextView fcmResult; 
		TextView fcmFollowupNote;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		HistoryBase.msgBean base = list2.get(groupPosition);
	    LinearLayout ll = new LinearLayout(parent.getContext());  
        ll.setOrientation(0);  
//        ImageView generallogo = new ImageView(TestExpandableListView.this);  
//        generallogo.setImageResource(generallogos[groupPosition][childPosition]);  
//        ll.addView(generallogo);  
        TextView textView = getTextView();  
        textView.setText("跟进信息："+base.getFcmFollowupNote());
        textView.setTextSize(14);
        ll.addView(textView);  
		return  ll;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	  //自己定义一个获得textview的方法  
    TextView getTextView() {  
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 100);  
        TextView textView = new TextView(context);  
        textView.setLayoutParams(lp);  
        textView.setGravity(Gravity.CENTER_VERTICAL);  
        textView.setPadding(36, 0, 0, 0);  
        textView.setTextSize(20);  
        textView.setTextColor(Color.BLACK);  
        return textView;  
    }  
}
