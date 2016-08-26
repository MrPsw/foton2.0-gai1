package cn.com.foton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import cn.com.foton.Dbhelper.DatabaseUtil;

public class CopyDb extends DatabaseUtil{
	
	 private static SQLiteDatabase db;
	String DATABASE_NAME = "test.db";
      String oldPath = "data/data/cn.com.foton/databases/" + DATABASE_NAME;
      String old2Path = "data/data/cn.com.foton/databases";
	    String newPath = Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME;
	  Context ct;  
	public CopyDb(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		ct=context;
//		copyDBToSDcrad();
		f1(context);
		
	}

	private void copyDBToSDcrad()
	{
	   
	     
	    copyFile(oldPath, newPath);
	}
	private void f1(Context ct) {
		// TODO Auto-generated method stub
		File file =new File(oldPath);
		File file1=new File(old2Path);
		if(!file1.exists()){
			file1.mkdirs();
		}
		if(!file.exists()){
			InputStream is=ct.getResources().openRawResource(R.raw.test);
			try {
				FileOutputStream fos=new FileOutputStream(file);
				byte buff[]=new byte[10240];
				int a=0;
				while((a=is.read(buff))>0){
					fos.write(buff, 0, a);
				}
				fos.close();
				is.close();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		db=SQLiteDatabase.openOrCreateDatabase(oldPath, null);
	}
	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径
	 * @param newPath
	 *            String 复制后路径
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath)
	{
	    try
	    {
	        int bytesum = 0;
	        int byteread = 0;
	        File oldfile = new File(oldPath);
	        File newfile = new File(newPath);
	        if (!newfile.exists())
	        {
	            newfile.createNewFile();
	        }
	        if (oldfile.exists())
	        { // 文件存在时
	            InputStream inStream = new FileInputStream(oldPath); // 读入原文件
	            FileOutputStream fs = new FileOutputStream(newPath);
	            byte[] buffer = new byte[1444];
	            while ((byteread = inStream.read(buffer)) != -1)
	            {
	                bytesum += byteread; // 字节数 文件大小
	                fs.write(buffer, 0, byteread);
	            }
	            inStream.close();
	        }
	    }
	    catch (Exception e)
	    {
	        System.out.println("复制单个文件操作出错");
	        e.printStackTrace();
	 
	    }
		db=SQLiteDatabase.openOrCreateDatabase(oldPath, null);

	}
}
