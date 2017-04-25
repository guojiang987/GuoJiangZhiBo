package com.rose.guojiangzhibo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.adapter.CommentAdapter;
import com.rose.guojiangzhibo.adapter.ExpressionAdapter;
import com.rose.guojiangzhibo.bean.CommentsItem;
import com.rose.guojiangzhibo.bean.UserItem;
import com.rose.guojiangzhibo.urlconfig.ConfigURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class ContentActivity extends AppCompatActivity implements View.OnClickListener {
    private UserItem userItem;
    private ListView comments;
    private String url;
    private CommentAdapter commentAdapter;
    private ArrayList<CommentsItem> commentsList;
    private List<View> expressionPagerView;
    private ImageOptions imageOptions;
    private ImageButton expression;
    private ImageButton addpic;
    private EditText text;
    private Button sendbt;
    private ViewPager expressionView;
    private InputMethodManager inputMethodManager;//用来关闭键盘
    private ArrayList<int[]> list = new ArrayList<>();
    private int[] expressionsPage1 = {R.mipmap.emoji_1
            , R.mipmap.emoji_2
            , R.mipmap.emoji_3
            , R.mipmap.emoji_4
            , R.mipmap.emoji_5
            , R.mipmap.emoji_6
            , R.mipmap.emoji_7
            , R.mipmap.emoji_8
            , R.mipmap.emoji_9
            , R.mipmap.emoji_10
            , R.mipmap.emoji_11
            , R.mipmap.emoji_12
            , R.mipmap.emoji_13
            , R.mipmap.emoji_14
            , R.mipmap.emoji_15
            , R.mipmap.emoji_16
            , R.mipmap.emoji_17
            , R.mipmap.emoji_18
            , R.mipmap.emoji_19
            , R.mipmap.emoji_20
            , R.mipmap.emoji_21
            , R.mipmap.emoji_22
            , R.mipmap.emoji_23
            , R.mipmap.emoji_24
            , R.mipmap.emoji_25
            , R.mipmap.emoji_26
            , R.mipmap.emoji_27
            , R.mipmap.emoji_del};
    private int[] expressionsPage2 = {
            R.mipmap.emoji_1f47d,
            R.mipmap.emoji_2764,
            R.mipmap.emoji_1f494,
            R.mipmap.emoji_1f44f,
            R.mipmap.emoji_1f44a,
            R.mipmap.emoji_1f44c,
            R.mipmap.emoji_1f44d,
            R.mipmap.emoji_1f44e,
            R.mipmap.emoji_1f48b,
            R.mipmap.emoji_1f414,
            R.mipmap.emoji_1f40f,
            R.mipmap.emoji_1f43b,
            R.mipmap.emoji_1f437,
            R.mipmap.emoji_1f418,
            R.mipmap.emoji_1f412,
            R.mipmap.emoji_1f42f,
            R.mipmap.emoji_1f430,
            R.mipmap.emoji_1f433,
            R.mipmap.emoji_1f436,
            R.mipmap.emoji_1f446,
            R.mipmap.emoji_1f447,
            R.mipmap.emoji_1f448,
            R.mipmap.emoji_1f449,
            R.mipmap.emoji_1f48a,
            R.mipmap.emoji_1f35e,
            R.mipmap.emoji_1f353,
            R.mipmap.emoji_1f34c,
            R.mipmap.emoji_del
    };
    private int[] expressionsPage3 = {
            R.mipmap.emoji_1f34e,
            R.mipmap.emoji_1f345,
            R.mipmap.emoji_1f349,
            R.mipmap.emoji_1f366,
            R.mipmap.emoji_1f361,
            R.mipmap.emoji_26a1,
            R.mipmap.emoji_2600,
            R.mipmap.emoji_1f319,
            R.mipmap.emoji_1f31a,
            R.mipmap.emoji_1f31d,
            R.mipmap.emoji_1f375,
            R.mipmap.emoji_1f37c,
            R.mipmap.emoji_1f335,
            R.mipmap.emoji_1f4a9,
            R.mipmap.emoji_1f4a8,
            R.mipmap.emoji_1f648,
            R.mipmap.emoji_del
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        imageOptions = new ImageOptions.Builder()
                .setCrop(false)
                .setConfig(Bitmap.Config.RGB_565)
                .setSquare(false)
                .setImageScaleType(ImageView.ScaleType.CENTER_INSIDE)
                .build();
        initView();
        initExpression();
        getData();
    }

    private void initExpression() {
        expressionView = (ViewPager) findViewById(R.id.expressionview);
        final Html.ImageGetter imageGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                //根据id从资源文件中获取图片对象
                Drawable d = getApplicationContext().getResources().getDrawable(id);
                d.setBounds(0, 0, 20, 20);
                return d;
            }
        };
        expressionPagerView = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.expressionpageone, null);
        GridLayout page1 = (GridLayout) view1.findViewById(R.id.gridlayoutpage1);
        for(int i = 0;i<page1.getChildCount();i++){
            final ImageView img = (ImageView)page1.getChildAt(i);
            img.setClickable(true);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img.getDrawable();
                    CharSequence charSequence = Html.fromHtml("<src='"+img+"'/>",imageGetter,null);
                    text.setText(charSequence);
                }
            });
        }
        View view2 = LayoutInflater.from(this).inflate(R.layout.expressionpagetwo, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.expressionpagethree, null);
        expressionPagerView.add(view1);
        expressionPagerView.add(view2);
        expressionPagerView.add(view3);

        ExpressionAdapter expressionAdapter = new ExpressionAdapter(expressionPagerView);
        expressionView.setAdapter(expressionAdapter);
    }



    private void downComments() {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.optJSONArray("data");
                    commentsList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        CommentsItem commentsItem = new CommentsItem();
                        commentsItem.getJSONObject(array.optJSONObject(i));
                        commentsList.add(commentsItem);
                    }
                    setListView();

                } catch (JSONException e) {
                    e.printStackTrace();
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

    private void setListView() {
        View view = LayoutInflater.from(this).inflate(R.layout.commentsheader, null);
        addDate(view);
        commentAdapter = new CommentAdapter(commentsList, this);
        comments.setAdapter(commentAdapter);


    }

    private void addDate(View view) {
        ImageView headimg = (ImageView) view.findViewById(R.id.head_comment);
        x.image().bind(headimg, userItem.getHeadPic());
        TextView username = (TextView) view.findViewById(R.id.username_comment);
        username.setText(userItem.getNickname());
        TextView time = (TextView) view.findViewById(R.id.pushtime_comment);
        int t = userItem.getAddTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm分钟");
        String s = simpleDateFormat.format(System.currentTimeMillis() - t * 1000);
        Log.i("time", s);
        time.setText(s);
        TextView circlename = (TextView) view.findViewById(R.id.circlename_comment);
        circlename.setText(userItem.getGroupName());
        ImageView pic = (ImageView) view.findViewById(R.id.pic_comment);
        x.image().bind(pic, userItem.getPics(), imageOptions);
        TextView content = (TextView) view.findViewById(R.id.title_comment);
        String str = userItem.getContent();
        str = str.substring(str.indexOf(">") + 1);
        content.setText(str);
        TextView supportnum = (TextView) view.findViewById(R.id.supportnum_comment);
        supportnum.setText(userItem.getSupportNum());
        TextView repnum = (TextView) view.findViewById(R.id.repnum_comment);
        repnum.setText(userItem.getReplyNum());
        ImageButton sharebt = (ImageButton) view.findViewById(R.id.share_bt);
        comments.addHeaderView(view);
        Log.i("comments", userItem.toString());

    }

    private void initView() {
        comments = (ListView) findViewById(R.id.commments_user);
        expression = (ImageButton) findViewById(R.id.expressionbt_comments);
        expression.setOnClickListener(this);
        addpic = (ImageButton) findViewById(R.id.addpic);
        addpic.setOnClickListener(this);
        text = (EditText) findViewById(R.id.editText);
        sendbt = (Button) findViewById(R.id.send);
        sendbt.setOnClickListener(this);
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userItem = (UserItem) bundle.getSerializable("UserItem");
        url = ConfigURL.getComments1() + userItem.getId() + ConfigURL.getComments2();
        downComments();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expressionbt_comments:
                if(expressionView.getVisibility() == View.GONE){
                    expressionView.setVisibility(View.VISIBLE);
                }
                inputMethodManager.hideSoftInputFromWindow(text.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.addpic:
                if(expressionView.getVisibility() == View.VISIBLE){
                    expressionView.setVisibility(View.GONE);
                }
                inputMethodManager.hideSoftInputFromWindow(text.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 2);
                break;
            case R.id.editText:
                if(expressionView.getVisibility() == View.VISIBLE){
                    expressionView.setVisibility(View.GONE);
                }
                break;
            case R.id.send:
                if(expressionView.getVisibility() == View.VISIBLE){
                    expressionView.setVisibility(View.GONE);
                }
                inputMethodManager.hideSoftInputFromWindow(text.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                break;
            default:
                if(expressionView.getVisibility() == View.VISIBLE){
                    expressionView.setVisibility(View.GONE);
                }
                inputMethodManager.hideSoftInputFromWindow(text.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 2) {
                Uri uri = data.getData();
                Toast.makeText(this, uri.getPath(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
