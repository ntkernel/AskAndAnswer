package com.ruiyi.askandanswer.ask.model.request;

public class TeacherAnswerReq {

    private int page;//第几页

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTeacherGuid() {
        return teacherGuid;
    }

    public void setTeacherGuid(String teacherGuid) {
        this.teacherGuid = teacherGuid;
    }

    private int number;//一页显示多少
    private String teacherGuid;//教师的guid
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherProvince() {
        return teacherProvince;
    }

    public void setTeacherProvince(String teacherProvince) {
        this.teacherProvince = teacherProvince;
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    private String teacherProvince;
    private String teacherTitle;
    private String schoolName;

    @Override
    public String toString() {
        return "TeacherAnswerReq{" +
                "page=" + page +
                ", number=" + number +
                ", teacherGuid='" + teacherGuid + '\'' +
                '}';
    }
}
