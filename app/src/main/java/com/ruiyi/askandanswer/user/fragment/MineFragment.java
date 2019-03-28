package com.ruiyi.askandanswer.user.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.utils.bitmap.SaveBitmapCallBack;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseFragment;
import com.ruiyi.askandanswer.user.activity.MyCollectActivity;
import com.ruiyi.askandanswer.user.activity.MyAskActivity;
import com.ruiyi.askandanswer.user.activity.MyAttentionActivity;
import com.ruiyi.askandanswer.user.activity.MyFootActivity;
import com.ruiyi.askandanswer.user.activity.MyGoldActivity;
import com.ruiyi.askandanswer.user.activity.SettingActivity;
import com.ruiyi.askandanswer.user.model.UserInfo;
import com.ruiyi.askandanswer.user.presenter.MinePresenter;
import com.ruiyi.askandanswer.user.presenter.PayPresenter;
import com.ruiyi.askandanswer.user.view.MineView;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.GlideEngine;
import com.ruiyi.askandanswer.utils.GlideUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;
import com.ruiyi.askandanswer.utils.ToolUtils;
import com.ruiyi.askandanswer.widgets.CategoryItemLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment implements MineView {

    @BindView(R.id.mAvatarCiv)
    CircleImageView mAvatarCiv;
    @BindView(R.id.mInfoTv)
    TextView mInfoTv;
    @BindView(R.id.mPInfoTv)
    TextView mPInfoTv;
    Unbinder unbinder;
    @BindView(R.id.mSettingIv)
    ImageView mSettingIv;
    @BindView(R.id.mFootLl)
    CategoryItemLayout mFootLl;
    @BindView(R.id.mAskLl)
    CategoryItemLayout mAskLl;
    @BindView(R.id.mAttentionLl)
    CategoryItemLayout mAttentionLl;
    @BindView(R.id.mMoneyLl)
    CategoryItemLayout mMoneyLl;
    @BindView(R.id.mGetLl)
    CategoryItemLayout mGetLl;
    private String goldNum;
    private RequestOptions options;
    private String filePath;

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ((MinePresenter) mPresenter).getUserInfo(1);
                    break;
                case 2:
                    ((MinePresenter) mPresenter).uploadAvatar(filePath,2);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        //不使用缓存中的图片
        options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
        mPresenter = new MinePresenter(getActivity(),mHander);
        mPresenter.mBaseView = this;
        Glide.with(getActivity()).load(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_AVATARURL, "")).apply(options).into(mAvatarCiv);
        mInfoTv.setText(AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_NAME, "") + " " +
                AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_SCHOOLNAME, "") + " " +
                AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_GRADE, ""));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MinePresenter) mPresenter).getUserInfo(1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onMineResult(UserInfo info) {
        goldNum = info.getCoinNumber() + "";
        mAttentionLl.setNumber(info.getAttentionNumber() + "");
        mAskLl.setNumber(info.getMyAskNumber() + "");
        mFootLl.setNumber(info.getFootprintNumber() + "");
        mMoneyLl.setNumber(info.getCoinNumber() + "");
    }

    @Override
    public void onAvatarResult(String url) {
        AppPrefsUtils.saveString(SharedPreferencesKey.SHAREDPREFERENCES_AVATARURL,url);
        Glide.with(getActivity()).load(url).apply(options).into(mAvatarCiv);
    }

    @OnClick({R.id.mSettingIv, R.id.mFootLl, R.id.mAskLl, R.id.mAttentionLl, R.id.mMoneyLl, R.id.mGetLl, R.id.mAvatarCiv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mSettingIv:
                ToolUtils.startActivity(getActivity(), SettingActivity.class, null);
                break;
            case R.id.mFootLl:
                ToolUtils.startActivity(getActivity(), MyFootActivity.class, null);
                break;
            case R.id.mAskLl:
                ToolUtils.startActivity(getActivity(), MyAskActivity.class, null);
                break;
            case R.id.mAttentionLl:
                ToolUtils.startActivity(getActivity(), MyAttentionActivity.class, null);
                break;
            case R.id.mMoneyLl:
                HashMap<String,String> map = new HashMap<>();
                map.put("Gold",goldNum);
                ToolUtils.startActivity(getActivity(), MyGoldActivity.class, map);
                break;
            case R.id.mGetLl:
                ToolUtils.startActivity(getActivity(), MyCollectActivity.class, null);
                break;
            case R.id.mAvatarCiv:
                EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.ruiyi.askandanswer.fileprovider")
                        .start(101);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //相机或相册回调
        if (requestCode == 101) {
            if(data != null){
                //返回图片地址集合：如果你只需要获取图片的地址，可以用这个
                ArrayList<String> resultPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);
                String savePath;
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    savePath = "/sdcard/Ask/pic/";
                } else {
                    savePath = getActivity().getApplicationContext().getFilesDir()
                            .getAbsolutePath()
                            + "/Ask/pic/";
                }

                EasyPhotos.saveBitmapToDir(getActivity(), savePath, "avatar", ToolUtils.compressImage(resultPaths.get(0)), false, new SaveBitmapCallBack() {
                    @Override
                    public void onSuccess(File file) {
                        filePath = file.getAbsolutePath();
                        ((MinePresenter) mPresenter).uploadAvatar(filePath,2);
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
