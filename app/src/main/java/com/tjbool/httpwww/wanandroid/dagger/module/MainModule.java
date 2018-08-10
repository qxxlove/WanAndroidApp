package com.tjbool.httpwww.wanandroid.dagger.module;

import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;
import com.tjbool.httpwww.wanandroid.mvp.contract.MainContract;
import com.tjbool.httpwww.wanandroid.mvp.model.MainModel;

import dagger.Module;
import dagger.Provides;

/** 
 * description: 
 * autour: TMM
 * date: 2018/7/30 11:17 
 * update: 2018/7/30
 * version: 
*/

@Module
public class MainModule {

    private MainContract.View view;

    /**
     *  构建MainModule时,将View的实现类传进来,
     *  这样就可以提供View的实现类给presenter
     * @param view
     */
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainContract.Model provideMainModel(MainModel model) {
        return model;
    }
}
