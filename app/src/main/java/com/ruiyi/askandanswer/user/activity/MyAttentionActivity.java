package com.ruiyi.askandanswer.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.AskActivity;
import com.ruiyi.askandanswer.ask.activity.TeacherInfoActivity;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.AttentionData;
import com.ruiyi.askandanswer.user.presenter.UserPresenter;
import com.ruiyi.askandanswer.user.view.AttentionView;
import com.ruiyi.askandanswer.utils.GlideUtils;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAttentionActivity extends BaseActivity implements AttentionView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mCanKnowRv)
    RecyclerView mCanKnowRv;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private BaseQuickAdapter baseQuickAdapter;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 10;//一次加载个数
    private List<AttentionData.AttentionsBean> attentionsBeanList;
    private int mPosition = 0;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 5:
                    ((UserPresenter) mPresenter).getMyAttention(limit, currentPage,5);
                    break;
                case 6:
                    ((UserPresenter) mPresenter).cancleAttention(attentionsBeanList.get(mPosition).getTeacherGuid(),6);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        attentionsBeanList = new ArrayList<>();
        mPresenter = new UserPresenter<>(MyAttentionActivity.this,mHander);
        mPresenter.mBaseView = this;
        ((UserPresenter) mPresenter).getMyAttention(limit, currentPage,5);

        mCanKnowRv.setLayoutManager(new LinearLayoutManager(MyAttentionActivity.this, LinearLayoutManager.VERTICAL, false));
        baseQuickAdapter = new BaseQuickAdapter<AttentionData.AttentionsBean, BaseViewHolder>(R.layout.item_attention, attentionsBeanList) {

            @Override
            protected void convert(BaseViewHolder helper, AttentionData.AttentionsBean item) {
                helper.setText(R.id.mTeacherTv, item.getTeacherName());
                helper.setText(R.id.mTitleTv, item.getTeacherTitle());
                helper.setText(R.id.mHelpTv,"帮我：" + item.getHelpNumber() + "次");
                helper.setText(R.id.mSchoolTv, item.getSchoolName() + "#"
                        + item.getTeacherProvince());
                GlideUtils.loadImage(MyAttentionActivity.this,item.getAvatarUrl(),(ImageView) helper.getView(R.id.mAvatarCiv));
                helper.addOnClickListener(R.id.mAvatarCiv);
                helper.addOnClickListener(R.id.mCancleTv);
                helper.addOnClickListener(R.id.mAskTv);
            }
        };
        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //判断id
                if (view.getId() == R.id.mAvatarCiv) {
                    HashMap<String,String> map = new HashMap<>();
                    map.put("Guid",attentionsBeanList.get(position).getTeacherGuid());
                    ToolUtils.startActivity(MyAttentionActivity.this,TeacherInfoActivity.class,map);
                } else if (view.getId() == R.id.mCancleTv) {
                    mPosition = position;
                    ((UserPresenter) mPresenter).cancleAttention(attentionsBeanList.get(position).getTeacherGuid(),6);
                } else if (view.getId() == R.id.mAskTv) {
                    Intent intent = new Intent(MyAttentionActivity.this,AskActivity.class);
                    intent.putExtra("TYPE",0);
                    intent.putExtra("TeaGuid",attentionsBeanList.get(position).getTeacherGuid());
                    intent.putExtra("TeaAvatar",attentionsBeanList.get(position).getAvatarUrl());
                    intent.putExtra("TeaName",attentionsBeanList.get(position).getTeacherName());
                    intent.putExtra("QGuid",attentionsBeanList.get(position).getTeacherGuid());
                    startActivity(intent);
                }
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
                            ((UserPresenter) mPresenter).getMyAttention(limit, currentPage,5);
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
                        ((UserPresenter) mPresenter).getMyAttention(limit, currentPage,5);
                        swiperefreshlayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }


    @Override
    public void onAttentionResult(AttentionData attentionData) {
        this.totalNum = attentionData.getTotalNumber();
        this.currentPage = attentionData.getCurrentPage();
        if (currentPage == 1) {
            attentionsBeanList.clear();
        }
        attentionsBeanList.addAll(attentionData.getAttentions());
        baseQuickAdapter.setNewData(attentionsBeanList);
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancleAttentionResult() {
        ToastUtil.show("取消成功");
        currentPage = 1;
        ((UserPresenter) mPresenter).getMyAttention(limit, currentPage,5);
    }
}
