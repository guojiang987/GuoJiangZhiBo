package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.guojiangzhibo.MyApplication;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.User;
import com.rose.guojiangzhibo.util.SharePreferenceUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity implements PlatformActionListener {
    private EditText edit_username_login;
    private EditText edit_password_login;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ShareSDK.initSDK(this);
        edit_username_login = (EditText) findViewById(R.id.edit_username_login);
        edit_password_login = (EditText) findViewById(R.id.edit_password_login);

    }

    public void onClickRegist(View view) {
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        Platform weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
        //每次都需要输入密码和帐号
        weibo.removeAccount(false);
        weibo.setPlatformActionListener(this);
        weibo.authorize();
        //不会调用客户端
        weibo.SSOSetting(true);
        weibo.showUser(null);//执行登录，登录后在回调里面获取用户资料
        //weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料
    }

    public void onClickTwo(View view) {
        Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
        //每次都需要输入密码账号
        qq.removeAccount(false);
        qq.setPlatformActionListener(this);
        qq.showUser(null);
    }

    public void onClickThree(View view) {
        Platform weixin = ShareSDK.getPlatform(this, Wechat.NAME);
        weixin.removeAccount(false);
        weixin.setPlatformActionListener(this);
        weixin.showUser(null);
    }

    public void onClicklogin(View view) {
        DbManager db = x.getDb(MyApplication.getMyApplication().getDaoConfig());
        username = edit_username_login.getText() + "";
        password = edit_password_login.getText() + "";
        try {
            List<User> userall = db.findAll(User.class);
            if (userall != null) {
                for (int i = 0; i < userall.size(); i++) {
                    if (username.equals(userall.get(i).getUsername() + "")) {
                        if (password.equals(userall.get(i).getPassword() + "")) {
                            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            SharePreferenceUtil.saveOrUpdateAttribute(this,"UID","uidflag",true);
                            finish();
                            return;
                        } else {
                            Toast.makeText(this, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                }
            } else {
                Toast.makeText(this, "请先注册", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "账号不存在", Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        //获取当前用户的资料
//        if (action == Platform.ACTION_USER_INFOR) {
//            PlatformDb platDB = platform.getDb();//获取数平台数据DB
//            //通过DB获取各种数据
//
//            platDB.getToken();
//            platDB.getUserGender();
//            platDB.getUserIcon();
//            platDB.getUserId();
//            platDB.getUserName();
//            textView.setText("token" + platDB.getToken() + "name" + platDB.getUserName());
//        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }


}
