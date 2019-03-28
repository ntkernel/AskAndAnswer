package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.UserInfo;

/*
    视图回调
 */
public interface PayView extends BaseView {

    void onAliPayResult(String info);

    void onWeChatResult(String info);
}
