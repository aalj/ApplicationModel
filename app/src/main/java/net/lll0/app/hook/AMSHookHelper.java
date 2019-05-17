package net.lll0.app.hook;

import net.lll0.base.utils.RefInvoke;

import java.lang.reflect.Proxy;

/**
 * Created by liangjun on 2019/3/18
 */
public class AMSHookHelper {
    public static void hookAMN() throws Exception {
//        Object getDefault = RefInvoke.getStaticFieldObject("android.app.ActivityManagerNative", "getDefault");
//        Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", getDefault, "mInstance");
//        Class<?> classB2Interface = Class.forName("android.app.IActivityManager");
//        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
//                new Class<?>[]{classB2Interface},
//                new MockClass1(mInstance));
//
//
//        RefInvoke.setFieldObject("android.util.Singleton",getDefault,"mInstance",proxy);
        //获取AMN的gDefault单例gDefault，gDefault是final静态的
        Object gDefault = RefInvoke.getStaticFieldObject("android.app.ActivityManagerNative", "gDefault");

        // gDefault是一个 android.util.Singleton<T>对象; 我们取出这个单例里面的mInstance字段
        Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", gDefault, "mInstance");

        // 创建一个这个对象的代理对象MockClass1, 然后替换这个字段, 让我们的代理对象帮忙干活
        Class<?> classB2Interface = Class.forName("android.app.IActivityManager");
        Object proxy = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class<?>[] { classB2Interface },
                new MockClass1(mInstance));

        //把gDefault的mInstance字段，修改为proxy
        RefInvoke.setFieldObject("android.util.Singleton", gDefault, "mInstance", proxy);
    }
}
