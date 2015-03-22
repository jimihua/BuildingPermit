/**   
 *    
 * 项目名称：TaurusClub     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-13 下午2:54:31   
 * 修改人：Administrator   
 * 修改时间：2015-3-13 下午2:54:31   
 * 修改备注：   
 * @version    
 *    
 */
package com.zjnu.buildpermit.view;

import org.apache.http.cookie.Cookie;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zjnu.buildpermit.common.LogUtils;
import com.zjnu.buildpermit.common.MyApp;

public class MyWebView extends WebView {
	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param mContext
	 * @param attrs
	 * @param defStyleAttr
	 */
	public MyWebView(Context mContext) {
		super(mContext);
		mActivity = (Activity) mContext;
		// TODO Auto-generated constructor stub
		init(mContext);
	}

	private Activity mActivity;

	@SuppressLint("SetJavaScriptEnabled")
	public void init(Context mContext) {
		mActivity = (Activity) mContext;

		WebSettings webSettings = this.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		// webSettings.setUseWideViewPort(true);
		this.setWebViewClient(mWebViewClientBase);
		this.setWebChromeClient(mWebChromeClientBase);
		this.onResume();
	}

	public void synCookies(Context context, String url) {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.removeSessionCookie();// 移除
		for (Cookie cookie : MyApp.getInstance().getRestoredCookies().getCookies()) {
			String cookieString = cookie.getName() + "=" + cookie.getValue() + "; domain=" + cookie.getDomain();
			cookieManager.setCookie(url,cookieString);
			LogUtils.d("value:" + cookieString);
			LogUtils.d("name:" + cookie.getName());
			LogUtils.d(cookieString);
		}
		// cookies是在HttpClient中获得的cookie
		CookieSyncManager.getInstance().sync();
	}

	private WebViewClientBase mWebViewClientBase = new WebViewClientBase();

	private class WebViewClientBase extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			CookieManager cookieManager = CookieManager.getInstance();
			String CookieStr = cookieManager.getCookie(url);
			Log.e("sunzn", "Cookies = " + CookieStr);
			super.onPageFinished(view, url);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
			// TODO Auto-generated method stub
			super.doUpdateVisitedHistory(view, url, isReload);
		}
	}

	private WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();

	private class WebChromeClientBase extends WebChromeClient {

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			mActivity.setProgress(newProgress * 1000);
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			// TODO Auto-generated method stub
			super.onReceivedTitle(view, title);
		}

		@Override
		public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
			// TODO Auto-generated method stub
			super.onReceivedTouchIconUrl(view, url, precomposed);
		}

		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
			// TODO Auto-generated method stub
			return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
		}

	}
}
