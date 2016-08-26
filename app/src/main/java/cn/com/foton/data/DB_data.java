		package cn.com.foton.data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import cn.com.foton.Dbhelper.DatabaseUtil;
import cn.com.foton.Dbhelper.Gsonbaen;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.Dbhelper.jsonbaen;
import cn.com.foton.Util.LogUtil;
import cn.com.foton.base.gradeBase;
 
import cn.com.foton.base.gradeBase.msgs;

public class DB_data {
	public static DatabaseUtil dbutils;

	public DB_data(Context c) {
		// TODO Auto-generated constructor stub
		dbutils = new DatabaseUtil(c);
		dbutils.open();
		//省市县
		String a[]={"40431001","40431002","40431003","40431004"};
		String tables[]={"table1","table2","table3","table4"};
		
		for (int j = 0; j < a.length; j++) {
			init(a[j],tables[j]);
		}
		//新建线索下拉数据
		String a2[]={"1107","1102","8018","1115","1106"};
		String students[]=static_data.STUDENT;
		
		for (int j = 0; j < a2.length; j++) {
			insterB(a2[j],students[j],"1100");
		}

	}
	private void init(final String str,final String tablename) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				// 创建对象
				OkHttpClient okhttp = new OkHttpClient();
				// new call
			
				RequestBody body = new FormEncodingBuilder()

					.add("fcmCode.codeType",str)
					.add("fcmCode.isAddress","true").
						build();
				Request request = new Request.Builder()
			            .url(App_url.URL)
			            .post(body)
			            .build();
			okhttp.newCall(request).enqueue(new Callback() {
				
				@Override
				public void onResponse(Response response) throws IOException {
					// TODO Auto-generated method stub
					try {
					
						String jsonString = response.body().string();
						//System.out.println(jsonString);
						String jsonstr=jsonT.Getjson(jsonString);
						if(jsonstr==null){
							return ;
						}
						
						if(tablename.contains("table3")){
							LogUtil.e(tablename, jsonString);
						}
						Gson gson=new Gson();
						Gsonbaen gs=gson.fromJson(jsonstr, Gsonbaen.class);
						 ArrayList<jsonbaen> msg = gs.msg;
					
						//System.out.println(msg.size());
						
						
							insterSQLlite(msg,tablename);	
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				
				@Override
				public void onFailure(Request arg0, IOException arg1) {
					// TODO Auto-generated method stub
					
				}
			});
//				Call call = okhttp.newCall(request);
//				
//				try {
//					Response response = call.execute();
//					String jsonString = response.body().string();
//					
//					//System.out.println(jsonString);
//					String jsonstr=jsonT.Getjson(jsonString);
//					Gson gson=new Gson();
//					Gsonbaen gs=gson.fromJson(jsonstr, Gsonbaen.class);
//					 ArrayList<jsonbaen> msg = gs.msg;
//				
//					System.out.println(msg.size());
//					
//					
//						insterSQLlite(msg,tablename);	
//					
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
			}

			private void insterSQLlite(ArrayList<jsonbaen> msg,String tablename) {
				
				
				boolean b = dbutils.tabbleIsExist(tablename);
				if (b!=true) {
					
				
				dbutils.createExec("create table "+tablename+"(_id integer primary key autoincrement,"
						+ "name varchar(20),code varchar(11),upcode varchar(12))");
				for (int j = 0; j < msg.size(); j++) {
				
					jsonbaen ss = msg.get(j);
					
					
					dbutils.createStudent(tablename, new String[]{"_id","upcode","code","name"},new String[]{null,ss.upCode,ss.getCode(),ss.getCodeName()});
				
				}//for
				}//if
			}
			
			
		}).start();
}
	/**
	 * 省市县三级
	 * @param table1 
	 * @param code
	 * @param table2
	 * @return
	 */
	public static Cursor getDB(String table1,String code,String table2){
		
	String sql="select "+table2+".name,"+table2+".code from "+table1+","+table2+" where "+table1+".code="+table2+".upcode and "+table1+".code="+code+"";
		
		//Cursor c = dbutils.rawQuery("select "+table2+".name from "+table1+","+table2+"where "+table1+".code="+table2+".upcode and "+table1+".code="+code+"",null);
	 Cursor c = dbutils.rawQuery(sql,null);
		return c;
		
	};
	
	public static Cursor getstudnt(String table1){
		String sql="select "+table1+".code,"+table1+".codeName from "+table1+"";
			
		 Cursor c = null;
		try {
				
			
		
		 c= dbutils.rawQuery(sql,null);
		 	
			} catch (Exception e) {
				// TODO: handle exception
			}
			return c;
			
		};
		
		/**
		 * 未分配线索查询
		 * @param table1  表名
		 * @param back  返回字段
		 * @param key  列名
		 * @param name  值
		 * @return
		 */
		public static String getcodename(String table1,String back,String key ,String name){
			String sql="select "+table1+"."+back+" from "+table1+"  where "+key+"="+name  ;
				
			String s = "";
			 Cursor c =null;
			try {
				
			
			 c = dbutils.rawQuery(sql,null);
			
			 if(c.getCount()>0){
			 
			while (c.moveToNext()) {
			s=	c.getString(0);
			
			}
			c.close();
			return s;
			
			 }
			} catch (Exception e) {
				// TODO: handle exception
			}
			return s;
				
			};
	public static void insterB(final String str,final String studentname,final String FcmCompanyCode){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				OkHttpClient client=new OkHttpClient();
				RequestBody body=new FormEncodingBuilder()
						.add("fcmCode.codeType",str)
						.add("fcmCode.isAddress", "false")
						.add("fcmCode.companyCode",FcmCompanyCode).build();
				
				Request request=new Request.Builder()
						.url(App_url.URL)
						.post(body).build();
				Call call=client.newCall(request);
				try {
					Response reaponse=call.execute();
					String 	s=reaponse.body().string();
					
					if(studentname.equals("Stuent2")){
						LogUtil.e(this,s);
					}
					if(studentname.equals("Stuent3")){
						LogUtil.e(this,s);
					}
					
					String json=jsonT.Getjson(s);
					if(json==null){
						return ;
					}
					Gson gson=new Gson();
					gradeBase gb = gson.fromJson(json, gradeBase.class);
					ArrayList<msgs> msg = gb.msg;
					//System.out.println(msg.get(0).codeName+"");
					
			
					insterstudent(msg,studentname);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	};
	public static void insterstudent(ArrayList<msgs> msg,String tablename) {
		
		if(tablename=="Stuent3"){
			DeleteTable("Stuent3");	
		}
		if(tablename=="Stuent2"){
			DeleteTable("Stuent2");	
		}
		if(tablename=="Stuent5"){
			DeleteTable("Stuent5");	
		}
		boolean b = dbutils.tabbleIsExist(tablename);
		if (b!=true) {
			
		
		dbutils.createExec("create table "+tablename+"(_id integer primary key autoincrement,"
				+ "code varchar(20),codeName varchar(11))");
		for (int j = 0; j < msg.size(); j++) {
		
			msgs ss = msg.get(j);
			
			
			dbutils.createStudent(tablename, new String[]{"_id","code","codeName"},new String[]{null,ss.code,ss.codeName});
		
		}//for
		}//if
	}
	
	public static void DeleteTable(String name){
		
		
		
			try {
				dbutils.createExec("DROP TABLE "+name);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
	
	}
	
}