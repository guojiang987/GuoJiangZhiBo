package com.rose.guojiangzhibo.fragment.onefragments;


import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.rock.teachlibrary.ImageLoader;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.OneFragmentData;
import com.rose.guojiangzhibo.dialog.CustomProgressDialog;
import com.rose.guojiangzhibo.fragment.OneFragment;
import com.rose.guojiangzhibo.helper.OkHttp3CookieHelper;
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
    private PullToRefreshListView listview_hotfragment;
    private BannerView viewpager_hotfragment;
    private List<OneFragmentData> list;

    public HotFragment() {
        // Required empty public constructor
    }

    private void initView() {
        imageViewsList = new ArrayList<>();
        list = new ArrayList<>();
        listview_hotfragment = (PullToRefreshListView) inflate.findViewById(R.id.listview_hotfragment);
        viewpager_hotfragment = (BannerView) inflate.findViewById(R.id.viewpager_hotfragment);
        ImageLoader.init(getActivity());
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
    }

    private void getData() {
        CustomProgressDialog.showProgressDialog(getContext(), null);
        OkHttp3CookieHelper cookieHelper = new OkHttp3CookieHelper();
        cookieHelper.setCookie(UrlConfigOne.HOTDATA, "uid", "6579212");
        cookieHelper.setCookie(UrlConfigOne.HOTDATA, "PHPSESSID", "3gsl5u4cm6tlsk7m4jq2fufmd7");
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                cookieJar(cookieHelper.cookieJar()).build();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.cookieJar(new CookieJar() {
//            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
//
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                cookieStore.put(url.host(), cookies);
//
//                for(Cookie cookie:cookies){
//                    Log.i("------name",cookie.name());
//                    Log.i("------path",cookie.path());
////                    System.out.println("cookie Name:"+cookie.name());
////                    System.out.println("cookie Path:"+cookie.path());
//                }
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                List<Cookie> cookies = cookieStore.get(url.host());
//                return cookies != null ? cookies : new ArrayList<Cookie>();
//            }
//        });
//        okHttpClient = builder.build();
        FormBody formBody = new FormBody.Builder().
                add("platform", Tools.getPlatform()).
                add("deviceName", Tools.getDeviceName()).
                add("version", Tools.getVersion()).
                add("packageId", "0").
                add("androidVersion", Tools.getAndroidVersion()).
                add("channel", Tools.getChannel()).
                add("page", "0").
                add("status", "1").
                build();
        Request request = new Request.Builder().url(UrlConfigOne.HOTDATA).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
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
                            list.add(oneFragmentData);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                viewpager();
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
                Toast.makeText(getContext(), "数据请求失败", Toast.LENGTH_SHORT).show();
                CustomProgressDialog.Dissmiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
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
    //    private void addHeader() {
//        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_hot_header,null);
//        ViewPager viewPager = (ViewPager) header.findViewById(R.id.viewpager_hotfragment);
//        for (int i = 0; i < hotHeaderDataList.size()*2; i++) {
//            ImageView imageHeader=new ImageView(getActivity());
//            imageHeader.setScaleType(ImageView.ScaleType.FIT_XY);
//            //下载图片
//            ImageLoader.display(imageHeader,hotHeaderDataList.get(i%hotHeaderDataList.size()).getPic());
//            imageViewsList.add(imageHeader);
//        }
//        myOneHeaderAdapter=new MyOneHeaderAdapter(imageViewsList);
//        viewPager.setAdapter(myOneHeaderAdapter);
//        viewPager.setCurrentItem(Integer.MAX_VALUE/2);
//        listView.addHeaderView(header);
//    }

//    private void getData() {
//        //解析头部轮播图片的数据
//        HttpUtil.getStringAsync(UrlConfigOne.URL_HEADER, new HttpUtil.RequestCallBack() {
//            @Override
//            public void onFailure() {
//
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                try {
//                    JSONObject jsonObject =new JSONObject(result);
//                    JSONObject focusImages=jsonObject.optJSONObject("focusImages");
//                    JSONArray jsonArray = focusImages.optJSONArray("list");
//                    hotHeaderDataList=new ArrayList<HotHeaderData>();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject object=jsonArray.optJSONObject(i);
//                        String pic=object.optString("pic");
//                        HotHeaderData hotHeaderData=new HotHeaderData();
//                        hotHeaderData.setPic(pic);
//                        hotHeaderDataList.add(hotHeaderData);
//                    }
//                    addHeader();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//        //解析主要布局的数据(第一段数据)
//        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_HOTONE, new HttpUtil.RequestCallBack() {
//            @Override
//            public void onFailure() {
//
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                oneFragmentDatasList.addAll(OneFragmentDataJson.getListFromOneFragment(result));
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//        //第二段数据
//        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_HOTTWO, new HttpUtil.RequestCallBack() {
//            @Override
//            public void onFailure() {
//
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                oneFragmentDatasList.addAll(OneFragmentDataJson.getListFromOneFragment(result));
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//        //第三段数据
//        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_HOTTHREE, new HttpUtil.RequestCallBack() {
//            @Override
//            public void onFailure() {
//
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                oneFragmentDatasList.addAll(OneFragmentDataJson.getListFromOneFragment(result));
//                myOneListAdapter.notifyDataSetChanged();
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                        Intent intent=new Intent(getActivity(), OnePlayerActivity.class);
////                        intent.putExtra("url",oneFragmentDatasList.get(position).getVideoPlayUrl());
////                        startActivityForResult(intent,123);
//                        playVideo(oneFragmentDatasList.get(position).getVideoPlayUrl(),position);
//                    }
//                });
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//    }

//    private void playVideo(String source,int position){
////		String source = mSourceET.getText().toString();
////        String source="rtmp://rtmppull.efeizao.com/live/room_132896/chat";
//        if(source == null || source.equals("")){
//            /**
//             * 简单检测播放源的合法性,不合法不播放
//             */
//            Toast.makeText(getActivity(), "please input your video source", 500).show();
//
////            source = "http://devimages.apple.com/iphone/samples/bipbop/gear4/prog_index.m3u8";
//            Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
//            intent.setData(Uri.parse(source));
//            intent.putExtra("rid",oneFragmentDatasList.get(position).getRid());
////            intent.putExtra("headerPic",oneFragmentDatasList.get(position).getHeadPic());
//            startActivityForResult(intent,123);
//        }else{
//            Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
//            intent.putExtra("rid",oneFragmentDatasList.get(position).getRid());
//            intent.putExtra("headerPic",oneFragmentDatasList.get(position).getHeadPic());
//            intent.putExtra("nickname",oneFragmentDatasList.get(position).getNickname());
//            startActivityForResult(intent,123);
//        }
//    }


}
