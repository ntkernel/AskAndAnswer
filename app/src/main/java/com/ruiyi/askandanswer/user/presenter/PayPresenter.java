package com.ruiyi.askandanswer.user.presenter;

import android.content.Context;
import android.os.Handler;

import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.impl.PayServiceImpl;
import com.ruiyi.askandanswer.service.impl.UserServiceImpl;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.view.LoginView;
import com.ruiyi.askandanswer.user.view.PayView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
    支付界面 Presenter
 */
public class PayPresenter extends LoginPresenter {

    PayServiceImpl payService;
    private Context mContext;

    public PayPresenter(Context context,Handler handler){
        super(context,handler);
        this.mContext = context;
        payService = new PayServiceImpl();
    }

    /*
           支付宝支付
        */
    public void getAliAppPay(String money,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        payService.getAliAppPay("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),money).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<String>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<String> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((PayView) mBaseView).onAliPayResult(baseResp.getData());
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        }else
                            mBaseView.showError(baseResp.getMessage());
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(t.getMessage().equals(BaseConstant.RESULT_401))
                        login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
                                AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        super.onError(t);
                    }
                });
    }

    /*
              微信支付
           */
    public void getWeChatPay(String money,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        payService.getWeChatPay("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),money).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<String>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<String> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((PayView) mBaseView).onWeChatResult(baseResp.getData());
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        }else
                            mBaseView.showError(baseResp.getMessage());
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(t.getMessage().equals(BaseConstant.RESULT_401))
                        login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
                                AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        super.onError(t);
                    }
                });
    }
}
