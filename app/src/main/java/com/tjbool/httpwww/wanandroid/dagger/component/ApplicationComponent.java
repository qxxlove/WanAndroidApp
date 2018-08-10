package com.tjbool.httpwww.wanandroid.dagger.component;

import android.content.Context;

import com.tjbool.httpwww.wanandroid.MyApplication;
import com.tjbool.httpwww.wanandroid.dagger.module.ApplicationModule;
import com.tjbool.httpwww.wanandroid.dagger.module.HttpModule;
import com.tjbool.httpwww.wanandroid.mvp.NewsApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * desc: .
 * author: Will .
 * date: 2017/9/2 .
 */
@Singleton
@Component(modules = {ApplicationModule.class,HttpModule.class})
public interface ApplicationComponent {

    MyApplication getApplication();

    NewsApi getNetEaseApi();
    
    Context getContext();
    
    /**用于管理网络请求层,以及数据缓存层*/
    //IRepositoryManager repositoryManager();
   
}
