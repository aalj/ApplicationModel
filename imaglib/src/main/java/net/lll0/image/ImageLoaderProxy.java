package net.lll0.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片加载代理类，所有的图片操作都通过该代理类去实现；
 * 如果要改变图片加载框架，只需要在该类里替换相应的图片加载框架即可，客户端所有引用的图片操作都不需要修改
 */
public class ImageLoaderProxy implements ImageLoader {
    private ImageLoader imageLoader;//代理对象
    private static ImageLoaderProxy imageLoaderProxy;

    public static ImageLoaderProxy getInstance() {
        if (imageLoaderProxy == null) {
            imageLoaderProxy = new ImageLoaderProxy();
        }

        return imageLoaderProxy;
    }

    private ImageLoaderProxy() {
        imageLoader = new GlideImageLoader();
    }


    @Override
    public void init(Context context) {
        imageLoader.init(context);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage) {
        imageLoader.displayImage(context, imageUrl, imageView, defaultImage);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView) {

        imageLoader.displayImage(context, imageUrl, imageView);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, GlideLoadListener listener) {

        imageLoader.displayImage(context, imageUrl, imageView,listener);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage) {
        imageLoader.displayImage(context, imageUrl, imageView, defaultImage, errorImage);

    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage, GlideLoadListener listener) {
        imageLoader.displayImage(context, imageUrl, imageView, defaultImage, errorImage,listener);

    }

    @Override
    public void displayCircleImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage) {
        imageLoader.displayCircleImage(context, imageUrl, imageView, defaultImage, errorImage);

    }

    @Override
    public void displayRoundImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage) {
        imageLoader.displayRoundImage(context, imageUrl, imageView, defaultImage, errorImage);

    }
    @Override
    public void displayCircleImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage, GlideLoadListener listener) {
        imageLoader.displayCircleImage(context, imageUrl, imageView, defaultImage, errorImage,listener);

    }

    @Override
    public void onDestroy(Context context) {
        imageLoader.onDestroy(context);
    }


    @Override
    public void resumeRequests(Context context) {
        imageLoader.resumeRequests(context);
    }


    @Override
    public void pauseRequests(Context context) {
        imageLoader.pauseRequests(context);
    }



}