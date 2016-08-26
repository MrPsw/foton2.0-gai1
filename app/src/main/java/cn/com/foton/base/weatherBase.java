package cn.com.foton.base;

public class weatherBase {
public 	result result;
public class result{
	public data data;
	public class data{
public 	realtime realtime;
	
	public class realtime{
		public String city_code;
		public String city_name;
		public String date;
		public String time;
		public int week;
		public String moon;
		public String dataUptime;
		public weather weather;
		public class weather{
			public String temperature;
			public String humidity;
			public String info;
			public String  img;
		}
		}
	}
	
}



}
