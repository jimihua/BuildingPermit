package com.zjnu.buildpermit.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.NoticeDetActivity;
import com.zjnu.buildpermit.adapter.ListViewNotAdapter;
import com.zjnu.buildpermit.adapter.ListViewRmndApeAdapter;
import com.zjnu.buildpermit.adapter.ListViewRmndPastAdapter;
import com.zjnu.buildpermit.adapter.ViewHolder;
import com.zjnu.buildpermit.base.BaseFragment;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.RemindApe;
import com.zjnu.buildpermit.bean.RemindApe;
import com.zjnu.buildpermit.bean.RemindApe;
import com.zjnu.buildpermit.bean.RemindApe;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

public class RemindPayFragment<T> extends BaseListFragment<T> implements XListView.IXListViewListener {

	@ViewInject(id = R.id.pm_rmnd_ape_lv_main)
	XListView mListView;

	private boolean isFirst = true;
	private Handler mHandler;
	private ArrayList<RemindApe> mRemindApes = new ArrayList<RemindApe>();
	private ProgressDialog mProgressDialog = null;
	private SimpleDateFormat sdf;
	private CommonAdapter<RemindApe> mAdapter;
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

		CFinal.fh.post(Urls.URL_REMINDPAY, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				RemindPayFragment.this.mProgressDialog.dismiss();

			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);
				try {
					RemindPayFragment.this.mRemindApes = gson.fromJson(str, new TypeToken<List<RemindApe>>() {
					}.getType());
				} catch (Exception e) {

					MyUtil.Log(TAG, e);
				}

				if (RemindPayFragment.this.isFirst) {
					RemindPayFragment.this.isFirst = false;
					RemindPayFragment.this.mAdapter = new ListViewRmndApeAdapter<RemindApe>(mContext, mRemindApes, R.layout.pm_rmnd_ape_list_item);
					RemindPayFragment.this.mListView.setAdapter(RemindPayFragment.this.mAdapter);
				}
				RemindPayFragment.this.mAdapter.notifyDataSetChanged();
				RemindPayFragment.this.mProgressDialog.dismiss();

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

				RemindPayFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				RemindPayFragment.this.Page = 1;
				RemindPayFragment.this.isFirst = false;
				RemindPayFragment.this.geneItems();
				RemindPayFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onResume() {
		// TODO Auto-generated method stub
		if (!isFirst) {
			RemindPayFragment.this.mAdapter = new ListViewRmndApeAdapter<RemindApe>(mContext, mRemindApes, R.layout.pm_rmnd_ape_list_item);
			RemindPayFragment.this.mListView.setAdapter(RemindPayFragment.this.mAdapter);

		}
		super.onResume();
	}
}