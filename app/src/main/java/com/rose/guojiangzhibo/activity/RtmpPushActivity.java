package com.rose.guojiangzhibo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.umeng.analytics.MobclickAgent;

import java.text.SimpleDateFormat;

public class RtmpPushActivity extends AppCompatActivity {

    private boolean flag = true;
    private TXLivePusher txLivePusher;
    private TXLivePushConfig myLivePushConfig;
    private static final int REQUEST_CODE = 123;
    private long start;
    private long current;
    TXCloudVideoView txCloudVideoView;
    private TextView timeView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeView.setText((String) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtmp_push);
        initView();
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(
                    getApplicationContext(), Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this
                        , new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                return;
            } else {
                push();
            }
        } else {
            push();
        }
    }

    private void initView() {
        timeView = (TextView) findViewById(R.id.time_push);
    }

    private void push() {
        txLivePusher = new TXLivePusher(this);
        myLivePushConfig = new TXLivePushConfig();
        txLivePusher.setConfig(myLivePushConfig);
        String rtmpUrl = "rtmp://8935.livepush.myqcloud.com/live/8935_fc0daa33f6?bizid=8935&txSecret=7726e067f02a430e5bacb7ef161ea333&txTime=58F7897F";
        txLivePusher.startPusher(rtmpUrl);
        txCloudVideoView = (TXCloudVideoView) findViewById(R.id.video_view);
        txLivePusher.setBeautyFilter(3, 2);
        txLivePusher.startCameraPreview(txCloudVideoView);
        startTime();
    }

    private void startTime() {
        start = System.currentTimeMillis();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);
                        current = System.currentTimeMillis();
                        final long time = current - start;
                        final String t = simpleDateFormat.format(time);
                        Message message = Message.obtain();
                        message.obj = t;
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back_push:
                finish();
                break;
            case R.id.changecamera:
                txLivePusher.switchCamera();
                break;

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        txCloudVideoView.onPause();
        txLivePusher.stopCameraPreview(true);// 停止摄像头采集（就算不停止也采集不到了）
        flag = false;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
