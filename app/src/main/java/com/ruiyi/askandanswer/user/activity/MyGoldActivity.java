package com.ruiyi.askandanswer.user.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.user.fragment.MyAskFragment;
import com.ruiyi.askandanswer.user.fragment.MyCionFragment;
import com.ruiyi.askandanswer.user.fragment.MyRechargeFragment;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyGoldActivity extends BaseActivity {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mMoneyTv)
    TextView mMoneyTv;
    @BindView(R.id.mChargeTv)
    TextView mChargeTv;
    @BindView(R.id.mIndicator)
    MagicIndicator mIndicator;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private FragmentPagerAdapter mPagerAdapter;
    private List<BaseFragment> mBaseFragmentLists;
    private List<String> titleLists;
    private String goldNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        goldNum = getIntent().getStringExtra("Gold");
        mMoneyTv.setText(goldNum);
        titleLists = new ArrayList<>();
        mBaseFragmentLists = new ArrayList<>();
        initData();
    }

    private void initData() {
        titleLists.add("消费记录");
        titleLists.add("充值记录");
        MyCionFragment myCionFragment = new MyCionFragment();
        myCionFragment.setArguements(1);
        mBaseFragmentLists.add(myCionFragment);
        MyRechargeFragment rechargeFragment = new MyRechargeFragment();
        rechargeFragment.setArguements(2);
        mBaseFragmentLists.add(rechargeFragment);

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mBaseFragmentLists.get(i);
            }

            @Override
            public int getCount() {
                return mBaseFragmentLists.size();
            }
        };
        mViewPager.setAdapter(mPagerAdapter);

        mIndicator.setBackgroundResource(R.drawable.indicator_bg);
        CommonNavigator commonNavigator = new CommonNavigator(MyGoldActivity.this);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleLists == null ? 0 : titleLists.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.common_blue));
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.common_white));
                colorTransitionPagerTitleView.setText(titleLists.get(index));
                colorTransitionPagerTitleView.setTextSize(14f);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.common_indicator_height);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(5);
                indicator.setYOffset(borderWidth);
                indicator.setColors(Color.parseColor("#03a9f4"));
                return indicator;
            }
        });
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, mViewPager);
    }

    @OnClick(R.id.mChargeTv)
    public void onViewClicked() {
        ToolUtils.startActivity(MyGoldActivity.this,RechargeActivity.class,null);
    }
}
