package com.v.quarter.Models;

import android.os.Environment;

import com.v.quarter.API.IHotFragmentModel;
import com.v.quarter.JavaBeans.FindHotBean;
import com.v.quarter.RxJava_Retrofit.RetrofitInstance;
import com.v.quarter.Utils.FileUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/14.
 */

public class FindHotModel implements IHotFragmentModel {

    @Override
    public void getFindHotBean(final CallBack callBack) {
        final File file = new File(Environment.getExternalStorageDirectory(), "FindHotInfo.txt");
//        Object o = FileUtil.ReadFromLocalFile(file);
//        if(o instanceof FindHotBean){
//            callBack.setHotBean((FindHotBean) o);
//        }

        Object o = FileUtil.ReadFromFile(file);
        if (o != null) {
            callBack.setHotBean((FindHotBean) o);
        } else {
            Observable<FindHotBean> findHotBean =
                    RetrofitInstance.getApiInterface().getFindHotBean();
            findHotBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<FindHotBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(FindHotBean findHotBean) {
                            FileUtil.writeObjectToFile(findHotBean, file);
                            callBack.setHotBean(findHotBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println("请求错误");
                            callBack.setHotBean(null);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
