package com.rose.guojiangzhibo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
 import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.activity.ContentActivity;
import com.rose.guojiangzhibo.bean.SelectionItem;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by I on 2016/10/17.
 */
public class SelectionAdapter extends BaseAdapter   {
    private Context context;
    ImageOptions imageOptions;
    private List<List<SelectionItem>> list = null;
    public SelectionAdapter(Context context) {
        this.context = context;

        list = new ArrayList<>();
        x.view().inject((Activity) context);
          imageOptions =  new ImageOptions.Builder()

                .setConfig(Bitmap.Config.RGB_565)
                .setCrop(false)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)//设置图片展示模式
                .setSquare(true)//是否显示为正方形
                .build();
    }

    public void setList(List<List<SelectionItem>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.selectionitem,parent,false);
            View view1 = convertView.findViewById(R.id.item1_selection);
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ContentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("UserItem",list.get(position).get(0));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            View view2 = convertView.findViewById(R.id.item2_selection);
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ContentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("UserItem",list.get(position).get(1));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            viewHolder.img1 = (ImageView) convertView.findViewById(R.id.img1_selection);
            viewHolder.title1 = (TextView) convertView.findViewById(R.id.title1_selection);
            viewHolder.content1 = (TextView) convertView.findViewById(R.id.content1_selection);
            viewHolder.praise1 = (TextView) convertView.findViewById(R.id.praise1_selection);
            viewHolder.comment1 = (TextView) convertView.findViewById(R.id.comment1_selection);

            viewHolder.img2 = (ImageView) convertView.findViewById(R.id.img2_selection);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.title2_selection);
            viewHolder.content2 = (TextView) convertView.findViewById(R.id.content2_selection);
            viewHolder.praise2 = (TextView) convertView.findViewById(R.id.praise2_selection);
            viewHolder.comment2 = (TextView) convertView.findViewById(R.id.comment2_selection);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        List<SelectionItem> l = list.get(position);
        SelectionItem item1 = l.get(0);
        SelectionItem item2 = l.get(1);



        x.image().bind(viewHolder.img1,item1.getPics(),imageOptions);
        viewHolder.title1.setText(item1.getNickname());
        String content1 = item1.getContent();
        content1 = content1.substring(content1.indexOf(">")+1);
        viewHolder.content1.setText(content1);
        viewHolder.praise1.setText(item1.getSupportNum());
        viewHolder.comment1.setText(item1.getReplyNum());

        x.image().bind(viewHolder.img2,item2.getPics(),imageOptions);
        viewHolder.title2.setText(item2.getNickname());

        String content2 = item2.getContent();
        content2 = content2.substring(content2.indexOf(">")+1);
        viewHolder.content2.setText(content2);
        viewHolder.praise2.setText(item2.getSupportNum());
        viewHolder.comment2.setText(item2.getReplyNum());
        return convertView;
    }



    class ViewHolder{
        ImageView img1 = null;
        TextView title1 = null;
        TextView content1 = null;
        TextView praise1 = null;
        TextView comment1 = null;

        ImageView img2 = null;
        TextView title2 = null;
        TextView content2 = null;
        TextView praise2 = null;
        TextView comment2 = null;


    }
}
