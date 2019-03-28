package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.AttentionData;
import com.ruiyi.askandanswer.user.model.UserInfo;

/*
    用户登陆 视图回调
 */
public interface AttentionView extends BaseView {

    void onAttentionResult(AttentionData attentionData);

    void onCancleAttentionResult();
}
