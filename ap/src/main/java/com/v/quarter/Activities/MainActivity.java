package com.v.quarter.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjm.bottomtabbar.BottomTabBar;
import com.v.quarter.API.MainAcitivityView;
import com.v.quarter.Base.BaseActivity;
import com.v.quarter.CustomViews.ShapeImageView;
import com.v.quarter.CustomViews.SlidingMenu;
import com.v.quarter.CustomViews.TitleView;
import com.v.quarter.DayAndNight.ThemeManager;
import com.v.quarter.Fragment.JokeFragment;
import com.v.quarter.Fragment.RecomFragment;
import com.v.quarter.Fragment.VideoFragment;
import com.v.quarter.JavaBeans.DayNightBean;
import com.v.quarter.JavaBeans.QQCallBack;
import com.v.quarter.Presenter.MainPresenter;
import com.v.quarter.R;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class MainActivity extends BaseActivity implements MainAcitivityView, ThemeManager.OnThemeChangeListener {


    private SlidingMenu mMenu;
    private TitleView mTitleView;
    private BottomTabBar mBottomTabBar;
    private ShapeImageView QQHead;
    private TextView QQName;
    private ImageView QQSex;
    private ImageView moon;
    private TextView tvMode;
    private RadioButton onoff;
    private boolean flag = false;
    private TextView textCare;
    private TextView textCollect;
    private TextView textSearchFridend;
    private TextView textMessageNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        MainPresenter presenter = new MainPresenter(this);
        presenter.init();

        //日夜间模式
        ThemeManager.registerThemeChangeListener(this);

    }


    private void initView() {
        mTitleView = (TitleView) findViewById(R.id.titleView);
        mMenu = (SlidingMenu) findViewById(R.id.id_menu);
        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        QQHead = (ShapeImageView) findViewById(R.id.QQHead);
        QQName = (TextView) findViewById(R.id.QQName);
        QQSex = (ImageView) findViewById(R.id.QQSex);
        moon = (ImageView) findViewById(R.id.moon);
        tvMode = (TextView) findViewById(R.id.tvMode);
        onoff = findViewById(R.id.onoff);
        textCare = (TextView) findViewById(R.id.textCare);
        textCollect = (TextView) findViewById(R.id.textCollect);
        textSearchFridend = (TextView) findViewById(R.id.textSearchFridend);
        textMessageNotify = (TextView) findViewById(R.id.textMessageNotify);
    }

    @Override
    public void setBottomBar() {
        //这个init ( getSupportFragmentManager() )方法一定要第一个调用，没有//这个初始化，后边什么也做不了。
        mBottomTabBar.init(getSupportFragmentManager())
                .addTabItem("推荐", R.drawable.p1, R.drawable.p3, RecomFragment.class)
                .addTabItem("段子", R.drawable.p1, R.drawable.p4, JokeFragment.class)
                .addTabItem("视频", R.drawable.p1, R.drawable.p2, VideoFragment.class);
mBottomTabBar.setTabBarBackgroundColor(Color.parseColor("#828090"));
        mBottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
            @Override
            public void onTabChange(int position, String name) {
                mTitleView.setTitleText(name);
            }
        });

        //注册  接收粘性事件
        EventBus.getDefault().register(this);
    }

    //自定义接收事件的方法
    @Subscribe(sticky = true)
    public void receiveQQCallBack(QQCallBack callBack) {

        if (callBack.isTourist()) {
            QQName.setText("路人甲表示不服");
            QQSex.setImageDrawable(getResources().getDrawable(R.drawable.women));
            QQHead.setImageDrawable(getResources().getDrawable(R.drawable.def));
            mTitleView.setImageLeftView();
        } else {
            Glide.with(this).load(callBack.getImageUrl()).asBitmap().into(QQHead);
            QQName.setText(callBack.getName());
            String nan = "男";
            if (callBack.getSex().equals(nan)) {
                QQSex.setImageDrawable(getResources().getDrawable(R.drawable.man));
            } else {
                QQSex.setImageDrawable(getResources().getDrawable(R.drawable.women));
            }
            mTitleView.setImageLeftView(callBack.getImageUrl(), this);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterThemeChangeListener(this);
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void setOn_Off() {
        mTitleView.imageLeftOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenu.toggle();
            }
        });
    }

    @Override
    public void setRight() {
        mTitleView.imageRightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,IntentActivity.class));
            }
        });

    }

    //登录
    public void ToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }


    //我的关心
    public void ToMyCare(View view) {

    }

    //消息通知
    public void ToMessageNotify(View view) {
    }

    //搜索好友
    public void ToSeachFriend(View view) {
    }

    //我的收藏
    public void ToMyCollection(View view) {
    }

    //日夜间
    public void Moon(View view) {
        ThemeManager.setThemeMode(ThemeManager.getThemeMode() == ThemeManager.ThemeMode.DAY
                ? ThemeManager.ThemeMode.NIGHT : ThemeManager.ThemeMode.DAY);

        tvMode.setText((flag) ? "夜间模式" : "日间模式");

        DayNightBean dayNightBean = new DayNightBean();
        moon.setImageDrawable((flag) ? getResources().getDrawable(R.drawable.moondark) : getResources().getDrawable(R.drawable.moonlight));
        if (flag) {
            //夜间模式
            dayNightBean.setDay(false);
            EventBus.getDefault().postSticky(dayNightBean);
            flag = false;
            onoff.setChecked(false);
        } else {
            //日间模式
            dayNightBean.setDay(true);
            EventBus.getDefault().postSticky(dayNightBean);
            onoff.setChecked(true);
            flag = true;
        }


    }


    @Override
    public void onThemeChanged() {
        mMenu.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.backgroundColor)));
        tvMode.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        QQName.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        textCollect.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        textCare.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        textMessageNotify.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        textSearchFridend.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        mBottomTabBar.setTabBarBackgroundColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.bottomBar));
    }

    @Override
    public void setColor(int color) {
        color=R.color.colorTitle;
        super.setColor(color);
    }


    //我的作品
    public void MyWorks(View view) {

    }

    //我的设置
    public void MySettings(View view) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}