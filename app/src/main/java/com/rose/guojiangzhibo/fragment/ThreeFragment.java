package com.rose.guojiangzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.fragment.ThreeFragments.IntrestFragment;
import com.rose.guojiangzhibo.fragment.ThreeFragments.NoIntreastFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {
    private TextView intrest;
    private TextView noIntrest;
    private TextView ingoreAll;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private IntrestFragment intrestFragment;
    private NoIntreastFragment noIntreastFragment;


    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_three, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        intrest = (TextView) inflate.findViewById(R.id.intrest_threefrag);
        noIntrest = (TextView) inflate.findViewById(R.id.nointrest_threefrag);
        ingoreAll = (TextView) inflate.findViewById(R.id.ingoreAll_threefrag);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        intrestFragment = new IntrestFragment();
        noIntreastFragment = new NoIntreastFragment();

        //添加fragment
        fragmentTransaction.add(R.id.framelayut_threefrag, intrestFragment, null);
        fragmentTransaction.add(R.id.framelayut_threefrag, noIntreastFragment, null);
        fragmentTransaction.show(intrestFragment);
        fragmentTransaction.hide(noIntreastFragment);
        fragmentTransaction.commit();

        //设置 已关注 为白色
        intrest.setTextColor(0xffffffff);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先都变为灰色
                fragmentTransaction = fragmentManager.beginTransaction();
                intrest.setTextColor(0xffbbbbbb);
                noIntrest.setTextColor(0xffbbbbbb);
                switch (v.getId()) {
                    case R.id.intrest_threefrag:
                        intrest.setTextColor(0xffffffff);
                        fragmentTransaction.show(intrestFragment);
                        fragmentTransaction.hide(noIntreastFragment);
                        break;
                    case R.id.nointrest_threefrag:
                        noIntrest.setTextColor(0xffffffff);
                        fragmentTransaction.show(noIntreastFragment);
                        fragmentTransaction.hide(intrestFragment);
                        break;
                }
                fragmentTransaction.commit();

            }
        };
        intrest.setOnClickListener(onClickListener);
        noIntrest.setOnClickListener(onClickListener);
    }

}
