package cn.com.foton.Util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;

public class CustomToast {   
    public static final int LENGTH_MAX = -1;   
    private boolean mCanceled = true;  
    private Handler mHandler;   
    private Context mContext;   
    private Toast mToast;
	private View v;
 
  
    public CustomToast(Context context) {   
        this(context,new Handler());   
    }   
  
  
    public CustomToast(Context context,Handler h) {   
        mContext = context;   
        mHandler = h;   
        mToast = Toast.makeText(mContext,"",Toast.LENGTH_SHORT);   
        mToast.setGravity(Gravity.BOTTOM, 0, 0);   
    }   
  
    public void show(int resId,int duration) {   
    	
    	   
        
        if(duration != LENGTH_MAX) {   
            mToast.setDuration(duration);   
            mToast.show();   
         } else if(mCanceled) {   
             mToast.setDuration(Toast.LENGTH_LONG);  
             mCanceled = false;  
             showUntilCancel();   
         }   
    }  
      
    /**  
     * @param text 要显示的内容  
     * @param duration 显示的时间长  
     * 根据LENGTH_MAX进行判断  
     * 如果不匹配，进行系统显示  
     * 如果匹配，永久显示，直到调用hide()  
     */  
    public void show(String text,int duration,Context context) {   
    	
    	mToast = Toast.makeText(mContext,"",Toast.LENGTH_SHORT);   
        mToast.setGravity(Gravity.BOTTOM, 0, 0); 
    	 v= LayoutInflater.from(context).inflate(R.layout.toast_image, null);
	        ImageView imageView=(ImageView) v.findViewById(R.id.imageView1);
	        Animation anim = AnimationUtils.loadAnimation(context, R.anim.tip);        
			LinearInterpolator lir = new LinearInterpolator();   
			anim.setInterpolator(lir);  
			imageView.startAnimation(anim);
			TextView tv=(TextView) v.findViewById(R.id.textView1);
			tv.setText(text);
			mToast.setView(v); 
     
        if(duration != LENGTH_MAX) {   
            mToast.setDuration(duration);   
            mToast.show();   
            } else {   
                if(mCanceled) {   
                    mToast.setDuration(Toast.LENGTH_LONG);   
                    mCanceled = false;   
                    showUntilCancel();  
                }  
            }   
        }   
  
    /**  
     * 隐藏Toast  
     */  
    public void hide(){  
        mToast.cancel();  
        mCanceled = true;  
    }  
      
    public boolean isShowing() {  
        return !mCanceled;  
    }  
      
    private void showUntilCancel() {   
        if(mCanceled)   
            return;   
        mToast.show();  
        mHandler.postDelayed(new Runnable() {  
            public void run() {   
                showUntilCancel();   
            }  
        },3000);   
    }   
}  