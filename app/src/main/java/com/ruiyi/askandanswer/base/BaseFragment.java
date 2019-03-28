package com.ruiyi.askandanswer.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.widgets.ProgressLoading;

public class BaseFragment<T extends BasePresenter>  extends Fragment implements BaseView {

    protected T mPresenter;
    public Context mContext;
    private ProgressLoading mLoadingDialog;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;//mCtx 是成员变量，上下文引用
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始加载框
        mLoadingDialog = ProgressLoading.create(mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void showLoading() {
        mLoadingDialog.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.hideLoading();
    }

    @Override
    public void showError(String msg) {
        ToastUtil.show(msg);
    }
}
