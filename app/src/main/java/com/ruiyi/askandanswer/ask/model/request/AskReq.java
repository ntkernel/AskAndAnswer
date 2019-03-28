package com.ruiyi.askandanswer.ask.model.request;

public class AskReq {

    public String getAskQuestionGuid() {
        return askQuestionGuid;
    }

    public void setAskQuestionGuid(String askQuestionGuid) {
        this.askQuestionGuid = askQuestionGuid;
    }

    public String getToUserGuid() {
        return toUserGuid;
    }

    public void setToUserGuid(String toUserGuid) {
        this.toUserGuid = toUserGuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String askQuestionGuid;//问题的guid
    private String toUserGuid;//教师的guid
    private String content;//提问的内容
}
