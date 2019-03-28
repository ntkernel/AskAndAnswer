package com.ruiyi.askandanswer.user.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.GradeInfo;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyGradeActivity extends BaseActivity<UserPresenter> implements UserView,RegisterView {


    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mGradeTv)
    TextView mGradeTv;
    @BindView(R.id.mGradeSp)
    AppCompatSpinner mGradeSp;
    @BindView(R.id.mConfirmBtn)
    Button mConfirmBtn;
    private String mGrade;//年级
    private List<String> gradeList;
    private List<GradeInfo> gradeInfoList;
    private ArrayAdapter mGradeAdapter;
    private int mGradeCode;//年级code

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 4:
                    mPresenter.changeGrade(mGradeCode + "",4);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifygrade);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        gradeList = new ArrayList<>();
        gradeInfoList = new ArrayList<>();
        mPresenter = new UserPresenter(ModifyGradeActivity.this,mHander);
        mPresenter.mBaseView = this;
        mPresenter.getGrade();

        mGradeTv.setText("原年级：" + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_GRADE,""));

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
    }

    @Override
    public void onForgetPwdResult(UserInfo info) {

    }

    @Override
    public void onChangeResult() {
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_GRADE,mGrade);
        ToastUtil.show("修改成功");
        finish();
    }

    @OnClick(R.id.mConfirmBtn)
    public void onViewClicked() {
        mPresenter.changeGrade(mGradeCode + "",4);
    }

    @Override
    public void getScoolInfo(List<SchoolInfo> infoList) {

    }

    @Override
    public void getGradelInfo(List<GradeInfo> infoList) {
        gradeList.clear();
        gradeInfoList = infoList;
        for (GradeInfo info : infoList) {
            gradeList.add(info.getGradeName());
        }
        mGradeAdapter = new ArrayAdapter(ModifyGradeActivity.this, android.R.layout.simple_spinner_item, gradeList);
        mGradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGradeSp.setAdapter(mGradeAdapter);
    }

    @Override
    public void onRegisterResult(UserInfo info) {

    }
}
