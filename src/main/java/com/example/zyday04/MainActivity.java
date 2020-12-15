package com.example.zyday04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.zyday04.adapter.GuideAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private ArrayList<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        mViews = new ArrayList<>();

        final View view1 = View.inflate(this, R.layout.guid1_layout, null);
        final View view2 = View.inflate(this, R.layout.guid2_layout, null);
        final View view3 = View.inflate(this, R.layout.guid3_layout, null);

        // 添加集合
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);

        // 创建适配器
        final GuideAdapter guideAdapter = new GuideAdapter(mViews);
        vp.setAdapter(guideAdapter);

        Button btn = view3.findViewById(R.id.guid3_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }
}
