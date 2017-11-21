package com.v.quarter.RxJava_Retrofit;

import com.v.quarter.JavaBeans.FindHotBean;
import com.v.quarter.JavaBeans.JokeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mamiaomiao on 2017/11/1.
 */

public interface APIInterface {
    String BASE = "http://172.17.29.27";

    //https://api.github.com/users/Guolei1130
    //接口定义；注解方式添加请求方式；get请求内部放拼接的连接和需要传递的参数；
    @GET("/quarter/user/findHot")
    Observable<FindHotBean> getFindHotBean();


    //http://172.17.29.27/quarter/character/select_character
    @GET("/quarter/character/select_character")
    Observable<JokeBean> getJokeBean();

    //    @GET("user/{username}")
//    Call<ResponseBody> getLoginInfo(@Path("username") String user);
//
//    @GET("user/{username}")
//    Observable<LoginBean> getLoginRx(@Path("username") String user);

    //注册POST
    @FormUrlEncoded
    @POST("/quarter/user/addUser")
    Observable<Object> getRegInfo(@Field("userName") String name, @Field("userPassword") String userPassword, @Field("userPhone") String userPhone, @Field("userSex") String userSex);

    //登录POST
    @FormUrlEncoded
    @POST("/quarter/user/addLogin")
    Observable<Object> getLoginInfo(@Field("userPassword") String userPassword, @Field("userPhone") String userPhone);
}
