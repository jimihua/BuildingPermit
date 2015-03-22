/**
 * 
 */
package com.zjnu.buildpermit.activity;

import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.LogUtils;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.service.ActService;
import com.zjnu.buildpermit.utils.MyUtil;

/**
 * @author Kimi
 * 
 */
public class LoginActivity extends BaseActivity {

	@ViewInject(id = R.id.login_email)
	EditText login_email;

	@ViewInject(id = R.id.login_pwd)
	EditText login_pwd;

	@ViewInject(id = R.id.login_btn, click = "onClick")
	Button login_btn;

	private ProgressDialog mProgressDialog = null;
	private String username = null;
	private String password = null;

	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.login_activity);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_btn:
			username = MyUtil.getText(login_email);
			password = MyUtil.getText(login_pwd);
			Login(username, password);
			break;

		default:
			break;
		}
	}

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 */
	@SuppressWarnings("unchecked")
	private void Login(final String username, final String password) {
		this.mProgressDialog = ProgressDialog.show(this.mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);
		
		AjaxParams params = new AjaxParams();
		params.put("UserName", username);
		params.put("Password", password);
		
		
		CFinal.fh.post(Urls.URL_LOGIN, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String paramAnonymousString) {
				super.onFailure(t, paramAnonymousInt, paramAnonymousString);
				LoginActivity.this.mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				LogUtils.d(object);
				LoginActivity.this.mProgressDialog.dismiss();
				CFinal.SetComXml(mContext, "login", "login");
				CFinal.SetComXml(mContext, "username", username);
				CFinal.SetComXml(mContext, "password", password);
				MyUtil.toast(mContext, "登陆成功");
				MyUtil.JumpPages(mContext, BuildingActivity.class, "loginsuccess");
				Intent service = new Intent();
				service.setClass(mContext, ActService.class);
				startService(service);
				LoginActivity.this.finish();
			}

		});
	}

	/*
	 * @author Kimi
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}
