package com.rose.guojiangzhibo.bean;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by I on 2016/10/17.
 */

public class SelectionHeaderItem implements Serializable {

    /**
     * id : 3
     * uid : 153428
     * name : 娱乐八卦
     * detail : 临兵斗者皆阵列在前，决定是你了小果酱！
     * logo : http://static.guojiang.tv/app/upload/2016_03_31/51c2deaf37e5422d53c34a097a1dce4b.jpg
     * background : http://static.guojiang.tv/app/upload/2016_04_09/a381b4dc24182c3ae13d69662f4c6863.jpg
     * postCount : 6429
     * status : 1
     * joined : true
     * hot : 76761
     * memberTotal : 351658
     * activeNeed : 30
     * isOwner : false
     * isAdmin : false
     * nickname : 好吃懒做
     */

    private String id;
    private String uid;
    private String name;
    private String detail;
    private String logo;
    private String background;
    private String postCount;
    private String status;
    private boolean joined;
    private String hot;
    private String memberTotal;
    private int activeNeed;
    private boolean isOwner;
    private boolean isAdmin;
    private String nickname;

    public void getJSONObject(JSONObject jsonObject){
        this.id= jsonObject.optString("id");
        this.name = jsonObject.optString("name");
        this.logo = jsonObject.optString("logo");
        this.detail = jsonObject.optString("detail");
        this.background = jsonObject.optString("background");
        this.nickname = jsonObject.optString("nickname");
        this.postCount =jsonObject.optString("postCount");//帖子
        this.hot = jsonObject.optString("hot");//人气
        this.memberTotal = jsonObject.optString("memberTotal");
         }

    @Override
    public String toString() {
        return "SelectionHeaderItem{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", logo='" + logo + '\'' +
                ", background='" + background + '\'' +
                ", postCount='" + postCount + '\'' +
                ", status='" + status + '\'' +
                ", joined=" + joined +
                ", hot='" + hot + '\'' +
                ", memberTotal='" + memberTotal + '\'' +
                ", activeNeed=" + activeNeed +
                ", isOwner=" + isOwner +
                ", isAdmin=" + isAdmin +
                ", nickname='" + nickname + '\'' +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(String memberTotal) {
        this.memberTotal = memberTotal;
    }

    public int getActiveNeed() {
        return activeNeed;
    }

    public void setActiveNeed(int activeNeed) {
        this.activeNeed = activeNeed;
    }

    public boolean isIsOwner() {
        return isOwner;
    }

    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
