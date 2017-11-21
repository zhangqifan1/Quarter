package com.v.quarter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.v.quarter.API.RegAcitivityView;
import com.v.quarter.Base.BaseActivity;
import com.v.quarter.JavaBeans.QQCallBack;
import com.v.quarter.Presenter.RegPresenter;
import com.v.quarter.R;

import de.greenrobot.event.EventBus;

public class RegActivity extends AppCompatActivity implements RegAcitivityView, View.OnClickListener {


    private EditText mEtName;
    private EditText mEtPwd;
    private EditText mEtPhone;
    private EditText mEtSex;
    private Button mButReg;
    private Button mButTourist;
    private RegPresenter regPresenter;
    private ImageView progress;
    private RelativeLayout realtabcontent;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        regPresenter = new RegPresenter(this, this);
    }


    @Override
    public String getUserName() {
        String s = mEtName.getText().toString();
        if (s != null && !s.equals("")) {
            return s;
        }
        return null;
    }

    @Override
    public String getUserPassword() {
        String s = mEtPwd.getText().toString();
        if (s != null && !s.equals("")) {
            return s;
        }
        return null;
    }

    @Override
    public String getUserPhone() {
        String s = mEtPhone.getText().toString();
        if (s.length() != 11) {
            Toast.makeText(this, "手机号11位", Toast.LENGTH_SHORT).show();
        }
        if (s != null && !s.equals("")) {
            return s;
        }
        return null;
    }

    @Override
    public String getUserSex() {
        String s = mEtSex.getText().toString();
        if (s != null && !s.equals("")) {
            return s;
        }
        return null;
    }

    @Override
    public void setIsSuccessful(int i) {

        realtabcontent.setVisibility(View.GONE);
        animation.cancel();
        if (i == 1) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        } else if (i == 2) {
            Toast.makeText(this, "输入不完整,注册失败", Toast.LENGTH_SHORT).show();
        } else if (i == 3) {
            Toast.makeText(this, "已经注册了", Toast.LENGTH_SHORT).show();
        } else if (i == 4) {
            Toast.makeText(this, "失败,服务器卡顿", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.etName);
        mEtPwd = (EditText) findViewById(R.id.etPwd);
        mEtPhone = (EditText) findViewById(R.id.etPhone);
        mEtSex = (EditText) findViewById(R.id.etSex);
        mButReg = (Button) findViewById(R.id.butReg);
        mButTourist = (Button) findViewById(R.id.butTourist);
        progress = findViewById(R.id.progress);
        mButReg.setOnClickListener(this);
        mButTourist.setOnClickListener(this);
        realtabcontent = (RelativeLayout) findViewById(R.id.realtabcontent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butReg:
                realtabcontent.setVisibility(View.VISIBLE);
                startRotate();
                regPresenter.setResultInfo();

                break;
            case R.id.butTourist:
                Toast.makeText(this, "游客登录", Toast.LENGTH_SHORT).show();
                //游客登录
                QQCallBack callBack = new QQCallBack();
                callBack.setTourist(true);
                EventBus.getDefault().postSticky(callBack);
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void startRotate() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        progress.setAnimation(animation);
        animation.setDuration(2000);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(-1);
        animation.start();
    }


    public void ToLoginActivity(View view) {
        startActivity(new Intent(this,Login2Activity.class));
    }
}
