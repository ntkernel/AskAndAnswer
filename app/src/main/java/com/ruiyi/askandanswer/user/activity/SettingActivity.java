package com.ruiyi.askandanswer.user.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.AppManager;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mExitBtn)
    Button mExitBtn;
    @BindView(R.id.mNameTv)
    TextView mNameTv;
    @BindView(R.id.mPhoneNextIv)
    ImageView mPhoneNextIv;
    @BindView(R.id.mPhoneTv)
    TextView mPhoneTv;
    @BindView(R.id.mSchoolNextIv)
    ImageView mSchoolNextIv;
    @BindView(R.id.mSchoolTv)
    TextView mSchoolTv;
    @BindView(R.id.mGradeNextIv)
    ImageView mGradeNextIv;
    @BindView(R.id.mGradeTv)
    TextView mGradeTv;
    @BindView(R.id.mNameRl)
    RelativeLayout mNameRl;
    @BindView(R.id.mPhoneRl)
    RelativeLayout mPhoneRl;
    @BindView(R.id.mPwdRl)
    RelativeLayout mPwdRl;
    @BindView(R.id.mSchoolRl)
    RelativeLayout mSchoolRl;
    @BindView(R.id.mGradeRl)
    RelativeLayout mGradeRl;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initDialog();
        mNameTv.setText(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_NAME, ""));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPhoneTv.setText(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PHONE, ""));
        mGradeTv.setText(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_GRADE,""));
        mSchoolTv.setText(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME,""));
    }

    /*
     初始化AlertDialog
      */
    public void initDialog() {
        //创建AlertDialog的构造器的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        //设置构造器标题
        builder.setTitle("提示");
        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
        builder.setMessage("您确定退出么？");
        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //第一个参数dialog是点击的确定按钮所属的Dialog对象,第二个参数which是按钮的标示值
                AppPrefsUtils.saveBoolean(SharedPreferencesKey.SHAREDPREFERENCES_ISLOGIN, false);
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN, "");
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_AVATARURL, "");
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_NAME, "");
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLGUID, "");
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME, "");
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_GRADE, "");
                mAlertDialog.dismiss();
                AppManager.getAppManager().AppExit(SettingActivity.this);
            }
        });
        //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialog.dismiss();
            }
        });
        //利用构造器创建AlertDialog的对象,实现实例化
        mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
    }

    @OnClick({R.id.mNameRl, R.id.mPhoneRl, R.id.mPwdRl, R.id.mSchoolRl, R.id.mGradeRl,R.id.mExitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mNameRl:
                break;
            case R.id.mPhoneRl:
                ToolUtils.startActivity(SettingActivity.this,ModifyPhoneActivity.class,null);
                break;
            case R.id.mPwdRl:
                ToolUtils.startActivity(SettingActivity.this,ModifyPwdActivity.class,null);
                break;
            case R.id.mSchoolRl:
                ToolUtils.startActivity(SettingActivity.this,ModifySchoolActivity.class,null);
                break;
            case R.id.mGradeRl:
                ToolUtils.startActivity(SettingActivity.this,ModifyGradeActivity.class,null);
                break;
            case R.id.mExitBtn:
                //当AlertDialog存在实例对象并且没有在展示时
                if (mAlertDialog != null && !mAlertDialog.isShowing()) {
                    mAlertDialog.show();
                    mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.common_blue));
                    mAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.text_light_dark));
                }
                break;
        }
    }
}
