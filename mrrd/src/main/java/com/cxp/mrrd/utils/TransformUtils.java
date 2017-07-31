package com.cxp.mrrd.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 文 件 名: TransformUtils
 * 创 建 人: CXP
 * 创建日期: 2017-02-05 9:27
 * 描    述: Rx线程变换
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TransformUtils {

    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        };
    }
}
