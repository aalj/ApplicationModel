package net.lll0.image;

import android.graphics.drawable.Drawable;

public interface GlideLoadListener {

    public boolean onException();
    public boolean onResourceReady(Drawable resource);
}
