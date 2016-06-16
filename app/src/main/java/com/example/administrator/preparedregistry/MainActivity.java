package com.example.administrator.preparedregistry;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.preparedregistry.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        imageView= (ImageView) findViewById(R.id.main_image);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.icon_xiugai_100x100px);
        MyDrawable drawable=new MyDrawable(bitmap);
        imageView.setImageDrawable(drawable);
    }

    @Override
    protected void initData() {

    }
}
