package com.ruiyi.askandanswer.user.model;

import java.util.List;

public class CityData {

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    private String cityName;
    private String cityCode;

    public List<SchoolData> getSchoolDataList() {
        return schoolDataList;
    }

    public void setSchoolDataList(List<SchoolData> schoolDataList) {
        this.schoolDataList = schoolDataList;
    }

    private List<SchoolData> schoolDataList;

    @Override
    public String toString() {
        return "CityData{" +
                "cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", schoolDataList=" + schoolDataList +
                '}';
    }
}
