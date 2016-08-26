package cn.com.foton.Util;

import java.util.ArrayList;

public class ArrayListUtils {
public static int getIndex(ArrayList<String> list,String s){
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)==s){
				return i;
			}
			
		}
	return -1;
	};
}
