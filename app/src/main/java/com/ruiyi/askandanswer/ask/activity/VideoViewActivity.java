package com.ruiyi.askandanswer.ask.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.exo.GSYExo2PlayerView;
import com.ruiyi.askandanswer.exo.GSYExoVideoManager;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.widgets.HeaderBar;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoViewActivity extends GSYBaseActivityDetail<GSYExo2PlayerView> {

    @BindView(R.id.mVideo)
    GSYExo2PlayerView mVideo;
    @BindView(R.id.mLeftIv)
    ImageView mLeftIv;
    @BindView(R.id.mTitleTv)
    TextView mTitleTv;
    private String mGuidStr;
    private String mExtensionStr;
    private String mTitleStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        mGuidStr = getIntent().getStringExtra("guid");
        mExtensionStr = getIntent().getStringExtra("extension");
        mTitleStr = getIntent().getStringExtra("title");
        mTitleTv.setText(mTitleStr);
        mLeftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Map<String, String> mapHeadData = new HashMap<>();
        mapHeadData.put("Authorization", "Bearer " + AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN, ""));
        //GSYBaseActivityDetail 的 普通模式初始化
        initVideo();
        String url = BaseConstant.BASE_URL + "AskAnswer/GetResource?start=0&end=100000&guid=" + mGuidStr + "&extension=" + mExtensionStr;
        Log.d("cj","url===>>>" + url);
//        String url = BaseConstant.BASE_URL + "AskAnswer/GetResource?start=0&end=100000&guid=30217b66b4cc45361b29e993a6ecb601&extension=.mp4";
//        String url = "http://center.lexuewang.cn:5003/aaa.mp4";
        List<GSYVideoModel> urls = new ArrayList<>();
        urls.add(new GSYVideoModel(url, ""));
        mVideo.setUp(urls, 0, null, mapHeadData);

        //使用 exo 的 CacheDataSourceFactory 实现
        mVideo.setExoCache(true);

        resolveNormalVideoUI();

        mVideo.setIsTouchWiget(true);
        //关闭自动旋转
        mVideo.setRotateViewAuto(false);
        mVideo.setLockLand(false);
        mVideo.setShowFullAnimation(false);
        mVideo.setNeedLockFull(true);

        mVideo.setVideoAllCallBack(this);

        mVideo.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
    }

    /**
     * 重载为GSYExoVideoManager的方法处理
     */
    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYExoVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    public GSYExo2PlayerView getGSYVideoPlayer() {
        return mVideo;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //不用builder的模式
        return null;
    }

    @Override
    public void clickForFullScreen() {
    }

    /**
     * 是否启动旋转横屏，true表示启动
     *
     * @return true
     */
    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    @Override
    public void onEnterFullscreen(String url, Object... objects) {
        super.onEnterFullscreen(url, objects);
        //隐藏调全屏对象的返回按键
        GSYVideoPlayer gsyVideoPlayer = (GSYVideoPlayer) objects[1];
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
    }


    private void resolveNormalVideoUI() {
        //增加title
        mVideo.getTitleTextView().setVisibility(View.GONE);
        mVideo.getBackButton().setVisibility(View.GONE);
    }

    private GSYVideoPlayer getCurPlay() {
        if (mVideo.getFullWindowPlayer() != null) {
            return mVideo.getFullWindowPlayer();
        }
        return mVideo;
    }
}
