package net.lll0.mvp.supper.delegate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.delegate.MvpDelegateCallbackProxy;

/**
 * Created by liangjun on 2018/10/13
 */
public   class FragmentMvpDelegateImpl<V extends MvpView,P extends MvpPresenter<V>> implements FragmentMvpDelegate<V,P> {

    private MvpDelegateCallbackProxy<V,P> mvpDelegateCallbackProxy;
    private  FragmentMvpDelegateCallback<V,P> fragmentMvpDelegateCallback;

    public FragmentMvpDelegateImpl(FragmentMvpDelegateCallback<V, P> fragmentMvpDelegateCallback) {
        if (fragmentMvpDelegateCallback==null) {
            throw new NullPointerException("FragmentMvpDelegateCallback is not null!");

        }
        this.fragmentMvpDelegateCallback = fragmentMvpDelegateCallback;
    }

    public MvpDelegateCallbackProxy<V, P> getMvpDelegateCallbackProxy() {
        if (mvpDelegateCallbackProxy==null) {
            mvpDelegateCallbackProxy  = new MvpDelegateCallbackProxy<V,P>(fragmentMvpDelegateCallback);
        }
        return mvpDelegateCallbackProxy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getMvpDelegateCallbackProxy().createPresenter();
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
    public void onStop() {

    }

    @Override
    public void onDestroyView() {
        getMvpDelegateCallbackProxy().unbindingView();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onDetach() {

    }
}
