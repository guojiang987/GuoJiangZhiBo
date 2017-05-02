package com.rose.guojiangzhibo.urlconfig;

/**
 * Created by cheng on 2017/4/27.
 */

public class RtmpURL {
    public static String getRtmpUrl(){
        return "rtmp://8935.livepush.myqcloud.com/live/8935_test0430?bizid=8935&txSecret=d70f36b94714d6e7ff6be27fb2cb19db&txTime=592D96FF";
    }
    public static String getPlayUrl_flv(){
        return "http://8935.liveplay.myqcloud.com/live/8935_test0430.flv";
    }
    public static String getPlayUrl_rtmp(){
        return "rtmp://8935.liveplay.myqcloud.com/live/8935_test0430";
    }
    public static String getPlayUrl_hls(){
        return "http://8935.liveplay.myqcloud.com/live/8935_test0430.m3u8";
    }

}
