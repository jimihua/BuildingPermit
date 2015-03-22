/**
 * 
 */
package com.zjnu.buildpermit.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.ExpressDetActivity;
import com.zjnu.buildpermit.adapter.ListViewExpAdapter;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.Express;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.LogUtils;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

/**
 * 快递接受类
 * 
 * @author vivid
 * 
 */
public class ExpressActivity extends BaseActivity<Express> {
	private String TAG = this.getClass().getName();

	@ViewInject(id = R.id.pm_exprcv_lv_kuaidi)
	ListView mListView;
	@ViewInject(id = R.id.main_top_tv_title)
	TextView main_top_tv_title;
	@ViewInject(id = R.id.warning)
	TextView warning;
	private ArrayList<Express> mExpresses = new ArrayList<Express>();
	private ArrayList<Express> mSameExpress = new ArrayList<Express>();
	private HashMap<String, String> transId = new HashMap<String, String>();
	private CommonAdapter<Express> mAdapter;

	@SuppressWarnings("unchecked")
	protected void geneItems() {

		this.mProgressDialog = ProgressDialog.show(this.mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		AjaxParams params = new AjaxParams();

		CFinal.fh.post(Urls.URL_EXPS, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				ExpressActivity.this.mProgressDialog.dismiss();

			}

			public void onSuccess(Object object) {
				super.onSuccess(object);

				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);

				try {
					ExpressActivity.this.mExpresses = gson.fromJson(str, new TypeToken<List<Express>>() {
					}.getType());
				} catch (Exception e) {

				}
				mSameExpress = new ArrayList<Express>();
				for (Express mExpress : mExpresses) {
					if (mExpress.getIsStained().equals("未签收")) {
						if (!transId.containsValue(mExpress.getTransportId())) {
							mSameExpress.add(mExpress);
							transId.put("transid", mExpress.getTransportId());
						}
					}
				}
				if (mSameExpress.size() == 0) {
					warning.setVisibility(View.VISIBLE);
					mListView.setVisibility(View.GONE);
				}
				if (mAdapter == null) {
					ExpressActivity.this.mAdapter = new ListViewExpAdapter(ExpressActivity.this.mContext, ExpressActivity.this.mSameExpress, R.layout.pm_exp_list_item_v2);
					ExpressActivity.this.mListView.setAdapter(ExpressActivity.this.mAdapter);

				} else {
					ExpressActivity.this.mListView.setAdapter(ExpressActivity.this.mAdapter);
					ExpressActivity.this.mAdapter.notifyDataSetChanged();

				}

				ExpressActivity.this.mProgressDialog.dismiss();
			}
		});
	}

	/*
	 * @author Kimi
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.pm_express);
		geneItems();
		main_top_tv_title.setText("快递接收");
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {
				MyUtil.JumpPages(mContext, ExpressDetActivity.class, "exp", mExpresses);
				String[] msg = { mSameExpress.get(position).getTransportId() };
				CFinal.setPutMsg(msg);

			}
		});
	}

}
