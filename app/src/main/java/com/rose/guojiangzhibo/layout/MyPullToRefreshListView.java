package com.rose.guojiangzhibo.layout;

import android.content.Context;
import android.util.AttributeSet;

import com.rose.guojiangzhibo.pulltorefresh.PullToRefreshListView;

/**
 * Created by xiedongyan on 2017/4/24.
 */
public class MyPullToRefreshListView extends PullToRefreshListView {
    public MyPullToRefreshListView(Context context) {
        super(context);
    }

    public MyPullToRefreshListView(Context context, int mode) {
        super(context, mode);
    }

    public MyPullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
