package com.rose.guojiangzhibo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;

import org.xutils.common.Callback;
import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.rose.guojiangzhibo.CircleBitmapTool.toRoundBitmap;


public class VideoViewPlayingActivity extends Activity {
    private void initView() {
        text_name = (TextView) findViewById(R.id.text_name);
        text_personnumber = (TextView) findViewById(R.id.text_personnumber);
        text_paopaonumber = (TextView) findViewById(R.id.text_paopaonumber);
        image_header = (ImageView) findViewById(R.id.image_header);
        image_more = (ImageView) findViewById(R.id.image_more);
        image_close = (ImageView) findViewById(R.id.image_close);
        image_talkplay = (ImageView) findViewById(R.id.image_talkplay);
        image_flowerplay = (ImageView) findViewById(R.id.image_flowerplay);
        image_gift = (ImageView) findViewById(R.id.image_gift);
        image_shareplay = (ImageView) findViewById(R.id.image_shareplay);
    }

    private final String TAG = "VideoViewPlayingActivity";

    //主播昵称 观众人数 泡泡数
    private TextView text_name, text_personnumber, text_paopaonumber;
    //圆形头像 更多 关闭 输入文字 送花 礼物 分享 关注
    private ImageView image_header, image_more, image_close, image_talkplay, image_flowerplay, image_gift, image_shareplay;
    //弹幕
    private ListView listview_oneplayer;
    //接收fragment传过来的值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_play);
        initView();
        Intent data = getIntent();
        if (data == null) {
            return;
        }
        String rid = data.getStringExtra("rid");
        String headerPic = data.getStringExtra("headerPic");
        downBitmap(headerPic);
        text_name.setText(data.getStringExtra("nickname"));
        text_personnumber.setText("观众" + data.getStringExtra("onlineNum") + "人");
        text_paopaonumber.setText("泡泡数" + data.getStringExtra("mid"));

    }

    //分享
    private void showShare() {
        ShareSDK.initSDK(this);
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
        oks.show(this);
    }

    public void downBitmap(String headPic) {
        x.image().bind(image_header, headPic, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                if (result != null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Bitmap circleBitmap = toRoundBitmap(bitmap);
                    image_header.setImageBitmap(circleBitmap);
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
}