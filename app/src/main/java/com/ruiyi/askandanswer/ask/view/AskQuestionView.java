package com.ruiyi.askandanswer.ask.view;

import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.user.model.GradeInfo;

import java.util.List;

import io.github.yedaxia.richeditor.IUploadEngine;

public interface AskQuestionView extends BaseView {

    void onSubjectsResult(List<SubjectData> subjectDataList);

    void onGradeResult(List<GradeInfo> infoList);

    void onSubmitResult(String guid);

    void onUpLoadImage(List<String> imageList,IUploadEngine.UploadProgressListener listener,String filePath);
}
