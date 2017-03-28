package com.rose.guojiangzhibo.bean;

/**
 * Created by xdy on 2016/10/20.
 */

public class ListData {


    /**
     * nickname : 詹老公
     * level : 23
     * headPic : http://static.guojiang.tv/app/upload/2016_05_05/f10a00ac259b627bc82fe808bc5d6f97.jpg
     * uid : 192110
     * verified : false
     * verifyInfo :
     */

    private String nickname;
    private String level;
    private String headPic;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "ListData{" +
                "nickname='" + nickname + '\'' +
                ", level='" + level + '\'' +
                ", headPic='" + headPic + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
