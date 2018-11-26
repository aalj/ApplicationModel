package net.lll0.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import net.lll0.base.utils.log.MyLog;
import net.lll0.viewlib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangjun on 2018/11/20
 */
public class DotGroupView extends RelativeLayout implements Runnable {


    private DotView[] dotViews;
    private int mColumn = 4;
    private List<Integer> list = new ArrayList<>();

    private Path linePath;
    private Paint paint;

    private Point endPoint = new Point();
    private float endDotX = 0;
    private float endDotY = 0;
    /**
     * 表示每个子View和间隔与DotView半径的比例 该值 只能为小数
     * 同时也表示连线是表示触摸到圆点的系数
     */
    private float scale = 0.5F;

    private boolean isCanClear = true;

    private int childRadius;

    public DotGroupView(Context context) {
        super(context);
        init();
    }


    public DotGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        if (linePath == null) {
            linePath = new Path();
        }

        if (paint == null) {
            paint = new Paint();
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.view_FF45AAF5));
        paint.setStrokeWidth(2F);
        paint.setAntiAlias(true);
        setGravity(Gravity.CENTER);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        //每个子View的半径
        childRadius = (int) (mWidth / (2 * mColumn + scale * mColumn + scale));
        //每个子View 存在的更上下左右的宽度.
        int childMargins = (int) ((scale * childRadius) / 2);

        //计算实际控件的实际宽度

        int realWidth = mColumn * childMargins * 2 + childMargins * (mColumn + 1);

        int diffWidth = mWidth - realWidth;

        MyLog.e("实际宽度和 总宽度的 差额   " + diffWidth);

        if (dotViews == null) {
            dotViews = new DotView[mColumn * mColumn];

            for (int i = 0; i < mColumn * mColumn; i++) {
                DotView dotView = new DotView(getContext());
                dotView.setActionStatus(DotView.STATUS.DEFAULT);
                dotView.setId(i + 1);

                dotViews[i] = dotView;

                //设置参数，主要是定位GestureLockView间的位置
                RelativeLayout.LayoutParams lockerParams = new RelativeLayout.LayoutParams(childRadius * 2, childRadius * 2);
                // 不是每行的第一个，则设置位置为前一个的右边
                if (i % mColumn != 0) {
                    lockerParams.addRule(RelativeLayout.RIGHT_OF,
                            dotViews[i - 1].getId());
                }
                // 从第二行开始，设置为上一行同一位置View的下面
                if (i > mColumn - 1) {
                    lockerParams.addRule(RelativeLayout.BELOW,
                            dotViews[i - mColumn].getId());
                }

                int marginsLeft = childMargins / 2;
                int marginsTop = childMargins / 2;
                int marginsRight = childMargins / 2;
                int marginsBottom = childMargins / 2;

                //第一列
                if (i % mColumn == 0) {
                    marginsLeft = childMargins;
                }
                if (i / mColumn < 0) {
                    marginsTop = childMargins;
                }
                //最后一列
                if (i % mColumn == mColumn - 1) {
                    marginsRight = childMargins;
                }
                if (i / mColumn == mColumn - 1) {
                    marginsBottom = childMargins;
                }

                lockerParams.setMargins(marginsLeft, marginsTop, marginsRight, marginsBottom);
                addView(dotViews[i], lockerParams);
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (linePath != null && paint != null) {
            canvas.drawPath(linePath, paint);
        }
        if (list.size() > 0) {
            canvas.drawLine(endDotX, endDotY, endPoint.x, endPoint.y, paint);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                reset();
                break;
            case MotionEvent.ACTION_MOVE:
//                MyLog.e("手指在移动");
                float x = event.getX();
                float y = event.getY();
                DotView dotView = getDotView(x, y);
                if (dotView != null && !list.contains(dotView.getId())) {
                    dotView.setActionStatus(DotView.STATUS.MOVE);
                    int[] dotViewCenterCoordinate = getDotViewCenterCoordinate(dotView);
                    endDotX = dotViewCenterCoordinate[0];
                    endDotY = dotViewCenterCoordinate[1];
                    if (list.size() <= 0) {
                        linePath.moveTo(dotViewCenterCoordinate[0], dotViewCenterCoordinate[1]);
                    } else {
                        linePath.lineTo(dotViewCenterCoordinate[0], dotViewCenterCoordinate[1]);
                    }
                    list.add(dotView.getId());
                    setAngle();
                } else {

                    setAngleNormal(x, y);
                }

                endPoint.x = (int) x;
                endPoint.y = (int) y;
                break;

            case MotionEvent.ACTION_UP:
                if (list.size() < 4) {
                    reset();
                } else {
                    for (int i = 0; i < list.size() - 1; i++) {
                        DotView view = findViewById(list.get(i));
                        view.setActionStatus(DotView.STATUS.UP);
                        view.invalidate();
                    }

                    endPoint.x = (int) endDotX;
                    endPoint.y = (int) endDotY;
                    if (isCanClear) {
                        postDelayed(this, 2000);
                    }
                }
                break;
        }
        invalidate();
        return true;
    }

    private void setAngleNormal(float x, float y) {

    }

    private void setAngle() {
        if (list.size() > 1) {
            int startId = list.get(list.size() - 2);
            int endId = list.get(list.size() - 1);
            DotView startDotView = findViewById(startId);
            DotView endDotView = findViewById(endId);
            int dx = endDotView.getLeft() - startDotView.getLeft();
            int dy = endDotView.getTop() - startDotView.getTop();
            startDotView.setDegrees((float) (Math.toDegrees(Math.atan2(dy, dx)) + 90));
            startDotView.invalidate();
        }
    }

    public void setScale(float scale) {
        if (1 > scale) {
            throw new RuntimeException("比例系数必须小于1");
        }
        this.scale = scale;
    }

    private void reset() {
        for (DotView dotView : dotViews) {
            if (list.contains(dotView.getId())) {
                dotView.setActionStatus(DotView.STATUS.DEFAULT);
                dotView.setDegrees(0);
                dotView.invalidate();
            }
        }
        linePath.reset();
        list.clear();
    }

    private DotView getDotView(float x, float y) {
        for (DotView dotView : dotViews) {
            //这个判断是否在那个点里面
            if (checkPositionInChild(dotView, x, y)) {
                return dotView;
            }
        }
        return null;
    }

    /**
     * 获取点击到的DotView 的中心坐标
     *
     * @param dotView
     * @return
     */
    private int[] getDotViewCenterCoordinate(DotView dotView) {
        int l = dotView.getLeft();
        int r = dotView.getRight();
        int t = dotView.getTop();
        int b = dotView.getBottom();
        int x = (l + r) / 2;
        int y = (t + b) / 2;
        return new int[]{x, y};
    }

    /**
     * 判断 x,y 坐标是否在圆内
     *
     * @param dotView
     * @param x
     * @param y
     * @return
     */
    private boolean checkPositionInChild(DotView dotView, float x, float y) {
        int l = dotView.getLeft();
        int r = dotView.getRight();
        int t = dotView.getTop();
        int b = dotView.getBottom();
        if (x > l && x < r && y > t && y < b) {
            int x1 = (l + r) / 2;
            int y1 = (t + b) / 2;
            if (Math.sqrt(Math.pow((x1 - x), 2) + Math.pow((y - y1), 2)) < childRadius) {
                return true;
            }
        }
        return false;
    }

    public void setCanClear(boolean canClear) {
        isCanClear = canClear;
    }

    @Override
    public void run() {
        //时间到清除当前页面上存在的内容
        reset();
        invalidate();
    }
}
