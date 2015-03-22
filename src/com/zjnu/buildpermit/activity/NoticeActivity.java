/**
 * 
 */
package com.zjnu.buildpermit.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.NoticeDetActivity;
import com.zjnu.buildpermit.adapter.ListViewNotAdapter;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.base.BaseListFragment;
import com.zjnu.buildpermit.bean.Notice;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

/**
 * 公告类
 * 
 * @author vivid
 * 
 */
public class NoticeActivity extends BaseActivity<Notice> {
	private String TAG = this.getClass().getName();
	@ViewInject(id = R.id.pm_notice_lv_not)
	ListView mListView;
	@ViewInject(id = R.id.main_top_tv_title)
	TextView main_top_tv_title;
	private boolean isFirst = true;
	private ArrayList<Notice> mNotices = new ArrayList<Notice>();
	private ListViewNotAdapter mAdapter;

	@SuppressWarnings("unchecked")
	protected void geneItems() {
		MyUtil.Log("notice", "geneitem");
		this.mProgressDialog = ProgressDialog.show(this.mContext, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		AjaxParams params = new AjaxParams();

		CFinal.fh.post(Urls.URL_NOTICE, params, new AjaxCallBack() {
			public void onFailure(Throwable t, int paramAnonymousInt, String strMsg) {
				super.onFailure(t, paramAnonymousInt, strMsg);
				NoticeActivity.this.mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				System.out.println(object);
				String str = object.toString();
				str = str.substring(str.indexOf("["), str.length() - 1);
				
				try {
					NoticeActivity.this.mNotices = gson.fromJson(str, new TypeToken<List<Notice>>() {
					}.getType());
				} catch (Exception e) {

					MyUtil.Log(TAG, e);
				}
				for (Notice notice : mNotices) {
					MyUtil.Log("noticeid", notice.getNoticeId());
					MyUtil.Log("notice", notice.getTitle());
				}
				if (NoticeActivity.this.isFirst) {
					NoticeActivity.this.isFirst = false;
					NoticeActivity.this.mAdapter = new ListViewNotAdapter(NoticeActivity.this.mContext, NoticeActivity.this.mNotices, R.layout.pm_not_list_item);
					NoticeActivity.this.mListView.setAdapter(NoticeActivity.this.mAdapter);
					MyUtil.Log("notice", "adapter");

				}
				NoticeActivity.this.mAdapter.notifyDataSetChanged();

				NoticeActivity.this.mProgressDialog.dismiss();
			}
		});

	}

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.pm_notice);
		geneItems();
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {
				MyUtil.JumpPages(mContext, NoticeDetActivity_v2.class, "position", mNotices.get(position).getNoticeId());
			}
		});
	}

}
