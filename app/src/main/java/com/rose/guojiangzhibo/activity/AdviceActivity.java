package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rose.guojiangzhibo.R;

public class AdviceActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        button = (Button) findViewById(R.id.button_four_advice);
    }

    public void onClick(View view) {
        Toast.makeText(this, "意见已提交", Toast.LENGTH_SHORT).show();
    }
}
