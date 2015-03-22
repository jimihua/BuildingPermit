package com.zjnu.buildpermit.utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zjnu.buildpermit.activity.NewsAcitivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyUtil {
	public static int LENGTH_LONG = 1;
	public static int LENGTH_SHORT = 0;
	public static int res = 0;
	private static Toast toast = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

	public static void CallPhone(Context paramContext, String paramString) {
		paramContext.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString)));
	}

	public static void ImageToast(Context paramContext, int paramInt1, CharSequence paramCharSequence, int paramInt2) {
		toast = Toast.makeText(paramContext, paramCharSequence, 1);
		toast.setGravity(17, 0, 0);
		View localView = toast.getView();
		ImageView localImageView = new ImageView(paramContext);
		localImageView.setImageResource(paramInt1);
		LinearLayout localLinearLayout = new LinearLayout(paramContext);
		localLinearLayout.addView(localImageView);
		localLinearLayout.addView(localView);
		toast.setView(localLinearLayout);
		toast.show();
	}

	public static void JumpPages(Context paramContext, Class<?> paramClass, String paramString) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, true);
		paramContext.startActivity(localIntent);
	}

	/**
	 * @param paramContext
	 * @param paramClass
	 * @param name
	 *            传值得名称
	 * @param value
	 *            传值得内容
	 */
	public static void JumpPages(Context paramContext, Class<?> paramClass, String name, String value) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(name, value);
		paramContext.startActivity(localIntent);
	}

	public static void JumpPages(Context paramContext, Class<?> paramClass, String paramString, int paramInt) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, paramInt);
		paramContext.startActivity(localIntent);
	}

	public static void JumpPages(Context paramContext, Class<?> paramClass, String paramString, Serializable serializable) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, serializable);
		paramContext.startActivity(localIntent);
	}

	public static <T> void JumpPages(Context paramContext, Class<?> paramClass, String paramString, ArrayList<T> arrayList) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		Bundle bundle = new Bundle();
		bundle.putSerializable(paramString, arrayList);
		localIntent.putExtra(paramString, bundle);
		paramContext.startActivity(localIntent);
	}

	public static void JumpPages(Context paramContext, Class<?> paramClass, String paramString, String[] paramArrayOfString) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, paramArrayOfString);
		paramContext.startActivity(localIntent);
	}

	public static void SendMsg(Context paramContext, String paramString1, String paramString2) {
		Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramString1));
		localIntent.putExtra("sms_body", paramString2);
		paramContext.startActivity(localIntent);
	}

	public static void ShowWindows(Context paramContext, String paramString1, String paramString2) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
		localBuilder.setTitle(paramString1);
		localBuilder.setMessage(paramString2);
		localBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
				paramAnonymousDialogInterface.dismiss();
			}
		});
		localBuilder.create().show();
	}

	public static void TextToast(Context paramContext, CharSequence paramCharSequence, int paramInt) {
		toast = Toast.makeText(paramContext, paramCharSequence, paramInt);
		toast.setGravity(17, 0, 0);
		toast.show();
	}

	public static String insertComma(String money, int length) {
		if ((money == null) || (money.length() < 1))
			return "";
		if ("0".equals(money))
			return "0.00";
		if ("0.0000".equals(money))
			return "0.00";
		double d = Double.parseDouble(money);
		DecimalFormat localDecimalFormat = null;
		if (length == 0) {
			localDecimalFormat = new DecimalFormat("###,###");
			return localDecimalFormat.format(d);
		}
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append("###,###.00");
		for (int i = 0; i <= length; i++) {
			localStringBuffer.append("#");
			localDecimalFormat = new DecimalFormat(localStringBuffer.toString());
		}
		return localDecimalFormat.format(d);
	}

	public static void showJumpDialog(final Context paramContext, String paramString1, String paramString2, Activity paramActivity) {

	}

	public static boolean checkNulls(Context context, EditText editText) {
		boolean bool = true;
		String str = editText.getText().toString();
		if (str == null) {
			bool = true;
		}
		if (str.length() >= 1) {
			bool = false;

		}
		if (bool)
			ShowWindows(context, "提示", editText.getHint() + "必须提供！");
		return bool;

	}

	public static boolean checkNulls(Context context, TextView textView) {
		boolean bool = true;
		String str = textView.getText().toString();
		if (str == null) {
			bool = true;
		}
		if (str.length() >= 1) {
			bool = false;

		}
		if (bool)
			ShowWindows(context, "提示", textView.getHint() + "必须提供！");
		return bool;

	}

	public static boolean checkSubmit(Context context, TextView textView, String str) {
		boolean bool = true;
		if (TextUtils.isEmpty(textView.getText().toString().trim())) {
			WarnUtils.toast(context, str);
			bool = true;
		} else {
			bool = false;
		}
		return bool;
	}

	public static String getDate(Context context) {
		final Calendar calendar = Calendar.getInstance();
		String date = (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.DAY_OF_MONTH);

		return date;
	}

	/**
	 * 方法描述: 输出error 日志信息
	 * 
	 * @param
	 * @author KIMI 创建时间：2014-10-17 上午11:44:18
	 * @version
	 * 
	 */
	public static String Log(String Str, Object log) {
		Log.e("^-^ ^o^" + Str, String.valueOf(log));
		return String.valueOf(log);

	}

	/**
	 * 获得textview的内容
	 * 
	 * @param textView
	 * @return
	 */
	public static String getText(TextView textView) {

		return textView.getText().toString().trim();

	}

	public static void toast(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	public static String getNews(String key) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("NoticeCount", "公告");
		map.put("TodayReminderCount", "今日提醒");
		map.put("UnAuditedProject", "项目审核");
		map.put("ProjectCertificateRequest", "项目证书审批");
		map.put("UnProjectCertificateRequest", "其他证书审批");
		map.put("UnSignCount", "证书签收");
		map.put("UnPostCount", "寄送证书");
		map.put("UnReturnCount", "");
		map.put("UnTransferCount", "调配证书");
		map.put("UnCenterCompanyDealCount", "总公司盖章办理");
		map.put("UnDealJinHuaFenCompanyCount", "金华盖章办理");
		map.put("GetFinaceMessageCount", "");
		map.put("GetFenCompanyMessageCount", "");
		map.put("UnDoNumberOfFinace", "外经证办理");
		map.put("BaozhengJinCount", "保证金办理");
		map.put("GetFinaceMessageCountCompany", "");

		return map.get(key);

	}

	/**
	 * 获得当前时间 格式如yyyy-mm-dd
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return sdf.format(new Date());

	}


}
