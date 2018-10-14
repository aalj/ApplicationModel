package net.lll0.mvp.supper.delegate;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;

/**
 * Created by liangjun on 2018/10/13
 */
public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V getMvpView();


    /**
     * 判断是否需要保存数据(该方法还会处理横竖屏切换)
     * @return
     */
    boolean shouldInstanceBeRetained();


}
