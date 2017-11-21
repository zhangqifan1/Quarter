package com.v.quarter.Presenter;

import android.app.Activity;

import com.v.quarter.API.IRegModel;
import com.v.quarter.API.RegAcitivityView;
import com.v.quarter.Models.RegModel;

/**
 * Created by Administrator on 2017/11/9.
 */

public class RegPresenter {
    private RegAcitivityView regAcitivityView;
    private RegModel regModel;
    public RegPresenter(RegAcitivityView regAcitivityView,Activity activity) {
        this.regAcitivityView = regAcitivityView;
        regModel=new RegModel();
    }
    public void setResultInfo() {
        regModel.getLoginInfoFromUrl( new IRegModel.RegInfoCallBack() {
            @Override
            public void setResult(int i) {
                regAcitivityView.setIsSuccessful(i);
            }
        }, regAcitivityView.getUserName(), regAcitivityView.getUserPassword(), regAcitivityView.getUserPhone(), regAcitivityView.getUserSex());
    }
}
