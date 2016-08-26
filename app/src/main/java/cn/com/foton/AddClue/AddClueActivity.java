package cn.com.foton.AddClue;

import android.support.v7.app.ActionBarActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.LoginActivity;
import cn.com.foton.R;
import cn.com.foton.Activity.MainActivity;
import cn.com.foton.Dbhelper.DatabaseUtil;
import cn.com.foton.Dialog.IOSdialog;
import cn.com.foton.JPushBase.allotBase;
import cn.com.foton.Util.AddJPush;
import cn.com.foton.Util.CustomToast;
import cn.com.foton.Util.FailtJPush2;
import cn.com.foton.Util.JPushHttp;
import cn.com.foton.Util.MyversionUtils;
import cn.com.foton.Util.TiemUtils;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Wait.WaitActivity;
import cn.com.foton.Will.Plan_Activity;
import cn.com.foton.Will.Will_tab1_Activity;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;
import cn.com.foton.data.DB_data;
import cn.com.foton.data.static_data;

public class AddClueActivity extends Activity implements OnClickListener {

	private ArrayList<String> list, list2, list3, Codename1, Codename2, Codename3, Codename4, Codename5;
	private ArrayList<String> listcode, listcode2, listcode3, code1, code2, code3, code4, code5;
	private Spinner sp, sp21, sp22, sp23, sp71, sp81, sp91;
	TextView xiansuo;
	private String[] students;
	private EditText et1, et2, et3, et5, et6, et7;
	String st1, st2, st3, st4, st5, st6, st7, st8;//
	String ets[];// 输入框
	private IOSdialog dialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_clue);
		

		ImageView image = (ImageView) findViewById(R.id.action2_image);
		image.setImageResource(R.drawable.back);
		image.setOnClickListener(this);
		TextView TV = (TextView) findViewById(R.id.action_text);
		TV.setText("潜客线索登记");
		ets = new String[6];

		sp = (Spinner) findViewById(R.id.spinner1);
		sp21 = (Spinner) findViewById(R.id.spinner21);
		sp22 = (Spinner) findViewById(R.id.spinner22);
		sp23 = (Spinner) findViewById(R.id.spinner23);
		sp71 = (Spinner) findViewById(R.id.spinner71);
		sp81 = (Spinner) findViewById(R.id.spinner81);
		sp91 = (Spinner) findViewById(R.id.spinner91);
		xiansuo = (TextView) findViewById(R.id.xiansuo);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText3);
		et3 = (EditText) findViewById(R.id.editText5);
		// et4 = (EditText) findViewById(R.id.editText6);
		et5 = (EditText) findViewById(R.id.editText11);
		et6 = (EditText) findViewById(R.id.editText12);
		et7 = (EditText) findViewById(R.id.editText13);
		Button bt = (Button) findViewById(R.id.bt_fp);
		bt.setText("保存");
		bt.setOnClickListener(this);

		students = static_data.STUDENT;

		Getstudent1(students[0]);
		Getstudent2(students[1]);
		Getstudent3(students[2]);
		Getstudent4(students[3]);
		Getstudent5(students[4]);
		setSpinner(sp, Codename1);
		setSpinner(sp71, Codename2);
		setSpinner(sp81, Codename3);
		setSpinner(sp91, Codename4);

		Getdblist("table1", "10000046", "table2");
		setSpinner(sp21, list);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(AddClueActivity.this, Codename1.get(position)
				// + "" + code1.get(position), 0).show();
				st1 = code1.get(position) + "";

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		sp21.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(AddClueActivity.this, list.get(position) + ""
				// + listcode.get(position), 0).show();
				Getdblist2("table2", listcode.get(position) + "", "table3");
				setSpinner(sp22, list2);
				st2 = listcode.get(position) + "";
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		sp22.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(AddClueActivity.this, list2.get(position) + ""
				// + listcode2.get(position), 0).show();
				Getdblist3("table3", listcode2.get(position) + "", "table4");
				setSpinner(sp23, list3);
				st3 = "" + listcode2.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		sp23.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(AddClueActivity.this, list3.get(position) + ""
				// + listcode3.get(position), 0).show();
				st4 = listcode3.get(position) + "";
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		String xiansuoT;
		sp71.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(AddClueActivity.this, Codename2.get(position)
				// + "" + code2.get(position), 0).show();
				st5 = code2.get(position) + "";

				String xiansuo2;
				switch (st5) {
				case "11021015":
					xiansuo.setText("H");
					xiansuo2=DB_data.getcodename("Stuent5","code","codeName","'H'");
					
					setString(xiansuo2);
				
					break;
				case "11021016":
					xiansuo.setText("A");
				
					xiansuo2 = DB_data.getcodename("Stuent5","code","codeName","'A'");
					setString(xiansuo2);

					break;
				case "11021017":
					xiansuo.setText("B");
					xiansuo2 = DB_data.getcodename("Stuent5","code","codeName","'B'");
				
					setString(xiansuo2);
					break;
				case "11021018":
					xiansuo.setText("C");
					xiansuo2 = DB_data.getcodename("Stuent5","code","codeName","'C'");
			
					setString(xiansuo2);
					break;
				case "11021019":
					xiansuo.setText("N");
					xiansuo2 = DB_data.getcodename("Stuent5","code","codeName","'N'");
				
					setString(xiansuo2);
					break;
				}
			

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		sp81.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(AddClueActivity.this, Codename3.get(position)
				// + "" + code3.get(position), 0).show();
				st6 = code3.get(position) + "";
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		sp91.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(AddClueActivity.this, Codename4.get(position)
				// + "" + code4.get(position), 0).show();
				st7 = code4.get(position) + "";
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void Getdblist(String table1, String code, String table2) {
		Cursor c = DB_data.getDB(table1, code, table2);

		listcode = new ArrayList<>();
		list = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			list.add(f1);
			listcode.add(f2);
		}
	}

	private void Getdblist2(String table1, String code, String table2) {
		Cursor c = DB_data.getDB(table1, code, table2);

		listcode2 = new ArrayList<>();
		list2 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			list2.add(f1);
			listcode2.add(f2);
		}
	}

	private void Getdblist3(String table1, String code, String table2) {
		Cursor c = DB_data.getDB(table1, code, table2);

		listcode3 = new ArrayList<>();
		list3 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			list3.add(f1);
			listcode3.add(f2);
		}
	}

	private void setSpinner(Spinner sp, ArrayList<String> list) {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		// 设置样式
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.action2_image:
			finish();
			break;

		case R.id.bt_fp:

			submit();
			break;
		}
	}
	
	private void Getstudent1(String table) {
		Cursor c = DB_data.getstudnt(table);

		Codename1 = new ArrayList<>();
		code1 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			Codename1.add(f2);
			code1.add(f1);
		}
		c.close();
	}

	private void Getstudent2(String table) {
		Cursor c = DB_data.getstudnt(table);

		Codename2 = new ArrayList<>();
		code2 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			Codename2.add(f2);
			code2.add(f1);
		}
		c.close();
	}

	private void Getstudent3(String table) {
		Cursor c = DB_data.getstudnt(table);

		Codename3 = new ArrayList<>();
		code3 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			Codename3.add(f2);
			code3.add(f1);
		}
		c.close();
	}

	private void Getstudent4(String table) {
		Cursor c = DB_data.getstudnt(table);

		Codename4 = new ArrayList<>();
		code4 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			Codename4.add(f2);
			code4.add(f1);
		}
		c.close();
	}

	private void Getstudent5(String table) {
		Cursor c = DB_data.getstudnt(table);

		Codename5 = new ArrayList<>();
		code5 = new ArrayList<>();
		while (c.moveToNext()) {
			String f1 = c.getString(0);
			String f2 = c.getString(1);
			Codename5.add(f2);
			code5.add(f1);
		}
		c.close();
	}

	boolean b = true;
	private String fcmuserid;
	private String fcmpositionid;
	private String fcmcompanycode;
	private String fcmdealercode;
	
	public void setString(String string){
		st8=string;
	}

	public void submit() {
		b=true;
		ets[0] = et1.getText().toString().trim();
		ets[1] = et2.getText().toString().trim();
		ets[2] = et3.getText().toString().trim();
		// ets[3]=et4.getText().toString().trim();
		ets[3] = et5.getText().toString().trim();
		ets[4] = et6.getText().toString().trim();
		ets[5] = et7.getText().toString().trim();
		String toast[]={"请填写姓名","请填写电话","请填写详细地址"};
		for (int i = 0; i <2; i++) {
			if (ets[i].equals("")) {
				cn.com.foton.Util.Toast.show(AddClueActivity.this, toast[i], 3000);
			
				b = false;
				return ;
			} else {

			}
		}
		SharedPreferences settings = getSharedPreferences(static_data.SETTING,
				Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE); // 获取一个
																				// SharedPreferences
																				// 对象
		fcmuserid = settings.getString(static_data.FCMUSERID, "");
		fcmpositionid = settings.getString(static_data.FCMPOSITIONID, "");
		fcmcompanycode = settings.getString(static_data.FCMCOMPANYCODE, "");
		fcmdealercode = settings.getString(static_data.FCMDEALERCODE, "");

		if (b) {
			dialog=new IOSdialog(AddClueActivity.this, "获取数据");
			
			
			HttpPost();
		} 

	}
	private CustomToast customToast;
	private void HttpPost() {
		
		new Thread(new Runnable() {

			

		

		
			public void run() {
				
				OkHttpClient okhttp = new OkHttpClient();
				RequestBody body = new FormEncodingBuilder()
						.add("fcmCustomer.fcmCustomerName", ets[0])
						.add("fcmCustomer.fcmCustomerSex", st1)
						.add("fcmCustomer.fcmCustomerMobile", ets[1])
						.add("fcmCustomer.fcmProvinceCode", st2)
						.add("fcmCustomer.fcmCityCode", st3)
						.add("fcmCustomer.fcmTownCode", st4)
						.add("fcmCustomer.fcmAddress", ets[2])
						.add("fcmCustomer.fcmCustomerCreateDate",TiemUtils.getTiem())
						.add("fcmCustomer.fcmCustomerLevel", st8)
						.add("fcmCustomer.fcmChangeType", st5)
						.add("fcmCustomer.fcmModelSeries", st6)
						.add("fcmCustomer.fcmInfoWay", st7)
						.add("fcmCustomer.fcmCustomerQQ", ets[3])
						.add("fcmCustomer.fcmCustomerWechat", ets[4])
						.add("fcmCustomer.fcmCustomerNote", ets[5])
						.add("fcmCustomer.fcmUserId", fcmuserid)
						.add("fcmCustomer.fcmPositionId", fcmpositionid)
						.add("fcmCustomer.fcmCompanyCode", fcmcompanycode)
						.add("fcmCustomer.fcmDealerCode", fcmdealercode)
						.add("fcmCustomer.fcmClassification", "1103")
						.add("fcmCustomer.fcmCategoryProperty", "1105")
						.add("fcmCustomer.fcmSellPolicyType", "8039")
						.add("fcmCustomer.fcmVocation", "8019")
						.add("fcmCustomer.fcmVehicleScale", "123")
						.add("fcmCustomer.fcmIndustry", "1112").build();
				Request request = new Request.Builder().url(App_url.URL2).post(body).build();

				Call call = okhttp.newCall(request);
				try {
					
		
					Response response = call.execute();
					String jsonString = response.body().string();
					dialog.close();
					System.out.println(jsonString+"");
					if (jsonString.contains("success:true")) {
			
						//addjpush();
						Message msg=new Message();
						msg.what=1;
						msg.obj=jsonString;
						h.sendMessage(msg);		
					
						System.out.println(jsonString + "提交结果");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			}

//			private void addjpush() {
//				addBase base=new addBase();
//				//base.CustomerId
//				base.CustomerName=ets[0];
//				base.CustomerSex=st1;
//				base.CustomerMobile=ets[1];
//				base.ProvinceCode=st2;
//				base.CityCode=st3;
//				base.TownCode=st4;
//				base.Address=ets[2];
//				base.CustomerCreateDate=TiemUtils.getTiem();
//				base.CustomerLevel=st8;
//				base.ChangeType=st5;
//				base.ModelSeries=st6;
//				base.InfoWay=st7;
//				base.CustomerQQ=ets[3];
//				base.CustomerWechat= ets[4];
//				base.CustomerNote= ets[5];
//				base.UserId=fcmuserid;
//				base.PositionId=fcmpositionid;
//				base.CompanyCode=fcmcompanycode;
//				base.DealerCode=fcmdealercode;
//		
//			
//				try {
//				
//				
//					allotBase allotbase = new allotBase();
//					allotbase.CustomerId="";
//					allotbase.BusinessId="";
//					allotbase.FromUserId=user.getFcmUserId();
//					allotbase.ToUserId="513871739021252058";
//					allotbase.CustomerName=base.CustomerName;
//					allotbase.Phone=base.CustomerMobile;
//					allotbase.CarType=base.ModelSeries;
//					allotbase.BuyTime=base.ChangeType;
//					allotbase.FailReason="";
//					AddJPush.allothttp(AddClueActivity.this, allotbase,h);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
			
		}).start();
		
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	
	}
Handler h=new Handler(){
	public void handleMessage(android.os.Message msg) {
		userBase user = UserUtils.getUserBase(AddClueActivity.this);

		allotBase allotbase = new allotBase();
		allotbase.CustomerId="";
		allotbase.BusinessId="";
		allotbase.FromUserId=user.getFcmUserId();
		allotbase.ToUserId=user.getFcmUpUserId();
		allotbase.CustomerName= ets[0];
		allotbase.Phone= ets[1];
		allotbase.CarType=st6;
		allotbase.BuyTime= st5;
		allotbase.FailReason="";
		AddJPush.allothttp(AddClueActivity.this, allotbase);

		finish();
	};
};
	
	
}
