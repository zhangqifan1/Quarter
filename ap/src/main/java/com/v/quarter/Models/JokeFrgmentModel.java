package com.v.quarter.Models;

import android.os.Environment;

import com.v.quarter.API.IJokeFramentModel;
import com.v.quarter.JavaBeans.JokeBean;
import com.v.quarter.RxJava_Retrofit.RetrofitInstance;
import com.v.quarter.Utils.FileUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/16.
 */

public class JokeFrgmentModel implements IJokeFramentModel {
    @Override
    public void getJokeBean(final CallBack callBack) {
        final File file = new File(Environment.getExternalStorageDirectory(), "JokeInfo.txt");
//        Object o = FileUtil.ReadFromLocalFile(file);
//
//        if (o instanceof JokeBean) {
//            callBack.setJokeBean((JokeBean) o);
//        }


        Object o = FileUtil.ReadFromFile(file);
        if (o != null) {
            callBack.setJokeBean((JokeBean) o);
        } else {

            Observable<JokeBean> jokeBean =
                    RetrofitInstance.getApiInterface().getJokeBean();
            jokeBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<JokeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(JokeBean jokeBean) {
                            FileUtil.writeObjectToFile(jokeBean, file);
                            callBack.setJokeBean(jokeBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            callBack.setJokeBean(null);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
