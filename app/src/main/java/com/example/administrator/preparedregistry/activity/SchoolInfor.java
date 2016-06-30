package com.example.administrator.preparedregistry.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.preparedregistry.R;

public class SchoolInfor extends AppCompatActivity {

    private ImageView mIvSchoolInforBack;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SchoolInfor.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_infor);
        mIvSchoolInforBack = (ImageView) findViewById(R.id.school_infor_back);
        mIvSchoolInforBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
