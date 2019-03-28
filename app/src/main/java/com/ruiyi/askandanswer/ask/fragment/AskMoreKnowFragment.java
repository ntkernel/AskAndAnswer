package com.ruiyi.askandanswer.ask.fragment;

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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.AnswerActivity;
import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.presenter.AskMorePresenter;
import com.ruiyi.askandanswer.ask.view.AskMoreView;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.utils.ToolUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AskMoreKnowFragment extends BaseFragment implements AskMoreView {

    @BindView(R.id.mCanKnowRv)
    RecyclerView mCanKnowRv;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    Unbinder unbinder;
    private String mSubjectId;
    private BaseQuickAdapter baseQuickAdapter;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 10;//一次加载个数
    private List<MoreKnowData.QuestionDataBean> questionDataBeanList;
    private final int MTYPE = 1;
    private View mView;
    private View mEmptyView;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 3:
                    ((AskMorePresenter) mPresenter).getMoreRecommend(mSubjectId, MTYPE, limit, currentPage,3);
                    break;
            }
        }
    };

    public void setArguements(String subjectId) {
        this.mSubjectId = subjectId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_more_recommend, container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        mEmptyView = getLayoutInflater().inflate(R.layout.empty_view,(ViewGroup) mCanKnowRv.getParent(), false);
        questionDataBeanList = new ArrayList<>();
        mPresenter = new AskMorePresenter(getActivity(),mHander);
        mPresenter.mBaseView = this;
        ((AskMorePresenter) mPresenter).getMoreRecommend(mSubjectId, MTYPE, limit, currentPage,3);
        mCanKnowRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        baseQuickAdapter = new BaseQuickAdapter<MoreKnowData.QuestionDataBean, BaseViewHolder>(R.layout.item_more_know, questionDataBeanList) {

            @Override
            protected void convert(BaseViewHolder helper, MoreKnowData.QuestionDataBean item) {
                helper.setText(R.id.mTitleTv, item.getAskData().getQuestion());
                helper.setText(R.id.mContentTv, item.getAskData().getAnswer());
                helper.setText(R.id.mTeaTv, item.getAskData().getAnswerUser().getName());
                helper.setText(R.id.mTeaLeavlTv, item.getAskData().getAnswerUser().getTitle());
                helper.setText(R.id.mTeaSchoolTv, item.getAskData().getAnswerUser().getSchoolName() + "#"
                        + item.getAskData().getAnswerUser().getProvince());
                helper.setText(R.id.mStuTv, item.getAskData().getAskUser().getName());
                helper.setText(R.id.mStuSchoolTv, item.getAskData().getAskUser().getSchoolName() + "#"
                        + item.getAskData().getAskUser().getProvince());
                helper.setText(R.id.mReadTv, item.getViewNumber() + "");
                Glide.with(getActivity()).load(item.getAskData().getAnswerUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mTeaCiv));
                Glide.with(getActivity()).load(item.getAskData().getAskUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mStuCiv));
            }
        };
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HashMap<String,String> map = new HashMap<>();
                map.put("Guid",questionDataBeanList.get(position).getAskData().getGuid());
                ToolUtils.startActivity(getActivity(),AnswerActivity.class,map);
            }
        });
        mCanKnowRv.setAdapter(baseQuickAdapter);
        initListener();
    }

    private void initListener() {
        baseQuickAdapter.setUpFetchEnable(false);
        baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage * 10 < totalNum) {
                            currentPage++;
                            ((AskMorePresenter) mPresenter).getMoreRecommend(mSubjectId, MTYPE, limit, currentPage,3);
                            baseQuickAdapter.loadMoreComplete();
                        } else {
                            baseQuickAdapter.loadMoreEnd();
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
                        ((AskMorePresenter) mPresenter).getMoreRecommend(mSubjectId, MTYPE, limit, currentPage,3);
                        swiperefreshlayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onSubjectsResult(List<SubjectData> subjectDataList) {

    }

    @Override
    public void onMoreKnowResult(MoreKnowData moreKnowData) {
        this.totalNum = moreKnowData.getTotalNumber();
        this.currentPage = moreKnowData.getCurrentPage();
        if (currentPage == 1) {
            questionDataBeanList.clear();
        }
        questionDataBeanList.addAll(moreKnowData.getQuestionData());
        if(questionDataBeanList.size() == 0)
            baseQuickAdapter.setEmptyView(mEmptyView);
        baseQuickAdapter.setNewData(questionDataBeanList);
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMoreErrorResult(MoreErrorData moreErrorData) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ((ViewGroup) mView.getParent()).removeView(mView);
    }
}
