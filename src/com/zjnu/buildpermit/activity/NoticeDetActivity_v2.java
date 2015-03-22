/**
 * @author Kimi
 */
package com.zjnu.buildpermit.activity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;

import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.MyApp;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.MyWebView;

/**
 * @author Kimi
 * 
 */
public class NoticeDetActivity_v2 extends BaseActivity {
	private ProgressDialog mProgressDialog = null;
	private String mNotice = "";
	private String mPosition = "0";
	@ViewInject(id = R.id.webview)
	MyWebView webview;
	@ViewInject(id = R.id.main_top_tv_title)
	TextView main_top_tv_title;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mNotice = msg.getData().getString("notice", "");

				break;

			default:
				break;
			}
		}
	};

	protected void onCreate(Bundle paramBundle) {
		// TODO Auto-generated method stub
		super.onCreate(paramBundle);
		setContentView(R.layout.pm_not_detail_v2);
		main_top_tv_title.setText("公告详情");
		mPosition = getIntent().getStringExtra("position");
		webview.init(mContext);
		webview.synCookies(mContext, Urls.URL_NOTICEDET + mPosition);
		webview.loadUrl(Urls.URL_NOTICEDET + mPosition);
		getNotice(mPosition);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getNotice(String mPosition) {

		this.mProgressDialog = ProgressDialog.show(this.mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		AjaxParams params = new AjaxParams();

		CFinal.fh.post(Urls.URL_NOTICEDET + mPosition, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				MyUtil.Log(strMsg, TAG);
				NoticeDetActivity_v2.this.mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				Bundle bundle = new Bundle();
				bundle.putString("notice", object.toString());
				MyUtil.Log(object.toString(), TAG);
				Message msg = new Message();
				msg.setData(bundle);
				msg.what = 1;
				mHandler.sendMessage(msg);
				NoticeDetActivity_v2.this.mProgressDialog.dismiss();
			}

		});

	}

}
