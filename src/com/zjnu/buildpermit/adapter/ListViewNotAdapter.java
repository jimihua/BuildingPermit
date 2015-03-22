package com.zjnu.buildpermit.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.bean.Express;
import com.zjnu.buildpermit.bean.Notice;
import com.zjnu.buildpermit.utils.MyUtil;

public class ListViewNotAdapter extends BaseAdapter {
	private int itemViewResource;
	private LayoutInflater listContainer;
	private ArrayList<String[]> listItems;
	private Context mContext;
	private SimpleDateFormat sdf;
	private ArrayList<Notice> mNotices = new ArrayList<Notice>();

	public ListViewNotAdapter(Context paramContext,
			ArrayList<String[]> paramArrayList, int paramInt) {
		this(paramContext, paramArrayList, paramInt, true);
	}

	@SuppressLint({ "SimpleDateFormat" })
	public ListViewNotAdapter(Context paramContext,
			ArrayList<String[]> paramArrayList, int paramInt,
			boolean paramBoolean) {
		this.mContext = paramContext;
		this.listContainer = LayoutInflater.from(paramContext);
		this.itemViewResource = paramInt;
		this.listItems = paramArrayList;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public ListViewNotAdapter(Context mContext, List<Notice> mNotices,
			int capitalAirListItem) {
		this.mNotices = (ArrayList<Notice>) mNotices;
		this.mContext = mContext;
		this.listContainer = LayoutInflater.from(mContext);
		this.itemViewResource = capitalAirListItem;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public int getCount() {
		return this.mNotices.size();
	}

	public Object getItem(int paramInt) {
		return null;
	}

	public long getItemId(int paramInt) {
		return 0L;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = this.listContainer.inflate(this.itemViewResource,
					null);
		TextView pm_not_list_tv_not = (TextView) ViewHolder.get(convertView,
				R.id.pm_not_list_tv_title);
		TextView pm_not_list_tv_date = (TextView) ViewHolder.get(convertView,
				R.id.pm_not_list_tv_date);
		MyUtil.Log("position", position);
		
		Notice mNotice = mNotices.get(position);
		pm_not_list_tv_not.setText(mNotice.getTitle());
		if (mNotice.getIsRed().equals(true)) {
			pm_not_list_tv_not.setTextColor(Color.RED);
		}
		pm_not_list_tv_date.setText(mNotice.getCreateTime());

		return convertView;
	}
}
