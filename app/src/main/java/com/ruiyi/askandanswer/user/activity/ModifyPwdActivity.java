package com.ruiyi.askandanswer.user.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.UserView;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyPwdActivity extends BaseActivity<UserPresenter> implements UserView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mOldEt)
    EditText mOldEt;
    @BindView(R.id.mNewEt)
    EditText mNewEt;
    @BindView(R.id.mConfirmEt)
    EditText mConfirmEt;
    @BindView(R.id.mExitBtn)
    Button mExitBtn;
    private String newPwd;
    private String oldPwd;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 2:
                    mPresenter.changePwd(oldPwd,newPwd,2);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypwd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPresenter = new UserPresenter(ModifyPwdActivity.this,mHander);
        mPresenter.mBaseView = this;
        mOldEt.addTextChangedListener(new TextChange());
        mNewEt.addTextChangedListener(new TextChange());
        mConfirmEt.addTextChangedListener(new TextChange());
    }

    @OnClick(R.id.mExitBtn)
    public void onViewClicked() {
        newPwd = mNewEt.getText().toString();
        oldPwd = mOldEt.getText().toString();
        if(newPwd.equals(mConfirmEt.getText().toString())){
            mPresenter.changePwd(oldPwd,newPwd,2);
        }else{
            ToastUtil.show("两次输入密码不一致");
        }
    }

    @Override
    public void onForgetPwdResult(UserInfo info) {

    }

    @Override
    public void onChangeResult() {
        ToastUtil.show("修改成功");
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
            boolean Sign1 = mOldEt.getText().length() > 0;
            boolean Sign2 = mNewEt.getText().length() > 0;
            boolean Sign3 = mConfirmEt.getText().length() > 0;

            if (Sign2 & Sign3) {
                mExitBtn.setEnabled(true);
            }
            // 在layout文件中，对Button的text属性应预先设置默认值，否则刚打开程序的时候Button是无显示的
            else {
                mExitBtn.setEnabled(false);
            }
        }
    }
}
