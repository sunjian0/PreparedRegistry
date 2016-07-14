package com.example.administrator.preparedregistry.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

public class Faq extends BaseActivity implements View.OnClickListener {

    private ImageView mImgFaqBack;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, Faq.class);
        context.startActivity(intent);
    }

    @Override
    protected int initLayout() {
        return R.layout.faq;
    }

    @Override
    protected void initView() {
        mImgFaqBack = bindView(R.id.faq_back);
    }

    @Override
    protected void initData() {
        mImgFaqBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.faq_back:
                finish();
                break;
        }
    }
}
