package com.ruiyi.askandanswer.user.fragment;

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
import android.webkit.WebView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.user.model.CollectData;
import com.ruiyi.askandanswer.user.model.MyCollectSection;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.CollectView;
import com.ruiyi.askandanswer.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCollectFragment extends BaseFragment implements CollectView{

    @BindView(R.id.mFootRv)
    RecyclerView mFootRv;
    Unbinder unbinder;
    private String mSubjectId;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 1000;//一次加载个数
    private List<CollectData.QuestionCollectBean> questionDataBeanList;
    private View mView;
    private List<MyCollectSection> mySectionList;
    private BaseSectionQuickAdapter baseSectionQuickAdapter;
    private MyCollectSection mySection;

    public void setArguements(String subjectId) {
        this.mSubjectId = subjectId;
    }

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 8:
                    ((UserPresenter) mPresenter).getMyCollection(mSubjectId, limit, currentPage,8);
                    break;
                case 12:
                    ((UserPresenter) mPresenter).getCollectQuestion(mySection.getQuestionCollectGroupBean().getQuestionGuid(),
                            mySection.getQuestionCollectGroupBean().getQuestionData().getGuid(), 0,12);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_collect, container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        mySectionList = new ArrayList<>();
        questionDataBeanList = new ArrayList<>();
        mPresenter = new UserPresenter<>(getActivity(),mHander);
        mPresenter.mBaseView = this;
        ((UserPresenter) mPresenter).getMyCollection(mSubjectId, limit, currentPage,8);
        mFootRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        baseSectionQuickAdapter = new BaseSectionQuickAdapter<MyCollectSection, BaseViewHolder>(R.layout.item_collect, R.layout.item_section_header, mySectionList) {

            @Override
            protected void convertHead(BaseViewHolder helper, MyCollectSection item) {
                helper.setText(R.id.mTitleTv, item.header);
            }

            @Override
            protected void convert(BaseViewHolder helper, MyCollectSection item) {
                CollectData.QuestionCollectBean.QuestionCollectGroupBean questionCollectGroupBean = item.t;
                WebView mWebView = helper.getView(R.id.mWebView);
                String html = BaseConstant.BASE_CSS + questionCollectGroupBean.getQuestionData().getContent() + "</body></html>";
                mWebView.loadData(html, "text/html", "uft-8");
                helper.setText(R.id.mAnswerTv, "我的答案：" + questionCollectGroupBean.getAnswer());
                WebView mAnalysisWv = helper.getView(R.id.mAnalysisWv);
                String html1 = BaseConstant.BASE_CSS + questionCollectGroupBean.getQuestionData().getAnalysis() + "</body></html>";
                mAnalysisWv.loadData(html1, "text/html", "uft-8");
                helper.addOnClickListener(R.id.mCollectTv);
                helper.setOnClickListener(R.id.mAnalysisTv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (helper.getView(R.id.mAnalysisLl).getVisibility() == View.VISIBLE)
                            helper.getView(R.id.mAnalysisLl).setVisibility(View.GONE);
                        else
                            helper.getView(R.id.mAnalysisLl).setVisibility(View.VISIBLE);
                    }
                });
            }
        };

        baseSectionQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mySection= mySectionList.get(position);
                ((UserPresenter) mPresenter).getCollectQuestion(mySection.getQuestionCollectGroupBean().getQuestionGuid(),
                        mySection.getQuestionCollectGroupBean().getQuestionData().getGuid(), 0,12);
            }
        });

        mFootRv.setAdapter(baseSectionQuickAdapter);
//        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ((ViewGroup) mView.getParent()).removeView(mView);
    }

    @Override
    public void onCollectResult(CollectData info) {
        questionDataBeanList.clear();
        mySectionList.clear();
        this.totalNum = info.getTotalNumber();
        this.currentPage = info.getCurrentPage();
        questionDataBeanList.addAll(info.getQuestionCollect());
        Log.d("okhttp", "questionDataBeanList===>>>" + questionDataBeanList.size());
        for (int i = 0; i < questionDataBeanList.size(); i++) {
            mySectionList.add(new MyCollectSection(true, questionDataBeanList.get(i).getTitle().split("T")[0]));
            for (int j = 0; j < questionDataBeanList.get(i).getQuestionCollectGroup().size(); j++) {
                mySectionList.add(new MyCollectSection(questionDataBeanList.get(i).getQuestionCollectGroup().get(j)));
            }
        }
        baseSectionQuickAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancleCollectResult() {
        ToastUtil.show("取消收藏成功！");
        ((UserPresenter) mPresenter).getMyCollection(mSubjectId, limit, currentPage,8);
    }
}
