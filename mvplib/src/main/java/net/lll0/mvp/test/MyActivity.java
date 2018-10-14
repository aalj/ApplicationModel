package net.lll0.mvp.test;

import android.support.v7.app.AppCompatActivity;

import net.lll0.mvp.base.view.MvpView;
import net.lll0.mvp.supper.ui.MvpActivity;
import net.lll0.mvp.test.mvp.MyPresenter;
import net.lll0.mvp.test.mvp.MyView;

/**
 * Created by liangjun on 2018/10/13
 */
public class MyActivity extends MvpActivity<MyView,MyPresenter> implements MyView {
    @Override
    public MyPresenter createPresenter() {
        return null;
    }


}
