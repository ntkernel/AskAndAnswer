package com.ruiyi.askandanswer.main.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.fragment.AskFragment;
import com.ruiyi.askandanswer.base.AppManager;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.main.fragment.HomeFragment;
import com.ruiyi.askandanswer.user.fragment.MineFragment;
import com.ruiyi.askandanswer.popwindow.AskPop;
import com.ruiyi.askandanswer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private Long pressTime = 0l;
    @BindView(R.id.mBottomBar)
    BottomNavigationBar mBottomBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private HomeFragment mHomeFragment;
    private AskFragment mAskFragment;
    private MineFragment mMineFragment;
    private int perSelected = 0;//记录上一次选择的tab

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /**
         * bottomNavigation 设置
         */

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        mBottomBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor(R.color.common_blue) //选中颜色
                .setInActiveColor(R.color.text_normal) //未选中颜色
                .setBarBackgroundColor(R.color.common_white);//导航栏背景色

        /** 添加导航按钮 */
        mBottomBar
                .addItem(new BottomNavigationItem(R.drawable.tab_home_bg, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.bg_white, ""))
                .addItem(new BottomNavigationItem(R.drawable.tab_mine_bg, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏

    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = new HomeFragment();
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();
    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        Log.d("cj", "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
                perSelected = 0;
                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                AskPop askPop = new AskPop(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
                askPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);

                if (mAskFragment == null) {
                    mAskFragment = new AskFragment();
                }
                transaction.replace(R.id.tb, mAskFragment);

                askPop.setOnCloseListener(new AskPop.OnCloseListener() {
                    @Override
                    public void onClose() {
                        Log.d("cj", "cj====>>>");
                        mBottomBar.selectTab(perSelected);
                        askPop.dismiss();
                    }

                    @Override
                    public void onSelected() {
                        mBottomBar.selectTab(perSelected);
                        askPop.dismiss();
                    }
                });
                break;
            case 2:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                }
                perSelected = 2;
                transaction.replace(R.id.tb, mMineFragment);
                break;

            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Long time = System.currentTimeMillis();
            if (time - pressTime > 2000) {
                ToastUtil.show("再按一次退出程序");
                pressTime = time;
            }else {
                AppManager.getAppManager().AppExit(MainActivity.this);
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {

    }
}
