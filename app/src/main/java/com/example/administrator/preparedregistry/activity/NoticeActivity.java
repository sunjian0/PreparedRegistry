package com.example.administrator.preparedregistry.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.preparedregistry.MyDrawable;
import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

/**
 * Created by 孙健 on 2016/6/16.
 * 公告
 */
public class NoticeActivity extends BaseActivity {

    private ImageView btnBack,image;

    @Override
    protected int initLayout() {
        return R.layout.activity_notice;
    }

    @Override
    protected void initView() {
        btnBack=bindView(R.id.notice_back);
        image=bindView(R.id.notice_image);

    }

    @Override
    protected void initData() {

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.icon_gonggao_100x100px);
        MyDrawable drawable=new MyDrawable(bitmap);
        image.setImageDrawable(drawable);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
