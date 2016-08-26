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
	
	
	
	
	
	//�߳���Toast
	public static void TextToast(Context context,String text){
		 Looper.prepare();
		 Toast.makeText(context,text, 1).show();
		 Looper.loop();// ����loop�е�ѭ�����鿴��Ϣ����
	}
	

	    private static Toast toast = null;  
	    public static int LENGTH_LONG = Toast.LENGTH_LONG;  
	    private static int LENGTH_SHORT = Toast.LENGTH_SHORT;  
	      
	    /** 
	     * ��ͨ�ı���Ϣ��ʾ 
	     * @param context 
	     * @param text 
	     * @param duration 
	     */  
	    public static void TextToast(Context context,CharSequence text,int duration){  
	        //����һ��Toast��ʾ��Ϣ  
	        toast = Toast.makeText(context, text, duration);  
	        //����Toast��ʾ��Ϣ����Ļ�ϵ�λ��  
	        toast.setGravity(Gravity.CENTER, 0, 0);  
	        //��ʾ��Ϣ  
	        toast.show();  
	    }  
	      
	    /** 
	     * ��ͼƬ��Ϣ��ʾ  ������ʽ̫��
	     * @param context 
	     * @param ImageResourceId 
	     * @param text 
	     * @param duration 
	     */  
	    public static void ImageToast(Context context,int ImageResourceId,CharSequence text,int duration){  
	        //����һ��Toast��ʾ��Ϣ  
	        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);  
	        //����Toast��ʾ��Ϣ����Ļ�ϵ�λ��  
	        toast.setGravity(Gravity.CENTER, 0, 0);  
	        //��ȡToast��ʾ��Ϣ��ԭ�е�View  
	        View toastView = toast.getView();  
	        //����һ��ImageView  
	        ImageView img = new ImageView(context);  
	        img.setImageResource(ImageResourceId);  
	        //����һ��LineLayout����  
	        LinearLayout ll = new LinearLayout(context);  
	        //��LinearLayout�����ImageView��Toastԭ�е�View  
	        ll.addView(img);  
	        ll.addView(toastView);  
	        //��LineLayout��������Ϊtoast��View  
	        toast.setView(ll);  
	        //��ʾ��Ϣ  
	        toast.show();  
	    }  
	    /**
	     * ��ʾ ͼƬtoast
	     * @param context
	     * @param ImageResourceId
	     * @param text
	     * @param duration
	     */
	    public static void ImageToastp(Context context,int ImageResourceId,CharSequence text,int duration){  
	        //����һ��Toast��ʾ��Ϣ  
	        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);  
	        //����Toast��ʾ��Ϣ����Ļ�ϵ�λ��  
	        toast.setGravity(Gravity.CENTER, 0, 0);  
	        //��ȡToast��ʾ��Ϣ��ԭ�е�View  
	        View toastView = toast.getView();  
	        //����һ��ImageView  
	      
	        View view=LayoutInflater.from(context).inflate(R.layout.toast_image,null);
	        //����һ��LineLayout����  
	        LinearLayout ll = new LinearLayout(context);  
	        ImageView image=(ImageView) view.findViewById(R.id.imageView1);
	        TextView tv=(TextView) view.findViewById(R.id.textView1);
	        //��LinearLayout�����ImageView��Toastԭ�е�View  
	        image.setImageResource(ImageResourceId); 
	        tv.setText(text);
	        //��LineLayout��������Ϊtoast��View  
	        toast.setView(view);  
	        //��ʾ��Ϣ  
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
