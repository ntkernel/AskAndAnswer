package com.ruiyi.askandanswer.service;

import com.ruiyi.askandanswer.ask.model.request.AnswerReq;
import com.ruiyi.askandanswer.ask.model.request.AskReq;
import com.ruiyi.askandanswer.ask.model.request.MoreRecommondReq;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.user.model.AttentionData;
import com.ruiyi.askandanswer.user.model.CionData;
import com.ruiyi.askandanswer.user.model.CollectData;
import com.ruiyi.askandanswer.user.model.FootData;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.model.RechargeData;
import com.ruiyi.askandanswer.user.model.SchoolInfo;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.model.request.UserInfoReq;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    //获取年级
    @POST("AskAnswer/Grades")
    Observable<BaseResp<List<GradeInfo>>> getGrade();

    //获取学校
    @POST("AskAnswer/Schools")
    Observable<BaseResp<List<SchoolInfo>>> getSchool();

    //获取验证码
    @POST("AskAnswer/VerificationCode")
    Observable<BaseResp<String>> getCode(@Body UserInfoReq userInfoReq);

    //用户注册
    @FormUrlEncoded
    @POST("AskAnswer/Register")
    Observable<BaseResp<UserInfo>> register(@Field("name")String name,@Field("schoolName")String schoolName,
                                            @Field("schoolGuid")String schoolGuid,@Field("grade")int grade,
                                            @Field("gender")String gender,@Field("phoneNumber")String mobile,
                                            @Field("verificationCode")String verifyCode,@Field("password")String pwd);

     //用户登录
    @FormUrlEncoded
    @POST("AskAnswer/Login")
    Observable<BaseResp<UserInfo>> login(@Field("userName")String userName,
              @Field("password")String password);

    //忘记密码
    @POST("AskAnswer/ForgetChangePassword")
    Observable<BaseResp<Boolean>> forgetPwd(@Body UserInfoReq userInfoReq);

    //获取用户信息
    @GET("AskAnswer/MyPage")
    Observable<BaseResp<UserInfo>> myPage(@Header("Authorization") String token);

    //上传头像
    @Multipart
    @POST("AskAnswer/UploadAvatar")
    Observable<BaseResp<String>> uploadAvatar(@Header("Authorization") String token, @Part()MultipartBody.Part file);

    //修改密码
    @POST("AskAnswer/ChangePassword")
    Observable<BaseResp> changePwd(@Header("Authorization") String token, @Body UserInfoReq userInfoReq);

    //修改手机号
    @POST("AskAnswer/ChangePhone")
    Observable<BaseResp> changePhone(@Header("Authorization") String token, @Body UserInfoReq userInfoReq);

    //修改学校
    @POST("AskAnswer/ChangeSchool")
    Observable<BaseResp> changeSchool(@Header("Authorization") String token, @Body UserInfoReq userInfoReq);

    //修改年级
    @GET("AskAnswer/ChangeGrade")
    Observable<BaseResp> changeGrade(@Header("Authorization") String token, @Query("grade")String grade);

    //获取我的关注
    @POST("AskAnswer/MyAttention")
    Observable<BaseResp<AttentionData>> getMyAttention(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //取消关注
    @GET("AskAnswer/CancelAttention")
    Observable<BaseResp> cancleAttention(@Header("Authorization") String token, @Query("teacherGuid")String teacherGuid);

    //获取我的足迹
    @POST("AskAnswer/FootPrint")
    Observable<BaseResp<FootData>> getMyFoot(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //获取我的提问
    @POST("AskAnswer/MyAsk")
    Observable<BaseResp<FootData>> getMyAsk(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //获取我的金币明细
    @POST("AskAnswer/MyCoin")
    Observable<BaseResp<CionData>> getMyCoin(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //获取我的充值
    @POST("AskAnswer/MyCoin")
    Observable<BaseResp<RechargeData>> getRecharge(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //获取我的收藏
    @POST("AskAnswer/MyCollection")
    Observable<BaseResp<CollectData>> getMyCollection(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //学生收藏题目
    @POST("AskAnswer/CollectQuestion")
    Observable<BaseResp> getCollectQuestion(@Header("Authorization") String token, @Body AnswerReq answerReq);
}
