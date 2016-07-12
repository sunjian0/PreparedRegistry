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
public class RegisterFatherActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private ImageView btnBack;
    private ToggleButton toggleButton;  // 是否已故选择器
    private LinearLayout layoutNotDie, layoutDie, layoutInsuranceTime;

    private TextView tvLabelOne, tvLabelTwo, tvLabelThree;// 最下面按钮上方的提示
    private Spinner spiInsurance, spiPoliceStation;
    private EditText etInsuranceTimeStart, etInsuranceTimeEnd, etLiveStart,
            etLiveEnd, etBusinessStart, etBusinessEnd;
    private Button btnToMother;
    private EditText etFatherName, etFatherIdCard, etFatherPhoneNumber,
            etFatherCompany, etFatherLiveCard, etFatherLiveCardAddress,
            etFatherBusinessCard, etFatherNote;

    private String fatherInsurance;
    private Bundle parentsBundle;
    private int fatherIfDie = 0;  // 是否已故  这里0为未故 1为已故
    private String fatherName, fatherIdCard, fatherPhoneNumber,
            fatherCompany, fatherLiveCard,
            fatherLiveCardpoliceStation = "请选择", fatherLiveStart, fatherLiveEnd,
            fatherLiveCardAddress, fatherBusinessCard,
            fatherBusinessStart, fatherBusinessEnd, fatherNote;


    @Override
    protected int initLayout() {
        return R.layout.activity_register_parents;
    }

    @Override
    protected void initView() {
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
        btnToMother = bindView(R.id.registerparents_btn);

        etFatherName = bindView(R.id.registerparents_et_name);
        etFatherIdCard = bindView(R.id.registerparents_et_idCard);
        etFatherPhoneNumber = bindView(R.id.registerparents_et_phoneNumber);
        etFatherCompany = bindView(R.id.registerparents_et_company);
        etFatherLiveCard = bindView(R.id.registerparents_et_liveCard);
        etFatherLiveCardAddress = bindView(R.id.registerparents_et_liveCardAddress);
        etFatherBusinessCard = bindView(R.id.registerparents_et_businessCard);
        etFatherNote = bindView(R.id.registerparents_et_note);
    }

    @Override
    protected void initData() {
        parentsBundle = this.getIntent().getBundleExtra("student");
//        String name = parentsBundle.getString("name");
//        String id = parentsBundle.getString("idCard");
//        String policeStation = parentsBundle.getString("policeStation");
//        Log.i("sss",name+" "+id+" "+policeStation+"");

        btnBack.setOnClickListener(this);
        btnToMother.setOnClickListener(this);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutNotDie.setVisibility(View.GONE);
                    layoutDie.setVisibility(View.VISIBLE);
                    tvLabelOne.setTextColor(Color.parseColor("#b2b2b2"));
                    tvLabelTwo.setTextColor(Color.parseColor("#b2b2b2"));
                    tvLabelThree.setTextColor(Color.parseColor("#b2b2b2"));
                    fatherIfDie = 1;
                } else {
                    layoutNotDie.setVisibility(View.VISIBLE);
                    layoutDie.setVisibility(View.GONE);
                    tvLabelOne.setTextColor(Color.parseColor("#d10000"));
                    tvLabelTwo.setTextColor(Color.parseColor("#d10000"));
                    tvLabelThree.setTextColor(Color.parseColor("#d10000"));
                    fatherIfDie = 0;
                }
            }
        });

        spiInsurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO  改为保险的 array
                String[] insurances = getResources().getStringArray(R.array.ifPayInsurance);
                if (insurances[position].equals("请选择")) {
                    layoutInsuranceTime.setVisibility(View.GONE);
                    fatherInsurance = "请选择";
                } else {
                    layoutInsuranceTime.setVisibility(View.VISIBLE);
                    fatherInsurance = insurances[position];
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
                fatherLiveCardpoliceStation = policeStations[position];
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


                // TODO 已故传什么？1?   未故传什么
                parentsBundle.putInt("fatherIfDie", fatherIfDie);

                if (fatherIfDie == 1) {

                    Intent intent = new Intent(this, RegisterMotherActivity.class);
                    intent.putExtra("father", parentsBundle);
                    startActivity(intent);
                    break;

                } else {
                    if ((etFatherName.getText() == null) || ("".equals(etFatherName.getText().toString().trim()))
                            || (etFatherIdCard.getText() == null) || ("".equals(etFatherIdCard.getText().toString().trim()))
                            || (etFatherPhoneNumber.getText() == null) || ("".equals(etFatherPhoneNumber.getText().toString().trim()))
                            || (etFatherCompany.getText() == null) || ("".equals(etFatherCompany.getText().toString().trim()))
                            || (etFatherLiveCard.getText() == null) || ("".equals(etFatherLiveCard.getText().toString().trim()))
                            || (etLiveStart.getText() == null) || ("".equals(etLiveStart.getText().toString().trim()))
                            || (etLiveEnd.getText() == null) || ("".equals(etLiveEnd.getText().toString().trim()))
                            || (etFatherLiveCardAddress.getText() == null) || ("".equals(etFatherLiveCardAddress.getText().toString().trim()))) {
                        Toast.makeText(RegisterFatherActivity.this, "请填写完整的信息", Toast.LENGTH_SHORT).show();
                    } else if (fatherLiveCardpoliceStation.equals("请选择")) {
                        Toast.makeText(RegisterFatherActivity.this, "请选择发证派出所", Toast.LENGTH_SHORT).show();
                    } else {
                        parentsBundle.putString("fatherName", etFatherName.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherIdCard", etFatherIdCard.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherPhoneNumber", etFatherPhoneNumber.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherInsurance", fatherInsurance);
                        parentsBundle.putString("fatherInsuranceTimeStart", etInsuranceTimeStart.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherInsuranceTimeEnd", etInsuranceTimeEnd.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherCompany", etFatherCompany.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherLiveCard", etFatherLiveCard.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherLiveCardpoliceStation", fatherLiveCardpoliceStation);
                        parentsBundle.putString("fatherLiveStart", etLiveStart.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherLiveEnd", etLiveEnd.getText().toString().replace(" ", ""));
                        parentsBundle.putString("fatherLiveCardAddress", etFatherLiveCardAddress.getText().toString().replace(" ", ""));

                        //是否有营业执照
                        if ((etFatherBusinessCard.getText() == null) || ("".equals(etFatherBusinessCard.getText().toString().trim()))) {
                        } else {
                            parentsBundle.putString("fatherBusinessCard", etFatherBusinessCard.getText().toString().replace(" ", ""));
                            if ((etBusinessStart.getText() == null) || ("".equals(etBusinessStart.getText().toString().trim()))
                                    || (etBusinessEnd.getText() == null) || ("".equals(etBusinessEnd.getText().toString().trim()))) {
                                Toast.makeText(RegisterFatherActivity.this, "有劳动合同或营业执照 请选择营业起止时间", Toast.LENGTH_SHORT).show();
                            } else {
                                parentsBundle.putString("fatherBusinessStart", etBusinessStart.getText().toString().replace(" ", ""));
                                parentsBundle.putString("fatherBusinessEnd", etBusinessEnd.getText().toString().replace(" ", ""));
                            }
                        }
                        // 是否有备注
                        if ((etFatherNote.getText() == null) || ("".equals(etFatherNote.getText().toString().trim()))) {
                        } else {
                            parentsBundle.putString("fatherNote", etFatherNote.getText().toString().replace(" ", ""));
                        }

                        if (fatherInsurance.equals("请选择")) {
                            // TODO 加一条“无”判断
                            Intent intent = new Intent(this, RegisterMotherActivity.class);
                            intent.putExtra("father", parentsBundle);
                            startActivity(intent);
                            break;
                        } else {
                            if ((etInsuranceTimeStart.getText() == null) || ("".equals(etInsuranceTimeStart.getText().toString().trim()))
                                    || (etInsuranceTimeEnd.getText() == null) || ("".equals(etInsuranceTimeEnd.getText().toString().trim()))) {
                                Toast.makeText(RegisterFatherActivity.this, "请输入保险时间", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(this, RegisterMotherActivity.class);
                                intent.putExtra("father", parentsBundle);
                                startActivity(intent);
                                break;
                            }
                        }

                    }
                }
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
            datePicker.setCalendarViewShown(false);
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
}
