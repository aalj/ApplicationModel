package net.lll0.mvp.supper.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.delegate.fragment.FragmentMvpDelegate;
import net.lll0.mvp.supper.delegate.fragment.FragmentMvpDelegateCallback;
import net.lll0.mvp.supper.delegate.fragment.FragmentMvpDelegateImpl;

/**
 * Created by liangjun on 2018/10/13
 */
public abstract class MvpFragment <V extends MvpView,P extends MvpPresenter<V>> extends Fragment
        implements FragmentMvpDelegateCallback<V,P>, MvpView {

    private P presenter;

    private FragmentMvpDelegate<V,P> fragmentMvpDelegate = null;



    public FragmentMvpDelegate<V, P> getFragmentMvpDelegate() {
        if (this.fragmentMvpDelegate==null) {
            this.fragmentMvpDelegate = new FragmentMvpDelegateImpl<V, P>(this) ;
        }
        return this.fragmentMvpDelegate;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentMvpDelegate().onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFragmentMvpDelegate().onActivityCreated(savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        getFragmentMvpDelegate().onViewCreated(view,savedInstanceState);
    }

    public void onStart() {
        super.onStart();
        getFragmentMvpDelegate().onStart();
    }

    public void onPause() {
        super.onPause();
        getFragmentMvpDelegate().onPause();
    }

    public void onResume() {
        super.onResume();
        getFragmentMvpDelegate().onResume();
    }

    public void onStop() {
        super.onStop();
        getFragmentMvpDelegate().onStart();
    }

    public void onDestroyView() {
        super.onDestroyView();
        getFragmentMvpDelegate().onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        getFragmentMvpDelegate().onDestroy();
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        getFragmentMvpDelegate().onSaveInstanceState(outState);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        getFragmentMvpDelegate().onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
        getFragmentMvpDelegate().onDetach();
    }
}
