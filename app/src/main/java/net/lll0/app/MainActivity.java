package net.lll0.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.lll0.app.hook.AMSHookHelper;
import net.lll0.base.utils.RefInvoke;

import java.util.Map;

public class MainActivity extends Activity {

    private TextView mText;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            AMSHookHelper.hookAMN();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.text);
    }

    private void initView() {
//        Instrumentation instrumentation = (Instrumentation) RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
//        Instrumentation ins = new EvilInstrumentation(instrumentation);
//        RefInvoke.setFieldObject(Activity.class, this, "mInstrumentation", ins);
        startActivity(new Intent(this, KotlinMainActivity.class));
    }

    public void button(View view) {
        initView();
    }
}
