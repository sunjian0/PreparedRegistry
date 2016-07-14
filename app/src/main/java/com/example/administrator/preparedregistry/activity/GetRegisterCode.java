package com.example.administrator.preparedregistry.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;
import com.example.administrator.preparedregistry.dialog.RegisterCodeDialog;

public class GetRegisterCode extends BaseActivity implements View.OnClickListener {

    private ImageView mImgGetRegCodeBack;
    private TextView mTvGetRegCodeChildID;
    private TextView mTvGetRegCodeFarID;
    private TextView mTVGetRegCodeMarID;
    private Button mBtnSubmmit;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, GetRegisterCode.class);
        context.startActivity(intent);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_get_register_code;
    }

    @Override
    protected void initView() {
        mImgGetRegCodeBack = bindView(R.id.img_get_reg_code_back);
        mTvGetRegCodeChildID = bindView(R.id.tv_get_reg_code_childID);
        mTvGetRegCodeFarID = bindView(R.id.tv_get_reg_code_farID);
        mTVGetRegCodeMarID = bindView(R.id.tv_get_reg_code_marID);
        mBtnSubmmit = bindView(R.id.btn_get_reg_code_submmit);
    }

    @Override
    protected void initData() {
        mImgGetRegCodeBack.setOnClickListener(this);
        mTvGetRegCodeChildID.setOnClickListener(this);
        mTvGetRegCodeFarID.setOnClickListener(this);
        mTVGetRegCodeMarID.setOnClickListener(this);
        mBtnSubmmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_get_reg_code_back:
                finish();
                break;
            case R.id.btn_get_reg_code_submmit:
                // TODO: 2016/7/11
                //提交信息
                String registercode = "XXXXXX";
                if (true) {
                    showRegisterCode(registercode);
                } else {
                    Toast.makeText(GetRegisterCode.this, "信息错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showRegisterCode(String registerCode) {
        RegisterCodeDialog.Builder builder = new RegisterCodeDialog.Builder(GetRegisterCode.this);
        builder.setRegisterCode(registerCode);
        builder.create().show();
    }
}
