package com.cxp.mrr.presenter;

import android.util.Log;

import com.cxp.mrr.api.RxApi;
import com.cxp.mrr.api.RxClient;
import com.cxp.mrr.base.BasePresenter;
import com.cxp.mrr.base.BaseSubscriber;
import com.cxp.mrr.model.ResponseModel;
import com.cxp.mrr.utils.ConstantUtils;
import com.cxp.mrr.utils.TransformUtils;
import com.cxp.mrr.view.RxView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 文 件 名: RxPresenter
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 7:41
 * 描    述: 逻辑处理 示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RxPresenter extends BasePresenter<RxView> {

    //新建订阅
    public CompositeSubscription cs ;

    @Override
    public void attachView(RxView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Inject
    public RxPresenter() {
        cs = new CompositeSubscription();
    }

    //普通请求
    public void loadGetListUsers() {
        Subscription subscription = RxClient.createApi(RxApi.class)
                .getListUsers()
                .compose(TransformUtils.<ResponseModel>defaultSchedulers())
                .subscribe(new BaseSubscriber<ResponseModel>() {

                    @Override
                    protected void onFailure(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    protected void onSuccess(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
                    }
                });
        cs.add(subscription);
    }

    //占位符请求
    public void loadGetUserQuery() {
        Subscription subscription = RxClient.createApi(RxApi.class)
                .getUserQuery("getUserNameGet.jhtml")
                .compose(TransformUtils.<ResponseModel>defaultSchedulers())
                .subscribe(new BaseSubscriber<ResponseModel>() {

                    @Override
                    protected void onFailure(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    protected void onSuccess(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
                    }
                });
        cs.add(subscription);
    }

    //@Query 传参请求
    public void loadGetUserQuerys() {
        Subscription subscription = RxClient.createApi(RxApi.class)
                .getUserQuerys("CXP")
                .compose(TransformUtils.<ResponseModel>defaultSchedulers())
                .subscribe(new BaseSubscriber<ResponseModel>() {

                    @Override
                    protected void onFailure(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    protected void onSuccess(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
                    }
                });
        cs.add(subscription);
    }

    //@Filed 表单查询
    public void loadFiledQuery() {
        Subscription subscription = RxClient.createApi(RxApi.class)
                .filedQuery("程小鹏")
                .compose(TransformUtils.<ResponseModel>defaultSchedulers())
                .subscribe(new BaseSubscriber<ResponseModel>() {

                    @Override
                    protected void onFailure(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    protected void onSuccess(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
                    }
                });
        cs.add(subscription);
    }

    //@FiledMap 表单多个参数查询
    public void loadFiledQueryMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "程小鹏");
        map.put("title", "哈哈。");
        map.put("pwd", "123456asdfadsf");
        Subscription subscription = RxClient.createApi(RxApi.class)
                .filedQueryMap(map)
                .compose(TransformUtils.<ResponseModel>defaultSchedulers())
                .subscribe(new BaseSubscriber<ResponseModel>() {

                    @Override
                    protected void onFailure(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    protected void onSuccess(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
                    }
                });
        cs.add(subscription);
    }

    //@QueryMap 传递多个参数
    public void loadGetUserQueryMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "CXP");
        map.put("title", "666666");
        Subscription subscription = RxClient.createApi(RxApi.class)
                .getUserQueryMap(map)
                .compose(TransformUtils.<ResponseModel>defaultSchedulers())
                .subscribe(new BaseSubscriber<ResponseModel>() {

                    @Override
                    protected void onFailure(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    protected void onSuccess(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
                    }
                });
        cs.add(subscription);
    }

    //单文件上传
    public void loadFileUpload() {
        Subscription subscription = RxClient.createApi(RxApi.class)
                .uploadFile(getMvpView().uploadFile(), "CXP")
                .compose(TransformUtils.<String>defaultSchedulers())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            Log.e(ConstantUtils.TAG_SUCCESS, s);
                            getMvpView().getData(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        cs.add(subscription);
    }

    //多文件上传
    public void loadFileUploadMap() {
        Subscription subscription = RxClient.createApi(RxApi.class)
                .uploadFiles(getMvpView().uploadFiles(), "CXP")
                .compose(TransformUtils.<String>defaultSchedulers())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(ConstantUtils.TAG_SUCCESS, s);
                        getMvpView().getData(s);
                    }
                });
        cs.add(subscription);
    }

    //取消订阅
    public void unSubscribe() {
        if (!cs.isUnsubscribed()) {
            cs.unsubscribe();
        }
    }
}
