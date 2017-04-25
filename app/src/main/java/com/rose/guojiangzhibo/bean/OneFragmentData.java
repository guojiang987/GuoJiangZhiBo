package com.rose.guojiangzhibo.bean;

import java.util.List;

/**
 * Created by xdy on 2016/10/17.
 */

public class OneFragmentData {

    /**
     * rid : 170668
     * playStartTime : 1492610154
     * sex : 1
     * mid : 1906905
     * nickname : 赵伟顺其自然
     * headPic : http://static.guojiang.tv/app/upload/2017_04_12/29c53abbcb1ece611691e9cc42bc223b.jpg
     * isPlaying : true
     * onlineNum : 2212
     * fansNum : 2981
     * announcement : 蒙面大侠不想露脸，露脸先表示
     * moderatorLevel : 14
     * verified : false
     * verifyInfo :
     * videoPlayUrl : rtmp://rtmppull.efeizao.com/live/room_170668/chat
     * topics : []
     * weight : 1100000018
     * timeZoneHotWeight : 1000000000
     * city : 南昌市
     * tags : []
     * id : 170668
     */

    private String rid;
    private int playStartTime;
    private String sex;
    private String mid;
    private String nickname;
    private String headPic;
    private boolean isPlaying;
    private String onlineNum;
    private String fansNum;
    private String announcement;
    private String moderatorLevel;
    private boolean verified;
    private String verifyInfo;
    private String videoPlayUrl;
    private int weight;
    private int timeZoneHotWeight;
    private String city;
    private String id;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getPlayStartTime() {
        return playStartTime;
    }

    public void setPlayStartTime(int playStartTime) {
        this.playStartTime = playStartTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public boolean isIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public String getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(String onlineNum) {
        this.onlineNum = onlineNum;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getModeratorLevel() {
        return moderatorLevel;
    }

    public void setModeratorLevel(String moderatorLevel) {
        this.moderatorLevel = moderatorLevel;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTimeZoneHotWeight() {
        return timeZoneHotWeight;
    }

    public void setTimeZoneHotWeight(int timeZoneHotWeight) {
        this.timeZoneHotWeight = timeZoneHotWeight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OneFragmentData{" +
                "rid='" + rid + '\'' +
                ", playStartTime=" + playStartTime +
                ", sex='" + sex + '\'' +
                ", mid='" + mid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headPic='" + headPic + '\'' +
                ", isPlaying=" + isPlaying +
                ", onlineNum=" + onlineNum +
                ", fansNum='" + fansNum + '\'' +
                ", announcement='" + announcement + '\'' +
                ", moderatorLevel='" + moderatorLevel + '\'' +
                ", verified=" + verified +
                ", verifyInfo='" + verifyInfo + '\'' +
                ", videoPlayUrl='" + videoPlayUrl + '\'' +
                ", weight=" + weight +
                ", timeZoneHotWeight=" + timeZoneHotWeight +
                ", city='" + city + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
