package com.ruiyi.askandanswer.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.ruiyi.askandanswer.R

/**
 * Created by Administrator on 2018/2/24.
 */

class MXRecyclerView : XRecyclerView {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        setPullRefreshEnabled(true)
        setRefreshProgressStyle(ProgressStyle.SysProgress)
        setArrowImageView(R.drawable.ic_pulltorefresh_arrow)
        val footView = XRecyclerLoadingMoreFooter(getContext())
        footView.setNoMoreHint("我是有底线的")
        footView.setProgressStyle(ProgressStyle.BallPulse)
        footView.visibility = View.GONE
        setFootView(footView, object : CustomFooterViewCallBack {
            override fun onLoadingMore(yourFooterView: View) {
                Log.d("cj", "onLoadingMore======>>>>")
                footView.setState(XRecyclerLoadingMoreFooter.STATE_LOADING)
            }

            override fun onLoadMoreComplete(yourFooterView: View) {
                Log.d("cj", "onLoadMoreComplete======>>>>")
                footView.setState(XRecyclerLoadingMoreFooter.STATE_COMPLETE)
            }

            override fun onSetNoMore(yourFooterView: View, noMore: Boolean) {
                Log.d("cj", "onSetNoMore======>>>>")
                footView.setState(if (noMore) XRecyclerLoadingMoreFooter.STATE_NOMORE else XRecyclerLoadingMoreFooter.STATE_COMPLETE)
            }
        })
    }
}
