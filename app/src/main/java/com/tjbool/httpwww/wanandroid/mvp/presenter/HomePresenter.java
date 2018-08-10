package com.tjbool.httpwww.wanandroid.mvp.presenter;

import com.tjbool.httpwww.wanandroid.app.base.BasePresenter;
import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;
import com.tjbool.httpwww.wanandroid.mvp.contract.HomeContract;

import javax.inject.Inject;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model,HomeContract.View> {

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }
}
