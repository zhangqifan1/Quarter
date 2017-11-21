package com.v.quarter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.quarter.Base.BaseActivity;
import com.v.quarter.R;

public class IntentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
    }

    @Override
    public void setColor(int color) {
        color=R.color.colorTitle;
        super.setColor(color);
    }

    //去发说说
    public void ShuoShuo(View view) {
        startActivity(new Intent(this,ShuoShuoActivity.class));
    }
}
