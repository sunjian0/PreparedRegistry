package com.example.administrator.preparedregistry.base;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.preparedregistry.R;


/**
 * Created by sunjian on 16/6/16.
 * Acticity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(initLayout());
        initView();
        initData();
    }

    //初始布局窗口
    protected abstract int initLayout();

    //初始化控件窗口

    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    //绑定控件方法
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }


}

