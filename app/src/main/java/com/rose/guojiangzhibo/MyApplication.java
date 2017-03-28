package com.rose.guojiangzhibo;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

import static android.view.View.X;

/**
 * Created by I on 2016/10/17.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    DbManager.DaoConfig daoConfig;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        x.Ext.init(this);
        //设置是否输出debug
        x.Ext.setDebug(true);
        setDaoConfig();
    }

    private void setDaoConfig() {
        daoConfig = new DbManager.DaoConfig().setDbVersion(1)
                .setDbName("my.db")
                .setDbDir(new File("/mysdcard"))
                .setDbOpenListener(new DbManager
                        .DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                }).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        //增删改
                    }
                });
    }

    //将全局的application传递出去
    public static MyApplication getMyApplication() {
        return myApplication;
    }

    //将数据库的参数传递出去
    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }


}
