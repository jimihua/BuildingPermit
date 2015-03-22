/**
 * @author Kimi
 */
package com.zjnu.buildpermit.adapter;

import java.util.Date;
import java.util.List;

import android.content.Context;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.Express;
import com.zjnu.buildpermit.bean.RemindApe;
import com.zjnu.buildpermit.utils.MyUtil;

/**
 * 快递签收适配器
 * 
 * @author Kimi
 * @param <T>
 * 
 */
public class ListViewExpAdapter<T> extends CommonAdapter<T> {

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public ListViewExpAdapter(Context mContext, List<T> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @author Kimi
	 */
	public void convert(ViewHolder helper, T item) {
		// TODO Auto-generated method stub
		helper.setText(R.id.pm_exp_list_tv_postinfo, ((Express) item).getPostInformation());
		helper.setText(R.id.pm_exp_list_tv_date, MyUtil.getCurrentTime());

	}
}
