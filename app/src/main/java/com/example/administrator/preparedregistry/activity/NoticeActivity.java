package com.example.administrator.preparedregistry.activity;

import android.view.View;
import android.widget.ImageView;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

/**
 * Created by Administrator on 2016/6/16.
 */
public class NoticeActivity extends BaseActivity {

    private ImageView btnBack;

    @Override
    protected int initLayout() {
        return R.layout.activity_notice;
    }

    @Override
    protected void initView() {
        btnBack=bindView(R.id.notice_back);

    }

    @Override
    protected void initData() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
