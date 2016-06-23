package com.example.administrator.preparedregistry.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    private TextView tvLabelOne,tvLabelTwo,tvLabelThree;

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
        tvLabelOne=bindView(R.id.registerfather_text_labelOne);
        tvLabelTwo=bindView(R.id.registerfather_text_labelTwo);
        tvLabelThree=bindView(R.id.registerfather_text_labelThree);
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
                    tvLabelOne.setTextColor(Color.parseColor("#b2b2b2"));
                    tvLabelTwo.setTextColor(Color.parseColor("#b2b2b2"));
                    tvLabelThree.setTextColor(Color.parseColor("#b2b2b2"));
                } else {
                    layoutNotDie.setVisibility(View.VISIBLE);
                    layoutDie.setVisibility(View.GONE);
                    tvLabelOne.setTextColor(Color.parseColor("#d10000"));
                    tvLabelTwo.setTextColor(Color.parseColor("#d10000"));
                    tvLabelThree.setTextColor(Color.parseColor("#d10000"));
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
