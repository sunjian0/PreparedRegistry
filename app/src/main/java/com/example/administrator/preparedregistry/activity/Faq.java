package com.example.administrator.preparedregistry.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

public class Faq extends BaseActivity implements View.OnClickListener {

    public static void startAction(Context context) {
        Intent intent = new Intent(context, Faq.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.faq;
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
