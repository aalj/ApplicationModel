package net.lll0.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import net.lll0.base.utils.RefInvoke;


/**
 * Created by liangjun on 2019/3/18
 */
public class EvilInstrumentation extends Instrumentation {
    Instrumentation base;

    public EvilInstrumentation(Instrumentation instrumentation) {
        base = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {

        Log.e("hook", "该方法执行没有");
        Class[] p1 = {Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class};
        Object[] v1 = {who, contextThread, token, target, intent, requestCode, options};
        return (ActivityResult) RefInvoke.invokeInstanceMethod(base, "execStartActivity", p1, v1);


    }

}
