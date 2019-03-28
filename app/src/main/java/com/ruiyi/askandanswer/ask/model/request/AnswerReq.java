package com.ruiyi.askandanswer.ask.model.request;

public class AnswerReq {

    private String questionGuid;
    private String answer;

    public int getCollectionFlag() {
        return collectionFlag;
    }

    public void setCollectionFlag(int collectionFlag) {
        this.collectionFlag = collectionFlag;
    }

    private int collectionFlag;

    public String getQuestionGuid() {
        return questionGuid;
    }

    public void setQuestionGuid(String questionGuid) {
        this.questionGuid = questionGuid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getThemeGuid() {
        return themeGuid;
    }

    public void setThemeGuid(String themeGuid) {
        this.themeGuid = themeGuid;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String themeGuid;
    private String subjectId;
    private String remark;

    public int getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(int answerResult) {
        this.answerResult = answerResult;
    }

    private int answerResult;

}
