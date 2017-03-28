package com.rose.guojiangzhibo.bean;

import java.util.List;

/**
 * Created by xdy on 2016/10/17.
 */

public class OneFragmentData {

    /**
     * rid : 1307
     * playStartTime : 1476751707
     * sex : 1
     * mid : 154815
     * nickname : 白轩
     * headPic : http://static.guojiang.tv/app/upload/2016_10_18/e4ff42577f2d7524f29675c5c354d418.jpg
     * isPlaying : true
     * onlineNum : 2021
     * fansNum : 13068
     * announcement : #土豪请秒榜#
     * moderatorLevel : 12
     * verified : false
     * verifyInfo :
     * videoPlayUrl : rtmp://rtmppull.efeizao.com/live/room_1307/chat
     * topics : [{"id":"39","title":"土豪请秒榜"}]
     * weight : 1100000028
     * id : 1307
     */

    private String rid;
    private int playStartTime;
    private String sex;
    private String mid;
    private String nickname;
    private String headPic;
    private boolean isPlaying;
    private int onlineNum;
    private String fansNum;
    private String announcement;
    private String moderatorLevel;
    private boolean verified;
    private String verifyInfo;
    private String videoPlayUrl;
    private int weight;
    private String id;
    /**
     * id : 39
     * title : 土豪请秒榜
     */

    private List<TopicsBean> topics;

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

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TopicsBean> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicsBean> topics) {
        this.topics = topics;
    }

    public static class TopicsBean {
        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TopicsBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
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
                ", id='" + id + '\'' +
                ", topics=" + topics +
                '}';
    }
}
