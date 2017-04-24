package com.rose.guojiangzhibo.fragment.onefragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.rock.teachlibrary.ImageLoader;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.VideoViewPlayingActivity;
import com.rose.guojiangzhibo.adapter.MyOneListAdapter;
import com.rose.guojiangzhibo.bean.OneFragmentData;
import com.rose.guojiangzhibo.dialog.CustomProgressDialog;
import com.rose.guojiangzhibo.fragment.OneFragment;
import com.rose.guojiangzhibo.helper.OkHttp3CookieHelper;
import com.rose.guojiangzhibo.layout.MyPullToRefreshListView;
import com.rose.guojiangzhibo.pulltorefresh.PullToRefreshBase;
import com.rose.guojiangzhibo.pulltorefresh.PullToRefreshListView;
import com.rose.guojiangzhibo.urlconfig.UrlConfigOne;
import com.rose.guojiangzhibo.util.Tools;
import com.rose.guojiangzhibo.viewpager.BannerView;
import com.rose.guojiangzhibo.viewpager.ImagePageAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

    private View inflate;
    private List<String> imageViewsList;//头部图片的集合
    private MyPullToRefreshListView listview_hotfragment;
    private BannerView viewpager_hotfragment;
    private List<OneFragmentData> list;
    private MyOneListAdapter myOneListAdapter;
    private int page = 0;
    private ListView listView;

    public HotFragment() {
        // Required empty public constructor
    }

    private void initView() {
        imageViewsList = new ArrayList<>();
        list = new ArrayList<>();
        listview_hotfragment = (MyPullToRefreshListView) inflate.findViewById(R.id.listview_hotfragment);
        viewpager_hotfragment = (BannerView) inflate.findViewById(R.id.viewpager_hotfragment);
        listView = listview_hotfragment.getRefreshableView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        inflate = inflater.inflate(R.layout.fragment_hot, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getHeader();
        getData();
        listview_hotfragment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        myOneListAdapter = new MyOneListAdapter(getContext(), list);
        listView.setAdapter(myOneListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OneFragmentData oneFragmentData = list.get(i);
                playVideo(oneFragmentData);
            }
        });
    }

    private void getData() {
        CustomProgressDialog.showProgressDialog(getContext(), null);
        OkHttp3CookieHelper cookieHelper = new OkHttp3CookieHelper();
        cookieHelper.setCookie(UrlConfigOne.HOTDATA, "uid", "6579212");
        cookieHelper.setCookie(UrlConfigOne.HOTDATA, "PHPSESSID", "3gsl5u4cm6tlsk7m4jq2fufmd7");
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                cookieJar(cookieHelper.cookieJar()).build();
        FormBody formBody = new FormBody.Builder().
                add("platform", Tools.getPlatform()).
                add("deviceName", Tools.getDeviceName()).
                add("version", Tools.getVersion()).
                add("packageId", "0").
                add("androidVersion", Tools.getAndroidVersion()).
                add("channel", Tools.getChannel()).
                add("page", page + "").
                add("status", "1").
                build();
        Request request = new Request.Builder().url(UrlConfigOne.HOTDATA).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                CustomProgressDialog.Dissmiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Tools.d(json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.optInt("errno") == 0) {
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            OneFragmentData oneFragmentData = new OneFragmentData();
                            JSONObject object = jsonArray.getJSONObject(i);
                            oneFragmentData.setRid(object.getString("rid"));
                            oneFragmentData.setId(object.getString("id"));
                            oneFragmentData.setHeadPic(object.optString("headPic"));
                            oneFragmentData.setIsPlaying(object.optBoolean("isPlaying"));
                            oneFragmentData.setOnlineNum(object.getInt("onlineNum"));
                            oneFragmentData.setAnnouncement(object.getString("announcement"));
                            oneFragmentData.setCity(object.getString("city"));
                            oneFragmentData.setVideoPlayUrl(object.getString("videoPlayUrl"));
                            oneFragmentData.setNickname(object.getString("nickname"));
                            list.add(oneFragmentData);
                        }
                        Message message = handler.obtainMessage();
                        message.what = 2;
                        handler.sendMessage(message);
                    } else {
                        Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    CustomProgressDialog.Dissmiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void refreshData() {
        page++;
        getData();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                viewpager();
            } else if (msg.what == 2) {
                if (myOneListAdapter != null) {
                    myOneListAdapter.notifyDataSetChanged();
                } else {
                    myOneListAdapter = new MyOneListAdapter(getContext(), list);
                    myOneListAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    public void getHeader() {
        OkHttpClient okHttpClient = new OkHttpClient();
        CustomProgressDialog.showProgressDialog(getContext(), null);
        FormBody formBody = new FormBody.Builder().
                add("platform", Tools.getPlatform()).
                add("deviceName", Tools.getDeviceName()).
                add("version", Tools.getVersion()).
                add("packageId", "0").
                add("androidVersion", Tools.getAndroidVersion()).
                add("channel", Tools.getChannel()).
                build();
        Request request = new Request.Builder().url(UrlConfigOne.FOCUSACTIVITY).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                CustomProgressDialog.Dissmiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Tools.d(json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("errno") == 0) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            imageViewsList.add(object.getString("pic"));
                        }
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        handler.sendMessage(message);
                    } else {
                        Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    CustomProgressDialog.Dissmiss();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private ImagePageAdapter imagePageAdapter;

    public void viewpager() {
        imagePageAdapter = new ImagePageAdapter(getContext(), imageViewsList.size());
        imagePageAdapter.addImagePageAdapterListener(new ImagePageAdapter.ImagePageAdapterListener() {
            @Override
            public void dispalyImage(ImageView banner, int position) {
                Picasso.with(getContext()).load(imageViewsList.get(position)).into(banner);
                onTouchViewPager(banner, position);
            }
        });
        viewpager_hotfragment.setBannerAdapter(imagePageAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewpager_hotfragment.startAutoScroll();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewpager_hotfragment.stopAutoScroll();
    }

    public void onTouchViewPager(View view, final int position) {
        view.setOnTouchListener(new View.OnTouchListener() {
            private long downTime;
            private int downX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = (int) event.getX();
                        downTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        if ((System.currentTimeMillis() - downTime < 500) && (Math.abs(downX - (int) event.getX()) < 30)) {
                            //TODO 添加参数
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return true;
            }
        });
    }

    private void playVideo(OneFragmentData oneFragmentData) {
        String source = oneFragmentData.getVideoPlayUrl();
        if (source == null || source.equals("")) {
            /**
             * 简单检测播放源的合法性,不合法不播放
             */
            Toast.makeText(getActivity(), "please input your video source", 500).show();

//            source = "http://devimages.apple.com/iphone/samples/bipbop/gear4/prog_index.m3u8";
            Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
            intent.setData(Uri.parse(source));
            intent.putExtra("rid", oneFragmentData.getRid());
//            intent.putExtra("headerPic",oneFragmentDatasList.get(position).getHeadPic());
            startActivityForResult(intent, 123);
        } else {
            Tools.d("数据----" + oneFragmentData.getRid() + "--" + oneFragmentData.getHeadPic() + "" + oneFragmentData.getNickname());
            Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
            intent.putExtra("rid", oneFragmentData.getRid());
            intent.putExtra("headerPic", oneFragmentData.getHeadPic());
            intent.putExtra("nickname", oneFragmentData.getNickname());
            startActivityForResult(intent, 123);
        }
    }


}
