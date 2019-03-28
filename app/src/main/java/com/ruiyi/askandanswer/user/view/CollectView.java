package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.CollectData;

import java.util.List;

/*
    足迹 视图回调
 */
public interface CollectView extends BaseView {

    void onCollectResult(CollectData info);

    void onCancleCollectResult();
}
