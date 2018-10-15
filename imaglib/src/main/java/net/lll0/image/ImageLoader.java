package net.lll0.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * 图片加载器功能接口；
 * 添加或者替换新的图片加载器实现该接口即可
 */
public interface ImageLoader {
    /**
     * Init ImageLoader
     */
    void init(Context context);

    /**
     * Show Image
     *
     * @param imageUrl
     * @param imageView
     * @param defaultImage
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage);

    /**
     * Show Image
     *
     * @param imageUrl
     * @param imageView
     */
    void displayImage(Context context, String imageUrl, ImageView imageView);

    /**
     * 加载图片返回加载的状态
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param listener  回调接口
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, GlideLoadListener listener);

    /**
     * @param imageUrl
     * @param imageView
     * @param defaultImage 占位图
     * @param errorImage   错误图
     */
    void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage);
    void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage, GlideLoadListener listener);


    /**
     * 显示圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param defaultImage 默认图
     * @param errorImage   错误图
     */
    void displayCircleImage(Context context, String url, ImageView imageView, @DrawableRes int defaultImage, int errorImage);

    /**
     * 显示圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param defaultImage 默认图
     * @param errorImage   错误图  v m
     */
    void displayRoundImage(Context context, String url, ImageView imageView, @DrawableRes int defaultImage, int errorImage);
    void displayCircleImage(Context context, String url, ImageView imageView, @DrawableRes int defaultImage, int errorImage, GlideLoadListener listener);


    /**
     * 销毁内容
     *
     * @param context
     */
    void onDestroy(Context context);

    /**
     * 重新加载
     *
     * @param context
     */
    void resumeRequests(Context context);

    /**
     * 暂定加载
     *
     * @param context
     */
    void pauseRequests(Context context);

}