package com.example.administrator.preparedregistry.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.preparedregistry.R;

public class ResultCheckInfor extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImRetChkInfBack;
    private TextView mTvRetChkInfReckReq;
    private Button mBtnRetChkInfSubmmit;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, ResultCheckInfor.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_infor);
        mImRetChkInfBack = (ImageView) findViewById(R.id.check_infor_back);
        mTvRetChkInfReckReq = (TextView) findViewById(R.id.tv_check_infor_reck_request);
        mBtnRetChkInfSubmmit = (Button) findViewById(R.id.btn_check_infor_submmit);

        mImRetChkInfBack.setOnClickListener(this);
        mTvRetChkInfReckReq.setOnClickListener(this);
        mBtnRetChkInfSubmmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_infor_back:
                finish();
                break;
            case R.id.tv_check_infor_reck_request:
                RecheckRequest.startAction(ResultCheckInfor.this);
                break;
            case R.id.btn_check_infor_submmit:
                // TODO: 2016/7/11
                Toast.makeText(ResultCheckInfor.this, "是否需要提交数据", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
