package com.ruiyi.askandanswer.user.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.CityData;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.model.ProvinceData;
import com.ruiyi.askandanswer.user.model.SchoolData;
import com.ruiyi.askandanswer.user.model.SchoolInfo;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.RegisterView;
import com.ruiyi.askandanswer.user.view.UserView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifySchoolActivity extends BaseActivity<UserPresenter> implements UserView,RegisterView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mSchoolNameTv)
    TextView mSchoolNameTv;
    @BindView(R.id.mProvinceSp)
    AppCompatSpinner mProvinceSp;
    @BindView(R.id.mCitySp)
    AppCompatSpinner mCitySp;
    @BindView(R.id.mSchoolSp)
    AppCompatSpinner mSchoolSp;
    @BindView(R.id.mConfirmBtn)
    Button mConfirmBtn;
    private String mSchoolName;//学校名
    private String mSchoolGuid;//学校Guid
    private List<ProvinceData> provinceDataList;//省列表
    private List<CityData> cityDataLists;//市列表
    private List<SchoolData> schoolDataLists;//省列表
    private ArrayAdapter mSchoolAdapter;
    private ArrayAdapter mCityAdapter;
    private ArrayAdapter mProvinceAdapter;
    private List<String> provinceList;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 3:
                    mPresenter.changeSchool(mSchoolGuid,mSchoolName,3);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyschool);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        provinceList = new ArrayList<>();
        provinceDataList = new ArrayList<>();
        cityDataLists = new ArrayList<>();
        schoolDataLists = new ArrayList<>();
        mSchoolNameTv.setText("原学校：" + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME,""));
        mPresenter = new UserPresenter(ModifySchoolActivity.this,mHander);
        mPresenter.mBaseView = this;
        mPresenter.getSchool();
    }

    @OnClick(R.id.mConfirmBtn)
    public void onViewClicked() {
        mPresenter.changeSchool(mSchoolGuid,mSchoolName,3);
    }

    @Override
    public void onForgetPwdResult(UserInfo info) {

    }

    @Override
    public void onChangeResult() {
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME,mSchoolName);
        ToastUtil.show("修改成功");
        finish();
    }

    @Override
    public void getScoolInfo(List<SchoolInfo> infoList) {
        //由于后台不是个的三级结构所以这边自己构建

        SchoolData schoolData = new SchoolData();
        schoolData.setSchoolGuid(infoList.get(0).getSchoolGuid());
        schoolData.setSchoolName(infoList.get(0).getSchoolName());
        List<SchoolData> schoolDataList = new ArrayList<>();
        schoolDataList.add(schoolData);
        CityData cityData = new CityData();
        cityData.setCityCode(infoList.get(0).getCityCode());
        cityData.setCityName(infoList.get(0).getCityName());
        cityData.setSchoolDataList(schoolDataList);
        List<CityData> cityDataList = new ArrayList<>();
        cityDataList.add(cityData);
        ProvinceData provinceData = new ProvinceData();
        provinceData.setProvinceCode(infoList.get(0).getProvinceCode());
        provinceData.setProvinceName(infoList.get(0).getProvinceName());
        provinceData.setCityDataList(cityDataList);
        provinceDataList.add(provinceData);

        SchoolData schoolData1 = new SchoolData();
        schoolData1.setSchoolGuid(infoList.get(1).getSchoolGuid());
        schoolData1.setSchoolName(infoList.get(1).getSchoolName());
        List<SchoolData> schoolDataList1 = new ArrayList<>();
        schoolDataList1.add(schoolData1);
        CityData cityData1 = new CityData();
        cityData1.setCityCode(infoList.get(1).getCityCode());
        cityData1.setCityName(infoList.get(1).getCityName());
        cityData1.setSchoolDataList(schoolDataList1);
        List<CityData> cityDataList1 = new ArrayList<>();
        cityDataList1.add(cityData1);

        SchoolData schoolData3 = new SchoolData();
        schoolData3.setSchoolGuid(infoList.get(3).getSchoolGuid());
        schoolData3.setSchoolName(infoList.get(3).getSchoolName());
        List<SchoolData> schoolDataList3 = new ArrayList<>();
        schoolDataList3.add(schoolData3);
        CityData cityData3 = new CityData();
        cityData3.setCityCode(infoList.get(3).getCityCode());
        cityData3.setCityName(infoList.get(3).getCityName());
        cityData3.setSchoolDataList(schoolDataList3);
        cityDataList1.add(cityData3);
        ProvinceData provinceData1 = new ProvinceData();
        provinceData1.setProvinceCode(infoList.get(1).getProvinceCode());
        provinceData1.setProvinceName(infoList.get(1).getProvinceName());
        provinceData1.setCityDataList(cityDataList1);
        provinceDataList.add(provinceData1);

        SchoolData schoolData2 = new SchoolData();
        schoolData2.setSchoolGuid(infoList.get(2).getSchoolGuid());
        schoolData2.setSchoolName(infoList.get(2).getSchoolName());
        List<SchoolData> schoolDataList2 = new ArrayList<>();
        schoolDataList2.add(schoolData2);
        CityData cityData2 = new CityData();
        cityData2.setCityCode(infoList.get(2).getCityCode());
        cityData2.setCityName(infoList.get(2).getCityName());
        cityData2.setSchoolDataList(schoolDataList1);
        List<CityData> cityDataList2 = new ArrayList<>();
        cityDataList2.add(cityData2);

        SchoolData schoolData5 = new SchoolData();
        schoolData5.setSchoolGuid(infoList.get(5).getSchoolGuid());
        schoolData5.setSchoolName(infoList.get(5).getSchoolName());
        List<SchoolData> schoolDataList5 = new ArrayList<>();
        schoolDataList5.add(schoolData5);
        CityData cityData5 = new CityData();
        cityData5.setCityCode(infoList.get(5).getCityCode());
        cityData5.setCityName(infoList.get(5).getCityName());
        cityData5.setSchoolDataList(schoolDataList5);
        cityDataList2.add(cityData5);
        ProvinceData provinceData2 = new ProvinceData();
        provinceData2.setProvinceCode(infoList.get(2).getProvinceCode());
        provinceData2.setProvinceName(infoList.get(2).getProvinceName());
        provinceData2.setCityDataList(cityDataList2);
        provinceDataList.add(provinceData2);

        SchoolData schoolData4 = new SchoolData();
        schoolData4.setSchoolGuid(infoList.get(4).getSchoolGuid());
        schoolData4.setSchoolName(infoList.get(4).getSchoolName());
        List<SchoolData> schoolDataList4 = new ArrayList<>();
        schoolDataList4.add(schoolData4);
        CityData cityData4 = new CityData();
        cityData4.setCityCode(infoList.get(4).getCityCode());
        cityData4.setCityName(infoList.get(4).getCityName());
        cityData4.setSchoolDataList(schoolDataList4);
        List<CityData> cityDataList4 = new ArrayList<>();
        cityDataList4.add(cityData4);
        ProvinceData provinceData4 = new ProvinceData();
        provinceData4.setProvinceCode(infoList.get(4).getProvinceCode());
        provinceData4.setProvinceName(infoList.get(4).getProvinceName());
        provinceData4.setCityDataList(cityDataList4);
        provinceDataList.add(provinceData4);

        provinceList = new ArrayList<>();
        List<String> cityList = new ArrayList<>();
        List<String> schoolList = new ArrayList<>();
        for(ProvinceData provinceDatas : provinceDataList)
            provinceList.add(provinceDatas.getProvinceName());

        mProvinceAdapter = new ArrayAdapter(ModifySchoolActivity.this, android.R.layout.simple_spinner_item, provinceList);
        mProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProvinceSp.setAdapter(mProvinceAdapter);
        mProvinceSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityDataLists = provinceDataList.get(position).getCityDataList();
                cityList.clear();
                for(CityData cityData : cityDataLists)
                    cityList.add(cityData.getCityName());
                schoolDataLists = provinceDataList.get(position).getCityDataList().get(0).getSchoolDataList();
                schoolList.clear();
                for(SchoolData schoolData : schoolDataLists)
                    schoolList.add(schoolData.getSchoolName());
                mCityAdapter = new ArrayAdapter(ModifySchoolActivity.this, android.R.layout.simple_spinner_item, cityList);
                mCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mCitySp.setAdapter(mCityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mCitySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                schoolDataLists = cityDataLists.get(position ).getSchoolDataList();
                schoolList.clear();
                for(SchoolData schoolData : schoolDataLists)
                    schoolList.add(schoolData.getSchoolName());
                mSchoolAdapter = new ArrayAdapter(ModifySchoolActivity.this, android.R.layout.simple_spinner_item, schoolList);
                mSchoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSchoolSp.setAdapter(mSchoolAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mSchoolSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSchoolName = mSchoolSp.getSelectedItem().toString();
                for(SchoolData schoolData : schoolDataLists){
                    if(schoolData.getSchoolName().equals(mSchoolName))
                        mSchoolGuid = schoolData.getSchoolGuid();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSchoolName = schoolDataLists.get(0).getSchoolName();
                mSchoolGuid = schoolDataLists.get(0).getSchoolGuid();
            }
        });
    }

    @Override
    public void getGradelInfo(List<GradeInfo> infoList) {

    }

    @Override
    public void onRegisterResult(UserInfo info) {

    }
}
