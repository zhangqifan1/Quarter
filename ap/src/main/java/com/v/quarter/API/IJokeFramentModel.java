package com.v.quarter.API;

import com.v.quarter.JavaBeans.JokeBean;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface IJokeFramentModel {
    void getJokeBean(CallBack callBack);
    interface CallBack{
        void setJokeBean(JokeBean bean);
    }
}
