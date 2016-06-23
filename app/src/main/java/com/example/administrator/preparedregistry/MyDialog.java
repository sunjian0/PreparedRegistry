package com.example.administrator.preparedregistry;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/6/17.
 */
public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_register);
        // 实例化新的窗口
        Window w = getWindow();
        // 获取默认显示数据
        Display display = w.getWindowManager().getDefaultDisplay();

        // 定义窗口的宽和高
        int width = (int) (display.getWidth() * 0.8);
        int height = (int) (display.getHeight() * 0.5);
        // 设置窗口的大小
        w.setLayout(width, height);
        // 设置窗口的显示位置
        w.setGravity(Gravity.CENTER);
        // 设置窗口的属性
        WindowManager.LayoutParams wl = w.getAttributes();
        w.setAttributes(wl);

    }
}
