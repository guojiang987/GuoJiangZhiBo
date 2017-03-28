package com.rose.guojiangzhibo.fragment.onefragments.personal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rock.teachlibrary.http.HttpUtil;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.adapter.MyPersonalAdapter;
import com.rose.guojiangzhibo.bean.PersonalData;
import com.rose.guojiangzhibo.layout.MyListView;
import com.rose.guojiangzhibo.urlconfig.UrlConfigOne;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendsFragment extends Fragment {
    //解析出来的总数据
    private List<PersonalData> personalDatasList;
    private MyListView listView;
    private String uid;
    private MyPersonalAdapter myPersonalAdapter;

    public TrendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_trends, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        listView = (MyListView) inflate.findViewById(R.id.listview_trendsfragment);
        personalDatasList=new ArrayList<>();
        Bundle bundle=getArguments();//接受到从activity传过来的值
        uid=bundle.getString("uid");

        getData(uid);
        myPersonalAdapter=new MyPersonalAdapter(getActivity(),personalDatasList);
        listView.setAdapter(myPersonalAdapter);
    }

    private void getData(String uid) {
        HttpUtil.getStringAsync(UrlConfigOne.URL_PERSONAL_INFO + uid, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.optJSONObject(i);
                        String content=object.optString("content");
                        int lastReplyTime=object.optInt("lastReplyTime");
                        String supportNum=object.optString("supportNum");
                        String replyNum=object.optString("replyNum");
                        JSONArray jsonArray1=object.optJSONArray("pics");
                        String pics=null;
                        for (int j=0;j<jsonArray1.length();j++){
                            pics=jsonArray1.optString(j);
                        }
                        String nickname=object.optString("nickname");
                        String headPic=object.optString("headPic");
                        String groupName=object.optString("groupName");
                        PersonalData personalData=new PersonalData();
                        personalData.setContent(content);
                        personalData.setLastReplyTime(lastReplyTime);
                        personalData.setSupportNum(supportNum);
                        personalData.setReplyNum(replyNum);
                        personalData.setPics(pics);
                        personalData.setNickname(nickname);
                        personalData.setHeadPic(headPic);
                        personalData.setGroupName(groupName);
                        personalDatasList.add(personalData);
                    }

                    myPersonalAdapter.notifyDataSetChanged();
                    myPersonalAdapter=new MyPersonalAdapter(getActivity(),personalDatasList);
                    listView.setAdapter(myPersonalAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }


}
