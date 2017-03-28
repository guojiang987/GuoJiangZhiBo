package com.rose.guojiangzhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.CommentsItem;

import org.w3c.dom.Text;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.rose.guojiangzhibo.R.id.view;

/**
 * Created by I on 2016/10/21.
 */

public class CommentAdapter extends BaseAdapter {
    private int[] level = {R.mipmap.userlevel_0,
            R.mipmap.userlevel_1,
            R.mipmap.userlevel_2,
            R.mipmap.userlevel_3,
            R.mipmap.userlevel_4,
            R.mipmap.userlevel_5,
            R.mipmap.userlevel_6,
            R.mipmap.userlevel_7,
            R.mipmap.userlevel_8,
            R.mipmap.userlevel_9,
            R.mipmap.userlevel_10,
            R.mipmap.userlevel_11,
            R.mipmap.userlevel_12,
            R.mipmap.userlevel_13,
            R.mipmap.userlevel_14,
            R.mipmap.userlevel_15,
            R.mipmap.userlevel_16,
            R.mipmap.userlevel_17,
            R.mipmap.userlevel_18};
    private List<CommentsItem> list;
    private Context context;
    private ImageOptions imageOptions = null;

    public CommentAdapter(List<CommentsItem> list, Context context) {
        this.list = list;
        this.context = context;
        imageOptions = new ImageOptions.Builder()
                .setCrop(false)
                .setConfig(Bitmap.Config.RGB_565)
                .setImageScaleType(ImageView.ScaleType.CENTER_INSIDE)
                .setSquare(false)
                .build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CommentsItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.commentslayout, parent, false);
            viewHolder.imgHeader = (ImageView) convertView.findViewById(R.id.headimg_comments);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.username_comments);
            viewHolder.imgLevel = (ImageView) convertView.findViewById(R.id.level_comment);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.date_comment);
            viewHolder.txtFloor = (TextView) convertView.findViewById(R.id.floor_comment);
            viewHolder.imgComment = (ImageView) convertView.findViewById(R.id.img_comment);
            viewHolder.comment = (TextView) convertView.findViewById(R.id.content_comment);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.comments_comments);
            viewHolder.textlist = new ArrayList<>();
            for(int i = 0;i<getItem(position).getLzlReplys().size();i++) {
                CommentsItem.LzlReplysBean lzlReplysBean = (CommentsItem.LzlReplysBean) getItem(position).getLzlReplys().get(i);
                View view = LayoutInflater.from(context).inflate(R.layout.comments2, null);
                TextView textView = (TextView) view.findViewById(R.id.username_comments2);
                Html.ImageGetter imageGetter = new Html.ImageGetter() {
                    public Drawable getDrawable(String source) {
                        int id = Integer.parseInt(source);
                        //根据id从资源文件中获取图片对象
                        Drawable d = context.getResources().getDrawable(id);
                        d.setBounds(0, 0, 20, 20);
                        return d;
                    }
                };
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd号");
                CharSequence charSequence = Html.fromHtml("<html><font color='#DD642B'>"+lzlReplysBean.getNickname()+":</font>"
                        +"<font size='12px'>"+lzlReplysBean.getContent()+"</font>"
                        +"<img src='" + level[Integer.parseInt(lzlReplysBean.getLevel())]
                        +"'/><font size='12px'>"+simpleDateFormat.format(lzlReplysBean.getAddTime()*1000)+"</font>"
                        + "<html>", imageGetter, null);
                textView.setText(charSequence);
                textView.setTextSize(12);
                viewHolder.linearLayout.addView(view);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CommentsItem commentsItem = list.get(position);
        x.image().bind(viewHolder.imgHeader, commentsItem.getHeadPic());
        viewHolder.userName.setText(commentsItem.getNickname());
        viewHolder.imgLevel.setImageResource(level[Integer.parseInt(commentsItem.getLevel())]);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd号");
        viewHolder.txtDate.setText(simpleDateFormat.format(commentsItem.getAddTime()*1000));
        viewHolder.txtFloor.setText("第" + (position + 1) + "楼");
        String content = commentsItem.getContent();
         if (content.contains("http")) {
            String img = content.substring(content.indexOf("http"));
            img = img.substring(0, img.indexOf("\""));
             x.image().bind(viewHolder.imgComment, img, imageOptions);
            viewHolder.imgComment.setVisibility(View.VISIBLE);
            content = content.substring(content.indexOf(">"));
        } else {
            viewHolder.imgComment.setVisibility(View.GONE);
            viewHolder.comment.setText(content);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imgHeader;
        TextView userName;
        ImageView imgLevel;
        TextView txtDate;
        TextView txtFloor;
        ImageView imgComment;
        TextView comment;
        LinearLayout linearLayout;
        List<TextView> textlist;
    }
}
