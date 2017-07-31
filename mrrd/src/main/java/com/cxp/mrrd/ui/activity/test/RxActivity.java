package com.cxp.mrrd.ui.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cxp.mrrd.R;
import com.cxp.mrrd.base.BaseActivity;
import com.cxp.mrrd.di.component.DaggerRxComponent;
import com.cxp.mrrd.di.module.RxModule;
import com.cxp.mrrd.lib.download.UpdateManager;
import com.cxp.mrrd.presenter.RxPresenter;
import com.cxp.mrrd.utils.AppUtils;
import com.cxp.mrrd.view.RxView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 文 件 名: RxActivity
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 15:03
 * 描    述: 网络请求 示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RxActivity extends BaseActivity implements RxView {

    @BindView(R.id.bt_click)
    Button btClick;
    @BindView(R.id.bt_click1)
    Button btClick1;
    @BindView(R.id.bt_click2)
    Button btClick2;
    @BindView(R.id.bt_click4)
    Button btClick4;
    @BindView(R.id.bt_click3)
    Button btClick3;
    @BindView(R.id.bt_click5)
    Button btClick5;
    @BindView(R.id.bt_click6)
    Button btClick6;
    @BindView(R.id.bt_click7)
    Button btClick7;
    @BindView(R.id.bt_click8)
    Button btClick8;

    @Inject
    RxPresenter mRxPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);

        //dagger绑定 好处是以后改构造 不需要改别的页面，更好的解耦
        DaggerRxComponent.builder()
                .rxModule(new RxModule())
                .build()
                .inject(this);

        //绑定视图
        mRxPresenter.attachView(this);

    }


    @OnClick({R.id.bt_click, R.id.bt_click1, R.id.bt_click2, R.id.bt_click4, R.id.bt_click3, R.id.bt_click5, R.id.bt_click6, R.id.bt_click7, R.id.bt_click8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_click:
                //普通请求
                loadGetListUsers();
                break;
            case R.id.bt_click1:
                //占位符请求
                loadGetUserQuery();
                break;
            case R.id.bt_click2:
                //@Query 传参请求
                loadGetUserQuerys();
                break;
            case R.id.bt_click3:
                //@Filed 表单传递
                loadFiledQuery();
                break;
            case R.id.bt_click4:
                //@QueryMap 传递多个参数
                loadGetUserQueryMap();
                break;
            case R.id.bt_click5:
                //@FiledMap 表单多个参数出传递
                loadFiledQueryMap();
                break;
            case R.id.bt_click6:
                //@Multipart 单文件上传
                loadFileUpload();
                break;
            case R.id.bt_click7:
                //@MultipartMap 多文件上传
                loadFileUploadMap();
                break;
            case R.id.bt_click8:
                //下载地址
                String path="https://cxp521.github.io/cxp.github.io/app-release.apk";
                AppUtils.DOWNLOAD_APK = path.substring(0, path.lastIndexOf("/") + 1);
                AppUtils.APK_NAME = path.substring(path.lastIndexOf("/") + 1);
                //检测版本更新
                new UpdateManager(this).checkUpdate(2, "    1. 修正一些bug\\n\" + \"    2. 更新一些功能\\n\" + \"    ");
                break;
        }
    }

    //普通请求
    private void loadGetListUsers() {
        mRxPresenter.loadGetListUsers();
    }

    //占位符请求
    private void loadGetUserQuery() {
        mRxPresenter.loadGetUserQuery();
    }

    //@Query 传参请求
    private void loadGetUserQuerys() {
        mRxPresenter.loadGetUserQuerys();
    }

    //@Filed 传递对象
    private void loadFiledQuery() {
        mRxPresenter.loadFiledQuery();
    }

    //@QueryMap 传递多个参数
    private void loadGetUserQueryMap() {
        mRxPresenter.loadGetUserQueryMap();
    }

    //@FiledMap 表单多个参数出传递
    private void loadFiledQueryMap() {
        mRxPresenter.loadFiledQueryMap();
    }

    //@Multipart 单文件上传
    private void loadFileUpload() {
        mRxPresenter.loadFileUpload();
    }

    //@Multipart 多文件上传
    private void loadFileUploadMap() {
        mRxPresenter.loadFileUploadMap();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRxPresenter != null) {
            //取消订阅
            mRxPresenter.unSubscribe();
            //解除绑定
            mRxPresenter.detachView();
        }
    }

    //单文件上传
    @Override
    public MultipartBody.Part uploadFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), new File(Environment.getExternalStorageDirectory(), "cxp_logo.png"));
        MultipartBody.Part photo = MultipartBody.Part.createFormData("uploadFile", sdf.format(new Date()) + "_CXP.png", photoRequestBody);
        return photo;
    }


    //多文件上传
    @Override
    public Map<String, RequestBody> uploadFiles() {
        Map<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put("" + i + "\"; filename=\"" + "IMG" + i + ".png", RequestBody.create(MediaType.parse("image/png"), new File(Environment.getExternalStorageDirectory(), "cxp_logo.png")));
        }
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy_MM_dd");
//        map.put("uploadFile\"; filename=\"" + "cxp_icon_03.png", RequestBody.create(MediaType.parse("image/png"), new File(Environment.getExternalStorageDirectory(), "CXP/cxp_icon_03.png")));
//        map.put("uploadFile1\"; filename=\"" + "cxp_icon_04.png", RequestBody.create(MediaType.parse("image/png"), new File(Environment.getExternalStorageDirectory(), "CXP/cxp_icon_04.png")));
//        map.put("uploadFile2\"; filename=\"" + "cxp_icon_05.png", RequestBody.create(MediaType.parse("image/png"), new File(Environment.getExternalStorageDirectory(), "CXP/cxp_icon_05.png")));
        return map;
    }

    @Override
    public void getData(String data) {
        Toast.makeText(RxActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RxActivity.class);
        context.startActivity(intent);
    }
}
