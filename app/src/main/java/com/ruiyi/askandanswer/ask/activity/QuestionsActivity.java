package com.ruiyi.askandanswer.ask.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.model.response.QuestionData;
import com.ruiyi.askandanswer.ask.model.response.QuestionModel;
import com.ruiyi.askandanswer.ask.presenter.AnsewerPresenter;
import com.ruiyi.askandanswer.ask.view.QuestionView;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.utils.GlideUtils;
import com.ruiyi.askandanswer.utils.ScreenUtils;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionsActivity extends BaseActivity implements QuestionView {

    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mQuestionVf)
    ViewFlipper mQuestionVf;
    @BindView(R.id.mPreTv)
    TextView mPreTv;
    @BindView(R.id.mNextTv)
    TextView mNextTv;
    @BindView(R.id.mCollectLl)
    LinearLayout mCollectLl;
    @BindView(R.id.mAskTv)
    TextView mAskTv;
    @BindView(R.id.mBottomLl)
    LinearLayout mBottomLl;
    @BindView(R.id.mCollectTv)
    TextView mCollectTv;
    private List<String> mGuidList;
    private List<QuestionModel> mQuestionDataList;
    private LayoutInflater layoutInflater;
    private TextView mTvRight;
    private ArrayList<String> mAnswerList;
    private ArrayList<String> mRemarkList;
    private ArrayList<Integer> mAnswerResult;
    private ArrayList<Integer> mCollectResult;
    private int mCurrentPage = 0;//当前页
    private String mGuid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mGuid = getIntent().getStringExtra("Guid");
        layoutInflater = LayoutInflater.from(QuestionsActivity.this);
        mGuidList = new ArrayList<>();
        mQuestionDataList = new ArrayList<>();
        mGuidList = getIntent().getStringArrayListExtra("GuidList");
        mPresenter = new AnsewerPresenter(QuestionsActivity.this);
        mTvRight = mHeaderBar.getRightTv();
        mPresenter.mBaseView = this;
        if (mGuidList.size() == 1) {
            mPreTv.setVisibility(View.VISIBLE);
            mNextTv.setVisibility(View.GONE);
            mTvRight.setVisibility(View.VISIBLE);
            mPreTv.setText("提交");
            mTvRight.setText(1 + "/" + mGuidList.size());
        } else {
            mNextTv.setVisibility(View.VISIBLE);
            mPreTv.setVisibility(View.GONE);
            mTvRight.setText(1 + "/" + mGuidList.size());
        }
        mAnswerList = new ArrayList<>();
        mRemarkList = new ArrayList<>();
        mAnswerResult = new ArrayList<>();
        mCollectResult = new ArrayList<>();
        for (String guid : mGuidList) {
            ((AnsewerPresenter) mPresenter).getQuestion(guid);
            mAnswerList.add("");
            mRemarkList.add("");
            mAnswerResult.add(0);
            mCollectResult.add(0);
        }
    }

    @Override
    public void onQuestionReault(QuestionModel questionData) {
        mQuestionDataList.add(questionData);
        if (mGuidList.size() == mQuestionDataList.size()) {
            initData();
        }
    }

    //回答结果回调
    @Override
    public void onAnswerResult() {
//        //只有一道题目关闭界面
        if (mGuidList.size() == 1)
            finish();
    }

    //收藏结果回调
    @Override
    public void onCollectResult() {
        if (mCollectResult.get(mCurrentPage) == 0) {
            mCollectResult.set(mCurrentPage, 1);
            Drawable drawableLeft = getResources().getDrawable(
                    R.drawable.ic_collect);
            mCollectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                    null, null, null);
            mCollectTv.setCompoundDrawablePadding(ScreenUtils.dip2px(5));
            ToastUtil.show("收藏成功!");
        } else {
            mCollectResult.set(mCurrentPage, 0);
            Drawable drawableLeft = getResources().getDrawable(
                    R.drawable.ic_uncollect);
            mCollectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                    null, null, null);
            mCollectTv.setCompoundDrawablePadding(ScreenUtils.dip2px(5));
            ToastUtil.show("取消收藏成功!");
        }
    }

    //初始化题目内容
    private void initData() {
        mQuestionVf.removeAllViews();
        for (int i = 0; i < mQuestionDataList.size(); i++) {
            Log.d("cj", "mQuestionDataList====>>>" + mQuestionDataList.get(i).getQuestionData().getType());
            //单选题
            if (mQuestionDataList.get(i).getQuestionData().getType() == 0) {
                QuestionModel questionModel = mQuestionDataList.get(i);
                //单选题布局
                View choiceView = layoutInflater.inflate(R.layout.item_question, mQuestionVf, false);
                WebView mWebView = choiceView.findViewById(R.id.mWebView);
                RadioGroup mSelectRg = choiceView.findViewById(R.id.mSelectRg);
                RadioButton mARb = choiceView.findViewById(R.id.mARb);
                RadioButton mBRb = choiceView.findViewById(R.id.mBRb);
                RadioButton mCRb = choiceView.findViewById(R.id.mCRb);
                RadioButton mDRb = choiceView.findViewById(R.id.mDRb);
                WebView mAnalysisWv = choiceView.findViewById(R.id.mAnalysisWv);
                TextView mTipTv = choiceView.findViewById(R.id.mTipTv);
                EditText mRemarkEt = choiceView.findViewById(R.id.mRemarkEt);

                String html = BaseConstant.BASE_CSS + questionModel.getQuestionData().getContent() + "</body></html>";
                mWebView.loadData(html, "text/html", "uft-8");

                int position = i;
                mRemarkEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        mRemarkList.set(position, s.toString());
                    }
                });

                mSelectRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (group.getCheckedRadioButtonId()) {
                            case R.id.mARb:
                                mAnswerList.set(position, "A");
                                break;
                            case R.id.mBRb:
                                mAnswerList.set(position, "B");
                                break;
                            case R.id.mCRb:
                                mAnswerList.set(position, "C");
                                break;
                            case R.id.mDRb:
                                mAnswerList.set(position, "D");
                                break;
                        }
                        if (mAnswerList.get(position).equals(questionModel.getQuestionData().getAnswer().get(0)))
                            mAnswerResult.set(position, 1);
                        else
                            mAnswerResult.set(position, 0);
                    }
                });

                if (!questionModel.getStudentAnswer().equals("")) {
                    mAnalysisWv.setVisibility(View.VISIBLE);
                    mTipTv.setVisibility(View.GONE);
                    for (int j = 0; j < mSelectRg.getChildCount(); j++) {
                        mSelectRg.getChildAt(j).setEnabled(false);
                    }
                    if (questionModel.getStudentAnswer().equals(questionModel.getQuestionData().getAnswer().get(0))) {
                        if (questionModel.getStudentAnswer().equals("A"))
                            mSelectRg.check(R.id.mARb);
                        else if (questionModel.getStudentAnswer().equals("B")) {
                            mSelectRg.check(R.id.mBRb);
                        } else if (questionModel.getStudentAnswer().equals("C")) {
                            mSelectRg.check(R.id.mCRb);
                        } else if (questionModel.getStudentAnswer().equals("D")) {
                            mSelectRg.check(R.id.mDRb);
                        }
                    } else {
                        if (questionModel.getStudentAnswer().equals("A")) {
                            mARb.setBackground(getResources().getDrawable(R.drawable.bg_rg_error));
                            mARb.setTextColor(getResources().getColor(R.color.common_white));
                        } else if (questionModel.getStudentAnswer().equals("B")) {
                            mBRb.setBackground(getResources().getDrawable(R.drawable.bg_rg_error));
                            mBRb.setTextColor(getResources().getColor(R.color.common_white));
                        } else if (questionModel.getStudentAnswer().equals("C")) {
                            mCRb.setBackground(getResources().getDrawable(R.drawable.bg_rg_error));
                            mCRb.setTextColor(getResources().getColor(R.color.common_white));
                        } else if (questionModel.getStudentAnswer().equals("D")) {
                            mDRb.setBackground(getResources().getDrawable(R.drawable.bg_rg_error));
                            mDRb.setTextColor(getResources().getColor(R.color.common_white));
                        }
                        if (questionModel.getQuestionData().getAnswer().get(0).equals("A"))
                            mSelectRg.check(R.id.mARb);
                        else if (questionModel.getQuestionData().getAnswer().get(0).equals("B")) {
                            mSelectRg.check(R.id.mBRb);
                        } else if (questionModel.getQuestionData().getAnswer().get(0).equals("C")) {
                            mSelectRg.check(R.id.mCRb);
                        } else if (questionModel.getQuestionData().getAnswer().get(0).equals("D")) {
                            mSelectRg.check(R.id.mDRb);
                        }
                    }
                    mAnalysisWv.loadData(BaseConstant.BASE_CSS + questionModel.getQuestionData().getAnalysis() + "</body></html>", "text/html", "uft-8");
                } else {
                    mAnalysisWv.setVisibility(View.GONE);
                    mTipTv.setVisibility(View.VISIBLE);
                    for (int j = 0; j < mSelectRg.getChildCount(); j++) {
                        mSelectRg.getChildAt(j).setEnabled(true);
                    }
                }
                mQuestionVf.addView(choiceView);
            } else {
                View errorView = layoutInflater.inflate(R.layout.error_view, mQuestionVf, false);
                mQuestionVf.addView(errorView);
            }
        }
    }

    @OnClick({R.id.mPreTv, R.id.mNextTv, R.id.mCollectLl, R.id.mAskTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mPreTv:
                Log.d("cj", "mPreTv====>>>" + mQuestionVf.getDisplayedChild());
                if (mGuidList.size() != 1) {
                    if (mQuestionVf.getDisplayedChild() == 1) {
                        mCurrentPage = 0;
                        mPreTv.setVisibility(View.GONE);
                    } else {
                        mNextTv.setText("下一题");
                    }
                    mQuestionVf.showPrevious();
                    mTvRight.setText((mQuestionVf.getDisplayedChild() + 1) + "/" + mGuidList.size());
                } else {
                    mCurrentPage = 0;
                }

                //不是错题提交答案
                if (mQuestionDataList.get(mCurrentPage).getQuestionData().getType() != -1) {
                    ((AnsewerPresenter) mPresenter).getStudentAnswer(mGuidList.get(mCurrentPage), mAnswerList.get(mCurrentPage),mGuid,
                            mQuestionDataList.get(mCurrentPage).getQuestionData().getSubjectId() + "", mRemarkList.get(mCurrentPage), mAnswerResult.get(mCurrentPage));
                } else {
                    if (mGuidList.size() == 1)
                        finish();
                }

                break;
            case R.id.mNextTv:
                Log.d("cj", "mNextTv====>>>" + mQuestionVf.getDisplayedChild());
                mPreTv.setVisibility(View.VISIBLE);
                if (mQuestionVf.getDisplayedChild() == 0) {
                    mQuestionVf.showNext();
                    mCurrentPage = mQuestionVf.getDisplayedChild() + 1;
                    mTvRight.setText(mCurrentPage + "/" + mGuidList.size());
                    mCurrentPage = mQuestionVf.getDisplayedChild();
                } else if (mQuestionVf.getDisplayedChild() == mQuestionVf.getChildCount() - 2) {
                    if(mQuestionDataList.get(mCurrentPage).getQuestionData().getType() != -1){
                        if (mQuestionDataList.get(mCurrentPage).getStudentAnswer().equals(""))
                            mNextTv.setText("提交");
                        else
                            mNextTv.setText("结束");
                    }else{
                        mNextTv.setText("结束");
                    }
                    mQuestionVf.showNext();
                    mCurrentPage = mQuestionVf.getDisplayedChild();
                    mTvRight.setText(mCurrentPage + 1 + "/" + mGuidList.size());
                    Log.d("cj","mCurrentPage-----111>>>>" + mCurrentPage);
                } else if (mQuestionVf.getDisplayedChild() == mQuestionVf.getChildCount() - 1) {
                    //达到最后一页
                    mCurrentPage = mGuidList.size() - 1;
                    Log.d("cj","mCurrentPage-----333>>>>" + mCurrentPage);
                    if(mQuestionDataList.get(mCurrentPage).getQuestionData().getType() != -1){
                        if (mQuestionDataList.get(mCurrentPage).getStudentAnswer().equals("")) {
                            mTvRight.setText(mCurrentPage + "/" + mGuidList.size());
                        } else
                            finish();
                    }else{
                        finish();
                    }
                } else {
                    mQuestionVf.showNext();
                    mCurrentPage = mQuestionVf.getDisplayedChild() + 1;
                    mTvRight.setText(mCurrentPage + "/" + mGuidList.size());
                    Log.d("cj","mCurrentPage-----222>>>>" + mCurrentPage);
                }
                //不是错题提交答案
                if (mQuestionDataList.get(mCurrentPage -1).getQuestionData().getType() != -1) {
                    Log.d("cj","mCurrentPage----->>>>" + mCurrentPage);
                    ((AnsewerPresenter) mPresenter).getStudentAnswer(mGuidList.get(mCurrentPage), mAnswerList.get(mCurrentPage), mGuid,
                            mQuestionDataList.get(mCurrentPage).getQuestionData().getSubjectId() + "", mRemarkList.get(mCurrentPage), mAnswerResult.get(mCurrentPage));
                }
                break;
            case R.id.mCollectLl:
                if(mQuestionDataList.get(mCurrentPage).getQuestionData().getType() != -1){
                    if (mCollectResult.get(mCurrentPage) == 0)
                        ((AnsewerPresenter) mPresenter).getCollectQuestion(mGuidList.get(mCurrentPage), mQuestionDataList.get(mCurrentPage).getQuestionData().getGuid(), 1);
                    else
                        ((AnsewerPresenter) mPresenter).getCollectQuestion(mGuidList.get(mCurrentPage), mQuestionDataList.get(mCurrentPage).getQuestionData().getGuid(), 0);
                }
                break;
            case R.id.mAskTv:
                if(mQuestionDataList.get(mCurrentPage).getQuestionData().getType() != -1){
                    Intent intent = new Intent(QuestionsActivity.this, AskActivity.class);
                    intent.putExtra("TYPE", 0);
                    intent.putExtra("TeaGuid", "");
                    intent.putExtra("QGuid", mGuidList.get(mCurrentPage));
                    intent.putExtra("Html", mQuestionDataList.get(mCurrentPage).getQuestionData().getContent());
                    startActivity(intent);
                }
                break;
        }
    }
}
