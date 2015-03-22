package com.zjnu.buildpermit.ui.fragment;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.zjnu.buildpermit.R;

public class MessageFragment extends Fragment {

	@ViewInject(id = R.id.pm_setting_btn_login)
	Button pm_setting_btn_login;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.message_fragment, container, false);
		FinalActivity.initInjectedView(this, viewRoot);
		return viewRoot;

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();

	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		// TODO Auto-generated method stub
		View parent = this.getView();

	}

	private OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.pm_setting_btn_login:
				break;

			default:
				break;
			}
		}
	};

}