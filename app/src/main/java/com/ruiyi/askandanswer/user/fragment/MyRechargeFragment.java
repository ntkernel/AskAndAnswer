package com.ruiyi.askandanswer.user.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.user.model.CionData;
import com.ruiyi.askandanswer.user.model.MyRechargeSection;
import com.ruiyi.askandanswer.user.model.RechargeData;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.RechargeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyRechargeFragment extends BaseFragment implements RechargeView {

    @BindView(R.id.mFootRv)
    RecyclerView mFootRv;
    Unbinder unbinder;
    private int mType;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 1000;//一次加载个数
    private List<RechargeData.RechargesBean> rechargesBeanList;
    private View mView;
    private List<MyRechargeSection> mySectionList;
    private BaseSectionQuickAdapter baseSectionQuickAdapter;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 11:
                    ((UserPresenter) mPresenter).getRecharge(mType, limit, currentPage,11);
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
        rechargesBeanList = new ArrayList<>();
        mySectionList = new ArrayList<>();
        mPresenter = new UserPresenter(getActivity(),mHander);
        mPresenter.mBaseView = this;
        ((UserPresenter) mPresenter).getRecharge(mType, limit, currentPage,11);
        mFootRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        baseSectionQuickAdapter = new BaseSectionQuickAdapter<MyRechargeSection,BaseViewHolder>(R.layout.item_recharge, R.layout.item_section_header, mySectionList) {

            @Override
            protected void convertHead(BaseViewHolder helper, MyRechargeSection item) {
                helper.setText(R.id.mTitleTv, item.header);
            }

            @Override
            protected void convert(BaseViewHolder helper, MyRechargeSection item) {
                RechargeData.RechargesBean.RechargeGroupBean rechargeGroupBean = item.t;
                helper.setText(R.id.mPayNameTv, rechargeGroupBean.getSource());
                helper.setText(R.id.mTimeTv, rechargeGroupBean.getOperateTime());
//                helper.setText(R.id.mTotalTv, rechargeGroupBean.getCoinNumber() + "");
                helper.setText(R.id.mNumTv, "+" + rechargeGroupBean.getActionNumber());
            }
        };

        mFootRv.setAdapter(baseSectionQuickAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ((ViewGroup) mView.getParent()).removeView(mView);
    }

    @Override
    public void onCionResult(CionData info) {

    }

    @Override
    public void onRechargeResult(RechargeData info) {
        rechargesBeanList = info.getRecharges();
        for(int i = 0; i < rechargesBeanList.size();i++){
            mySectionList.add(new MyRechargeSection(true, rechargesBeanList.get(i).getTitle().split("T")[0]));
            for(int j = 0;j < rechargesBeanList.get(i).getRechargeGroup().size();j++){
                mySectionList.add(new MyRechargeSection(rechargesBeanList.get(i).getRechargeGroup().get(j)));
            }
        }
        baseSectionQuickAdapter.notifyDataSetChanged();
    }
}
