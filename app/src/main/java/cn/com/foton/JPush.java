package cn.com.foton;

import java.util.Set;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class JPush {
	/**
	 * @��������:initJPush
	 * @�����ˣ�Mr.peng
	 * @����ʱ�䣺2016��3��15�� ����10:05:31
	 * @��ע��
	 * @�������ͣ�void
	 */
	public static void initJPush(Context c,Object id) {
			
		
		// ��ʼ����������sdk
		JPushInterface.setDebugMode(true);
		JPushInterface.init(c);
	
		JPushInterface.setAliasAndTags(c,id+"_ft", null, new TagAliasCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see cn.jpush.android.api.TagAliasCallback#gotResult(int,
			 * java.lang.String, java.util.Set)
			 * 
			 * Code ���� ��ϸ���� 6001 ��Ч�����ã�tag/alias ��Ӧ������Ϊ null 6002 ���ó�ʱ �������� 6003
			 * alias �ַ������Ϸ� ��Ч�ı�������ǩ��ɣ���ĸ�����ִ�Сд�������֡��»��ߡ����֡� 6004 alias���������40���ֽ�
			 * ���� UTF-8 �� 3 ���ֽ� 6005 ĳһ�� tag
			 * �ַ������Ϸ���Ч�ı�������ǩ��ɣ���ĸ�����ִ�Сд�������֡��»��ߡ����֡� 6006 ĳһ�� tag ������һ�� tag ���
			 * 40���ֽ����� UTF-8 �� 3 ���ֽ� 6007 tags �����������ơ���� 100��
			 * ����һ̨�豸�����ơ�һ��Ӧ��ȫ�ֵı�ǩ���������ơ� 6008 tag/alias �����ܳ������ơ��ܳ������ 1K �ֽ� 6011
			 * 10s������tag��alias����3�� ��ʱ���ڲ�������Ƶ��
			 */
			@Override
			public void gotResult(int responseCode, String alias, Set<String> tags) {
				switch (responseCode) {
				case 0:
					Log.i("WedoApplication", "JPushInterface-->���óɹ�!!!");
					break;
				case 6001:
					Log.i("WedoApplication", "JPushInterface-->��Ч�����ã�tag/alias ��Ӧ������Ϊ null");
					break;
				case 6002:
					Log.i("WedoApplication", "JPushInterface-->���ó�ʱ ��������");
					break;
				case 6003:
					Log.i("WedoApplication", "JPushInterface-->alias �ַ������Ϸ� ��Ч�ı�������ǩ��ɣ���ĸ�����ִ�Сд�������֡��»��ߡ�����");
					break;
				case 6004:
					Log.i("WedoApplication", "JPushInterface-->alias���������40���ֽ� ���� UTF-8 �� 3 ���ֽ�");
					break;
				case 6005:
					Log.i("WedoApplication", "JPushInterface-->ĳһ�� tag �ַ������Ϸ���Ч�ı�������ǩ��ɣ���ĸ�����ִ�Сд�������֡��»��ߡ�����");
					break;
				case 6006:
					Log.i("WedoApplication", "JPushInterface-->ĳһ�� tag ������һ�� tag ��� 40���ֽ����� UTF-8 �� 3 ���ֽ�");
					break;
				case 6007:
					Log.i("WedoApplication", "JPushInterface-->tags �����������ơ���� 100�� ����һ̨�豸�����ơ�һ��Ӧ��ȫ�ֵı�ǩ����������");
					break;
				case 6008:
					Log.i("WedoApplication", "JPushInterface-->tag/alias �����ܳ������ơ��ܳ������ 1K �ֽ� ");
					break;
				case 6011:
					Log.i("WedoApplication", "JPushInterface-->10s������tag��alias����3�� ��ʱ���ڲ�������Ƶ�� ");
					break;
				}
			}
		});

	}

	/**
	 * 
	* @��������:getDeviceId
	* @����: ��ȡ�豸Ψһid
	* @�����ˣ�wangxy
	* @����ʱ�䣺2015-3-31 ����11:29:50
	* @��ע��    
	* @return  
	* @�������ͣ�String
	 */
	public String getDeviceId(){
//		// ��ȡ�ֻ�Ψһʶ����
//			TelephonyManager tm = (TelephonyManager) applicationContext
//				.getSystemService(Context.TELEPHONY_SERVICE);
//			
//				System.out.println(tm.getDeviceId()+"�豸id");
//				Log.e("MyReceiver", tm.getDeviceId());
			return "2126454535545_ft";
			
			
	 }
}
