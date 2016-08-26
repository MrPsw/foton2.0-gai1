package cn.com.foton.Util;

import android.content.Context;
import android.widget.Toast;
import cn.com.foton.R;
import cn.com.foton.allot.AllotActivity;

public class ArrayBooleanUtils {
public static boolean getArrayBooleanIsTrue(boolean[] cb,Context context){
	boolean b=false;

	for (int i = 0; i < cb.length; i++) {
		if(cb[i]){
			b=true;
		}
	}

	if(!b){
		Toast.makeText(context, "请先选择需要分配的客户", 0).show();
		return false;
	}else{
		return true;	
	}
	}
}
