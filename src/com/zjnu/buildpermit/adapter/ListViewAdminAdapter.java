/**
 * @author Kimi
 */
package com.zjnu.buildpermit.adapter;

import java.util.List;

import android.content.Context;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.ProjectAdmin;

/**
 * 项目审核Adapter
 * @author Kimi
 * 
 */
public class ListViewAdminAdapter extends CommonAdapter<ProjectAdmin> {

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public ListViewAdminAdapter(Context mContext, List<ProjectAdmin> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @author Kimi
	 */
	@Override
	public void convert(ViewHolder mViewHolder, ProjectAdmin projectAdmin) {
		// TODO Auto-generated method stub
		mViewHolder.setText(R.id.pm_admin_title, projectAdmin.getProjectName());
		mViewHolder.setText(R.id.pm_admin_owner, projectAdmin.getOwner());
		mViewHolder.setText(R.id.pm_admin_type, projectAdmin.getType());
		mViewHolder.setText(R.id.pm_admin_company, projectAdmin.getCompany());

	}
}
