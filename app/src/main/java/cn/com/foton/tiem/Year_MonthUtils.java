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

public class Year_MonthUtils {

private Dialog seletorDialog;
private PickerView year_pv;
private PickerView month_pv;
private TextView tv_cancle;
private TextView tv_select;
private TextView tv_title;
private String year;
private String month;
Context ct;
private ResultHandler handler;
private String tiem;

public interface ResultHandler {
    void handle(String time);
}
public Year_MonthUtils(Context context,String start,ResultHandler Handler) {
	// TODO Auto-generated constructor stub
	this.handler=Handler;
			String[] tiems = DataTiemUtils(start);
			year=tiems[0];
			
			month=tiems[1];
			
			ct=context;
	  seletorDialog = new Dialog(context, R.style.time_dialog);
      seletorDialog.setCancelable(false);
      seletorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      seletorDialog.setContentView(R.layout.dialog_selector2);
      Window window = seletorDialog.getWindow();
      window.setGravity(Gravity.BOTTOM);
      WindowManager.LayoutParams lp = window.getAttributes();
      int width = ScreenUtil.getInstance(context).getScreenWidth();
      lp.width = width;
      window.setAttributes(lp);
      seletorDialog.show();
      List<String> data = new ArrayList<String>();
      List<String> seconds = new ArrayList<String>();
      String  times[]=start.split("-");
      tiem = tiems[0];
      month=tiems[1];
      for (int i = 0; i < 10; i++)
      {
          data.add("201" + i);
      }
      for (int i = 1; i < 12; i++)
      {
          seconds.add(i < 10 ? "0" + i : "" + i);
      }
      
	initView(data, seconds);
}
public String[]  DataTiemUtils(String tiem){

	String[] tiemw = tiem.split("-");
	
	return tiemw;
};
private void initView(List<String> data, List<String> seconds) {
	// TODO Auto-generated method stub
	   year_pv = (PickerView) seletorDialog.findViewById(R.id.year_pv);
       month_pv = (PickerView) seletorDialog.findViewById(R.id.month_pv);
       year_pv.setData(data);
       month_pv.setData(seconds);
       int idex=data.indexOf(tiem);
       int idx=seconds.indexOf(month);
       year_pv.setSelected(idex);
       month_pv.setSelected(idx);
       tv_cancle = (TextView) seletorDialog.findViewById(R.id.tv_cancle);
       tv_select = (TextView) seletorDialog.findViewById(R.id.tv_select);
       tv_title = (TextView) seletorDialog.findViewById(R.id.tv_title);
    
       year_pv.setOnSelectListener(new onSelectListener() {
			@Override
			public void onSelect(String text) {
				year = text;
			}
		});
       month_pv.setOnSelectListener(new onSelectListener() {


		@Override
		public void onSelect(String text) {
			// TODO Auto-generated method stub
			month = text;
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
        	   handler.handle(year+"-"+month);

               seletorDialog.dismiss();
           }
       });
}
public void show(){
	seletorDialog.show();
}
}
