package com.example.administrator.preparedregistry.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by 孙健 on 2016/6/17.
 * 基本信息页
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spiIfHaveHome;
    private RelativeLayout layoutHomeCard, layoutHomePlace;
    private Button buttonToFather;
    private ImageView ivBack;
    private TextView tvCity;
    private LinearLayout layoutCity;
    private EditText etName, etIdCard, etPoliceStation, etPhoneNumber,
            etLiveAddress, etHomeID, etHomeAddress;
    private String name, idCard, policeStation, phoneNumber,
            liveAddress, homeID, homeAddress, city;

    private CityPicker cityPicker; //城市选择器
    private String[] ifhavehome;
    private int pos=0;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        spiIfHaveHome = bindView(R.id.register_spinner_ifhavehome);
        layoutHomeCard = bindView(R.id.register_layoutHomeCardNumber);
        layoutHomePlace = bindView(R.id.register_layoutHomePlace);
        buttonToFather = bindView(R.id.register_btn);
        ivBack = bindView(R.id.register_back);
        layoutCity = bindView(R.id.register_layout_city);
        tvCity = bindView(R.id.register_text_city);

        etName = bindView(R.id.register_et_name);
        etIdCard = bindView(R.id.register_et_idCard);
        etPoliceStation = bindView(R.id.register_et_policeStation);
        etPhoneNumber = bindView(R.id.register_et_phoneNumber);
        etLiveAddress = bindView(R.id.register_et_liveAddress);
        etHomeID = bindView(R.id.register_et_homeID);
        etHomeAddress = bindView(R.id.register_et_homeAddress);
    }

    @Override
    protected void initData() {

        ifhavehome = getResources().getStringArray(R.array.ifhavehome);

        showDialog();

        // 是否有房产
        spiIfHaveHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //切换为有时
                if (ifhavehome[position].equals("有")) {
                    pos=1;
                    layoutHomeCard.setVisibility(View.VISIBLE);
                    layoutHomePlace.setVisibility(View.VISIBLE);
                }
                if (ifhavehome[position].equals("无")) {
                    pos=0;
                    layoutHomeCard.setVisibility(View.GONE);
                    layoutHomePlace.setVisibility(View.GONE);
                    etHomeID.setText("");
                    etHomeAddress.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ivBack.setOnClickListener(this);
        buttonToFather.setOnClickListener(this);
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

    public void cityDialog() {
        AlertDialog.Builder cityBuilder = new AlertDialog.Builder(this);
        View cityView = getLayoutInflater().inflate(R.layout.dialog_city, null);

        cityPicker = (CityPicker) cityView.findViewById(R.id.dialog_citypicker);

        cityBuilder.setView(cityView);

        cityBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cityString = cityPicker.getCity_string();
                tvCity.setText(cityString);
                tvCity.setTextColor(Color.parseColor("#323232"));
            }
        });
        cityBuilder.setCancelable(false);
        cityBuilder.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                name = etName.getText().toString().replace(" ", "");
                idCard = etIdCard.getText().toString().replace(" ", "");
                policeStation = etPoliceStation.getText().toString().replace(" ", "");
                phoneNumber = etPhoneNumber.getText().toString().replace(" ", "");
                liveAddress = etLiveAddress.getText().toString().replace(" ", "");
                city = tvCity.getText().toString();
                homeID = etHomeID.getText().toString().replace(" ", "");
                homeAddress = etHomeAddress.getText().toString().replace(" ", "");


                if ((etName == null) || ("".equals(etName.getText().toString().trim()))
                        || (etIdCard.getText() == null) || ("".equals(etIdCard.getText().toString().trim()))
                        || (etPoliceStation.getText() == null) || ("".equals(etPoliceStation.getText().toString().trim()))
                        || (etPhoneNumber.getText() == null) || ("".equals(etPhoneNumber.getText().toString().trim()))
                        || (etLiveAddress.getText() == null) || ("".equals(etLiveAddress.getText().toString().trim()))
                        || (city.equals("省 市 区"))) {

                    Toast.makeText(RegisterActivity.this, "请填写完整的信息", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("idCard", idCard);
                    bundle.putString("policeStation", policeStation);
                    bundle.putString("phoneNumber", phoneNumber);
                    bundle.putString("liveAddress", liveAddress);
                    bundle.putString("city", city);
                    if (pos==0){
                        Intent intent = new Intent(RegisterActivity.this, RegisterFatherActivity.class);
                        intent.putExtra("student", bundle);
                        startActivity(intent);

                    }else {
                        if ((etHomeID == null) || ("".equals(etHomeID.getText().toString().trim()))) {
                            Toast.makeText(RegisterActivity.this, "请填写完整的房产信息", Toast.LENGTH_SHORT).show();
                        } else {
                            if ((etHomeAddress == null) || ("".equals(etHomeAddress.getText().toString().trim()))) {
                                Toast.makeText(RegisterActivity.this, "请填写完整的房产信息", Toast.LENGTH_SHORT).show();
                            } else {
                                bundle.putString("homeID", homeID);
                                bundle.putString("homeAddress", homeAddress);

                                Intent intent = new Intent(RegisterActivity.this, RegisterFatherActivity.class);
                                intent.putExtra("student", bundle);
                                startActivity(intent);
                            }
                        }
                    }

                }


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
