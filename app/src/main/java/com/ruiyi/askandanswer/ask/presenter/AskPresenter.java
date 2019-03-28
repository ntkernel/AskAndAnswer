package com.ruiyi.askandanswer.ask.presenter;

import android.content.Context;
import android.os.Handler;

import com.ruiyi.askandanswer.ask.model.request.AskDataReq;
import com.ruiyi.askandanswer.ask.model.request.MoreRecommondReq;
import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.view.AskMoreView;
import com.ruiyi.askandanswer.ask.view.AskQuestionView;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.impl.AskServiceImpl;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.presenter.LoginPresenter;
import com.ruiyi.askandanswer.user.view.MineView;
import com.ruiyi.askandanswer.user.view.RegisterView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;

import java.io.File;
import java.util.List;

import io.github.yedaxia.richeditor.IUploadEngine;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
     查看更多Presenter
 */
public class AskPresenter extends LoginPresenter {

    AskServiceImpl askService;
    private Context mContext;

    public AskPresenter(Context context,Handler handler){
        super(context,handler);
        this.mContext = context;
        askService = new AskServiceImpl();
    }
    /*
           获取学科列表
        */
    public void getSubjects(int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        askService.getSubjects("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,"")).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<List<SubjectData>>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<List<SubjectData>> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AskQuestionView) mBaseView).onSubjectsResult(baseResp.getData());
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
     获取年级
  */
    public void getGrade(int requestType) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        askService.getGrade().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<List<GradeInfo>>>(mBaseView) {
                    @Override
                    public void onNext(BaseResp<List<GradeInfo>> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AskQuestionView) mBaseView).onGradeResult(baseResp.getData());
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
        提问
     */
    public void getAskQuestion(String subjectId, int grade, int privateFlag, int type, String questionGuid, String teacherGuid,
                               String content, String schoolGuid, String schoolName, String province,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        AskDataReq askReq = new AskDataReq();
        askReq.setTeacherGuid(teacherGuid);
        askReq.setQuestionGuid(questionGuid);
        askReq.setSchoolGuid(schoolGuid);
        askReq.setSubjectId(subjectId);
        askReq.setContent(content);
        askReq.setGrade(grade);
        askReq.setPrivateFlag(privateFlag);
        askReq.setType(type);
        askReq.setSchoolName(schoolName);
        askReq.setProvince(province);

        askService.getAskQuestion("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),askReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<String>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<String> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AskQuestionView) mBaseView).onSubmitResult(baseResp.getData());
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
        上传图片
     */
    public void uploadImage(String filePath,IUploadEngine.UploadProgressListener listener,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        //构建body
        File file = new File(filePath);//filePath 图片地址
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), imageBody);
        askService.uploadImage("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<List<String>>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<List<String>> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AskQuestionView) mBaseView).onUpLoadImage(baseResp.getData(),listener,filePath);
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
