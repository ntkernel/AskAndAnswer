package com.ruiyi.askandanswer.user.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.EnvUtils;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.pay.PayUtils;
import com.ruiyi.askandanswer.user.presenter.PayPresenter;
import com.ruiyi.askandanswer.user.view.PayView;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity implements PayView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mCionTv)
    TextView mCionTv;
    @BindView(R.id.mMoneyTv)
    TextView mMoneyTv;
    @BindView(R.id.mYuanTv)
    TextView mYuanTv;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R.id.iv_alipay_dot)
    ImageView ivAlipayDot;
    @BindView(R.id.mAliPayRl)
    RelativeLayout mAliPayRl;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.iv_weixin_dot)
    ImageView ivWeixinDot;
    @BindView(R.id.mWeiXinRl)
    RelativeLayout mWeiXinRl;
    @BindView(R.id.mConfirmBtn)
    Button mConfirmBtn;
    @BindView(R.id.mTenRb)
    RadioButton mTenRb;
    @BindView(R.id.mFiftyRb)
    RadioButton mFiftyRb;
    @BindView(R.id.mHunderdRb)
    RadioButton mHunderdRb;
    @BindView(R.id.mMoneyRg)
    RadioGroup mMoneyRg;
    @BindView(R.id.mMoneyEt)
    EditText mMoneyEt;
    private int mType = 0;
    private String mMoneyStr;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ((PayPresenter) mPresenter).getAliAppPay(mMoneyStr,1);
                    break;
                case 2:
                    ((PayPresenter) mPresenter).getWeChatPay(mMoneyStr,2);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        mPresenter = new PayPresenter(RechargeActivity.this,mHander);
        mPresenter.mBaseView = this;
        initView();
    }

    private void initView() {
        mMoneyRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.mTenRb:
                        mMoneyEt.setText(10 + "");
                        break;
                    case R.id.mFiftyRb:
                        mMoneyEt.setText(5 + "");
                        break;
                    case R.id.mHunderdRb:
                        mMoneyEt.setText(100 + "");
                        break;
                }
            }
        });
    }

    @OnClick({R.id.mAliPayRl, R.id.mWeiXinRl, R.id.mConfirmBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mAliPayRl:
                ivAlipayDot.setImageResource(R.drawable.ic_dot);
                ivWeixinDot.setImageResource(R.drawable.ic_dot_stoke);
                mType = 0;
                break;
            case R.id.mWeiXinRl:
                ivAlipayDot.setImageResource(R.drawable.ic_dot_stoke);
                ivWeixinDot.setImageResource(R.drawable.ic_dot);
                mType = 1;
                break;
            case R.id.mConfirmBtn:
                mMoneyStr = mMoneyEt.getText().toString();
                if(mType == 0)
                    ((PayPresenter) mPresenter).getAliAppPay(mMoneyStr,1);
                else
                    ((PayPresenter) mPresenter).getWeChatPay(mMoneyStr,2);
                break;
        }
    }

    @Override
    public void onAliPayResult(String info) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        PayUtils.getInstance(this).pay(PayUtils.PAY_TYPE_ALI,info);
    }

    @Override
    public void onWeChatResult(String info) {

    }
}
