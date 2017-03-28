package com.rose.guojiangzhibo.fragment.onefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rock.teachlibrary.http.HttpUtil;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.adapter.MyNewestAdapter;
import com.rose.guojiangzhibo.bean.OneFragmentData;
import com.rose.guojiangzhibo.jsondata.OneFragmentDataJson;
import com.rose.guojiangzhibo.urlconfig.UrlConfigOne;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends Fragment {


    private ListView listView;
    //所有数据集合
    private List<OneFragmentData> oneFragmentDatasList;
    private MyNewestAdapter myNewestAdapter;

    public NewestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_newest, container, false);
        initView(inflate);
        getData();
        return inflate;
    }

    private void initView(View inflate) {
        listView = (ListView) inflate.findViewById(R.id.listview_newestfragment);
        oneFragmentDatasList=new ArrayList<>();
        myNewestAdapter=new MyNewestAdapter(getActivity(),oneFragmentDatasList);
        listView.setAdapter(myNewestAdapter);
    }

    public void getData() {
        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_NEWEST, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
//                Log.i("-----------------",result);
                oneFragmentDatasList= OneFragmentDataJson.getListFromOneFragment(result);
                myNewestAdapter.notifyDataSetChanged();
//                Log.i("-----------------",oneFragmentDatasList.toString());
                myNewestAdapter=new MyNewestAdapter(getActivity(),oneFragmentDatasList);
                listView.setAdapter(myNewestAdapter);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
