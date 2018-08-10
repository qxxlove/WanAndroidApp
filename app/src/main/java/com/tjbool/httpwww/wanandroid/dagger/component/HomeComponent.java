package com.tjbool.httpwww.wanandroid.dagger.component;

import com.tjbool.httpwww.wanandroid.dagger.module.HomeModule;
import com.tjbool.httpwww.wanandroid.mvp.ui.fragemnt.HomeFramgent;

import dagger.Component;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/8/10.
 * 邮箱：123123@163.com
 */


@Component(modules = HomeModule.class, dependencies =ApplicationComponent.class )
public interface HomeComponent  {

    void  inject (HomeFramgent homeFramgent);
}
