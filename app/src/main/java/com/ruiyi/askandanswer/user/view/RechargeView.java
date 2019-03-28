package com.ruiyi.askandanswer.user.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.CionData;
import com.ruiyi.askandanswer.user.model.RechargeData;

/*
   视图回调
 */
public interface RechargeView extends BaseView {

    void onCionResult(CionData info);
    void onRechargeResult(RechargeData info);
}
