package com.cxp.mrr.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.cxp.mrr.R;
import com.cxp.mrr.ui.widget.dialog.ShapeLoadingDialog;
import com.cxp.mrr.utils.AppUtils;
import com.cxp.mrr.utils.T;
import com.cxp.mrr.view.MvpView;
public class BaseActivity extends AppCompatActivity implements MvpView {

    public static final String TAG = "MRR";

    private ShapeLoadingDialog shapeLoadingDialog;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Activity
        MyApplication.addActivity(this);
    }

    //手机系统字体大小 改变 App不变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    //显示加载弹层
    protected void showDialog(Context context) {
        //检测网络
        if (AppUtils.isNoNet(context)) {
            return;
        }
        shapeLoadingDialog = new ShapeLoadingDialog(context);
        shapeLoadingDialog.show();
        //15秒自动关闭
        handler.postDelayed(run, 30000);
    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            if (shapeLoadingDialog.isShow()) {
                shapeLoadingDialog.dismiss();
                T.show(getApplicationContext(),  getString(R.string.net_error));
            }
        }
    };

    //隐藏加载弹层
    protected void dissDialog() {
        shapeLoadingDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除一个Activity
        MyApplication.removeActivity(this);
        if (handler != null) {
            handler.removeCallbacks(run);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked, int position) {

    }

    // 点击文本框外 自动隐藏软键盘
    //=========================================================================================================================================
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    //=========================================================================================================================================


}
