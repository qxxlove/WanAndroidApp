package com.tjbool.httpwww.wanandroid;

import android.app.Application;

import com.tjbool.httpwww.wanandroid.dagger.component.ApplicationComponent;
import com.tjbool.httpwww.wanandroid.dagger.component.DaggerApplicationComponent;
import com.tjbool.httpwww.wanandroid.dagger.module.ApplicationModule;
import com.tjbool.httpwww.wanandroid.dagger.module.HttpModule;

/** 
 * description: 入口
 * autour: TMM
 * date: 2018/7/24 16:04 
 * update: 2018/7/24
 * version: 
*/
public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    private static MyApplication sMyApp;




    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();


    }

    public static MyApplication getInstance() {
        return sMyApp;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
