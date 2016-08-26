package cn.com.foton.adapter;



import cn.com.foton.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class List_adapter extends BaseAdapter{
	Context tc;
	int list;
	public List_adapter(Context t) {
		// TODO Auto-generated constructor stub
		tc=t;
		
	}
	public void setcount(int n){
		list=n;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=LayoutInflater.from(tc).inflate(R.layout.list_tab_item,null);
		return v;
	}

}
