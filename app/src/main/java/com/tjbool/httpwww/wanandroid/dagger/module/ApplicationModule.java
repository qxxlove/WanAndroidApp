package com.tjbool.httpwww.wanandroid.dagger.module;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.tjbool.httpwww.wanandroid.MyApplication;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */
@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Singleton
    @Provides
    MyApplication provideApplication() {
        return (MyApplication) mContext.getApplicationContext();
    }
    @Singleton
    @Provides
    Context provideContext() {
        return mContext;
    }

  /*  @Singleton
    @Provides
    public IRepositoryManager provideRepositoryManager(RepositoryManager repositoryManager) {
        return repositoryManager;
    }*/

    @Singleton
    @Provides
    public Map<String, Object> provideExtras(){
        return new ArrayMap<>();
    }

}

