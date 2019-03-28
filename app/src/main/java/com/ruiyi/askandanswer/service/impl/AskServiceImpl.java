package com.ruiyi.askandanswer.service.impl;

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
import com.ruiyi.askandanswer.data.net.RetrofitFactory;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.ask.model.request.AskDataReq;
import com.ruiyi.askandanswer.service.AskService;
import com.ruiyi.askandanswer.user.model.GradeInfo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import rx.Observable;

public class AskServiceImpl implements AskService {

    public static Retrofit retrofit = null;

    static {
        retrofit = RetrofitFactory.getIntance().getRetrofit();
    }

    //获取学科列表
    @Override
    public Observable<BaseResp<List<SubjectData>>> getSubjects(String token) {
        return retrofit.create(AskService.class).getSubjects(token);
    }

    //获取更多推荐
    @Override
    public Observable<BaseResp<MoreKnowData>> getMoreRecommend(String token,MoreRecommondReq recommondReq) {
        return retrofit.create(AskService.class).getMoreRecommend(token,recommondReq);
    }

    //错题
    @Override
    public Observable<BaseResp<MoreErrorData>> getMoreErrorRecommend(String token, MoreRecommondReq recommondReq) {
        return retrofit.create(AskService.class).getMoreErrorRecommend(token,recommondReq);
    }

    //搜索
    @Override
    public Observable<BaseResp<MoreKnowData>> search(String token, MoreRecommondReq recommondReq) {
        return retrofit.create(AskService.class).search(token,recommondReq);
    }

    //问答详情
    @Override
    public Observable<BaseResp<AnswerData>> getAnswerDetail(String token, String guid) {
        return retrofit.create(AskService.class).getAnswerDetail(token,guid);
    }

    @Override
    public Observable<BaseResp> getThumbUp(String token, String guid, int type) {
        return retrofit.create(AskService.class).getThumbUp(token,guid,type);
    }

    //问答资源
    @Override
    public Observable<byte[]> getAnswerResource(String token,Long start,Long end,String guid,String extension) {
        return RetrofitFactory.getIntance().getByteRetrofit().create(AskService.class).getAnswerResource(token,start,end,guid,extension);
    }

    @Override
    public Observable<BaseResp<String>> getVedioResource(String token, Long start, Long end, ResourceReq resourceReq) {
        return retrofit.create(AskService.class).getVedioResource(token,start,end,resourceReq);
    }

    //结束问答
    @Override
    public Observable<BaseResp<AnswerData>> endAskQuestion(String token, String guid) {
        return retrofit.create(AskService.class).endAskQuestion(token,guid);
    }

    @Override
    public Observable<BaseResp> askAgain(String token, AskReq askReq) {
        return retrofit.create(AskService.class).askAgain(token,askReq);
    }

    //老师回答
    @Override
    public Observable<BaseResp<TeacherAnswerData>> teacherAnswer(String token, TeacherAnswerReq teacherAnswerReq) {
        return retrofit.create(AskService.class).teacherAnswer(token,teacherAnswerReq);
    }

    //关注老师
    @Override
    public Observable<BaseResp> attentionTeacher(String token, TeacherAnswerReq teacherAnswerReq) {
        return retrofit.create(AskService.class).attentionTeacher(token,teacherAnswerReq);
    }

    //取消关注
    @Override
    public Observable<BaseResp> cancleAttention(String token, String guid) {
        return retrofit.create(AskService.class).cancleAttention(token,guid);
    }

    @Override
    public Observable<BaseResp<QuestionModel>> getQuestion(String token, String guid) {
        return retrofit.create(AskService.class).getQuestion(token,guid);
    }

    @Override
    public Observable<BaseResp> getStudentAnswer(String token, AnswerReq answerReq) {
        return retrofit.create(AskService.class).getStudentAnswer(token,answerReq);}

    @Override
    public Observable<BaseResp> getCollectQuestion(String token, AnswerReq answerReq) {
        return retrofit.create(AskService.class).getCollectQuestion(token,answerReq);
    }

    @Override
    public Observable<BaseResp<List<GradeInfo>>> getGrade() {
        return retrofit.create(AskService.class).getGrade();
    }

    @Override
    public Observable<BaseResp<String>> getAskQuestion(String token, AskDataReq askDataReq) {
        return retrofit.create(AskService.class).getAskQuestion(token,askDataReq);
    }

    @Override
    public Observable<BaseResp<List<String>>> uploadImage(String token, MultipartBody.Part file) {
        return retrofit.create(AskService.class).uploadImage(token,file);
    }
}