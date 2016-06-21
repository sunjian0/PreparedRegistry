package com.example.administrator.preparedregistry.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.administrator.preparedregistry.MyDialog;
import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

/**
 * Created by Administrator on 2016/6/17.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spiIfHaveHome;
    private RelativeLayout layoutHomeCard, layoutHomePlace;
    private Button button;
    private ImageView ivBack;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        spiIfHaveHome = bindView(R.id.register_spinnerIfhavehome);
        layoutHomeCard = bindView(R.id.register_layoutHomeCardNumber);
        layoutHomePlace = bindView(R.id.register_layoutHomePlace);
        button=bindView(R.id.register_btn);
        ivBack=bindView(R.id.notice_back);
    }

    @Override
    protected void initData() {
        showDialog();

        // 是否有房产
        spiIfHaveHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] ifhavehome = getResources().getStringArray(R.array.ifhavehome);
                //切换为有时
                if (ifhavehome[position].equals("有")) {
                    layoutHomeCard.setVisibility(View.VISIBLE);
                    layoutHomePlace.setVisibility(View.VISIBLE);
                }
                if (ifhavehome[position].equals("无")){
                    layoutHomeCard.setVisibility(View.GONE);
                    layoutHomePlace.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ivBack.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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


        View view = getLayoutInflater().inflate(R.layout.register_dialog, null);

        builder.setView(view);
        builder.setCancelable(false);
        builder.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                // TODO
                break;
            case R.id.notice_back:
                finish();
                break;
        }
    }
}
