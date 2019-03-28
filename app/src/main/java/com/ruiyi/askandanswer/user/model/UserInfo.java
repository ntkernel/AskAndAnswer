package com.ruiyi.askandanswer.user.model;


public class UserInfo {

    /**
     * tokenString : eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJHdWlkIjoiYThiMTEyN2UtNWVmYi0xMWU4LTg3MjAtMzMzMzMzMzMzMzMzMyIsIk5hbWUiOiLlsI_lsI_mmI4iLCJSb2xlSWQiOiIwNyIsIm5iZiI6MTU0NjE1ODk2NSwiZXhwIjoxNTQ2MjQ1MzY1LCJpc3MiOiJSdWlZaVl1biIsImF1ZCI6IlN0dWRlbnRzIn0.KM9O16AUF7nzGOQuCrm4tBwFqlhRk4mFscMW8fTCxJo
     * name : 小小明
     * avatarUrl : https://center.lexuewang.cn:5002/a8b/112/7e5/a8b1127e-5efb-11e8-8720-3333333333333.jpeg
     * schoolGuid : 172FA177222540D99FCFED96F8AA6990
     * schoolName : 中山中学
     * grade : 303
     */

    private String tokenString;
    private String name;
    private String avatarUrl;
    private String schoolGuid;
    private String schoolName;
    private String grade;
    private int attentionNumber;
    private String province;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    private String guid;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;

    public int getCoinNumber() {
        return coinNumber;
    }

    public void setCoinNumber(int coinNumber) {
        this.coinNumber = coinNumber;
    }

    private int coinNumber;

    public int getAttentionNumber() {
        return attentionNumber;
    }

    public void setAttentionNumber(int attentionNumber) {
        this.attentionNumber = attentionNumber;
    }

    public int getFootprintNumber() {
        return footprintNumber;
    }

    public void setFootprintNumber(int footprintNumber) {
        this.footprintNumber = footprintNumber;
    }

    public int getMyAskNumber() {
        return myAskNumber;
    }

    public void setMyAskNumber(int myAskNumber) {
        this.myAskNumber = myAskNumber;
    }

    private int footprintNumber;
    private int myAskNumber;

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
