package com.faywong.circlelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * A circle layout to hold some widgets symmetrically padding property is
 * supported
 */
public class CircleLayout extends ViewGroup {

	public CircleLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CircleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CircleLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int w = r - l;
		int h = b - t;
		int padding_l = getPaddingLeft();
		int padding_t = getPaddingTop();
		int padding_r = getPaddingRight();
		int padding_b = getPaddingBottom();
		Log.d("CircleLayout", "padding_l:" + padding_l + " padding_t:"
				+ padding_t + " padding_r:" + padding_r + " padding_b:"
				+ padding_b);

		// double radius = Math.min(w / 2, h / 2);
		double radius = Math.min((w - padding_l - padding_r) / 2, (h
				- padding_t - padding_b) / 2);

		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			measureChild(child, w, h);
			double v = Math.toRadians(360 * i / count);
			int child_width = child.getMeasuredWidth();
			int child_height = child.getMeasuredHeight();
			int child_x = (r - padding_r + l + padding_l) / 2
					+ (int) (Math.cos(v) * radius);
			int child_y = (t + padding_t + b - padding_b) / 2
					+ (int) (Math.sin(v) * radius);

			child_x = Math.max(Math.min(r - child_width - padding_r, child_x),
					l + padding_l);
			child_y = Math.max(Math.min(b - child_height - padding_b, child_y),
					t + padding_t);
			int child_r = Math.min(r - padding_r, child_x + child_width);
			int child_b = Math.min(b - padding_b, child_y + child_height);

			child.layout(child_x, child_y, child_r, child_b);
		}
	}
}
