package com.v.quarter.Presenter;

import com.v.quarter.API.MainAcitivityView;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MainPresenter {
    private MainAcitivityView mainAcitivityInterface;

    public MainPresenter(MainAcitivityView mainAcitivityInterface) {

        this.mainAcitivityInterface = mainAcitivityInterface;
    }

    public void init() {
        mainAcitivityInterface.setBottomBar();
        mainAcitivityInterface.setOn_Off();
        mainAcitivityInterface.setRight();
    }




}
