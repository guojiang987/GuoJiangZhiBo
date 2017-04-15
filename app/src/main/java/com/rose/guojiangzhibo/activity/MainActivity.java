package com.rose.guojiangzhibo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rose.guojiangzhibo.fragment.FourFragment;
import com.rose.guojiangzhibo.activity.RtmpPushActivity;
import com.rose.guojiangzhibo.fragment.FourFragment;
import com.rose.guojiangzhibo.fragment.OneFragment;
import com.rose.guojiangzhibo.fragment.ThreeFragment;
import com.rose.guojiangzhibo.fragment.TwoFragment;
import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup rgMenus;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction, fragmentTransactionInit;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private long firstTime = 0;
    private Button push;

    //显示当前的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //先显示第一个界面
        fragmentTransactionInit = fragmentManager.beginTransaction();
        if (oneFragment != null) {
            fragmentTransactionInit.show(oneFragment);
        } else {
            oneFragment = new OneFragment();
            fragmentTransactionInit.add(R.id.framelayout_main, oneFragment);
        }
        fragmentTransactionInit.commit();
    }

    private void initView() {
        rgMenus = (RadioGroup) findViewById(R.id.rg_main_menus);
        rgMenus.setOnCheckedChangeListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager = getSupportFragmentManager();
        push = (Button) findViewById(R.id.rtmppush);
        push.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (checkedId) {
            case R.id.rb_main_one:
                if (oneFragment != null) {
                    fragmentTransaction.show(oneFragment);
                } else {
                    oneFragment = new OneFragment();
                    fragmentTransaction.add(R.id.framelayout_main, oneFragment);
                }
                break;
            case R.id.rb_main_two:
                if (twoFragment != null) {
                    fragmentTransaction.show(twoFragment);
                } else {
                    twoFragment = new TwoFragment(this);
                    fragmentTransaction.add(R.id.framelayout_main, twoFragment);
                }
                break;
            case R.id.rb_main_three:
                if (threeFragment != null) {
                    fragmentTransaction.show(threeFragment);
                } else {
                    threeFragment = new ThreeFragment();
                    fragmentTransaction.add(R.id.framelayout_main, threeFragment);
                }
                break;
            case R.id.rb_main_four:
                if (fourFragment != null) {
                    fragmentTransaction.show(fourFragment);
                } else {
                    fourFragment = new FourFragment();
                    fragmentTransaction.add(R.id.framelayout_main, fourFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    //再按一次退出
    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1200) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            this.finish();
        }
    }

    public void hideFragment(FragmentTransaction fragmentTransaction) {
        if (oneFragment != null) {
            fragmentTransaction.hide(oneFragment);
        }
        if (twoFragment != null) {
            fragmentTransaction.hide(twoFragment);
        }
        if (threeFragment != null) {
            fragmentTransaction.hide(threeFragment);
        }
        if (fourFragment != null) {
            fragmentTransaction.hide(fourFragment);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RtmpPushActivity.class);
        startActivity(intent);

    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
