package com.cxp.mrrd.di.component;

import com.cxp.mrrd.di.module.RxModule;
import com.cxp.mrrd.ui.activity.test.RxActivity;

import dagger.Component;

/**
 * 文 件 名: RxComponent
 * 创 建 人: CXP
 * 创建日期: 2017-05-23 16:15
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@Component(modules = {RxModule.class})
public interface RxComponent {
    void inject(RxActivity activity);
}
