package com.ruiyi.askandanswer.base;

import android.util.Log;

import rx.Subscriber;

public class BaseSubscriber<T> extends Subscriber<T> {

    private BaseView mBaseView;

    public BaseSubscriber(BaseView baseView){
        this.mBaseView = baseView;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onCompleted() {
        mBaseView.hideLoading();
    }

    @Override
    public void onError(Throwable t) {
        mBaseView.hideLoading();
        Log.d("cj","Throwable=====>>>" + t.getMessage());
        if (t instanceof BaseException){
            Log.d("cj","msg=====>>>" + ((BaseException) t).getMsg());
            mBaseView.showError(((BaseException) t).getMsg());
        }
    }
}
