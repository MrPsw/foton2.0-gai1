package cn.com.foton.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import cn.com.foton.R;
import cn.com.foton.Util.ImageUtils;
import cn.com.foton.Util.JPushHttp;
import cn.com.foton.Util.SharedPreferencesUtils;
import cn.com.foton.Util.ToastUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.allot.FgActivity;
import cn.com.foton.base.GsonBase.Msg.FcmCustomer;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.data.DB_data;

public class wait_List_adapter2 extends BaseAdapter {
	Context tc;
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	ImageUtils utils = new ImageUtils();
	boolean ccb[];

	private static HashMap<Integer, Boolean> isSelected;
	List<FcmCustomer> list;

	private FcmCustomer base;
	boolean b = true;

	private boolean b2;
	String activityId;

	public wait_List_adapter2(Context t, List<FcmCustomer> lists, boolean bl, String id) {
		// TODO Auto-generated constructor stub
		tc = t;
		list = lists;
		ccb = new boolean[lists.size()];
		b = bl;
		activityId = id;
	}

	public void setccbSize(int i) {
		ccb = new boolean[i];
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
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder=null;
		String id = SharedPreferencesUtils.querysharep2(tc, "userid");
		convertView=null;

	
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
			holder.fcmIntentionSeries = (TextView) convertView.findViewById(R.id.ModelSerieswait);
			holder.fcmCustomerLevel = (TextView) convertView.findViewById(R.id.CustomerLevelwait);
			holder.fcmCustomerCreateDate = (TextView) convertView.findViewById(R.id.CustomerCreateDatewait);
			holder.fcmInfoWay = (TextView) convertView.findViewById(R.id.fcmInfoWaywait);
			holder.fcmCustomerSaleManId = (TextView) convertView.findViewById(R.id.fcmCustomerSaleManId);
			holder.fcmLeadStatusLin = (LinearLayout) convertView.findViewById(R.id.fcmLeadStatusLayout);
			holder.fcmLeadStatus = (TextView) convertView.findViewById(R.id.fcmLeadStatus);
			holder.li = (LinearLayout) convertView.findViewById(R.id.item_onclik);
//			utils.setImageView(tc, holder.image,base.fcmIntentionSeries);
			holder.image.setTag(base.fcmIntentionSeries);
			convertView.setTag(holder);

		} else {
			// 取出holder
			holder = (ViewHolder) convertView.getTag();
			
		}

		if(holder.image.getTag()==base.fcmIntentionSeries){
			utils.setImageView(tc, holder.image,base.fcmIntentionSeries);
		}
//		imageLoader.DisplayImage(url, imageView);  

		
		holder.fcmCustomerName.setText(base.fcmCustomerName + "");

		String fcmCustomerSexs = DB_data.getcodename("Stuent1", "codeName", "code", base.fcmCustomerSex);
		holder.fcmCustomerSex.setText(fcmCustomerSexs + "");
		holder.fcmCustomerMobile.setText(base.fcmCustomerMobile + "");

		String fcmProvinceCode = DB_data.getcodename("table2", "name", "code", base.fcmProvinceCode);
		String fcmCityCode = DB_data.getcodename("table3", "name", "code", base.fcmCityCode);
		String fcmTownCode = DB_data.getcodename("table4", "name", "code", base.fcmTownCode);

		holder.fcmProvinceCode.setText(fcmProvinceCode + "  " + fcmCityCode + " " + fcmTownCode);

		String fcmModelSerie = "";
		if (base.fcmIntentionSeries.equals("")) {

		} else {
			fcmModelSerie = DB_data.getcodename("Stuent3", "codeName", "code", base.fcmIntentionSeries);
		}
		
		
		
		holder.fcmIntentionSeries.setText(fcmModelSerie + "");

		String fcmCustomerLevel = DB_data.getcodename("Stuent5", "codeName", "code", base.fcmCustomerLevel);

