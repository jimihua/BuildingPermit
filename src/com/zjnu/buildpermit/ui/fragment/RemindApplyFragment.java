package com.zjnu.buildpermit.ui.fragment;

import java.util.Date;
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

import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.adapter.ListViewRmndApeAdapter;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.bean.RemindApe;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

public class RemindApplyFragment<T> extends BaseListFragment implements XListView.IXListViewListener {
	@ViewInject(id = R.id.pm_rmnd_ape_lv_main)
	XListView mListView;
	private Handler mHandler;
	private Context mContext;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.pm_rmnd_ape, container, false);
		FinalActivity.initInjectedView(this, viewRoot);
		mContext = this.getActivity();
		this.mListView.setPullLoadEnable(true);
		geneItems();
		this.mListView.setXListViewListener(this);
		this.mHandler = new Handler();
		this.mListView.setDividerHeight(0);
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {

			}
		});
		return viewRoot;
	}

	@SuppressWarnings("unchecked")
	private void geneItems() {
		this.mProgressDialog = ProgressDialog.show(mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);
		AjaxParams params = new AjaxParams();
		CFinal.fh.post(Urls.URL_REMINDAPPLY, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				if (RemindApplyFragment.this.mProgressDialog != null) {
					RemindApplyFragment.this.mProgressDialog.dismiss();
				}
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);
				try {
					RemindApplyFragment.this.mDatas = gson.fromJson(str, new TypeToken<List<RemindApe>>() {
					}.getType());
				} catch (Exception e) {
					MyUtil.Log(TAG, e);
				}

				if (RemindApplyFragment.this.isFirst) {
					RemindApplyFragment.this.isFirst = false;
					RemindApplyFragment.this.mAdapter = new ListViewRmndApeAdapter<T>(mContext, mDatas, R.layout.pm_rmnd_ape_list_item);
					RemindApplyFragment.this.mListView.setAdapter(RemindApplyFragment.this.mAdapter);
				}
				RemindApplyFragment.this.mAdapter.notifyDataSetChanged();
				if (RemindApplyFragment.this.mProgressDialog != null) {
					RemindApplyFragment.this.mProgressDialog.dismiss();
				}

			}
		});

	}

	private void onLoad() {
		this.mListView.stopRefresh();
		this.mListView.stopLoadMore();
		this.mListView.setRefreshTime(this.sdf.format(new Date()));
	}

	public void onLoadMore() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				RemindApplyFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				RemindApplyFragment.this.isFirst = false;
				RemindApplyFragment.this.geneItems();
				RemindApplyFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onResume() {
		// TODO Auto-generated method stub
		if (!isFirst) {
			RemindApplyFragment.this.mAdapter = new ListViewRmndApeAdapter<T>(mContext, mDatas, R.layout.pm_rmnd_ape_list_item);
			RemindApplyFragment.this.mListView.setAdapter(RemindApplyFragment.this.mAdapter);
		}
		super.onResume();
	}
}