package com.v.quarter.API;

import android.app.Activity;

/**
 * Created by Administrator on 2017/11/11.
 */

public interface ILogin2Model {
    void getLoginInfoFromUrl(LogInfoCallBack callBack,String userPhone, String userPassword);
    interface LogInfoCallBack{
        void setResult(int i);
    }
}
