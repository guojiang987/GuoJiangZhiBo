package com.rose.guojiangzhibo.bean;

/**
 * Created by Cheng on 2016/10/23.
 */

public class MyMessage implements IType {
    private String context;
    private String icon;


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int getType() {
        return 0;
    }
}
