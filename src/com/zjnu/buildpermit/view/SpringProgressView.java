package com.zjnu.buildpermit.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SpringProgressView extends View {
	private static int[] arrayOfInt;
	private static final int[] SECTION_COLORS = arrayOfInt;
	private float currentCount;
	private int mHeight;
	private Paint mPaint;
	private int mWidth;
	private float maxCount;

	static {
		int[] arrayOfInt = new int[3];
		arrayOfInt[0] = Color.rgb(31, 187, 0);
		arrayOfInt[1] = Color.rgb(31, 187, 0);
		arrayOfInt[2] = Color.rgb(31, 187, 0);
	}

	public SpringProgressView(Context paramContext) {
		super(paramContext);
		initView(paramContext);
	}

	public SpringProgressView(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		initView(paramContext);
	}

	public SpringProgressView(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		initView(paramContext);
	}

	private int dipToPx(int paramInt) {
		float f = getContext().getResources().getDisplayMetrics().density
				* paramInt;
		if (paramInt >= 0)
			;
		for (int i = 1;; i = -1)
			return (int) (f + 0.5F * i);
	}

	private void initView(Context paramContext) {
	}

	public float getCurrentCount() {
		return this.currentCount;
	}

	public float getMaxCount() {
		return this.maxCount;
	}

	public void setCurrentCount(float paramFloat) {
		if (paramFloat > this.maxCount)
			paramFloat = this.maxCount;
		this.currentCount = paramFloat;
		invalidate();
	}

	public void setMaxCount(float paramFloat) {
		this.maxCount = paramFloat;
	}
}

