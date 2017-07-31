package com.cxp.mrrdemo.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.cxp.mrrdemo.R;
import com.cxp.mrrdemo.model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文 件 名: AppUtils
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 5:15
 * 描    述: 常用方法工具类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class AppUtils {

    //下载链接
    public static String DOWNLOAD_APK = "";
    //APK名字
    public static String APK_NAME = "";

    private static AppUtils mInstance;
    private Context context;

    public AppUtils(Context context) {
        this.context = context;
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static AppUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AppUtils.class) {
                if (mInstance == null) {
                    mInstance = new AppUtils(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 判断App在前台还是后台
     *
     * @param context
     * @return
     */
    public boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                    /*
                    BACKGROUND=400 EMPTY=500 FOREGROUND=100
                    GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                     */
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(context.getPackageName(), "处于后台"
                            + appProcess.processName);
                    return true;
                } else {
                    Log.i(context.getPackageName(), "处于前台"
                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 保存本地对象
     *
     * @param user
     * @throws IOException
     */
    public void saveAppUser(User user) throws IOException {

        //保存在sd卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录

            File sdFile = new File(sdCardDir, "/mrr/");
            if (!sdFile.exists()) {
                sdFile.mkdirs();
            }
            File file = new File(sdCardDir, "/mrr/user.out");
            if (!file.exists()) {
                file.createNewFile();
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(user);// 写入
                oos.close();
                fos.close(); // 关闭输出流
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Toast.makeText(WebviewTencentActivity.this, "成功保存到sd卡", Toast.LENGTH_LONG).show();

        }
    }

    /**
     * 获取本地对象
     *
     * @return
     */
    public User readAppUser() {
        User user = null;
        File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
        File sdFile = new File(sdCardDir, "/mrr/");
        if (!sdFile.exists()) {
            sdFile.mkdirs();
        }
        File file = new File(sdCardDir, "/mrr/user.out");

        try {
            FileInputStream fis = new FileInputStream(file);   //获得输入流
            ObjectInputStream ois = new ObjectInputStream(fis);
            user = (User) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
     * 解决TextView换行问题（符号不能开头）
     */
    public String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 处理json  Map<Integer, List<Map<Integer, String>>> mapList
     *
     * @param mapList
     * @return
     */
    public String mapListMapToJson(Map<Integer, List<Map<Integer, String>>> mapList) {
        Gson gson = new Gson();
        Map<Integer, String> map = new HashMap<>();
        for (Map.Entry<Integer, List<Map<Integer, String>>> integerListEntry : mapList.entrySet()) {
            map.put(integerListEntry.getKey(), gson.toJson(integerListEntry.getValue()));
        }

        String json = gson.toJson(map);
        json = json.replace("\"[", "[");
        json = json.replace("]\"", "]");
        json = json.replace("\\", "");
        return json;
    }

    /**
     * List下Map排序
     *
     * @param list
     */
    public void listMapOrderBy(List<Map<String, Object>> list) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                if (o1.get("score") != null && o2.get("score") != null) {
                    String score1 = o1.get("score").toString();
                    String score2 = o2.get("score").toString();
                    return score1.compareTo(score2);
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * 最大显示几行后面带省略号
     *
     * @param textView
     */
    public void showTextLine(final TextView textView, final int num) {
        ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = textView.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                //判断行数大于多少时改变   此时最大显示2行
                if (num > 0) {
                    if (textView.getLineCount() > num - 1) {
                        int lineEndIndex = textView.getLayout().getLineEnd(num - 1); //设置第六行打省略号
                        String text = textView.getText().subSequence(0, lineEndIndex - 3) + "...";
                        textView.setText(text);
                    }
                }
            }
        });
    }

    /**
     * 判断字符串是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        boolean flag = false;
        if (object != null && object.toString().trim().length() != 0) {
            flag = true;
        }
        return flag;
    }

    //网络判断
    public static boolean isNoNet(Context context) {
        //网络检查
        if (!NetUtils.isConnected(context)) {
            T.show(context, context.getString(R.string.net_error));
            return true;
        }
        return false;
    }

    /**
     * 生成唯一标识
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
