package com.v.quarter.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.v.quarter.JavaBeans.QQCallBack;
import com.v.quarter.R;

import java.util.Map;
import java.util.Set;

import de.greenrobot.event.EventBus;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private UMShareAPI mShareAPI = null;
    private SHARE_MEDIA platform = null;
    private RelativeLayout mIvLog;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mShareAPI = UMShareAPI.get(this);
        initView();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        mIvLog = findViewById(R.id.ivLog);
        mIvLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLog:
                platform = SHARE_MEDIA.QQ;
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                mShareAPI.deleteOauth(LoginActivity.this, platform, umAuthListener);
                break;
        }
    }


    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onComplete(SHARE_MEDIA arg0, int arg1,
                               Map<String, String> data) {
            QQCallBack callBack=new QQCallBack();
            if (data != null) {
                Set<String> keySet = data.keySet();
                //得到头像
                String iconurl = new String();
                //得到昵称
                String screenname = new String();
                //得到性别
                String sex=new String();
                for (String string : keySet) {
                    if (string.equals("screen_name")) {
                        //获取登录的名字
                        screenname = data.get("screen_name");
                        callBack.setName(screenname);
                    }
                    if (string.equals("profile_image_url")) {
                        //获取登录的图片
                        iconurl = data.get("profile_image_url");
                        callBack.setImageUrl(iconurl);
                    }
                    if(string.equals("gender")){
                        sex=data.get("gender");
                        callBack.setSex(sex);
                    }

                }
                callBack.setTourist(false);
                EventBus.getDefault().postSticky(callBack);
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show();
        }
    };

    //记得要重写这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    public  void InTourist(View view){
       startActivity(new Intent(this,Login2Activity.class));
    }

    public void LoginBack(View view) {
        finish();
    }


}

