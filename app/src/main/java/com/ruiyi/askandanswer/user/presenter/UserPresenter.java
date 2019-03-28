package com.ruiyi.askandanswer.user.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.ruiyi.askandanswer.ask.model.request.AnswerReq;
import com.ruiyi.askandanswer.ask.model.request.MoreRecommondReq;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.view.AskMoreView;
import com.ruiyi.askandanswer.ask.view.QuestionView;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.impl.UserServiceImpl;
import com.ruiyi.askandanswer.user.model.AttentionData;
import com.ruiyi.askandanswer.user.model.CionData;
import com.ruiyi.askandanswer.user.model.CollectData;
import com.ruiyi.askandanswer.user.model.FootData;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.model.RechargeData;
import com.ruiyi.askandanswer.user.model.SchoolInfo;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.model.request.UserInfoReq;
import com.ruiyi.askandanswer.user.view.AttentionView;
import com.ruiyi.askandanswer.user.view.CollectView;
import com.ruiyi.askandanswer.user.view.FootView;
import com.ruiyi.askandanswer.user.view.RechargeView;
import com.ruiyi.askandanswer.user.view.RegisterView;
import com.ruiyi.askandanswer.user.view.UserView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToastUtil;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
    密码界面 Presenter
 */
public class UserPresenter<T> extends LoginPresenter {

    UserServiceImpl userService;
    private Context mContext;

    public UserPresenter(Context context,Handler handler){
        super(context,handler);
        this.mContext = context;
        userService = new UserServiceImpl();
    }

    /*
          获取验证码
       */
    public void getCode(String phone) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setPhoneNumber(phone);
        userService.getCode(userInfoReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<String>>(mBaseView) {
                    @Override
                    public void onNext(BaseResp<String> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ToastUtil.show("获取成功！");
                        else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }

    /*
            忘记密码
         */
    public void forgetPwd(String mobile, String verifyCode,String pwd) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading(); UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setPhoneNumber(mobile);
        userInfoReq.setVerificationCode(verifyCode);
        userInfoReq.setNewPassword(pwd);
        userService.forgetPwd(userInfoReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<Boolean>>(mBaseView) {
                    @Override
                    public void onNext(BaseResp<Boolean> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((UserView) mBaseView).onChangeResult();
                        else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }


    /*
          修改手机号
       */
    public void changePhone(String phone, String code,int requestType) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setPhoneNumber(phone);
        userInfoReq.setVerificationCode(code);
        userService.changePhone("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""), userInfoReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView) {
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((UserView) mBaseView).onChangeResult();
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
          修改密码
       */
    public void changePwd(String oldPwd, String newPwd,int requestType) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setNewPassword(newPwd);
        userInfoReq.setOrginalPassword(oldPwd);
        userService.changePwd("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""), userInfoReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView) {
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((UserView) mBaseView).onChangeResult();
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
         修改学校
      */
    public void changeSchool(String schoolGuid, String schoolName,int requestType) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setSchoolGuid(schoolGuid);
        userInfoReq.setSchoolName(schoolName);
        userService.changeSchool("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""), userInfoReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView) {
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((UserView) mBaseView).onChangeResult();
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        } else
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
         修改年级
      */
    public void changeGrade(String grade,int requestType) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        userService.changeGrade("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""), grade).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView) {
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((UserView) mBaseView).onChangeResult();
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
       获取学校
    */
    public void getSchool() {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        userService.getSchool().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<List<SchoolInfo>>>(mBaseView) {
                    @Override
                    public void onNext(BaseResp<List<SchoolInfo>> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((RegisterView) mBaseView).getScoolInfo(baseResp.getData());
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
//                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
//                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        }else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }

    /*
       获取年级
    */
    public void getGrade() {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        userService.getGrade().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<List<GradeInfo>>>(mBaseView) {
                    @Override
                    public void onNext(BaseResp<List<GradeInfo>> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((RegisterView) mBaseView).getGradelInfo(baseResp.getData());
                        else if(baseResp.getStatusCode() == BaseConstant.RESULT_EXPIRED){
//                            login(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,""),
//                                    AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,""),requestType);
                        }else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }

    /*
         获取我的关注
      */
    public void getMyAttention(int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);

        userService.getMyAttention("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<AttentionData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<AttentionData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AttentionView) mBaseView).onAttentionResult(baseResp.getData());
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
    取消关注
 */
    public void cancleAttention(String teacherGuid,int requestType) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        userService.cancleAttention("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),teacherGuid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView) {
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((AttentionView) mBaseView).onCancleAttentionResult();
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
           获取我的足迹
        */
    public void getMyFoot(String subjectId,int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setSubjectId(subjectId);
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);

        userService.getMyFoot("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<FootData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<FootData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        Log.d("cj","baseResp=====>>>>");
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((FootView) mBaseView).onFootResult(baseResp.getData());
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
          获取我的收藏
       */
    public void getMyCollection(String subjectId,int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setSubjectId(subjectId);
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);

        userService.getMyCollection("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<CollectData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<CollectData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        Log.d("cj","baseResp=====>>>>");
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((CollectView) mBaseView).onCollectResult(baseResp.getData());
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
           获取我的提问
        */
    public void getMyAsk(int type, int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);
        moreRecommondReq.setType(type);

        userService.getMyAsk("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<FootData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<FootData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((FootView) mBaseView).onFootResult(baseResp.getData());
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
       获取我的金币明细
    */
    public void getMyCoin(int type, int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);
        moreRecommondReq.setType(type);

        userService.getMyCoin("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<CionData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<CionData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((RechargeView) mBaseView).onCionResult(baseResp.getData());
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
      获取我的充值
   */
    public void getRecharge(int type, int number, int page,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        MoreRecommondReq moreRecommondReq = new MoreRecommondReq();
        moreRecommondReq.setNumber(number);
        moreRecommondReq.setPage(page);
        moreRecommondReq.setType(type);

        userService.getRecharge("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),moreRecommondReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<RechargeData>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<RechargeData> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((RechargeView) mBaseView).onRechargeResult(baseResp.getData());
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
     *  学生收藏题目
     */
    public void getCollectQuestion(String questionGuid,String themGuid,int collectionFlag,int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        AnswerReq answerReq = new AnswerReq();
        answerReq.setQuestionGuid(questionGuid);
        answerReq.setCollectionFlag(collectionFlag);
        answerReq.setThemeGuid(themGuid);
        userService.getCollectQuestion("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),answerReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp>(mBaseView){
                    @Override
                    public void onNext(BaseResp baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((CollectView) mBaseView).onCancleCollectResult();
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
