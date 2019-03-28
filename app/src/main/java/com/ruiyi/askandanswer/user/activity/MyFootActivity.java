package com.ruiyi.askandanswer.user.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.SearchActivity;
import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.presenter.AskMorePresenter;
import com.ruiyi.askandanswer.ask.view.AskMoreView;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.user.fragment.MyFootFragment;
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

public class MyFootActivity extends BaseActivity implements AskMoreView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mIndicator)
    MagicIndicator mIndicator;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private FragmentPagerAdapter mPagerAdapter;
    private List<BaseFragment> mBaseFragmentLists;
    private List<String> titleLists;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ((AskMorePresenter) mPresenter).getSubjects(1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfoot);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleLists = new ArrayList<>();
        mBaseFragmentLists = new ArrayList<>();

        mPresenter = new AskMorePresenter(MyFootActivity.this,mHander);
        mPresenter.mBaseView = this;
        ((AskMorePresenter) mPresenter).getSubjects(1);
    }

    @Override
    public void onSubjectsResult(List<SubjectData> subjectDataList) {
        for (SubjectData item : subjectDataList) {
            titleLists.add(item.getName());
            MyFootFragment fragmenet = new MyFootFragment();
            fragmenet.setArguements(item.getCode());
            mBaseFragmentLists.add(fragmenet);
        }
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

        CommonNavigator commonNavigator = new CommonNavigator(MyFootActivity.this);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleLists == null ? 0 : titleLists.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.text_normal));
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.common_blue));
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
                indicator.setColors(Color.parseColor("#03a9f4"));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, mViewPager);
    }

    @Override
    public void onMoreKnowResult(MoreKnowData moreKnowData) {

    }

    @Override
    public void onMoreErrorResult(MoreErrorData moreErrorData) {

    }
}
