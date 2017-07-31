package com.cxp.mrrdemo.presenter;


import com.cxp.mrrdemo.view.MvpView;

/**
 * 文 件 名: Presenter
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 7:37
 * 描    述: 绑定视图 解除绑定
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface Presenter<V extends MvpView>  {

    //绑定视图
    void attachView(V mvpView);

    //解除绑定
    void detachView();
}
