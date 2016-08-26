package cn.com.foton.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;

import com.bigkoo.svprogresshud.SVProgressHUD;


public class IOSdialog {

	private static SVProgressHUD mSVProgressHUD;
	private AlertDialog alertDialog;

public  IOSdialog(Activity activity,String title){
	mSVProgressHUD = new SVProgressHUD(activity);
	mSVProgressHUD.showWithStatus(title);


	new Thread(new Runnable()
	{
		public void run()
		{
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			Message msg = new Message();
			msg.arg1 = 1;
			handler.sendMessage(msg);//----告诉主线程执行任务


		}
	}).start();

}
public void close(){
//	if(alertDialog!=null){
//		alertDialog.dismiss();
//	}

	if(mSVProgressHUD.isShowing()){

		mSVProgressHUD.dismiss();
	}

}
Handler handler=new Handler(){
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		close();
	}
};

}
