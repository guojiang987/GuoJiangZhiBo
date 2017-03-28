package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rose.guojiangzhibo.MainActivity;
import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.fragment.OneFragment;
import com.rose.guojiangzhibo.fragment.onefragments.popular.PopularFragment;
import com.rose.guojiangzhibo.fragment.onefragments.rich.RichFragment;
import com.rose.guojiangzhibo.fragment.onefragments.star.StarFragment;

public class ListActivity extends AppCompatActivity {

    private TextView textListStar;
    private TextView textListPopular;
    private TextView textListRich;
    private ImageView imageListBack;

    //要显示在界面上的fragment
    private StarFragment starFragment;
    private PopularFragment popularFragment;
    private RichFragment richFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransactionInit,fragmentTransactionStar,fragmentTransactionPopular,fragmentTransactionRich;
//    private ImageView imageOneList;
//    private ImageView imageTwoList;
//    private ImageView imageThreeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        //先显示第一个的界面
        fragmentTransactionInit=fragmentManager.beginTransaction();
        if(starFragment!=null){
            fragmentTransactionInit.show(starFragment);
        }else{
            starFragment=new StarFragment();
            fragmentTransactionInit.add(R.id.framelayout_list,starFragment);
        }
        fragmentTransactionInit.commit();
        //设置监听事件
        setListener();
    }

    private void setListener() {
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.text_liststar:
                        //点击之后首先初始化字体颜色和图标显示状态
                        textListStar.setTextColor(0xffdddddd);
                        textListPopular.setTextColor(0xffdddddd);
                        textListRich.setTextColor(0xffdddddd);
//                        imageOneList.setVisibility(View.GONE);
//                        imageTwoList.setVisibility(View.GONE);
//                        imageThreeList.setVisibility(View.GONE);
//                        imageOneList.setVisibility(View.VISIBLE);
                        ((TextView)v).setTextColor(0xffffffff);
                        //当前要显示的fragment
                        fragmentTransactionStar=fragmentManager.beginTransaction();
                        hideFragment(fragmentTransactionStar);
                        if(starFragment!=null){
                            fragmentTransactionStar.show(starFragment);
                        }else{
                            starFragment=new StarFragment();
                            fragmentTransactionStar.add(R.id.framelayout_list,starFragment);
                        }
                        fragmentTransactionStar.commit();
                        break;
                    case R.id.text_listpopular:
                        textListStar.setTextColor(0xffdddddd);
                        textListPopular.setTextColor(0xffdddddd);
                        textListRich.setTextColor(0xffdddddd);
//                        imageOneList.setVisibility(View.GONE);
//                        imageTwoList.setVisibility(View.GONE);
//                        imageThreeList.setVisibility(View.GONE);
//                        imageTwoList.setVisibility(View.VISIBLE);
                        ((TextView)v).setTextColor(0xffffffff);

                        fragmentTransactionPopular=fragmentManager.beginTransaction();
                        hideFragment(fragmentTransactionPopular);
                        if(popularFragment!=null){
                            fragmentTransactionPopular.show(popularFragment);
                        }else{
                            popularFragment=new PopularFragment();
                            fragmentTransactionPopular.add(R.id.framelayout_list,popularFragment);
                        }
                        fragmentTransactionPopular.commit();
                        break;
                    case R.id.text_listrich:
                        textListStar.setTextColor(0xffdddddd);
                        textListPopular.setTextColor(0xffdddddd);
                        textListRich.setTextColor(0xffdddddd);
//                        imageOneList.setVisibility(View.GONE);
//                        imageTwoList.setVisibility(View.GONE);
//                        imageThreeList.setVisibility(View.GONE);
//                        imageThreeList.setVisibility(View.VISIBLE);
                        ((TextView)v).setTextColor(0xffffffff);

                        fragmentTransactionRich=fragmentManager.beginTransaction();
                        hideFragment(fragmentTransactionRich);
                        if(richFragment!=null){
                            fragmentTransactionRich.show(richFragment);
                        }else{
                            richFragment=new RichFragment();
                            fragmentTransactionRich.add(R.id.framelayout_list,richFragment);
                        }
                        fragmentTransactionRich.commit();
                        break;
                    case R.id.image_listback:
                        Intent intent=new Intent(ListActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        };
        textListStar.setOnClickListener(onClickListener);
        textListPopular.setOnClickListener(onClickListener);
        textListRich.setOnClickListener(onClickListener);
        imageListBack.setOnClickListener(onClickListener);
    }

    private void initView() {
        textListStar = (TextView) findViewById(R.id.text_liststar);
        textListPopular = (TextView) findViewById(R.id.text_listpopular);
        textListRich = (TextView) findViewById(R.id.text_listrich);
        imageListBack = (ImageView) findViewById(R.id.image_listback);
//        imageOneList = (ImageView) findViewById(R.id.image_onelist);
//        imageTwoList = (ImageView) findViewById(R.id.image_twolist);
//        imageThreeList = (ImageView) findViewById(R.id.image_threelist);

        //初始化字体颜色、图标显示状态
        textListStar.setTextColor(0xffffffff);
        textListPopular.setTextColor(0xffdddddd);
        textListRich.setTextColor(0xffdddddd);
//        imageOneList.setVisibility(View.VISIBLE);
//        imageTwoList.setVisibility(View.GONE);
//        imageThreeList.setVisibility(View.GONE);

        fragmentManager=getSupportFragmentManager();

    }
    public void hideFragment(FragmentTransaction fragmentTransaction){
        if(starFragment!=null){
            fragmentTransaction.hide(starFragment);
        }
        if(popularFragment!=null){
            fragmentTransaction.hide(popularFragment);
        }
        if(richFragment!=null){
            fragmentTransaction.hide(richFragment);
        }
    }
}
