package com.ruiyi.askandanswer.ask.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.utils.bitmap.SaveBitmapCallBack;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.model.response.SubjectData;
import com.ruiyi.askandanswer.ask.presenter.AnsewerPresenter;
import com.ruiyi.askandanswer.ask.presenter.AskPresenter;
import com.ruiyi.askandanswer.ask.view.AskQuestionView;
import com.ruiyi.askandanswer.base.BaseActivity;
import com.ruiyi.askandanswer.user.model.GradeInfo;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.GlideEngine;
import com.ruiyi.askandanswer.utils.GlideUtils;
import com.ruiyi.askandanswer.utils.ScreenUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToastUtil;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.HeaderBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.yedaxia.richeditor.IImageLoader;
import io.github.yedaxia.richeditor.IRichEditor;
import io.github.yedaxia.richeditor.IUploadEngine;
import io.github.yedaxia.richeditor.RichTextEditor;

public class AskActivity extends BaseActivity implements AskQuestionView {

    private static final int REQUEST_CODE_SELECT_IMG = 0x11;
    @BindView(R.id.mHeaderBar)
    HeaderBar mHeaderBar;
    @BindView(R.id.mSubjectSp)
    AppCompatSpinner mSubjectSp;
    @BindView(R.id.mGradeSp)
    AppCompatSpinner mGradeSp;
    @BindView(R.id.mPublicSw)
    Switch mPublicSw;
    @BindView(R.id.mPictureIv)
    ImageView mPictureIv;
    @BindView(R.id.mBoldIv)
    ImageView mBoldIv;
    @BindView(R.id.mHeaderIv)
    ImageView mHeaderIv;
    @BindView(R.id.mPIv)
    ImageView mPIv;
    @BindView(R.id.mLinkIv)
    ImageView mLinkIv;
    @BindView(R.id.mRichEditor)
    RichTextEditor mRichEditor;
    @BindView(R.id.mWebView)
    WebView mWebView;
    @BindView(R.id.mAvatarCiv)
    CircleImageView mAvatarCiv;
    @BindView(R.id.mTeaTv)
    TextView mTeaTv;
    @BindView(R.id.mAnswerLl)
    LinearLayout mAnswerLl;
    private List<String> mSubjectList;
    private List<SubjectData> mSubjectDataList;
    private ArrayAdapter arrayAdapter;
    private String mSubjectStr;
    private String mSubjectIdStr;
    private AlertDialog mLinkAlertDialog;
    private String mTeaGuid = "";//如果有指定教师的话，没有传空字符串
    private String mTeaAvatar = "";//如果有指定教师的话，没有传空字符串
    private String mTeaName = "";//如果有指定教师的话，没有传空字符串
    private int mType = 0;
    private String mGrade;//年级
    private List<String> gradeList;
    private List<GradeInfo> gradeInfoList;
    private ArrayAdapter mGradeAdapter;
    private int mGradeCode;//年级code
    private int mPrivate = 1;
    private String mQuestionGuid = "";//如果是通过通过题目直接提问的话，没有传空字符串
    public IUploadEngine.UploadProgressListener mListener;
    private String mHtml = "";

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ((AskPresenter) mPresenter).getSubjects(1);
                    break;
                case 2:
                    ((AskPresenter) mPresenter).getGrade(2);
                    break;
                case 3:
                    ((AskPresenter) mPresenter).getAskQuestion(mSubjectIdStr, mGradeCode, mPrivate, mType, mQuestionGuid, mTeaGuid,
                            mRichEditor.getHtmlContent(), AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLGUID, ""),
                            AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME, ""),
                            AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PROVINCE, ""),3);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        gradeList = new ArrayList<>();
        gradeInfoList = new ArrayList<>();
        mTeaGuid = getIntent().getStringExtra("TeaGuid");
        mTeaAvatar = getIntent().getStringExtra("TeaAvatar");
        mTeaName = getIntent().getStringExtra("TeaName");
        mQuestionGuid = getIntent().getStringExtra("QGuid");
        mHtml = getIntent().getStringExtra("Html");
        mType = getIntent().getIntExtra("TYPE", 0);
        mSubjectList = new ArrayList<>();
        mSubjectDataList = new ArrayList<>();

        if(!TextUtils.isEmpty(mTeaGuid)){
            mAnswerLl.setVisibility(View.VISIBLE);
            GlideUtils.loadImage(AskActivity.this,mTeaAvatar,mAvatarCiv);
           mTeaTv.setText(mTeaName);
        }

        if (!TextUtils.isEmpty(mHtml))
            mWebView.loadData(mHtml, "text/html", "uft-8");
        else
            mWebView.setVisibility(View.GONE);

        mPresenter = new AskPresenter(AskActivity.this,mHander);
        mPresenter.mBaseView = this;
        ((AskPresenter) mPresenter).getSubjects(1);
        ((AskPresenter) mPresenter).getGrade(2);

        initListener();
    }

    private void initListener() {

        //设置图片加载器，必须
        mRichEditor.setImageLoader(new IImageLoader() {
            @Override
            public void loadIntoImageView(ImageView imageView, String uri) {
                Glide.with(AskActivity.this).load(uri).into(imageView);
            }
        });

        //设置图片上传，用户一选择图片就开始上传，非必须
        mRichEditor.setUploadEngine(new IUploadEngine() {

            @Override
            public void uploadImage(String imagePath, UploadProgressListener listener) {
                ((AskPresenter) mPresenter).uploadImage(imagePath, listener,3);
            }

            @Override
            public void cancelUpload(String imagePath) {

            }
        });

        mGradeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGrade = mGradeSp.getSelectedItem().toString();
                for (GradeInfo gradeInfo : gradeInfoList) {
                    if (gradeInfo.getGradeName().equals(mGrade))
                        mGradeCode = gradeInfo.getGradeCode();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGrade = gradeInfoList.get(0).getGradeName();
                mGradeCode = gradeInfoList.get(0).getGradeCode();
            }
        });

        mSubjectSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSubjectStr = mSubjectSp.getSelectedItem().toString();
                for (SubjectData subjectData : mSubjectDataList) {
                    if (subjectData.getName().equals(mSubjectStr))
                        mSubjectIdStr = subjectData.getCode();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSubjectStr = mSubjectDataList.get(0).getName();
                mSubjectIdStr = mSubjectDataList.get(0).getCode();
            }
        });

        mHeaderBar.getRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mHtml))
                    mHtml += mRichEditor.getHtmlContent();
                else
                    mHtml = mRichEditor.getHtmlContent();
                ((AskPresenter) mPresenter).getAskQuestion(mSubjectIdStr, mGradeCode, mPrivate, mType, mQuestionGuid, mTeaGuid,
                        mHtml, AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLGUID, ""),
                        AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME, ""),
                        AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_PROVINCE, ""),3);
            }
        });

        mPublicSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mPrivate = 1;
                else
                    mPrivate = 0;
            }
        });
    }

    @Override
    public void onSubjectsResult(List<SubjectData> subjectDataList) {
        mSubjectDataList = subjectDataList;
        for (SubjectData info : subjectDataList) {
            mSubjectList.add(info.getName());
        }
        arrayAdapter = new ArrayAdapter(AskActivity.this, android.R.layout.simple_spinner_item, mSubjectList) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(AskActivity.this).inflate(R.layout.item_spinner,
                        null);
                TextView label = (TextView) view
                        .findViewById(R.id.mNameTv);
                label.setText(mSubjectDataList.get(position).getName());
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        mSubjectSp.setAdapter(arrayAdapter);
    }

    @Override
    public void onGradeResult(List<GradeInfo> infoList) {
        gradeList.clear();
        gradeInfoList = infoList;
        for (GradeInfo info : infoList) {
            gradeList.add(info.getGradeName());
        }
        mGradeAdapter = new ArrayAdapter(AskActivity.this, android.R.layout.simple_spinner_item, gradeList) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(AskActivity.this).inflate(R.layout.item_spinner,
                        null);
                TextView label = (TextView) view
                        .findViewById(R.id.mNameTv);
                label.setText(gradeList.get(position));
                return view;
            }
        };
        mGradeAdapter.setDropDownViewResource(R.layout.item_spinner);
        mGradeSp.setAdapter(mGradeAdapter);
    }

    @Override
    public void onSubmitResult(String guid) {
        ToastUtil.show("提交成功！");
        HashMap<String,String> map = new HashMap<>();
        map.put("Guid",guid);
        ToolUtils.startActivity(AskActivity.this,AnswerActivity.class,map);
        finish();
    }

    @Override
    public void onUpLoadImage(List<String> imageList, IUploadEngine.UploadProgressListener listener, String filePath) {
        int[] wh = ToolUtils.getImageSize(AskActivity.this, filePath);
        listener.onUploadSuccess(filePath, imageList.get(0), 345, 600);
    }

    @OnClick({R.id.mPictureIv, R.id.mBoldIv, R.id.mHeaderIv, R.id.mPIv, R.id.mLinkIv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mPictureIv:
                startSelectImageIntent();
                break;
            case R.id.mBoldIv:
                mRichEditor.toggleBoldSelectText();
                break;
            case R.id.mHeaderIv:
                mRichEditor.insertHeading(IRichEditor.HEADING_1);
                break;
            case R.id.mPIv:
                mRichEditor.insertParagraph();
                break;
            case R.id.mLinkIv:
                onLinkClick();
                break;
        }
    }

    //链接
    private void onLinkClick() {
        if (mLinkAlertDialog == null) {
            View linkInputView = LayoutInflater.from(this).inflate(R.layout.dialog_link, null);
            final EditText etText = (EditText) linkInputView.findViewById(R.id.mTextEt);
            final EditText etLink = (EditText) linkInputView.findViewById(R.id.mLinkEt);
            mLinkAlertDialog = new AlertDialog.Builder(this)
                    .setTitle("添加链接")
                    .setView(linkInputView)
                    .setCancelable(true)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String text = etText.getText().toString().trim();
                            String link = etLink.getText().toString().trim();
                            if (text.isEmpty() || link.isEmpty()) {
                                Toast.makeText(getApplication(), "内容不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            mRichEditor.insertHyperlink(text, link);
                        }
                    }).create();
        }

        mLinkAlertDialog.show();
    }

    //选择图片，拍照
    private void startSelectImageIntent() {
        EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                .setFileProviderAuthority("com.ruiyi.askandanswer.fileprovider")
                .setOriginalMenu(false, false, "")
                .start(101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //相机或相册回调
        if (requestCode == 101) {
            if (data != null) {
                //返回图片地址集合：如果你只需要获取图片的地址，可以用这个
                ArrayList<String> resultPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);

                String savePath;
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    savePath = "/sdcard/Ask/pic/";
                } else {
                    savePath = getApplicationContext().getFilesDir()
                            .getAbsolutePath()
                            + "/Ask/pic/";
                }
//                for(int i = 0;i < resultPaths.set())

                EasyPhotos.saveBitmapToDir(AskActivity.this, savePath, "avatar", ToolUtils.compressImage(resultPaths.get(0)), false, new SaveBitmapCallBack() {
                    @Override
                    public void onSuccess(File file) {
                        Log.d("cj", "file====>>>" + file.getAbsolutePath());
                        mRichEditor.insertImage(file.getAbsolutePath());
//                        ((AskPresenter) mPresenter).uploadAvatar(file.getAbsolutePath());
                    }

                    @Override
                    public void onIOFailed(IOException exception) {

                    }

                    @Override
                    public void onCreateDirFailed() {

                    }
                });
            }
            return;
        }
    }
}