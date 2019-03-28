package com.ruiyi.askandanswer.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.ruiyi.askandanswer.base.BaseApplication;

import java.lang.reflect.Field;

public class ScreenUtils {

	public static String getWidth(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		return display.getWidth() + "," + display.getHeight();
	}

	public static float getWidthJust(Context context) {// 返回像素宽度

		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.widthPixels;

	}
	
	public static int getStatusHeight (Context comContext){

		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 48;
		try {
		    c = Class.forName("com.android.internal.R$dimen");
		    obj = c.newInstance();
		    field = c.getField("status_bar_height");
		    x = Integer.parseInt(field.get(obj).toString());
		    sbar = comContext.getResources().getDimensionPixelSize(x);
		} catch(Exception e1) {
		    e1.printStackTrace();
		}
		return sbar;
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static float dip2pxf(Context context,float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return  (dpValue * scale + 0.5f);
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(float dpValue) {
		final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(double dpValue) {
		final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(float pxValue) {
		final float scale =  BaseApplication.getInstance().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	public static int getActionbarHeight(Context context){
	    int actionBarHeight=96;
	    TypedValue tv = new TypedValue();
	    if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {  
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        } 
	    return actionBarHeight;
	}
	
	public static float getHeightJust(Context context) {// 返回像素高度
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dp2px(Context context, float pxValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context
				.getResources().getDisplayMetrics());
	}
	
	public static int sp2px(Context context, float pxValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxValue, context
				.getResources().getDisplayMetrics());
	}
}
