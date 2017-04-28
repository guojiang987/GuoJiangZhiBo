package com.rose.guojiangzhibo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.guojiangzhibo.MyApplication;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.User;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistActivity extends AppCompatActivity {

    private List<Map<String, String>> users = null;
    private EditText editusername;
    private EditText editpassword;
    private EditText editpassagain;

    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        editusername = (EditText) findViewById(R.id.edit_username_login);
        editpassword = (EditText) findViewById(R.id.edit_password_login);
        editpassagain = (EditText) findViewById(R.id.editpassagain_regist);

        users = new ArrayList<Map<String, String>>();
        //创建出一个数据库
        dbManager = x.getDb(MyApplication.getMyApplication().getDaoConfig());
        addData("admin", "123");
    }

    public void onClickregist(View view) {
        String rusername = editusername.getText() + "";
        String rpassword = editpassword.getText() + "";
        String rpasswordagain = editpassagain.getText() + "";
        try {
            List<User> userall = dbManager.findAll(User.class);
             for (int i = 0; i < userall.size(); i++) {
              if ((userall.get(i).getUsername() + "").equals(rusername)) {
                Toast.makeText(this, "账号已存在", Toast.LENGTH_SHORT).show();
              return;
            }
              }
            if (rusername != null && rpassword.equals(rpasswordagain)) {
                addData(rusername, rpassword);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
                return;
            } else {
                Toast.makeText(this, "两次密码不相同", Toast.LENGTH_SHORT).show();
                return;
            }

        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    private void addData(String username, String psd) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(psd);
        try {
            dbManager.save(user);
            Log.i("--", "cl");
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


}
