package com.zjnu.buildpermit.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjnu.buildpermit.common.ImageLoader;
import com.zjnu.buildpermit.common.ImageLoader.Type;

public class ViewHolder {
	public static View get(View convertview, int resourceId) {
		@SuppressWarnings("unchecked")
		SparseArray<View> localSparseArray = (SparseArray<View>) convertview.getTag();
		if (localSparseArray == null) {
			localSparseArray = new SparseArray<View>();
			convertview.setTag(localSparseArray);
		}
		View localView = (View) localSparseArray.get(resourceId);
		if (localView == null) {
			localView = convertview.findViewById(resourceId);
			localSparseArray.put(resourceId, localView);
		}
		return localView;

	}

	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		// setTag
		mConvertView.setTag(this);
	}

	/**
	 * 拿到一个ViewHolder对象
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	/*
	 * 通过get方法判断当前界面是否为第一次实例化
	 * */
	public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		}
		return (ViewHolder) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 为TextView设置字符串
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);

		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageByUrl(int viewId, String url) {
		ImageLoader.getInstance(3, Type.LIFO).loadImage(url, (ImageView) getView(viewId));
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

	/**
	 * view 设置按钮事件
	 * 
	 * @param viewId
	 * @param onClickListener
	 * @return
	 */
	public ViewHolder setOnClickListener(int viewId, OnClickListener onClickListener) {
		TextView view = getView(viewId);
		view.setOnClickListener(onClickListener);
		return this;
	}

	/**
	 * 设置字体颜色
	 * 
	 * @param viewId
	 * @param mColor
	 * @return
	 */
	public ViewHolder setTextColor(int viewId, int mColor) {
		TextView view = getView(viewId);
		view.setTextColor(mColor);
		return this;
	}
	
	
	
	

}
