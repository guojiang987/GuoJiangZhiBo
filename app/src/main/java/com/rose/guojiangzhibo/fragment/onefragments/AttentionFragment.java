package com.rose.guojiangzhibo.fragment.onefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.urlconfig.RtmpURL;
import com.rose.guojiangzhibo.util.CheckUtil;
import com.rose.guojiangzhibo.util.ToastUtils;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static android.R.attr.type;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends Fragment implements View.OnClickListener {
    private Button pause, speed_attention;
    private TXLivePlayer mLivePlayer;
    private boolean pauseflag = true;
    private TXCloudVideoView mPlayerView;
    private TXLivePlayConfig mPlayConfig;
    private String flvUrl;

    public AttentionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_attention,container, false);
        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        //mPlayerView即step1中添加的界面view
        mPlayerView = (TXCloudVideoView) inflate.findViewById(R.id.video_view);
        pause = (Button) inflate.findViewById(R.id.pause_attention);
        //创建player对象
        mLivePlayer = new TXLivePlayer(getActivity());
        //关键player对象与界面view
        mLivePlayer.setPlayerView(mPlayerView);
        mPlayConfig = new TXLivePlayConfig();

         flvUrl = RtmpURL.getPlayUrl_flv();
        if (!CheckUtil.isEmpty(flvUrl)) {
            mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV);
            mLivePlayer.setRenderMode(0);
            mLivePlayer.setPlayListener(new MyListener());

        }

        //自动模式
        mPlayConfig.setAutoAdjustCacheTime(true);
        mPlayConfig.setMinAutoAdjustCacheTime(1);
        mPlayConfig.setMaxAutoAdjustCacheTime(5);
        mLivePlayer.setConfig(mPlayConfig);

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
            case R.id.pause_attention:
                if (pauseflag) {
                    // 暂停
                    mLivePlayer.pause();
                    pauseflag = false;
                } else {
                    //播放
                    mLivePlayer.resume();
                    pauseflag = true;
                }
                break;
            case R.id.speed_attention:
                mLivePlayer.stopPlay(true);
                mLivePlayer.enableHardwareDecode(true);
                mLivePlayer.startPlay(flvUrl, type);
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
}
