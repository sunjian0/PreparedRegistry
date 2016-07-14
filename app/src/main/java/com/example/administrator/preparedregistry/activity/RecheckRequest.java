package com.example.administrator.preparedregistry.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

public class RecheckRequest extends BaseActivity implements View.OnClickListener {

    private ImageView mRecheckRequestBack;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, RecheckRequest.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_recheck_request;
    }

    @Override
    protected void initView() {
        mRecheckRequestBack = (ImageView) findViewById(R.id.recheck_request_back);
    }

    @Override
    protected void initData() {


        mRecheckRequestBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recheck_request_back:
                finish();
                break;

            //TODO 提交处理
        }
    }


}
