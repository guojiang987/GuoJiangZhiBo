package com.rose.guojiangzhibo.fragment.onefragments.personal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.layout.MyListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FanFragment extends Fragment {


    private MyListView listView;


    public FanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fan, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        listView = (MyListView) inflate.findViewById(R.id.listview_fanfragment);

    }

}
