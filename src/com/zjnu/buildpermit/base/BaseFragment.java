package com.zjnu.buildpermit.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zjnu.buildpermit.utils.MyUtil;

/* 
 *Fragment作为Activity界面的一部分组成出现
 *可以在一个Activity中同时出现多个Fragment，并且，一个Fragment亦可在多个Activity中使用。
 *在Activity运行过程中，可以添加、移除或者替换Fragment（add()、remove()、replace()）
 *Fragment可以响应自己的输入事件，并且有自己的生命周期，当然，它们的生命周期直接被其所属的
 *宿主activity的生命周期影响。
 * 可以
 * */
public class BaseFragment<T> extends Fragment {

	protected String TAG = this.getClass().getSimpleName();
	protected Gson gson = new Gson();
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected CommonAdapter<T> mAdapter;
	protected List<T> mDatas = new ArrayList<T>();
	protected ProgressDialog mProgressDialog;
	protected Handler mHandler;
	protected boolean isFirst = true;
	protected int Page = 1;
	protected String TYPEID = "0";
	protected int TotalPage = 0;
	protected Context mContext;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		MyUtil.Log("BaseActivity", this.getActivity());
		mContext = this.getActivity();

	}

	public void exit() {
		this.getActivity().finish();
	}

	public static final void setTextStyle(TextView view, boolean bold) {
		view.getPaint().setFakeBoldText(bold);
	}

}
