package cn.com.foton.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.foton.R;
import cn.com.foton.base.GridviewBase;
import cn.com.foton.base.fragment_base;


public class App_data {
	private static ArrayList<Map<String, Object>> data_list;
	public static int vpimages[] = { R.drawable.zhu_3, R.drawable.zhu_4,
			R.drawable.zhu_5 };

	public static List<Map<String, Object>> getData() {
		int images[] = { R.drawable.yuan1, R.drawable.yuan2, R.drawable.yuan3,
				R.drawable.yuan4, R.drawable.yuan5, R.drawable.yuan6,
				R.drawable.yuan7, R.drawable.yuan8 };
		String functions[] = { "未分配线索", "未跟进线索", "超时未跟进线索", "待办任务",
				"全部线索查询", "战败审核", "报表分析", "集客线索创建" };
		String datas[] = { "1", "32", "24", "23", "7", "21", "", "" };
		data_list = new ArrayList<Map<String, Object>>();
		// cion和iconName的长度是相同的，这里任选其一都可以
		for (int i = 0; i < images.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", images[i]);
			map.put("text", functions[i]);
			map.put("text2", datas[i]);
			map.put("image2", "");

			data_list.add(map);
		}

		return data_list;
	}
	//
	public static List<GridviewBase> geGridViewData(String TEXTS[]){
		int images[] = { R.drawable.yuan1, R.drawable.yuan2, R.drawable.yuan3,
				R.drawable.yuan4, R.drawable.yuan5, R.drawable.yuan6,
				R.drawable.yuan7, R.drawable.yuan8 };
		String functions[] = { "未分配线索", "待办任务","超时未跟进线索","今日跟进线索",
				"全部线索查询", "本月累计闭环线索", "报表分析", "集客线索创建" };
		String datas[] = { "1", "32", "24", "23", "7", "21", "", "" };
		
		List<GridviewBase> list=new ArrayList<>();
		for (int i = 0; i < images.length; i++) {
			GridviewBase gb=new GridviewBase();
			gb.setBj(images[i]);
			gb.setNumber(TEXTS[i]);
			gb.setText(functions[i]);
			list.add(gb);
		}
		
		
		return list;
		
	}
	public static List<GridviewBase> geGridViewData2(String TEXTS[]){
		int images[] = { R.drawable.yuan1, R.drawable.yuan2, R.drawable.yuan3,
				R.drawable.yuan4,R.drawable.yuan8, R.drawable.yuan5, R.drawable.yuan6
				 };
		String functions[] = {  "待办任务","超时未跟进线索","今日跟进线索",
				"全部线索查询", "本月累计闭环线索", "报表分析", "集客线索创建"  };
		
		
		List<GridviewBase> list=new ArrayList<>();
		for (int i = 0; i < images.length; i++) {
			GridviewBase gb=new GridviewBase();
			gb.setBj(images[i]);
			gb.setNumber(TEXTS[i]);
			gb.setText(functions[i]);
			list.add(gb);
		}
		
		
		return list;
		
	}
	
	public static List<Map<String, Object>> getDatas() {
		int images[] = { R.drawable.yuan1, R.drawable.yuan2, R.drawable.yuan3,
				R.drawable.yuan4,
				R.drawable.yuan7, R.drawable.yuan8 };
		String functions[] = { "未跟进线索", "超时未跟进线索", "待办任务",
				"全部线索查询",  "报表分析", "集客线索创建" };
		String datas[] = { "0", "0", "24", "23", "", "" };
		data_list = new ArrayList<Map<String, Object>>();
		// cion和iconName的长度是相同的，这里任选其一都可以
		for (int i = 0; i < images.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", images[i]);
			map.put("text", functions[i]);
			map.put("text2", datas[i]);
			map.put("image2", "");

			data_list.add(map);
		}

		return data_list;
	}

	
	static String name[]={"线索编号：","客户姓名：","性别：","联系电话：","客户城市：","地址：","时间：","意向车型：","意向购车时间：","线索等级：","线索来源：","QQ:","微信：","备注：",};
	static String txt[]={"111","张三","男","1332432424","上海","闵行区莘庄7239弄","2016—5—23 16:23","莎瓦娜","一周内","F","外部媒体","24545242","zxwe2324","无",};
	
	
	//详细信息
	public static List<fragment_base> getparticular(){
		
		List<fragment_base> list=new ArrayList<>();
		for (int i = 0; i < name.length; i++) {
			fragment_base f=new fragment_base();
			f.setName(name[i]);
			f.setText(txt[i]);
			list.add(f);
		}
		
		
		
		
		return list;
	}
	static String name1[]={"线索编号：","客户姓名：","联系电话：","时间：","意向车型：","意向购车时间：","已分配至：","战败原因："};
	static String txt1[]={"111","张三","1332432424","2016—5—23 16:23","莎瓦娜","一周内","王五","周围人影响"};
	//详细信息
	public static List<fragment_base> getfollowupList(){
		
		List<fragment_base> list=new ArrayList<>();
		for (int i = 0; i < name1.length; i++) {
			fragment_base f=new fragment_base();
			f.setName(name1[i]);
			f.setText(txt1[i]);
			list.add(f);
		}
		return list;
	}
}
