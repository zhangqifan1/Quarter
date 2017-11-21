package com.v.quarter.RxJava_Retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private Retrofit mRetrofit = null;
    private OkHttpClient mOkHttpClient = null;
    private APIInterface mApiInterface = null;
    private RetrofitInstance() {
        initOkHttp();
        initRetrofit();
        mApiInterface = mRetrofit.create(APIInterface.class);
    }

    private void initOkHttp() {
        //设置拦截器，打印出每次的请求结果,在初始化okhttp时 addInterceptor
        //带上token则 addNetworkInterceptor
        //https://drakeet.me/retrofit-2-0-okhttp-3-0-config

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Cookie的持久化管理，为每次请求带上cookie
        //http://blog.csdn.net/lyhhj/article/details/51345386
        CookieJar mCookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                Hawk.put("cookie", cookies.get(cookies.size() - 1));
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());

                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };


        mOkHttpClient = new OkHttpClient.Builder()
        //.addInterceptor(XiQueApplication.isDebug ? loggingInterceptor : null)
                .addInterceptor(loggingInterceptor)
//                .cookieJar(mCookieJar)
//                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(APIInterface.BASE)
                .build();
    }

    private static class SingletonHolder {
        private static final RetrofitInstance INSTANCE = new RetrofitInstance();
    }

    //获取retrofit单例
    public static RetrofitInstance getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //获取apiInterface单例
    public static <T> APIInterface getApiInterface() {
        return getInstance().mApiInterface;
    }

    public static void disposeFailureInfo(Throwable t) {
        if (t.toString().contains("GaiException")
                || t.toString().contains("SocketTimeoutException")
                || t.toString().contains("UnknownHostException")) {
//            ToastUtil.showLong("网络不给力");
        } else if (t.toString().contains("ConnectException")) {
//            ToastUtil.showLong("网络连接失败");
        } else if (t.getMessage().equals("未登录")) {
//            UnLoginDispose.startLoginActivity();
//            ToastUtil.show("未登录");
        } else {
//            ToastUtil.show("其它错误");
        }

    }


}