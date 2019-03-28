package com.ruiyi.askandanswer.user.model;

public class SchoolInfo {


    /**
     * schoolGuid : 132015661
     * schoolName : 北京展示中心
     * provinceCode : 11
     * provinceName : 北京
     * cityCode : 110108
     * cityName : 海淀区
     */

    private String schoolGuid;
    private String schoolName;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;

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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
