package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.fragment.onefragments.personal.FanFragment;
import com.rose.guojiangzhibo.fragment.onefragments.personal.FocusFragment;
import com.rose.guojiangzhibo.fragment.onefragments.personal.TrendsFragment;

import org.xutils.common.Callback;
import org.xutils.x;

import static com.rose.guojiangzhibo.customclass.RoundHeader.toRoundBitmap;

public class PersonalActivity extends AppCompatActivity {

//    //详细信息页面返回到榜单页面
//    @BindView(R.id.image_perosonback)
//    ImageView imagePerosonback;
//    //详细信息的大头像
//    @BindView(R.id.image_personbigheader)
//    ImageView imagePersonbigheader;
//    //详细信息的名字/昵称
//    @BindView(R.id.text_personbigname)
//    TextView textPersonbigname;
//    //详细信息的动态数目
//    @BindView(R.id.text_trendnumber)
//    TextView textTrendnumber;
//    //详细信息的粉丝数目
//    @BindView(R.id.text_fannumber)
//    TextView textFannumber;
//    //详细信息的关注数据
//    @BindView(R.id.text_focusnumber)
//    TextView textFocusnumber;
//    //动态
//    @BindView(R.id.text_persontrend)
//    TextView textPersontrend;
//    //粉丝
//    @BindView(R.id.text_personfan)
//    TextView textPersonfan;
//    //关注
//    @BindView(R.id.text_personfocus)
//    TextView textPersonfocus;
//    //底部一栏关注的图标
//    @BindView(R.id.image_personattention)
//    ImageView imagePersonattention;
//    //底部一栏关注
//    @BindView(R.id.text_personattention)
//    TextView textPersonattention;
//    //底部私信图标
//    @BindView(R.id.image_privatetalk)
//    ImageView imagePrivatetalk;
//    //底部私信
//    @BindView(R.id.text_privatetalk)
//    TextView textPrivatetalk;
//    //底部拉黑图标
//    @BindView(R.id.image_defriend)
//    ImageView imageDefriend;
//    //底部拉黑
//    @BindView(R.id.text_defriend)
//    TextView textDefriend;
//    //右上角进入房间
//    @BindView(R.id.text_personenter)
//    TextView textPersonenter;
//    //动态、粉丝、关注切换的三个页面
//    @BindView(R.id.framelayout_personal)
//    FrameLayout framelayoutPersonal;


    private String uid;
    private String headerPic;
    private String name;

    private TrendsFragment trendsFragment;
    private FanFragment fanFragment;
    private FocusFragment focusFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransactionInit, fragmentTransactionTrend,
            fragmentTransactionFan, fragmentTransactionFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
//        ButterKnife.bind(this);
        uid = getIntent().getExtras().getString("uid");
        headerPic = getIntent().getExtras().getString("headerPic");
        name = getIntent().getExtras().getString("name");
        Bundle bundle=new Bundle();
        bundle.putString("uid",uid);
        //开启显示第一个页面的事务
        fragmentManager = getSupportFragmentManager();
        fragmentTransactionInit = fragmentManager.beginTransaction();

        //先显示第一个界面
        if (trendsFragment != null) {
            fragmentTransactionInit.show(trendsFragment);
        } else {
            trendsFragment = new TrendsFragment();
            //activity给fragment传值
            trendsFragment.setArguments(bundle);
            fragmentTransactionInit.add(R.id.framelayout_personal, trendsFragment);
        }
        fragmentTransactionInit.commit();
        initView();
    }


    private void initView() {
//        textPersonbigname.setText(name);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                x.image().bind(imagePersonbigheader, headerPic, new Callback.CommonCallback<Drawable>() {
//                    @Override
//                    public void onSuccess(Drawable result) {
//                        if (result != null) {
//                            BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
//                            Bitmap bitmap = bitmapDrawable.getBitmap();
//                            Bitmap circleBitmap = toRoundBitmap(bitmap);
//                            imagePersonbigheader.setImageBitmap(circleBitmap);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//
//            }
//        }).start();

    }



//    @OnClick({R.id.image_perosonback, R.id.image_personbigheader, R.id.text_persontrend, R.id.text_personfan,
//            R.id.text_personfocus, R.id.image_personattention, R.id.text_personattention, R.id.image_privatetalk,
//            R.id.text_privatetalk, R.id.image_defriend, R.id.text_defriend})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.image_perosonback:
//                //点击回退
//                Intent intent=new Intent(this,ListActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.image_personbigheader:
//
//                break;
//            case R.id.text_persontrend:
//                //显示当前的页面
//                fragmentTransactionTrend = fragmentManager.beginTransaction();
//                hideFragment(fragmentTransactionTrend);
//                if (trendsFragment != null) {
//                    fragmentTransactionTrend.show(trendsFragment);
//                } else {
//                    trendsFragment = new TrendsFragment();
//                    fragmentTransactionTrend.add(R.id.framelayout_personal, trendsFragment);
//                }
//                fragmentTransactionTrend.commit();
//                break;
//            case R.id.text_personfan:
//
//                fragmentTransactionFan = fragmentManager.beginTransaction();
//                hideFragment(fragmentTransactionFan);
//                if (fanFragment != null) {
//                    fragmentTransactionFan.show(fanFragment);
//                } else {
//                    fanFragment = new FanFragment();
//                    fragmentTransactionFan.add(R.id.framelayout_personal, fanFragment);
//                }
//                fragmentTransactionFan.commit();
//                break;
//            case R.id.text_personfocus:
//
//                fragmentTransactionFocus = fragmentManager.beginTransaction();
//                hideFragment(fragmentTransactionFocus);
//                if (focusFragment != null) {
//                    fragmentTransactionFocus.show(focusFragment);
//                } else {
//                    focusFragment = new FocusFragment();
//                    fragmentTransactionFocus.add(R.id.framelayout_personal, focusFragment);
//                }
//                fragmentTransactionFocus.commit();
//                break;
//            case R.id.image_personattention:
//
//                break;
//            case R.id.text_personattention:
//                break;
//            case R.id.image_privatetalk:
//                break;
//            case R.id.text_privatetalk:
//                break;
//            case R.id.image_defriend:
//                break;
//            case R.id.text_defriend:
//                break;
//        }
//    }

    public void hideFragment(FragmentTransaction fragmentTransaction) {
        if (trendsFragment != null) {
            fragmentTransaction.hide(trendsFragment);
        }
        if (fanFragment != null) {
            fragmentTransaction.hide(fanFragment);
        }
        if (focusFragment != null) {
            fragmentTransaction.hide(focusFragment);
        }
    }
}
