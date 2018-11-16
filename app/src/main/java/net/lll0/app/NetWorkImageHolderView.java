package net.lll0.app;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;


import net.lll0.image.ImageLoaderProxy;
import net.lll0.widget.convenientbanner.holder.Holder;


/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetWorkImageHolderView extends Holder<String> {
    ImageView ivPost;
    private Activity mActivity;


    public NetWorkImageHolderView(Activity activity, View itemView) {
        super(itemView);
        mActivity = activity;
    }

    @Override
    protected void initView(View itemView) {
        ivPost = itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(String data) {
        ImageLoaderProxy.getInstance().displayImage(mActivity, data, ivPost);
    }
}
