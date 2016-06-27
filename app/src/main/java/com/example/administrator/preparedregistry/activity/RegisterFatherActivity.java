package com.example.administrator.preparedregistry.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
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
    private ToggleButton toggleButton;
    private LinearLayout layoutNotDie, layoutDie, layoutInsuranceTime;

    private TextView tvLabelOne, tvLabelTwo, tvLabelThree;
    private Spinner spiInsurance;
    private EditText etInsuranceTimeStart,etInsuranceTimeEnd,etLiveStart,etLiveEnd,etBusinessStart,etBusinessEnd;

    @Override
    protected int initLayout() {
        return R.layout.activity_register_father;
    }

    @Override
    protected void initView() {
        btnBack = bindView(R.id.registerfather_back);
        toggleButton = bindView(R.id.registerfather_togglebutton);
        layoutNotDie = bindView(R.id.registerfather_layout_notdie);
        layoutDie = bindView(R.id.registerfather_layout_die);
        tvLabelOne = bindView(R.id.registerfather_text_labelOne);
        tvLabelTwo = bindView(R.id.registerfather_text_labelTwo);
        tvLabelThree = bindView(R.id.registerfather_text_labelThree);
        layoutInsuranceTime = bindView(R.id.registerfather_layout_insuranceTime);
        spiInsurance = bindView(R.id.registerfather_spinner_insurance);
        etInsuranceTimeStart=bindView(R.id.registerfather_et_insuranceTimeStart);
        etInsuranceTimeEnd=bindView(R.id.registerfather_et_insuranceTimeEnd);
        etLiveStart=bindView(R.id.registerfather_et_liveStart);
        etLiveEnd=bindView(R.id.registerfather_et_liveEnd);
        etBusinessStart=bindView(R.id.registerfather_et_businessStart);
        etBusinessEnd=bindView(R.id.registerfather_et_businessEnd);
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

        spiInsurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // TODO  改为保险的 array
                String[] insurance = getResources().getStringArray(R.array.ifhavehome);
                if (insurance[position].equals("无")) {
                    layoutInsuranceTime.setVisibility(View.GONE);
                } else {
                    layoutInsuranceTime.setVisibility(View.VISIBLE);
                }
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
            case R.id.registerfather_back:
                //TODO finish基本信息页
                finish();
                break;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            View view=View.inflate(this,R.layout.dialog_date,null);
            final DatePicker datePicker= (DatePicker) view.findViewById(R.id.dialog_date_picker);
            builder.setView(view);

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
            if (v.getId()==R.id.registerfather_et_insuranceTimeStart){
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

            }else if (v.getId()==R.id.registerfather_et_insuranceTimeEnd){
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
            }else if (v.getId()==R.id.registerfather_et_liveStart){
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
            }else if (v.getId()==R.id.registerfather_et_liveEnd){
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
            }else if (v.getId()==R.id.registerfather_et_businessStart){
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
            }else if (v.getId()==R.id.registerfather_et_businessEnd){
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
