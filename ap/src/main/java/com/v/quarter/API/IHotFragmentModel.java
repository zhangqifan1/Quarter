package com.v.quarter.API;

import com.v.quarter.JavaBeans.FindHotBean;

/**
 * Created by Administrator on 2017/11/14.
 */

public interface IHotFragmentModel {
    void getFindHotBean(CallBack callBack);
    interface CallBack{
        void setHotBean(FindHotBean bean);
    }
}
