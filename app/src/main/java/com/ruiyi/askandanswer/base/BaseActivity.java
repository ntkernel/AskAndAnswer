package com.ruiyi.askandanswer.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.widgets.ProgressLoading;

/**
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;
    private ProgressLoading mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始加载框
        mLoadingDialog = ProgressLoading.create(this);
        AppManager.getAppManager().addActivity(BaseActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(BaseActivity.this);
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
