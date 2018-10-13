package net.lll0.mvp.supper.delegate.activity;

import android.os.Bundle;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;

/**
 * Created by liangjun on 2018/10/13
 */
public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {
    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onPause();

    public void onResume();

    public void onRestart();

    public void onStop();

    public void onDestroy();

    public void onContentChanged();

    public void onSaveInstanceState(Bundle outState);

    public void onAttachedToWindow();

    public Object onRetainCustomNonConfigurationInstance();

    public Object getLastCustomNonConfigurationInstance();
}
