package net.lll0.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import net.lll0.base.utils.log.MyLog;
import net.lll0.viewlib.R;

import static android.graphics.Paint.Style.STROKE;

/**
 * Created by liangjun on 2019/1/16
 */
public class ChartView extends View {

    private int widthSpec;
    private int heightSpec;
    //圆的圆心 x 轴
    private int circleX = 0;
    //圆的圆心 y 轴
    private int circleY = 0;
    //圆的半径
    private int radius = 100;
    //画笔的宽度
    private float mStrokeWid = 2F;

    Paint paint;

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization(context, attrs, defStyleAttr);
    }

    private void initialization(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        heightSpec = MeasureSpec.getMode(heightMeasureSpec);


        widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        heightSpec = MeasureSpec.getSize(heightMeasureSpec);

        // 取长和宽中的小值
        widthSpec = widthSpec < heightSpec ? widthSpec : heightSpec;
        radius = circleX = circleY = widthSpec / 2;
        radius -= mStrokeWid / 2;

        setMeasuredDimension(widthSpec, widthSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int circlePoint = getWidth() / 2;
        //第一步:画背景(即内层圆)
        paint.setColor(getResources().getColor(R.color.view_FF4599F5)); //设置圆的颜色
        paint.setStyle(STROKE); //设置空心
        paint.setStrokeWidth(60); //设置圆的宽度
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(circlePoint, circlePoint, radius, paint); //画出圆

        MyLog.e("坐标点  ->  " + circlePoint);


    }
}
