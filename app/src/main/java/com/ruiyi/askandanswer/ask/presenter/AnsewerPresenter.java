package com.ruiyi.askandanswer.ask.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ruiyi.askandanswer.ask.model.request.AnswerReq;
import com.ruiyi.askandanswer.ask.model.request.AskReq;
import com.ruiyi.askandanswer.ask.model.request.ResourceReq;
import com.ruiyi.askandanswer.ask.model.request.TeacherAnswerReq;
import com.ruiyi.askandanswer.ask.model.response.AnswerData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.QuestionData;
import com.ruiyi.askandanswer.ask.model.response.QuestionModel;
import com.ruiyi.askandanswer.ask.model.response.TeacherAnswerData;
import com.ruiyi.askandanswer.ask.view.AnswerView;
import com.ruiyi.askandanswer.ask.view.QuestionView;
import com.ruiyi.askandanswer.ask.view.ResourceView;
import com.ruiyi.askandanswer.ask.view.TeacherInfoView;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.AskService;
import com.ruiyi.askandanswer.service.impl.AskServiceImpl;
import com.ruiyi.askandanswer.service.impl.UserServiceImpl;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.presenter.LoginPresenter;
import com.ruiyi.askandanswer.user.view.LoginView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
     Presenter
 */
public class AnsewerPresenter extends LoginPresenter {

    AskService askService;
    private Context mContext;

    public AnsewerPresenter(Context context,Handler handler){
        super(context,handler);
        this.mContext = context;
        askService = new AskServiceImpl();
    }

    public AnsewerPresenter(Context context){
        super(context);
        this.mContext = context;
        askService = new AskServiceImpl();
    }

    /*
           获取回答详情
        */
    public void getAnswerDetail(String guid,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        askService.getAnswerDetail("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),guid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<AnswerData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<AnswerData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AnswerView) mBaseView).getAnswer(baseResp.getData());
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        }else{
                            mBaseView.showError(baseResp.getMessage());
                        }
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
        获取点赞
     */
    public void getThumbUp(String guid,int type,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        askService.getThumbUp("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),guid,type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView){
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AnswerView) mBaseView).onLikeResult();
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
              获取具体资源
           */
    public void getAnswerResource(String guid,String extension){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        askService.getAnswerResource("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),
                0l,100000000l,guid,extension).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new Subscriber<byte[]>() {
                    @Override
                    public void onCompleted() {
                        Log.d("cj","onCompleted====>>>");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("cj","Throwable====>>>" + e);
                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        mBaseView.hideLoading();
                        ((ResourceView) mBaseView).onResourceResult(bytes);
                    }
                });
    }

    /*
        结束问题
     */
    public void endAskQuestion(String guid,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        askService.endAskQuestion("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),guid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<AnswerData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<AnswerData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AnswerView) mBaseView).onEndQuestionResult();
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
      追问
   */
    public void askAgain(String askGuid,String teaGuid,String content,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        AskReq askReq = new AskReq();
        askReq.setAskQuestionGuid(askGuid);
        askReq.setToUserGuid(teaGuid);
        askReq.setContent(content);
        askService.askAgain("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),askReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView){
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AnswerView) mBaseView).onAskAgainResult();
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

    /**
     *   教师最近回答的问题
     */
    public void teacherAnswer(String guid,int page,int number,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        TeacherAnswerReq teacherAnswerReq = new TeacherAnswerReq();
        teacherAnswerReq.setTeacherGuid(guid);
        teacherAnswerReq.setNumber(number);
        teacherAnswerReq.setPage(page);

        askService.teacherAnswer("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),teacherAnswerReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<TeacherAnswerData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<TeacherAnswerData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((TeacherInfoView) mBaseView).onTeacherInfoReault(baseResp.getData());
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

    /**
     *   关注教师
     */
    public void attentionTeacher(TeacherAnswerData.TeacherInforBean teacherInforBean,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        TeacherAnswerReq teacherAnswerReq = new TeacherAnswerReq();
        teacherAnswerReq.setTeacherGuid(teacherInforBean.getGuid());
        teacherAnswerReq.setSchoolName(teacherInforBean.getSchoolName());
        teacherAnswerReq.setTeacherName(teacherInforBean.getName());
        teacherAnswerReq.setTeacherProvince(teacherInforBean.getProvince());
        teacherAnswerReq.setTeacherTitle(teacherInforBean.getTitle() == null ? "" : teacherInforBean.getTitle());

        askService.attentionTeacher("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),teacherAnswerReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView){
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((TeacherInfoView) mBaseView).onAttentionResult();
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

    /**
     *   取消关注教师
     */
    public void cancleAttention(String guid,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();

        askService.cancleAttention("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),guid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView){
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((TeacherInfoView) mBaseView).onCancleAttentionResult();
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

    /**
     *   获取题目
     */
    public void getQuestion(String guid){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();

        askService.getQuestion("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),guid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<QuestionModel>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<QuestionModel> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((QuestionView) mBaseView).onQuestionReault(baseResp.getData());
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
//                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
//                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""));
                        }else{
                            QuestionModel questionModel = new QuestionModel();
                            QuestionData questionData = new QuestionData();
                            questionData.setType(-1);
                            questionModel.setQuestionData(questionData);
                            ((QuestionView) mBaseView).onQuestionReault(questionModel);
                        }
                    }
                });
    }

    /**
     *  学生作答结果
     */
    public void getStudentAnswer(String questionGuid,String answer,String themGuid,String subjectId,String remark,int answerResult){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        AnswerReq answerReq = new AnswerReq();
        answerReq.setQuestionGuid(questionGuid);
        answerReq.setAnswer(answer);
        answerReq.setThemeGuid(themGuid);
        answerReq.setSubjectId(subjectId);
        answerReq.setRemark(remark);
        answerReq.setAnswerResult(answerResult);
        askService.getStudentAnswer("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),answerReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView){
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((QuestionView) mBaseView).onAnswerResult();
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
//                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
//                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        }else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }

    /**
     *  学生收藏题目
     */
    public void getCollectQuestion(String questionGuid,String themGuid,int collectionFlag){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        AnswerReq answerReq = new AnswerReq();
        answerReq.setQuestionGuid(questionGuid);
        answerReq.setCollectionFlag(collectionFlag);
        answerReq.setThemeGuid(themGuid);
        askService.getCollectQuestion("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),answerReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView){
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((QuestionView) mBaseView).onCollectResult();
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
//                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
//                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        }else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }
}
