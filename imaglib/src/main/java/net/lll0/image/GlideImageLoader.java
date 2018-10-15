package net.lll0.image;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;

import net.lll0.image.transform.GlideCircleTransform;
import net.lll0.image.transform.GlideRoundTransform;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GlideImageLoader implements ImageLoader {
    private Context mContext = null;
    private GlideRequests glideRequests;

    @Override
    public void init(Context context) {
        this.mContext = context;

        glideRequests =
                GlideApp.with(context);
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                glideRequests =
                        GlideApp.with((FragmentActivity) context);
            } else if (context instanceof Activity) {
                glideRequests =
                        GlideApp.with((Activity) context);
            } else if (context instanceof ContextWrapper) {
                glideRequests =
                        GlideApp.with(((ContextWrapper) context).getBaseContext());
            }
        }

    }


    @Override
    public void displayImage(Context context, String uri, final ImageView img, int default_pic) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        init(context);
        glideRequests.load(uri)
                .placeholder(default_pic) //占位符 也就是加载中的图片，可放个gif
                .transition(withCrossFade())
                .into(img);
    }

    @Override
    public void displayImage(Context context, String imageUrl, final ImageView imageView) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        init(context);
        glideRequests
                .load(imageUrl)
                .transition(withCrossFade())
                .into(imageView);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, final GlideLoadListener listener) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        init(context);
        glideRequests
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onException();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (listener != null) {
                            listener.onResourceReady(resource);
                        }
                        return false;
                    }
                })
                .transition(withCrossFade())
                .into(imageView);
    }

    @Override
    public void displayImage(Context context, String imageUrl, final ImageView imageView, int defaultImage, int errorImage) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        init(context);
        glideRequests.load(imageUrl)
                .placeholder(defaultImage) //占位符 也就是加载中的图片，可放个gif
                .error(errorImage) //失败图片
                .transition(withCrossFade())
                .into(imageView);
    }

    @Override
    public void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage, final GlideLoadListener listener) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        init(context);
        glideRequests.load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onException();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (listener != null) {
                            listener.onResourceReady(resource);
                        }
                        return false;
                    }
                })
                .placeholder(defaultImage) //占位符 也就是加载中的图片，可放个gif
                .error(errorImage) //失败图片
                .transition(withCrossFade())
                .into(imageView);
    }


    @Override
    public void displayCircleImage(Context context, String url, final ImageView imageView, int defaultImage, int errorImage) {
        if (null == glideRequests) {
            init(context);
        }
        finishGlide();
        glideRequests.load(url)
                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    @Override
    public void displayRoundImage(Context context, String url, final ImageView imageView, int defaultImage, int errorImage) {
        if (null == glideRequests) {
            init(context);
        }
        finishGlide();
        glideRequests.load(url)
                .centerCrop()
                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new GlideRoundTransform(context))
                .into(imageView);
    }

    @Override
    public void displayCircleImage(Context context, String url, final ImageView imageView, int defaultImage, int errorImage, final GlideLoadListener listener) {
        if (null == glideRequests) {
            init(context);
        }
        finishGlide();
        glideRequests.load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onException();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (listener != null) {
                            listener.onResourceReady(resource);
                        }
                        return false;
                    }
                })
                .centerCrop()

                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }


    @Override
    public void onDestroy(Context context) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if (glideRequests != null) {
            glideRequests.pauseRequests();
        }
    }

    @Override
    public void resumeRequests(Context context) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if (glideRequests != null) {
            glideRequests.resumeRequests();
        }
    }

    @Override
    public void pauseRequests(Context context) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if (glideRequests != null) {
            glideRequests.pauseRequests();
        }
    }

    private void finishGlide() {
        if (glideRequests != null) {
            glideRequests.resumeRequests();
        }
    }

}