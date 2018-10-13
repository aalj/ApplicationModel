package net.lll0.mvp.supper.delegate.activity;

import net.lll0.mvp.base.presenter.MvpPresenter;
import net.lll0.mvp.base.view.MvpView;

/**
 * Created by liangjun on 2018/10/13
 * 该类主要的作用是用户在缓存页面生命周期发生变化是临时保存数据
 */
public class ActivityMvpConfigurationInstance<V extends MvpView,P extends MvpPresenter<V>> {
    private P presenter;
    private Object customizeConfigurationInstance;

    public ActivityMvpConfigurationInstance(P presenter, Object customizeConfigurationInstance) {
        super();
        this.presenter = presenter;
        this.customizeConfigurationInstance = customizeConfigurationInstance;
    }

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public  P getPresenter(){
        return presenter;

    }

    public Object getCustomizeConfigurationInstance() {
        return customizeConfigurationInstance;
    }

    public void setCustomizeConfigurationInstance(Object customizeConfigurationInstance) {
        this.customizeConfigurationInstance = customizeConfigurationInstance;
    }
}
