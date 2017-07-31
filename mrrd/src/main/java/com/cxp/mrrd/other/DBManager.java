package com.cxp.mrrd.other;

import android.content.Context;

import com.cxp.mrrd.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;


/**
 * 文 件 名: DBManager
 * 创 建 人: CXP
 * 创建日期: 2017-01-18 10:01
 * 描    述: 数据库管理
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class DBManager {

    private final static String dbName = "mrr_db";
    private static DBManager mInstance;
    private  DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper=new DaoMaster.DevOpenHelper(context,dbName,null);
    }

    /**
     * 获取单例引用
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context){
        if (mInstance==null) {
            synchronized (DBManager.class){
                if (mInstance==null) {
                    mInstance =new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    public Database getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        Database db = openHelper.getReadableDb();
        return db;
    }

    /**
     * 获取可写数据库
     */
    public  Database getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        Database db = openHelper.getWritableDb();
        return db;
    }

}
