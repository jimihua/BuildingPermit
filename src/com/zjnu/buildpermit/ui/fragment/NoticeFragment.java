/**
 * 
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
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.NoticeDetActivity;
import com.zjnu.buildpermit.adapter.ListViewNotAdapter;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.bean.Notice;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

/**
 * 公告类
 * 
 * @author vivid
 * 
 */
public class NoticeFragment extends BaseListFragment implements
		XListView.IXListViewListener {
	private String TAG = this.getClass().getName();
	private int red = Color.rgb(255, 120, 0);
	private int black = Color.rgb(67, 67, 67);
	@ViewInject(id = R.id.pm_notice_lv_not)
	XListView mListView;
	@ViewInject(id = R.id.main_top_tv_title)
	TextView main_top_tv_title;
	private boolean isFirst = true;
	private Context mContext;
	private Handler mHandler;
	private ArrayList<Notice> mNotices = new ArrayList<Notice>();
	private ListViewNotAdapter mAdapter;

	@SuppressWarnings("unchecked")
	protected void geneItems() {
		MyUtil.Log("notice", "geneitem");
		this.mProgressDialog = ProgressDialog.show(this.mContext, "请稍后",
				"正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		AjaxParams params = new AjaxParams();

		CFinal.fh.post(Urls.URL_NOTICE, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt,
					String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				NoticeFragment.this.mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);
				try {
					NoticeFragment.this.mNotices = gson.fromJson(str,
							new TypeToken<List<Notice>>() {
							}.getType());
				} catch (Exception e) {

					MyUtil.Log(TAG, e);
				}
				for (Notice notice : mNotices) {
					MyUtil.Log("noticeid", notice.getNoticeId());
					MyUtil.Log("notice", notice.getTitle());
				}
				if (NoticeFragment.this.isFirst) {
					NoticeFragment.this.isFirst = false;
					NoticeFragment.this.mAdapter = new ListViewNotAdapter(
							NoticeFragment.this.mContext,
							NoticeFragment.this.mNotices,
							R.layout.pm_not_list_item);
					NoticeFragment.this.mListView
							.setAdapter(NoticeFragment.this.mAdapter);
					MyUtil.Log("notice", "adapter");

				}
				NoticeFragment.this.mAdapter.notifyDataSetChanged();

				NoticeFragment.this.mProgressDialog.dismiss();
			}
		});

	}

	private void onLoad() {
		this.mListView.stopRefresh();
		this.mListView.stopLoadMore();
		this.mListView.setRefreshTime(this.sdf.format(new Date()));
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.pm_notice, container, false);
		FinalActivity.initInjectedView(this, viewRoot);
		this.mContext = this.getActivity();
		this.mListView.setPullLoadEnable(true);
		MyUtil.Log(TAG, "onCreateView");
		geneItems();
		main_top_tv_title.setText("公告查看");
		this.mListView.setXListViewListener(this);
		this.mHandler = new Handler();
		this.mListView.setDividerHeight(0);
		this.mListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(
							AdapterView<?> paramAnonymousAdapterView,
							View view, int position, long paramAnonymousLong) {
						MyUtil.JumpPages(mContext, NoticeDetActivity.class,
								"position", mNotices.get(position - 1)
										.getNoticeId());
					}
				});
		return viewRoot;
	}

	public void onLoadMore() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				if (NoticeFragment.this.TotalPage > NoticeFragment.this.Page) {
					NoticeFragment localExpressReceiveActivity = NoticeFragment.this;
					localExpressReceiveActivity.Page = (1 + localExpressReceiveActivity.Page);
					NoticeFragment.this.geneItems();
				}
				NoticeFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				NoticeFragment.this.Page = 1;
				NoticeFragment.this.isFirst = false;
				NoticeFragment.this.geneItems();
				NoticeFragment.this.mAdapter = new ListViewNotAdapter(
						NoticeFragment.this.mContext,
						NoticeFragment.this.mNotices, R.layout.pm_not_list_item);
				NoticeFragment.this.mListView
						.setAdapter(NoticeFragment.this.mAdapter);
				NoticeFragment.this.onLoad();
			}
		}, 100L);
	}

	/*
	 * @author Kimi
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		if (!isFirst) {
			onRefresh();
		}
		super.onResume();
	}

}
