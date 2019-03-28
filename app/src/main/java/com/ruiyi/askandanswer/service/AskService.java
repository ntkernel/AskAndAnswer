package com.ruiyi.askandanswer.service;

import com.ruiyi.askandanswer.ask.model.request.AnswerReq;
import com.ruiyi.askandanswer.ask.model.request.AskReq;
import com.ruiyi.askandanswer.ask.model.request.MoreRecommondReq;
import com.ruiyi.askandanswer.ask.model.request.ResourceReq;
import com.ruiyi.askandanswer.ask.model.request.TeacherAnswerReq;
import com.ruiyi.askandanswer.ask.model.response.AnswerData;
import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.QuestionData;
import com.ruiyi.askandanswer.ask.model.response.QuestionModel;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.model.response.TeacherAnswerData;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.ask.model.request.AskDataReq;
import com.ruiyi.askandanswer.user.model.GradeInfo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface AskService {

    //获取学科列表
    @GET("AskAnswer/Subjects")
    Observable<BaseResp<List<SubjectData>>> getSubjects(@Header("Authorization") String token);

    //获取更多推荐
    @POST("AskAnswer/MoreRecommend")
    Observable<BaseResp<MoreKnowData>> getMoreRecommend(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //获取更多推荐错题
    @POST("AskAnswer/MoreRecommend")
    Observable<BaseResp<MoreErrorData>> getMoreErrorRecommend(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //搜索
    @POST("AskAnswer/Search")
    Observable<BaseResp<MoreKnowData>> search(@Header("Authorization") String token, @Body MoreRecommondReq recommondReq);

    //获取问答具体内容
    @GET("AskAnswer/AskQuestionDetail")
    Observable<BaseResp<AnswerData>> getAnswerDetail(@Header("Authorization") String token, @Query("guid") String guid);

    //获取问答具体内容
    @GET("AskAnswer/ThumbUp")
    Observable<BaseResp> getThumbUp(@Header("Authorization") String token, @Query("guid") String guid,@Query("type") int type);

    //获取问答具体资源
    @GET("AskAnswer/GetResource")
    Observable<byte[]> getAnswerResource(@Header("Authorization") String token,
                                         @Query("start") Long start, @Query("end") Long end,
                                         @Query ("guid") String guid,@Query ("extension") String extension);

    //获取问答具体资源
    @POST("AskAnswer/GetResource")
    Observable<BaseResp<String>> getVedioResource(@Header("Authorization") String token,
                                         @Header("start") Long start, @Header("end") Long end, @Body ResourceReq resourceReq);

    //结束问题
    @GET("AskAnswer/EndAskQuestion")
    Observable<BaseResp<AnswerData>> endAskQuestion(@Header("Authorization") String token, @Query("guid") String guid);

    //追问
    @POST("AskAnswer/AskAgain")
    Observable<BaseResp> askAgain(@Header("Authorization") String token, @Body AskReq askReq);

    //教师最近回答的问题
    @POST("AskAnswer/TeacherAnswer")
    Observable<BaseResp<TeacherAnswerData>> teacherAnswer(@Header("Authorization") String token, @Body TeacherAnswerReq teacherAnswerReq);

    //关注老师
    @POST("AskAnswer/GiveAttention")
    Observable<BaseResp> attentionTeacher(@Header("Authorization") String token, @Body TeacherAnswerReq teacherAnswerReq);

    //取消关注老师
    @GET("AskAnswer/CancelAttention")
    Observable<BaseResp> cancleAttention(@Header("Authorization") String token, @Query("teacherGuid") String guid);

    //获取题目
    @GET("AskAnswer/GetQuestion")
    Observable<BaseResp<QuestionModel>> getQuestion(@Header("Authorization") String token, @Query("guid") String guid);

    //学生作答结果
    @POST("AskAnswer/StudentAnswer")
    Observable<BaseResp> getStudentAnswer(@Header("Authorization") String token, @Body AnswerReq answerReq);

    //学生收藏题目
    @POST("AskAnswer/CollectQuestion")
    Observable<BaseResp> getCollectQuestion(@Header("Authorization") String token, @Body AnswerReq answerReq);

    //获取年级
    @POST("AskAnswer/Grades")
    Observable<BaseResp<List<GradeInfo>>> getGrade();

    //提问
    @POST("AskAnswer/AskQuestion")
    Observable<BaseResp<String>> getAskQuestion(@Header("Authorization") String token, @Body AskDataReq askReq);

    //上传图片
    @Multipart
    @POST("AskAnswer/AskQuestionPicture")
    Observable<BaseResp<List<String>>> uploadImage(@Header("Authorization") String token, @Part() MultipartBody.Part file);
}