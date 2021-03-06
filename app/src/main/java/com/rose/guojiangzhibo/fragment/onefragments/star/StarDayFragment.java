package com.rose.guojiangzhibo.fragment.onefragments.star;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.rock.teachlibrary.http.HttpUtil;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.PersonalActivity;
import com.rose.guojiangzhibo.adapter.MyTopListAdapter;
import com.rose.guojiangzhibo.bean.ListData;
import com.rose.guojiangzhibo.urlconfig.UrlConfigOne;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StarDayFragment extends Fragment {

    private ListView listView;
    private List<ListData> listDatasList;
    private MyTopListAdapter myTopListAdapter;


    public StarDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_star_day, container, false);
        initView(inflate);
        getData();
        return inflate;
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), PersonalActivity.class);
                intent.putExtra("uid",listDatasList.get(position).getUid());
                intent.putExtra("name",listDatasList.get(position).getNickname());
                intent.putExtra("headerPic",listDatasList.get(position).getHeadPic());
                startActivityForResult(intent,123);
            }
        });
    }

    private void initView(View inflate) {
        listView = (ListView) inflate.findViewById(R.id.listview_starday);
        listDatasList=new ArrayList<>();
        myTopListAdapter=new MyTopListAdapter(getActivity(),listDatasList);
        listView.setAdapter(myTopListAdapter);
    }

    public void getData() {
        HttpUtil.getStringAsync(UrlConfigOne.URL_LIST, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONObject data=jsonObject.optJSONObject("data");
                    JSONObject userConsumeRank=data.optJSONObject("moderatorIncomeRank");
                    JSONArray jsonArray=userConsumeRank.optJSONArray("last");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.optJSONObject(i);
                        String nickname=object.optString("nickname");
                        String headPic=object.optString("headPic");
                        String level=object.optString("level");
                        ListData listData=new ListData();
                        int uid=object.optInt("mid");
                        listData.setUid(uid+"");
                        listData.setNickname(nickname);
                        listData.setHeadPic(headPic);
                        listData.setLevel(level);
                        listDatasList.add(listData);
                    }
                    myTopListAdapter.notifyDataSetChanged();
                    setListener();
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
