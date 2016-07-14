package com.example.administrator.preparedregistry.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.preparedregistry.R;

import com.example.administrator.preparedregistry.base.BaseActivity;
import com.example.administrator.preparedregistry.base.BaseApplication;
import com.example.administrator.preparedregistry.city.CityPicker;



/**
 * Created by Administrator on 2016/6/17.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spiIfHaveHome;
    private RelativeLayout layoutHomeCard, layoutHomePlace;
    private Button button;
    private ImageView ivBack;
    private TextView tvCity;
    private LinearLayout layoutCity;

    private Button btnCity;
    private CityPicker cityPicker;



    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        spiIfHaveHome = bindView(R.id.register_spinner_ifhavehome);
        layoutHomeCard = bindView(R.id.register_layoutHomeCardNumber);
        layoutHomePlace = bindView(R.id.register_layoutHomePlace);
        button=bindView(R.id.register_btn);
        ivBack=bindView(R.id.register_back);
        layoutCity=bindView(R.id.register_layout_city);
        tvCity=bindView(R.id.register_text_city);
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
        layoutCity.setOnClickListener(this);
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


        View view = getLayoutInflater().inflate(R.layout.dialog_register, null);

        builder.setView(view);
        builder.setCancelable(false);
        builder.show();


    }

    public void cityDialog(){
        AlertDialog.Builder cityBuilder = new AlertDialog.Builder(this);
        View cityView = getLayoutInflater().inflate(R.layout.dialog_city, null);

        cityPicker = (CityPicker) cityView.findViewById(R.id.dialog_citypicker);

        cityBuilder.setView(cityView);

        cityBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cityString=cityPicker.getCity_string();
                tvCity.setText(cityString);
                tvCity.setTextColor(Color.parseColor("#323232"));
            }
        });
        cityBuilder.setCancelable(false);
        cityBuilder.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:

                // TODO  一系列发送数据到服务器的操作
                Intent intent=new Intent(RegisterActivity.this,RegisterFatherActivity.class);
                startActivity(intent);

                break;
            case R.id.register_back:
                finish();
                break;
            case R.id.register_layout_city:
                cityDialog();
                break;

        }
    }


}
