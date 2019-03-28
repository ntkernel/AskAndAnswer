package com.ruiyi.askandanswer.service;

import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.main.model.response.BannerData;
import com.ruiyi.askandanswer.main.model.response.HomeCanKnow;
import com.ruiyi.askandanswer.main.model.request.HomeCanKnowReq;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

public interface HomeService {

    //获取首页想知道
    @POST("AskAnswer/FirstShow")
    Observable<BaseResp<HomeCanKnow>> canKnow(@Header("Authorization") String token, @Body HomeCanKnowReq homeCanKnow);

    //获取轮播图
    @POST("AskAnswer/Banner")
    Observable<BaseResp<List<BannerData>>> getBanner(@Header("Authorization") String token);
}
