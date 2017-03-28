package com.rose.guojiangzhibo.urlconfig;

/**
 * Created by I on 2016/10/17.
 */

public class ConfigURL {
  public static String getSelcetion(){
   return "http://app.guojiang.tv/groupPost/getNiceposts?page=0&limit=40";
  }
    public static String getSelectionHeader(){
        return "http://app.guojiang.tv/group/getRecommendGroups";
    }
    public static String getCircleContent(){
        return "http://app.guojiang.tv/groupPost/getPosts?page=0&sort=last_reply_time&limit=20";
    }
    public static String getCircle(){
        return "http://app.guojiang.tv/group/getGroupDetail?groupId=";
    }
    public static String getGroup(){
        return "http://app.guojiang.tv/groupPost/getPosts?page=0&sort=last_reply_time&limit=20&groupId=";
    }
    public static String getComments1(){
        return "http://app.guojiang.tv/groupPost/getPostReplys?postId=" ;
    }
    public static String getComments2() {
     return   "&page=0&limit=20";
    }
}
