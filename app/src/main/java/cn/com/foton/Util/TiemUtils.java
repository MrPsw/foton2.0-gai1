package cn.com.foton.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TiemUtils {
	/**��ȡϵͳʱ��
	 * 
	 * @return
	 */
public static String  getTiem(){
	SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm");       
	Date    curDate    =   new    Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
	String    Tiem    =    formatter.format(curDate);  	
	return Tiem ;
}
/**
 * ���������
 * @return
 */
public static String  getTiemYMD(String tiem){
	 String Tiem=tiem.replace(" ","'");
	 String[] TiemS = Tiem.split("'");
 	Tiem=TiemS[0];
	return Tiem ;
}
/**
 * ���������
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
		Date date = new Date();// �½���ʱ�ĵ�ϵͳʱ��

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +num);//�����ʱ���num��
		date = calendar.getTime();
		String    Tiem    =    formatter.format(date);

		return Tiem;
	}

	public static String addDate(String tiem ,int day )  {
		String tiemt = null;
		try{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // ���ڸ�ʽ
		Date date = dateFormat.parse(tiem); // ָ������



		long time = date.getTime(); // �õ�ָ�����ڵĺ�����
		day = day*24*60*60*1000; // Ҫ���ϵ�����ת���ɺ�����
		time+=day; // ��ӵõ��µĺ�����
			Date newDate = new Date(time); // ָ�����ڼ���day��
			tiemt=dateFormat.format(newDate);
			System.out.println(dateFormat.format(newDate));

		}catch (Exception E){};

		return tiemt; // ��������ת��������
	}
}
