package com.ruiyi.askandanswer.ask.view;

import com.ruiyi.askandanswer.ask.model.response.QuestionData;
import com.ruiyi.askandanswer.ask.model.response.QuestionModel;
import com.ruiyi.askandanswer.base.BaseView;

public interface QuestionView extends BaseView {

    void onQuestionReault(QuestionModel questionData);
    void onAnswerResult();
    void onCollectResult();
}
