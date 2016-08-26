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
		String functions[] = { "δ��������", "δ��������", "��ʱδ��������", "��������",
				"ȫ��������ѯ", "ս�����", "�������", "������������" };
		String datas[] = { "1", "32", "24", "23", "7", "21", "", "" };
		data_list = new ArrayList<Map<String, Object>>();
		// cion��iconName�ĳ�������ͬ�ģ�������ѡ��һ������
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
		String functions[] = { "δ��������", "��������","��ʱδ��������","���ո�������",
				"ȫ��������ѯ", "�����ۼƱջ�����", "�������", "������������" };
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
		String functions[] = {  "��������","��ʱδ��������","���ո�������",
				"ȫ��������ѯ", "�����ۼƱջ�����", "�������", "������������"  };
		
		
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
		String functions[] = { "δ��������", "��ʱδ��������", "��������",
				"ȫ��������ѯ",  "�������", "������������" };
		String datas[] = { "0", "0", "24", "23", "", "" };
		data_list = new ArrayList<Map<String, Object>>();
		// cion��iconName�ĳ�������ͬ�ģ�������ѡ��һ������
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

	
	static String name[]={"������ţ�","�ͻ�������","�Ա�","��ϵ�绰��","�ͻ����У�","��ַ��","ʱ�䣺","�����ͣ�","���򹺳�ʱ�䣺","�����ȼ���","������Դ��","QQ:","΢�ţ�","��ע��",};
	static String txt[]={"111","����","��","1332432424","�Ϻ�","������ݷׯ7239Ū","2016��5��23 16:23","ɯ����","һ����","F","�ⲿý��","24545242","zxwe2324","��",};
	
	
	//��ϸ��Ϣ
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
	static String name1[]={"������ţ�","�ͻ�������","��ϵ�绰��","ʱ�䣺","�����ͣ�","���򹺳�ʱ�䣺","�ѷ�������","ս��ԭ��"};
	static String txt1[]={"111","����","1332432424","2016��5��23 16:23","ɯ����","һ����","����","��Χ��Ӱ��"};
	//��ϸ��Ϣ
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
