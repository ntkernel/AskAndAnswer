package com.ruiyi.askandanswer.ask.view;

import com.ruiyi.askandanswer.base.BaseView;

public interface ResourceView extends BaseView {

    void onResourceResult(byte[] mbyte);

    void onResourceResult(String url);
}
