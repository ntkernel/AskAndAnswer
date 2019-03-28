package com.ruiyi.askandanswer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;

public class CategoryItemLayout extends LinearLayout {

    private TextView mNameTv;
    private TextView mNumberTv;
    private ImageView mLeftIv;
    private ImageView mNextIv;

    public CategoryItemLayout(Context context) {
        this(context,null);
    }

    public CategoryItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(context,attrs);
    }

    private void initWidget(Context context, AttributeSet attrs){
        View view = View.inflate(getContext(), R.layout.item_category, null);
        mNameTv = view.findViewById(R.id.mNameTv);
        mNumberTv = view.findViewById(R.id.mNumberTv);
        mLeftIv = view.findViewById(R.id.mLeftIv);
        mNextIv = view.findViewById(R.id.mNextIv);
        addView(view, 0);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.category_item);
        mNameTv.setText(typedArray.getString(R.styleable.category_item_txt));
        mNumberTv.setText(typedArray.getString(R.styleable.category_item_num));
        mLeftIv.setImageResource(typedArray.getResourceId(R.styleable.category_item_img_left_src, 0));
        mNextIv.setImageResource(typedArray.getResourceId(R.styleable.category_item_img_right_src, 0));

        boolean isVisiable = typedArray.getBoolean(R.styleable.category_item_img_right_visiable, true);
        if (isVisiable) {
            mNextIv.setVisibility(View.VISIBLE);
        } else {
            mNextIv.setVisibility(View.GONE);
        }
        typedArray.recycle();
    }

    public void setNumber(String number){
        mNumberTv.setText(number);
    }
}
