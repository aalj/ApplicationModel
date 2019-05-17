package net.lll0.app.hook;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by liangjun on 2019/3/18
 */
class MockClass1 implements InvocationHandler {
    Object invocationHandler = null;


    public MockClass1(Object mInstance) {
        invocationHandler = mInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        if ("startActivity".equals(method.getName())) {
            Log.e("hook", "测试是否这项");
            return method.invoke(invocationHandler, args);
        }
        return method.invoke(invocationHandler, args);

    }
}
