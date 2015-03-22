/**
 * 
 */
package com.zjnu.buildpermit.ui.fragment;

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
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.ExpressDetActivity;
import com.zjnu.buildpermit.adapter.ListViewExpAdapter;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.Express;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

/**
 * 快递接受类
 * 
 * @author vivid
 * 
 */
public class ExpressFragment extends BaseListFragment implements XListView.IXListViewListener {
	private String TAG = this.getClass().getName();

	@ViewInject(id = R.id.pm_exprcv_lv_kuaidi)
	XListView mListView;
	@ViewInject(id = R.id.main_top_tv_title)
	TextView main_top_tv_title;
	private Context mContext;
	private Handler mHandler;
	private ArrayList<Express> mExpresses = new ArrayList<Express>();
	private ArrayList<Express> mSameExpress=new ArrayList<Express>();
	private HashMap<String, String> transId = new HashMap<String, String>();
	private boolean isFirst = true;
	private CommonAdapter<Express> mAdapter;

	@SuppressWarnings("unchecked")
	protected void geneItems() {

		this.mProgressDialog = ProgressDialog.show(this.mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		AjaxParams params = new AjaxParams();

		CFinal.fh.post(Urls.URL_EXPS, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				ExpressFragment.this.mProgressDialog.dismiss();
				MyUtil.Log("fail", strMsg.toString());
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);

				try {
					ExpressFragment.this.mExpresses = gson.fromJson(str, new TypeToken<List<Express>>() {
					}.getType());
				} catch (Exception e) {
					MyUtil.Log(TAG, e);
				}
				mSameExpress = new ArrayList<Express>();
				for (Express mExpress : mExpresses) {
					if (!transId.containsValue(mExpress.getTransportId())) {
						mSameExpress.add(mExpress);
						transId.put("transid", mExpress.getTransportId());
					}
				}

				if (mAdapter == null) {
					ExpressFragment.this.mAdapter = new ListViewExpAdapter(ExpressFragment.this.mContext, ExpressFragment.this.mSameExpress, R.layout.pm_exp_list_item);
					ExpressFragment.this.mListView.setAdapter(ExpressFragment.this.mAdapter);
					MyUtil.Log("log", "12");
				} else {
					ExpressFragment.this.mListView.setAdapter(ExpressFragment.this.mAdapter);
					ExpressFragment.this.mAdapter.notifyDataSetChanged();
					MyUtil.Log("log", "13");
				}
				MyUtil.Log("log", mSameExpress.get(0).getCertificateId());

				ExpressFragment.this.mProgressDialog.dismiss();
			}
		});
	}

	private void onLoad() {

		this.mListView.stopRefresh();
		this.mListView.stopLoadMore();
		this.mListView.setRefreshTime(this.sdf.format(new Date()));
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View viewRoot = inflater.inflate(R.layout.pm_express, container, false);
		MyUtil.Log("log", "4");
		MyUtil.Log("log", isFirst);
		FinalActivity.initInjectedView(this, viewRoot);
		this.mContext = this.getActivity();
		this.mListView.setPullLoadEnable(true);
		geneItems();

		main_top_tv_title.setText("快递接收");
		this.mListView.setXListViewListener(this);
		this.mHandler = new Handler();
		this.mListView.setDividerHeight(0);
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {
				MyUtil.JumpPages(mContext, ExpressDetActivity.class, "exp", mExpresses);
				String[] msg = { mSameExpress.get(position - 1).getTransportId() };
				CFinal.setPutMsg(msg);
				System.out.println(mSameExpress.get(position - 1).getTransportId());
			}
		});
		return viewRoot;
	}

	public void onLoadMore() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {

				if (ExpressFragment.this.TotalPage > ExpressFragment.this.Page) {
					ExpressFragment localExpressReceiveActivity = ExpressFragment.this;
					localExpressReceiveActivity.Page = (1 + localExpressReceiveActivity.Page);
					ExpressFragment.this.geneItems();
				}
				ExpressFragment.this.onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				ExpressFragment.this.Page = 1;
				ExpressFragment.this.isFirst = true;
				ExpressFragment.this.geneItems();
				ExpressFragment.this.mAdapter = new ListViewExpAdapter(ExpressFragment.this.mContext, mDatas, R.layout.pm_exp_list_item);
				ExpressFragment.this.mListView.setAdapter(ExpressFragment.this.mAdapter);
				ExpressFragment.this.onLoad();
			}
		}, 100L);
	}

	/*
	 * @author Kimi
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MyUtil.Log("log", "1");

	}

	
	

}
