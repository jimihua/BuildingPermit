/**
 * 
 */
package com.zjnu.buildpermit.common;

import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.PreferencesCookieStore;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SyncHttpClient;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author vivid
 * 
 */
public class MyApp extends Application {
	private final String TAG = MyApp.class.getSimpleName();
	/* URLS */
	public static final String URL_LOGIN_PAGE = "http://gd.10086.cn/common/include/public/dispatcher.jsp";
	public static final String URL_QUERY_BALANCE_REF = "http://gd.10086.cn/ngcrm/hall/servicearea/Balance/index.jsp";
	public static final String URL_QUERY_BALANCE = "http://gd.10086.cn/ngcrm/hall/servicearea/queryRateInfo.action";
	public static final String URL_VRFCODE = "http://gd.10086.cn/image?sds=";
	public static final String URL_LOGIN = "http://gd.10086.cn/ServicesServlet/LOGIN";
	public static final String URL_LOGIN_CALL_NOTICE = "http://gd.10086.cn/login/LoginCallNotice.jsp";
	public static final String URL_LOGIN_CALL_NOTICE_REF = "http://gd.10086.cn/common/include/public/dispatcher.jsp?_backURL=http://gd.10086.cn/ngcrm/hall/servicearea/Balance/index.jsp&_portalCode=bsacNB";
	public static final String URL_SSO_NGCRM = "http://gd.10086.cn/ngcrm/sso/callback?";
	public static final String URL_SSO_COMMODITY = "http://gd.10086.cn/commodity/sso/uapCallback/invok.jsps?";
	public static final String URL_SSO_PMARKETING = "http://gd.10086.cn/pmarketing/sso/uapCallback/invok.jsps?";

	private final String PRE_COOKIES = "cookies";
	private static MyApp mApp;
	private FinalHttp fh;
	private BasicCookieStore cookieStore;
	private AsyncHttpClient asyncHttpClient;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate()");
		mApp = this;
	}

	private ProgressDialog pd;

	private ProgressDialog getProgressDialog(Context ctx, String msg) {
		pd = new ProgressDialog(ctx);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage(msg);
		return pd;
	}

	public void showProgressDialog(Context ctx, String msg) {
		if (pd != null && pd.isShowing()) {
			killDialog();
		}
		getProgressDialog(ctx, msg).show();
	}

	public void killDialog() {
		if (pd != null && pd.isShowing()) {
			pd.dismiss();
			pd = null;
		}
	}

	public void saveCookies() {

		if (cookieStore != null) {
			PreferencesCookieStore preCookieStore = new PreferencesCookieStore(this);
			for (Cookie cookie : cookieStore.getCookies()) {
				preCookieStore.addCookie(cookie);
			}
		}
	}

	public CookieStore getRestoredCookies() {

		if (cookieStore == null) {
			cookieStore = new BasicCookieStore();
			PreferencesCookieStore preCookieStore = new PreferencesCookieStore(this);
			List<Cookie> cookies = preCookieStore.getCookies();
			cookieStore.addCookies(cookies.toArray(new Cookie[cookies.size()]));
		}
		return cookieStore;
	}

	public void clearCookies() {
		PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
		cookieStore = null;
		if (fh != null) {
			fh.configCookieStore(getRestoredCookies());
		}

		Toast.makeText(this, "清除Cookie成功", Toast.LENGTH_SHORT).show();
	}

	public static MyApp getInstance() {
		return mApp;
	}

	public FinalHttp getFinalHttp(String charset) {
		if (fh == null) {
			fh = new FinalHttp();
			fh.configTimeout(20 * 1000);// 20s
			fh.configCookieStore(getRestoredCookies());
			fh.addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.3.3; en-us; sdk Build/GRI34) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
		}
		fh.configCharset(charset);
		return fh;
	}

	public AsyncHttpClient getSyncHttpClient() {
		if (asyncHttpClient == null) {
			asyncHttpClient = new SyncHttpClient();
			asyncHttpClient.addHeader("User-Agent",
					"Mozilla/5.0 (Linux; U; Android 2.3.3; en-us; sdk Build/GRI34) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
			
			asyncHttpClient.setConnectTimeout(20 * 1000);
		}
		return asyncHttpClient;

	}

}