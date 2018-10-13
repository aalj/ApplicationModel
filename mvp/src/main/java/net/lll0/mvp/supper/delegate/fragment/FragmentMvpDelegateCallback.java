package net.lll0.mvp.supper.delegate.fragment;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.delegate.MvpDelegateCallback;

/**
 * Created by liangjun on 2018/10/13
 * fragment 扩展接口
 */
public interface FragmentMvpDelegateCallback<V extends MvpView,P extends MvpPresenter<V>>  extends MvpDelegateCallback<V,P> {
}
