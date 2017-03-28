package com.rose.guojiangzhibo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.rose.guojiangzhibo.R;

public class IncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        WebView myWebView = (WebView) findViewById(R.id.webview_four_income);
        myWebView.loadUrl("http://m.guojiang.tv/income");
    }
}
