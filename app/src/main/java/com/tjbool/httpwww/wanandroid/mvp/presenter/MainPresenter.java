package com.tjbool.httpwww.wanandroid.mvp.presenter;

import com.tjbool.httpwww.wanandroid.app.base.BasePresenter;
import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;
import com.tjbool.httpwww.wanandroid.mvp.contract.MainContract;

import javax.inject.Inject;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View> {

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }
}
