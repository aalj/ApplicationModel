package net.lll0.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import net.lll0.viewlib.R;

/**
 * Created by liangjun on 2018/11/13
 * 按钮倒计时 控件
 */
public class SendSmsButton extends AppCompatTextView implements Runnable, View.OnClickListener {
    private String defaultText = "发送验证码";
    private String sendText = "%s秒后重试";

    private boolean isSendSuccess = false;

    private int defaultTextColor = Color.argb(255, 69, 170, 245);
    private int sendTextColor = Color.argb(255, 194, 194, 194);

    View.OnClickListener onClickListener;
    private int countDown = 60;
    private int mCountDown = countDown;

    public SendSmsButton(Context context) {
        super(context);
        initialize(context, null);
    }

    public SendSmsButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public SendSmsButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        String defaultTextStr = "";
        String sendTextStr = "";
        if (attrs != null) {
            //获取自定义属性。
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SmsButText);
            //获取字体大小,默认大小是16dp
            defaultTextStr = ta.getString(R.styleable.SmsButText_default_text);
            //获取文字内容
            sendTextStr = ta.getString(R.styleable.SmsButText_send_text);
            //获取文字颜色，默认颜色是BLUE
            defaultTextColor = ta.getColor(R.styleable.SmsButText_default_text_color, defaultTextColor);
            sendTextColor = ta.getColor(R.styleable.SmsButText_send_text_color, sendTextColor);
            countDown = ta.getInt(R.styleable.SmsButText_countdown, countDown);
            ta.recycle();
        }
        if (!TextUtils.isEmpty(defaultTextStr)) {
            defaultText = defaultTextStr;
        }
        if (!TextUtils.isEmpty(sendTextStr)) {
            sendText = sendTextStr;
        }
        if (!isSendSuccess) {
            setText(defaultText);
            setTextColor(defaultTextColor);
        }

        super.setOnClickListener(this);
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        onClickListener = l;
    }

    public void restart() {
        mCountDown = countDown;
        restartView();
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        if (!TextUtils.isEmpty(defaultText)) {
            this.defaultText = defaultText;
            setText(defaultText);
        }
    }

    public void setSendText(String sendText) {
        if (sendText.contains("%s")) {
            throw new RuntimeException("发送成功的文字确实模板关键字 %s");
        }
        this.sendText = sendText;
        if (!TextUtils.isEmpty(sendText)) {
            setText(sendText);
        }
    }


    public void setDefaultTextColor(@ColorInt int defaultTextColor) {
        this.defaultTextColor = defaultTextColor;
        setTextColor(defaultTextColor);

    }


    public void setSendTextColor(int sendTextColor) {
        this.sendTextColor = sendTextColor;
    }

    @Override
    public void run() {
        Log.e("SendSmsButton", "线程执行");
        mCountDown--;

        if (mCountDown > 0) {
            String text = String.format(sendText, mCountDown);
            setText(text);
            setTextColor(sendTextColor);
            postDelayed(this, 1000);
        } else {
            restartView();

        }
    }

    private void restartView() {
        removeCallbacks(this);
        setEnabled(true);
        setText(defaultText);
        setTextColor(defaultTextColor);
        mCountDown = countDown;
    }


    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
    }

    public void startCountDown() {
        setEnabled(false);
        mCountDown = countDown;
        post(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(this);
        super.onDetachedFromWindow();

    }

    public interface Action {

    }
}
