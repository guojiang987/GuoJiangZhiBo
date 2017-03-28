package com.rose.guojiangzhibo.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by I on 2016/10/17.
 */

public class SelectionItem  extends  UserItem implements Serializable {

    /**
     * id : 83425
     * uid : 190052
     * content :
     #一个脑残粉顶十黑，你最讨厌谁家的脑残粉呢？#
     在网络世界里，最惹人嫌的大概就属“脑残粉”了。他们队伍庞大，撕起逼来分分钟让人以为是哪来的邪教教徒。最擅长的撕逼语言是“你们懂什么，他有多努力你知道吗？”或者是“你行你上啊，不行别逼逼”，又或者“你就是嫉妒，你就是想红，你就是想出名”。（此处应有白眼）
     脑残粉是因爱而盲目，为了自家的偶像豁出性命都在所不惜。他们本来作为真爱粉丝是很好的存在，但盲目程度太深，有时候路人说一句很客观很普通的个人看法，只要不是赞扬他们喜欢的偶像，就能立刻遭到脑残粉的攻击。在他们的眼里，偶像什么都是好的，做什么都是对的。
     你遭遇过脑残粉的攻击吗？你最讨厌谁家的脑残粉呢？欢迎在评论底下留言，我将会在后台随机抽取十名用户，每人送出1000泡泡。

     参与时间：2016/10/14至2016/10/19下午16:00
     参与方式：在评论底下说出你最讨厌谁家的脑残粉，讲一下与脑残粉撕逼的经历
     抽奖方式：后台随机抽出10位幸运用户
     活动奖励：1000泡泡（10名）

     欢迎大家踊跃参与
     * addTime : 1476415056
     * lastReplyTime : 1476675824
     * viewNum : 649
     * supportNum : 16
     * replyNum : 123
     * pics : ["http://static.guojiang.tv/app/upload/2016_10_14/ec9b2e89c6b2251637bfe438100318dd.jpg?w=1080&h=1080"]
     * nickname : 小污婆输了
     * level : 1
     * headPic : http://static.guojiang.tv/app/upload/2016_10_11/b27457cbb65068a442b5c833fcf48d28.jpg
     * type : 1
     * supported : false
     * groupId : 3
     * groupName : 娱乐八卦
     * isGroupAdmin : false
     * isGroupOwner : false
     * groupJoined : false
     * bookmark : false
     * isTop : false
     * isBan : false
     * isNice : false
     * isUserBanned : false
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
    private String pics;


    @Override
    public String toString() {
        return "SelectionItem{" +
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
                ", pics='" + pics + '\'' +
                '}';
    }

    public  void getJSONObject(JSONObject jsonObject){
        this.id = jsonObject.optString("id");
        this.content = jsonObject.optString("content");
        this.supportNum = jsonObject.optString("supportNum");
        this.replyNum = jsonObject.optString("replyNum");
        try {
            this.pics = (String) jsonObject.optJSONArray("pics").get(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.nickname = jsonObject.optString("nickname");
        this.addTime = jsonObject.optInt("addTime");
        this.headPic = jsonObject.optString("headPic");
        this.groupName = jsonObject.optString("groupName");
    }



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

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
}
