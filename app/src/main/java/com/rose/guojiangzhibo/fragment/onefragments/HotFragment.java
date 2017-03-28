package com.rose.guojiangzhibo.fragment.onefragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.rock.teachlibrary.ImageLoader;
import com.rock.teachlibrary.http.HttpUtil;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.VideoViewPlayingActivity;
import com.rose.guojiangzhibo.adapter.MyOneHeaderAdapter;
import com.rose.guojiangzhibo.adapter.MyOneListAdapter;
import com.rose.guojiangzhibo.bean.HotHeaderData;
import com.rose.guojiangzhibo.bean.OneFragmentData;
import com.rose.guojiangzhibo.jsondata.OneFragmentDataJson;
import com.rose.guojiangzhibo.urlconfig.UrlConfigOne;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

    //头部图片的集合
    private List<ImageView> imageViewsList;
    //头部图片数据的总集合
    private List<HotHeaderData> hotHeaderDataList;
    //中间内容总的集合

    private List<OneFragmentData> oneFragmentDatasList;
    //适配器
    private MyOneHeaderAdapter myOneHeaderAdapter;
    private MyOneListAdapter myOneListAdapter;
    private ListView listView;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_hot, container, false);
        initView(inflate);
        getData();
        return inflate;
    }

    private void getData() {
        //解析头部轮播图片的数据
        HttpUtil.getStringAsync(UrlConfigOne.URL_HEADER, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject =new JSONObject(result);
                    JSONObject focusImages=jsonObject.optJSONObject("focusImages");
                    JSONArray jsonArray = focusImages.optJSONArray("list");
                    hotHeaderDataList=new ArrayList<HotHeaderData>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.optJSONObject(i);
                        String pic=object.optString("pic");
                        HotHeaderData hotHeaderData=new HotHeaderData();
                        hotHeaderData.setPic(pic);
                        hotHeaderDataList.add(hotHeaderData);
                    }
                    addHeader();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFinish() {

            }
        });
        //解析主要布局的数据(第一段数据)
        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_HOTONE, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                oneFragmentDatasList.addAll(OneFragmentDataJson.getListFromOneFragment(result));
            }

            @Override
            public void onFinish() {

            }
        });
        //第二段数据
        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_HOTTWO, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                oneFragmentDatasList.addAll(OneFragmentDataJson.getListFromOneFragment(result));
            }

            @Override
            public void onFinish() {

            }
        });
        //第三段数据
        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_HOTTHREE, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                oneFragmentDatasList.addAll(OneFragmentDataJson.getListFromOneFragment(result));
                myOneListAdapter.notifyDataSetChanged();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent=new Intent(getActivity(), OnePlayerActivity.class);
//                        intent.putExtra("url",oneFragmentDatasList.get(position).getVideoPlayUrl());
//                        startActivityForResult(intent,123);
                        playVideo(oneFragmentDatasList.get(position).getVideoPlayUrl(),position);
                    }
                });
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void addHeader() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_hot_header,null);
        ViewPager viewPager = (ViewPager) header.findViewById(R.id.viewpager_hotfragment);
        for (int i = 0; i < hotHeaderDataList.size()*2; i++) {
            ImageView imageHeader=new ImageView(getActivity());
            imageHeader.setScaleType(ImageView.ScaleType.FIT_XY);
            //下载图片
            ImageLoader.display(imageHeader,hotHeaderDataList.get(i%hotHeaderDataList.size()).getPic());
            imageViewsList.add(imageHeader);
        }
        myOneHeaderAdapter=new MyOneHeaderAdapter(imageViewsList);
        viewPager.setAdapter(myOneHeaderAdapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE/2);
        listView.addHeaderView(header);
    }

    private void initView(View inflate) {
        listView = (ListView) inflate.findViewById(R.id.listview_hot);
        imageViewsList=new ArrayList<>();

        oneFragmentDatasList=new ArrayList<>();
        ImageLoader.init(getActivity());
        myOneListAdapter =  new MyOneListAdapter(getActivity(),oneFragmentDatasList);
        listView.setAdapter(myOneListAdapter);
    }


    private void playVideo(String source,int position){
//		String source = mSourceET.getText().toString();
//        String source="rtmp://rtmppull.efeizao.com/live/room_132896/chat";
        if(source == null || source.equals("")){
            /**
             * 简单检测播放源的合法性,不合法不播放
             */
            Toast.makeText(getActivity(), "please input your video source", 500).show();

//            source = "http://devimages.apple.com/iphone/samples/bipbop/gear4/prog_index.m3u8";
            Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
            intent.setData(Uri.parse(source));
            intent.putExtra("rid",oneFragmentDatasList.get(position).getRid());
//            intent.putExtra("headerPic",oneFragmentDatasList.get(position).getHeadPic());
            startActivityForResult(intent,123);
        }else{
            Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
            intent.putExtra("rid",oneFragmentDatasList.get(position).getRid());
            intent.putExtra("headerPic",oneFragmentDatasList.get(position).getHeadPic());
            intent.putExtra("nickname",oneFragmentDatasList.get(position).getNickname());
            startActivityForResult(intent,123);
        }
    }


}
