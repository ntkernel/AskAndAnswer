package com.ruiyi.askandanswer.base;

import android.content.Context;

import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.utils.NetWorkUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Presenter基类
 */
public class BasePresenter{

    public BaseView mBaseView;

    /*
          检查网络是否可用
       */
    public boolean checkNetWork(Context context){
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true;
        }
        mBaseView.showError("网络不可用");
        return false;
    }
}
