package net.lll0.base;

import android.app.Application;

/**
 * Created by liangjun on 2018/11/13
 */
public class BaseApplication extends Application {
    public static  BaseApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }

}
