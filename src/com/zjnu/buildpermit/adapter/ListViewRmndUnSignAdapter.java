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
import com.zjnu.buildpermit.bean.RemindUnSign;

/**
 * @author Kimi
 * @param <T>
 * 
 */
public class ListViewRmndUnSignAdapter<T> extends CommonAdapter<T> {

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public ListViewRmndUnSignAdapter(Context mContext, List<T> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	/*
	 */
	public void convert(ViewHolder helper, T item) {
		// TODO Auto-generated method stub

		helper.setText(R.id.pm_rmndunsign_tv_reccomp, ((RemindUnSign) item).getReceivedCompany());
		helper.setText(R.id.pm_rmndunsign_tv_recemp, ((RemindUnSign) item).getReceivedEmployee());
		helper.setText(R.id.pm_rmndunsign_tv_rmndtime, ((RemindUnSign) item).getBeginRemindTime());
		helper.setText(R.id.pm_rmndunsign_tv_sendtime, ((RemindUnSign) item).getSendTime());
		helper.setText(R.id.pm_rmndunsign_tv_sendtype, ((RemindUnSign) item).getSendType());
	}
}
