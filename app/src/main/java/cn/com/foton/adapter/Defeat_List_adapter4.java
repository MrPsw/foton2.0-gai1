package cn.com.foton.adapter;

import cn.com.foton.R;
import cn.com.foton.Util.ImageUtils;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.StringUtils;
import cn.com.foton.allot.FgActivity;
import cn.com.foton.base.Fail.MsgM;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.data.DB_data;
import cn.jpush.a.a.bl;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Defeat_List_adapter4 extends BaseAdapter {
	Context tc;
	ImageUtils utils = new ImageUtils();
	boolean ccb[];
	private ViewHolder holder;
	private static HashMap<Integer, Boolean> isSelected;
	List<MsgM> list;

	private MsgM base;
	boolean b = true;

	private boolean b2;

	public Defeat_List_adapter4(Context t, List<MsgM> lists, boolean bl) {
		// TODO Auto-generated constructor stub
		tc = t;
		list = lists;

		b = bl;

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
		String id = SharedPreferencesUtils.querysharep2(tc, "userid");
		LayoutInflater inflater = LayoutInflater.from(tc);
		convertView=null;
		base = list.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_wait_item2, null);

			holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
			holder.image = (ImageView) convertView.findViewById(R.id.imageVs);
			holder.bt = (Button) convertView.findViewById(R.id.chuiban);

			holder.Lin = (LinearLayout) convertView.findViewById(R.id.Linear);

			holder.fcmCustomerName = (TextView) convertView.findViewById(R.id.CustomerNamewait);
			holder.fcmCustomerSex = (TextView) convertView.findViewById(R.id.CustomerSexwait);
			holder.fcmCustomerMobile = (TextView) convertView.findViewById(R.id.CustomerMobilewait);
			holder.fcmProvinceCode = (TextView) convertView.findViewById(R.id.fcmProvinceCodewait);
			holder.fcmModelSeries = (TextView) convertView.findViewById(R.id.ModelSerieswait);
			holder.fcmCustomerLevel = (TextView) convertView.findViewById(R.id.CustomerLevelwait);
			holder.fcmCustomerCreateDate = (TextView) convertView.findViewById(R.id.CustomerCreateDatewait);
			holder.fcmInfoWay = (TextView) convertView.findViewById(R.id.fcmInfoWaywait);
			holder.image.setTag(base.fcmModelSeries);
			convertView.setTag(holder);
		} else {
			// È¡³öholder
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.fcmCustomerName.setText(base.fcmCustomerName + "");

		String fcmCustomerSexs = DB_data.getcodename("Stuent1", "codeName", "code", base.fcmCustomerSex);
		holder.fcmCustomerSex.setText(fcmCustomerSexs + "");
		holder.fcmCustomerMobile.setText(base.fcmCustomerMobile + "");

		String fcmProvinceCode = DB_data.getcodename("table2", "name", "code", base.fcmProvinceCode);
		String fcmCityCode = DB_data.getcodename("table3", "name", "code", base.fcmCityCode);
		String fcmTownCode = DB_data.getcodename("table4", "name", "code", base.fcmTownCode);

		holder.fcmProvinceCode.setText(fcmProvinceCode + "" + fcmCityCode + fcmTownCode);

		String fcmModelSerie = "";
		if (base.fcmModelSeries==""||base.fcmModelSeries==null) {

		} else {
			fcmModelSerie = DB_data.getcodename("Stuent3", "codeName", "code", base.fcmModelSeries);
		}
		holder.fcmModelSeries.setText(fcmModelSerie + "");

		String fcmCustomerLevel = DB_data.getcodename("Stuent5", "codeName", "code", base.fcmCustomerLevel);

		holder.fcmCustomerLevel.setText(fcmCustomerLevel + "");

//		String fcmCreateDate = DB_data.getcodename("Stuent2", "codeName", "code", base.fcmChangeType);
//		holder.fcmCustomerCreateDate.setText(fcmCreateDate + "");
		
		holder.fcmCustomerCreateDate.setText(base.fcmCustomerCreateDate);
		String fcmInfoWay = DB_data.getcodename("Stuent4", "codeName", "code", base.fcmInfoWay);
		holder.fcmInfoWay.setText(fcmInfoWay + "");
		
		if(holder.image.getTag()==base.fcmModelSeries){
			utils.setImageView(tc, holder.image,base.fcmModelSeries);
		}
		
		holder.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ccb[position] = true;
				} else {
					ccb[position] = false;
				}
			}
		});

	
		if (b) {
			if(id.equals("1")){
				holder.cb.setVisibility(View.INVISIBLE);
				holder.bt.setVisibility(View.INVISIBLE);
			}

		} else {
			holder.bt.setVisibility(View.INVISIBLE);
			holder.Lin.setVisibility(View.GONE);
			holder.cb.setVisibility(View.INVISIBLE);
			
			
		}

		
		
		
		return convertView;
	}

	public void setGone(boolean b) {
		b2 = b;
	}

	class ViewHolder {

		public LinearLayout Lin;

		public Button bt;
		public ImageView image;
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

	public boolean[] getcheck() {

		return ccb;
	}

}
