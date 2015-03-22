/**
 * 
 */
package com.zjnu.buildpermit.ui.fragment;

import java.text.SimpleDateFormat;
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
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.adapter.ListViewExpAdapter3;
import com.zjnu.buildpermit.base.BaseListFragment;
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
public class ExpressFragmentold extends BaseListFragment implements XListView.IXListViewListener {
	private String TAG = this.getClass().getName();
	private int red = Color.rgb(255, 120, 0);
	private int black = Color.rgb(67, 67, 67);
	@ViewInject(id = R.id.pm_exprcv_lv_kuaidi)
	XListView mListView;
	@ViewInject(id = R.id.main_top_tv_title)
	TextView main_top_tv_title;
	@ViewInject(id = R.id.pm_exp_btn_all)
	Button pm_exp_btn_all;
	@ViewInject(id = R.id.pm_exp_btn_ysz)
	Button pm_exp_btn_ysz;
	@ViewInject(id = R.id.pm_exp_btn_dqs)
	Button pm_exp_btn_dqs;
	@ViewInject(id = R.id.pm_exp_btn_yqs)
	Button pm_exp_btn_yqs;

	private int Page = 1;
	private String TYPEID = "0";
	private int TotalPage = 0;
	private Gson gson;
	private boolean isFirst = true;
	private Context mContext;
	private Handler mHandler;
	private ArrayList<Express> mExpresses = new ArrayList<Express>();
	private ArrayList<Express> mSameExpress = new ArrayList<Express>();
	private ProgressDialog mProgressDialog = null;
	private SimpleDateFormat sdf;
	private ListViewExpAdapter3 mAdapter;
	private HashMap<String, String> transId = new HashMap<String, String>();

