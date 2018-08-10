package com.tjbool.httpwww.wanandroid.dagger.component;


import com.tjbool.httpwww.wanandroid.dagger.module.ApplicationModule;
import com.tjbool.httpwww.wanandroid.dagger.module.MainModule;
import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;
import com.tjbool.httpwww.wanandroid.mvp.ui.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = {ApplicationModule.class,MainModule.class})
public interface MainComponent {
    
    void inject(MainActivity activity);
}