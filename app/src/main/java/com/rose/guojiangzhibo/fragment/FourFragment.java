package com.rose.guojiangzhibo.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rock.teachlibrary.http.HttpUtil;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.AboutActivity;
import com.rose.guojiangzhibo.activity.AdviceActivity;
import com.rose.guojiangzhibo.activity.BalanceActivity;
import com.rose.guojiangzhibo.activity.IncomeActivity;
import com.rose.guojiangzhibo.activity.RobotActivity;
import com.rose.guojiangzhibo.bean.CricleFour;
import com.rose.guojiangzhibo.customclass.RoundHeader;
import com.rose.guojiangzhibo.urlconfig.UrlConfigOne;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cheng.JPush.ExampleUtil;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment {
    public Bitmap cricleHead;
    private RelativeLayout myincome;
    private RelativeLayout balence;

    //好友分享

    private RelativeLayout mylevel;
    private RelativeLayout repay;
    private RelativeLayout collect;

    //意见反馈

    private RelativeLayout advice;
    private RelativeLayout custor;
    //关于软件
    private RelativeLayout setting;
    //圆形头像

    private ImageView cricleimg;
    private List<CricleFour> cricleFours;
    //消息推送
    private Button mInit;
    private Button mSetting;
    private Button mStopPush;
    private Button mResumePush;
    private Button mGetRid;
    private TextView mRegId;
    private EditText msgText;
    public static boolean isForeground = false;

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ShareSDK.initSDK(getActivity());
        View inflate = inflater.inflate(R.layout.fragment_four, container, false);
        initView(inflate);
        registerMessageReceiver();
        getDate();
        return inflate;
    }
    private void initView(View inflate) {
        //消息推送
        myincome = (RelativeLayout) inflate.findViewById(R.id.myincome_four);
        balence = (RelativeLayout) inflate.findViewById(R.id.balance_four);
        mylevel = (RelativeLayout) inflate.findViewById(R.id.mylevel_four);
        repay = (RelativeLayout) inflate.findViewById(R.id.repay_four);
        collect = (RelativeLayout) inflate.findViewById(R.id.collect_four);
        advice = (RelativeLayout) inflate.findViewById(R.id.advice_four);
        custor = (RelativeLayout) inflate.findViewById(R.id.customer_four);
        setting = (RelativeLayout) inflate.findViewById(R.id.setting_four);

        cricleimg = (ImageView) inflate.findViewById(R.id.img_fourfrag_head);
        cricleFours = new ArrayList<>();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.myincome_four:
                        Intent intent3 = new Intent(getActivity(), IncomeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.balance_four:
                        Intent intent4 = new Intent(getActivity(), BalanceActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.mylevel_four:
                        showShare();
                        break;
                    case R.id.repay_four:
                        Toast.makeText(getActivity(), "开启消息推送", Toast.LENGTH_SHORT).show();
                        init();
                        JPushInterface.resumePush(getActivity());
                        break;
                    //消息推送
                    case R.id.collect_four:
                        Toast.makeText(getActivity(), "关闭消息推送", Toast.LENGTH_SHORT).show();
                        JPushInterface.stopPush(getActivity());
                        break;
                    //意见反馈
                    case R.id.advice_four:
                        Intent intent2 = new Intent(getActivity(), AdviceActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.customer_four:
                        Intent intent5 = new Intent(getActivity(), RobotActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.setting_four:
                        Intent intent = new Intent(getActivity(), AboutActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        myincome.setOnClickListener(onClickListener);
        balence.setOnClickListener(onClickListener);
        mylevel.setOnClickListener(onClickListener);
        repay.setOnClickListener(onClickListener);
        collect.setOnClickListener(onClickListener);
        advice.setOnClickListener(onClickListener);
        custor.setOnClickListener(onClickListener);
        setting.setOnClickListener(onClickListener);

    }

    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("果酱直播");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://m.guojiang.tv");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("一起加入我们吧");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("m.guojiang.tv");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("好玩儿的直播平台");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://m.guojiang.tv");
        // 启动分享GUI
        oks.show(getActivity());
    }
    private void getDate() {
        HttpUtil.getStringAsync(UrlConfigOne.URL_CONTENT_HOTONE, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    Gson gson = new Gson();
                    CricleFour criclefour = gson.fromJson(result, CricleFour.class);
                    List<CricleFour.DataBean> datas = criclefour.getData();
                    for (CricleFour.DataBean idatas : datas) {
                        criclefour.getData().get(0).setHeadPic(idatas.getHeadPic());
                        cricleFours.add(criclefour);
                    }
                }
                x.image().bind(cricleimg, cricleFours.get(0).getData().get(0).getHeadPic(), new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {
                        if (result != null) {
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                            Bitmap bitmap = bitmapDrawable.getBitmap();

                            cricleHead = RoundHeader.toRoundBitmap(bitmap);
                            cricleimg.setImageBitmap(cricleHead);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }

            @Override
            public void onFinish() {

            }
        });
    }

    //消息推送
    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init() {
        JPushInterface.init(getActivity());
    }

    @Override
    public void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    public void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        getActivity().registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }

    private void setCostomMsg(String msg) {
        if (null != msgText) {
            msgText.setText(msg);
            msgText.setVisibility(android.view.View.VISIBLE);
        }
    }


}
