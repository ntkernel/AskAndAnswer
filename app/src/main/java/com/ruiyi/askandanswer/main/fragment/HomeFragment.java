package com.ruiyi.askandanswer.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.AnswerActivity;
import com.ruiyi.askandanswer.ask.activity.AskMoreActivity;
import com.ruiyi.askandanswer.ask.presenter.AnsewerPresenter;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.main.model.response.BannerData;
import com.ruiyi.askandanswer.main.model.response.HomeCanKnow;
import com.ruiyi.askandanswer.main.presenter.HomePresenter;
import com.ruiyi.askandanswer.main.view.HomeView;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.ImageViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主界面
 */

public class HomeFragment extends BaseFragment implements HomeView {

    @BindView(R.id.mBanner)
    ConvenientBanner mBanner;
    @BindView(R.id.mMoreTv)
    TextView mMoreTv;
    @BindView(R.id.mCanKnowRv)
    RecyclerView mCanKnowRv;
    Unbinder unbinder;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private BaseQuickAdapter homeKnowAdapter;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 10;//一次加载个数
    private List<HomeCanKnow.QuestionDataBean> questionDataBeanList;
    private List<BannerData> bannerDataList;//轮播图

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ((HomePresenter) mPresenter).getBanner(1);
                    break;
                case 2:
                    ((HomePresenter) mPresenter).canKnow(currentPage, limit,2);
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        bannerDataList = new ArrayList<>();
        questionDataBeanList = new ArrayList<>();
        mPresenter = new HomePresenter(getActivity(),mHander);
        mPresenter.mBaseView = this;
        ((HomePresenter) mPresenter).getBanner(1);
        ((HomePresenter) mPresenter).canKnow(currentPage, limit,2);

        mCanKnowRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        homeKnowAdapter = new BaseQuickAdapter<HomeCanKnow.QuestionDataBean, BaseViewHolder>(R.layout.item_know, questionDataBeanList) {

            @Override
            protected void convert(BaseViewHolder helper, HomeCanKnow.QuestionDataBean item) {
                helper.setText(R.id.mTitleTv, item.getAskData().getQuestion());
                helper.setText(R.id.mTimeTv, item.getAskData().getOperateTime().split("T")[0]);
                helper.setText(R.id.mContentTv, item.getAskData().getAnswer());
                helper.setText(R.id.mTeacherTv, item.getAskData().getAnswerUser().getName());
                helper.setText(R.id.mSchoolTv, item.getAskData().getAnswerUser().getSchoolName() + "#"
                        + item.getAskData().getAnswerUser().getProvince());
                helper.setText(R.id.mReadTv, item.getViewNumber() + "");
                Glide.with(getActivity()).load(item.getAskData().getAskUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mAvatarCiv));
            }
        };

        homeKnowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HashMap<String,String> map = new HashMap<>();
                map.put("Guid",questionDataBeanList.get(position).getAskData().getGuid());
                ToolUtils.startActivity(getActivity(),AnswerActivity.class,map);
            }
        });
        mCanKnowRv.setAdapter(homeKnowAdapter);
        initListener();
    }

    private void initListener() {
        homeKnowAdapter.setUpFetchEnable(false);
        homeKnowAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage * 10 < totalNum) {
                            currentPage++;
                            ((com.ruiyi.askandanswer.main.presenter.HomePresenter) mPresenter).canKnow(currentPage, limit,2);
                            homeKnowAdapter.loadMoreComplete();
                        } else {
                            homeKnowAdapter.loadMoreEnd();
                        }
                    }
                }, 500);
            }
        }, mCanKnowRv);

        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCanKnowRv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentPage = 1;
                        ((com.ruiyi.askandanswer.main.presenter.HomePresenter) mPresenter).canKnow(currentPage,limit,2);
                        swiperefreshlayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        mMoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolUtils.startActivity(getActivity(),AskMoreActivity.class,null);
            }
        });

        mBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == 2){
                    ToolUtils.startActivity(getActivity(),AskMoreActivity.class,null);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCanKnowResult(HomeCanKnow homeCanKnow) {
        this.totalNum = homeCanKnow.getTotalNumber();
        this.currentPage = homeCanKnow.getCurrentPage();
        if (currentPage == 1) {
            questionDataBeanList.clear();
        }
        questionDataBeanList.addAll(homeCanKnow.getQuestionData());
        Log.d("cj","size=====>>>" + questionDataBeanList.size());
        homeKnowAdapter.setNewData(questionDataBeanList);
        homeKnowAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBannerResult(List<BannerData> bannerDataList) {
        List<String> imageList = new ArrayList<>();
        this.bannerDataList = bannerDataList;
        for(BannerData info : bannerDataList){
            imageList.add(info.getImageUrl());
        }

        int[] array= new int[]{R.drawable.ic_banner_dian_blur, R.drawable.ic_banner_dian_focus};
         mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageViewHolder();
            }
        },imageList)//设置两个点作为指示器
                 .setPageIndicator(array)//设置指示器的方向水平  居中
         .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        mBanner.startTurning(5000);     //设置开始轮播以及轮播时间  建议在onResume方法中设置
    }
}