package net.lll0.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.text);
        initView();
    }

    private void initView() {
        TelephonyManager telephonyManager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        String deviceId = "";
        deviceId = telephonyManager.getDeviceId();
        Log.e("设备号", deviceId);
        mText.setText(deviceId);
    }


    public void button(View view) {
        initView();
    }
}
