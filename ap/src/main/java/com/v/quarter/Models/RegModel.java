package com.v.quarter.Models;

import com.v.quarter.API.IRegModel;
import com.v.quarter.RxJava_Retrofit.RetrofitInstance;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/9.
 */

public class RegModel implements IRegModel {

    @Override
    public void getLoginInfoFromUrl(final RegInfoCallBack callBack, final String userName, final String userPassword, final String userPhone, final String userSex) {
        if (userPhone == null || userPassword == null || userName == null || userSex == null) {
            callBack.setResult(2);
        } else {
            Observable<Object> loginInfo = RetrofitInstance.getApiInterface().getRegInfo(userName, userPassword, userPhone, userSex)

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            loginInfo.subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object responseBody) {
                    String string = responseBody.toString();
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
                    callBack.setResult(4);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

}
