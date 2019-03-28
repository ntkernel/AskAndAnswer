package com.ruiyi.askandanswer.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ruiyi.askandanswer.R;

/*
    加载对话框封装
 */
public class ProgressLoading extends Dialog {

    public static AnimationDrawable animDrawable;
    public static ProgressLoading mDialog;

    public ProgressLoading(Context context,int theme) {
        super(context);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    /*
              创建加载对话框
           */
    public static ProgressLoading create(Context context){
        //样式引入
        mDialog = new ProgressLoading(context, R.style.LightProgressDialog);
        //设置布局
        mDialog.setContentView(R.layout.progress_dialog);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        //设置属性
        mDialog.getWindow().setAttributes(lp);

        //获取动画视图
        ImageView loadingView = mDialog.findViewById(R.id.iv_loading);
        animDrawable = (AnimationDrawable) loadingView.getBackground();

        return mDialog;
    }

    /*
        显示加载对话框，动画开始
     */
    public void showLoading() {
        super.show();
        animDrawable.start();
    }

    /*
        隐藏加载对话框，动画停止
     */
    public void  hideLoading(){
        super.dismiss();
        animDrawable.stop();
    }
}
