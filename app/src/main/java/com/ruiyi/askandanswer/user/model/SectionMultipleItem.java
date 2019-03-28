package com.ruiyi.askandanswer.user.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionMultiEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionMultipleItem extends SectionMultiEntity<CionData.CostsBean.QuestionDataGroupBean> implements MultiItemEntity {

    public static final int ANSWERD = 1;
    public static final int WAITE = 0;
    private int itemType;

    public CionData.CostsBean.QuestionDataGroupBean getQuestionDataGroupBean() {
        return questionDataGroupBean;
    }

    public void setQuestionDataGroupBean(CionData.CostsBean.QuestionDataGroupBean questionDataGroupBean) {
        this.questionDataGroupBean = questionDataGroupBean;
    }

    private CionData.CostsBean.QuestionDataGroupBean questionDataGroupBean;

    public SectionMultipleItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionMultipleItem(int itemType, CionData.CostsBean.QuestionDataGroupBean questionDataGroupBean) {
        super(questionDataGroupBean);
        this.questionDataGroupBean = questionDataGroupBean;
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
