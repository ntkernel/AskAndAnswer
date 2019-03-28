package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.model.SchoolInfo;
import com.ruiyi.askandanswer.user.model.UserInfo;

import java.util.List;

/*
    用户注册 视图回调
 */
public interface RegisterView extends BaseView {

    void getScoolInfo(List<SchoolInfo> infoList);
    void getGradelInfo(List<GradeInfo> infoList);
    void onRegisterResult(UserInfo info);
}
