package com.cxp.mrr.base;

import rx.Subscriber;

/**
 * 文 件 名: BaseSubscriber
 * 创 建 人: CXP
 * 创建日期: 2017-02-05 9:46
 * 描    述: Rx返回信息封装
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    protected abstract void onFailure(Throwable e);

    protected abstract void onSuccess(T t);
}