	@SuppressWarnings("unchecked")
	protected void geneItems() {

		this.mProgressDialog = ProgressDialog.show(this.mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		AjaxParams params = new AjaxParams();

		CFinal.fh.post(Urls.URL_EXPS, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);

				ExpressFragmentold.this.mProgressDialog.dismiss();
			}

			public void onLoading(long count, long current) { // 每1秒钟自动被回调一次

			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);
				MyUtil.Log(TAG, str);
				try {
					ExpressFragmentold.this.mExpresses = gson.fromJson(str, new TypeToken<List<Express>>() {
					}.getType());
				} catch (Exception e) {

					MyUtil.Log(TAG, e);
				}

				for (Express mExpress : mExpresses) {
					if (!transId.containsValue(mExpress.getTransportId())) {
						mSameExpress.add(mExpress);
						transId.put("transid", mExpress.getTransportId());
					}
				}

				if (ExpressFragmentold.this.isFirst) {
					ExpressFragmentold.this.isFirst = false;
					ExpressFragmentold.this.mAdapter = new ListViewExpAdapter3(ExpressFragmentold.this.mContext, ExpressFragmentold.this.mSameExpress, R.layout.pm_exp_list_item);
					ExpressFragmentold.this.mListView.setAdapter(ExpressFragmentold.this.mAdapter);
				}
				ExpressFragmentold.this.mAdapter.notifyDataSetChanged();

				ExpressFragmentold.this.mProgressDialog.dismiss();
			}
		});

		if (ExpressFragmentold.this.isFirst) {
			ExpressFragmentold.this.isFirst = false;
			ExpressFragmentold.this.mAdapter = new ListViewExpAdapter3(ExpressFragmentold.this.mContext, ExpressFragmentold.this.mSameExpress, R.layout.pm_exp_list_item);
			ExpressFragmentold.this.mListView.setAdapter(ExpressFragmentold.this.mAdapter);
		}
		ExpressFragmentold.this.mAdapter.notifyDataSetChanged();

	}

	private void onLoad() {
		this.mListView.stopRefresh();
		this.mListView.stopLoadMore();
		this.mListView.setRefreshTime(this.sdf.format(new Date()));
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View viewRoot = inflater.inflate(R.layout.pm_express, container, false);
		FinalActivity.initInjectedView(this, viewRoot);
		this.mContext = this.getActivity();
		this.gson = new Gson();
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.mListView.setPullLoadEnable(true);
		geneItems();
		main_top_tv_title.setText("快递接收");
		this.mListView.setXListViewListener(this);
		this.mHandler = new Handler();
		this.mListView.setDividerHeight(0);
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {

			}
		});
		return viewRoot;
	}


	public void onLoadMore() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				if (ExpressFragmentold.this.TotalPage > ExpressFragmentold.this.Page) {
					ExpressFragmentold localExpressReceiveActivity = ExpressFragmentold.this;
					localExpressReceiveActivity.Page = (1 + localExpressReceiveActivity.Page);
					ExpressFragmentold.this.geneItems();
				}
				ExpressFragmentold.this.onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				ExpressFragmentold.this.Page = 1;
				ExpressFragmentold.this.isFirst = true;
				ExpressFragmentold.this.geneItems();

				ExpressFragmentold.this.mAdapter = new ListViewExpAdapter3(ExpressFragmentold.this.mContext, ExpressFragmentold.this.mSameExpress, R.layout.pm_exp_list_item);
				ExpressFragmentold.this.mListView.setAdapter(ExpressFragmentold.this.mAdapter);
				ExpressFragmentold.this.onLoad();
			}
		}, 100L);
	}

	public void onBaseRefresh(String status) {

		ExpressFragmentold.this.Page = 1;
		ArrayList<Express> statusExps = getStatusExpress(ExpressFragmentold.this.mSameExpress, status);
		ExpressFragmentold.this.mAdapter = new ListViewExpAdapter3(ExpressFragmentold.this.mContext, statusExps, R.layout.pm_exp_list_item);
		ExpressFragmentold.this.mListView.setAdapter(ExpressFragmentold.this.mAdapter);
		ExpressFragmentold.this.onLoad();

	}

	private OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View view) {
			switch (view.getId()) {
			default:
				break;
			case R.id.pm_exp_btn_all:
				ExpressFragmentold.this.TYPEID = "0";
				ExpressFragmentold.this.pm_exp_btn_all.setTextColor(red);
				ExpressFragmentold.this.pm_exp_btn_dqs.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_yqs.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_ysz.setTextColor(black);
				onRefresh();
				break;
			case R.id.pm_exp_btn_ysz:
				ExpressFragmentold.this.TYPEID = "1";
				ExpressFragmentold.this.pm_exp_btn_all.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_dqs.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_yqs.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_ysz.setTextColor(red);
				onBaseRefresh("运输中");
				break;
			case R.id.pm_exp_btn_dqs:
				ExpressFragmentold.this.TYPEID = "2";
				ExpressFragmentold.this.pm_exp_btn_all.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_dqs.setTextColor(red);
				ExpressFragmentold.this.pm_exp_btn_yqs.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_ysz.setTextColor(black);
				onBaseRefresh("代签收");
				break;
			case R.id.pm_exp_btn_yqs:
				ExpressFragmentold.this.TYPEID = "3";
				ExpressFragmentold.this.pm_exp_btn_all.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_dqs.setTextColor(black);
				ExpressFragmentold.this.pm_exp_btn_yqs.setTextColor(red);
				ExpressFragmentold.this.pm_exp_btn_ysz.setTextColor(black);
				onBaseRefresh("已签收");
				break;

			}// TODO Auto-generated method stub

		}
	};

	/**
	 * 获得快递过滤数据，根据Status过滤
	 * 
	 * @param status
	 * @return
	 */
	private ArrayList<Express> getStatusExpress(ArrayList<Express> mExpresses, String status) {
		ArrayList<Express> statusExps = new ArrayList<Express>();
		for (Express express : mExpresses) {
			if (express.getStatus().equals("在库") || express.getStatus().equals("使用中")) {
				if (status.equals("已签收")) {
					statusExps.add(express);
				}
			}
			if (status.equals(express.getStatus())) {
				statusExps.add(express);
			}
		}
		return statusExps;

	}
}
