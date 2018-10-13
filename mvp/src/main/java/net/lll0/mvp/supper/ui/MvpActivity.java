package net.lll0.mvp.supper.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.delegate.activity.ActivityMvpDelegate;
import net.lll0.mvp.supper.delegate.activity.ActivityMvpDelegateCallback;
import net.lll0.mvp.supper.delegate.activity.ActivityMvpDelegateImpl;

/**
 * Created by liangjun on 2018/10/13
 *
 * 该类作为 所有的Activity 的父类, 实际开发中需要继承该类在次进行封装
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity
        implements ActivityMvpDelegateCallback<V, P>, MvpView {

    private P presenter;
    private ActivityMvpDelegate<V, P> activityMvpDelegate;
    private boolean retainInstance;

    public ActivityMvpDelegate<V, P> getActivityMvpDelegate() {
        if (activityMvpDelegate == null) {
            activityMvpDelegate = new ActivityMvpDelegateImpl<>(this);
        }
        return this.activityMvpDelegate;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    public P getPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }
    public void onStart() {
        super.onStart();
        getActivityMvpDelegate().onStart();
    }


    public void onPause() {
        super.onPause();
        getActivityMvpDelegate().onPause();
    }

    public void onResume() {
        super.onResume();
        getActivityMvpDelegate().onResume();
    }


    public void onRestart() {
        super.onRestart();
        getActivityMvpDelegate().onRestart();
    }


    public void onStop() {
        super.onStop();
        getActivityMvpDelegate().onStop();
    }


    public void onDestroy() {
        super.onDestroy();
        getActivityMvpDelegate().onDestroy();
    }


    public void onContentChanged() {
        getActivityMvpDelegate().onContentChanged();
    }


    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getActivityMvpDelegate().onSaveInstanceState(outState);
    }


    public void onAttachedToWindow() {
        getActivityMvpDelegate().onAttachedToWindow();
    }


    public Object onRetainCustomNonConfigurationInstance() {
        return getActivityMvpDelegate().onRetainCustomNonConfigurationInstance();
    }


    public Object getLastCustomNonConfigurationInstance() {
        return super.getLastCustomNonConfigurationInstance();
    }

    @Override
    public Object getNonLastCustomNonConfigurationInstance() {
        return getActivityMvpDelegate().getLastCustomNonConfigurationInstance();
    }

    public boolean isRetainInstance() {
        return retainInstance;
    }

    public void setRetainInstance(boolean retainInstance) {
        this.retainInstance = retainInstance;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return this.retainInstance && isChangingConfigurations();
    }
}
