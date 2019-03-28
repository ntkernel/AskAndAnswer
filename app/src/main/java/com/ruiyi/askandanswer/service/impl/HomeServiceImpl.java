package com.ruiyi.askandanswer.service.impl;

import com.ruiyi.askandanswer.data.net.RetrofitFactory;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.main.model.response.BannerData;
import com.ruiyi.askandanswer.main.model.response.HomeCanKnow;
import com.ruiyi.askandanswer.service.HomeService;
import com.ruiyi.askandanswer.main.model.request.HomeCanKnowReq;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

public class HomeServiceImpl implements HomeService {

    public static Retrofit retrofit = null;

    static {
        retrofit = RetrofitFactory.getIntance().getRetrofit();
    }

    //你可能想知道
    @Override
    public Observable<BaseResp<HomeCanKnow>> canKnow(String token, HomeCanKnowReq homeCanKnow) {
        return retrofit.create(HomeService.class).canKnow(token,homeCanKnow);
    }

    //轮播图
    @Override
    public Observable<BaseResp<List<BannerData>>> getBanner(String token) {
        return retrofit.create(HomeService.class).getBanner(token);
    }
}
