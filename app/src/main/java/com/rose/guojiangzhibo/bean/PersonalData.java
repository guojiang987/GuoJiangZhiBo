package com.rose.guojiangzhibo.bean;

import java.util.List;

/**
 * Created by xdy on 2016/10/21.
 */

public class PersonalData {

    /**
     * id : 84256
     * uid : 45948
     * content :
     实力画杠！我也是陶醉[哭][哭][哭][哭]
     * addTime : 1476655203
     * lastReplyTime : 1476984277
     * viewNum : 241
     * supportNum : 22
     * replyNum : 32
     * pics : ["http://static.guojiang.tv/app/upload/2016_10_17/07fcdd5f764b437383fa6b66a0459b00.jpg?w=828&h=1104"]
     * nickname : ↘杠-杠上开花
     * level : 14
     * headPic : http://static.guojiang.tv/app/upload/2016_09_30/df4d63af984a59049807836d14c7df4b.jpg
     * type : 2
     * supported : false
     * groupId : 132
     * groupName : 杠宝宝的圈圈
     * isGroupAdmin : false
     * isGroupOwner : false
     * groupJoined : false
     * bookmark : false
     * isTop : false
     * isBan : false
     * isNice : false
     * isUserBanned : false
     * moderatorLevel : 21
     * verified : 1
     * verifyInfo : 很乖很小宝，很丑很自豪
     */

    private String id;
    private String uid;
    private String content;
    private int addTime;
    private int lastReplyTime;
    private String viewNum;
    private String supportNum;
    private String replyNum;
    private String nickname;
    private String level;
    private String headPic;
    private String type;
    private boolean supported;
    private String groupId;
    private String groupName;
    private boolean isGroupAdmin;
    private boolean isGroupOwner;
    private boolean groupJoined;
    private boolean bookmark;
    private boolean isTop;
    private boolean isBan;
    private boolean isNice;
    private boolean isUserBanned;
    private String moderatorLevel;
    private String verified;
    private String verifyInfo;
    private String pics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(int lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(String supportNum) {
        this.supportNum = supportNum;
    }

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSupported() {
        return supported;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isIsGroupAdmin() {
        return isGroupAdmin;
    }

    public void setIsGroupAdmin(boolean isGroupAdmin) {
        this.isGroupAdmin = isGroupAdmin;
    }

    public boolean isIsGroupOwner() {
        return isGroupOwner;
    }

    public void setIsGroupOwner(boolean isGroupOwner) {
        this.isGroupOwner = isGroupOwner;
    }

    public boolean isGroupJoined() {
        return groupJoined;
    }

    public void setGroupJoined(boolean groupJoined) {
        this.groupJoined = groupJoined;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public boolean isIsBan() {
        return isBan;
    }

    public void setIsBan(boolean isBan) {
        this.isBan = isBan;
    }

    public boolean isIsNice() {
        return isNice;
    }

    public void setIsNice(boolean isNice) {
        this.isNice = isNice;
    }

    public boolean isIsUserBanned() {
        return isUserBanned;
    }

    public void setIsUserBanned(boolean isUserBanned) {
        this.isUserBanned = isUserBanned;
    }

    public String getModeratorLevel() {
        return moderatorLevel;
    }

    public void setModeratorLevel(String moderatorLevel) {
        this.moderatorLevel = moderatorLevel;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", content='" + content + '\'' +
                ", addTime=" + addTime +
                ", lastReplyTime=" + lastReplyTime +
                ", viewNum='" + viewNum + '\'' +
                ", supportNum='" + supportNum + '\'' +
                ", replyNum='" + replyNum + '\'' +
                ", nickname='" + nickname + '\'' +
                ", level='" + level + '\'' +
                ", headPic='" + headPic + '\'' +
                ", type='" + type + '\'' +
                ", supported=" + supported +
                ", groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", isGroupAdmin=" + isGroupAdmin +
                ", isGroupOwner=" + isGroupOwner +
                ", groupJoined=" + groupJoined +
                ", bookmark=" + bookmark +
                ", isTop=" + isTop +
                ", isBan=" + isBan +
                ", isNice=" + isNice +
                ", isUserBanned=" + isUserBanned +
                ", moderatorLevel='" + moderatorLevel + '\'' +
                ", verified='" + verified + '\'' +
                ", verifyInfo='" + verifyInfo + '\'' +
                ", pics=" + pics +
                '}';
    }
}
