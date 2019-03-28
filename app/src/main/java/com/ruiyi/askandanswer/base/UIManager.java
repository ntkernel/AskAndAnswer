package com.ruiyi.askandanswer.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ruiyi.askandanswer.R;

public class UIManager {

    private static UIManager INSTANCE;
    private Context mBaseContext;
    private int              mHeight;
    private int              mWidth;
    private int              mDensity;

    private UIManager() {

    }

    public synchronized static UIManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UIManager();
        }
        return INSTANCE;
    }

    // uimanager  windowmanger,跨进程,收广播的时候,对话框依赖Activity
    public Context getBaseContext() {
        return mBaseContext;
    }

    public void setBaseContext(Context context) {
        mBaseContext = context;
    }

    /**
     * 获取手机高
     *
     * @return
     */
    public int getHeight() {
        return mHeight;
    }

    /**
     * 保存手机高
     *
     * @param height
     */
    public void setHeight(int height) {
        this.mHeight = height;
    }

    /**
     * 获取手机宽
     *
     * @return
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * 保存手机宽
     *
     * @param width
     */
    public void setWidth(int width) {
        this.mWidth = width;
    }

    /**
     * 获取手机密度
     *
     * @return
     */
    public int getDensity() {
        return mDensity;
    }

    /**
     * 保存手机密度
     *
     * @param density
     */
    public void setDensity(int density) {
        this.mDensity = density;
    }


    /**
     * 显示加载框
     *
     * @param activity
     */
    public void showLoadView(Activity activity) {
        if (activity != null) {
            ViewStub loadStub = ((ViewStub) activity.findViewById(R.id.viewsub_loading));
            if (loadStub != null) {
                loadStub.inflate();
            }
            else {
                ProgressBar load = (ProgressBar) activity.findViewById(R.id.loading_progressbar);
                if (load != null) {
                    load.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * 隐藏加载框
     *
     * @param activity
     */
    public void hideLoadView(Activity activity) {
        if (activity != null) {
            ViewStub loadStub = ((ViewStub) activity.findViewById(R.id.viewsub_loading));
            if (loadStub != null) {
                loadStub.inflate().setVisibility(View.GONE);
            }
            else {
                ProgressBar load = (ProgressBar) activity.findViewById(R.id.loading_progressbar);
                if (load != null) {
                    load.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 显示加载框
     *
     * @param view
     */
    public void showLoadView(View view) {
        if (view != null) {
            ViewStub loadStub = ((ViewStub) view.findViewById(R.id.viewsub_loading));
            if (loadStub != null) {
                loadStub.inflate();
            }
            else {
                ProgressBar load = (ProgressBar) view.findViewById(R.id.loading_progressbar);
                if (load != null) {
                    load.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * 隐藏加载框
     *
     * @param view
     */
    public void hideLoadView(View view) {
        if (view != null) {
            ViewStub loadStub = ((ViewStub) view.findViewById(R.id.viewsub_loading));
            if (loadStub != null) {
                loadStub.inflate().setVisibility(View.GONE);
            }
            else {
                ProgressBar load = (ProgressBar) view.findViewById(R.id.loading_progressbar);
                if (load != null) {
                    load.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 显示错误提示框
     *
     * @param activity
     */
    public View showErrorView(Activity activity, String msg) {
        View error = null;
        if (activity != null) {
            ViewStub errorStub = ((ViewStub) activity.findViewById(R.id.viewsub_error));
            if (errorStub != null) {
                error = errorStub.inflate();
                TextView textView = (TextView) error.findViewById(R.id.error_message);
//                textView.setText(msg);
            }
            else {
                TextView textView = (TextView) activity.findViewById(R.id.error_message);
                if (textView != null) {
//                    textView.setText(msg);
                    textView.setVisibility(View.VISIBLE);
                    error = textView;
                }
            }
        }
        return error;
    }

    /**
     * 隐藏错误提示框
     *
     * @param activity
     */
    public View hideErrorView(Activity activity) {
        View error = null;
        if (activity != null) {
            ViewStub errorStub = ((ViewStub) activity.findViewById(R.id.viewsub_error));
            if (errorStub != null) {
                error = errorStub.inflate();
                error.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) activity.findViewById(R.id.error_message);
                if (textView != null) {
                    textView.setVisibility(View.GONE);
                    error = textView;
                }
            }
        }
        return error;
    }

    /**
     * 显示错误提示框
     *
     * @param view
     */
    public View showErrorView(View view, String msg) {
        View error = null;
        if (view != null) {
            ViewStub errorStub = (ViewStub) view.findViewById(R.id.viewsub_error);
            if (errorStub != null) {
                error = errorStub.inflate();
                TextView textView = (TextView) error.findViewById(R.id.error_message);
//                textView.setText(msg);
            }
            else {
                TextView textView = (TextView) view.findViewById(R.id.error_message);
                if (textView != null) {
//                    textView.setText(msg);
                    textView.setVisibility(View.VISIBLE);
                    error = textView;
                }
            }
        }
        return error;
    }

    /**
     * 隐藏错误提示框
     *
     * @param view
     */
    public View hideErrorView(View view) {
        View error = null;
        if (view != null) {
            ViewStub errorStub = ((ViewStub) view.findViewById(R.id.viewsub_error));
            if (errorStub != null) {
                error = errorStub.inflate();
                error.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) view.findViewById(R.id.error_message);
                if (textView != null) {
                    textView.setVisibility(View.GONE);
                    error = textView;
                }
            }
        }
        return error;
    }

    /**
     * 显示空数据提示
     *
     * @param msg
     */
    public View showEmptyView(Activity activity, String msg) {
        View empty = null;
        if (activity != null) {
            ViewStub errorStub = (ViewStub) activity.findViewById(R.id.viewsub_empty);
            if (errorStub != null) {
                empty = errorStub.inflate();
                TextView textView = (TextView) empty.findViewById(R.id.empty_message);
                textView.setText(msg);
            }
            else {
                TextView textView = (TextView) activity.findViewById(R.id.empty_message);
                if (textView != null) {
                    textView.setText(msg);
                    empty = textView;
                    empty.setVisibility(View.VISIBLE);
                }
            }
        }
        return empty;
    }

    /**
     * 显示空数据提示
     *
     * @param view
     */
    public View showEmptyView(View view, String msg) {
        View empty = null;
        if (view != null) {
            ViewStub errorStub = (ViewStub) view.findViewById(R.id.viewsub_empty);
            if (errorStub != null) {
                empty = errorStub.inflate();
                TextView textView = (TextView) empty.findViewById(R.id.empty_message);
                textView.setText(msg);
            }
            else {
                TextView textView = (TextView) view.findViewById(R.id.empty_message);
                if (textView != null) {
                    textView.setText(msg);
                    empty = textView;
                    empty.setVisibility(View.VISIBLE);
                }
            }
        }
        return empty;
    }

    /**
     * 指定空view
     *
     * @param view
     */
    public View showEmptyView(View view) {
        return view;
    }


    /**
     * 隐藏空数据提示
     *
     * @param activity
     */
    public View hideEmptyView(Activity activity) {
        View empty = null;
        if (activity != null) {
            ViewStub errorStub = (ViewStub) activity.findViewById(R.id.viewsub_empty);
            if (errorStub != null) {
                empty = errorStub.inflate();
                empty.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) activity.findViewById(R.id.empty_message);
                if (textView != null) {
                    empty = textView;
                    empty.setVisibility(View.GONE);
                }
            }
        }
        return empty;
    }

    /**
     * 隐藏空数据提示
     *
     * @param view
     */
    public View hideEmptyView(View view) {
        View empty = null;
        if (view != null) {
            ViewStub errorStub = (ViewStub) view.findViewById(R.id.viewsub_empty);
            if (errorStub != null) {
                empty = errorStub.inflate();
                empty.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) view.findViewById(R.id.empty_message);
                if (textView != null) {
                    empty = textView;
                    empty.setVisibility(View.GONE);
                }
            }
        }
        return empty;
    }


    /**
     * 清空 INSTANCE
     */
    public void close() {
        setHeight(0);
        setWidth(0);
        setDensity(0);
    }

}
