package com.rose.guojiangzhibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.bean.IType;
import com.rose.guojiangzhibo.bean.MyMessage;
import com.rose.guojiangzhibo.bean.YourMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cheng on 2016/10/23.
 */

public class RobotAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<IType> list;

    public RobotAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addItem(IType iType) {
        this.list.add(iType);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View inflate = LayoutInflater.from(context).inflate(R.layout.item_my_robot, parent, false);
                holder = new MyTypeHolder(inflate);
                break;
            case 1:
                View inflate1 = LayoutInflater.from(context).inflate(R.layout.item_your_robot, parent, false);
                holder = new YourTypeHolder(inflate1);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyTypeHolder) {
            ((MyTypeHolder) holder).text.setText(((MyMessage) list.get(position))
                    .getContext());
        } else if (holder instanceof YourTypeHolder) {
            ((YourTypeHolder) holder).text2.setText(((YourMessage) list.get(position))
                    .getContext());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyTypeHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;

        public MyTypeHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_my_robot);
            text = (TextView) itemView.findViewById(R.id.text_my_robot);
        }
    }

    class YourTypeHolder extends RecyclerView.ViewHolder {
        ImageView img2;
        TextView text2;

        public YourTypeHolder(View itemView) {
            super(itemView);
            img2 = (ImageView) itemView.findViewById(R.id.img_your_robot);
            text2 = (TextView) itemView.findViewById(R.id.text_your_robot);
        }
    }
}
