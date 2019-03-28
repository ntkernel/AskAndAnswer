package com.ruiyi.askandanswer.ask.model.request;

public class AskDataReq {

    private String subjectId;
    private int grade;

    public String getSubjectId() {
        return subjectId;

    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(int privateFlag) {
        this.privateFlag = privateFlag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestionGuid() {
        return questionGuid;
    }

    public void setQuestionGuid(String questionGuid) {
        this.questionGuid = questionGuid;
    }

    public String getTeacherGuid() {
        return teacherGuid;
    }

    public void setTeacherGuid(String teacherGuid) {
        this.teacherGuid = teacherGuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSchoolGuid() {
        return schoolGuid;
    }

    public void setSchoolGuid(String schoolGuid) {
        this.schoolGuid = schoolGuid;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private int privateFlag;//是否公开
    private int type;//问题还是其他
    private String questionGuid;//如果是通过通过题目直接提问的话，没有传空字符串
    private String teacherGuid;//如果有指定教师的话，没有传空字符串
    private String content;//提问的内容
    private String schoolGuid;
    private String schoolName;
    private String province;
}
