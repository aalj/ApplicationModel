package net.lll0.widget.square;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by liangjun on 2018/11/16
 */
public class SquareLinearLayout extends LinearLayout {
    public SquareLinearLayout(Context context) {
        super(context);
    }

    public SquareLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(SquareDelegate.measureWidth(widthMeasureSpec, heightMeasureSpec),
                SquareDelegate.measureHeight(widthMeasureSpec, heightMeasureSpec));
    }
}
