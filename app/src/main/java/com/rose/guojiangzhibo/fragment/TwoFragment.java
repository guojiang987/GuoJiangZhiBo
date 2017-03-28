package com.rose.guojiangzhibo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.SelectionHeaderItem;
import com.rose.guojiangzhibo.bean.SelectionItem;
import com.rose.guojiangzhibo.fragment.TwoFragments.CircleFragment;
import com.rose.guojiangzhibo.fragment.TwoFragments.SelectionFragment;
import com.rose.guojiangzhibo.urlconfig.ConfigURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {


    private View view;
    private TextView selection;
    private TextView circle;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private ArrayList<SelectionItem> selections;
    private SelectionFragment selectionFragment;
    private CircleFragment circleFragment;
    private ArrayList<SelectionHeaderItem> headers;
    private Context context;

    public TwoFragment(Context context) {
        this.context = context;

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_two, container, false);
        getListViewData();
        getHeaderData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        selectionFragment = new SelectionFragment();
        circleFragment = new CircleFragment();
        fragmentTransaction.add(R.id.contentframelayout_twofrag, selectionFragment, null);
        fragmentTransaction.show(selectionFragment);
        fragmentTransaction.add(R.id.contentframelayout_twofrag, circleFragment, null);
        fragmentTransaction.hide(circleFragment);
        fragmentTransaction.commit();

        selection = (TextView) view.findViewById(R.id.selectiontitle_twofragment);
        circle = (TextView) view.findViewById(R.id.circletitle_twofragment);
        selection.setTextColor(0xffffffff);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                selection.setTextColor(0xffbbbbbb);
                circle.setTextColor(0xffbbbbbb);
                switch (v.getId()) {
                    case R.id.selectiontitle_twofragment:
                        selection.setTextColor(0xffffffff);
                        fragmentTransaction.show(selectionFragment);
                        fragmentTransaction.hide(circleFragment);
                        break;
                    case R.id.circletitle_twofragment:
                        circle.setTextColor(0xffffffff);
                        fragmentTransaction.show(circleFragment);
                        fragmentTransaction.hide(selectionFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        };
        selection.setOnClickListener(onClickListener);
        circle.setOnClickListener(onClickListener);


    }


    public void getListViewData() {

        RequestParams requestParams = new RequestParams(ConfigURL.getSelcetion());
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    selections = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        SelectionItem item = new SelectionItem();
                        item.getJSONObject(jsonArray.getJSONObject(i));
                        selections.add(item);
                    }

                    initListView();

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

    private void initHeader(ArrayList<SelectionHeaderItem> headers) {
        selectionFragment.setHeader(headers);
    }

    private void initListView() {
        selectionFragment.setList(selections);
    }

    public void getHeaderData() {
        final RequestParams headerString = new RequestParams(ConfigURL.getSelectionHeader());
        x.http().get(headerString, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray data = jsonObject.optJSONArray("data");
                    headers = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        SelectionHeaderItem selectionHeaderItem = new SelectionHeaderItem();
                        JSONObject object = data.optJSONObject(i);
                        selectionHeaderItem.getJSONObject(object);
                        headers.add(selectionHeaderItem);
                    }
                    initHeader(headers);
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
