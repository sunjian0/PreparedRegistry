package com.example.administrator.preparedregistry.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.preparedregistry.MyDrawable;
import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.base.BaseActivity;
import com.example.administrator.preparedregistry.dialog.TimeTipsDialog;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static String preregistermessage = "不在预登记时间范围内";
    private static String mRegisterBeginTime = "2016-1-1";
    private static String mRegisterEndTime = "2016-2-2";
    private ImageView imageView;
    private LinearLayout notice, register, change, checkResult, applyForCheck, school;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        // TODO 状态栏
//        Window window=this.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////设置状态栏颜色
//        window.setStatusBarColor(R.color.head_color);

        imageView = bindView(R.id.main_image);

        notice = bindView(R.id.main_notice);
        register = bindView(R.id.main_register);
        change = bindView(R.id.main_change);
        checkResult = bindView(R.id.main_checkResult);
        applyForCheck = bindView(R.id.main_applyForCheck);
        school = bindView(R.id.main_school);

    }

    @Override
    protected void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_xiugai_100x100px);
        MyDrawable drawable = new MyDrawable(bitmap);
        imageView.setImageDrawable(drawable);

        notice.setOnClickListener(this);
        register.setOnClickListener(this);
        change.setOnClickListener(this);
        checkResult.setOnClickListener(this);
        applyForCheck.setOnClickListener(this);
        school.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_notice:
                //是否在规定时间
                if (false) {
                    Intent intentNotice = new Intent(MainActivity.this, NoticeActivity.class);
                    startActivity(intentNotice);
                } else {
                    showTimeTips(preregistermessage, mRegisterBeginTime, mRegisterEndTime);
                }
                break;
            case R.id.main_register:

                Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegister);

                break;
            //结果核查
            case R.id.main_checkResult:
                ResultCheckActivity.startAction(MainActivity.this);
                break;
            //复核申请
            case R.id.main_applyForCheck:
                RecheckRequest.startAction(MainActivity.this);
                break;
            //学院信息
            case R.id.main_school:
                SchoolInfor.startAction(MainActivity.this);
                break;
            //faq
            /*
             case R.id.action_settings:
                Faq.startAction(MainActivity.this);
                break;
            * */

        }
    }

    private void showTimeTips(String message, String begintime, String endTime) {
        TimeTipsDialog.Builder builder = new TimeTipsDialog.Builder(this);
        builder.setDialogmessage(message);
        builder.setBeginningTime(begintime);
        builder.setEndingTime(endTime);
        builder.create().show();
    }

}
