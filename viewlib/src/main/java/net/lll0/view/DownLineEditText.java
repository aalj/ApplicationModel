package net.lll0.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import net.lll0.utils.time.DimenUtils;
import net.lll0.viewlib.R;

/**
 * Created by liangjun on 2018/11/14
 */
public class DownLineEditText extends AppCompatEditText {
    private int mRight;
    private int mBottom;
    private int mTop;
    private int mLeft;

    Paint paint = null;

    public DownLineEditText(Context context) {
        super(context);
        initialize(context);
    }


    public DownLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public DownLineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.view_FF45AAF5));
//
//        android:textColor="@color/text_color_FF333333"
//        android:textColorHint="@color/common_text_color_02"

        setTextColor(getResources().getColor(R.color.view_FF333333));
        setHintTextColor(getResources().getColor(R.color.view_FFB8B8B8));
        setPadding(0, DimenUtils.getDimenInt(getContext(), R.dimen.base_dp_10), 0, DimenUtils.getDimenInt(getContext(), R.dimen.base_dp_10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获得控件可用用的宽度
        //获得控价的高度
        super.onDraw(canvas);
        if (paint != null) {
            canvas.drawLine(mLeft, mBottom, mRight, mBottom, paint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRight = getMeasuredWidth();
        mLeft = 0;
        mTop = 0;
        mBottom = getMeasuredHeight();
    }
}
