package com.example.administrator.preparedregistry.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

/**
 * Created by Administrator on 2016/6/21.
 */
public class RegisterFatherActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    private ToggleButton toggleButton;
    private LinearLayout layoutNotDie,layoutDie;

    @Override
    protected int initLayout() {
        return R.layout.activity_register_father;
    }

    @Override
    protected void initView() {
        btnBack = bindView(R.id.registerfather_back);
        toggleButton = bindView(R.id.registerfather_togglebutton);
        layoutNotDie = bindView(R.id.registerfather_layout_notdie);
        layoutDie=bindView(R.id.registerfather_layout_die);
    }

    @Override
    protected void initData() {
        btnBack.setOnClickListener(this);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutNotDie.setVisibility(View.GONE);
                    layoutDie.setVisibility(View.VISIBLE);
                } else {
                    layoutNotDie.setVisibility(View.VISIBLE);
                    layoutDie.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerfather_back:
                //TODO finish基本信息页
                finish();
                break;

        }
    }
}
