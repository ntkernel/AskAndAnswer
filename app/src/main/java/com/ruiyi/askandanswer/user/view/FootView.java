package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.FootData;

/*
    足迹 视图回调
 */
public interface FootView extends BaseView {

    void onFootResult(FootData info);
}
