package net.lll0.mvp.test.mvp;

import android.content.Context;

import net.lll0.mvp.base.presenter.impl.MvpPresenterImpl;

/**
 * Created by liangjun on 2018/10/13
 */
public class MyPresenter extends MvpPresenterImpl<MyView> {
    public MyPresenter(Context context) {
        super(context);
    }
}
