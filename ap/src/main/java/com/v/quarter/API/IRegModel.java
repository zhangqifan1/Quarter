package com.v.quarter.API;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2017/11/9.
 */

public interface IRegModel {
    void getLoginInfoFromUrl(RegInfoCallBack callBack, String userName, String userPassword, String userPhone, String userSex);

    interface RegInfoCallBack{
        void setResult(int i);
    }
}
