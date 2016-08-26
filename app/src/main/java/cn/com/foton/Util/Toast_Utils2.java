package cn.com.foton.Util;
import java.util.Timer;
import java.util.TimerTask;
 
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;
public class Toast_Utils2 {


	    
	    private WindowManager wdm;
	    private double time;
	    private WindowManager.LayoutParams params;
	    private Timer timer;
		private View toastView;
		  View v;
	    private Toast_Utils2(Context context, String text, double time){
	        wdm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	        timer = new Timer();
	        
	     
	        
	        
	        params = new WindowManager.LayoutParams();
	        params.height = WindowManager.LayoutParams.WRAP_CONTENT;  
	        params.width = WindowManager.LayoutParams.WRAP_CONTENT;  
	        params.format = PixelFormat.TRANSLUCENT;  
	        params.type = WindowManager.LayoutParams.TYPE_TOAST;  
	        params.setTitle("Toast");  
	        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON  
	                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  
	                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
	        
	        
	        this.time = time;
	       // toast.setGravity(Gravity.CENTER, 0, 0);  
	      //创建一个ImageView  
	        v= LayoutInflater.from(context).inflate(R.layout.toast_image, null);
	        ImageView imageView=(ImageView) v.findViewById(R.id.imageView1);
	        Animation anim = AnimationUtils.loadAnimation(context, R.anim.tip);        
			LinearInterpolator lir = new LinearInterpolator();   
			anim.setInterpolator(lir);  
			imageView.startAnimation(anim);
			TextView tv=(TextView) v.findViewById(R.id.textView1);
			tv.setText(text);
			//将LineLayout容器设置为toast的View  
	       // toast.setView(v); 
	    }
	    
	    public static Toast_Utils2 makeText(Context context, String text, double time){
	    	Toast_Utils2 toastCustom = new Toast_Utils2(context, text, time);
	        return toastCustom;
	    }
	    
	    public void show(){
	        wdm.addView(v, params);
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                wdm.removeView(toastView);
	            }
	        }, (long)(time * 1000));
	    }
	    
	    public void cancel(){
	        wdm.removeView(v);
	        timer.cancel();
	    }
	    

}
