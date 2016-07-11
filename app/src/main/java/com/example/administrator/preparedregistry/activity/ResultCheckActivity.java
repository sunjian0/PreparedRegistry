package com.example.administrator.preparedregistry.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

public class ResultCheckActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mResultCheckBack;

    private Button mResultCheckBtn;
    private TextView mResultCheckTv;

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
        mResultCheckBack = bindView(R.id.check_login_back);
        mResultCheckBtn = bindView(R.id.check_login_btn_login);
        mResultCheckTv = bindView(R.id.check_loging_forget_pwd);

    }

    @Override
    protected void initData() {

        mResultCheckBack.setOnClickListener(this);
        mResultCheckBtn.setOnClickListener(this);
        mResultCheckTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_login_back:
                finish();
                break;
            case R.id.check_login_btn_login:
                //TODO 验证是否通过
                if (true) {
                    ResultCheckInfor.startAction(ResultCheckActivity.this);
                } else {
                    Toast.makeText(ResultCheckActivity.this, "信息有误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.check_loging_forget_pwd:

                break;

        }
    }

}
