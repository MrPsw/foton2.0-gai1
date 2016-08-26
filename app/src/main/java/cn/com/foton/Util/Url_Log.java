package cn.com.foton.Util;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

public class Url_Log {
	
		public static void urlString(String body){
		
			body.replace(".add(\"","&");
			body.replace(",","=");
			body.replace(")","");
			
		
		};


}
