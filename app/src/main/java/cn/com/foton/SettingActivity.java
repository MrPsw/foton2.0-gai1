package cn.com.foton;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.foton.Activity.MarketActivity;
import cn.com.foton.jpush.MsmListActivity;

public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		// …Ë÷√Title
				TextView tv = (TextView) findViewById(R.id.action_text);
				ImageView actionimg = (ImageView) findViewById(R.id.action2_image);
				Button tuichu = (Button)findViewById(R.id.tuichu);
				
				
				
				tv.setText("…Ë÷√");
				actionimg.setImageResource(R.drawable.back);
				actionimg.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
				tuichu.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						JPush.initJPush(SettingActivity.this,"");
						Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
						startActivity(intent);
						finish();
					
					
					}
				});
				
				
	}
	public void msm(View v){
		startActivity(new Intent(SettingActivity.this,MsmListActivity.class));
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}
	
}
