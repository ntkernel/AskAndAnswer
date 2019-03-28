package com.ruiyi.askandanswer.ask.view;

import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.GradeInfo;

import java.util.List;

public interface AskMoreView extends BaseView {

    void onSubjectsResult(List<SubjectData> subjectDataList);

    void onMoreKnowResult(MoreKnowData moreKnowData);

    void onMoreErrorResult(MoreErrorData moreErrorData);
}
