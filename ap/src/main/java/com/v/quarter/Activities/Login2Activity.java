package com.v.quarter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.v.quarter.API.ILogin2ActivityView;
import com.v.quarter.Base.BaseActivity;
import com.v.quarter.JavaBeans.QQCallBack;
import com.v.quarter.Presenter.Login2Presenter;
import com.v.quarter.R;

import de.greenrobot.event.EventBus;

public class Login2Activity extends AppCompatActivity implements ILogin2ActivityView {

    private EditText etName2;
    private EditText etPwd2;
    private Login2Presenter login2Presenter;
    private Animation animation;
    private ImageView progressLog;
    private RelativeLayout realtabcontentLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
        login2Presenter = new Login2Presenter(this);

    }

    public void InTourist2(View view) {
        Toast.makeText(this, "游客登录", Toast.LENGTH_SHORT).show();
        //游客登录
        QQCallBack callBack = new QQCallBack();
        callBack.setTourist(true);
        EventBus.getDefault().postSticky(callBack);
        startActivity(new Intent(this, MainActivity.class));
    }

    public void ToReg(View view) {
        startActivity(new Intent(this, RegActivity.class));
    }


    //登录
    public void Login(View view) {
        realtabcontentLog.setVisibility(View.VISIBLE);
        startRotate();
        login2Presenter.setLoginInfo();
    }

    @Override
    public String getUserPassword() {
        String s = etPwd2.getText().toString();
        if (s != null && !s.equals("")) {
            return s;
        } else {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    public String getUserPhone() {
        String s = etName2.getText().toString();
        if (s.length() != 11) {
            Toast.makeText(this, "手机号11位", Toast.LENGTH_SHORT).show();
        }
        if (s != null && !s.equals("")) {
            return s;
        }
        return null;
    }

    @Override
    public void setIsSuccessful(int i) {
        realtabcontentLog.setVisibility(View.GONE);
        animation.cancel();
        if (i == 1) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else if (i == 2) {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        } else if (i == 3) {
            Toast.makeText(this, "登录失败500", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        etName2 = (EditText) findViewById(R.id.etName2);
        etPwd2 = (EditText) findViewById(R.id.etPwd2);

        progressLog = (ImageView) findViewById(R.id.progressLog);
        realtabcontentLog = (RelativeLayout) findViewById(R.id.realtabcontentLog);
    }

    private void startRotate() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        progressLog.setAnimation(animation);
        animation.setDuration(2000);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(-1);
        animation.start();
    }


    //忘记密码  直接去
    public void forgetPwd(View view) {
        Toast.makeText(this, "没有接口,你再办个手机卡注册一下吧,么么哒!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,RegActivity.class));

    }

    public void Login2Back(View view) {
        finish();
    }



}
