package com.ruiyi.askandanswer.user.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionMultiEntity;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.AnswerActivity;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.user.adapter.SectionMultipleItemAdapter;
import com.ruiyi.askandanswer.user.model.CionData;
import com.ruiyi.askandanswer.user.model.FootData;
import com.ruiyi.askandanswer.user.model.MyCoinSection;
import com.ruiyi.askandanswer.user.model.MyCoinSection;
import com.ruiyi.askandanswer.user.model.RechargeData;
import com.ruiyi.askandanswer.user.model.SectionMultipleItem;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.FootView;
import com.ruiyi.askandanswer.user.view.RechargeView;
import com.ruiyi.askandanswer.utils.ToolUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCionFragment extends BaseFragment implements RechargeView {

    @BindView(R.id.mFootRv)
    RecyclerView mFootRv;
    Unbinder unbinder;
    private int mType;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 1000;//一次加载个数
    private List<CionData.CostsBean> costsBeanList;
    private View mView;
    private List<SectionMultipleItem> mySectionList;
    private SectionMultipleItemAdapter sectionMultipleItemAdapter;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10:
                    ((UserPresenter) mPresenter).getMyCoin(mType, limit, currentPage,10);
                    break;
            }
        }
    };

    public void setArguements(int type) {
        this.mType = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_foot, container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        costsBeanList = new ArrayList<>();
        mySectionList = new ArrayList<>();
        mPresenter = new UserPresenter(getActivity(),mHander);
        mPresenter.mBaseView = this;
        ((UserPresenter) mPresenter).getMyCoin(mType, limit, currentPage,10);
        mFootRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        sectionMultipleItemAdapter = new SectionMultipleItemAdapter(getActivity(),R.layout.item_section_header,mySectionList);
        mFootRv.setAdapter(sectionMultipleItemAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ((ViewGroup) mView.getParent()).removeView(mView);
    }

    @Override
    public void onCionResult(CionData info) {
        costsBeanList = info.getCosts();
        for(int i = 0; i < costsBeanList.size();i++){
            mySectionList.add(new SectionMultipleItem(true, costsBeanList.get(i).getTitle().split("T")[0]));
            for(int j = 0;j < costsBeanList.get(i).getQuestionDataGroup().size();j++){
                if(costsBeanList.get(i).getQuestionDataGroup().get(j).getAskStatus() == 1)
                    mySectionList.add(new SectionMultipleItem(1,costsBeanList.get(i).getQuestionDataGroup().get(j)));
                else
                    mySectionList.add(new SectionMultipleItem(0,costsBeanList.get(i).getQuestionDataGroup().get(j)));
            }
        }
        sectionMultipleItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRechargeResult(RechargeData info) {

    }
}
