package com.ruiyi.askandanswer.ask.view;

import com.ruiyi.askandanswer.ask.model.response.TeacherAnswerData;
import com.ruiyi.askandanswer.base.BaseView;

public interface TeacherInfoView extends BaseView {

    void onTeacherInfoReault(TeacherAnswerData teacherAnswerData);

    void onAttentionResult();

    void onCancleAttentionResult();
}