		holder.fcmCustomerLevel.setText(fcmCustomerLevel + "");

		// String fcmCreateDate = DB_data.getcodename("Stuent2", "codeName",
		// "code", base.fcmChangeType);
		holder.fcmCustomerCreateDate.setText(base.fcmCreateDate + "");

		String fcmInfoWay = DB_data.getcodename("Stuent4", "codeName", "code", base.fcmInfoWay);
		holder.fcmInfoWay.setText(fcmInfoWay + "");

		holder.fcmCustomerSaleManId.setText(base.fcmCustomerSaleManName);

		if(base.fcmLeadStatus!=""){
			holder.fcmLeadStatusLin.setVisibility(View.VISIBLE);
			holder.fcmLeadStatus.setText(base.fcmLeadStatus+"");
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

		holder.li.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(parent.getContext(), FgActivity.class);
				i.putExtra("position", position + "");
				i.putExtra("Activityid", activityId);
				parent.getContext().startActivity(i);

			}
		});
		holder.bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				FcmCustomer baset = list.get(position);
				
				userBase user = UserUtils.getUserBase(tc);

				String fcmModelSerie = DB_data.getcodename("Stuent3", "codeName", "code", base.fcmIntentionSeries);

				try {
		
					JSONObject jsonObject = new JSONObject();
					JSONObject data = new JSONObject();
					data.put("CustomerId", baset.fcmCustomerId);
					data.put("BusinessId", baset.fcmBusinessId);
					data.put("FromUserId", user.getFcmUserId());
					data.put("ToUserId", baset.fcmCustomerSaleManId);
					data.put("Message", "您收到一条催办消息");
					data.put("Title", "Foton");
					data.put("CustomerName", baset.fcmCustomerName);
					data.put("Phone", baset.fcmCustomerMobile);
					data.put("CarType", baset.fcmIntentionSeries + "");
					data.put("BuyTime", baset.fcmChangeType);
					data.put("FailReason", "");
					jsonObject.put("Data", data);
					jsonObject.put("UserId", user.getFcmUserId() + "_ft");
					jsonObject.put("Token", "AFTTOKENCARPOWERVIC");
					// 最后返回jsonObject
					// 或者
					final String json = jsonObject.toString();
					// 返回json
					System.out.println(json);
					new JPushHttp(json,App_url.JPush_cuiban, h, 1, tc).start();
					ToastUtils.ImageToastp(tc, R.drawable.cuibanchengg, "催办成功", 0);

				} catch (Exception e) {
					// TODO: handle exception
				}

			
			}
		});

		if (ccb[position] == true) {
			holder.cb.setChecked(true);

		} else {
			holder.cb.setChecked(false);
		}
		if (b) {
			if (id.equals("1")) {
				holder.cb.setVisibility(View.INVISIBLE);
				holder.bt.setVisibility(View.INVISIBLE);
			}

		} else {
			holder.bt.setVisibility(View.INVISIBLE);
			holder.Lin.setVisibility(View.GONE);

		}

	    
		return convertView;
	}

	public void setGone(boolean b) {
		b2 = b;
	}

	class ViewHolder {

		public LinearLayout li;

		public TextView fcmCustomerSaleManId;

		public ImageView image;

		public LinearLayout Lin;

		public Button bt;

		CheckBox cb;

		TextView fcmCustomerName;
		TextView fcmCustomerSex;
		TextView fcmCustomerMobile;
		TextView fcmProvinceCode;
		TextView fcmIntentionSeries;
		TextView fcmCustomerLevel;
		TextView fcmCustomerCreateDate;
		TextView fcmInfoWay;


		public LinearLayout fcmLeadStatusLin;
		public TextView fcmLeadStatus;
	}

	public boolean[] getcheck() {

		return ccb;
	}

	Handler h = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String t = (String) msg.obj;
				//Toast.makeText(tc, t, 0).show();

				break;

			default:
				break;
			}
		};
	};
}
