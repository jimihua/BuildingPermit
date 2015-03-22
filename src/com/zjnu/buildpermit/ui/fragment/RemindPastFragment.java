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
import com.zjnu.buildpermit.adapter.ListViewRmndPastAdapter;
import com.zjnu.buildpermit.adapter.ViewHolder;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.RemindPast;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

public class RemindPastFragment<T> extends BaseListFragment implements XListView.IXListViewListener {
	@ViewInject(id = R.id.pm_rmnd_past_lv_main)
	XListView mListView;
	private Handler mHandler;
	private Context mContext;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.pm_rmnd_past, container, false);
		FinalActivity.initInjectedView(this, viewRoot);
		this.mListView.setPullLoadEnable(true);
		mContext = this.getActivity();
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
	protected void geneItems() {
		this.mProgressDialog = ProgressDialog.show(mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);
		AjaxParams params = new AjaxParams();
		CFinal.fh.post(Urls.URL_REMINDPAST, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				RemindPastFragment.this.mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);
				try {
					RemindPastFragment.this.mDatas = gson.fromJson(str, new TypeToken<List<RemindPast>>() {
					}.getType());
				} catch (Exception e) {
					MyUtil.Log(TAG, e);
				}
				if (RemindPastFragment.this.isFirst) {
					RemindPastFragment.this.isFirst = false;
					RemindPastFragment.this.mAdapter = new ListViewRmndPastAdapter<RemindPast>(mContext, mDatas, R.layout.pm_rmnd_past_list_item);
					RemindPastFragment.this.mListView.setAdapter(RemindPastFragment.this.mAdapter);
				}
				RemindPastFragment.this.mAdapter.notifyDataSetChanged();
				RemindPastFragment.this.mProgressDialog.dismiss();

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
				RemindPastFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				RemindPastFragment.this.Page = 1;
				RemindPastFragment.this.isFirst = false;
				RemindPastFragment.this.geneItems();
				RemindPastFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onResume() {
		// TODO Auto-generated method stub
		if (!isFirst) {
			RemindPastFragment.this.mAdapter = new ListViewRmndPastAdapter<RemindPast>(mContext, mDatas, R.layout.pm_rmnd_past_list_item);
			RemindPastFragment.this.mListView.setAdapter(RemindPastFragment.this.mAdapter);
		}
		super.onResume();
	}
}