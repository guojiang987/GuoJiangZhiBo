package com.rose.guojiangzhibo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rock.teachlibrary.ImageLoader;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.OneFragmentData;

import java.util.List;

/**
 * Created by xdy on 2016/10/19.
 */

public class MyNewestAdapter extends BaseAdapter {
    private Context context;
    private List<OneFragmentData> oneFragmentDatasList;

    public MyNewestAdapter(Context context, List<OneFragmentData> oneFragmentDatasList) {
        this.context = context;
        this.oneFragmentDatasList = oneFragmentDatasList;
        ImageLoader.init(context);
    }

    @Override
    public int getCount() {
        return oneFragmentDatasList.size()/3;
    }

    @Override
    public Object getItem(int position) {
        return oneFragmentDatasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;
        if(convertView==null){
            myViewHolder=new MyViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_newestfragment,parent,false);
            myViewHolder.image_newest_one= (ImageView) convertView.findViewById(R.id.image_newest_one);
            myViewHolder.image_newest_two= (ImageView) convertView.findViewById(R.id.image_newest_two);
            myViewHolder.image_newest_three= (ImageView) convertView.findViewById(R.id.image_newest_three);
            convertView.setTag(myViewHolder);
        }else{
            myViewHolder= (MyViewHolder) convertView.getTag();
        }
        //设置数据
        position=position*3;
        ImageLoader.display(myViewHolder.image_newest_one,oneFragmentDatasList.get(position).getHeadPic());
        ImageLoader.display(myViewHolder.image_newest_two,oneFragmentDatasList.get(position+1).getHeadPic());
        ImageLoader.display(myViewHolder.image_newest_three,oneFragmentDatasList.get(position+2).getHeadPic());

        return convertView;
    }
    class MyViewHolder{
        ImageView image_newest_one,image_newest_two,image_newest_three;
    }
}
