package com.v.quarter.Presenter;

import com.v.quarter.API.ILogin2ActivityView;
import com.v.quarter.API.ILogin2Model;
import com.v.quarter.Models.Login2Model;

/**
 * Created by Administrator on 2017/11/11.
 */

public class Login2Presenter {
    private ILogin2ActivityView iLogin2ActivityView;
    private Login2Model login2Model;

    public Login2Presenter(ILogin2ActivityView iLogin2ActivityView) {
        this.iLogin2ActivityView = iLogin2ActivityView;
        login2Model = new Login2Model();
    }


    public  void setLoginInfo(){
        login2Model.getLoginInfoFromUrl(new ILogin2Model.LogInfoCallBack() {
            @Override
            public void setResult(int i) {
                iLogin2ActivityView.setIsSuccessful(i);
            }
        },iLogin2ActivityView.getUserPhone(),iLogin2ActivityView.getUserPassword());
    }
}
