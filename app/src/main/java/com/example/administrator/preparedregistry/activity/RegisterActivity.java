package com.example.administrator.preparedregistry.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.preparedregistry.MyDialog;
import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

/**
 * Created by Administrator on 2016/6/17.
 */
public class RegisterActivity extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showDialog();
    }

    public void showDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        View view=getLayoutInflater().inflate(R.layout.register_dialog,null);

        builder.setView(view);
        builder.setCancelable(false);
        builder.show();




    }
}
