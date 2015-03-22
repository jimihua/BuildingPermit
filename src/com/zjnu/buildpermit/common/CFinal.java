package com.zjnu.buildpermit.common;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.SyncHttpClient;

public class CFinal extends FinalHttp {
	private static SharedPreferences sp;

	public static final FinalHttp fh = MyApp.getInstance().getFinalHttp("utf-8");
	private static String[] putMsg;

	public static void setPutMsg(String[] putMsg) {
		// TODO Auto-generated method stub
		CFinal.putMsg = putMsg;
	}

	/**
	 * 异步请求服务器
	 * 
	 * @author Kimi
	 */
	private static AsyncHttpClient asyncHttpClient;
	/**
	 * 同步请求HttpClient
	 * 
	 * @author Kimi
	 */
	private static SyncHttpClient syncHttpClient;
	private static PersistentCookieStore pCookieStore;

	public static String[] getPutMsg() {
		// TODO Auto-generated method stub
		return putMsg;
	}

	public static String GetComXml(Context mContext, String key) {
		// TODO Auto-generated method stub
		sp = mContext.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}

	public static boolean SetComXml(Context mContext, String key, String string) {
		// TODO Auto-generated method stub
		sp = mContext.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(key, string);
		return editor.commit();
	}

	/**
	 * 方法描述: 获得FinalDb
	 * 
	 * @param
	 * @author KIMI 创建时间：2014-12-14 下午1:56:49
	 * @version
	 * 
	 */
	public static FinalDb getFb(Context mContext) {
		return FinalDb.create(mContext);
	}
	/**
	 * 获得异步请求HttpClient
	 * 
	 * @param mContext
	 * @return
	 */
	public static AsyncHttpClient getAsyncHttpClient(Context mContext) {

		if (asyncHttpClient == null) {
			asyncHttpClient = new AsyncHttpClient();

			pCookieStore = new PersistentCookieStore(mContext);
			pCookieStore.clear();
			asyncHttpClient.setCookieStore(pCookieStore);
			asyncHttpClient.setConnectTimeout(20 * 1000);

		}
		return asyncHttpClient;

	}

	/**
	 * 获得同步请求HttpClient
	 * 
	 * @param mContext
	 * @return
	 */
	public static SyncHttpClient getSyncHttpClient(Context mContext) {
		if (syncHttpClient == null) {
			syncHttpClient = new SyncHttpClient();
			pCookieStore = new PersistentCookieStore(mContext);
			pCookieStore.clear();
			asyncHttpClient.setCookieStore(pCookieStore);
			syncHttpClient.setConnectTimeout(20 * 1000);
		}
		return syncHttpClient;

	}

}
