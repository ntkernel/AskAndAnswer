package com.ruiyi.askandanswer.user.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyCollectSection extends SectionEntity<CollectData.QuestionCollectBean.QuestionCollectGroupBean> {

    public CollectData.QuestionCollectBean.QuestionCollectGroupBean getQuestionCollectGroupBean() {
        return questionCollectGroupBean;
    }

    public void setQuestionCollectGroupBean(CollectData.QuestionCollectBean.QuestionCollectGroupBean questionCollectGroupBean) {
        this.questionCollectGroupBean = questionCollectGroupBean;
    }

    private CollectData.QuestionCollectBean.QuestionCollectGroupBean questionCollectGroupBean;

    public MyCollectSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyCollectSection(CollectData.QuestionCollectBean.QuestionCollectGroupBean t) {
        super(t);
        this.questionCollectGroupBean = t;
    }

}
