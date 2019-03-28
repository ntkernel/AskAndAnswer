package com.ruiyi.askandanswer.popwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.ask.activity.AskActivity;
import com.ruiyi.askandanswer.ask.view.AskQuestionView;
import com.ruiyi.askandanswer.utils.ToolUtils;

import java.util.HashMap;

public class AskPop extends PopupWindow implements View.OnClickListener{

    ImageView mCloseIv;
    LinearLayout mTopicLl;
    LinearLayout mOtherLl;
    View contentView;
    Context mContext;

    public AskPop(Context context) {
        super(context);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setFocusable(true);
        this.mContext = context;
        setBackgroundDrawable(new ColorDrawable(0));//new ColorDrawable(0)即为透明背景
        contentView = LayoutInflater.from(context).inflate(R.layout.pop_ask,
                null, false);
        setContentView(contentView);
        initView();
    }

    private void initView() {
        mCloseIv = contentView.findViewById(R.id.mCloseIv);
        mTopicLl = contentView.findViewById(R.id.mTopicLl);
        mOtherLl = contentView.findViewById(R.id.mOtherLl);

        mCloseIv.setOnClickListener(this);
        mTopicLl.setOnClickListener(this);
        mOtherLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext,AskActivity.class);
        switch (view.getId()){
            case R.id.mTopicLl:
                intent.putExtra("TYPE",0);
                intent.putExtra("TeaGuid","");
                intent.putExtra("QGuid","");
                mContext.startActivity(intent);
                if(onCloseListener != null)
                    onCloseListener.onSelected();
                break;
            case R.id.mOtherLl:
                intent.putExtra("TYPE",1);
                intent.putExtra("TeaGuid","");
                intent.putExtra("QGuid","");
                mContext.startActivity(intent);
                if(onCloseListener != null)
                    onCloseListener.onSelected();
                break;
            case R.id.mCloseIv:
                if(onCloseListener != null)
                    onCloseListener.onClose();
                break;
        }
    }

    public interface OnCloseListener {
        void onClose();
        void onSelected();
    }

    private OnCloseListener onCloseListener;

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.onCloseListener = onCloseListener;
    }
}
