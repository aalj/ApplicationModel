package net.lll0.base.ui;

import android.os.Build;
import android.os.Bundle;

import net.lll0.base.R;
import net.lll0.mvp.base.presenter.impl.MvpPresenterImpl;
import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.ui.MvpActivity;


/**
 * Created by liangjun on 2018/10/13
 */
public abstract class BaseMvpActivity<V extends MvpView,P extends MvpPresenterImpl<V>>  extends MvpActivity<V,P> {
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



    /**
     * 获取布局的id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图控件
     */
    protected abstract void initView();

    /**
     * 获取权限
     */
    protected void initPermission() {
    }


    /**
     * 初始化方法 处于初始化视图和初始化数据中间
     */
    protected void init() {
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
