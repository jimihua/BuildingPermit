package com.zjnu.buildpermit.ui.fragment;

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

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.NoticeDetActivity;
import com.zjnu.buildpermit.adapter.ListViewNotAdapter;
import com.zjnu.buildpermit.adapter.ViewHolder;
import com.zjnu.buildpermit.base.BaseFragment;
import com.zjnu.buildpermit.base.CommonAdapter;
import com.zjnu.buildpermit.bean.News;
import com.zjnu.buildpermit.bean.Notice;
import com.zjnu.buildpermit.bean.RemindPast;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;
import com.zjnu.buildpermit.view.XListView;

public class NewsFragment extends BaseFragment {
	@ViewInject(id = R.id.xlistview)
	ListView mListView;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	private Context mContext;
	private Handler mHandler;
	private ListViewNotAdapter mAdapter;
	private List<News> newsList = new ArrayList<News>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.activity_news, container, false);

		FinalActivity.initInjectedView(this, viewRoot);
		this.mContext = this.getActivity();

		geneItems();
		this.mHandler = new Handler();
		this.mListView.setDividerHeight(0);

		return viewRoot;
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
				newsList.add(news);
				mListView.setAdapter(new CommonAdapter<News>(mContext, newsList, R.layout.pm_news_item) {

					@Override
					public void convert(ViewHolder helper, News item) {
						// TODO Auto-generated method stub
					
					}

				});
			}
		});
	}

	private String getNewsMsg(News news) {
		StringBuilder stringBuilder = new StringBuilder();
		Field[] fields = news.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				if (!field.get(news).equals("0")) {
					stringBuilder.append(MyUtil.getNews(field.getName())).append(field.get(news) + "件").append(",");
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stringBuilder.append("请及时处理");
		return stringBuilder.toString();

	}

	/*
	 * @author Kimi
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}