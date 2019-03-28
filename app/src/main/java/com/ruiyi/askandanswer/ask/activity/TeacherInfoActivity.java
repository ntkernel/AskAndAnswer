package com.ruiyi.askandanswer.ask.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.model.response.TeacherAnswerData;
import com.ruiyi.askandanswer.ask.presenter.AnsewerPresenter;
import com.ruiyi.askandanswer.ask.view.TeacherInfoView;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.utils.GlideUtils;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherInfoActivity extends BaseActivity implements TeacherInfoView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mTeaCiv)
    CircleImageView mTeaCiv;
    @BindView(R.id.mTeaTv)
    TextView mTeaTv;
    @BindView(R.id.mTeaLeavlTv)
    TextView mTeaLeavlTv;
    @BindView(R.id.mTeaSchoolTv)
    TextView mTeaSchoolTv;
    @BindView(R.id.mAnswerTv)
    TextView mAnswerTv;
    @BindView(R.id.mLoveTv)
    TextView mLoveTv;
    @BindView(R.id.mAttentionIv)
    ImageView mAttentionIv;
    @BindView(R.id.mCanKnowRv)
    RecyclerView mCanKnowRv;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private String mGuid;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 10;//一次加载个数
    private BaseQuickAdapter baseQuickAdapter;
    private List<TeacherAnswerData.QuestionDataBean> questionDataBeanList;//回答列表
    private int mAttentionFlag = 0;
    private TeacherAnswerData.TeacherInforBean teacherInforBean;//教师信息

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 5:
                    ((AnsewerPresenter) mPresenter).teacherAnswer(mGuid, currentPage, limit,5);
                    break;
                case 6:
                    ((AnsewerPresenter) mPresenter).attentionTeacher(teacherInforBean,6);
                    break;
                case 7:
                    ((AnsewerPresenter) mPresenter).cancleAttention(teacherInforBean.getGuid(),7);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        questionDataBeanList = new ArrayList<>();
        mGuid = getIntent().getStringExtra("Guid");
        mPresenter = new AnsewerPresenter(TeacherInfoActivity.this,mHander);
        mPresenter.mBaseView = this;
        ((AnsewerPresenter) mPresenter).teacherAnswer(mGuid, currentPage, limit,5);
        mCanKnowRv.setLayoutManager(new LinearLayoutManager(TeacherInfoActivity.this, LinearLayoutManager.VERTICAL, false));
        baseQuickAdapter = new BaseQuickAdapter<TeacherAnswerData.QuestionDataBean, BaseViewHolder>(R.layout.item_more_know, questionDataBeanList) {

            @Override
            protected void convert(BaseViewHolder helper, TeacherAnswerData.QuestionDataBean item) {
                Log.d("cj", "item====>>>" + item.getViewNumber());
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
                Glide.with(TeacherInfoActivity.this).load(item.getAskData().getAnswerUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mTeaCiv));
                Glide.with(TeacherInfoActivity.this).load(item.getAskData().getAskUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mStuCiv));
            }
        };
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HashMap<String, String> map = new HashMap<>();
                map.put("Guid", questionDataBeanList.get(position).getAskData().getGuid());
                ToolUtils.startActivity(TeacherInfoActivity.this, AnswerActivity.class, map);
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
                            ((AnsewerPresenter) mPresenter).teacherAnswer(mGuid, currentPage, limit,5);
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
                        ((AnsewerPresenter) mPresenter).teacherAnswer(mGuid, currentPage, limit,5);
                        swiperefreshlayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onTeacherInfoReault(TeacherAnswerData teacherAnswerData) {
        teacherInforBean = teacherAnswerData.getTeacherInforBean();
        mAttentionFlag = teacherAnswerData.getAttentionFlag();
        if (mAttentionFlag == 0)
            GlideUtils.loadImage(TeacherInfoActivity.this, R.drawable.ic_unattention, mAttentionIv);
        else
            GlideUtils.loadImage(TeacherInfoActivity.this, R.drawable.ic_attention, mAttentionIv);
        GlideUtils.loadImage(TeacherInfoActivity.this,teacherInforBean.getAvatarUrl(),mTeaCiv);
        mTeaTv.setText(teacherInforBean.getName());
        if (teacherInforBean.getTitle() != null)
            mTeaLeavlTv.setText(teacherInforBean.getTitle());
        else
            mTeaLeavlTv.setVisibility(View.GONE);
        mTeaSchoolTv.setText(teacherInforBean.getSchoolName() + "#" + teacherInforBean.getProvince());
        mAnswerTv.setText("回答" + teacherInforBean.getTotalNumber());
        mLoveTv.setText("点赞" + teacherInforBean.getThumbupNumber());
        this.totalNum = teacherInforBean.getTotalNumber();
        if (currentPage == 1) {
            questionDataBeanList.clear();
        }
        questionDataBeanList.addAll(teacherAnswerData.getQuestionData());
        baseQuickAdapter.setNewData(questionDataBeanList);
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttentionResult() {
        mAttentionFlag = 1;
        GlideUtils.loadImage(TeacherInfoActivity.this, R.drawable.ic_attention, mAttentionIv);
        ToastUtil.show("关注成功");
    }

    @Override
    public void onCancleAttentionResult() {
        mAttentionFlag = 0;
        GlideUtils.loadImage(TeacherInfoActivity.this, R.drawable.ic_unattention, mAttentionIv);
        ToastUtil.show("取消关注成功");
    }

    @OnClick(R.id.mAttentionIv)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.mAttentionIv:
                if(teacherInforBean != null){
                    if(mAttentionFlag == 0)
                        ((AnsewerPresenter) mPresenter).attentionTeacher(teacherInforBean,6);
                    else
                        ((AnsewerPresenter) mPresenter).cancleAttention(teacherInforBean.getGuid(),7);
                }else{
                    ToastUtil.show("请检查网络");
                }
                break;
        }
    }
}
