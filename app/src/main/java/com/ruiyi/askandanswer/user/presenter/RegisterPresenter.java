package com.ruiyi.askandanswer.user.presenter;

import android.content.Context;

import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.model.SchoolInfo;
import com.ruiyi.askandanswer.service.impl.UserServiceImpl;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.model.request.UserInfoReq;
import com.ruiyi.askandanswer.user.view.RegisterView;
import com.ruiyi.askandanswer.utils.ToastUtil;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
    注册界面 Presenter
 */
public class RegisterPresenter extends BasePresenter {

    UserServiceImpl userService;
    private Context mContext;

    public RegisterPresenter(Context context) {
        this.mContext = context;
        userService = new UserServiceImpl();
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
                    public void onNext(BaseResp<List<SchoolInfo>> listBaseResp) {
                        super.onNext(listBaseResp);
                        mBaseView.hideLoading();
                        if (listBaseResp.getStatusCode() == 200)
                            ((RegisterView) mBaseView).getScoolInfo(listBaseResp.getData());
                        else
                            mBaseView.showError(listBaseResp.getMessage());
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
                    public void onNext(BaseResp<List<GradeInfo>> listBaseResp) {
                        super.onNext(listBaseResp);
                        mBaseView.hideLoading();
                        if (listBaseResp.getStatusCode() == 200)
                            ((RegisterView) mBaseView).getGradelInfo(listBaseResp.getData());
                        else
                            mBaseView.showError(listBaseResp.getMessage());
                    }
                });
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
                        if (baseResp.getStatusCode() == 200)
                            ToastUtil.show("获取成功！");
                        else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }

    /*
        用户注册
     */
    public void register(String name, String schoolName, String schoolGuid, int grade,String gender, String mobile, String verifyCode, String pwd) {
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        userService.register(name,schoolName,schoolGuid,grade,gender,
                mobile,verifyCode,pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<UserInfo>>(mBaseView) {
                    @Override
                    public void onNext(BaseResp<UserInfo> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if (baseResp.getStatusCode() == 200)
                            ((RegisterView) mBaseView).onRegisterResult(baseResp.getData());
                        else
                            mBaseView.showError(baseResp.getMessage());
                    }
                });
    }
}
