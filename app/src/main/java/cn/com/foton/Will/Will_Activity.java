package cn.com.foton.Will;



import cn.com.foton.LoginActivity;
import cn.com.foton.R;
import cn.com.foton.R.id;
import cn.com.foton.R.layout;
import cn.com.foton.R.menu;
import cn.com.foton.Activity.MainActivity;
import cn.com.foton.Activity.MarketActivity;
import cn.com.foton.Util.SharedPreferencesUtils;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
/**
 * 待办任务
 * @author Administrator
 *
 */
public class Will_Activity extends TabActivity {
	

	private Intent intent0, intent1;
	private TabHost tabhost;
	Intent Intent[] = { intent0, intent1 };
	private String[] tabMenu = { "今日待办", "全部待办" };
	private TabHost.TabSpec tabSpec0, tabSpec1;
	private TabHost.TabSpec[] tabSpecs = { tabSpec0, tabSpec1 };
	private TextView tv1;
	private TextView tv2;
	private TextView[] ts;
	private ListView[] lvs;
	Class intents[]={Will_tab1_Activity.class,Wil_tab2_Activity.class};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_will_);
		Init();
		button();
	}

	private void button() {
		// TODO Auto-generated method stub
		//设置Title
		TextView tv=(TextView) findViewById(R.id.action_text);
		ImageView actionimg=(ImageView) findViewById(R.id.action2_image);
		tv.setText("待办任务");
		actionimg.setImageResource(R.drawable.back);
		
		actionimg.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String uesrid=SharedPreferencesUtils.querysharep2(Will_Activity.this, "userid");
			if(uesrid.equals("1")){
				Intent i2=new Intent(Will_Activity.this, MarketActivity.class);
				startActivity(i2);
				finish();
			}else if(uesrid.equals("2")){
				Intent i2=new Intent(Will_Activity.this, MainActivity.class);
				startActivity(i2);
				finish();
			}
	
		}
	});
	}
	private void Init() {
		tv1 = (TextView) findViewById(R.id.t1);
		tv2 = (TextView) findViewById(R.id.t2);
		ts = new TextView[] {tv1,tv2};
		
		
		
		
		//从TabActivity上面获取放置Tab的TabHost
        tabhost = getTabHost();
		
		for (int i = 0; i < tabSpecs.length; i++) {
			Intent[i]=new Intent();
			Intent[i].setClass(this,intents[i]); 
			tabSpecs[i] = tabhost.newTabSpec(tabMenu[i]); 
            tabSpecs[i].setIndicator(tabMenu[i]);// 设置文字  
            tabSpecs[i].setContent(Intent[i]);
            tabhost.addTab(tabSpecs[i]);// 将该页的内容添加到Tabhost  
		}
		
		   updateTab(tabhost);//初始化Tab的颜色，和字体的颜色  
		   tabhost.setOnTabChangedListener(new onTabclik()); // 选择监听器  
	}
	private void updateTab(TabHost tabhost2) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) { 
            View view = tabhost.getTabWidget().getChildAt(i); 
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); 
            tv.setTextSize(16); 
            tv.setTypeface(Typeface.SERIF, 2); // 设置字体和风格  
            if (tabhost.getCurrentTab() == i) {//选中  
                view.setBackgroundColor(getResources().getColor(R.color.xz));//选中后的背景  
                tv.setTextColor(this.getResources().getColorStateList( 
                        android.R.color.white)); 
            ts[i].setVisibility(View.VISIBLE);
          
                
            } else {//不选中  
            	  view.setBackgroundColor(getResources().getColor(R.color.wxz));//非选择的背景  
                tv.setTextColor(this.getResources().getColorStateList( 
                        android.R.color.white)); 
                ts[i].setVisibility(View.INVISIBLE);
           
            } 
		}
	}
	
	class onTabclik implements OnTabChangeListener{

		@Override
		public void onTabChanged(String tabId) {
			// TODO Auto-generated method stub
			 tabhost.setCurrentTabByTag(tabId); 
			 updateTab(tabhost); 
			 
		}
		
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		 switch (event.getKeyCode()) {
         case KeyEvent.KEYCODE_BACK:
     
  	           //do something...
  				String uesrid=SharedPreferencesUtils.querysharep2(Will_Activity.this, "userid");
  				if(uesrid.equals("1")){
  					Intent i2=new Intent(Will_Activity.this, MarketActivity.class);
  					startActivity(i2);
  					finish();
  					  return false;
  				}else if(uesrid.equals("2")){
  					Intent i2=new Intent(Will_Activity.this, MainActivity.class);
  					startActivity(i2);
  					finish();
  					  return false;
  				}
  			
        	 break;
       
     }
		
		
		return super.dispatchKeyEvent(event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
           //do something...
			String uesrid=SharedPreferencesUtils.querysharep2(Will_Activity.this, "userid");
			if(uesrid.equals("1")){
				Intent i2=new Intent(Will_Activity.this, MarketActivity.class);
				startActivity(i2);
				finish();
				  return false;
			}else if(uesrid.equals("2")){
				Intent i2=new Intent(Will_Activity.this, MainActivity.class);
				startActivity(i2);
				finish();
				  return false;
			}
			
        
        }
		
		return super.onKeyDown(keyCode, event);
	}
}
