package com.v.quarter.JavaBeans;

/**
 * Created by Administrator on 2017/11/10.
 */

public class QQCallBack {
    public String Name, ImageUrl, Sex;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    boolean IsTourist;

    public boolean isTourist() {
        return IsTourist;
    }

    public void setTourist(boolean tourist) {
        IsTourist = tourist;
    }

}
