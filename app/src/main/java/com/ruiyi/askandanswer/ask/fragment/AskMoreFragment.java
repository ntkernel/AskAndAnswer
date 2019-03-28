package com.ruiyi.askandanswer.ask.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AskMoreFragment extends BaseFragment{

    Unbinder unbinder;
    @BindView(R.id.mKnowRb)
    RadioButton mKnowRb;
    @BindView(R.id.mErrorRb)
    RadioButton mErrorRb;
    @BindView(R.id.mAskRg)
    RadioGroup mAskRg;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private String mSubjectId;
    private List<BaseFragment> mBaseFragmentLists;
    private AskMoreKnowFragment mAskMoreKonwFragment;
    private AskMoreErrorFragment mAskMoreErrorFragment;
    private FragmentPagerAdapter mPagerAdapter;
//    private FragmentTabAdapter mFragementTabAdapter;

    public void setArguements(String subjectId) {
        this.mSubjectId = subjectId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_askmore, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mAskRg.check(R.id.mKnowRb);
        mAskMoreKonwFragment = new AskMoreKnowFragment();
        mAskMoreErrorFragment = new AskMoreErrorFragment();
        mAskMoreErrorFragment.setArguements(mSubjectId);
        mAskMoreKonwFragment.setArguements(mSubjectId);

        mBaseFragmentLists = new ArrayList<>();
        mBaseFragmentLists.add(mAskMoreKonwFragment);
        mBaseFragmentLists.add(mAskMoreErrorFragment);

        mPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
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
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int positon) {
                switch (positon){
                    case 0:
                        mAskRg.check(R.id.mKnowRb);
                        break;
                    case 1:
                        mAskRg.check(R.id.mErrorRb);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

//        mFragementTabAdapter = new FragmentTabAdapter(getActivity(), mBaseFragmentLists, R.id.mContentLl, mAskRg);
        mAskRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.mKnowRb:
                        /**
                         * setCurrentItem第二个参数控制页面切换动画
                         * true:打开/false:关闭
                         */
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.mErrorRb:
                        mViewPager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
