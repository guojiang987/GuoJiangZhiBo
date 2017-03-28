package com.rose.guojiangzhibo.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Cheng on 2016/10/22.
 */
@Table(name = "myuser")
public class User {
    @Column(name = "username", property = "not null")
    private String username;
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "password", property = "not null")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {

    }

    public User(String username, int id, String password) {
        this.username = username;
        this.id = id;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
