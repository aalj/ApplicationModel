package net.lll0.view.convenientbanner.holder;

import android.view.View;

/**
 * @ClassName :  ViewHolderCreator 
 * @Description : 
 * @Author Sai
 * @Date 2014年11月30日 下午3:29:34
 *
 * 接口 ,交给谁使用谁去实例化 主要是创建 供给 HolderView 使用的 布局ID 和 对应的Holder
 */
public interface CBViewHolderCreator {

	Holder createHolder(View itemView);
	int getLayoutId();
}
