package com.example.administrator.preparedregistry.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.preparedregistry.R;

public class ResultCheckInfor extends AppCompatActivity {

    public static void startAction(Context context) {
        Intent intent = new Intent(context, ResultCheckInfor.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_infor);
    }

}
