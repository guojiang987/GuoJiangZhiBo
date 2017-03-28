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

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.ListData;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.List;

import static com.rose.guojiangzhibo.customclass.RoundHeader.toRoundBitmap;

/**
 * Created by xdy on 2016/10/20.
 */

public class MyTopListAdapter extends BaseAdapter {

    private Context context;
    private List<ListData> listDatasList;
    private int LevelView[] = {R.mipmap.icon_rank_0, R.mipmap.icon_rank_1, R.mipmap.icon_rank_2, R.mipmap.icon_rank_3, R.mipmap.icon_rank_4, R.mipmap.icon_rank_5
            , R.mipmap.icon_rank_6, R.mipmap.icon_rank_7, R.mipmap.icon_rank_8, R.mipmap.icon_rank_9};
    private int ListViewLevel[] = {R.mipmap.userlevel_0, R.mipmap.userlevel_1, R.mipmap.userlevel_2, R.mipmap.userlevel_3, R.mipmap.userlevel_4,
            R.mipmap.userlevel_5, R.mipmap.userlevel_6, R.mipmap.userlevel_7, R.mipmap.userlevel_8, R.mipmap.userlevel_9,
            R.mipmap.userlevel_10, R.mipmap.userlevel_11, R.mipmap.userlevel_12, R.mipmap.userlevel_13, R.mipmap.userlevel_14, R.mipmap.userlevel_15,
            R.mipmap.userlevel_16, R.mipmap.userlevel_17, R.mipmap.userlevel_18, R.mipmap.user_anchor_0, R.mipmap.user_anchor_1, R.mipmap.user_anchor_2,
            R.mipmap.user_anchor_3, R.mipmap.user_anchor_4, R.mipmap.user_anchor_5, R.mipmap.user_anchor_6,
            R.mipmap.user_anchor_7, R.mipmap.user_anchor_8, R.mipmap.user_anchor_9, R.mipmap.user_anchor_10};


    public MyTopListAdapter(Context context, List<ListData> listDatasList) {
        this.context = context;
        this.listDatasList = listDatasList;
    }

    @Override
    public int getCount() {
        return listDatasList.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatasList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            myViewHolder.text_namelist = (TextView) convertView.findViewById(R.id.text_namelist);
            myViewHolder.image_headerlist = (ImageView) convertView.findViewById(R.id.image_headerlist);
            myViewHolder.image_ListLevel = (ImageView) convertView.findViewById(R.id.image_listlevel);
            myViewHolder.image_gift = (ImageView) convertView.findViewById(R.id.image_gift);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        //设置数据
        myViewHolder.text_namelist.setText(listDatasList.get(position).getNickname());
        myViewHolder.image_ListLevel.setImageResource(LevelView[position]);
        myViewHolder.image_gift.setImageResource(ListViewLevel[Integer.parseInt(listDatasList.get(position).getLevel())+1]);
        x.image().bind(myViewHolder.image_headerlist, listDatasList.get(position).getHeadPic(), new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                if (result != null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Bitmap circleBitmap = toRoundBitmap(bitmap);
                    myViewHolder.image_headerlist.setImageBitmap(circleBitmap);
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

    static class MyViewHolder {
        ImageView image_headerlist, image_ListLevel, image_gift;
        TextView text_namelist;
    }
}
