package com.ruiyi.askandanswer.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;

import butterknife.BindView;

public class HeaderBar extends FrameLayout {

    ImageView mLeftIv;
    TextView mTitleTv;
    TextView mRightTv;
    ImageView mRightIv;
    //是否显示"返回"图标
    private boolean isShowBack = true;
    //Title文字
    private String titleText = null;
    private String rightText = null;

    public HeaderBar(Context context) {
        this(context, null);
    }

    public HeaderBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar);

        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true);
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText);
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText);

        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(getContext(), R.layout.layout_header_bar, null);
        mLeftIv = view.findViewById(R.id.mLeftIv);
        mRightIv = view.findViewById(R.id.mRightIv);
        mTitleTv = view.findViewById(R.id.mTitleTv);
        mRightTv = view.findViewById(R.id.mRightTv);

        addView(view, 0);

        mTitleTv.setText(titleText);
        mRightTv.setText(rightText);

        if (isShowBack)
            mLeftIv.setVisibility( View.VISIBLE);
        else
            mLeftIv.setVisibility( View.GONE);

        //返回图标默认实现（关闭Activity）
        mLeftIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof Activity){
                    ((Activity)context).finish();
                }
            }
        });
    }

    //设置标题
    public void setTitle(String title){
        mTitleTv.setText(title);
    }

    //设置右侧文字
    public void setRightImage(int resId){
        mRightIv.setImageResource(resId);
    }

    //设置右侧显示
    public void setRightVisiable(boolean isShow){
        if(isShow)
            mRightIv.setVisibility(View.VISIBLE);
        else
            mRightIv.setVisibility(View.GONE);
    }

    public ImageView getLeftView(){
        return mLeftIv;
    }

    public ImageView getRightView(){
        return mRightIv;
    }

    public TextView getRightTv(){
        return mRightTv;
    }
}
