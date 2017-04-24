package com.rose.guojiangzhibo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.cyberplayer.core.BVideoView;
import com.rose.guojiangzhibo.R;

import org.xutils.common.Callback;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.rose.guojiangzhibo.customclass.RoundHeader.toRoundBitmap;


public class VideoViewPlayingActivity extends Activity {

    private final String TAG = "VideoViewPlayingActivity";
    //绑定各个控件
    //头像
    @BindView(R.id.image_header)
    ImageView imageHeader;
    //昵称
    @BindView(R.id.text_name)
    TextView textName;
    //观众人数
    @BindView(R.id.text_personnumber)
    TextView textPersonnumber;
    //泡泡个数
    @BindView(R.id.text_paopaonumber)
    TextView textPaopaonumber;
    //查看更多
    @BindView(R.id.image_more)
    ImageView imageMore;
    //关闭
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.image_talkplay)
    ImageView imageTalkplay;
    @BindView(R.id.image_flowerplay)
    ImageView imageFlowerplay;
    @BindView(R.id.image_gift)
    ImageView imageGift;
    @BindView(R.id.image_shareplay)
    ImageView imageShareplay;
    @BindView(R.id.image_messageplay)
    ImageView imageMessageplay;
    //弹幕

    /**
     * 您的AK
     * 请到http://console.bce.baidu.com/iam/#/iam/accesslist获取
     */
    private String AK = "1c992243e766444e8112e8dab754a1ca";  // 请录入您的AK !!!

    private String mVideoSource = null;
    /**
     * 记录播放位置
     */
    private int mLastPos = 0;

    @OnClick({R.id.image_header, R.id.image_more, R.id.image_close, R.id.image_talkplay, R.id.image_flowerplay,
            R.id.image_gift, R.id.image_shareplay, R.id.image_messageplay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_header:
                Toast.makeText(this, "我是" + nickname + "欢迎你来收看我的节目", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_more:

                break;
            case R.id.image_close:
                finish();
                break;
            case R.id.image_talkplay:

                break;
            case R.id.image_flowerplay:
                break;
            case R.id.image_gift:
                break;
            case R.id.image_shareplay:
                showShare();
                break;
            case R.id.image_messageplay:
                break;

        }
    }

    //分享
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("果酱直播");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://m.guojiang.tv");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("一起加入我们吧");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("m.guojiang.tv");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("好玩儿的直播平台");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://m.guojiang.tv");
// 启动分享GUI
        oks.show(this);
    }


    /**
     * 播放状态
     */
    private enum PLAYER_STATUS {
        PLAYER_IDLE, PLAYER_PREPARING, PLAYER_PREPARED,
    }

    private PLAYER_STATUS mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;

    private BVideoView mVV = null;

    private EventHandler mEventHandler;
    private HandlerThread mHandlerThread;

    private final Object SYNC_Playing = new Object();

    private WakeLock mWakeLock = null;
    private static final String POWER_LOCK = "VideoViewPlayingActivity";

    private boolean mIsHwDecode = false;

    private final int EVENT_PLAY = 0;
    private final int UI_EVENT_UPDATE_CURRPOSITION = 1;
    //接收fragment传过来的值
    private String rid;
    private String headerPic;
    private String nickname;

    class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_PLAY:
                    /**
                     * 如果已经播放了，等待上一次播放结束
                     */
                    if (mPlayerStatus != PLAYER_STATUS.PLAYER_IDLE) {
                        synchronized (SYNC_Playing) {
                            try {
                                SYNC_Playing.wait();
                                Log.v(TAG, "wait player status to idle");
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }

                    /**
                     * 设置播放url
                     */
                    mVV.setVideoPath(mVideoSource);

                    /**
                     * 续播，如果需要如此
                     */
                    if (mLastPos > 0) {

                        mVV.seekTo(mLastPos);
                        mLastPos = 0;
                    }

                    /**
                     * 显示或者隐藏缓冲提示
                     */
                    mVV.showCacheInfo(true);

                    /**
                     * 开始播放
                     */
                    mVV.start();

                    mPlayerStatus = PLAYER_STATUS.PLAYER_PREPARING;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_play);
        ButterKnife.bind(this);

        rid = getIntent().getStringExtra("rid");
        headerPic = getIntent().getStringExtra("headerPic");
        nickname = getIntent().getStringExtra("nickname");
        //接收fragment传递的值
        downBitmap(headerPic);
        textName.setText(nickname);
        textPersonnumber.setText("观众1650人");
        textPaopaonumber.setText("泡泡数 226790 ");
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, POWER_LOCK);

        mIsHwDecode = getIntent().getBooleanExtra("isHW", false);
        Uri uriPath = getIntent().getData();
        if (null != uriPath) {
            String scheme = uriPath.getScheme();
            if (null != scheme) {
                mVideoSource = uriPath.toString();
            } else {
                mVideoSource = uriPath.getPath();
            }
        }
        initUI();

        /**
         * 开启后台事件处理线程
         */
        mHandlerThread = new HandlerThread("event handler thread",
                Process.THREAD_PRIORITY_BACKGROUND);
        mHandlerThread.start();
        mEventHandler = new EventHandler(mHandlerThread.getLooper());

    }

    /**
     * 初始化界面
     */
    private void initUI() {

        /**
         * 设置ak
         */
        BVideoView.setAK(AK);

        /**
         *获取BVideoView对象
         */
        mVV = (BVideoView) findViewById(R.id.video_view);

        /**
         * 设置解码模式
         */
        mVV.setDecodeMode(mIsHwDecode ? BVideoView.DECODE_HW : BVideoView.DECODE_SW);
    }

    public void downBitmap(String headPic) {
        x.image().bind(imageHeader, headPic, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                if (result != null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Bitmap circleBitmap = toRoundBitmap(bitmap);
                    imageHeader.setImageBitmap(circleBitmap);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void updateTextViewWithTimeFormat(TextView view, int second) {
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String strTemp = null;
        if (0 != hh) {
            strTemp = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            strTemp = String.format("%02d:%02d", mm, ss);
        }
        view.setText(strTemp);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        /**
         * 在停止播放前 你可以先记录当前播放的位置,以便以后可以续播
         */
        if (mPlayerStatus == PLAYER_STATUS.PLAYER_PREPARED) {
            mLastPos = mVV.getCurrentPosition();
            mVV.stopPlayback();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.v(TAG, "onResume");
        if (null != mWakeLock && (!mWakeLock.isHeld())) {
            mWakeLock.acquire();
        }
        /**
         * 发起一次播放任务,当然您不一定要在这发起
         */
        mEventHandler.sendEmptyMessage(EVENT_PLAY);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 退出后台事件处理线程
         */
        mHandlerThread.quit();
    }
}
