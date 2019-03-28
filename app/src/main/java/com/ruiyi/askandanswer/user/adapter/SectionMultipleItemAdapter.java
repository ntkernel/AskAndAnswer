package com.ruiyi.askandanswer.user.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.user.model.CionData;
import com.ruiyi.askandanswer.user.model.SectionMultipleItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionMultipleItemAdapter extends BaseSectionMultiItemQuickAdapter<SectionMultipleItem, BaseViewHolder> {
    /**
     * init SectionMultipleItemAdapter
     * 1. add your header resource layout
     * 2. add some kind of items
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    private Context mContext;

    public SectionMultipleItemAdapter(Context context, int sectionHeadResId, List data) {
        super(sectionHeadResId, data);
        this.mContext = context;
        addItemType(SectionMultipleItem.ANSWERD, R.layout.item_cion);
        addItemType(SectionMultipleItem.WAITE, R.layout.item_wait_answer);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final SectionMultipleItem item) {
        // deal with header viewHolder
        helper.setText(R.id.mTitleTv, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionMultipleItem item) {
        // deal with multiple type items viewHolder
        CionData.CostsBean.QuestionDataGroupBean cionDataGroupBean = item.t;
        if (item.getItemType() == 1) {
            helper.setText(R.id.mTitleTv, cionDataGroupBean.getAskData().getQuestion());
            helper.setText(R.id.mContentTv, cionDataGroupBean.getAskData().getAnswer());
            helper.setText(R.id.mTeaTv, cionDataGroupBean.getAskData().getAnswerUser().getName());
            helper.setText(R.id.mTeaLeavlTv, cionDataGroupBean.getAskData().getAnswerUser().getTitle());
            helper.setText(R.id.mTeaSchoolTv, cionDataGroupBean.getAskData().getAnswerUser().getSchoolName() + "#"
                    + cionDataGroupBean.getAskData().getAnswerUser().getProvince());
            helper.setText(R.id.mCionTv, "-" + cionDataGroupBean.getActionNumber());
            Glide.with(mContext).load(cionDataGroupBean.getAskData().getAnswerUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.mTeaCiv));
        } else {
            helper.setText(R.id.mTitleTv, cionDataGroupBean.getAskData().getQuestion());
            helper.setText(R.id.mCionTv, "-" + cionDataGroupBean.getActionNumber());
        }
    }
}
