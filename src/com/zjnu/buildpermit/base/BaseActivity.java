package com.zjnu.buildpermit.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.Gson;
import com.zjnu.buildpermit.common.AppManager;

@SuppressWarnings("all")
public class BaseActivity<T> extends FinalActivity {

	protected final String TAG = this.getClass().getName();
	private boolean allowDestroy = true;
	private boolean allowFullScreen = true;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected CommonAdapter<T> mAdapter;
	protected List<T> mDatas = new ArrayList<T>();
	protected ProgressDialog mProgressDialog;
	protected Handler mHandler;
	protected boolean isFirst = true;
	protected int Page = 1;
	protected String TYPEID = "0";
	protected int TotalPage = 0;
	protected Gson gson;
	BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
		public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {
			ConnectivityManager localConnectivityManager = (ConnectivityManager) BaseActivity.this.getSystemService("connectivity");
			NetworkInfo localNetworkInfo1 = localConnectivityManager.getNetworkInfo(0);
			NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(1);
			/*
			 * if ((!localNetworkInfo1.isConnected()) &&
			 * (!localNetworkInfo2.isConnected())) {
			 * BaseActivity.this.setNerWorking(false);
			 * WarnUtils.toast(BaseActivity.this.mContext, "获取不到网络数据，请检查网络");
			 * return; }
			 */
			BaseActivity.this.setNerWorking(true);
		}
	};

	private boolean isNerWorking = false;
	public Context mContext;
	private View view;

	public boolean isAllowFullScreen() {
		return this.allowFullScreen;
	}

	public boolean isNerWorking() {
		return this.isNerWorking;
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		this.mContext = this;
		this.allowFullScreen = true;
		gson = new Gson();
		AppManager.getAppManager().addActivity(this);
		requestWindowFeature(1);
		IntentFilter localIntentFilter = new IntentFilter();
		localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(this.connectionReceiver, localIntentFilter);
	}

	protected void onDestroy() {
		super.onDestroy();
		if (this.connectionReceiver != null)
			unregisterReceiver(this.connectionReceiver);
		AppManager.getAppManager().finishActivity(this);
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if ((paramInt == 4) && (this.view != null)) {
			this.view.onKeyDown(paramInt, paramKeyEvent);
			if (!this.allowDestroy)
				return false;
		}
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

	public void onPause() {
		super.onPause();

	}

	public void onResume() {
		super.onResume();

	}

	protected void onStart() {
		super.onStart();
	}

	public void setAllowDestroy(boolean paramBoolean) {
		this.allowDestroy = paramBoolean;
	}

	public void setAllowDestroy(boolean paramBoolean, View paramView) {
		this.allowDestroy = paramBoolean;
		this.view = paramView;
	}

	public void setAllowFullScreen(boolean paramBoolean) {
		this.allowFullScreen = paramBoolean;
	}

	public void setNerWorking(boolean paramBoolean) {
		this.isNerWorking = paramBoolean;
	}

}
