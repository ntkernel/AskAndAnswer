package com.ruiyi.askandanswer.user.model;

public class SchoolData {

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

    private String schoolGuid;
    private String schoolName;

    @Override
    public String toString() {
        return "SchoolData{" +
                "schoolGuid='" + schoolGuid + '\'' +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
