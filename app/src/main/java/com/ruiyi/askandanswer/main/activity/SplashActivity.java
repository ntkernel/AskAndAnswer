package com.ruiyi.askandanswer.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.user.activity.LoginActivity;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.NetWorkUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.utils.ToolUtils;

public class SplashActivity extends AppCompatActivity {

    private View loadView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        loadView = LayoutInflater.from(SplashActivity.this).inflate(R.layout.activity_splash,null);
        setContentView(loadView);
        // 网络状态
        if (!NetWorkUtils.isNetWorkAvailable(this)) {
            ToastUtil.show("亲，您的网络挂了！");
        }

        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(3000);
        loadView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gotoMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void gotoMainActivity() {
        if (AppPrefsUtils.getBoolean(SharedPreferencesKey.SHAREDPREFERENCES_ISLOGIN, false)) {//false未登录true登录
            ToolUtils.startActivity(SplashActivity.this,MainActivity.class,null);
        } else {
            ToolUtils.startActivity(SplashActivity.this,LoginActivity.class,null);
        }
        finish();
    }
}
