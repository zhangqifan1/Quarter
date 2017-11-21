package com.v.quarter.Base;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.v.quarter.R;

/**
 * Created by Administrator on 2017/11/9.
 */

public  class BaseActivity extends AppCompatActivity {

    private SystemBarTintManager tintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断当前系统版本是否>=Andoird4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置状态栏背景状态
            //true：表明当前Android系统版本>=4.4
            setTranslucentStatus(true);
        }
        //实例化SystemBarTintManager
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        // 通知标题栏所需颜色
        setColor(R.color.colorTitle);
    }

    public  void setColor(int color){
        tintManager.setStatusBarTintResource(color);
    };
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
