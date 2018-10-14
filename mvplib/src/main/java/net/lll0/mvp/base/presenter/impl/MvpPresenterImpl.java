package net.lll0.mvp.base.presenter.impl;

import android.content.Context;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liangjun on 2018/10/13
 * 主要的作用就是进行视图的绑定和解绑
 * 采用动态绑定 为了解决视图的销毁问题
 */
public class MvpPresenterImpl<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<Context> contextWeakReference;
    private WeakReference<V> vWeakReference;
    private V view;

    public MvpPresenterImpl(Context context) {
        this.contextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public void bingingView(V mvpView) {
        //采用动态代理管理

        vWeakReference = new WeakReference<>(mvpView);
        MvpInvocationHandler mvpInvocationHandler = new MvpInvocationHandler(this.vWeakReference.get());
        view = (V) Proxy.newProxyInstance(mvpView.getClass().getClassLoader(), mvpView.getClass().getInterfaces(), mvpInvocationHandler);
    }

    @Override
    public void unbindingView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
        }
    }

    public V getView() {
        return view;
    }

    ;

    /**
     * 当前是否已经绑定
     *
     * @return true 表示已经绑定
     * false 表示没有绑定
     */
    public boolean isBindingView() {
        if (vWeakReference != null && vWeakReference.get() != null) {
            return true;
        }
        return false;

    }


    private class MvpInvocationHandler implements InvocationHandler {
        private MvpView mvpView;

        public MvpInvocationHandler(MvpView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            if (isBindingView()) {
                return method.invoke(mvpView, objects);
            }

            return null;
        }
    }


}
