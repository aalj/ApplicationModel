package net.lll0.app.base;

import android.os.Build;
import android.os.Bundle;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.presenter.impl.MvpPresenterImpl;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.base.view.impl.MVpBaseActivity;
import net.lll0.mvp.supper.ui.MvpActivity;

/**
 * Created by liangjun on 2018/10/13
 */
public abstract class BaseMvpActivity<V extends MvpView,P extends MvpPresenterImpl<V>>  extends MvpActivity<V,P>  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.base_colorPrimaryDark), 0);
        setContentView(getLayoutId());
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initPermission();
        }
        init();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    /**
     * 获取权限
     */
    protected void initPermission() {
    }

    ;

    protected void init() {
    }

    protected abstract void initData();

}
