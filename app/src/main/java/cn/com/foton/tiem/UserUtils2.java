package cn.com.foton.tiem;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;
import cn.com.foton.tiem.PickerView.onSelectListener;

public class UserUtils2 {

private Dialog seletorDialog;
private PickerView username;
private PickerView month_pv;
private TextView tv_cancle;
private TextView tv_select;
private TextView tv_title;
private String Username;

Context ct;
private ResultHandler handler;

public interface ResultHandler {
    void handle(String time);
}
public UserUtils2(Context context,List<String>  tiems,ResultHandler Handler) {
	// TODO Auto-generated constructor stub
			this.handler=Handler;
			
			Username=tiems.get(0);
			
	System.out.println(tiems.size());
			
			ct=context;
	  seletorDialog = new Dialog(context, R.style.time_dialog);
      seletorDialog.setCancelable(false);
      seletorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      seletorDialog.setContentView(R.layout.dialog_selector3);
      Window window = seletorDialog.getWindow();
      window.setGravity(Gravity.BOTTOM);
      WindowManager.LayoutParams lp = window.getAttributes();
      int width = ScreenUtil.getInstance(context).getScreenWidth();
      lp.width = width;
      window.setAttributes(lp);
      seletorDialog.show();
   
      
	initView(tiems);
}

private void initView(List<String> data) {
	// TODO Auto-generated method stub
	   username = (PickerView) seletorDialog.findViewById(R.id.year_pv);

       username.setData(data);
 
       username.setSelected(0);

       tv_cancle = (TextView) seletorDialog.findViewById(R.id.tv_cancle);
       tv_select = (TextView) seletorDialog.findViewById(R.id.tv_select);
       tv_title = (TextView) seletorDialog.findViewById(R.id.tv_title);
    
       username.setOnSelectListener(new onSelectListener() {
			@Override
			public void onSelect(String text) {
				Username = text;
			}
		});

       
       
       tv_cancle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               seletorDialog.dismiss();
           }
       });
       tv_select.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
        	   handler.handle(Username+"");
//          Toast.makeText(ct, year+"-"+month, 0).show();
               seletorDialog.dismiss();
           }
       });
}
public void show(){
	seletorDialog.show();
}
}
