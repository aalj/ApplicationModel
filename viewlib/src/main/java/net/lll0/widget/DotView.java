package net.lll0.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import net.lll0.base.utils.log.MyLog;
import net.lll0.viewlib.R;

/**
 * Created by liangjun on 2018/11/19
 */
public class DotView extends View implements Runnable {


    enum STATUS {
        DEFAULT, MOVE, UP
    }

    private STATUS actionStatus = STATUS.DEFAULT;
    //圆的圆心 x 轴
    private int circleX = 0;
    //圆的圆心 y 轴
    private int circleY = 0;
    //圆的半径
    private int radius = 100;

    //画圆的笔
    private Paint paint = new Paint();

    private float degrees = 0;

    private Path path = null;


    private int widthSpec;
    private int heightSpec;
    //画笔的宽度
    private float mStrokeWid = 2F;

    private boolean isDrawCanTriangle = false;

    public DotView(Context context) {
        super(context);
        initialize(context, null);
    }


    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);

    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    public void setActionStatus(STATUS actionStatus) {
        this.actionStatus = actionStatus;
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);

    }

    private void initialize(Context context, AttributeSet attrs) {

        if (attrs != null) {

        }

        circleX = radius;
        circleY = radius;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.view_FF45AAF5));
        paint.setStrokeWidth(mStrokeWid);
        paint.setAntiAlias(true);

        path = new Path();
//        postDelayed(this, 1000);

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
        MyLog.e("是否进行绘制");
        canvas.rotate(degrees, circleX, circleY);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(circleX, circleY, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(circleX, circleY, radius / 3, paint);

        if (actionStatus.equals(STATUS.UP)) {
            drawTriangle(canvas);
        }
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }

    /**
     * 绘制三角形
     *
     * @param canvas
     */
    private void drawTriangle(Canvas canvas) {
        int h = 20;
        int a = radius / 4;
        path.moveTo(radius, a);

        path.lineTo(radius - h, a + h);
        path.lineTo(radius + h, a + h);
        path.close();
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
    }

    @Override
    public void run() {
        degrees = degrees + 1;
        if (degrees == 360) {
            degrees = 0;
        }
    }
}
