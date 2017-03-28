package com.rose.guojiangzhibo.fragment.TwoFragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.CircleActivity;
import com.rose.guojiangzhibo.adapter.CircleAdapter;
import com.rose.guojiangzhibo.bean.CircleIListItem;
import com.rose.guojiangzhibo.bean.SelectionHeaderItem;
import com.rose.guojiangzhibo.urlconfig.ConfigURL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;


import org.json.JSONException;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends Fragment  {

    private List<CircleIListItem> list;

    private ListView listView;
    private CircleAdapter circleAdapter;
    private ArrayList<SelectionHeaderItem> selectionHeaderItems;

    private ImageOptions imageOptions;

    public CircleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        x.view().inject(getActivity());
        imageOptions = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.RGB_565)
                .setRadius(10)
                .setCrop(false)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .setSquare(true)
                .build();
        View view = inflater.inflate(R.layout.twofragment_circle, container, false);
        initView(view);
        getData();
        return view;
    }

    private void getData() {
        RequestParams requestParams = new RequestParams(ConfigURL.getCircleContent());
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.optJSONArray("data");
                    list = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        CircleIListItem circleIListItem = new CircleIListItem();
                        circleIListItem.getJSONObject(array.getJSONObject(i));
                        list.add(circleIListItem);
                    }
                    initlistAdapter();
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
        selectionHeaderItems = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {

            if (i == 4) {
                downData(ConfigURL.getCircle() + i, true);
            }else{
                downData(ConfigURL.getCircle() + i, false);
            }
        }
    }

    private void initHeader() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.circlelistheader, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.header_circlelist);
        for (int i = 0; i < selectionHeaderItems.size(); i++) {
            final SelectionHeaderItem selectionHeaderItem = selectionHeaderItems.get(i);
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.circleheader, null);
            item.setClickable(true);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),CircleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("circleitem",selectionHeaderItem);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });
            ImageView imageView = (ImageView) item.findViewById(R.id.circlelogo);
            x.image().bind(imageView, selectionHeaderItem.getLogo(), imageOptions);
            TextView name = (TextView) item.findViewById(R.id.name_cirlce);
            name.setText(selectionHeaderItem.getName());
            TextView count = (TextView) item.findViewById(R.id.count_circle);
            count.setText(selectionHeaderItem.getMemberTotal()+"人");

            linearLayout.addView(item);
        }
        listView.addHeaderView(view);

    }

    public void downData(String url, final boolean flag) {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject object = jsonObject.optJSONObject("data");
                    SelectionHeaderItem selectionHeaderItem = new SelectionHeaderItem();
                    selectionHeaderItem.getJSONObject(object);
                    selectionHeaderItems.add(selectionHeaderItem);

                    if (flag) {
                        initHeader();
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

    }

    private void initlistAdapter() {
        circleAdapter = new CircleAdapter(getActivity(), list);
        listView.setAdapter(circleAdapter);
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_circle);
    }


}
