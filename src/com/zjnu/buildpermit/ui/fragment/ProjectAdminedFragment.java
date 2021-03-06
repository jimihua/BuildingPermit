/**
 * @author Kimi
 */
package com.zjnu.buildpermit.ui.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.ProjectAdminedDetActivity;
import com.zjnu.buildpermit.adapter.ListViewAdminAdapter;
import com.zjnu.buildpermit.base.BaseFragment;
import com.zjnu.buildpermit.bean.ProjectAdmin;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.LogUtils;
import com.zjnu.buildpermit.common.StringUtils;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;
import com.zjnu.buildpermit.view.XListView.IXListViewListener;

/**
 * @author Kimi
 * 
 */
public class ProjectAdminedFragment extends BaseFragment<ProjectAdmin> implements IXListViewListener {

	@ViewInject(id = R.id.listView)
	XListView mListView;
	private Handler mHandler;

	@Override
	public View onCreateView(LayoutInflater mInflater, ViewGroup root, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = mInflater.inflate(R.layout.fragment_project_admin, root, false);
		FinalActivity.initInjectedView(this, rootView);
		mHandler = new Handler();
		this.mListView.setPullLoadEnable(true);
		geneItems();
		this.mListView.setXListViewListener(this);
		this.mListView.setDividerHeight(0);
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {
				LogUtils.d(mDatas.get(position).getProjectId());
				MyUtil.JumpPages(mContext, ProjectAdminedDetActivity.class, "id", mDatas.get(position-1).getProjectId());
			}
		});
		return rootView;
	}

	private void onLoad() {
		this.mListView.stopRefresh();
		this.mListView.stopLoadMore();
		this.mListView.setRefreshTime(this.sdf.format(new Date()));
	}

	@SuppressWarnings("unchecked")
	private void geneItems() {
		this.mProgressDialog = ProgressDialog.show(this.getActivity(), "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);
		AjaxParams params = new AjaxParams();
		CFinal.fh.post(Urls.URL_PRPJECTADMIN, params, new AjaxCallBack() {
			public void onFailure(Throwable throwable, int param, String string) {
				super.onFailure(throwable, param, string);
				mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				LogUtils.d(object.toString());
				String msg = StringUtils.getJsonObject(object, "rows");
				String total = StringUtils.getJsonObject(object, "total");
				if (!StringUtils.isNull(msg)) {
					mDatas = gson.fromJson(msg, new TypeToken<List<ProjectAdmin>>() {
					}.getType());
					mDatas = getAdminedList(mDatas);
					if (isFirst) {
						isFirst = false;
						mAdapter = new ListViewAdminAdapter(mContext, mDatas, R.layout.fragment_admin_item);
						mListView.setAdapter(mAdapter);
					}
					mAdapter.notifyDataSetChanged();
				}

				mProgressDialog.dismiss();
			}
		});

	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				isFirst = true;
				geneItems();
				mAdapter = new ListViewAdminAdapter(mContext, mDatas, R.layout.fragment_admin_item);
				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 100L);
	}

	public void onLoadMore() {
		// TODO Auto-generated method stub
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				onLoad();
			}
		}, 100L);
	}

	public void onClick(View view) {
		switch (view.getId()) {

		default:
			break;
		}
	}

	private List<ProjectAdmin> getAdminedList(List<ProjectAdmin> mList) {
		for (ProjectAdmin projectAdmin : mList) {
			if (projectAdmin.getState().equals("未审批")) {
				mList.remove(projectAdmin);
			}
		}
		return mList;
	}
}
