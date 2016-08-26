package cn.com.SMS;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MSMutils {
	
	public static void sendSmsWithBody(Context context, String number, String body) {
		Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
		sendIntent.setData(Uri.parse("smsto:" + number));
		sendIntent.putExtra("sms_body", body);
		context.startActivity(sendIntent);
	}

}
