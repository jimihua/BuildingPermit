/**
 * 
 */
package com.zjnu.buildpermit.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.ExpressDetActivity;
import com.zjnu.buildpermit.adapter.ListViewExpAdapter;
import com.zjnu.buildpermit.adapter.ProjectAdminFragPageAdapter;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.base.BaseFragmentActivity;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.Express;
import com.zjnu.buildpermit.bean.ProjectAdmin;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.LogUtils;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.ui.fragment.ProjectAdminedFragment;
import com.zjnu.buildpermit.ui.fragment.ProjectPendFragment;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

/**
 * 项目审核
 * 
 * @author vivid
 * 
 */
@SuppressWarnings("all")
public class ProjectAdminActivity extends BaseFragmentActivity {
	private String TAG = this.getClass().getName();

	@ViewInject(id = R.id.radioGroup)
	RadioGroup radioGroup;

	/**
	 * 未审核
	 * 
	 * @author Kimi
	 */
	@ViewInject(id = R.id.pm_project_pending, click = "onClick")
	RadioButton pm_project_pending;

	/**
	 * 项目已审核
	 * 
	 * @author Kimi
	 */
	@ViewInject(id = R.id.pm_project_completed, click = "onClick")
	RadioButton pm_project_completed;

	/**
	 * fragment的容器
	 * 
	 * @author Kimi
	 */
	@ViewInject(id = R.id.viewpager)
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_pm_projectadmin);
		FinalActivity.initInjectedView(this);
		getMsgs();
	}

	/**
	 * 
	 */
	private void getMsgs() {
		// TODO Auto-generated method stub
		List<Fragment> mList = new ArrayList<Fragment>();
		mList.add(new ProjectPendFragment());
		mList.add(new ProjectAdminedFragment());
		viewPager.setAdapter(new ProjectAdminFragPageAdapter(getSupportFragmentManager(), mList));
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pm_project_pending:
			viewPager.setCurrentItem(0);
			break;
		case R.id.pm_project_completed:
			viewPager.setCurrentItem(1);
			break;
		default:
			break;
		}
	}
}
