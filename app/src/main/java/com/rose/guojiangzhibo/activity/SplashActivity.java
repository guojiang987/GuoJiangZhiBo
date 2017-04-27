package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.util.SharePreferenceUtil;
import com.squareup.picasso.Picasso;


public class SplashActivity extends AppCompatActivity implements Handler.Callback {
    private ImageView imageView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        Picasso.with(this).load(R.drawable.splash).into(imageView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }).start();
    }

    private void init() {
        imageView = (ImageView) findViewById(R.id.img_splash);
        handler = new Handler(this);

    }

    Intent intent;

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == 0) {
//            SharePreferenceUtil.saveOrUpdateAttribute(this,"UID","uidflag",true);

            boolean b = (boolean) SharePreferenceUtil.getAttributeByKey(this, "UID", "uidflag", SharePreferenceUtil.VALUE_IS_BOOLEAN);
            if (b) {
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return false;
    }


}
