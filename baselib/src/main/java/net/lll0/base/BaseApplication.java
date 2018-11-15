package net.lll0.base;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

/**
 * Created by liangjun on 2018/11/13
 */
public class BaseApplication extends MultiDexApplication {
    public static  BaseApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }

}
