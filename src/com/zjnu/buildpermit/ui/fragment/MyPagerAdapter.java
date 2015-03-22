package com.zjnu.buildpermit.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyPagerAdapter extends FragmentPagerAdapter {


	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 1:
			 return new NoticeFragment();
		case 2:
			// return new LoanShowMainFragment(menu);
		case 3:
			// return new RepaymentMainActivity(menu);
		case 4:
			// return new MainAdviceFragment(menu);

		}
		return null;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
