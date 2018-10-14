package net.lll0.mvp.supper.delegate;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;

/**
 * Created by liangjun on 2018/10/13
 */
public class MvpDelegateCallbackProxy<V extends MvpView, P extends MvpPresenter<V>> implements MvpDelegateCallback<V, P> {

    private MvpDelegateCallback<V, P> mvpDelegateCallback;

    public MvpDelegateCallbackProxy(MvpDelegateCallback<V, P> mvpDelegateCallback) {
        this.mvpDelegateCallback = mvpDelegateCallback;
    }

    @Override
    public P createPresenter() {
        P presenter = mvpDelegateCallback.getPresenter();
        if (presenter == null) {
            presenter = mvpDelegateCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter is not null ");
        }

        mvpDelegateCallback.setPresenter(presenter);
        return getPresenter();

    }

    @Override
    public P getPresenter() {

        P presenter = mvpDelegateCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("presenter is not null ");

        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mvpDelegateCallback.setPresenter(presenter);
    }

    @Override
    public V getMvpView() {
        //接口此处会调用实现类的方法,
        return mvpDelegateCallback.getMvpView();
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return false;
    }


    public void bindingView(){
        getPresenter().bingingView(getMvpView());
    }
    public void unbindingView(){
        getPresenter().unbindingView();
    }
}
