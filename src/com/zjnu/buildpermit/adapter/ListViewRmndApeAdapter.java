/**
 * @author Kimi
 */
package com.zjnu.buildpermit.adapter;

import java.util.List;

import android.content.Context;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.RemindApe;

/**
 * @author Kimi
 * @param <T>
 * 
 */
public class ListViewRmndApeAdapter<T> extends CommonAdapter<T> {

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public ListViewRmndApeAdapter(Context mContext, List<T> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @author Kimi
	 */
	public void convert(ViewHolder helper, T item) {
		// TODO Auto-generated method stub

		helper.setText(R.id.pm_rmndape_tv_rmndtime, ((RemindApe) item).getBeginRemindTime());
		helper.setText(R.id.pm_rmndape_tv_duetime, ((RemindApe) item).getDueTime());
		helper.setText(R.id.pm_rmndape_tv_info, ((RemindApe) item).getInfo());

	}
}
