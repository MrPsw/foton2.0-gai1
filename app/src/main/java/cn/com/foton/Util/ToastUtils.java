package cn.com.foton.Util;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.foton.R;

public class ToastUtils {
	
	
	
	
	
	//线程中Toast
	public static void TextToast(Context context,String text){
		 Looper.prepare();
		 Toast.makeText(context,text, 1).show();
		 Looper.loop();// 进入loop中的循环，查看消息队列
	}
	

	    private static Toast toast = null;  
	    public static int LENGTH_LONG = Toast.LENGTH_LONG;  
	    private static int LENGTH_SHORT = Toast.LENGTH_SHORT;  
	      
	    /** 
	     * 普通文本消息提示 
	     * @param context 
	     * @param text 
	     * @param duration 
	     */  
	    public static void TextToast(Context context,CharSequence text,int duration){  
	        //创建一个Toast提示消息  
	        toast = Toast.makeText(context, text, duration);  
	        //设置Toast提示消息在屏幕上的位置  
	        toast.setGravity(Gravity.CENTER, 0, 0);  
	        //显示消息  
	        toast.show();  
	    }  
	      
	    /** 
	     * 带图片消息提示  弃用样式太丑
	     * @param context 
	     * @param ImageResourceId 
	     * @param text 
	     * @param duration 
	     */  
	    public static void ImageToast(Context context,int ImageResourceId,CharSequence text,int duration){  
	        //创建一个Toast提示消息  
	        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);  
	        //设置Toast提示消息在屏幕上的位置  
	        toast.setGravity(Gravity.CENTER, 0, 0);  
	        //获取Toast提示消息里原有的View  
	        View toastView = toast.getView();  
	        //创建一个ImageView  
	        ImageView img = new ImageView(context);  
	        img.setImageResource(ImageResourceId);  
	        //创建一个LineLayout容器  
	        LinearLayout ll = new LinearLayout(context);  
	        //向LinearLayout中添加ImageView和Toast原有的View  
	        ll.addView(img);  
	        ll.addView(toastView);  
	        //将LineLayout容器设置为toast的View  
	        toast.setView(ll);  
	        //显示消息  
	        toast.show();  
	    }  
	    /**
	     * 提示 图片toast
	     * @param context
	     * @param ImageResourceId
	     * @param text
	     * @param duration
	     */
	    public static void ImageToastp(Context context,int ImageResourceId,CharSequence text,int duration){  
	        //创建一个Toast提示消息  
	        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);  
	        //设置Toast提示消息在屏幕上的位置  
	        toast.setGravity(Gravity.CENTER, 0, 0);  
	        //获取Toast提示消息里原有的View  
	        View toastView = toast.getView();  
	        //创建一个ImageView  
	      
	        View view=LayoutInflater.from(context).inflate(R.layout.toast_image,null);
	        //创建一个LineLayout容器  
	        LinearLayout ll = new LinearLayout(context);  
	        ImageView image=(ImageView) view.findViewById(R.id.imageView1);
	        TextView tv=(TextView) view.findViewById(R.id.textView1);
	        //向LinearLayout中添加ImageView和Toast原有的View  
	        image.setImageResource(ImageResourceId); 
	        tv.setText(text);
	        //将LineLayout容器设置为toast的View  
	        toast.setView(view);  
	        //显示消息  
	        toast.show();  
	    }


	public static void showToast(Context mContext, String id) {
		if (toast == null) {
			toast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
		}
		else {
			toast.setText(id);
		}
		toast.show();
	}
}
