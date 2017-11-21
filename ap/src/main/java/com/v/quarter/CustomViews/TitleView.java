package com.v.quarter.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v.quarter.R;

import java.net.URL;

/**
 * Created by Administrator on 2017/11/8.
 */

public class TitleView extends RelativeLayout {

    private ImageView imageLeft;
    private TextView tvTitle;
    private ImageView imageRight;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.titleview, this);
        imageLeft = view.findViewById(R.id.imageLeft);
        tvTitle = view.findViewById(R.id.tvTitle);
        imageRight = view.findViewById(R.id.imageRight);
    }
    public void imageLeftOnclick(OnClickListener listener){
        imageLeft.setOnClickListener(listener);
    }
    public void imageRightOnclick(OnClickListener listener){
        imageRight.setOnClickListener(listener);
    }
    public void setImageLeftView(String url,Context context){
        Glide.with(context).load(url).asBitmap().into(imageLeft);
    }
    public void setImageLeftView(){
        imageLeft.setImageDrawable(getResources().getDrawable(R.drawable.def));
    }
    public void setTitleText(String s){
        tvTitle.setText(s);
    }

}
