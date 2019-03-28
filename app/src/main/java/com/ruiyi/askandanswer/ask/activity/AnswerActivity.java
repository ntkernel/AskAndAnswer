package com.ruiyi.askandanswer.ask.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.model.response.AnswerData;
import com.ruiyi.askandanswer.ask.presenter.AnsewerPresenter;
import com.ruiyi.askandanswer.ask.view.AnswerView;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.GlideUtils;
import com.ruiyi.askandanswer.utils.ScreenUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
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

public class AnswerActivity extends BaseActivity implements AnswerView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mStuCiv)
    CircleImageView mStuCiv;
    @BindView(R.id.mStuTv)
    TextView mStuTv;
    @BindView(R.id.mTimeTv)
    TextView mTimeTv;
    @BindView(R.id.mQuestionWv)
    WebView mQuestionWv;
    @BindView(R.id.mTeaCiv)
    CircleImageView mTeaCiv;
    @BindView(R.id.mTeaTv)
    TextView mTeaTv;
    @BindView(R.id.mTeaLeavlTv)
    TextView mTeaLeavlTv;
    @BindView(R.id.mTeaSchoolTv)
    TextView mTeaSchoolTv;
    @BindView(R.id.mLoveNumTv)
    TextView mLoveNumTv;
    @BindView(R.id.mAnswerWv)
    WebView mAnswerWv;
    @BindView(R.id.mGotoTv)
    TextView mGotoTv;
    @BindView(R.id.mResourceLl)
    LinearLayout mResourceLl;//资源layout
    @BindView(R.id.mSubjectTv)
    TextView mSubjectTv;
    @BindView(R.id.mAskRv)
    RecyclerView mAskRv;
    @BindView(R.id.mDownIv)
    ImageView mDownIv;
    @BindView(R.id.mAskLl)
    LinearLayout mAskLl;
    @BindView(R.id.mRecommendRv)
    RecyclerView mRecommendRv;
    @BindView(R.id.mAgainEt)
    EditText mAgainEt;
    @BindView(R.id.mConfirmTv)
    TextView mConfirmTv;
    @BindView(R.id.mAgainRl)
    RelativeLayout mAgainRl;
    @BindView(R.id.mBottomLl)
    LinearLayout mBottomLl;
    @BindView(R.id.mLikeIv)
    ImageView mLikeIv;
    @BindView(R.id.mLikeLl)
    LinearLayout mLikeLl;
    @BindView(R.id.mAgainLl)
    LinearLayout mAgainLl;
    @BindView(R.id.mCloseLl)
    LinearLayout mCloseLl;
    @BindView(R.id.mBottomFl)
    FrameLayout mBottomFl;
    @BindView(R.id.mSubjectRl)
    RelativeLayout mSubjectRl;
    private String mGuid = "";
    private BaseQuickAdapter mAskAdapter;
    private BaseQuickAdapter mRecommendAdapter;
    private List<AnswerData.RecommendBean> mRecommendList;//相似问题
    private List<AnswerData.ExplanationBean> mExplanationList;//追问
    private AlertDialog mAlertDialog;
    private AnswerData mAnswerData;
    private LinearLayout.LayoutParams lp;
    private boolean isOpen = false;//追问是否打开
    private int mLike = 0;
    private int thumbUp = 0;
    private String mContentStr;
    private String mQuestionGuid;
    private String mAnswerGuid;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ((AnsewerPresenter) mPresenter).getAnswerDetail(mGuid,1);
                    break;
                case 2:
                    ((AnsewerPresenter) mPresenter).getThumbUp(mGuid, thumbUp,2);
                break;
                case 3:
                    ((AnsewerPresenter) mPresenter).endAskQuestion(mGuid,3);
                    break;
                case 4:
                    ((AnsewerPresenter) mPresenter).askAgain(mQuestionGuid,
                            mAnswerGuid, mContentStr,4);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mGuid = getIntent().getStringExtra("Guid");
        mRecommendList = new ArrayList<>();
        mExplanationList = new ArrayList<>();
        mPresenter = new AnsewerPresenter(AnswerActivity.this,mHander);
        mPresenter.mBaseView = this;
        ((AnsewerPresenter) mPresenter).getAnswerDetail(mGuid,1);

        //禁用滑动事件
        mAskRv.setNestedScrollingEnabled(false);
        mRecommendRv.setNestedScrollingEnabled(false);
        mAskRv.setLayoutManager(new LinearLayoutManager(AnswerActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecommendRv.setLayoutManager(new LinearLayoutManager(AnswerActivity.this, LinearLayoutManager.VERTICAL, false));
        initData();
        initDialog();
    }

    private void initData() {
        //追问
        mAskAdapter = new BaseQuickAdapter<AnswerData.ExplanationBean, BaseViewHolder>(R.layout.item_ask, mExplanationList) {

            @Override
            protected void convert(BaseViewHolder helper, AnswerData.ExplanationBean item) {
                helper.setText(R.id.mTimeTv, item.getOperateTime().split("T")[0]);
//                if (mAnswerData.getQuestionData().getAskData().getAskUser().getGuid().equals(item.getFromUserGuid())) {
//                    helper.setText(R.id.mNameTv, mAnswerData.getQuestionData().getAskData().getAskUser().getName());
//                    GlideUtils.loadImage(AnswerActivity.this, mAnswerData.getQuestionData().getAskData().getAskUser().getAvatarUrl(), helper.getView(R.id.mStuCiv));
//                } else if (mAnswerData.getQuestionData().getAskData().getAnswerUser().getGuid().equals(item.getFromUserGuid())) {
//                    helper.setText(R.id.mNameTv, mAnswerData.getQuestionData().getAskData().getAnswerUser().getName());
//                    GlideUtils.loadImage(AnswerActivity.this, mAnswerData.getQuestionData().getAskData().getAnswerUser().getAvatarUrl(), helper.getView(R.id.mStuCiv));
//                }
                helper.setText(R.id.mNameTv, mAnswerData.getQuestionData().getAskData().getAskUser().getName());
                GlideUtils.loadImage(AnswerActivity.this, mAnswerData.getQuestionData().getAskData().getAskUser().getAvatarUrl(), helper.getView(R.id.mStuCiv));
                helper.setText(R.id.mContentTv, item.getContent());
            }
        };
        mAskRv.setAdapter(mAskAdapter);

        //相似问题
        mRecommendAdapter = new BaseQuickAdapter<AnswerData.RecommendBean, BaseViewHolder>(R.layout.item_more_know, mRecommendList) {

            @Override
            protected void convert(BaseViewHolder helper, AnswerData.RecommendBean item) {
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
                Glide.with(AnswerActivity.this).load(item.getAskData().getAnswerUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mTeaCiv));
                Glide.with(AnswerActivity.this).load(item.getAskData().getAskUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mStuCiv));
            }
        };

        mRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HashMap<String, String> map = new HashMap<>();
                map.put("Guid", mRecommendList.get(position).getAskData().getGuid());
                ToolUtils.startActivity(AnswerActivity.this, AnswerActivity.class, map);
            }
        });
        mRecommendRv.setAdapter(mRecommendAdapter);
    }

    @OnClick({R.id.mDownIv, R.id.mAgainLl, R.id.mCloseLl, R.id.mTeaCiv, R.id.mConfirmTv, R.id.mGotoTv, R.id.mLikeLl})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.mGotoTv:
                if (mAnswerData.getQuestionData().getAskData().getRefrenceResource() != null) {
                    Intent intent = new Intent(AnswerActivity.this, QuestionsActivity.class);
                    intent.putExtra("IsAnalysisShow", false);
                    intent.putExtra("Guid",mGuid);
                    intent.putStringArrayListExtra("GuidList", (ArrayList<String>) mAnswerData.getQuestionData().getAskData().getRefrenceQuestion());
                    startActivity(intent);
                }
                break;
            case R.id.mDownIv:
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (!isOpen) {
                    lp.height = ScreenUtils.dip2px(mExplanationList.size() * 58);
                    mAskRv.setLayoutParams(lp);
                    mDownIv.setImageResource(R.drawable.ic_up);
                    isOpen = true;
                } else {
                    lp.height = ScreenUtils.dip2px(116);
                    mAskRv.setLayoutParams(lp);
                    mDownIv.setImageResource(R.drawable.ic_down);
                    isOpen = false;
                }
                break;
            case R.id.mAgainLl:
                mBottomLl.setVisibility(View.GONE);
                mAgainRl.setVisibility(View.VISIBLE);
                break;
            case R.id.mCloseLl:
                //当AlertDialog存在实例对象并且没有在展示时
                if (mAlertDialog != null && !mAlertDialog.isShowing()) {
                    mAlertDialog.show();
                    mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.common_blue));
                    mAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.text_light_dark));
                }
                break;
            case R.id.mTeaCiv:
                if (mAnswerData != null) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("Guid", mAnswerData.getQuestionData().getAskData().getAnswerUser().getGuid());
                    ToolUtils.startActivity(AnswerActivity.this, TeacherInfoActivity.class, map);
                }
                break;
            case R.id.mConfirmTv:
                mContentStr = mAgainEt.getText().toString();
                mQuestionGuid = mAnswerData.getQuestionData().getAskQuestionGuid();
                mAnswerGuid = mAnswerData.getQuestionData().getAskData().getAnswerUser().getGuid();
                if (mAnswerData != null) {
                    if (!TextUtils.isEmpty(mContentStr))
                        ((AnsewerPresenter) mPresenter).askAgain(mQuestionGuid,
                                mAnswerGuid, mContentStr,4);
                    else
                        ToastUtil.show("请输入内容");
                }
                break;
            case R.id.mLikeLl:
                if (mLike == 0){
                    thumbUp = 1;
                }else
                    thumbUp = 0;
                ((AnsewerPresenter) mPresenter).getThumbUp(mGuid, thumbUp,2);
                break;
        }
    }

    /*
   初始化AlertDialog
    */
    public void initDialog() {
        //创建AlertDialog的构造器的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(AnswerActivity.this);
        //设置构造器标题
        builder.setTitle("提示");
        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
        builder.setMessage("您是否要关闭问答？");
        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //第一个参数dialog是点击的确定按钮所属的Dialog对象,第二个参数which是按钮的标示值
                ((AnsewerPresenter) mPresenter).endAskQuestion(mGuid,3);
            }
        });
        //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialog.dismiss();
            }
        });
        //利用构造器创建AlertDialog的对象,实现实例化
        mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void getAnswer(AnswerData answerData) {
        mAnswerData = answerData;
        mLike = mAnswerData.getThumbUpFlag();
        if (mLike == 0)
            GlideUtils.loadImage(AnswerActivity.this, R.drawable.ic_unlike, mLikeIv);
        else
            GlideUtils.loadImage(AnswerActivity.this, R.drawable.ic_like, mLikeIv);
        if (mAnswerData.getQuestionData().getAskStatus() == 2 ||
                !AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_GUID, "").equals(mAnswerData.getQuestionData().getAskData().getAskUser().getGuid())) {
            mAgainLl.setBackgroundColor(getResources().getColor(R.color.common_disable));
            mCloseLl.setBackgroundColor(getResources().getColor(R.color.common_disable));
            mAgainLl.setEnabled(false);
            mCloseLl.setEnabled(false);
        }

        //显示学生信息
        GlideUtils.loadImage(AnswerActivity.this, answerData.getQuestionData().getAskData().getAskUser().getAvatarUrl(), mStuCiv);
        mStuTv.setText(answerData.getQuestionData().getAskData().getAskUser().getName());
        mTimeTv.setText(answerData.getQuestionData().getOperateTime().split("T")[0]);
        if (answerData.getQuestionData().getAskData().getQuestion() != null) {
            String html = BaseConstant.BASE_CSS + answerData.getQuestionData().getAskData().getQuestion() + "</body></html>";
            mQuestionWv.loadData(html, "text/html", "uft-8");
        } else {
            mQuestionWv.setVisibility(View.GONE);
        }

        //显示老师信息和点赞数
        GlideUtils.loadImage(AnswerActivity.this, answerData.getQuestionData().getAskData().getAnswerUser().getAvatarUrl(), mTeaCiv);
        mTeaTv.setText(answerData.getQuestionData().getAskData().getAnswerUser().getName());
        mTeaLeavlTv.setText(answerData.getQuestionData().getAskData().getAnswerUser().getTitle());
        mTeaSchoolTv.setText(answerData.getQuestionData().getAskData().getAnswerUser().getSchoolName());
        mLoveNumTv.setText(answerData.getQuestionData().getThumupNumber() + "");
        if (answerData.getQuestionData().getAskData().getRefrenceResource() != null)
            mSubjectTv.setText("过关练习 共" + mAnswerData.getQuestionData().getAskData().getRefrenceQuestion().size() + "题");
        else
            mSubjectRl.setVisibility(View.GONE);
        if (answerData.getQuestionData().getAskData().getAnswer() != null) {
            String html2 = BaseConstant.BASE_CSS + answerData.getQuestionData().getAskData().getAnswer() + "</body></html>";
            mAnswerWv.loadData(html2, "text/html", "uft-8");
        } else {
            mAnswerWv.setVisibility(View.GONE);
        }

        //动态增加问答资源
        if (answerData.getQuestionData().getAskData().getRefrenceResource() != null) {
            if (answerData.getQuestionData().getAskData().getRefrenceResource().size() == 0)
                mResourceLl.setVisibility(View.GONE);
            else {
                mResourceLl.removeAllViews();
                for (int i = 0; i < answerData.getQuestionData().getAskData().getRefrenceResource().size(); i++) {
                    View mResourceView = LayoutInflater.from(AnswerActivity.this).inflate(R.layout.item_resource, null);
                    //根据Resourcetype判断资源类型
                    if (answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getResourceType().equals(".docx")) {
                        ImageView mResourceIV = mResourceView.findViewById(R.id.mResourceIv);
                        mResourceIV.setImageResource(R.drawable.ic_word);
                        TextView mResourceTv = mResourceView.findViewById(R.id.mResourceTv);
                        mResourceTv.setText(answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getTitle());
                    } else if (answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getResourceType().equals(".pdf")) {
                        ImageView mResourceIV = mResourceView.findViewById(R.id.mResourceIv);
                        mResourceIV.setImageResource(R.drawable.ic_pdf);
                        TextView mResourceTv = mResourceView.findViewById(R.id.mResourceTv);
                        mResourceTv.setText(answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getTitle());

                    } else if (answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getResourceType().equals(".pptx")) {
                        ImageView mResourceIV = mResourceView.findViewById(R.id.mResourceIv);
                        mResourceIV.setImageResource(R.drawable.ic_ppt);
                        TextView mResourceTv = mResourceView.findViewById(R.id.mResourceTv);
                        mResourceTv.setText(answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getTitle());
                    } else if (answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getResourceType().equals(".mp4") ||
                            answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getResourceType().equals(".flv") ||
                            answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getResourceType().equals(".avi")) {
                        ImageView mResourceIV = mResourceView.findViewById(R.id.mResourceIv);
                        mResourceIV.setImageResource(R.drawable.ic_mp4);
                        TextView mResourceTv = mResourceView.findViewById(R.id.mResourceTv);
                        mResourceTv.setText(answerData.getQuestionData().getAskData().getRefrenceResource().get(i).getTitle());
                    }
                    int positon = i;
                    mResourceView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getResourceType().equals(".mp4") ||
                                    answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getResourceType().equals(".flv") ||
                                    answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getResourceType().equals(".avi")) {
                                //点击跳转视频播放
                                HashMap<String, String> map = new HashMap<>();
                                map.put("guid", answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getGuid());
                                map.put("extension", answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getResourceType());
                                map.put("title", answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getTitle());
                                ToolUtils.startActivity(AnswerActivity.this, VideoViewActivity.class, map);
                            } else {
                                //点击跳转pdf预览
                                HashMap<String, String> map = new HashMap<>();
                                map.put("guid", answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getGuid());
                                map.put("extension", answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getResourceType());
                                map.put("title", answerData.getQuestionData().getAskData().getRefrenceResource().get(positon).getTitle());
                                ToolUtils.startActivity(AnswerActivity.this, PdfViewActivity.class, map);
                            }
                        }
                    });
                    mResourceLl.addView(mResourceView);
                }
            }
        } else {
            mResourceLl.setVisibility(View.GONE);
        }

        //追问
        mExplanationList = answerData.getExplanation();
        Log.d("cj", "size====dddddd>>>" + mExplanationList.size());
        if (mExplanationList.size() > 0) {
            mAskAdapter.setNewData(mExplanationList);
            mAskLl.setVisibility(View.VISIBLE);
            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.height = ScreenUtils.dip2px(116);
            if (mExplanationList.size() >= 2) {
                mDownIv.setVisibility(View.VISIBLE);
                mAskRv.setLayoutParams(lp);
            } else {
                mDownIv.setVisibility(View.GONE);
            }
        } else
            mAskLl.setVisibility(View.GONE);
        //相似问题数据
        mRecommendList = answerData.getRecommend();
        mRecommendAdapter.setNewData(mRecommendList);
    }

    @Override
    public void onLikeResult() {
        ((AnsewerPresenter) mPresenter).getAnswerDetail(mGuid,1);
        if (mLike == 0) {
            mLike = 1;
            ToastUtil.show("点赞成功！");
            GlideUtils.loadImage(AnswerActivity.this, R.drawable.ic_like, mLikeIv);
        } else {
            mLike = 0;
            ToastUtil.show("取消点赞！");
            GlideUtils.loadImage(AnswerActivity.this, R.drawable.ic_unlike, mLikeIv);
        }
    }

    @Override
    public void onEndQuestionResult() {
        mAgainLl.setBackgroundColor(getResources().getColor(R.color.common_disable));
        mCloseLl.setBackgroundColor(getResources().getColor(R.color.common_disable));
        mAgainLl.setEnabled(false);
        mCloseLl.setEnabled(false);
        if (mAlertDialog != null && mAlertDialog.isShowing())
            mAlertDialog.dismiss();
        finish();
    }

    @Override
    public void onAskAgainResult() {
        ((AnsewerPresenter) mPresenter).getAnswerDetail(mGuid,1);
        mBottomLl.setVisibility(View.VISIBLE);
        mAgainRl.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mAgainRl.getVisibility() == View.VISIBLE) {
                mBottomLl.setVisibility(View.VISIBLE);
                mAgainRl.setVisibility(View.GONE);
            }
        }
        return false;
    }
}
