package com.v.quarter.Activities;

import android.os.Bundle;

import com.v.quarter.Base.BaseActivity;
import com.v.quarter.R;

public class ShuoShuoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuo_shuo);



    }

    @Override
    public void setColor(int color) {
        color = R.color.colorTitle;
        super.setColor(color);
    }

}
