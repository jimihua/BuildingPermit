package com.zjnu.buildpermit.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

/**
 * 所有提示都放到这里
 * 
 * @author liudongqiu
 * 
 */
public class WarnUtils {

	private static Toast toast = null;

	public static void toast(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	public static final void showDialog(Context context, String text) {
		new AlertDialog.Builder(context).setMessage(text)
				.setNegativeButton("关闭", null).create().show();
	}

	public interface OnClickListener extends DialogInterface.OnClickListener {

	}

	public static Builder getItemBuilder(Context context, String title) {

		Builder builder;

		builder = new AlertDialog.Builder(context).setTitle(title).setIcon(
				android.R.drawable.ic_dialog_info);
		return builder;
	}

	public static String Log(String log) {
		Log.e(log, log);
		return log;

	}

}
