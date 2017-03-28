package com.rose.guojiangzhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.teachlibrary.ImageLoader;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.PersonalData;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.List;

import static com.rose.guojiangzhibo.customclass.RoundHeader.toRoundBitmap;

/**
 * Created by xdy on 2016/10/21.
 */

public class MyPersonalAdapter extends BaseAdapter {

    private Context context;
    private List<PersonalData> personalDatasList;

    public MyPersonalAdapter(Context context, List<PersonalData> personalDatasList) {
        this.context = context;
        this.personalDatasList = personalDatasList;
        ImageLoader.init(context);
    }

    @Override
    public int getCount() {
        return personalDatasList.size();
    }

    @Override
    public Object getItem(int position) {
        return personalDatasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder myViewHolder;
        if(convertView==null){
            myViewHolder=new MyViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_persontrends,parent,false);
            myViewHolder.text_personname= (TextView) convertView.findViewById(R.id.text_personname);
            myViewHolder.text_persontime= (TextView) convertView.findViewById(R.id.text_persontime);
            myViewHolder.text_praisenumber= (TextView) convertView.findViewById(R.id.text_praisenumber);
            myViewHolder.text_personmood= (TextView) convertView.findViewById(R.id.text_personmood);
            myViewHolder.text_talknumber= (TextView) convertView.findViewById(R.id.text_talknumber);
            myViewHolder.image_perosonheader= (ImageView) convertView.findViewById(R.id.image_perosonheader);
            myViewHolder.image_personpicture= (ImageView) convertView.findViewById(R.id.image_personpicture);
            convertView.setTag(myViewHolder);
        }else{
            myViewHolder= (MyViewHolder) convertView.getTag();
        }
        Log.i("position----",position+"");
        //设置数据
        myViewHolder.text_personname.setText(personalDatasList.get(position).getNickname());
        //TODO 发表的时间需要转换为年月
        myViewHolder.text_persontime.setText(personalDatasList.get(position).getLastReplyTime()+"");
        myViewHolder.text_personmood.setText(personalDatasList.get(position).getContent());
        myViewHolder.text_praisenumber.setText(personalDatasList.get(position).getSupportNum());
        myViewHolder.text_talknumber.setText(personalDatasList.get(position).getReplyNum());
        ImageLoader.display(myViewHolder.image_personpicture,personalDatasList.get(position).getPics());
        x.image().bind(myViewHolder.image_perosonheader, personalDatasList.get(position).getHeadPic(), new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                if (result != null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Bitmap circleBitmap = toRoundBitmap(bitmap);
                    myViewHolder.image_perosonheader.setImageBitmap(circleBitmap);
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
    class MyViewHolder{
        //头像、大图片
        ImageView image_perosonheader,image_personpicture;
        //昵称、发表时间、点赞个数、评论个数
        TextView text_personname,text_persontime,text_praisenumber,text_personmood,text_talknumber;
    }
}
