package cn.com.foton.Util;

public class StringUtils {

	public static String getData(String Tiem){
		String[] tiems=Tiem.split("\\.");
		String tiem=tiems[0];
		
		return tiem;
	}
	
	

}
