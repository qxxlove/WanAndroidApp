package com.tjbool.httpwww.wanandroid.dagger.module;

import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;
import com.tjbool.httpwww.wanandroid.mvp.contract.HomeContract;
import com.tjbool.httpwww.wanandroid.mvp.model.HomeModel;

import dagger.Module;
import dagger.Provides;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/8/10.
 * 邮箱：123123@163.com
 */

@Module
public class HomeModule  {

    private HomeContract.View view;

    /**
     *
     * @param view  传入View的实例
     */
    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HomeContract.View provideHomeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HomeContract.Model provideHomeModel(HomeModel model) {
        return model;
    }

}
