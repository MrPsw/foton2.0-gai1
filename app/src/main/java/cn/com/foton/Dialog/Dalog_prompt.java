package cn.com.foton.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.foton.R;

public class Dalog_prompt {
	private Activity activity;
	private AlertDialog dialog;
	private String title;


	public Dalog_prompt(Activity activity,String title) {
		this.activity = activity;
		this.title = title;
		showDialog();
		
	}
	

	public void showDialog(){
		dialog=new AlertDialog.Builder(activity).create();
		//点击外部区域不能取消dialog 
		dialog.setCanceledOnTouchOutside(false);
		dialog.setOnKeyListener(keylistener);
		dialog.show();

		window = dialog.getWindow();
		window.setContentView(R.layout.my_dialog);
		TextView tv_title = (TextView) window.findViewById(R.id.dialog_title);
		tv_title.setText(title);
		
		rL = (RelativeLayout) window.findViewById(R.id.RL);
		
		tv_confirm = (TextView) window.findViewById(R.id.tv_confirm);
		tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
		message = (TextView) window.findViewById(R.id.dialog_message);
		
		
		rL.setVisibility(View.GONE);
		
		window.setBackgroundDrawableResource(android.R.color.transparent);
		window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		window.setGravity(Gravity.CENTER);
		
	}
	public static interface  PositiveButtonOnclik {
		void confirm();
	}
	public static interface  NegativeButtonOnclik {
		void cancel();
	}
	
	public Dalog_prompt setMessage(String msg){
		rL.setVisibility(View.VISIBLE);
		
		message.setText(msg);
		return this;
	}
	
	public Dalog_prompt setPositiveButton(String msg,final PositiveButtonOnclik onclik){
		tv_confirm.setText(msg);
		tv_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onclik.confirm();
				dialog.dismiss();
			}
		});
	
		return this;
	}
	public Dalog_prompt setNegativeButton(String msg,final NegativeButtonOnclik onclik){
		tv_cancel.setText(msg);
		tv_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onclik.cancel();
				dialog.dismiss();
			}
		});
	
		return this;
	}
	
	
	
//	public void setMessage(String msg){
//		rL.setVisibility(View.VISIBLE);
//		
//		message.setText(msg);
//		
//	}
	public static OnKeyListener keylistener = new DialogInterface.OnKeyListener(){
		public boolean onKey(DialogInterface dialog,  int keyCode, KeyEvent event) {
			if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	} ;
	private TextView message;
	private RelativeLayout rL;
	private TextView tv_confirm;
	private TextView tv_cancel;
	private Window window;
}
