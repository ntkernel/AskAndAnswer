package com.ruiyi.askandanswer.user.model;

import java.util.List;

public class ProvinceData {

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    private String provinceName;
    private String provinceCode;

    public List<CityData> getCityDataList() {
        return cityDataList;
    }

    public void setCityDataList(List<CityData> cityDataList) {
        this.cityDataList = cityDataList;
    }

    private List<CityData> cityDataList;

    @Override
    public String toString() {
        return "ProvinceData{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityDataList=" + cityDataList +
                '}';
    }
}
