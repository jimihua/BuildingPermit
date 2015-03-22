/**
 * @author Kimi
 */
package com.zjnu.buildpermit.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 项目审批适配器
 * @author Kimi
 * 
 */
public class ProjectAdminFragPageAdapter extends FragmentPagerAdapter {

	private List<Fragment> mList;

	/**
	 * @param fm
	 */
	public ProjectAdminFragPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	public ProjectAdminFragPageAdapter(FragmentManager fm, List<Fragment> mList) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.mList = mList;
	}

	/*
	 * @author Kimi
	 */
	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	/*
	 * @author Kimi
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

}
