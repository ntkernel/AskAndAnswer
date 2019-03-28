package com.ruiyi.askandanswer.user.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyCoinSection extends SectionEntity<CionData.CostsBean.QuestionDataGroupBean> {

    public MyCoinSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyCoinSection(CionData.CostsBean.QuestionDataGroupBean t) {
        super(t);
    }

}
