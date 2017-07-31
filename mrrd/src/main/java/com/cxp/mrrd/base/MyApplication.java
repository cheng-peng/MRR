package com.cxp.mrrd.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: MyApplication
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 5:08
 * 描    述: 初始化数据
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends Application {

    private static Context appContext;
    private static List<Activity> activityList;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        activityList = new ArrayList<>();
        appContext = getApplicationContext();

        //谷歌调试工具
        Stetho.initializeWithDefaults(this);

    }

    //添加一个Activity
    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    //删除一个Activity
    public static void removeActivity(Activity activity) {
        if (activityList != null && activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    //关闭所有Activity
    public static void clearActivity() {
        if (activityList != null) {
            for (Activity activity : activityList) {
                activity.finish();
            }
            activityList = null;
        }
    }

    /**
     * 退出软件
     */
    private static long exitTime = 0;

    public static void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(appContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //退出应用
            clearActivity();
        }
    }

}
