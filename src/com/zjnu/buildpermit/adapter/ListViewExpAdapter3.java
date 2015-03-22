package com.zjnu.buildpermit.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.bean.Express;
import com.zjnu.buildpermit.common.CFinal;

public class ListViewExpAdapter3 extends BaseAdapter {
	private int itemViewResource;
	private LayoutInflater listContainer;
	private ArrayList<String[]> listItems;
	private Context mContext;
	private SimpleDateFormat sdf;
	private ArrayList<Express> mExpress = new ArrayList<Express>();

	public ListViewExpAdapter3(Context paramContext,
			ArrayList<String[]> paramArrayList, int paramInt) {
		this(paramContext, paramArrayList, paramInt, true);
	}

	@SuppressLint({ "SimpleDateFormat" })
	public ListViewExpAdapter3(Context paramContext,
			ArrayList<String[]> paramArrayList, int paramInt,
			boolean paramBoolean) {
		this.mContext = paramContext;
		this.listContainer = LayoutInflater.from(paramContext);
		this.itemViewResource = paramInt;
		this.listItems = paramArrayList;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public ListViewExpAdapter3(Context mContext, List<Express> mExpress,
			int capitalAirListItem) {
		this.mExpress = (ArrayList<Express>) mExpress;
		this.mContext = mContext;
		this.listContainer = LayoutInflater.from(mContext);
		this.itemViewResource = capitalAirListItem;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public int getCount() {
		return this.mExpress.size();
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
		// TextView pm_exp_list_tv_name = (TextView) ViewHolder.get(convertView,
		// R.id.pm_exp_list_tv_name);
		// TextView pm_exp_list_tv_code = (TextView) ViewHolder.get(convertView,
		// R.id.pm_exp_list_tv_code);
		TextView pm_exp_list_tv_postinfo = (TextView) ViewHolder.get(
				convertView, R.id.pm_exp_list_tv_postinfo);
		// TextView pm_exp_list_tv_status = (TextView)
		// ViewHolder.get(convertView,
		// R.id.pm_exp_list_tv_status);

		Express express = mExpress.get(position);

		// pm_exp_list_tv_name.setText(express.getName());
		// pm_exp_list_tv_code.setText(express.getCode());
		pm_exp_list_tv_postinfo.setText(express.getPostInformation());
		// pm_exp_list_tv_status.setText(express.getStatus());
		
		return convertView;

	}
}
