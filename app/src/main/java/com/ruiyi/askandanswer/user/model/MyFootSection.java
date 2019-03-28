package com.ruiyi.askandanswer.user.model;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.ruiyi.askandanswer.user.model.FootData;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyFootSection extends SectionEntity<FootData.QuestionDataBean.QuestionDataGroupBean> {

    public MyFootSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyFootSection(FootData.QuestionDataBean.QuestionDataGroupBean t) {
        super(t);
    }

}
