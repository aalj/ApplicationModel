package net.lll0.app.base;

import android.os.Bundle;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.presenter.impl.MvpPresenterImpl;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.base.view.impl.MVpBaseActivity;
import net.lll0.mvp.supper.ui.MvpActivity;

/**
 * Created by liangjun on 2018/10/13
 */
public abstract class BaseActivity<V extends MvpView,P extends MvpPresenterImpl<V>>  extends MvpActivity<V,P>  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
