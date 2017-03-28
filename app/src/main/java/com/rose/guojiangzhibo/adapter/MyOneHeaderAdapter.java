package com.rose.guojiangzhibo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by xdy on 2016/10/18.
 */

public class MyOneHeaderAdapter extends PagerAdapter {
    private List<ImageView> imageViewsList;

    public MyOneHeaderAdapter(List<ImageView> imageViewsList) {
        this.imageViewsList = imageViewsList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViewsList.get(position%imageViewsList.size()));
        return imageViewsList.get(position%imageViewsList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewsList.get(position%imageViewsList.size()));
    }
}
