package net.lll0.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lll0.base.utils.DensityUtils;
import net.lll0.viewlib.R;


/**
 * 所有titlebar都用这个，布局自己定，根布局relative不要改
 */
public class TitleBar extends RelativeLayout {

    private ImageView leftImgView;//左边返回按钮图片
    private TextView leftText;//左边返回按钮文字
    private TextView mTvTitle, mRightTv;

    private ImageButton mRightButton00;
    private ImageButton mRightButton;

    private LinearLayout mLeftLayout;
    private Context context;

    private RelativeLayout mRootLayout;
    private boolean isShowTitleText = false;

    Drawable background = null;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.layout_white_titlebar, this);
        leftImgView = (ImageView) findViewById(R.id.tv_fctitlebar_left_img);
        leftText = (TextView) findViewById(R.id.tv_fctitlebar_left);
        mTvTitle = (TextView) findViewById(R.id.tv_fctitlebar_title);
        mRightButton00 = (ImageButton) findViewById(R.id.ib_fctitlebar_right_00);
        mRightButton = (ImageButton) findViewById(R.id.ib_fctitlebar_right);
        mLeftLayout = (LinearLayout) findViewById(R.id.ll_fctitlebar_left);
        mRootLayout = (RelativeLayout) findViewById(R.id.rl_titlebar_root);
        mRightTv = (TextView) findViewById(R.id.tv_fctitlebar_right);
        background = mRootLayout.getBackground();
        mRootLayout.setBackgroundColor(getResources().getColor(R.color.view_white));
    }

    /**
     * 设置左边部分是否可见
     *
     * @param visibility
     */
    public void setLeftSideVisible(int visibility) {
        mLeftLayout.setVisibility(visibility);
    }

    /**
     * 隐藏左边的
     */
    public void hideLeftSide() {
        mLeftLayout.setVisibility(View.GONE);
    }

    /**
     * 设置标题
     *
     * @param text
     * @return
     */
    public TitleBar setTitle(String text) {
        mTvTitle.setText(text);
        return this;
    }

    public TitleBar setTitleTextButton(String text, OnClickListener listener) {
        mTvTitle.setText(text);
        mTvTitle.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置标记旁边图片
     *
     * @param drawable
     */
    public void setCompoundDrawable(int drawable) {
        Drawable drawDown = getResources().getDrawable(drawable);
        drawDown.setBounds(0, 0, drawDown.getMinimumWidth(), drawDown.getIntrinsicHeight());
        mTvTitle.setCompoundDrawables(null, null, drawDown, null);
        mTvTitle.setCompoundDrawablePadding(DensityUtils.dip2px(getContext(), 10));
    }

    /**
     * 设置titlebar的背景色
     *
     * @param color
     */
    public void setTitleBarBgColor(int color) {
        mRootLayout.setBackgroundColor(color);
    }

    /**
     * 设置titlebar的背景色
     *
     * @param
     */
    public void setTitleBarBgRes(int resId) {
        mRootLayout.setBackgroundResource(resId);
    }

    /**
     * 设置右边第一个图片按钮
     *
     * @param resId
     * @param listener
     * @return
     */
    public TitleBar setRightButton00(int resId, OnClickListener listener) {
        mRightButton00.setImageResource(resId);
        mRightButton00.setBackgroundColor(Color.TRANSPARENT);
        mRightButton00.setOnClickListener(listener);
        mRightButton00.setVisibility(View.VISIBLE);
        return this;
    }

    //返回标题栏扫描入口的view
    public View getRightButton00() {
        return mRightButton00;
    }

    /**
     * 设置右边第一个图片按钮隐藏和显示
     *
     * @param ishow
     * @return
     */
    public TitleBar isShowRightButton00(boolean ishow) {
        if (ishow) {
            mRightButton00.setVisibility(VISIBLE);
        } else {
            mRightButton00.setVisibility(GONE);
        }
        return this;
    }

    /**
     * 右边的图片按钮
     *
     * @param resId
     * @param listener
     * @return
     */
    public TitleBar setRightButton(int resId, OnClickListener listener) {
        mRightButton.setImageResource(resId);
        mRightButton.setBackgroundColor(Color.TRANSPARENT);
        mRightButton.setOnClickListener(listener);
        mRightButton.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右边图片按钮图片
     *
     * @param resId
     * @return
     */
    public TitleBar setRightButtonImg(int resId) {
        mRightButton.setImageResource(resId);
        mRightButton.setBackgroundColor(Color.TRANSPARENT);
        mRightButton.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 右边的文字按钮按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public TitleBar setRightTextButton(String text, OnClickListener listener) {
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText(text);
        mRightTv.setOnClickListener(listener);
        return this;
    }

    public void setmRightTvSize(int size) {
//        mRightTv.setTextSize(size);
    }

    /**
     * 右边的文字按钮按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public TitleBar setRightTextButton(String text, int color, OnClickListener listener) {
        if (null != mRightTv) {
            mRightTv.setTextColor(color);
            setRightTextButton(text, listener);
        }
        return this;
    }

    /**
     * 右边的文字按钮按钮
     *
     * @param text
     * @param
     * @param listener
     * @return
     */
    public TitleBar setRightTextAndBgButton(String text, int bgResId, OnClickListener listener) {
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText(text);
        mRightTv.setBackgroundResource(bgResId);
        mRightTv.setOnClickListener(listener);
        return this;
    }

    /**
     * 右边的文字按钮按钮
     *
     * @param text
     * @param
     * @param listener
     * @return
     */
    public TitleBar setRightTextAndBgButton(String text, OnClickListener listener) {
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText(text);
        mRightTv.setOnClickListener(listener);
        return this;
    }

    /**
     * 右边的文字按钮按钮
     *
     * @param text
     * @param
     * @param listener
     * @return
     */
    public TitleBar setLeftTextAndBgButton(String text, int bgResId, OnClickListener listener) {

        leftImgView.setVisibility(View.GONE);
        leftText.setText(text);
        leftText.setVisibility(View.VISIBLE);
        leftText.setBackgroundResource(bgResId);
        mLeftLayout.setOnClickListener(listener);
        return this;
    }

    public void hideRightTextButton() {
        mRightTv.setVisibility(View.GONE);
    }

    public void showRightTextButton() {
        mRightTv.setVisibility(View.VISIBLE);
    }


    /**
     * 设置左边返回点击finish的activity
     *
     * @param aty
     * @return
     */
    public TitleBar setLeftClickFinish(final Activity aty) {
        mLeftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (View.VISIBLE == leftImgView.getVisibility()) {
                    aty.finish();
                }
            }
        });
        return this;
    }

    /**
     * 设置右边按钮的文字
     *
     * @param text
     * @param
     * @return
     */
    public TitleBar setRightText(String text) {
        mRightTv.setText(text);
        return this;
    }


    /**
     * 设置左边返回点击finish的activity
     *
     * @param
     * @return
     */
    public TitleBar setLeftClickListener(OnClickListener l) {
        mLeftLayout.setOnClickListener(l);
        return this;
    }

    /**
     * 设置标题
     *
     * @param text
     * @return
     */
    public TitleBar setTitle(String text, int color) {
        mTvTitle.setText(text);
        mTvTitle.setTextColor(getResources().getColor(color));
        return this;
    }

    /**
     * 设置左边文字
     *
     * @param text
     * @return
     */
    public TitleBar setLeftText(String text) {
        leftImgView.setVisibility(GONE);
        leftText.setVisibility(VISIBLE);
        leftText.setText(text);
        return this;
    }

    /**
     * 隐藏左边内容
     *
     * @return
     */
    public TitleBar setHideLeftText() {
        leftImgView.setVisibility(GONE);
        leftText.setVisibility(GONE);
        return this;
    }


    /**
     * 设置返回图片 文字不显示
     */
    public TitleBar setLeftImagNotText() {
        leftImgView.setVisibility(VISIBLE);
        leftText.setVisibility(GONE);

        return this;
    }


    /**
     * 设置左边文字旁边图片
     *
     * @param drawable
     */
    public void setLeftTextCompoundDrawable(int drawable) {
        Drawable drawDown = getResources().getDrawable(drawable);
        drawDown.setBounds(0, 0, drawDown.getMinimumWidth(), drawDown.getIntrinsicHeight());
        leftText.setCompoundDrawables(null, null, drawDown, null);
        leftText.setCompoundDrawablePadding(DensityUtils.dip2px(getContext(), 5));
    }

}
