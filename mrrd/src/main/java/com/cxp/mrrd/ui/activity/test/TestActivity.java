package com.cxp.mrrd.ui.activity.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.cxp.mrrd.R;
import com.cxp.mrrd.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: TestActivity
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 15:01
 * 描    述: 示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TestActivity extends BaseActivity {

    @BindView(R.id.bt_rx)
    Button btRx;
    @BindView(R.id.bt_user)
    Button btUser;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        context=TestActivity.this;
    }

    @OnClick({R.id.bt_rx, R.id.bt_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_rx:
                RxActivity.startActivity(context);
                break;
            case R.id.bt_user:
                UserActivity.startActivity(context);
                break;
        }
    }
}
