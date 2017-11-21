package com.v.quarter.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.v.quarter.Adapters.FragmentAdapter;
import com.v.quarter.ContentFragment.CareFragment;
import com.v.quarter.ContentFragment.HotFragment;
import com.v.quarter.JavaBeans.DayNightBean;
import com.v.quarter.R;
import com.v.quarter.Utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecomFragment extends Fragment {

    private RelativeLayout relaRecom;
    private TabLayout mTabLayout;
    private ViewPager mMyViewPager;

    @Subscribe(sticky = true)
    public void DayNightReceive(DayNightBean bean) {
        if (!bean.isDay()) {
            relaRecom.setBackgroundColor(Color.BLACK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_recom, container, false);
        initView(inflate);
        initData();


        return inflate;
    }

    private void initData() {
        //准备数据
        List<Fragment> list = new ArrayList<>();
        HotFragment hotFragment = new HotFragment();
        CareFragment careFragment = new CareFragment();
        list.add(hotFragment);
        list.add(new HotFragment());
        //设置标题
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        //适配器
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager());
        fragmentAdapter.setList(list);

        //搭建
        mMyViewPager.setAdapter(fragmentAdapter);
        mTabLayout.getTabAt(0).setText("热门");
        mTabLayout.getTabAt(1).setText("关注");

    }

    private void initView(View inflate) {
        relaRecom = (RelativeLayout) inflate.findViewById(R.id.relaRecom);
        mTabLayout = (TabLayout) inflate.findViewById(R.id.TabLayout);
        mMyViewPager = (ViewPager) inflate.findViewById(R.id.MyViewPager);
        mTabLayout.setupWithViewPager(mMyViewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(mTabLayout, 50, 50);
            }
        });
    }


}
