package com.ruiyi.askandanswer.service.impl;

import com.ruiyi.askandanswer.data.net.RetrofitFactory;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.PayService;

import retrofit2.Retrofit;
import rx.Observable;

public class PayServiceImpl implements PayService {

    public static Retrofit retrofit = null;

    static {
        retrofit = RetrofitFactory.getIntance().getRetrofit();
    }

    @Override
    public Observable<BaseResp<String>> getAliAppPay(String token, String totalAmout) {
        return retrofit.create(PayService.class).getAliAppPay(token,totalAmout);
    }

    @Override
    public Observable<BaseResp<String>> getWeChatPay(String token, String totalAmout) {
        return retrofit.create(PayService.class).getWeChatPay(token,totalAmout);
    }
}
