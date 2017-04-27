package com.rose.guojiangzhibo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.layout.TXHorizontalPickerView;
import com.rose.guojiangzhibo.urlconfig.RtmpURL;
import com.rose.guojiangzhibo.util.CheckUtil;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.umeng.analytics.MobclickAgent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.graphics.BitmapFactory.decodeResource;

public class RtmpPushActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    private boolean flag = true;
    private TXLivePusher txLivePusher;
    private TXLivePushConfig myLivePushConfig;
    private static final int REQUEST_CODE = 123;
    private long start;
    private long current;
    TXCloudVideoView txCloudVideoView;
    private TextView timeView;
    private boolean mVideoPublish;
    private Button changecamera, btnPlay, mBtnHWEncode;
    private ImageButton back_push;
    private boolean mHWVideoEncode = false;
    private ArrayAdapter<String> mFilterAdapter;

    /**
     * 滤镜定义
     */
    public static final int FILTERTYPE_NONE         = 0;    //无特效滤镜
    public static final int FILTERTYPE_langman      = 1;    //浪漫滤镜
    public static final int FILTERTYPE_qingxin      = 2;    //清新滤镜
    public static final int FILTERTYPE_weimei       = 3;    //唯美滤镜
    public static final int FILTERTYPE_fennen 		= 4;    //粉嫩滤镜
    public static final int FILTERTYPE_huaijiu 		= 5;    //怀旧滤镜
    public static final int FILTERTYPE_landiao 		= 6;    //蓝调滤镜
    public static final int FILTERTYPE_qingliang 	= 7;    //清凉滤镜
    public static final int FILTERTYPE_rixi 		= 8;    //日系滤镜


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeView.setText((String) msg.obj);
        }
    };
    private LinearLayout mFaceBeautyLayout;
    private SeekBar mBeautySeekBar;
    private SeekBar mExposureSeekBar;
    private SeekBar mWhiteningSeekBar;
    private Button mBtnFaceBeauty;
    private ArrayList<String> mFilterList;
    private TXHorizontalPickerView mFilterPicker;
    private int mFilterType;
    private int              mBeautyLevel = 5;
    private int              mWhiteningLevel = 3;
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
        //开启硬件加速
        mBtnHWEncode.getBackground().setAlpha(mHWVideoEncode ? 255 : 100);

        //美颜部分


        mBtnFaceBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFaceBeautyLayout.setVisibility(mFaceBeautyLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        mFilterList = new ArrayList<String>();
        mFilterList.add("无滤镜");
        mFilterList.add("浪漫滤镜");
        mFilterList.add("清新滤镜");
        mFilterList.add("唯美滤镜");
        mFilterList.add("粉嫩滤镜");
        mFilterList.add("怀旧滤镜");
        mFilterList.add("蓝调滤镜");
        mFilterList.add("清凉滤镜");
        mFilterList.add("日系滤镜");


        mFilterAdapter = new ArrayAdapter<String>(this, 0, mFilterList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                String value = getItem(position);
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
                }
                TextView view = (TextView) convertView.findViewById(android.R.id.text1);
                view.setTag(position);
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                view.setText(value);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = (int) view.getTag();
                        ViewGroup group = (ViewGroup) mFilterPicker.getChildAt(0);
                        for (int i = 0; i < mFilterAdapter.getCount(); i++) {
                            View v = group.getChildAt(i);
                            if (v instanceof TextView) {
                                if (i == index) {
                                    ((TextView) v).setTextColor(Color.GRAY);
                                } else {
                                    ((TextView) v).setTextColor(Color.BLACK);
                                }
                            }
                        }
                        setFilter(index);
                    }
                });
                return convertView;

            }
        };
        mFilterPicker.setAdapter(mFilterAdapter);
        mFilterPicker.setClicked(0);

    }

    private void initView() {
        timeView = (TextView) findViewById(R.id.time_push);
        changecamera = (Button) findViewById(R.id.changecamera);
        back_push = (ImageButton) findViewById(R.id.back_push);
        mBtnHWEncode = (Button) findViewById(R.id.btnHWDecode);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        mFaceBeautyLayout = (LinearLayout) findViewById(R.id.layoutFaceBeauty);
        mBeautySeekBar = (SeekBar) findViewById(R.id.beauty_seekbar);
        mExposureSeekBar = (SeekBar) findViewById(R.id.exposure_seekbar);
        mWhiteningSeekBar = (SeekBar) findViewById(R.id.whitening_seekbar);
        mBtnFaceBeauty = (Button) findViewById(R.id.btnFaceBeauty);
        mFilterPicker = (TXHorizontalPickerView) findViewById(R.id.filterPicker);
        changecamera.setOnClickListener(this);
        back_push.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        mBtnHWEncode.setOnClickListener(this);
        mBeautySeekBar.setOnSeekBarChangeListener(this);
        mExposureSeekBar.setOnSeekBarChangeListener(this);
        mWhiteningSeekBar.setOnSeekBarChangeListener(this);
        mVideoPublish = false;
    }

    private void push() {
        txLivePusher = new TXLivePusher(this);
        myLivePushConfig = new TXLivePushConfig();
        txLivePusher.setConfig(myLivePushConfig);
        myLivePushConfig.setBeautyFilter(mBeautyLevel, mWhiteningLevel);
        String rtmpUrl = RtmpURL.getRtmpUrl();
        if (!CheckUtil.isEmpty(rtmpUrl)) {
            txLivePusher.startPusher(rtmpUrl);
            txCloudVideoView = (TXCloudVideoView) findViewById(R.id.video_view);
            txLivePusher.setBeautyFilter(3, 2);
            txLivePusher.startCameraPreview(txCloudVideoView);
            startTime();
        }
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

    protected void setFilter(int filterType) {
        mFilterType = filterType;
        Bitmap bmp = null;
        switch (filterType) {
            case FILTERTYPE_langman:
                bmp = decodeResource(getResources(), R.drawable.langman);
                break;
            case FILTERTYPE_qingxin:
                bmp = decodeResource(getResources(), R.drawable.qingxin);
                break;
            case FILTERTYPE_weimei:
                bmp = decodeResource(getResources(), R.drawable.weimei);
                break;
            case FILTERTYPE_fennen:
                bmp = decodeResource(getResources(), R.drawable.fennen);
                break;
            case FILTERTYPE_huaijiu:
                bmp = decodeResource(getResources(), R.drawable.huaijiu);
                break;
            case FILTERTYPE_landiao:
                bmp = decodeResource(getResources(), R.drawable.landiao);
                break;
            case FILTERTYPE_qingliang:
                bmp = decodeResource(getResources(), R.drawable.qingliang);
                break;
            case FILTERTYPE_rixi:
                bmp = decodeResource(getResources(), R.drawable.rixi);
                break;
            default:
                bmp = null;
                break;
        }
        if (txLivePusher != null) {
            txLivePusher.setFilter(bmp);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changecamera:
                txLivePusher.switchCamera();
                break;
            case R.id.back_push:
                finish();
                break;
            case R.id.btnPlay:
                txLivePusher.stopCameraPreview(true);
                finish();
                break;
            case R.id.btnHWDecode:
                getSpeed();
                break;
        }
    }
     //点击硬件加速时
    private void getSpeed() {
        boolean HWVideoEncode = mHWVideoEncode;
        mHWVideoEncode = !mHWVideoEncode;
        mBtnHWEncode.getBackground().setAlpha(mHWVideoEncode ? 255 : 100);

        if (mHWVideoEncode) {
            if (myLivePushConfig != null) {
                if (Build.VERSION.SDK_INT < 18) {
                    Toast.makeText(this, "硬件加速失败，当前手机API级别过低（最低18）", Toast.LENGTH_SHORT).show();
                    mHWVideoEncode = false;
                }
            }
        }
        if (HWVideoEncode != mHWVideoEncode) {
            myLivePushConfig.setHardwareAcceleration(mHWVideoEncode);
            if (mHWVideoEncode == false) {
                Toast.makeText(this, "取消硬件加速", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "开启硬件加速", Toast.LENGTH_SHORT).show();
            }
        }
        if (txLivePusher != null) {
            txLivePusher.setConfig(myLivePushConfig);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.exposure_seekbar) {
            if (txLivePusher != null) {
                txLivePusher.setExposureCompensation(((float)progress - 10.0f)/10.0f);
            }
            return;
        } else if (seekBar.getId() == R.id.beauty_seekbar) {
            mBeautyLevel = progress;
        } else if (seekBar.getId() == R.id.whitening_seekbar) {
            mWhiteningLevel = progress;
        }

        if (txLivePusher != null) {
            if (!txLivePusher.setBeautyFilter(mBeautyLevel, mWhiteningLevel)) {
                Toast.makeText(this, "当前机型的性能无法支持美颜功能", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
