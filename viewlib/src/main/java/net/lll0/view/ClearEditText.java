package net.lll0.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import net.lll0.viewlib.R;

/**
 * Created by liangjun on 2018/11/13
 */
public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, View.OnTouchListener, TextWatcher {

    private Drawable clearIcon;
    private OnFocusChangeListener focusChangeListener;
    private OnTouchListener touchListener;

    public ClearEditText(Context context) {
        super(context);
        initialize(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        //获取图片 放置到控件末尾
        Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.icon_input_del);
        clearIcon = drawable;
        clearIcon.setBounds(0, 0, clearIcon.getIntrinsicWidth(), clearIcon.getIntrinsicHeight());
        //初始化的时候不显示山按钮
        setClearVisible(false);
        //输入监听
        super.addTextChangedListener(this);
        //点击监听
        super.setOnTouchListener(this);
        //获取焦点
        super.setOnFocusChangeListener(this);


    }

    private void setClearVisible(boolean isVisible) {
        if (clearIcon.isVisible() == isVisible) {
            return;
        }
        clearIcon.setVisible(isVisible, false);
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0],
                drawables[1],
                isVisible ? clearIcon : null,
                drawables[3]);

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //获取已经输入额长度
        if (hasFocus && getText().toString().length() > 0) {
            setClearVisible(true);
        } else {
            setClearVisible(false);
        }
        if (focusChangeListener != null) {
            focusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //获取点击的位置的X轴
        int x = (int) event.getX();
        if (clearIcon.isVisible() && x > getWidth() - getPaddingRight() - clearIcon.getIntrinsicWidth()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                setText("");
            }
        }
        return touchListener != null && touchListener.onTouch(v, event);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        focusChangeListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        touchListener = l;
    }

    //------------------------------------输入建监听   start-----------------------------
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocused()) {
            setClearVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    //------------------------------------输入建监听  end-----------------------------

}
