package net.lll0.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.ui.MvpFragment;


/**
 * Created by liangjun on 2018/10/13
 */
public abstract class BaseMvpFragment<V extends MvpView,P extends MvpPresenter<V>> extends MvpFragment {
    //我们自己的Fragment需要缓存视图


    private boolean isInit;
    protected View mRootView;
    protected LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (needReUsingView()) {
            mInflater = inflater;
            if (mRootView == null) {
                mRootView = inflater.inflate(getLayoutId(), null);
                initView(mRootView);
            }
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
            return mRootView;
        } else {
            mRootView = inflater.inflate(getLayoutId(), null);
            initView(mRootView);

            return mRootView;
        }
    }




    public final <T extends View> T findViewById(int id) {
        if (mRootView == null) {
            return null;
        }
        try {
            return (T) mRootView.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    public final <T extends View> T findViewById(View parentView, int id) {
        if (parentView == null) {
            return null;
        }
        try {
            return (T) parentView.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit){
            this.isInit = true;
            initData();
        }
    }

    public abstract int getContentView();

    public abstract void getLayoutId(View contentView);
    /**
     * 不需要复用可重写
     *
     * @return
     */
    protected boolean needReUsingView() {
        return true;
    }

    /**
     * 获取当前fragment对应layout的id
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

}
