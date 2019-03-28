package com.ruiyi.askandanswer.user.presenter;

import android.content.Context;
import android.os.Handler;

import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BasePresenter;
import com.ruiyi.askandanswer.base.BaseSubscriber;
import com.ruiyi.askandanswer.data.protocol.BaseResp;
import com.ruiyi.askandanswer.service.impl.UserServiceImpl;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.view.MineView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MinePresenter extends LoginPresenter {

    UserServiceImpl userService;
    private Context mContext;

    public MinePresenter(Context context,Handler handler){
        super(context,handler);
        this.mContext = context;
        userService = new UserServiceImpl();
    }

    /*
           获取个人信息
        */
    public void getUserInfo(int requestType){
        if (!checkNetWork(mContext)) {
            return;
        }
        mBaseView.showLoading();
        userService.myPage("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,"")).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<UserInfo>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<UserInfo> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((MineView) mBaseView).onMineResult(baseResp.getData());
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
          上传头像
       */
    public void uploadAvatar(String filePath,int requestType){
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
        userService.uploadAvatar("Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,""),body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseSubscriber<BaseResp<String>>(mBaseView){
                    @Override
                    public void onNext(BaseResp<String> baseResp) {
                        super.onNext(baseResp);
                        mBaseView.hideLoading();
                        if(baseResp.getStatusCode() == BaseConstant.RESULT_OK)
                            ((MineView) mBaseView).onAvatarResult(baseResp.getData());
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
