package com.rose.guojiangzhibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xdy on 2016/10/20.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    //tab上面显示的文字的集合
    private List<String> tabList;
    private List<Fragment> fragmentsList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<String> tabList, List<Fragment> fragmentsList) {
        super(fm);
        this.tabList=tabList;
        this.fragmentsList=fragmentsList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
