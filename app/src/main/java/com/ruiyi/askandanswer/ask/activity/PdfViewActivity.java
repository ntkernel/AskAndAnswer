package com.ruiyi.askandanswer.ask.activity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.presenter.AnsewerPresenter;
import com.ruiyi.askandanswer.ask.view.ResourceView;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfViewActivity extends BaseActivity implements ResourceView {

    @BindView(R.id.mPdfView)
    PDFView mPdfView;
    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    private String mGuidStr;
    private String mExtensionStr;
    private String mTitleStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mGuidStr = getIntent().getStringExtra("guid");
        mExtensionStr = getIntent().getStringExtra("extension");
        mTitleStr = getIntent().getStringExtra("title");

        mHeaderBar.setTitle(mTitleStr);
        mPresenter = new AnsewerPresenter(PdfViewActivity.this);
        mPresenter.mBaseView = this;
        ((AnsewerPresenter) mPresenter).getAnswerResource(mGuidStr, mExtensionStr);
    }

    @Override
    public void onResourceResult(byte[] mbyte) {
        mPdfView.fromBytes(mbyte).load();
    }

    @Override
    public void onResourceResult(String url) {

    }
}
