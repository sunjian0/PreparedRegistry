package com.example.administrator.preparedregistry.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.preparedregistry.MyDrawable;
import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView;
    private LinearLayout notice,register,change,checkResult,applyForCheck,school;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        imageView= bindView(R.id.main_image);

        notice=bindView(R.id.main_notice);
        register=bindView(R.id.main_register);
        change=bindView(R.id.main_change);
        checkResult=bindView(R.id.main_checkResult);
        applyForCheck=bindView(R.id.main_applyForCheck);
        school=bindView(R.id.main_school);

    }

    @Override
    protected void initData() {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.icon_xiugai_100x100px);
        MyDrawable drawable=new MyDrawable(bitmap);
        imageView.setImageDrawable(drawable);

        notice.setOnClickListener(this);
        register.setOnClickListener(this);
        change.setOnClickListener(this);
        checkResult.setOnClickListener(this);
        applyForCheck.setOnClickListener(this);
        school.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_notice:
                Intent intentNotice=new Intent(MainActivity.this,NoticeActivity.class);
                startActivity(intentNotice);
                break;
            case R.id.main_register:
                Intent intentRegister=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
                break;

//            case    TODO 其余跳转
        }
    }
}
