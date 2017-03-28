package com.rose.guojiangzhibo.pulltorefresh;

import android.view.View;

/**
 * Interface that allows PullToRefreshBase to hijack the call to
 * AdapterView.setEmptyView()
 * 
 * @author chris
 */
public interface EmptyViewMethodAccessor {


	public void setEmptyViewInternal(View emptyView);


	public void setEmptyView(View emptyView);

}
