package com.v.quarter.CustomViews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v.quarter.R;

/**
 * Created by Administrator on 2017/11/16.
 */

public class MenuView extends RelativeLayout {


    public ImageView imageJubaoJ, imagePingbiJ, imageCopyLineJ, imageOpenMenuJ;
    public TextView tvJubaoJ, tvPingbiJ, tvCopyLineJ;
    private int width;


    public MenuView(Context context) {
        this(context, null);
    }

    public MenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

        if(aBoolean=false){
            initLocation();
        }else{
            init2Location();
        }
    }


    private Boolean aBoolean=false;

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    private void initLocation() {
        int top = imageCopyLineJ.getTop();
        int bottom = imageCopyLineJ.getBottom();
        imageCopyLineJ.layout(0, top, 0, bottom);
        imageJubaoJ.layout(0, top, 0, bottom);
        imagePingbiJ.layout(0, top, 0, bottom);
        tvCopyLineJ.layout(0, top, 0, bottom);
        tvJubaoJ.layout(0, top, 0, bottom);
        tvPingbiJ.layout(0, top, 0, bottom);
        imageOpenMenuJ.setImageResource(R.drawable.openmenu);
    }
    private void init2Location() {
        int top = imageCopyLineJ.getTop();
        int width = imageCopyLineJ.getWidth();
        int bottom = imageCopyLineJ.getBottom();

        imageCopyLineJ.layout(width*2, top, 0, bottom);
        imageJubaoJ.layout(width, top, 0, bottom);
        imagePingbiJ.layout(width*3, top, 0, bottom);
        tvCopyLineJ.layout(width*2, top, 0, bottom);
        tvJubaoJ.layout(width+10, top, 0, bottom);
        tvPingbiJ.layout(width*3+20, top, 0, bottom);
//        imageOpenMenuJ.setImageResource(R.drawable.openmenu);
    }



    private void init(Context context) {
        View inflate = View.inflate(context, R.layout.menuview, this);
        imageJubaoJ = inflate.findViewById(R.id.imageJubaoJ);
        imagePingbiJ = inflate.findViewById(R.id.imagePingbiJ);
        imageCopyLineJ = inflate.findViewById(R.id.imageCopyLineJ);
        imageOpenMenuJ = inflate.findViewById(R.id.imageOpenMenuJ);
        tvJubaoJ = inflate.findViewById(R.id.tvJubaoJ);
        tvPingbiJ = inflate.findViewById(R.id.tvPingbiJ);
        tvCopyLineJ = inflate.findViewById(R.id.tvCopyLineJ);
    }


    public void closeMenu() {
        width = imageJubaoJ.getWidth();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageJubaoJ, "TranslationX", -width, 0f);
        ObjectAnimator animatorTV1 = ObjectAnimator.ofFloat(tvJubaoJ, "TranslationX", -width - 10, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageCopyLineJ, "TranslationX", -width * 2, 0f);
        ObjectAnimator animatorTV2 = ObjectAnimator.ofFloat(tvCopyLineJ, "TranslationX", -width * 2, 0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imagePingbiJ, "TranslationX", -width * 3, 0f);
        ObjectAnimator animatorTV3 = ObjectAnimator.ofFloat(tvPingbiJ, "TranslationX", -width * 3 - 20, 0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageOpenMenuJ, "Rotation", 0f, 360);

        //启动动画
        animator1.setDuration(1500).start();
        animator2.setDuration(1500).start();
        animator3.setDuration(1500).start();
        animator4.setDuration(1500).start();
        animatorTV1.setDuration(1500).start();
        animatorTV2.setDuration(1500).start();
        animatorTV3.setDuration(1500).start();
        animator4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tvPingbiJ.setVisibility(View.GONE);
                tvJubaoJ.setVisibility(View.GONE);
                tvCopyLineJ.setVisibility(View.GONE);
                imageOpenMenuJ.setImageResource(R.drawable.openmenu);
            }
        });
    }

    public void openMenu() {
        width = imageJubaoJ.getWidth();
        tvPingbiJ.setVisibility(View.VISIBLE);
        tvJubaoJ.setVisibility(View.VISIBLE);
        tvCopyLineJ.setVisibility(View.VISIBLE);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageJubaoJ, "TranslationX", 0f, -50);
        ObjectAnimator animatorT1 = ObjectAnimator.ofFloat(tvJubaoJ, "TranslationX", 0f, -width - 10);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageCopyLineJ, "TranslationX", 0f, -width * 2);
        ObjectAnimator animatorT2 = ObjectAnimator.ofFloat(tvCopyLineJ, "TranslationX", 0f, -width * 2);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imagePingbiJ, "TranslationX", 0f, -width * 3);
        ObjectAnimator animatorT3 = ObjectAnimator.ofFloat(tvPingbiJ, "TranslationX", 0f, -width * 3 - 20);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageOpenMenuJ, "Rotation", 0f, 360);

        //添加自由落体效果插值器
        animator1.setInterpolator(new BounceInterpolator());
        animator2.setInterpolator(new BounceInterpolator());
        animator3.setInterpolator(new BounceInterpolator());
        animator4.setInterpolator(new BounceInterpolator());
        animatorT1.setInterpolator(new BounceInterpolator());
        animatorT2.setInterpolator(new BounceInterpolator());
        animatorT3.setInterpolator(new BounceInterpolator());

        //启动动画
        animator1.setDuration(1500).start();
        animator2.setDuration(1500).start();
        animator3.setDuration(1500).start();
        animator4.setDuration(1500).start();
        animatorT1.setDuration(1500).start();
        animatorT2.setDuration(1500).start();
        animatorT3.setDuration(1500).start();

        animator4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageOpenMenuJ.setImageResource(R.drawable.closemenu);
            }
        });
    }


}
