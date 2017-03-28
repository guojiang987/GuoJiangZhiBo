package com.rose.guojiangzhibo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;

import com.rock.teachlibrary.http.HttpUtil;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.adapter.RobotAdapter;
import com.rose.guojiangzhibo.bean.IType;
import com.rose.guojiangzhibo.bean.MyMessage;
import com.rose.guojiangzhibo.bean.YourMessage;
import com.rose.guojiangzhibo.urlconfig.FourConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class RobotActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
//    private Button button;
    private LinearLayoutManager manager;
    private RobotAdapter robotAdapter;
    private String message;
    private int position = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);
        initView();

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_robot);
        editText = (EditText) findViewById(R.id.edit_robot);
//        button = (Button) findViewById(R.id.button_robot);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        robotAdapter = new RobotAdapter(this);
        recyclerView.setAdapter(robotAdapter);
    }

    public void onClick(View view) {
        message = editText.getText() + "";
        editText.setText("");
        MyMessage myMessage = new MyMessage();
        myMessage.setContext(message);
        robotAdapter.addItem(myMessage);
        getDate();

    }

    public void getDate() {
        try {
            String messagetwo = URLEncoder.encode(message, "utf-8");
            String url = FourConfig.getRobot();
            url = url + messagetwo;
            HttpUtil.getStringAsync(url, new HttpUtil.RequestCallBack() {
                @Override
                public void onFailure() {

                }

                @Override
                public void onSuccess(String result) {
                    if (!TextUtils.isEmpty(result)) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            YourMessage yourMessage = new YourMessage();
                            yourMessage.setContext(jsonObject.optString("text"));
                            robotAdapter.addItem(yourMessage);
                            position+=2;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFinish() {
                    recyclerView.smoothScrollToPosition(position);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
