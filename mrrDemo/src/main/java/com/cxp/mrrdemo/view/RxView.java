package com.cxp.mrrdemo.view;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 文 件 名: RxView
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 7:42
 * 描    述: 示例
 * 修 改 人:
 * 修改时：
 * 修改备注：
 */
public interface RxView extends MvpView {

    //请求数据
    //单文件
    MultipartBody.Part uploadFile();

    //多文件
    Map<String, RequestBody> uploadFiles();

    void getData(String data);
}
