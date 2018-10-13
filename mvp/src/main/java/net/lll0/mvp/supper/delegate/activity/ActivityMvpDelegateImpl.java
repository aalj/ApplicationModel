package net.lll0.mvp.supper.delegate.activity;

import android.os.Bundle;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.delegate.MvpDelegateCallbackProxy;

/**
 * Created by liangjun on 2018/10/13
 * 静态代理 代理类是Activity
 */
public class ActivityMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        implements ActivityMvpDelegate<V, P> {

    private MvpDelegateCallbackProxy<V, P> mvpDelegateCallbackProxy;
    private ActivityMvpDelegateCallback<V, P> activityMvpDelegateCallback;

    public ActivityMvpDelegateImpl(ActivityMvpDelegateCallback<V, P> activityMvpDelegateCallback) {
        if (activityMvpDelegateCallback == null) {
            throw new NullPointerException("ActivityMvpDelegateCallback is not null!");

        }
        this.activityMvpDelegateCallback = activityMvpDelegateCallback;
    }

    private MvpDelegateCallbackProxy<V, P> getMvpDelegateCallbackProxy() {
        if (this.mvpDelegateCallbackProxy == null) {
            this.mvpDelegateCallbackProxy = new MvpDelegateCallbackProxy<>(activityMvpDelegateCallback);
        }
        return mvpDelegateCallbackProxy;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //判断当前是否存在缓存数据
        //存在则使用缓存的内容
        //不存在则创建新的 presenter

        //此处实质上是获取系统保存的内容 但是保存的内容还是自己处理的数据  就是 ActivityMvpConfigurationInstance 对象保存的内容
        Object instance = activityMvpDelegateCallback.getLastCustomNonConfigurationInstance();
        if (instance != null && instance instanceof ActivityMvpConfigurationInstance) {
            ActivityMvpConfigurationInstance<V, P> configurationInstance = (ActivityMvpConfigurationInstance<V, P>) instance;
            if (configurationInstance != null) {
                getMvpDelegateCallbackProxy().setPresenter(configurationInstance.getPresenter());
            } else {
                getMvpDelegateCallbackProxy().createPresenter();
            }
        } else {
            getMvpDelegateCallbackProxy().createPresenter();
        }

        getMvpDelegateCallbackProxy().bindingView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        getMvpDelegateCallbackProxy().unbindingView();
    }

    @Override
    public void onContentChanged() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        boolean retained = activityMvpDelegateCallback.shouldInstanceBeRetained();
        P presenter = retained ? activityMvpDelegateCallback.getPresenter() : null;
        Object o = activityMvpDelegateCallback.onRetainCustomNonConfigurationInstance();
        if (presenter == null && o == null) {
            return null;
        }
        return new ActivityMvpConfigurationInstance<V, P>(presenter, o);
    }

    @Override
    public Object getLastCustomNonConfigurationInstance() {

        Object instance = activityMvpDelegateCallback.getLastCustomNonConfigurationInstance();
        if (instance != null && instance instanceof ActivityMvpConfigurationInstance) {
            ActivityMvpConfigurationInstance<V, P> configurationInstance = (ActivityMvpConfigurationInstance<V, P>) instance;
            return configurationInstance;
        }
        return null;
    }
}
