package com.rose.guojiangzhibo.fragment.onefragments;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.urlconfig.RtmpURL;
import com.rose.guojiangzhibo.util.CheckUtil;
import com.rose.guojiangzhibo.util.ToastUtils;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends Fragment implements View.OnClickListener {
    private Button pause, speed_attention, mBtnPlay, mBtnHWDecode;
    private TXLivePlayer mLivePlayer;
    private boolean pauseflag = true;
    private TXCloudVideoView mPlayerView;
    private TXLivePlayConfig mPlayConfig;
    private String flvUrl;
    private boolean mVideoPlay;
    private int mPlayType = TXLivePlayer.PLAY_TYPE_LIVE_FLV;
    private ImageView mLoadingView;
    private boolean mVideoPause = false;
    private boolean mHWDecode = false;


    public AttentionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_attention, container, false);
        mVideoPlay = false;

        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        //mPlayerView即step1中添加的界面view
        mBtnPlay = (Button) inflate.findViewById(R.id.btnPlay);
        mPlayerView = (TXCloudVideoView) inflate.findViewById(R.id.video_view);
        mLoadingView = (ImageView) inflate.findViewById(R.id.loadingImageView);
        pause = (Button) inflate.findViewById(R.id.btnPlay);
        //创建player对象
        mLivePlayer = new TXLivePlayer(getActivity());
        //关键player对象与界面view
        mLivePlayer.setPlayerView(mPlayerView);
        mPlayConfig = new TXLivePlayConfig();

        mBtnPlay.setOnClickListener(this);
        flvUrl = RtmpURL.getPlayUrl_flv();
        if (!CheckUtil.isEmpty(flvUrl)) {
            mLivePlayer.startPlay(flvUrl, mPlayType);
            mLivePlayer.setRenderMode(0);
            mLivePlayer.setPlayListener(new MyListener());

        }

        //自动模式
        mPlayConfig.setAutoAdjustCacheTime(true);
        mPlayConfig.setMinAutoAdjustCacheTime(1);
        mPlayConfig.setMaxAutoAdjustCacheTime(5);
        mLivePlayer.setConfig(mPlayConfig);

        //硬件解码
        mBtnHWDecode = (Button) inflate.findViewById(R.id.btnHWDecode);
        mBtnHWDecode.getBackground().setAlpha(mHWDecode ? 255 : 100);
        mBtnHWDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHWDecode = !mHWDecode;
                mBtnHWDecode.getBackground().setAlpha(mHWDecode ? 255 : 100);

                if (mHWDecode) {
                    Toast.makeText(getActivity().getApplicationContext(), "已开启硬件解码加速", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "已关闭硬件解码加速", Toast.LENGTH_SHORT).show();
                }

                if (mVideoPlay) {
                    stopPlayRtmp();
                    startPlayRtmp();
                    if (mVideoPause) {
                        if (mPlayerView != null) {
                            mPlayerView.onResume();
                        }
                        mVideoPause = false;
                    }
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        mPlayerView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                if (mVideoPlay) {
                    if (mPlayType == TXLivePlayer.PLAY_TYPE_VOD_FLV || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_HLS || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_MP4 || mPlayType == TXLivePlayer.PLAY_TYPE_LOCAL_VIDEO) {
                        if (mVideoPause) {
                            mLivePlayer.resume();
                            mBtnPlay.setBackgroundResource(R.drawable.play_pause);
                        } else {
                            mLivePlayer.pause();
                            mBtnPlay.setBackgroundResource(R.drawable.play_start);
                        }
                        mVideoPause = !mVideoPause;

                    } else {
                        stopPlayRtmp();
                        mVideoPlay = !mVideoPlay;
                    }

                } else {
                    if (startPlayRtmp()) {
                        mVideoPlay = !mVideoPlay;
                    }
                }


                break;
        }
    }

    //播放进度监听
    public class MyListener implements ITXLivePlayListener {

        @Override
        public void onNetStatus(Bundle bundle) {

        }

        @Override
        public void onPlayEvent(int event, Bundle param) {
            // RTMP SDK Demo 的源码中，视频画面卡住时，会在其上绘制一个半透明的loading动画。
            // 这里的 stopLoadingAnimation 是将loading动画停止掉。
            if (event == TXLiveConstants.PLAY_EVT_PLAY_BEGIN) {
                ToastUtils.showToas(getActivity(), "开始播放");
            }
            // 如下这段代码是处理播放进度
            else if (event == TXLiveConstants.PLAY_EVT_PLAY_PROGRESS) {
                int progress = param.getInt(TXLiveConstants.EVT_PLAY_PROGRESS); //进度（秒数）
                int duration = param.getInt(TXLiveConstants.EVT_PLAY_DURATION); //时间（秒数）

                return;
            }
            // 如下这段代码是处理播放结束的事件
            else if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT
                    || event == TXLiveConstants.PLAY_EVT_PLAY_END) {
//                stopPlayRtmp();
//                mVideoPlay = false;
                onDestroy();
            }
        }
    }

    private boolean startPlayRtmp() {
        String playUrl = RtmpURL.getPlayUrl_flv();

//        //由于iOS AppStore要求新上架的app必须使用https,所以后续腾讯云的视频连接会支持https,但https会有一定的性能损耗,所以android将统一替换会http
//        if (playUrl.startsWith("https://")) {
//            playUrl = "http://" + playUrl.substring(8);
//        }


        mBtnPlay.setBackgroundResource(R.drawable.play_pause);

        mLivePlayer.setPlayerView(mPlayerView);

        // 硬件加速在1080p解码场景下效果显著，但细节之处并不如想象的那么美好：
        // (1) 只有 4.3 以上android系统才支持
        // (2) 兼容性我们目前还仅过了小米华为等常见机型，故这里的返回值您先不要太当真
        mLivePlayer.enableHardwareDecode(mHWDecode);
        //设置播放器缓存策略
        //这里将播放器的策略设置为自动调整，调整的范围设定为1到4s，您也可以通过setCacheTime将播放器策略设置为采用
        //固定缓存时间。如果您什么都不调用，播放器将采用默认的策略（默认策略为自动调整，调整范围为1到4s）
        //mLivePlayer.setCacheTime(5);
        mLivePlayer.setConfig(mPlayConfig);

        int result = mLivePlayer.startPlay(playUrl, mPlayType); // result返回值：0 success;  -1 empty url; -2 invalid url; -3 invalid playType;
        if (result == -2) {
            Toast.makeText(getActivity().getApplicationContext(), "非腾讯云链接地址，若要放开限制，请联系腾讯云商务团队", Toast.LENGTH_SHORT).show();
        }
        if (result != 0) {
            mBtnPlay.setBackgroundResource(R.drawable.play_start);
            return false;
        }
        return true;
    }

    private void stopPlayRtmp() {
        mBtnPlay.setBackgroundResource(R.drawable.play_start);
        stopLoadingAnimation();
        if (mLivePlayer != null) {
            mLivePlayer.setPlayListener(null);
            mLivePlayer.stopPlay(true);
        }
    }

    private void stopLoadingAnimation() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
            ((AnimationDrawable) mLoadingView.getDrawable()).stop();
        }
    }
}
