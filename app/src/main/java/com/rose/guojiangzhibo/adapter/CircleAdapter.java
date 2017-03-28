package com.rose.guojiangzhibo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rose.guojiangzhibo.CircleBitmapTool;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.ContentActivity;
import com.rose.guojiangzhibo.bean.CircleIListItem;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static android.R.attr.id;


/**
 * Created by I on 2016/10/18.
 */

public class CircleAdapter extends BaseAdapter {
    private Context context;
    private List<CircleIListItem> list;
    ImageOptions imageOptions = null;

    public CircleAdapter(Context context, List<CircleIListItem> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
        x.view().inject((Activity) this.context);
        imageOptions = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.RGB_565)
                .setCrop(false)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)//设置图片展示模式
                .setSquare(true)//是否显示为正方形
                .build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CircleIListItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.circleitem, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ContentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("UserItem",list.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            viewHolder.headerImg = (ImageView) convertView.findViewById(R.id.headimg_circle);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.username_circle);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time_circle);
            viewHolder.circle = (TextView) convertView.findViewById(R.id.circlename_circle);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.pic_circle);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_circle);
            viewHolder.support = (TextView) convertView.findViewById(R.id.supportnum_cirlce);
            viewHolder.repay = (TextView) convertView.findViewById(R.id.repnum_circle);
            viewHolder.share = (ImageButton) convertView.findViewById(R.id.share_item);
            viewHolder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showShare(getItemId(position));
                }
            });
            
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CircleIListItem circleIListItem = getItem(position);

        final ViewHolder finalViewHolder = viewHolder;
        x.image().bind(viewHolder.headerImg, circleIListItem.getHeadPic(), new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                if (result != null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Bitmap circleBitmap = CircleBitmapTool.toRoundBitmap(bitmap);
                    finalViewHolder.headerImg.setImageBitmap(circleBitmap);
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
        viewHolder.userName.setText(circleIListItem.getNickname());
        Log.i("time",circleIListItem.getAddTime()+"current:"+ System.currentTimeMillis());
        int time = (int) System.currentTimeMillis() - 1000 * circleIListItem.getAddTime();
        Log.i("time====",time+"");
        SimpleDateFormat simpleDateFormat =null;

        if(time>1000*60*24){
            simpleDateFormat = new SimpleDateFormat("MM月dd日");
            viewHolder.time.setText(simpleDateFormat.format(circleIListItem.getAddTime()));
        }else
        if (time > 1000 * 60) {
              simpleDateFormat = new SimpleDateFormat("mm分钟前");
        viewHolder.time.setText(simpleDateFormat.format(time));
        } else {
              simpleDateFormat = new SimpleDateFormat("HH小时前");
        viewHolder.time.setText(simpleDateFormat.format(time));
        }

        viewHolder.circle.setText(circleIListItem.getGroupName());
        String content = circleIListItem.getContent();

        String text = content.substring(content.indexOf(">") + 1);
        x.image().bind(viewHolder.imageView, circleIListItem.getPics(), imageOptions);
        viewHolder.title.setText(text);
        viewHolder.support.setText(circleIListItem.getSupportNum());
        viewHolder.repay.setText(circleIListItem.getReplyNum());
        return convertView;
    }

    private void showShare(long id) {
        ShareSDK.initSDK(context);
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
        oks.setSite(context.getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://m.guojiang.tv/group/postDetail/id/"+id);
// 启动分享GUI
        oks.show(context);
    }


    class ViewHolder {
        ImageView headerImg;
        TextView userName;
        TextView time;
        TextView circle;
        ImageView imageView;
        TextView title;
        TextView support;
        TextView repay;
        ImageButton share;
    }

}
