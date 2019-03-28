package com.ruiyi.askandanswer.ask.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.AskActivity;
import com.ruiyi.askandanswer.ask.activity.QuestionsActivity;
import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.presenter.AskMorePresenter;
import com.ruiyi.askandanswer.ask.view.AskMoreView;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AskMoreErrorFragment extends BaseFragment implements AskMoreView {

    @BindView(R.id.mCanKnowRv)
    RecyclerView mCanKnowRv;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    Unbinder unbinder;
    private String mSubjectId;
    private final int MTYPE = 2;
    private View mView;
    private BaseQuickAdapter baseQuickAdapter;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 10;//一次加载个数
    private View mEmptyView;
    private List<MoreErrorData.QuestionDataBeanX> questionDataBeanList;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 2:
                    ((AskMorePresenter) mPresenter).getMoreErrorRecommend(mSubjectId, MTYPE, limit, currentPage,2);
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
        ((AskMorePresenter) mPresenter).getMoreErrorRecommend(mSubjectId, MTYPE, limit, currentPage,2);
        mCanKnowRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        baseQuickAdapter = new BaseQuickAdapter<MoreErrorData.QuestionDataBeanX, BaseViewHolder>(R.layout.item_more_error, questionDataBeanList) {

            @Override
            protected void convert(BaseViewHolder helper, MoreErrorData.QuestionDataBeanX item) {
                helper.setText(R.id.mTitleTv, item.getSource() + "#" + item.getOperateTime().split("T")[0]);
                String html = BaseConstant.BASE_CSS + item.getQuestionData().getContent() + "</body></html>";
                WebView mWebView =  helper.getView(R.id.mWebView);
                mWebView.loadData(html, "text/html", "uft-8");
                helper.setText(R.id.mWrongRateTv,"错误率：" + item.getWrongRate() + "");
                helper.setOnClickListener(R.id.mAskTv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),AskActivity.class);
                        intent.putExtra("TYPE",0);
                        intent.putExtra("TeaGuid","");
                        intent.putExtra("QGuid",item.getQuestionGuid());
                        intent.putExtra("Html",html);
                        startActivity(intent);
                    }
                });
                helper.setOnClickListener(R.id.mWebView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> questionList = new ArrayList<>();
                        questionList.add(item.getQuestionGuid());
                        Intent intent = new Intent(getActivity(),QuestionsActivity.class);
                        intent.putExtra("IsAnalysisShow",true);
                        intent.putStringArrayListExtra("GuidList", (ArrayList<String>) questionList);
                        startActivity(intent);
                    }
                });
            }
        };

        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<String> questionList = new ArrayList<>();
                questionList.add(questionDataBeanList.get(position).getQuestionGuid());
                Intent intent = new Intent(getActivity(),QuestionsActivity.class);
                intent.putExtra("IsAnalysisShow",true);
                intent.putStringArrayListExtra("GuidList", (ArrayList<String>) questionList);
                startActivity(intent);
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
                            ((AskMorePresenter) mPresenter).getMoreErrorRecommend(mSubjectId, MTYPE, limit, currentPage,2);
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
                        ((AskMorePresenter) mPresenter).getMoreErrorRecommend(mSubjectId, MTYPE, limit, currentPage,2);
                        swiperefreshlayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mView.getParent()).removeView(mView);
        unbinder.unbind();
    }

    @Override
    public void onSubjectsResult(List<SubjectData> subjectDataList) {

    }

    @Override
    public void onMoreKnowResult(MoreKnowData moreKnowData) {

    }

    @Override
    public void onMoreErrorResult(MoreErrorData moreErrorData) {
        this.totalNum = moreErrorData.getTotalNumber();
        this.currentPage = moreErrorData.getCurrentPage();
        if (currentPage == 1) {
            questionDataBeanList.clear();
        }
        questionDataBeanList.addAll(moreErrorData.getQuestionData());
        if(questionDataBeanList.size() == 0)
            baseQuickAdapter.setEmptyView(mEmptyView);
        baseQuickAdapter.setNewData(questionDataBeanList);
        baseQuickAdapter.notifyDataSetChanged();
    }
}
