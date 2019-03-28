package com.ruiyi.askandanswer.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.user.pay.PayListenerUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    private String TAG = WXPayEntryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay);
        api = WXAPIFactory.createWXAPI(this,BaseConstant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.i(TAG, "WX code:------>" + baseResp.errCode);
        switch (baseResp.errCode) {
            case 0:
                //成功
                PayListenerUtils.getInstance(this).addSuccess();
                break;
            case -1:
                //失败
                PayListenerUtils.getInstance(this).addError();
                break;
            case -2:
                //用户取消
                PayListenerUtils.getInstance(this).addCancel();
                break;
        }
        finish();

    }
}