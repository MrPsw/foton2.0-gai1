package cn.com.foton.Dbhelper;

import org.json.JSONObject;

public class jsonT {
 public static String Getjson(String jsonString){
	 if(jsonString.contains("success:true")){
			
			jsonString=jsonString.replace("{success:true, msg:'","{\"success\":true,\"msg\":");
			
			jsonString=jsonString.replace("'","");
			
			return jsonString;
		}
	 
	 
	 return null;
	 
 }
 /**
  * ��ʽ��Ϊ��������������
  * @param jsonString
  * @return
  */
 public static String GetjsonAll(String jsonString){
	 if(jsonString.contains("success:true")){
			jsonString=jsonString.replace("'","");
			jsonString=jsonString.replace("success:true","\"success\":true");
			jsonString=jsonString.replace("total", "\"total\"");
			jsonString=jsonString.replace("msg", "\"msg\"");
		
			
			return jsonString;
		}
	 

	 return null;
	 
 }
 /**
  * jsonstring���Ͷ�����
  */
 public static String GetjsonAlls(String jsonString){
	 if(jsonString.contains("success:true")){
			
		 String[] st = jsonString.split("msg:'");
		 	jsonString=st[1];
		 	jsonString=jsonString.replace("'}","");
			return jsonString;
		}
	 

	 return null;
	 
 }
 
}
