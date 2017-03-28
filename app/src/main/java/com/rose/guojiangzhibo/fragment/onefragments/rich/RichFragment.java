package com.rose.guojiangzhibo.fragment.onefragments.rich;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RichFragment extends Fragment {

    private TabLayout tabLayout;
    private List<String> tabsList;
    private ViewPager viewPager;
    private List<Fragment> fragmentsList;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public RichFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_rich, container, false);
        initView(inflate);
        return inflate;
    }
    private void initView(View inflate) {
        tabLayout = (TabLayout) inflate.findViewById(R.id.tablayout_richfragment);
        viewPager= (ViewPager) inflate.findViewById(R.id.viewpager_richfragment);
        tabsList=new ArrayList<>();
        fragmentsList=new ArrayList<>();
        addTabs();
    }

    private void addTabs() {
        tabsList.add("日榜");
        tabsList.add("周榜");
        tabsList.add("总榜");
        //添加tab到tablayout里面
        for (int i = 0; i < tabsList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabsList.get(i)));
        }
        initViewPager();
        //设置tablayout与viewPager联动
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViewPager() {
        fragmentsList.add(new RichDayFragment());
        fragmentsList.add(new RichWeekFragment());
        fragmentsList.add(new RichAllFragment());
        myFragmentPagerAdapter=new MyFragmentPagerAdapter(getFragmentManager(),tabsList,fragmentsList);
        viewPager.setAdapter(myFragmentPagerAdapter);
    }

}
