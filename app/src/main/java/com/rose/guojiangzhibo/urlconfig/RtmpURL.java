package com.rose.guojiangzhibo.urlconfig;

/**
 * Created by cheng on 2017/4/27.
 */

public class RtmpURL {
    public static String getRtmpUrl(){
        return "rtmp://8935.livepush.myqcloud.com/live/8935_0929test01?bizid=8935&txSecret=93a59ea3e5a29f0dbcdedfef509d90e9&txTime=5902157F";
    }
    public static String getPlayUrl_flv(){
        return "http://8935.liveplay.myqcloud.com/live/8935_0929test01.flv";
    }
    public static String getPlayUrl_rtmp(){
        return "rtmp://8935.liveplay.myqcloud.com/live/8935_0929test01";
    }
    public static String getPlayUrl_hls(){
        return "http://8935.liveplay.myqcloud.com/live/8935_0929test01.m3u8";
    }

}
