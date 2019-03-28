package com.ruiyi.askandanswer.ask.view;

import com.ruiyi.askandanswer.ask.model.response.AnswerData;
import com.ruiyi.askandanswer.base.BaseView;

public interface AnswerView extends BaseView {

    void getAnswer(AnswerData answerData);

    void onLikeResult();

    void onEndQuestionResult();

    void onAskAgainResult();
}
