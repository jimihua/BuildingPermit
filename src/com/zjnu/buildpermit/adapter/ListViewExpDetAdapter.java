/**
 * @author Kimi
 */
package com.zjnu.buildpermit.adapter;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.Express;

/**
 * 快递签收适配器
 * 
 * @author Kimi
 * @param <T>
 * 
 */
public class ListViewExpDetAdapter<T> extends CommonAdapter<T> {
	private Context mContext;
	private ViewHolder mViewHolder;

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public ListViewExpDetAdapter(Context mContext, List<T> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
		this.mContext = mContext;

	}

	/*
	 * @author Kimi
	 */
	public void convert(ViewHolder mViewHolder, T item) {
		// TODO Auto-generated method stub
		mViewHolder.setText(R.id.pm_expdet_tv_owner, ((Express) item).getOwner());
		mViewHolder.setText(R.id.pm_expdet_tv_name, ((Express) item).getName());
		mViewHolder.setText(R.id.pm_expdet_tv_certstatus,((Express) item).getStatus());

	}
}
