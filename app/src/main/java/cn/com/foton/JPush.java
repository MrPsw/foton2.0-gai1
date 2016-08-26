package cn.com.foton;

import java.util.Set;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class JPush {
	/**
	 * @方法名称:initJPush
	 * @创建人：Mr.peng
	 * @创建时间：2016年3月15日 上午10:05:31
	 * @备注：
	 * @返回类型：void
	 */
	public static void initJPush(Context c,Object id) {
			
		
		// 初始化极光推送sdk
		JPushInterface.setDebugMode(true);
		JPushInterface.init(c);
	
		JPushInterface.setAliasAndTags(c,id+"_ft", null, new TagAliasCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see cn.jpush.android.api.TagAliasCallback#gotResult(int,
			 * java.lang.String, java.util.Set)
			 * 
			 * Code 描述 详细解释 6001 无效的设置，tag/alias 不应参数都为 null 6002 设置超时 建议重试 6003
			 * alias 字符串不合法 有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。 6004 alias超长。最多40个字节
			 * 中文 UTF-8 是 3 个字节 6005 某一个 tag
			 * 字符串不合法有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。 6006 某一个 tag 超长。一个 tag 最多
			 * 40个字节中文 UTF-8 是 3 个字节 6007 tags 数量超出限制。最多 100个
			 * 这是一台设备的限制。一个应用全局的标签数量无限制。 6008 tag/alias 超出总长度限制。总长度最多 1K 字节 6011
			 * 10s内设置tag或alias大于3次 短时间内操作过于频繁
			 */
			@Override
			public void gotResult(int responseCode, String alias, Set<String> tags) {
				switch (responseCode) {
				case 0:
					Log.i("WedoApplication", "JPushInterface-->调用成功!!!");
					break;
				case 6001:
					Log.i("WedoApplication", "JPushInterface-->无效的设置，tag/alias 不应参数都为 null");
					break;
				case 6002:
					Log.i("WedoApplication", "JPushInterface-->设置超时 建议重试");
					break;
				case 6003:
					Log.i("WedoApplication", "JPushInterface-->alias 字符串不合法 有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字");
					break;
				case 6004:
					Log.i("WedoApplication", "JPushInterface-->alias超长。最多40个字节 中文 UTF-8 是 3 个字节");
					break;
				case 6005:
					Log.i("WedoApplication", "JPushInterface-->某一个 tag 字符串不合法有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字");
					break;
				case 6006:
					Log.i("WedoApplication", "JPushInterface-->某一个 tag 超长。一个 tag 最多 40个字节中文 UTF-8 是 3 个字节");
					break;
				case 6007:
					Log.i("WedoApplication", "JPushInterface-->tags 数量超出限制。最多 100个 这是一台设备的限制。一个应用全局的标签数量无限制");
					break;
				case 6008:
					Log.i("WedoApplication", "JPushInterface-->tag/alias 超出总长度限制。总长度最多 1K 字节 ");
					break;
				case 6011:
					Log.i("WedoApplication", "JPushInterface-->10s内设置tag或alias大于3次 短时间内操作过于频繁 ");
					break;
				}
			}
		});

	}

	/**
	 * 
	* @方法名称:getDeviceId
	* @描述: 获取设备唯一id
	* @创建人：wangxy
	* @创建时间：2015-3-31 上午11:29:50
	* @备注：    
	* @return  
	* @返回类型：String
	 */
	public String getDeviceId(){
//		// 获取手机唯一识别码
//			TelephonyManager tm = (TelephonyManager) applicationContext
//				.getSystemService(Context.TELEPHONY_SERVICE);
//			
//				System.out.println(tm.getDeviceId()+"设备id");
//				Log.e("MyReceiver", tm.getDeviceId());
			return "2126454535545_ft";
			
			
	 }
}
