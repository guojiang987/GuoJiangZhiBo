package com.rose.guojiangzhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.teachlibrary.ImageLoader;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.OneFragmentData;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import static com.rose.guojiangzhibo.customclass.RoundHeader.toRoundBitmap;

/**
 * Created by xdy on 2016/10/18.
 */

public class MyOneListAdapter extends BaseAdapter {
    private Context context;
    private List<OneFragmentData> oneFragmentDataList;
    private ImageOptions imageOptions;

    public MyOneListAdapter(Context context, List<OneFragmentData> oneFragmentDataList) {
        this.context = context;
        this.oneFragmentDataList = oneFragmentDataList;
        imageOptions = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.RGB_565)
                .setCrop(false)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)//设置图片展示模式
                .setSquare(true)//是否显示为正方形
                .build();
    }

    @Override
    public int getCount() {
        return oneFragmentDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return oneFragmentDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder myViewHolder;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hotfragment, parent, false);
            myViewHolder.textOne = (TextView) convertView.findViewById(R.id.text_one);
            myViewHolder.textTwo = (TextView) convertView.findViewById(R.id.text_two);
            myViewHolder.textHot = (TextView) convertView.findViewById(R.id.text_hot);
            myViewHolder.imageHeader = (ImageView) convertView.findViewById(R.id.image_hot_head);
            myViewHolder.imageHot = (ImageView) convertView.findViewById(R.id.image_hot);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //设置数据
        OneFragmentData oneFragmentData = oneFragmentDataList.get(position);
        myViewHolder.textOne.setText(oneFragmentData.getNickname());
        myViewHolder.textTwo.setText(oneFragmentData.getAnnouncement());
        //videoview的图片
        Picasso.with(context).load(oneFragmentData.getHeadPic()).config(Bitmap.Config.RGB_565).into(myViewHolder.imageHot);
        //圆形头像
        x.image().bind(myViewHolder.imageHeader, oneFragmentData.getHeadPic(), new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                if (result != null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Bitmap circleBitmap = toRoundBitmap(bitmap);
                    myViewHolder.imageHeader.setImageBitmap(circleBitmap);
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
        return convertView;
    }

    class MyViewHolder {
        TextView textOne, textTwo, textHot;
        ImageView imageHeader, imageHot;

    }

}
