package com.v.quarter.Models;

import com.v.quarter.API.ILogin2Model;
import com.v.quarter.RxJava_Retrofit.RetrofitInstance;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/11.
 */

public class Login2Model implements ILogin2Model {

    @Override
    public void getLoginInfoFromUrl(final LogInfoCallBack callBack, String userPhone, String userPassword) {

        if(userPhone==null  || userPassword==null ){
            callBack.setResult(2);
        }else{
        Observable<Object> loginInfo = RetrofitInstance.getApiInterface().getLoginInfo(userPassword, userPhone);
        Observable<Object> objectObservable = loginInfo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        try {
            objectObservable.subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object o) {
                    String string = o.toString();

                    System.out.println(string);
                    if (string.contains("200")) {
                        callBack.setResult(1);
                    } else if (string.contains("500")) {
                        callBack.setResult(3);
                    } else {
                        callBack.setResult(2);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    callBack.setResult(2);
                    System.out.println("0000000000000000000000");
                }

                @Override
                public void onComplete() {

                }
            });
        } catch (java.lang.AssertionError e) {//防止SocketTimeoutException
            e.printStackTrace();
        }

        }
    }
}
