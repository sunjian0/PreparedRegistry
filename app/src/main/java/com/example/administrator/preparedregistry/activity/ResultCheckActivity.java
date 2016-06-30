package com.example.administrator.preparedregistry.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

public class ResultCheckActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mResultCheckBack;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, ResultCheckActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_check_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }


}
