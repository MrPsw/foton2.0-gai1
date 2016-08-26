package cn.com.foton.jpush;

import cn.com.foton.R;
import cn.com.foton.Defeat.DefeatActivity;
import cn.com.foton.JPushBase.MsgListBanben.Data2.Data;
import cn.com.foton.Will.Will_Activity;
import cn.com.foton.allot.AllotActivity;
import cn.com.foton.data.DB_data;
import cn.com.foton.overtime.OvertimeActivity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class msgList_adapter extends BaseAdapter {
	Context tc;
	List<Data> list;
	String[] title = { "无该类别", "您收到一条新销售线索", "您收到一条分配消息", "您收到一条催办消息", "您收到一条战败申请" };

	public msgList_adapter(Context t, List<Data> list) {
		// TODO Auto-generated constructor stub
		tc = t;
		this.list = list;
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
		LayoutInflater inflater = LayoutInflater.from(tc);
		Viewhoder viewhoder = new Viewhoder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.msglist_item, null);
			viewhoder.title = (TextView) convertView.findViewById(R.id.title);
			viewhoder.time = (TextView) convertView.findViewById(R.id.tiem);
			viewhoder.tel = (TextView) convertView.findViewById(R.id.tel);
			viewhoder.che = (TextView) convertView.findViewById(R.id.che);
			viewhoder.name = (TextView) convertView.findViewById(R.id.name);
			viewhoder.ishide = (LinearLayout) convertView.findViewById(R.id.zhanbai1is);
			viewhoder.zhanbai = (TextView) convertView.findViewById(R.id.zhanbai);
			viewhoder.ishide.setVisibility(View.GONE);
			convertView.setTag(viewhoder);
		} else {
			viewhoder = (Viewhoder) convertView.getTag();
			viewhoder.ishide.setVisibility(View.GONE);
		}
		final Data data = list.get(position);

		String CarType = DB_data.getcodename("Stuent3", "codeName", "code", data.CarType);

		String BuyTime = DB_data.getcodename("Stuent2", "codeName", "code", data.BuyTime);

		viewhoder.title.setText((data.Message) == "" || (data.Message) == null ? "暂无数据" : data.Message + "");
		viewhoder.name
				.setText((data.CustomerName) == "" || (data.CustomerName) == null ? "暂无数据" : data.CustomerName + "");
		viewhoder.time.setText((BuyTime) == "" || (BuyTime) == null ? "暂无数据" : BuyTime + "");
		viewhoder.tel.setText((data.Phone) == "" || (data.Phone) == null ? "暂无数据" : data.Phone + "");
		viewhoder.che.setText((CarType) == "" || (CarType) == null ? "暂无数据" : CarType + "");

		viewhoder.ishide.setVisibility(View.GONE);
		if (data.MessageType == 4 || data.equals("4")) {
			System.out.println(data.MessageType);
			System.out.println(position);
			viewhoder.ishide.setVisibility(View.VISIBLE);
			viewhoder.zhanbai.setText(data.FailReason);
		}
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (data.MessageType) {
				case 1:
					tc.startActivity(new Intent(tc, AllotActivity.class));
					break;

				case 2:
					tc.startActivity(new Intent(tc, Will_Activity.class));
					break;

				case 3:
					tc.startActivity(new Intent(tc, OvertimeActivity.class));
					break;

				case 4:
					tc.startActivity(new Intent(tc, DefeatActivity.class));
					break;

				}
				
			}
		});
		return convertView;
	}

	class Viewhoder {
		LinearLayout ishide;
		TextView title;
		TextView che;
		TextView tel;
		TextView time;
		TextView name;
		TextView zhanbai;
	}
}
