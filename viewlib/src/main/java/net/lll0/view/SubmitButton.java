package net.lll0.view;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;

import net.lll0.base.utils.log.MyLog;
import net.lll0.viewlib.R;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

/**
 * Created by liangjun on 2018/11/14
 */
public class SubmitButton extends AppCompatButton {
    public SubmitButton(Context context) {
        super(context);
        initialize(context);
    }

    public SubmitButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public SubmitButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        setBackgroundResource(R.drawable.common_round_rectangle_bg_theme_gradient);
        setTextColor(getResources().getColor(R.color.view_white));
        setGravity(Gravity.CENTER);
        setTextSize(COMPLEX_UNIT_PX, getDimenInt(R.dimen.base_dp_20));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) getLayoutParams();
            p.setMargins(getDimenInt(R.dimen.base_dp_36), getDimenInt(R.dimen.base_dp_13), getDimenInt(R.dimen.base_dp_36), getDimenInt(R.dimen.base_dp_13));
            setLayoutParams(p);
            requestLayout();
        }
    }

    /**
     * 获取dp 对应的 数值 直接舍去小数
     *
     * @param id
     * @return
     */
    private int getDimenInt(@DimenRes int id) {
        return getResources().getDimensionPixelOffset(id);
    }

    public void setNotSubmit() {
        setEnabled(false);
        setBackgroundResource(R.drawable.common_round_rectangle_not_select_gradient);
//        postInvalidate();
    }

    public void setCanSubmit() {
        setEnabled(true);
        setBackgroundResource(R.drawable.common_round_rectangle_bg_theme_gradient);
//        postInvalidate();
    }
}
