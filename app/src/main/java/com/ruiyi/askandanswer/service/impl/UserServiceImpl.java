package com.ruiyi.askandanswer.service.impl;

import com.ruiyi.askandanswer.ask.model.request.AnswerReq;
import com.ruiyi.askandanswer.ask.model.request.MoreRecommondReq;
import com.ruiyi.askandanswer.data.net.RetrofitFactory;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.AskService;
import com.ruiyi.askandanswer.user.model.AttentionData;
import com.ruiyi.askandanswer.user.model.CionData;
import com.ruiyi.askandanswer.user.model.CollectData;
import com.ruiyi.askandanswer.user.model.FootData;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.model.RechargeData;
import com.ruiyi.askandanswer.user.model.SchoolInfo;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.service.UserService;
import com.ruiyi.askandanswer.user.model.request.UserInfoReq;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import rx.Observable;

public class UserServiceImpl implements UserService {

    public static Retrofit retrofit = null;

    static {
        retrofit = RetrofitFactory.getIntance().getRetrofit();
    }

    @Override
    public Observable<BaseResp<List<GradeInfo>>> getGrade() {
        return retrofit.create(UserService.class).getGrade();
    }

    @Override
    public Observable<BaseResp<List<SchoolInfo>>> getSchool() {
        return retrofit.create(UserService.class).getSchool();
    }

    @Override
    public Observable<BaseResp<String>> getCode(UserInfoReq userInfoReq) {
        return retrofit.create(UserService.class).getCode(userInfoReq);
    }

    @Override
    public Observable<BaseResp<UserInfo>> register(String name, String schoolName, String schoolGuid, int grade,String gender, String mobile, String verifyCode, String pwd) {
        return retrofit.create(UserService.class).register(name,schoolName,schoolGuid,grade,gender,
                mobile,verifyCode,pwd);
    }

    @Override
    public Observable<BaseResp<UserInfo>> login(String userName, String password) {
        return retrofit.create(UserService.class).login(userName,password);
    }

    @Override
    public Observable<BaseResp<Boolean>> forgetPwd(UserInfoReq userInfoReq) {
        return retrofit.create(UserService.class).forgetPwd(userInfoReq);
    }

    @Override
    public Observable<BaseResp<UserInfo>> myPage(String token) {
        return retrofit.create(UserService.class).myPage(token);
    }

    @Override
    public Observable<BaseResp<String>> uploadAvatar(String token, MultipartBody.Part file) {
        return retrofit.create(UserService.class).uploadAvatar(token,file);
    }

    @Override
    public Observable<BaseResp> changePwd(String token, UserInfoReq userInfoReq) {
        return retrofit.create(UserService.class).changePwd(token,userInfoReq);
    }

    @Override
    public Observable<BaseResp> changePhone(String token, UserInfoReq userInfoReq) {
        return retrofit.create(UserService.class).changePhone(token,userInfoReq);
    }

    @Override
    public Observable<BaseResp> changeSchool(String token, UserInfoReq userInfoReq) {
        return retrofit.create(UserService.class).changeSchool(token,userInfoReq);
    }

    @Override
    public Observable<BaseResp> changeGrade(String token, String grade) {
        return retrofit.create(UserService.class).changeGrade(token,grade);
    }

    @Override
    public Observable<BaseResp<AttentionData>> getMyAttention(String token, MoreRecommondReq recommondReq) {
        return retrofit.create(UserService.class).getMyAttention(token,recommondReq);
    }

    @Override
    public Observable<BaseResp> cancleAttention(String token, String teacherGuid) {
        return retrofit.create(UserService.class).cancleAttention(token,teacherGuid);
    }

    //获取我的足迹
    @Override
    public Observable<BaseResp<FootData>> getMyFoot(String token, MoreRecommondReq recommondReq) {
        return retrofit.create(UserService.class).getMyFoot(token,recommondReq);
    }

    //获取我的提问
    @Override
    public Observable<BaseResp<FootData>> getMyAsk(String token,MoreRecommondReq recommondReq) {
        return retrofit.create(UserService.class).getMyAsk(token,recommondReq);
    }

    //获取我的金币明细
    @Override
    public Observable<BaseResp<CionData>> getMyCoin(String token, MoreRecommondReq recommondReq) {
        return retrofit.create(UserService.class).getMyCoin(token,recommondReq);
    }

    @Override
    public Observable<BaseResp<RechargeData>> getRecharge(String token, MoreRecommondReq recommondReq) {
        return retrofit.create(UserService.class).getRecharge(token,recommondReq);
    }

    //获取我的收藏
    @Override
    public Observable<BaseResp<CollectData>> getMyCollection(String token, MoreRecommondReq recommondReq) {
        return retrofit.create(UserService.class).getMyCollection(token,recommondReq);
    }

    @Override
    public Observable<BaseResp> getCollectQuestion(String token, AnswerReq answerReq) {
        return retrofit.create(AskService.class).getCollectQuestion(token,answerReq);
    }
}
