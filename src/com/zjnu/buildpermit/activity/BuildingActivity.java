/**
 * @author Kimi
 */
package com.zjnu.buildpermit.activity;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.utils.MyUtil;

/**
 * @author Kimi
 * 
 */
public class BuildingActivity extends BaseActivity {

	/**
	 * 快递签收
	 */
	@ViewInject(id = R.id.send_express_layout, click = "onClick")
	RelativeLayout send_express_layout;

	/**
	 * 通知
	 */
	@ViewInject(id = R.id.notice_layout, click = "onClick")
	RelativeLayout notice_layout;

	/**
	 * 公告
	 */
	@ViewInject(id = R.id.announce_layout, click = "onClick")
	RelativeLayout announce_layout;

	/**
	 * 消息
	 */
	@ViewInject(id = R.id.message_layout, click = "onClick")
	RelativeLayout message_layout;

	/**
	 * 项目审核
	 */
	@ViewInject(id = R.id.admin_layout, click = "onClick")
	RelativeLayout admin_layout;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_pm_main);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.send_express_layout:
			MyUtil.JumpPages(mContext, ExpressActivity.class, "express");
			break;

		case R.id.notice_layout:
			MyUtil.JumpPages(mContext, NoticeActivity.class, "notice");
			break;
		case R.id.announce_layout:

			break;
		case R.id.message_layout:
			MyUtil.JumpPages(mContext, NewsAcitivity.class, "notice");
			break;
		case R.id.admin_layout:
			MyUtil.JumpPages(mContext, ProjectAdminActivity.class, "admin");
			break;
		default:
			break;
		}
	}
}
