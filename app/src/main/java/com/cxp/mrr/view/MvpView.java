package com.cxp.mrr.view;

import android.view.View;
import android.widget.CompoundButton;

/**
 * 文 件 名: MvpView
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 5:15
 * 描    述: MVP
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface MvpView {

    //子项单击
    void onItemClick(View view, int position);

    //子项长按
    void onItemLongClick(View view, int position);

    //子项CheckeBox改变
    void onCheckedChanged(CompoundButton buttonView, boolean isChecked, int position);

}
