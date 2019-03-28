package com.ruiyi.askandanswer.user.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.AnswerActivity;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.user.model.FootData;
import com.ruiyi.askandanswer.user.model.MyFootSection;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.FootView;
import com.ruiyi.askandanswer.utils.ToolUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFootFragment extends BaseFragment implements FootView {

    @BindView(R.id.mFootRv)
    RecyclerView mFootRv;
    Unbinder unbinder;
    private String mSubjectId;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 1000;//一次加载个数
    private List<FootData.QuestionDataBean> questionDataBeanList;
    private View mView;
    private List<MyFootSection> mySectionList;
    private BaseSectionQuickAdapter baseSectionQuickAdapter;

    public void setArguements(String subjectId) {
        this.mSubjectId = subjectId;
    }

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 7:
                    ((UserPresenter) mPresenter).getMyFoot(mSubjectId, limit, currentPage,7);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_foot, container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        mySectionList = new ArrayList<>();
        questionDataBeanList = new ArrayList<>();
        mPresenter = new UserPresenter<>(getActivity(),mHander);
        mPresenter.mBaseView = this;
        ((UserPresenter) mPresenter).getMyFoot(mSubjectId, limit, currentPage,7);
        mFootRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        baseSectionQuickAdapter = new BaseSectionQuickAdapter<MyFootSection,BaseViewHolder>(R.layout.item_more_know, R.layout.item_section_header, mySectionList) {

            @Override
            protected void convertHead(BaseViewHolder helper, MyFootSection item) {
                helper.setText(R.id.mTitleTv, item.header);
            }

            @Override
            protected void convert(BaseViewHolder helper, MyFootSection item) {
                FootData.QuestionDataBean.QuestionDataGroupBean questionDataGroupBean = (FootData.QuestionDataBean.QuestionDataGroupBean) item.t;
                helper.setText(R.id.mTitleTv, questionDataGroupBean.getAskData().getQuestion());
                helper.setText(R.id.mContentTv, questionDataGroupBean.getAskData().getAnswer());
                helper.setText(R.id.mTeaTv, questionDataGroupBean.getAskData().getAnswerUser().getName());
                helper.setText(R.id.mTeaLeavlTv, questionDataGroupBean.getAskData().getAnswerUser().getTitle());
                helper.setText(R.id.mTeaSchoolTv, questionDataGroupBean.getAskData().getAnswerUser().getSchoolName() + "#"
                        + questionDataGroupBean.getAskData().getAnswerUser().getProvince());
                helper.setText(R.id.mStuTv, questionDataGroupBean.getAskData().getAskUser().getName());
                helper.setText(R.id.mStuSchoolTv, questionDataGroupBean.getAskData().getAskUser().getSchoolName() + "#"
                        + questionDataGroupBean.getAskData().getAskUser().getProvince());
                helper.setText(R.id.mReadTv, questionDataGroupBean.getViewNumber() + "");
                Glide.with(getActivity()).load(questionDataGroupBean.getAskData().getAnswerUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mTeaCiv));
                Glide.with(getActivity()).load(questionDataGroupBean.getAskData().getAskUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mStuCiv));
            }
        };

        baseSectionQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFootSection mySection = mySectionList.get(position);
                if (mySection.isHeader)
                    Log.d("cj","header=====>>>>" + mySection.header);
                else{
                    HashMap<String,String> map = new HashMap<>();
                    map.put("Guid",mySection.t.getAskQuestionGuid());
                    ToolUtils.startActivity(getActivity(),AnswerActivity.class,map);
                }
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
    public void onFootResult(FootData info) {
        this.totalNum = info.getTotalNumber();
        this.currentPage = info.getCurrentPage();
        questionDataBeanList = info.getQuestionData();
        for(int i = 0; i < questionDataBeanList.size();i++){
            mySectionList.add(new MyFootSection(true, questionDataBeanList.get(i).getTitle().split("T")[0]));
            for(int j = 0;j < questionDataBeanList.get(i).getQuestionDataGroup().size();j++){
                mySectionList.add(new MyFootSection(questionDataBeanList.get(i).getQuestionDataGroup().get(j)));
            }
        }
        baseSectionQuickAdapter.notifyDataSetChanged();
    }
}
