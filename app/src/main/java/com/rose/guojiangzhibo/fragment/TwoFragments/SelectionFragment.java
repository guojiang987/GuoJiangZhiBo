package com.rose.guojiangzhibo.fragment.TwoFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.CircleActivity;
import com.rose.guojiangzhibo.adapter.SelectionAdapter;
import com.rose.guojiangzhibo.bean.SelectionHeaderItem;
import com.rose.guojiangzhibo.bean.SelectionItem;
import com.rose.guojiangzhibo.pulltorefresh.PullToRefreshBase;
import com.rose.guojiangzhibo.pulltorefresh.PullToRefreshListView;
import com.rose.guojiangzhibo.urlconfig.ConfigURL;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectionFragment extends Fragment {

    private int page = 1;

    private List<SelectionItem> selectionItems;
    private ListView listView;
    private List<List<SelectionItem>> list = null;
    private SelectionAdapter selectionAdapter;
    private LinearLayout header;
    private List<SelectionHeaderItem> headers;
    PullToRefreshListView pullToRefreshListView;
    public SelectionFragment() {

    }


    public void setList(List<SelectionItem> selectionItems) {


        this.selectionItems = selectionItems;
        list = new ArrayList<>();
        List<SelectionItem> l = null;
        l = new ArrayList<>();
        for (int i = 0; i < selectionItems.size(); i++) {
            if (i % 2 == 0 && i != 0) {
                list.add(l);
                l = new ArrayList<>();
            }
            SelectionItem selectionItem = selectionItems.get(i);
            l.add(selectionItem);
        }
        selectionAdapter.setList(list);
    }

    public void setHeader(List<SelectionHeaderItem> headers) {
        this.headers = headers;

        if (header != null) {
            for (int i = 0; i < headers.size(); i++) {

                View view = LayoutInflater.from(getActivity()).inflate(R.layout.twofragment_headeritem, null);
                view.setClickable(true);
                final SelectionHeaderItem finalSelectionHeaderItem = headers.get(i);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),CircleActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("circleitem", finalSelectionHeaderItem);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
                ImageView img = (ImageView) view.findViewById(R.id.img1_selectionheader);
                x.image().bind(img, headers.get(i).getLogo());
                TextView text = (TextView) view.findViewById(R.id.text1_selectionheader);
                text.setText(headers.get(i).getName());
                header.addView(view);
            }
        }
    }
    public void addfooter(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.listviewmore,null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addlist();
            }
        });
        listView.addFooterView(view);
    }

    private void addlist() {
        String url = "http://app.guojiang.tv/groupPost/getNiceposts?page="+(page++)+"&limit=40";
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    selectionItems = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        SelectionItem item = new SelectionItem();
                        item.getJSONObject(jsonArray.getJSONObject(i));
                        selectionItems.add(item);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });

        List<SelectionItem> l = new ArrayList<>();
        for (int i = 0; i < selectionItems.size(); i++) {
            if (i % 2 == 0 && i != 0) {
                list.add(l);
                l = new ArrayList<>();
            }
            SelectionItem selectionItem = selectionItems.get(i);
            l.add(selectionItem);
        }

        Log.i("size",list.size()+"");
        selectionAdapter.setList(list);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.twofragment_selection, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

         pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.listview_selection);
        listView =pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getListViewData();
            }
        });
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.twofragment_selectionlistviewheader, null);
        header = (LinearLayout) view1.findViewById(R.id.linearlayout_header);
        listView.addHeaderView(view1);
        addfooter();
        selectionAdapter = new SelectionAdapter(getActivity());
        listView.setAdapter(selectionAdapter);


    }


    public void getListViewData() {

        RequestParams requestParams = new RequestParams(ConfigURL.getSelcetion());
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    selectionItems = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        SelectionItem item = new SelectionItem();
                        item.getJSONObject(jsonArray.getJSONObject(i));
                        selectionItems.add(item);
                    }
                    pullToRefreshListView.onRefreshComplete();
                    setList(selectionItems);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });

    }
}
