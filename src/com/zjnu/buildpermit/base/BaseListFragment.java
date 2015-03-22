/**
 * @author Kimi
 */
package com.zjnu.buildpermit.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zjnu.buildpermit.utils.MyUtil;

/**
 * @author Kimi
 * @param <T>
 * 
 */
public abstract class BaseListFragment<T> extends BaseFragment {

	protected int Page = 1;
	protected String TYPEID = "0";
	protected int TotalPage = 0;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected CommonAdapter<T> mAdapter;
	protected List<T> mDatas = new ArrayList<T>();
	protected ProgressDialog mProgressDialog = null;
	protected Handler mHandler;
	protected boolean isFirst = true;

	/**
	 * 获取数据
	 */

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	/*
	 * @author Kimi
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		return super.onCreateView(inflater, container, savedInstanceState);

	}
}
