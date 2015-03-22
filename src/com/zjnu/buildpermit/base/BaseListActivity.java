/**
 * @author Kimi
 */
package com.zjnu.buildpermit.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Handler;

/**
 * @author Kimi
 * @param <T>
 * 
 */
public class BaseListActivity<T> extends BaseActivity {
	protected List<T> mDatas = new ArrayList<T>();
	protected int Page = 1;
	protected String TYPEID = "0";
	protected int TotalPage = 0;
	@SuppressLint("SimpleDateFormat")
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected CommonAdapter<T> mAdapter;
	protected ProgressDialog mProgressDialog = null;
	protected Handler mHandler;
	protected boolean isFirst = true;

}
