package com.ruiyi.askandanswer.user.presenter;

import android.content.Context;
import android.os.Handler;

import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.impl.UserServiceImpl;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.view.LoginView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
    登录界面 Presenter
 */
public class LoginPresenter extends BasePresenter {

    private String TAG = "LoginPresenter";
    UserServiceImpl userService;
    private Context mContext;
    private Handler mHandler;

    public LoginPresenter(Context context) {
        this.mContext = context;
        userService = new UserServiceImpl();
    }

    public LoginPresenter(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        userService = new UserServiceImpl();
    }

    /*
           登录
        */
    public void login(String phone, String pwd, int type) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        userService.login(phone, pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<UserInfo>>(mBaseView) {
                    @Override
                    public void onNext(BaseResp<UserInfo> userInfoBaseResp) {
                        super.onNext(userInfoBaseResp);
                        mBaseView.hideLoading();
                        if (userInfoBaseResp.getStatusCode() == 200) {
                            if (type != -1) {//登陆过期
                                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN, userInfoBaseResp.getData().getTokenString());
                                mHandler.sendEmptyMessage(type);
                            } else {//正常登陆
                                ((LoginView) mBaseView).onLoginResult(userInfoBaseResp.getData());
                            }
                        } else
                            mBaseView.showError(userInfoBaseResp.getMessage());
                    }
                });
    }
}
