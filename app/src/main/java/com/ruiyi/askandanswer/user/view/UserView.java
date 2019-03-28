package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.UserInfo;

/*
    用户登陆 视图回调
 */
public interface UserView extends BaseView {

    void onForgetPwdResult(UserInfo info);

    void onChangeResult();
}
