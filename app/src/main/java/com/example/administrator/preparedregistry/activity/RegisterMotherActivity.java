package com.example.administrator.preparedregistry.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/6/21.
 */
public class RegisterMotherActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    private ImageView btnBack;
    private ToggleButton toggleButton;// 是否已故选择器
    private LinearLayout layoutNotDie, layoutDie, layoutInsuranceTime;

    private TextView tvLabelOne, tvLabelTwo, tvLabelThree;// 最下面按钮上方的提示
    private Spinner spiInsurance, spiPoliceStation;
    private EditText etInsuranceTimeStart, etInsuranceTimeEnd, etLiveStart, etLiveEnd, etBusinessStart, etBusinessEnd;
    private Button btnEnd;
    private String motherInsurance;

    private TextView tvTaskbarThree, tvTaskbarMother; // 上方显示页面的进度的textview
    private TextView tvPageHead, tvName, tvIdCard; // 输入框左边的文字内容
    private Bundle parentsBundle;

    private EditText etMotherName, etMotherIdCard, etMotherPhoneNumber, etMotherCompany,
            etMotherLiveCard, etMotherLiveCardAddress, etMotherBusinessCard, etMotherNote;
    private String motherLiveCardpoliceStation = "请选择";
    private int motherIfDie = 0;  // 是否已故  这里0为未故 1为已故

    @Override
    protected int initLayout() {
        return R.layout.activity_register_parents;
    }

    @Override
    protected void initView() {
        tvPageHead = bindView(R.id.registerparents_test_pageHead);
        tvName = bindView(R.id.registerparents_tv_name);
        tvIdCard = bindView(R.id.registerparents_tv_idCard);
        btnBack = bindView(R.id.registerparents_back);
        toggleButton = bindView(R.id.registerparents_togglebutton);
        layoutNotDie = bindView(R.id.registerparents_layout_notdie);
        layoutDie = bindView(R.id.registerparents_layout_die);
        tvLabelOne = bindView(R.id.registerparents_text_labelOne);
        tvLabelTwo = bindView(R.id.registerparents_text_labelTwo);
        tvLabelThree = bindView(R.id.registerparents_text_labelThree);
        layoutInsuranceTime = bindView(R.id.registerparents_layout_insuranceTime);
        spiInsurance = bindView(R.id.registerparents_spinner_insurance);
        spiPoliceStation = bindView(R.id.registerparents_spinner_policeStation);
        etInsuranceTimeStart = bindView(R.id.registerparents_et_insuranceTimeStart);
        etInsuranceTimeEnd = bindView(R.id.registerparents_et_insuranceTimeEnd);
        etLiveStart = bindView(R.id.registerparents_et_liveStart);
        etLiveEnd = bindView(R.id.registerparents_et_liveEnd);
        etBusinessStart = bindView(R.id.registerparents_et_businessStart);
        etBusinessEnd = bindView(R.id.registerparents_et_businessEnd);
        btnEnd = bindView(R.id.registerparents_btn);
        tvTaskbarThree = bindView(R.id.registerparents_tv_taskbarThree);
        tvTaskbarMother = bindView(R.id.registerparents_tv_taskbarMother);

        etMotherName = bindView(R.id.registerparents_et_name);
        etMotherIdCard = bindView(R.id.registerparents_et_idCard);
        etMotherPhoneNumber = bindView(R.id.registerparents_et_phoneNumber);
        etMotherCompany = bindView(R.id.registerparents_et_company);
        etMotherLiveCard = bindView(R.id.registerparents_et_liveCard);
        etMotherLiveCardAddress = bindView(R.id.registerparents_et_liveCardAddress);
        etMotherBusinessCard = bindView(R.id.registerparents_et_businessCard);
        etMotherNote = bindView(R.id.registerparents_et_note);

    }

    @Override
    protected void initData() {
        parentsBundle = this.getIntent().getBundleExtra("father");
        String name = parentsBundle.getString("name");
        Log.i("sss", name + "");

        btnBack.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        tvTaskbarThree.setBackgroundResource(R.drawable.registerchecked_shape);
        tvTaskbarMother.setTextColor(Color.parseColor("#3FD5A8"));
        tvPageHead.setText("母亲信息");
        tvName.setText("母亲姓名");
        tvIdCard.setText("母亲身份证号");

        // TODO 切换后删除信息
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutNotDie.setVisibility(View.GONE);
                    layoutDie.setVisibility(View.VISIBLE);
                    tvLabelOne.setTextColor(Color.parseColor("#b2b2b2"));
                    tvLabelTwo.setTextColor(Color.parseColor("#b2b2b2"));
                    tvLabelThree.setTextColor(Color.parseColor("#b2b2b2"));
                    motherIfDie = 1;
                } else {
                    layoutNotDie.setVisibility(View.VISIBLE);
                    layoutDie.setVisibility(View.GONE);
                    tvLabelOne.setTextColor(Color.parseColor("#d10000"));
                    tvLabelTwo.setTextColor(Color.parseColor("#d10000"));
                    tvLabelThree.setTextColor(Color.parseColor("#d10000"));
                    motherIfDie = 0;
                }
            }
        });

        spiInsurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO  改为保险的 array
                String[] insurance = getResources().getStringArray(R.array.ifPayInsurance);
                if (insurance[position].equals("请选择")) {
                    layoutInsuranceTime.setVisibility(View.GONE);
                    motherInsurance = "请选择";
                } else {
                    layoutInsuranceTime.setVisibility(View.VISIBLE);
                    motherInsurance = insurance[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spiPoliceStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] policeStations = getResources().getStringArray(R.array.policeStation);
                motherLiveCardpoliceStation = policeStations[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etInsuranceTimeStart.setOnTouchListener(this);
        etInsuranceTimeEnd.setOnTouchListener(this);
        etLiveStart.setOnTouchListener(this);
        etLiveEnd.setOnTouchListener(this);
        etBusinessStart.setOnTouchListener(this);
        etBusinessEnd.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerparents_back:

                finish();
                break;
            case R.id.registerparents_btn:
                // 提交
                String fatherInsurance = parentsBundle.getString("fatherInsurance");
                if (fatherInsurance.equals("请选择") && motherInsurance.equals("请选择")) {
                    Toast.makeText(RegisterMotherActivity.this, "父母一方必须在杭州已缴纳保险", Toast.LENGTH_SHORT).show();
                } else {
                    if (motherIfDie == 1) {
                        setData();
                    } else {
                        if ((etMotherName.getText() == null) || ("".equals(etMotherName.getText().toString().trim()))
                                || (etMotherIdCard.getText() == null) || ("".equals(etMotherIdCard.getText().toString().trim()))
                                || (etMotherPhoneNumber.getText() == null) || ("".equals(etMotherPhoneNumber.getText().toString().trim()))
                                || (etMotherLiveCard.getText() == null) || ("".equals(etMotherLiveCard.getText().toString().trim()))
                                || (etLiveStart.getText() == null) || ("".equals(etLiveStart.getText().toString().trim()))
                                || (etLiveEnd.getText() == null) || ("".equals(etLiveEnd.getText().toString().trim()))
                                || (etMotherLiveCardAddress.getText() == null) || ("".equals(etMotherLiveCardAddress.getText().toString().trim()))) {
                            Toast.makeText(RegisterMotherActivity.this, "请填写完整的信息", Toast.LENGTH_SHORT).show();
                        } else if (motherLiveCardpoliceStation.equals("请选择")) {
                            Toast.makeText(RegisterMotherActivity.this, "请选择发证派出所", Toast.LENGTH_SHORT).show();
                        }else {
                            if (fatherInsurance.equals("请选择")) {
                                // TODO 加一条“无”判断
                                setData();
                                break;
                            } else {
                                if ((etInsuranceTimeStart.getText() == null) || ("".equals(etInsuranceTimeStart.getText().toString().trim()))
                                        || (etInsuranceTimeEnd.getText() == null) || ("".equals(etInsuranceTimeEnd.getText().toString().trim()))) {
                                    Toast.makeText(RegisterMotherActivity.this, "请输入保险时间", Toast.LENGTH_SHORT).show();
                                } else {
                                   setData();
                                    break;
                                }
                            }
                        }
                    }

                }
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.dialog_date, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
            builder.setView(view);

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
            if (v.getId() == R.id.registerparents_et_insuranceTimeStart) {
                final int inType = etInsuranceTimeStart.getInputType();
                etInsuranceTimeStart.setInputType(InputType.TYPE_NULL);
                etInsuranceTimeStart.onTouchEvent(event);
                etInsuranceTimeStart.setInputType(inType);
                etInsuranceTimeStart.setSelection(etInsuranceTimeStart.getText().length());

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        etInsuranceTimeStart.setText(sb);

                        dialog.cancel();
                    }
                });

            } else if (v.getId() == R.id.registerparents_et_insuranceTimeEnd) {
                int inType = etInsuranceTimeEnd.getInputType();
                etInsuranceTimeEnd.setInputType(InputType.TYPE_NULL);
                etInsuranceTimeEnd.onTouchEvent(event);
                etInsuranceTimeEnd.setInputType(inType);
                etInsuranceTimeEnd.setSelection(etInsuranceTimeEnd.getText().length());

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        etInsuranceTimeEnd.setText(sb);

                        dialog.cancel();
                    }
                });
            } else if (v.getId() == R.id.registerparents_et_liveStart) {
                int inType = etLiveStart.getInputType();
                etLiveStart.setInputType(InputType.TYPE_NULL);
                etLiveStart.onTouchEvent(event);
                etLiveStart.setInputType(inType);
                etLiveStart.setSelection(etLiveStart.getText().length());

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        etLiveStart.setText(sb);

                        dialog.cancel();
                    }
                });
            } else if (v.getId() == R.id.registerparents_et_liveEnd) {
                int inType = etLiveEnd.getInputType();
                etLiveEnd.setInputType(InputType.TYPE_NULL);
                etLiveEnd.onTouchEvent(event);
                etLiveEnd.setInputType(inType);
                etLiveEnd.setSelection(etLiveEnd.getText().length());

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        etLiveEnd.setText(sb);

                        dialog.cancel();
                    }
                });
            } else if (v.getId() == R.id.registerparents_et_businessStart) {
                int inType = etBusinessStart.getInputType();
                etBusinessStart.setInputType(InputType.TYPE_NULL);
                etBusinessStart.onTouchEvent(event);
                etBusinessStart.setInputType(inType);
                etBusinessStart.setSelection(etBusinessStart.getText().length());

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        etBusinessStart.setText(sb);

                        dialog.cancel();
                    }
                });
            } else if (v.getId() == R.id.registerparents_et_businessEnd) {
                int inType = etBusinessEnd.getInputType();
                etBusinessEnd.setInputType(InputType.TYPE_NULL);
                etBusinessEnd.onTouchEvent(event);
                etBusinessEnd.setInputType(inType);
                etBusinessEnd.setSelection(etBusinessEnd.getText().length());

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        etBusinessEnd.setText(sb);

                        dialog.cancel();
                    }
                });
            }
            Dialog dialog = builder.create();
            dialog.show();

        }

        return true;
    }


    // 上传数据
    public void setData() {


    }

    // 拉取数据
    public void getData() {

    }
}
