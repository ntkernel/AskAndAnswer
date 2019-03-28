package com.ruiyi.askandanswer.user.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.CityData;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.user.model.ProvinceData;
import com.ruiyi.askandanswer.user.model.SchoolData;
import com.ruiyi.askandanswer.user.model.SchoolInfo;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.presenter.RegisterPresenter;
import com.ruiyi.askandanswer.user.view.RegisterView;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
    注册界面
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {

    @BindView(R.id.mMobileEt)
    EditText mMobileEt;
    @BindView(R.id.mVerifyCodeEt)
    EditText mVerifyCodeEt;
    @BindView(R.id.mPwdEt)
    EditText mPwdEt;
    @BindView(R.id.mPwdConfirmEt)
    EditText mPwdConfirmEt;
    @BindView(R.id.mRegisterBtn)
    Button mRegisterBtn;
    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mVerifyCodeBtn)
    Button mVerifyCodeBtn;
    @BindView(R.id.mNameEt)
    EditText mNameEt;
    @BindView(R.id.mSchoolTv)
    TextView mSchoolTv;
    @BindView(R.id.mGradeSp)
    AppCompatSpinner mGradeSp;
    @BindView(R.id.mGenderSp)
    AppCompatSpinner mGenderSp;
    private TimeCount timer;
    private ArrayAdapter mGradeAdapter;
    private List<String> gradeList;
    private List<GradeInfo> gradeInfoList;
    private String mGender;//性别
    private String mGrade;//年级
    private int mGradeCode;//年级code
    private String mSchoolName;//学校名
    private String mSchoolGuid;//学校Guid
    private List<ProvinceData> provinceDataList;//省列表
    private OptionsPickerView mCityPicker;//三级联动
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        provinceDataList = new ArrayList<>();
        gradeList = new ArrayList<>();
        gradeInfoList = new ArrayList<>();
        timer = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        mPresenter = new RegisterPresenter(RegisterActivity.this);
        mPresenter.mBaseView = this;
        mHeaderBar.getLeftView().setVisibility(View.GONE);
        initListener();
    }

    private void initListener() {
        mNameEt.addTextChangedListener(new TextChange());
        mPwdEt.addTextChangedListener(new TextChange());
        mMobileEt.addTextChangedListener(new TextChange());
        mVerifyCodeEt.addTextChangedListener(new TextChange());
        mPwdConfirmEt.addTextChangedListener(new TextChange());
        mPresenter.getSchool();
        mPresenter.getGrade();

        mGradeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGrade = mGradeSp.getSelectedItem().toString();
                for(GradeInfo gradeInfo : gradeInfoList){
                    if(gradeInfo.getGradeName().equals(mGrade))
                        mGradeCode = gradeInfo.getGradeCode();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGrade = gradeInfoList.get(0).getGradeName();
                mGradeCode = gradeInfoList.get(0).getGradeCode();
            }
        });

        mGenderSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGender = mGenderSp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = "男";
            }
        });
    }

    //三级联动
    private void initCityPicker(){

        mCityPicker = new OptionsPickerBuilder(RegisterActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String schoolName = options3Items.get(options1).get(option2).get(options3);
                for (int i = 0;i < provinceDataList.size();i++){
                    for(int j = 0;j < provinceDataList.get(i).getCityDataList().size();j++){
                        for (int k = 0;k < provinceDataList.get(i).getCityDataList().get(j).getSchoolDataList().size();k++) {
                            if (provinceDataList.get(i).getCityDataList().get(j).getSchoolDataList().get(k).getSchoolName().equals(schoolName)) {
                                mSchoolGuid = provinceDataList.get(i).getCityDataList().get(j).getSchoolDataList().get(k).getSchoolGuid();
                                mSchoolTv.setText(schoolName);
                            }
                        }
                    }
                }
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20).build();
        mCityPicker.setPicker(options1Items, options2Items, options3Items);
    }

    @OnClick({R.id.mRegisterBtn, R.id.mVerifyCodeBtn,R.id.mSchoolTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mRegisterBtn:
                String pwd = mPwdEt.getText().toString();
                String confirmPwd = mPwdConfirmEt.getText().toString();
                if(pwd.equals(confirmPwd))
                    mPresenter.register(mNameEt.getText().toString(),mSchoolName,mSchoolGuid,mGradeCode,mGender,
                        mMobileEt.getText().toString(),mVerifyCodeEt.getText().toString(),mPwdEt.getText().toString());
                else
                    ToastUtil.show("两次输入密码不一致");
                break;
            case R.id.mVerifyCodeBtn:
                String phone = mMobileEt.getText().toString();
                if (ToolUtils.isMobileNO(phone)) {
                    timer.start();
                    mPresenter.getCode(phone);
                } else
                    ToastUtil.show("请输入正确的手机号");
                break;
            case R.id.mSchoolTv:
                mCityPicker.show();
                break;
            default:
                break;
        }
    }

    /**
     * 定义一个倒计时内部类
     *
     * @author
     */
    private class TimeCount extends CountDownTimer {

        TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (mVerifyCodeBtn != null) {
                mVerifyCodeBtn.setClickable(false);
                String second = millisUntilFinished / 1000 + "秒";
                mVerifyCodeBtn.setText(second);
            }
        }

        @Override
        public void onFinish() {
            if (mVerifyCodeBtn != null) {
                mVerifyCodeBtn.setText("获取验证码");
                mVerifyCodeBtn.setClickable(true);
            }
        }
    }

    // EditText监听器
    private class TextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {
            boolean Sign0 = mNameEt.getText().length() > 0;
            boolean Sign1 = mMobileEt.getText().length() > 0;
            boolean Sign2 = mPwdEt.getText().length() > 0;
            boolean Sign3 = mVerifyCodeEt.getText().length() > 0;
            boolean Sign4 = mPwdConfirmEt.getText().length() > 0;

            if (Sign1 && Sign2 && Sign3 && Sign4) {
                mRegisterBtn.setEnabled(true);
            }
            // 在layout文件中，对Button的text属性应预先设置默认值，否则刚打开程序的时候Button是无显示的
            else {
                mRegisterBtn.setEnabled(false);
            }

            if (Sign1)
                mVerifyCodeBtn.setEnabled(true);
            else
                mVerifyCodeBtn.setEnabled(false);
        }
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

        for(int i = 0;i < provinceDataList.size();i++){
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < provinceDataList.get(i).getCityDataList().size(); c++) {//遍历该省份的所有城市
                String city = provinceDataList.get(i).getCityDataList().get(c).getCityName();
                cityList.add(city);//添加城市
                ArrayList<String> schoolName = new ArrayList<>();
                for (int s = 0; s < provinceDataList.get(i).getCityDataList().get(c).getSchoolDataList().size(); s++) {//遍历该省份的所有城市
                    schoolName.add(provinceDataList.get(i).getCityDataList().get(c).getSchoolDataList().get(s).getSchoolName());
                }
                ArrayList<String> school = new ArrayList<>();//该城市的所有地区列表
                school.addAll(schoolName);
                province_AreaList.add(school);//添加该省所有地区数据
            }
            options1Items.add(provinceDataList.get(i).getProvinceName());
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
        initCityPicker();
    }

    @Override
    public void getGradelInfo(List<GradeInfo> infoList) {
        gradeList.clear();
        gradeInfoList = infoList;
        for (GradeInfo info : infoList) {
            gradeList.add(info.getGradeName());
        }
        mGradeAdapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_item, gradeList);
        mGradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGradeSp.setAdapter(mGradeAdapter);
    }

    @Override
    public void onRegisterResult(UserInfo info) {
        ToastUtil.show("注册成功");
        finish();
    }
}
