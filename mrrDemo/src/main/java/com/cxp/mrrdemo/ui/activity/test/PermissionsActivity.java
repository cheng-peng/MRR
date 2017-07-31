package com.cxp.mrrdemo.ui.activity.test;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cxp.mrrdemo.R;
import com.cxp.mrrdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: PermissionsActivity
 * 创 建 人: CXP
 * 创建日期: 2017-07-31 11:58
 * 描    述: 权限管理
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class PermissionsActivity extends BaseActivity {

    @BindView(R.id.permissions_status)
    TextView permissionsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_permissions);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.permissions_bt1, R.id.permissions_bt2, R.id.permissions_bt3, R.id.permissions_bt4, R.id.permissions_bt5, R.id.permissions_bt6, R.id.permissions_bt7, R.id.permissions_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.permissions_bt1:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        permissionsStatus.setText("成功获取到相机权限");
                    }
                }, 1, "用于拍照功能", Manifest.permission.CAMERA);
                break;
            case R.id.permissions_bt2:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        permissionsStatus.setText("成功获取到电话权限");
                    }
                }, 2, "用于电话功能", Manifest.permission.CALL_PHONE);
                break;
            case R.id.permissions_bt3:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        permissionsStatus.setText("成功获取到定位权限");
                    }
                }, 3, "用于定位功能", Manifest.permission.ACCESS_FINE_LOCATION);
                break;
            case R.id.permissions_bt4:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        permissionsStatus.setText("成功获取到存储权限");
                    }
                }, 4, "用于存储功能", Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.permissions_bt5:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        permissionsStatus.setText("成功获取到发短信权限");
                    }
                }, 5, "用于发短信功能", Manifest.permission.SEND_SMS);
                break;
            case R.id.permissions_bt6:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        permissionsStatus.setText("成功获取到录音权限");
                    }
                }, 6, "用于录音功能", Manifest.permission.RECORD_AUDIO);
                break;
            case R.id.permissions_bt7:
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        permissionsStatus.setText("成功获取到通讯录权限");
                    }
                }, 7, "用于通讯录功能", Manifest.permission.READ_CONTACTS);
                break;
            case R.id.permissions_all:
                checkPermission(new CheckPermListener() {
                                    @Override
                                    public void superPermission() {
                                        permissionsStatus.setText("成功获取全部权限");
                                    }
                                }, 10, "获取全部权限", Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SEND_SMS,
                        Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_CONTACTS);
                break;
        }
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PermissionsActivity.class);
        context.startActivity(intent);
    }
}
