package com.rose.guojiangzhibo.fragment.onefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.rock.teachlibrary.ImageLoader;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.pulltorefresh.PullToRefreshListView;
import com.rose.guojiangzhibo.urlconfig.UrlConfigOne;
import com.rose.guojiangzhibo.util.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

    private List<String> imageViewsList;//头部图片的集合
    private PullToRefreshListView listview_hotfragment;
    private ViewPager viewpager_hotfragment;
    private OkHttpClient okHttpClient = new OkHttpClient();

    public HotFragment() {
        // Required empty public constructor
    }

    private void initView(View inflate) {
        listview_hotfragment = (PullToRefreshListView) inflate.findViewById(R.id.listview_hotfragment);
        viewpager_hotfragment = (ViewPager) inflate.findViewById(R.id.viewpager_hotfragment);
        ImageLoader.init(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_hot, container, false);
        initView(inflate);
        getHeader(inflate);
        return inflate;
    }

    public void getHeader(View inflate) {
        ViewPager viewPager = (ViewPager) inflate.findViewById(R.id.viewpager_hotfragment);
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

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("errno") == 0) {
                        imageViewsList.add(jsonObject.getString("pic"));
                        for (int i = 0; i < imageViewsList.size(); i++) {

                        }
                    } else {
                        Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
