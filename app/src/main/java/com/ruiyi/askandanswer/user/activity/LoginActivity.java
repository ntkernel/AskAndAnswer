package com.ruiyi.askandanswer.user.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.presenter.LoginPresenter;
import com.ruiyi.askandanswer.user.view.LoginView;
import com.ruiyi.askandanswer.main.activity.MainActivity;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.GlideUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
    登陆界面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView{

    @BindView(R.id.mMobileEt)
    EditText mMobileEt;
    @BindView(R.id.mPwdEt)
    EditText mPwdEt;
    @BindView(R.id.mEyeIv)
    ImageView mEyeIv;
    @BindView(R.id.mLoginBtn)
    Button mLoginBtn;
    @BindView(R.id.mRegisterTv)
    TextView mRegisterTv;
    @BindView(R.id.mForgetPwdTv)
    TextView mForgetPwdTv;
    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    private String mPhoneStr;
    private String mPwdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPresenter = new LoginPresenter(LoginActivity.this);
        mPresenter.mBaseView = this;
        mHeaderBar.getLeftView().setVisibility(View.GONE);
        mPwdEt.addTextChangedListener(new TextChange());
        mMobileEt.addTextChangedListener(new TextChange());
    }

    @OnClick({R.id.mLoginBtn,R.id.mEyeIv,R.id.mRegisterTv,R.id.mForgetPwdTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLoginBtn:
                mPhoneStr = mMobileEt.getText().toString();
                mPwdStr = mPwdEt.getText().toString();
                mPresenter.login(mPhoneStr,mPwdStr,-1);
                break;
            case R.id.mEyeIv:
                TransformationMethod type = mPwdEt.getTransformationMethod();
                if (PasswordTransformationMethod.getInstance() == type) {
                    mPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    GlideUtils.loadImage(LoginActivity.this, R.drawable.ic_eye_off, mEyeIv);
                } else {
                    mPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    GlideUtils.loadImage(LoginActivity.this, R.drawable.ic_eye_on, mEyeIv);
                }
                break;
            case R.id.mRegisterTv:
                ToolUtils.startActivity(LoginActivity.this,RegisterActivity.class,null);
                break;
            case R.id.mForgetPwdTv:
                ToolUtils.startActivity(LoginActivity.this,ForgetPwdActivity.class,null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginResult(UserInfo info) {
        AppPrefsUtils.saveBoolean(SharedPreferencesKey.SHAREDPREFERENCES_ISLOGIN,true);
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,info.getTokenString());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_AVATARURL,info.getAvatarUrl());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_NAME,info.getName());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLGUID,info.getSchoolGuid());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME,info.getSchoolName());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_GRADE,info.getGrade());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_PHONE,info.getPhoneNumber());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_PROVINCE,info.getProvince());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_GUID,info.getGuid());
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_ACCESSNAME,mPhoneStr);
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_PASSWORD,mPwdStr);
        ToolUtils.startActivity(LoginActivity.this,MainActivity.class,null);
        finish();
    }

    // EditText监听器
    private class TextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {

            boolean Sign2 = mMobileEt.getText().length() > 0;
            boolean Sign3 = mPwdEt.getText().length() > 0;

            if (Sign2 & Sign3) {
                mLoginBtn.setEnabled(true);
            }
            // 在layout文件中，对Button的text属性应预先设置默认值，否则刚打开程序的时候Button是无显示的
            else {
                mLoginBtn.setEnabled(false);
            }
        }
    }
}
