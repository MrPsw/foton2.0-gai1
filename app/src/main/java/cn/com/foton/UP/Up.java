package cn.com.foton.UP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

public class Up {
	
	private Context context;
	public Up(Context ct) {
		// TODO Auto-generated constructor stub
		context=ct;
		handler1.sendEmptyMessage(0);

	}
	 private ProgressDialog pBar;
	void downFile(final String url,final Context context) {   
	        pBar = new ProgressDialog(context);
	        
	        //pBar= ProgressDialog.show(context,"��������","���Ժ�...");
	        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
	        pBar.setTitle("��������");  
	        pBar.setMessage("���Ժ�...");  
	        pBar.setProgress(0);  
	        pBar.setCanceledOnTouchOutside(false);
	        pBar.setCancelable(false);
	        pBar.show();  
	        new Thread() {  
	            public void run() {          
	                HttpClient client = new DefaultHttpClient();  
	                HttpGet get = new HttpGet(url);  
	                HttpResponse response;  
	                try {  
	                    response = client.execute(get);  
	                    HttpEntity entity = response.getEntity();  
	                    int length = (int) entity.getContentLength();   //��ȡ�ļ���С  
	                                        pBar.setMax(length);                            //���ý��������ܳ���  
	                    InputStream is = entity.getContent();  
	                    FileOutputStream fileOutputStream = null;  
	                    if (is != null) {  
	                        File file = new File(  
	                                Environment.getExternalStorageDirectory(),  
	                                "FotonDealerAPPAndroid.apk");  
	                        fileOutputStream = new FileOutputStream(file);  
	                        byte[] buf = new byte[10];   //����ǻ���������һ�ζ�ȡ10�����أ���Ū��С�˵㣬��Ϊ�ڱ��أ�������ֵ̫��һ �¾��������ˣ�������progressbar��Ч����  
	                        int ch = -1;  
	                        int process = 0;  
	                        while ((ch = is.read(buf)) != -1) {         
	                            fileOutputStream.write(buf, 0, ch);  
	                            process += ch;  
	                            pBar.setProgress(process);       //������ǹؼ���ʵʱ���½����ˣ�  
	                        }  
	  
	                    }  
	                    fileOutputStream.flush();  
	                    if (fileOutputStream != null) {  
	                        fileOutputStream.close();  
	                    }  
	                    down(context);  
	                } catch (ClientProtocolException e) {  
	                    e.printStackTrace();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	  
	        }.start();  
	    }  
	  
	    void down(final Context c) {  
	        handler1.post(new Runnable() {  
	            public void run() {  
	                pBar.cancel();  
	                update(c);  
	            }  
	        });  
	    }  
	//��װ�ļ���һ��̶�д��  
	    void update(Context c) {                      
	        Intent intent = new Intent(Intent.ACTION_VIEW);  
	        intent.setDataAndType(Uri.fromFile(new File(Environment  
	                .getExternalStorageDirectory(), "FotonDealerAPPAndroid.apk")),  
	                "application/vnd.android.package-archive");  
	        c.startActivity(intent);  
	    }  
	    
	    private Handler handler1 = new Handler() {  
	        public void handleMessage(Message msg) {  
	            // ����и��¾���ʾ  
	    
	                showUpdateDialog();  //����Ĵ����  
	         
	        };  
	    };  
	    
	    private void showUpdateDialog() {  
	        AlertDialog.Builder builder = new AlertDialog.Builder(context);  
	        builder.setIcon(android.R.drawable.ic_dialog_info);  
	        builder.setTitle("����������°汾");  
	        builder.setMessage("��Ҫ����");  
	        builder.setCancelable(false);  
	      
	        builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
	      
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                if (Environment.getExternalStorageState().equals(  
	                        Environment.MEDIA_MOUNTED)) {  
	                    downFile("http://dealerapp.foton.com.cn/package/FotonDealerAPPAndroid.apk",context);     //������Ĵ����  
	                } else {  
	                    Toast.makeText(context, "SD�������ã������SD��",  
	                            Toast.LENGTH_SHORT).show();  
	                }  
	            }  
	        });  
	        
	        builder.create().show();  
	    }  
	      
	
	      
	    // ��ȡ��ǰ�汾�İ汾��  
	    private String getVersion() {  
	        try {  
	            PackageManager packageManager =context.getPackageManager();  
	            PackageInfo packageInfo = packageManager.getPackageInfo(  
	            		context.getPackageName(), 0);  
	            return packageInfo.versionName;  
	        } catch (NameNotFoundException e) {  
	            e.printStackTrace();  
	            return "�汾��δ֪";  
	        }  
	    }
}
