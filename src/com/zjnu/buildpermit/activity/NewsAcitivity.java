package com.zjnu.buildpermit.activity;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.NoticeDetActivity;
import com.zjnu.buildpermit.adapter.ListViewNotAdapter;
import com.zjnu.buildpermit.adapter.ViewHolder;
import com.zjnu.buildpermit.base.BaseActivity;
import com.zjnu.buildpermit.base.BaseFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.News;
import com.zjnu.buildpermit.bean.Notice;
import com.zjnu.buildpermit.bean.RemindPast;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

public class NewsAcitivity extends BaseActivity implements OnItemClickListener {
	@ViewInject(id = R.id.xlistview)
	ListView mListView;

	@ViewInject(id = R.id.main_top_tv_title)
	TextView main_top_tv_title;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_news);
		main_top_tv_title.setText("消息");
		geneItems();
		mListView.setOnItemClickListener(this);
	}

	private void geneItems() {
		AjaxParams mParams = new AjaxParams();

		CFinal.fh.post(Urls.URL_GETISNEW, mParams, new AjaxCallBack<Object>() {
			public void onFailure(Throwable t, int paramAnonymousInt, String failure) {
				super.onFailure(t, paramAnonymousInt, failure);

			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				String msg = object.toString();

				final News news = gson.fromJson(msg, News.class);
				mDatas = getNewsMsg(news);
				mListView.setAdapter(new CommonAdapter<String>(mContext, mDatas, R.layout.pm_news_item) {

					@Override
					public void convert(ViewHolder helper, String str) {
						// TODO Auto-generated method stub
						helper.setText(R.id.msg_title, str);
						helper.setText(R.id.msg_date, sdf.format(new Date()).toString());
					}

				});

			}
		});
	}

	private List<String> getNewsMsg(News news) {
		List<String> lists = new ArrayList<String>();
		Field[] fields = news.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				if (!field.get(news).equals("0")) {
					lists.add(MyUtil.getNews(field.getName()) + field.get(news) + "件");

				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lists;

	}

	/*
	 * @author Kimi
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

}