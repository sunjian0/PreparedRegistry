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

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;
import com.example.administrator.preparedregistry.city.CityPicker;
import com.example.administrator.preparedregistry.dialog.CommitmentDialog;


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

        showCommitmentDialog();
        //showDialog();

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

    private void showCommitmentDialog() {
        CommitmentDialog.Builder builder = new CommitmentDialog.Builder(this);

        builder.setMessage("\u3000\u3000" + "为申请本人子女在接受义务教育，我承诺以下填写的信息合法、真实、有效，" +
                "并同意教育行政部门对我提交的信息及材料与相关部门进行核实。");
        builder.setTitle("承诺");

        builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("不同意",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();

                    }
                });


        builder.create().show();

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
