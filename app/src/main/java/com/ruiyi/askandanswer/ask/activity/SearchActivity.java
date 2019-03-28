package com.ruiyi.askandanswer.ask.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.model.response.MoreErrorData;
import com.ruiyi.askandanswer.ask.model.response.MoreKnowData;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.presenter.AskMorePresenter;
import com.ruiyi.askandanswer.ask.view.AskMoreView;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.IconCenterEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements AskMoreView {

    @BindView(R.id.mSearchEt)
    IconCenterEditText mSearchEt;
    @BindView(R.id.mBackIv)
    ImageView mBackIv;
    @BindView(R.id.mSearchBarRl)
    RelativeLayout mSearchBarRl;
    @BindView(R.id.mHistoryRv)
    RecyclerView mHistoryRv;
    @BindView(R.id.mResultRv)
    RecyclerView mResultRv;
    @BindView(R.id.mCancleTv)
    TextView mCancleTv;
    @BindView(R.id.mHistoryLl)
    LinearLayout mHistoryLl;
    private List<String> historyList;// 搜索历史记录
    private BaseQuickAdapter historyAdapter;
    private String mSearchStr = "";
    private BaseQuickAdapter baseQuickAdapter;
    private int totalNum = 1;//总条数
    private int currentPage = 1;//当前页
    private int limit = 10;//一次加载个数
    private List<MoreKnowData.QuestionDataBean> questionDataBeanList;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 4:
                    ((AskMorePresenter) mPresenter).search(mSearchStr, limit, currentPage,4);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        historyList = new ArrayList<>();
        questionDataBeanList = new ArrayList<>();
        mPresenter = new AskMorePresenter(SearchActivity.this,mHander);
        mPresenter.mBaseView = this;
        mSearchEt.setFocusable(true);
        initData();
        initListener();
        getHistory();
    }

    private void initData() {
        mHistoryRv.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        historyAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_history, historyList) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.mHistoryTv, item);
                helper.addOnClickListener(R.id.mDeleteIv);
            }
        };
        mHistoryRv.setAdapter(historyAdapter);

        mResultRv.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
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
                Glide.with(SearchActivity.this).load(item.getAskData().getAnswerUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mTeaCiv));
                Glide.with(SearchActivity.this).load(item.getAskData().getAskUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mStuCiv));
            }
        };
        mResultRv.setAdapter(baseQuickAdapter);
    }

    private void initListener() {

        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchEt.setText("");
                mSearchEt.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标
                ToolUtils.hideInput(SearchActivity.this, mSearchEt);
            }
        });

        historyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String historyString = "";
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_HISTORY, historyString);
                historyList.remove(position);
                historyAdapter.notifyDataSetChanged();
                for(int i = 0;i < historyList.size();i++){
                    historyString = historyString + " " + historyList.get(i);
                }
                AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_HISTORY, historyString);
            }
        });

        historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getHistory();
                mSearchStr = historyList.get(position);
                mSearchEt.setFocusable(true);
                mSearchEt.setText(mSearchStr);
                currentPage = 1;
                storeHistory(mSearchStr, false);
                ((AskMorePresenter) mPresenter).search(mSearchStr, limit, currentPage,4);
                mHistoryLl.setVisibility(View.GONE);
                mResultRv.setVisibility(View.VISIBLE);
            }
        });

        mSearchEt.setOnSearchClickListener(new IconCenterEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                mSearchStr = mSearchEt.getText().toString();
                currentPage = 1;
                storeHistory(mSearchStr, false);
                ((AskMorePresenter) mPresenter).search(mSearchStr, limit, currentPage,4);
                mHistoryLl.setVisibility(View.GONE);
                mResultRv.setVisibility(View.VISIBLE);
            }
        });

        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    historyList.clear();
                    getHistory();
                    mHistoryLl.setVisibility(View.VISIBLE);
                    mResultRv.setVisibility(View.GONE);
                } else {
                    questionDataBeanList.clear();
                    mHistoryLl.setVisibility(View.GONE);
                    mResultRv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        baseQuickAdapter.setUpFetchEnable(false);
        baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage * 10 < totalNum) {
                            currentPage++;
                            ((AskMorePresenter) mPresenter).search(mSearchStr, limit, currentPage,4);
                            baseQuickAdapter.loadMoreComplete();
                        } else {
                            baseQuickAdapter.loadMoreEnd();
                        }
                    }
                }, 500);
            }
        }, mResultRv);
    }

    private void getHistory() {
        historyList.clear();
        historyList = readSearchHistory();
        if (historyList.size() > 0) {
            mResultRv.setVisibility(View.GONE);
            mHistoryLl.setVisibility(View.VISIBLE);
            historyAdapter.setNewData(historyList);
            historyAdapter.notifyDataSetChanged();
        } else {
            mResultRv.setVisibility(View.VISIBLE);
            mHistoryLl.setVisibility(View.GONE);
        }
    }

    /**
     * 获取搜索历史
     *
     * @return
     */
    private List<String> readSearchHistory() {
        String history = AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_HISTORY, "");
        String[] historyArray = history.split(" ");
        for (int i = 0; i < historyArray.length; i++) {
            String string = new String(historyArray[i]);
            if (!TextUtils.isEmpty(string)) {
                historyList.add(string);
            }
        }
        return historyList;
    }

    ;

    /******
     * 保存历史搜索，按照先进先出的原则保存5条
     *
     * @author ThinkinBunny
     * @param keyWord
     *            ，为null是强制保存
     * ********/
    public void storeHistory(String keyWord, boolean isRemoveKW) {
//        historyList.trimToSize();
        if (isRemoveKW) {
            historyList.remove(keyWord);
        } else {
            if (historyList.contains(keyWord)) {
                String tmpKeyWordString = keyWord;
                historyList.remove(keyWord);
                historyList.add(0, tmpKeyWordString);
            } else if (historyList.size() < 5) {
                historyList.add(0, keyWord);
            } else {
                historyList.remove(historyList.size() - 1);
                historyList.add(0, keyWord);
            }
        }

        String historyString = "";
        for (int i = 0; i < historyList.size(); i++) {
            historyString = historyString + " "
                    + historyList.get(i);
        }
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_HISTORY, historyString);
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
        baseQuickAdapter.setNewData(questionDataBeanList);
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMoreErrorResult(MoreErrorData moreErrorData) {

    }
}
