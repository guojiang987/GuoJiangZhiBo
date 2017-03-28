package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rose.guojiangzhibo.CircleBitmapTool;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.adapter.CircleAdapter;
import com.rose.guojiangzhibo.bean.CircleIListItem;
import com.rose.guojiangzhibo.bean.SelectionHeaderItem;
import com.rose.guojiangzhibo.urlconfig.ConfigURL;

import java.util.ArrayList;



public class CircleActivity extends AppCompatActivity implements View.OnClickListener {

    private SelectionHeaderItem selectionHeaderItem;
    private ListView listView;
    private ArrayList<CircleIListItem> list;

    private ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        imageOptions = new ImageOptions.Builder()
                .setCrop(true)
                .setConfig(Bitmap.Config.RGB_565)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setSquare(false)
                .build();
        getData();
        downData();
        initView();
    }

    private void downData() {
        RequestParams requestParams =  new RequestParams(ConfigURL.getGroup()+selectionHeaderItem.getId());
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.optJSONArray("data");
                    list = new ArrayList<>();
                    for(int i = 0;i<array.length();i++){
                        CircleIListItem circleIListItem = new CircleIListItem();
                        circleIListItem.getJSONObject(array.optJSONObject(i));
                        list.add(circleIListItem);
                    }
                    setListView();
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

    private void setListView() {

        CircleAdapter circleAdapter = new CircleAdapter(this,list);
        listView.setAdapter(circleAdapter);
    }

    private void initView() {
         listView = (ListView) findViewById(R.id.circle_circle);
        View header = LayoutInflater.from(this).inflate(R.layout.circlecontentheader, null);
        ImageButton imgreturn = (ImageButton) header.findViewById(R.id.imgreturn);
        imgreturn.setOnClickListener(this);
        ImageButton getmore = (ImageButton) header.findViewById(R.id.imgmore);
        getmore.setOnClickListener(this);

        if (!selectionHeaderItem.getBackground().equals("")) {
            ImageView bg = (ImageView) header.findViewById(R.id.bg_circle);
            x.image().bind(bg, selectionHeaderItem.getBackground(), imageOptions);
        }
        TextView circlename = (TextView) header.findViewById(R.id.circlename);
        circlename.setText(selectionHeaderItem.getName());
        TextView nikename = (TextView) header.findViewById(R.id.nikename);
        nikename.setText("圈主   "+selectionHeaderItem.getNickname());
        TextView detailcircle = (TextView) header.findViewById(R.id.detailcircle);
        detailcircle.setText(selectionHeaderItem.getDetail());
        TextView popularitycount = (TextView) header.findViewById(R.id.popularitycount);
        popularitycount.setText(selectionHeaderItem.getHot());
        TextView invitationcount = (TextView) header.findViewById(R.id.invitationcount);
        invitationcount.setText(selectionHeaderItem.getPostCount());
        TextView fans = (TextView) header.findViewById(R.id.fanscount);
        fans.setText(selectionHeaderItem.getMemberTotal());
        final ImageView userimg = (ImageView) header.findViewById(R.id.userheaderimg);
        x.image().bind(userimg, selectionHeaderItem.getLogo(), new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Bitmap img = CircleBitmapTool.toRoundBitmap(bitmap);
                userimg.setImageBitmap(img);

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
        listView.addHeaderView(header);

    }

    public void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        selectionHeaderItem = (SelectionHeaderItem) bundle.getSerializable("circleitem");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgreturn:
                finish();
                break;
        }
    }
}
