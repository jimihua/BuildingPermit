/**
 * @author Kimi
 */
package com.zjnu.buildpermit.adapter;

import java.util.List;

import android.content.Context;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.RemindApe;
import com.zjnu.buildpermit.bean.RemindPast;

/**
 * @author Kimi
 * @param <T>
 * 
 */
public class ListViewRmndPastAdapter<T> extends CommonAdapter<T> {

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public ListViewRmndPastAdapter(Context mContext, List<T> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @author Kimi
	 */
	public void convert(ViewHolder helper, T item) {
		// TODO Auto-generated method stub

		helper.setText(R.id.pm_rmndpast_tv_certcode, ((RemindPast) item).getCertificateCode());
		helper.setText(R.id.pm_rmndpast_tv_certname, ((RemindPast) item).getCertificateName());
		helper.setText(R.id.pm_rmndpast_tv_certowner, ((RemindPast) item).getCertificateOwner());
		helper.setText(R.id.pm_rmndpast_tv_compname, ((RemindPast) item).getCompanyName());
		helper.setText(R.id.pm_rmndpast_tv_duetime, ((RemindPast) item).getDueTime());
	}
}
