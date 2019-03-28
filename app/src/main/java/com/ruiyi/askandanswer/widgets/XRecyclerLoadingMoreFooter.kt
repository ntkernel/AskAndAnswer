package com.ruiyi.askandanswer.widgets

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.SimpleViewSwitcher
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView
import com.ruiyi.askandanswer.R

class XRecyclerLoadingMoreFooter : LinearLayout {

    private var progressCon: SimpleViewSwitcher? = null

    private var mText: TextView? = null
    private var loadingHint: String? = null
    private var noMoreHint: String? = null
    private var loadingDoneHint: String? = null
    private var v_left: View? = null
    private var v_right: View? = null
    private var footView: View? = null

    private var progressView: AVLoadingIndicatorView? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    /**
     * @param context
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    fun destroy() {
        progressCon = null
        if (progressView != null) {
            progressView!!.destroy()
            progressView = null
        }
    }

    fun setLoadingHint(hint: String) {
        loadingHint = hint
    }

    fun setNoMoreHint(hint: String) {
        noMoreHint = hint
    }

    fun setLoadingDoneHint(hint: String) {
        loadingDoneHint = hint
    }

    fun initView() {
        gravity = Gravity.CENTER
        layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        progressCon = SimpleViewSwitcher(context)
        progressCon!!.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        progressView = AVLoadingIndicatorView(this.context)
        progressView!!.setIndicatorColor(-0x4a4a4b)
        progressView!!.setIndicatorId(ProgressStyle.BallSpinFadeLoader)
        progressCon!!.setView(progressView)

        addView(progressCon)

        footView = LayoutInflater.from(context).inflate(R.layout.item_xrecyclerview_bottom, null)
        mText = footView!!.findViewById<View>(R.id.tv_tip) as TextView
        mText!!.text = context.getString(R.string.listview_loading)
        v_left = footView!!.findViewById<View>(R.id.v_left) as View
        v_right = footView!!.findViewById<View>(R.id.v_right) as View
        v_left!!.visibility = View.GONE
        v_right!!.visibility = View.GONE

        if (loadingHint == null || loadingHint == "") {
            loadingHint = context.getText(R.string.listview_loading) as String
        }
        if (noMoreHint == null || noMoreHint == "") {
            noMoreHint = context.getText(R.string.nomore_loading) as String
        }
        if (loadingDoneHint == null || loadingDoneHint == "") {
            loadingDoneHint = context.getText(R.string.loading_done) as String
        }

        addView(footView)
    }

    fun setProgressStyle(style: Int) {
        if (style == ProgressStyle.SysProgress) {
            progressCon!!.setView(ProgressBar(context, null, android.R.attr.progressBarStyle))
        } else {
            progressView = AVLoadingIndicatorView(this.context)
            progressView!!.setIndicatorColor(-0x4a4a4b)
            progressView!!.setIndicatorId(style)
            progressCon!!.setView(progressView)
        }
    }

    fun setState(state: Int) {
        when (state) {
            STATE_LOADING -> {
                progressCon!!.visibility = View.VISIBLE
                mText!!.text = loadingHint
                v_left!!.visibility = View.GONE
                v_right!!.visibility = View.GONE
                this.visibility = View.VISIBLE
            }
            STATE_COMPLETE -> {
                mText!!.text = loadingDoneHint
                v_left!!.visibility = View.GONE
                v_right!!.visibility = View.GONE
                this.visibility = View.GONE
            }
            STATE_NOMORE -> {
                mText!!.text = noMoreHint
                progressCon!!.visibility = View.GONE
                v_left!!.visibility = View.VISIBLE
                v_right!!.visibility = View.VISIBLE
                this.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        val STATE_LOADING = 0
        val STATE_COMPLETE = 1
        val STATE_NOMORE = 2
    }
}
