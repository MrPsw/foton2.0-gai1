package cn.com.foton.data;

import java.util.HashMap;
import java.util.Map;

import cn.com.foton.R;



public class weatherData {
public static Map<String,Integer>  getImage(){
	String weatherkey[]={"��","����","��","����","������","��������б���","���ѩ","С��","����","����","����","����","�ش���"
			,"��ѩ","Сѩ","��ѩ","��ѩ","��ѩ","��","����","ɳ����","С��-����","����-����","����-����","����-����","����-�ش���"
			,"Сѩ-��ѩ","��ѩ-��ѩ","��ѩ-��ѩ","����","��ɳ","ǿɳ����","��"
		};

	int  images[]={R.drawable.d00,
			R.drawable.d01,
			R.drawable.d02,
			R.drawable.d03,
			R.drawable.d04,
			R.drawable.d05,
			R.drawable.d06,
			R.drawable.d07,
			R.drawable.d08,
			R.drawable.d09,
			R.drawable.d10,
			R.drawable.d11,
			R.drawable.d12,
			R.drawable.d13,
			R.drawable.d14,
			R.drawable.d15,
			R.drawable.d16,
			R.drawable.d17,
			R.drawable.d18,
			R.drawable.d19,
			R.drawable.d20,
			R.drawable.d21,
			R.drawable.d22,
			R.drawable.d23,
			R.drawable.d24,
			R.drawable.d25,
			R.drawable.d26,
			R.drawable.d27,
			R.drawable.d28,
			R.drawable.d29,
			R.drawable.d30,
			R.drawable.d31,
			R.drawable.d53,
		};

	Map<String,Integer> weatherData=new HashMap<>();	
	for (int i = 0; i < weatherkey.length; i++) {
		weatherData.put(weatherkey[i],images[i]);
	}
	return weatherData;

}


}
