package com.ruiyi.askandanswer.user.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyRechargeSection extends SectionEntity<RechargeData.RechargesBean.RechargeGroupBean> {

    public MyRechargeSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyRechargeSection(RechargeData.RechargesBean.RechargeGroupBean t) {
        super(t);
    }

}
