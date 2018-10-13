package net.lll0.mvp.base.presenter;

import net.lll0.mvp.base.view.MvpView;

/**
 * Created by liangjun on 2018/10/13
 */
public interface MvpPresenter<V extends MvpView> {
    void bingingView(V mvpView);
    void unbindingView();
}
