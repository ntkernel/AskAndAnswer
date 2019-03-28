package com.ruiyi.askandanswer.user.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.UserView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyPhoneActivity extends BaseActivity<UserPresenter> implements UserView {

    @BindView(R.id.mMobileEt)
    EditText mMobileEt;
    @BindView(R.id.mVerifyCodeEt)
    EditText mVerifyCodeEt;
    @BindView(R.id.mConfirmBtn)
    Button mConfirmBtn;
    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mVerifyCodeBtn)
    Button mVerifyCodeBtn;
    @BindView(R.id.mPhoneTv)
    TextView mPhoneTv;
    private TimeCount timer;
    private String phone;
    private String code;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mPresenter.changePhone(phone,code,1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyphone);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPhoneTv.setText(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PHONE,""));
        timer = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        mPresenter = new UserPresenter(ModifyPhoneActivity.this,mHander);
        mPresenter.mBaseView = this;
        initListener();
    }

    private void initListener() {
        mMobileEt.addTextChangedListener(new TextChange());
        mVerifyCodeEt.addTextChangedListener(new TextChange());
    }

    @OnClick({R.id.mConfirmBtn, R.id.mVerifyCodeBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mConfirmBtn:
                phone = mMobileEt.getText().toString();
                code = mVerifyCodeEt.getText().toString();
                mPresenter.changePhone(phone,code,1);
                break;
            case R.id.mVerifyCodeBtn:
                String phone = mMobileEt.getText().toString();
                if (ToolUtils.isMobileNO(phone)) {
                    timer.start();
                    mPresenter.getCode(phone);
                } else
                    ToastUtil.show("请输入正确的手机号");
                break;
            default:
                break;
        }
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
            boolean Sign1 = mMobileEt.getText().length() > 0;
            boolean Sign2 = mVerifyCodeEt.getText().length() > 0;

            if (Sign1 && Sign2) {
                mConfirmBtn.setEnabled(true);
            }
            // 在layout文件中，对Button的text属性应预先设置默认值，否则刚打开程序的时候Button是无显示的
            else {
                mConfirmBtn.setEnabled(false);
            }

            if (Sign1)
                mVerifyCodeBtn.setEnabled(true);
            else
                mVerifyCodeBtn.setEnabled(false);
        }
    }

    /**
     * 定义一个倒计时内部类
     *
     * @author
     */
    private class TimeCount extends CountDownTimer {

        TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (mVerifyCodeBtn != null) {
                mVerifyCodeBtn.setClickable(false);
                String second = millisUntilFinished / 1000 + "秒";
                mVerifyCodeBtn.setText(second);
            }
        }

        @Override
        public void onFinish() {
            if (mVerifyCodeBtn != null) {
                mVerifyCodeBtn.setText("获取验证码");
                mVerifyCodeBtn.setClickable(true);
            }
        }
    }

    @Override
    public void onForgetPwdResult(UserInfo info) {

    }

    @Override
    public void onChangeResult() {
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_PHONE, phone);
        ToastUtil.show("修改成功");
        finish();
    }
}
