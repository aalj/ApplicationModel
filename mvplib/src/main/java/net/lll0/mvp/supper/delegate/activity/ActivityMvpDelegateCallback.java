package net.lll0.mvp.supper.delegate.activity;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.delegate.MvpDelegateCallback;

/**
 * Created by liangjun on 2018/10/13
 */
public interface ActivityMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> extends MvpDelegateCallback<V, P> {
    /**
     * 与横竖屏切换 获取保存数据方法名字相同 在该处也是为了代理该方法存在
     *
     * @return
     */
    Object getLastCustomNonConfigurationInstance();

    /**
     * 与横竖屏切换 保存数据方法名字相同 在该处也是为了代理该方法存在
     *
     * @return
     */
    Object onRetainCustomNonConfigurationInstance();

    /**
     * 该方法表示的就是扩展代理扩展的方法
     *
     * @return
     */
    Object getNonLastCustomNonConfigurationInstance();
}
