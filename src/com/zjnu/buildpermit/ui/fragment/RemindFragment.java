/**
 * 
 */
package com.zjnu.buildpermit.ui.fragment;

import java.util.ArrayList;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.BaseFragment;

/**
 * 快递接受类
 * 
 * @author vivid
 * 
 */
public class RemindFragment extends BaseFragment {
	@ViewInject(id = R.id.pm_rmnd_btn_apply)
	Button pm_rmnd_btn_apply;
	@ViewInject(id = R.id.pm_rmnd_btn_pay)
	Button pm_rmnd_btn_pay;
	@ViewInject(id = R.id.pm_rmnd_btn_past)
	Button pm_rmnd_btn_past;
	@ViewInject(id = R.id.pm_rmnd_btn_exam)
	Button pm_rmnd_btn_exam;
	@ViewInject(id = R.id.pm_rmnd_btn_unsign)
	Button pm_rmnd_btn_unsign;
	@ViewInject(id = R.id.pm_rmnd_frag_vpager)
	ViewPager mViewPager;
	private RemindPagerAdapter mPagerAdapter;
	private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();

	/*
	 * @author Kimi
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View viewRoot = inflater.inflate(R.layout.pm_remind, null);
		FinalActivity.initInjectedView(this, viewRoot);
		initView();
		return viewRoot;

	}

	@SuppressWarnings("rawtypes")
	private void initView() {
		pm_rmnd_btn_apply.setOnClickListener(onClickListener);
		pm_rmnd_btn_pay.setOnClickListener(onClickListener);
		pm_rmnd_btn_past.setOnClickListener(onClickListener);
		pm_rmnd_btn_exam.setOnClickListener(onClickListener);
		pm_rmnd_btn_unsign.setOnClickListener(onClickListener);
		fragmentsList.add(new RemindPastFragment());
		fragmentsList.add(new RemindUnSignFragment());
		fragmentsList.add(new RemindApplyFragment());
		fragmentsList.add(new RemindPayFragment());
		fragmentsList.add(new RemindExamragment());
		mPagerAdapter = new RemindPagerAdapter(getChildFragmentManager(), fragmentsList);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setCurrentItem(0);

	}

	private OnClickListener onClickListener = new OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.pm_rmnd_btn_past:
				mViewPager.setCurrentItem(0);
				break;
			case R.id.pm_rmnd_btn_unsign:
				mViewPager.setCurrentItem(1);
				break;
			case R.id.pm_rmnd_btn_apply:
				mViewPager.setCurrentItem(2);
				break;
			case R.id.pm_rmnd_btn_pay:
				mViewPager.setCurrentItem(3);
				break;
			case R.id.pm_rmnd_btn_exam:
				mViewPager.setCurrentItem(4);
				break;
			default:
				break;
			}
		}
	};

}
