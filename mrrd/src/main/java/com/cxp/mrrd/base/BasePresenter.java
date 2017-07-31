package com.cxp.mrrd.base;

import com.cxp.mrrd.presenter.Presenter;
import com.cxp.mrrd.view.MvpView;

/**
 * 文 件 名: BasePresenter
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 7:38
 * 描    述: Presenter 父类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class BasePresenter <T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    //绑定视图
    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    //解除绑定
    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached(){
        return mMvpView!=null;
    }

    public T getMvpView(){
        return mMvpView;
    }

    public  void checkViewAttached(){
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
