package com.anbang.palm.util;

import android.app.Activity;
import android.widget.Toast;
import android.content.Context;

public class ToastUtils {

	public static void showToas(final Activity act,final String msg){
		if("main".equals(Thread.currentThread().getName())){
			Toast.makeText(act, msg, 0).show();
		}else{
			act.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					Toast.makeText(act, msg, 0).show();					
				}
			});
		}
	}
	public static void displayToastCenter(Context context, int strResId) {
		Toast.makeText(context, strResId, Toast.LENGTH_SHORT).show();
	}
}
