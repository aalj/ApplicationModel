package net.lll0.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import net.lll0.base.BaseApplication;
import net.lll0.base.utils.log.MyLog;

/**
 * Created by liangjun on 2018/11/14
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.init(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
