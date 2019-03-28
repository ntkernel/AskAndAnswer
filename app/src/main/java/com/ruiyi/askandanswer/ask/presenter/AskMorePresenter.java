package com.ruiyi.askandanswer.ask.presenter;

import android.content.Context;
import android.os.Handler;

import com.ruiyi.askandanswer.ask.model.request.MoreRecommondReq;
import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.view.AskMoreView;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.view.AskQuestionView;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.ask.model.request.AskDataReq;
import com.ruiyi.askandanswer.service.impl.AskServiceImpl;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.presenter.LoginPresenter;
import com.ruiyi.askandanswer.user.view.RegisterView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
     查看更多Presenter
 */
public class AskMorePresenter extends LoginPresenter {

    AskServiceImpl askService;
    private Context mContext;

    public AskMorePresenter(Context context,Handler handler){
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
                            ((AskMoreView) mBaseView).onSubjectsResult(baseResp.getData());
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
             获取更多推荐
          */
    public void getMoreRecommend(String subjectId, int type, int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setSubjectId(subjectId);
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);
        moreRecommondReq.setType(type);

        askService.getMoreRecommend("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<MoreKnowData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<MoreKnowData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AskMoreView) mBaseView).onMoreKnowResult(baseResp.getData());
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
           获取更多推荐错题
        */
    public void getMoreErrorRecommend(String subjectId, int type, int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setSubjectId(subjectId);
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);
        moreRecommondReq.setType(type);

        askService.getMoreErrorRecommend("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<MoreErrorData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<MoreErrorData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AskMoreView) mBaseView).onMoreErrorResult(baseResp.getData());
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
         搜索
      */
    public void search(String searchKey, int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setSearchKey(searchKey);
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);

        askService.search("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<MoreKnowData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<MoreKnowData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AskMoreView) mBaseView).onMoreKnowResult(baseResp.getData());
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
