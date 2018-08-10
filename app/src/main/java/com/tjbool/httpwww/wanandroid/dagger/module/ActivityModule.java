package com.tjbool.httpwww.wanandroid.dagger.module;

import android.app.Activity;

import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


/**
 * @author quchao
 * @date 2017/11/27
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return mActivity;
    }

}
