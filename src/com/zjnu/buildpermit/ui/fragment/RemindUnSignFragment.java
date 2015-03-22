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
import com.zjnu.buildpermit.adapter.ListViewRmndPastAdapter;
import com.zjnu.buildpermit.adapter.ListViewRmndUnSignAdapter;
import com.zjnu.buildpermit.adapter.ViewHolder;
import com.zjnu.buildpermit.base.BaseFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.RemindUnSign;
import com.zjnu.buildpermit.bean.RemindUnSign;
import com.zjnu.buildpermit.bean.RemindUnSign;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

public class RemindUnSignFragment<T> extends BaseFragment implements XListView.IXListViewListener {

	@ViewInject(id = R.id.pm_rmnd_unsign_lv_main)
	XListView mListView;

	private int Page = 1;
	private String TYPEID = "0";
	private int TotalPage = 0;
	private Gson gson;
	private boolean isFirst = true;
	private Handler mHandler;
	private ArrayList<RemindUnSign> mRemindUnSigns = new ArrayList<RemindUnSign>();
	private ProgressDialog mProgressDialog = null;
	private SimpleDateFormat sdf;
	private CommonAdapter<RemindUnSign> mAdapter;
	private Context mContext;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.pm_rmnd_unsign, container, false);
		FinalActivity.initInjectedView(this, viewRoot);
		mContext = this.getActivity();
		this.gson = new Gson();
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

		CFinal.fh.post(Urls.URL_REMINDUNSIGN, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				RemindUnSignFragment.this.mProgressDialog.dismiss();
				
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);
				MyUtil.Log(TAG, str);
				try {
					RemindUnSignFragment.this.mRemindUnSigns = gson.fromJson(str, new TypeToken<List<RemindUnSign>>() {
					}.getType());
				} catch (Exception e) {

					MyUtil.Log(TAG, e);
				}

				if (RemindUnSignFragment.this.isFirst) {
					RemindUnSignFragment.this.isFirst = false;
					RemindUnSignFragment.this.mAdapter = new ListViewRmndUnSignAdapter<RemindUnSign>(mContext, mRemindUnSigns, R.layout.pm_rmnd_unsign_list_item);
					RemindUnSignFragment.this.mListView.setAdapter(RemindUnSignFragment.this.mAdapter);
				}
				RemindUnSignFragment.this.mAdapter.notifyDataSetChanged();
				RemindUnSignFragment.this.mProgressDialog.dismiss();
				

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

				RemindUnSignFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				RemindUnSignFragment.this.Page = 1;
				RemindUnSignFragment.this.isFirst = false;
				RemindUnSignFragment.this.geneItems();
				RemindUnSignFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onResume() {
		// TODO Auto-generated method stub
		if (!isFirst) {
			RemindUnSignFragment.this.mAdapter = new ListViewRmndUnSignAdapter<RemindUnSign>(mContext, mRemindUnSigns, R.layout.pm_rmnd_unsign_list_item);
			RemindUnSignFragment.this.mListView.setAdapter(RemindUnSignFragment.this.mAdapter);
		}
		super.onResume();
	}
}