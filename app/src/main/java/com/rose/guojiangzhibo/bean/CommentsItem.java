package com.rose.guojiangzhibo.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I on 2016/10/21.
 */

public class CommentsItem {

    /**
     * id : 144429
     * uid : 1779634
     * content : 没经历过咋办[破涕为笑]
     * addTime : 1476417763
     * level : 0
     * nickname : ↘杠-傻傻傻傻
     * headPic : http://q.qlogo.cn/qqapp/101144047/206C6827C9DEE7AA439B8B1913FB5FCB/40
     * type : 1
     * pics : []
     * lzlReplys : [{"id":"144476","uid":"190052","content":"😂😂😂😂😂😂挺好挺好","addTime":1476425250,"level":"1","nickname":"小污婆输了","type":"1","lzlReplyId":"0","fReplyId":"144429"}]
     * isUserBanned : false
     */

    private String id;
    private String uid;
    private String content;
    private int addTime;
    private String level;
    private String nickname;
    private String headPic;
    private String type;
    private boolean isUserBanned;
    private List<?> pics;
    /**
     * id : 144476
     * uid : 190052
     * content : 😂😂😂😂😂😂挺好挺好
     * addTime : 1476425250
     * level : 1
     * nickname : 小污婆输了
     * type : 1
     * lzlReplyId : 0
     * fReplyId : 144429
     */

    private List<LzlReplysBean> lzlReplys;


    @Override
    public String toString() {
        return "CommentsItem{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", content='" + content + '\'' +
                ", addTime=" + addTime +
                ", level='" + level + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headPic='" + headPic + '\'' +
                ", type='" + type + '\'' +
                ", isUserBanned=" + isUserBanned +
                ", pics=" + pics +
                ", lzlReplys=" + lzlReplys +
                '}';
    }

    public void getJSONObject(JSONObject jsonObject){
        this.level = jsonObject.optString("level");
        this.content = jsonObject.optString("content");
        this.nickname = jsonObject.optString("nickname");
        this.addTime = jsonObject.optInt("addTime");
        this.headPic = jsonObject.optString("headPic");
        JSONArray array = jsonObject.optJSONArray("lzlReplys");
        lzlReplys  = new ArrayList<>();
        for(int i = 0;i<array.length();i++){
            LzlReplysBean lzlReplysBean = new LzlReplysBean();
            lzlReplysBean.getJSONObject(array.optJSONObject(i));
            lzlReplys.add(lzlReplysBean);
        }
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsUserBanned() {
        return isUserBanned;
    }

    public void setIsUserBanned(boolean isUserBanned) {
        this.isUserBanned = isUserBanned;
    }

    public List<?> getPics() {
        return pics;
    }

    public void setPics(List<?> pics) {
        this.pics = pics;
    }

    public List<LzlReplysBean> getLzlReplys() {
        return lzlReplys;
    }

    public void setLzlReplys(List<LzlReplysBean> lzlReplys) {
        this.lzlReplys = lzlReplys;
    }

    public static class LzlReplysBean {
        private String id;
        private String uid;
        private String content;
        private int addTime;
        private String level;
        private String nickname;
        private String type;
        private String lzlReplyId;
        private String fReplyId;

        @Override
        public String toString() {
            return "LzlReplysBean{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", content='" + content + '\'' +
                    ", addTime=" + addTime +
                    ", level='" + level + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", type='" + type + '\'' +
                    ", lzlReplyId='" + lzlReplyId + '\'' +
                    ", fReplyId='" + fReplyId + '\'' +
                    '}';
        }

        public void getJSONObject(JSONObject jsonObject){
            this.id = jsonObject.optString("id");
            this.nickname = jsonObject.optString("nickname");
            this.content = jsonObject.optString("content");
            this.addTime = jsonObject.optInt("addTime");
            this.level = jsonObject.optString("level");
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLzlReplyId() {
            return lzlReplyId;
        }

        public void setLzlReplyId(String lzlReplyId) {
            this.lzlReplyId = lzlReplyId;
        }

        public String getFReplyId() {
            return fReplyId;
        }

        public void setFReplyId(String fReplyId) {
            this.fReplyId = fReplyId;
        }
    }
}
