package com.cxp.mrrd.di.module;

import com.cxp.mrrd.presenter.RxPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * 文 件 名: RxModule
 * 创 建 人: CXP
 * 创建日期: 2017-05-23 16:12
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@Module
public class RxModule {

    @Provides
    public RxPresenter provideRxPresenter() {
        return new RxPresenter();
    }
}
