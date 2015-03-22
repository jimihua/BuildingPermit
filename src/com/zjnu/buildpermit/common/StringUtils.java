package com.zjnu.buildpermit.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zjnu.buildpermit.utils.MyUtil;

public class StringUtils {
	public static final DecimalFormat format = new DecimalFormat("0.00");
	private static Gson gson = new Gson();

	/**
	 * 方法描述: 是否是身份证
	 * 
	 * @param
	 * @author KIMI 创建时间：2014-12-7 下午4:59:38
	 * @version
	 * 
	 */
	public static String isIDCard(String paramString) {

		if ((paramString.length() == 0) || ("".equals(paramString)))
			return "请填写身份证";
		if ((paramString.length() != 15) && (paramString.length() != 18))
			return "身份证号码长度应该为15位或18位。";

		if (paramString.length() == 18)
			if (!Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$").matcher(paramString).matches()) {
				return "身份证号码填写有问题";
			}
		if (paramString.length() == 15)
			if (!Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$").matcher(paramString).matches()) {
				return "身份证号码填写有问题";
			}

		return "";

	}

	public static boolean isDate(String paramString) {
		return Pattern
				.compile(
						"^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$")
				.matcher(paramString).matches();
	}

	/**
	 * 是否是数字
	 * 
	 * */
	public static boolean isNumeric(String numberic) {
		return Pattern.compile("[0-9]*").matcher(numberic).matches();
	}

	/**
	 * 方法描述: 是否是数字含小数
	 * 
	 * @param
	 * @author KIMI 创建时间：2014-11-26 上午11:44:15
	 * @version
	 * 
	 */
	public static boolean isNumber(String number) {
		return Pattern.compile("[0-9]*|d*.d{1}?d*").matcher(number).matches();

	}

	public static boolean isEmail(String email) {
		return Pattern.compile("\\w+@\\w+\\.(com\\.cn)|\\w+@\\w+\\.(com|cn)").matcher(email).matches();
	}

	/**
	 * 检测手机号码是否正确，如果为空，返回"请填写手机号码"，如果手机号码有误，显示手机号码格式有问题，其他情况返回""
	 * 
	 * */
	public static String isPhoneNumber(String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber)) {
			return "请填写手机号码";
		}
		if (!Pattern.compile("1[3,4,5,8]{1}\\d{9}").matcher(phoneNumber).matches()) {
			return "手机号码格式有问题";
		}
		return "";
	}

	/**
	 * 判断字符串是否为手机号码，是手机号码，返回true，不是手机号码，返回false
	 * 
	 * */
	public static boolean isPhone(String phone) {
		if (Pattern.compile("1[3,4,5,8]{1}\\d{9}").matcher(phone).matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 方法描述: 是否是金钱格式
	 * 
	 * @param
	 * @author KIMI 创建时间：2014-10-14 下午3:21:44
	 * @version
	 * 
	 */

	public static String isMoney(String money) {
		String string = "";
		if (TextUtils.isEmpty(money)) {
			return string = "借款金额不能为空";
		} else if (!isNumeric(money)) {
			return string = "借款金额格式出错";
		}

		return string;

	}

	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	// private final static SimpleDateFormat dateFormater = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// private final static SimpleDateFormat dateFormater2 = new
	// SimpleDateFormat("yyyy-MM-dd");

	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	/**
	 * 将字符串转为日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 清除文本框的空字符
	 * 
	 * @param sdate
	 * @return
	 */
	public static String StringTrim(TextView textView) {

		return textView.getText().toString().trim();

	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 返回long类型的今天的日期
	 * 
	 * @return
	 */
	public static long getToday() {
		Calendar cal = Calendar.getInstance();
		String curDate = dateFormater2.get().format(cal.getTime());
		curDate = curDate.replace("-", "");
		return Long.parseLong(curDate);
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 将一个InputStream流转换成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

	/**
	 * 方法描述:获得交易类型
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-6 下午12:10:57
	 * @version
	 * 
	 */
	public static String getTradeType(String status) {
		Map<String, String> tradeMap = new HashMap<String, String>();
		tradeMap.put("全部", "ALL");
		tradeMap.put("充值", "RECHARGE");
		tradeMap.put("投资项目", "CHECKOUT");
		tradeMap.put("提现", "CASH_DRAW");
		tradeMap.put("收益", "REPAY");
		tradeMap.put("债权还款", "REPAY");
		return tradeMap.get("status");
	}

	public static final String formatTwo(double decimal) {

		return format.format(decimal);

	}

	/**
	 * 方法描述:判断textview是否为空
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-1-6 下午2:59:00
	 * @version
	 * 
	 */
	public static boolean isTextNull(Context mContext, TextView textView, String errorMsg) {
		if (textView.getText().toString().trim().length() <= 0) {
			MyUtil.toast(mContext, errorMsg);
			return true;
		}
		return false;
	}

	/**
	 * 获得简单Json对象 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-5 下午1:52:15
	 * @version
	 * 
	 */
	public static String getJsonObject(byte[] arg2, String key) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = new JSONObject(new String(arg2));
			return jsonObject.getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得简单Json对象 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-5 下午1:52:15
	 * @version
	 * 
	 */
	public static String getJsonObject(Object object, String key) {
		JSONObject jsonObject = new JSONObject();
		try {
			LogUtils.d(object);
			jsonObject = new JSONObject(object.toString());
			LogUtils.d(jsonObject.getString(key));
			return jsonObject.getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方法描述: 判断是否为空，如果为空 返回true
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-6 下午1:31:31
	 * @version
	 * 
	 */
	public static boolean isNull(String str) {
		boolean isNull = false;
		if (str == null || str.isEmpty()) {
			isNull = true;
		}
		return isNull;

	}
}
