package com.ruiyi.askandanswer.service;

import com.ruiyi.askandanswer.data.protocol.BaseResp;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface PayService {

    //支付宝支付
    @GET("PayMent/AliAppPay")
    Observable<BaseResp<String>> getAliAppPay(@Header("Authorization") String token, @Query("totalAmout") String totalAmout);

    //微信支付
    @GET("PayMent/WeChatAppPay")
    Observable<BaseResp<String>> getWeChatPay(@Header("Authorization") String token, @Query("totalAmout") String totalAmout);
}
