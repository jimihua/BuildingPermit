/**
 * 
 */
package com.zjnu.buildpermit.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.ui.fragment.ExpressFragment;
import com.zjnu.buildpermit.ui.fragment.ExpressFragmentold;
import com.zjnu.buildpermit.ui.fragment.FragmentPage2;
import com.zjnu.buildpermit.ui.fragment.FragmentPage3;
import com.zjnu.buildpermit.ui.fragment.NewsFragment;
import com.zjnu.buildpermit.ui.fragment.NoticeFragment;
import com.zjnu.buildpermit.ui.fragment.RemindFragment;
import com.zjnu.buildpermit.ui.fragment.MessageFragment;

/**
 * @author vivid
 * 
 */

public class MainBuildingActivity extends FragmentActivity {

	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;

	// 定义一个布局
	private LayoutInflater layoutInflater;
	private FragmentManager fragmentManager = getSupportFragmentManager();
	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = { NoticeFragment.class, ExpressFragment.class, RemindFragment.class, MessageFragment.class, NewsFragment.class };

	// 定义数组来存放按钮图片
	private int mImageViewArray[] = { R.drawable.icon_notice, R.drawable.icon_express, R.drawable.tab_selfinfo_btn, R.drawable.tab_square_btn, R.drawable.tab_more_btn };

	// Tab选项卡的文字
	private String mTextviewArray[] = { "公告查看", "快递签收", "今日提醒", "消息通知", "更多" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab_layout);

		initView();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}

	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}

	private boolean changeFragment(int id, FragmentPagerAdapter mPagerAdapter) {

		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.pm_rmnd_frag_contain, mPagerAdapter.getItem(id));
		fragmentTransaction.commit();
		return true;

	}
}
