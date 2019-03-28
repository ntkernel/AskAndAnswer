package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.UserInfo;

/*
    我的登陆 视图回调
 */
public interface MineView extends BaseView {

    void onMineResult(UserInfo info);

    void onAvatarResult(String url);
}
