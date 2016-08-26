package cn.com.foton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cn.com.foton.R;
import cn.com.foton.Util.ImageUtils;
import cn.com.foton.Util.StringUtils;
import cn.com.foton.base.GsonBase;
import cn.com.foton.data.DB_data;

public class wait_List_adapter3 extends BaseAdapter {
	Context tc;
	ImageUtils utils = new ImageUtils();

	private ViewHolder holder;
	private static HashMap<Integer, Boolean> isSelected;
	List<GsonBase.Msg.FcmCustomer> list;

	private GsonBase.Msg.FcmCustomer base;
	boolean b=true;
	public wait_List_adapter3(Context t, List<GsonBase.Msg.FcmCustomer>  list2, boolean bl) {
		// TODO Auto-generated constructor stub
		tc = t;
		list = list2;
	
		b=bl;

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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = null;
		base = list.get(position);
		LayoutInflater inflater = LayoutInflater.from(tc);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_wait_item2, null);
			
			holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

			holder.bt = (Button) convertView.findViewById(R.id.chuiban);
			
			holder.Lin = (LinearLayout) convertView.findViewById(R.id.Linear);
			
			holder.image = (ImageView) convertView.findViewById(R.id.imageVs);
			holder.fcmCustomerName = (TextView) convertView.findViewById(R.id.CustomerNamewait);
			holder.fcmCustomerSex = (TextView) convertView.findViewById(R.id.CustomerSexwait);
			holder.fcmCustomerMobile = (TextView) convertView.findViewById(R.id.CustomerMobilewait);
			holder.fcmProvinceCode = (TextView) convertView.findViewById(R.id.fcmProvinceCodewait);
			holder.fcmModelSeries = (TextView) convertView.findViewById(R.id.ModelSerieswait);
			holder.fcmCustomerLevel = (TextView) convertView.findViewById(R.id.CustomerLevelwait);
			holder.fcmCustomerCreateDate = (TextView) convertView.findViewById(R.id.CustomerCreateDatewait);
			holder.fcmInfoWay = (TextView) convertView.findViewById(R.id.fcmInfoWaywait);
			holder.image.setTag(base.fcmIntentionSeries);
			convertView.setTag(holder);
		} else {
			// È¡³öholder
			holder = (ViewHolder) convertView.getTag();
		}
	
		if(b){
			
			
		}else{
			holder.cb.setVisibility(View.INVISIBLE);
			holder.bt.setVisibility(View.INVISIBLE);
			holder.Lin.setVisibility(View.GONE);
		}
		

	
		holder.fcmCustomerName.setText(base.fcmCustomerName + "");

		String fcmCustomerSexs = DB_data.getcodename("Stuent1", "codeName", "code", base.fcmCustomerSex);
		holder.fcmCustomerSex.setText(fcmCustomerSexs + "");
		holder.fcmCustomerMobile.setText(base.fcmCustomerMobile + "");

		String fcmProvinceCode = DB_data.getcodename("table2", "name", "code", base.fcmProvinceCode);
		String fcmCityCode = DB_data.getcodename("table3", "name", "code", base.fcmCityCode);
		String fcmTownCode = DB_data.getcodename("table4", "name", "code", base.fcmTownCode);

		holder.fcmProvinceCode.setText(fcmProvinceCode + "  " + fcmCityCode +"  "+fcmTownCode);
		
		String fcmModelSerie="";
		if(base.fcmIntentionSeries.equals("")){
			
		}else{
		 fcmModelSerie=DB_data.getcodename("Stuent3","codeName","code",base.fcmIntentionSeries);
		}
		holder.fcmModelSeries.setText(fcmModelSerie + "");

		String fcmCustomerLevel = DB_data.getcodename("Stuent5", "codeName", "code", base.fcmCustomerLevel);

		holder.fcmCustomerLevel.setText(fcmCustomerLevel + "");

		String fcmCreateDate = StringUtils.getData(base.fcmCreateDate + "");

		holder.fcmCustomerCreateDate.setText(fcmCreateDate + "");
		

		String fcmInfoWay = DB_data.getcodename("Stuent4", "codeName", "code", base.fcmInfoWay);
		holder.fcmInfoWay.setText(fcmInfoWay + "");

		if(holder.image.getTag()==base.fcmIntentionSeries){
			utils.setImageView(tc, holder.image,base.fcmIntentionSeries);
		}
		
		return convertView;
	}

	class ViewHolder {

		public ImageView image;

		public LinearLayout Lin;

		public Button bt;

		CheckBox cb;
	
		TextView fcmCustomerName;
		TextView fcmCustomerSex;
		TextView fcmCustomerMobile;
		TextView fcmProvinceCode;
		TextView fcmModelSeries;
		TextView fcmCustomerLevel;
		TextView fcmCustomerCreateDate;
		TextView fcmInfoWay;

	}
	

	

}
