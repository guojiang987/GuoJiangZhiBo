package com.rose.guojiangzhibo.bean;

/**
 * Created by I on 2016/10/21.
 */

public abstract class UserItem {
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


    public abstract String toString();

    public abstract String getId() ;

    public abstract void setId(String id);

    public abstract String getUid();

    public abstract void setUid(String uid) ;


    public abstract String getContent();

    public abstract void setContent(String content);

    public abstract int getAddTime();

    public abstract void setAddTime(int addTime);

    public abstract int getLastReplyTime() ;

    public abstract void setLastReplyTime(int lastReplyTime);

    public abstract String getViewNum();

    public abstract void setViewNum(String viewNum) ;

    public abstract String getSupportNum() ;

    public abstract void setSupportNum(String supportNum) ;

    public abstract String getReplyNum() ;

    public abstract void setReplyNum(String replyNum) ;

    public abstract String getNickname() ;

    public abstract void setNickname(String nickname);

    public abstract String getLevel() ;

    public abstract void setLevel(String level)  ;

    public abstract String getHeadPic() ;

    public abstract void setHeadPic(String headPic);



    public abstract String getGroupName() ;

    public abstract void setGroupName(String groupName) ;



    public abstract String getPics();

    public abstract void setPics(String pics) ;
}
