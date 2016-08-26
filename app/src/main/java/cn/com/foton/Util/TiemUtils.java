package cn.com.foton.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TiemUtils {
	/**获取系统时间
	 * 
	 * @return
	 */
public static String  getTiem(){
	SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm");       
	Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
	String    Tiem    =    formatter.format(curDate);  	
	return Tiem ;
}
/**
 * 获得年月日
 * @return
 */
public static String  getTiemYMD(String tiem){
	 String Tiem=tiem.replace(" ","'");
	 String[] TiemS = Tiem.split("'");
 	Tiem=TiemS[0];
	return Tiem ;
}
/**
 * 获得年月日
 * @return
 */
public static String  getTiemYM_(String tiem){
	String tiemt;
	 String[] TiemS = tiem.split("-");
	 
 	int iem=Integer.parseInt(TiemS[1]);
 	
 	if(iem<9){
 		iem=iem+1;
 		tiemt=TiemS[0]+"-0"+iem;
 	}else{
		iem=iem+1;
 		if(iem>12){
 			iem=iem%12;
 			tiemt=TiemS[0]+"-0"+iem;
 		}
 
 		tiemt=TiemS[0]+"-"+iem;	
 	}
// 	if(iem>10){
// 		iem=iem-1;
// 		tiemt=TiemS[0]+"-"+iem;
// 	}else{
// 		iem=iem-1;
// 		tiemt=TiemS[0]+"-0"+iem;
// 	}
	return tiemt ;
}





	public static String getNextDay(int num) {
		num=num-1;
		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm");
		Date date = new Date();// 新建此时的的系统时间

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +num);//今天的时间加num天
		date = calendar.getTime();
		String    Tiem    =    formatter.format(date);

		return Tiem;
	}

	public static String addDate(String tiem ,int day )  {
		String tiemt = null;
		try{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 日期格式
		Date date = dateFormat.parse(tiem); // 指定日期



		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time+=day; // 相加得到新的毫秒数
			Date newDate = new Date(time); // 指定日期加上day天
			tiemt=dateFormat.format(newDate);
			System.out.println(dateFormat.format(newDate));

		}catch (Exception E){};

		return tiemt; // 将毫秒数转换成日期
	}
}
