package cn.com.foton.Will;

import cn.com.foton.data.DB_data;

public class XSutils {
private static String xiansuo2=null;

public static String GetTiem(String st5){
	switch (st5) {
	case "11061006":
	
		xiansuo2=DB_data.getcodename("Stuent2","codeName","code","'11021005'");
		
		 
	
		break;
	case "11061007":

		xiansuo2 = DB_data.getcodename("Stuent2","codeName","code","'11021006'");


		break;
	case "11061008":

		xiansuo2 = DB_data.getcodename("Stuent2","codeName","code","'11021007'");
	
		
		break;
	case "11061009":

		xiansuo2 = DB_data.getcodename("Stuent2","codeName","code","'11021011'");

	
		break;
	case "11061010":
	
		xiansuo2 = DB_data.getcodename("Stuent2","codeName","code","'11021009'");
		
	
		break;
	}
	return xiansuo2;
}
}
