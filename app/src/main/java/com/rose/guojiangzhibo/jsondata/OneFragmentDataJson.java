package com.rose.guojiangzhibo.jsondata;

import android.util.Log;

import com.google.gson.JsonObject;
import com.rose.guojiangzhibo.bean.OneFragmentData;
import com.rose.guojiangzhibo.fragment.OneFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdy on 2016/10/18.
 */

public class OneFragmentDataJson {
    public static List<OneFragmentData> getListFromOneFragment(String json){
        List<OneFragmentData> oneFragmentsList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.optJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object=jsonArray.optJSONObject(i);
                String headPic=object.optString("headPic");
                String nickname=object.optString("nickname");
                String announcement=object.optString("announcement");
                String videoPlayUrl=object.optString("videoPlayUrl");
                String rid=object.optString("rid");
                OneFragmentData oneFragmentData=new OneFragmentData();
                oneFragmentData.setHeadPic(headPic);
                oneFragmentData.setNickname(nickname);
                oneFragmentData.setAnnouncement(announcement);
                oneFragmentData.setVideoPlayUrl(videoPlayUrl);
                oneFragmentData.setRid(rid);
                oneFragmentsList.add(oneFragmentData);
            }
            return oneFragmentsList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
