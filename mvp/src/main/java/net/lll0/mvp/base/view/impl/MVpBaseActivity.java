package net.lll0.mvp.base.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;

/**
 * Created by liangjun on 2018/10/13
 */
public abstract class MVpBaseActivity<V extends MvpView, P extends MvpPresenter> extends AppCompatActivity {
    private V view;
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (view== null) {
            this.view = createView();
        }

        if (presenter==null) {
            this . presenter =   createPresenter();
        }
        if (view!=null&&presenter!=null) {
            presenter.bingingView(this.view);
        }
    }




    protected  abstract P createPresenter();
    protected  abstract V createView();
}
