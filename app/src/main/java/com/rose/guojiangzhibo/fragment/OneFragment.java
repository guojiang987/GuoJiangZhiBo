package com.rose.guojiangzhibo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.SearchActivity;
import com.rose.guojiangzhibo.activity.ListActivity;
import com.rose.guojiangzhibo.fragment.onefragments.AttentionFragment;
import com.rose.guojiangzhibo.fragment.onefragments.HotFragment;
import com.rose.guojiangzhibo.fragment.onefragments.NewestFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    private TextView textOne;
    private TextView textTwo;
    private TextView textThree;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransactionOne,fragmentTransactionInit,fragmentTransactionTwo,fragmentTransactionThree;
    private HotFragment hotFragment;
    private AttentionFragment attentionFragment;
    private NewestFragment newestFragment;

    private ImageView imageSearch;
    private ImageView imageList;


    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
        initView(inflate);
        setListener();
        fragmentTransactionInit=fragmentManager.beginTransaction();
        if(hotFragment!=null){
            fragmentTransactionInit.show(hotFragment);
        }else {
            hotFragment=new HotFragment();
            fragmentTransactionInit.add(R.id.framelayut_onefragment,hotFragment);
        }
        fragmentTransactionInit.commit();
        return inflate;
    }

    private void setListener() {


        textOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textOne.setTextColor(0xffdddddd);
                textTwo.setTextColor(0xffdddddd);
                textThree.setTextColor(0xffdddddd);
                fragmentTransactionOne=fragmentManager.beginTransaction();
                hideFragment(fragmentTransactionOne);

                ((TextView)v).setTextColor(0xffffffff);
                if(attentionFragment!=null){
                    fragmentTransactionOne.show(attentionFragment);
                }else {
                    attentionFragment=new AttentionFragment();
                    fragmentTransactionOne.add(R.id.framelayut_onefragment,attentionFragment);
                }
                fragmentTransactionOne.commit();
            }
        });
        textTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textOne.setTextColor(0xffdddddd);
                textTwo.setTextColor(0xffdddddd);
                textThree.setTextColor(0xffdddddd);
                ((TextView)v).setTextColor(0xffffffff);

                fragmentTransactionTwo=fragmentManager.beginTransaction();
                hideFragment(fragmentTransactionTwo);
                if(hotFragment!=null){
                    fragmentTransactionTwo.show(hotFragment);
                }else {
                    hotFragment=new HotFragment();
                    fragmentTransactionTwo.add(R.id.framelayut_onefragment,hotFragment);
                }
                fragmentTransactionTwo.commit();
            }

        });
        textThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textOne.setTextColor(0xffdddddd);
                textTwo.setTextColor(0xffdddddd);
                textThree.setTextColor(0xffdddddd);
                ((TextView)v).setTextColor(0xffffffff);

                fragmentTransactionThree=fragmentManager.beginTransaction();
                hideFragment(fragmentTransactionThree);
                if(newestFragment!=null){
                    fragmentTransactionThree.show(newestFragment);
                }else {
                    newestFragment=new NewestFragment();
                    fragmentTransactionThree.add(R.id.framelayut_onefragment,newestFragment);
                }
                fragmentTransactionThree.commit();
            }
        });
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        imageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    private void initView(View inflate) {
        textOne = (TextView) inflate.findViewById(R.id.text_onefragment_one);
        textTwo = (TextView) inflate.findViewById(R.id.text_onefragment_two);
        textThree = (TextView) inflate.findViewById(R.id.text_onefragment_three);
        imageSearch = (ImageView) inflate.findViewById(R.id.image_search);
        imageList = (ImageView) inflate.findViewById(R.id.image_list);

        textOne.setTextColor(0xffdddddd);
        textTwo.setTextColor(0xffffffff);
        textThree.setTextColor(0xffdddddd);
        fragmentManager=getFragmentManager();
    }
    public void hideFragment(FragmentTransaction fragmentTransaction){
        if(hotFragment!=null){
            fragmentTransaction.hide(hotFragment);
        }
        if(attentionFragment!=null){
            fragmentTransaction.hide(attentionFragment);
        }
        if(newestFragment!=null){
            fragmentTransaction.hide(newestFragment);
        }
    }
}
