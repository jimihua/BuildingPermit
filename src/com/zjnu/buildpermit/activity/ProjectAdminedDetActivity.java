/**
 * @author Kimi
 */
package com.zjnu.buildpermit.activity;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.view.MyWebView;

/**
 * @author Kimi
 * 
 */
public class ProjectAdminedDetActivity extends BaseActivity {

	@ViewInject(id = R.id.webView)
	MyWebView webView;

	private String id = "745";

	@Override
	protected void onCreate(Bundle paramBundle) {
		// TODO Auto-generated method stub
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_pm_admineddet);
		id = getIntent().getStringExtra("id");
		getItems();
	}

	private void getItems() {
		webView.init(mContext);
		webView.synCookies(mContext, Urls.URL_PRPJECTADMINEDDETAIL + id);
		webView.loadUrl(Urls.URL_PRPJECTADMINEDDETAIL + id);

	}

}
