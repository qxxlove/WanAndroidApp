package com.tjbool.httpwww.wanandroid.app.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.tjbool.httpwww.wanandroid.app.config.IRepositoryManager;
import com.tjbool.httpwww.wanandroid.app.impl.IModel;

/**
 * description: BaseModel
 * autour: TMM
 * date: 2018/7/24 16:08 
 * update: 2018/7/24
 * version: 
*/

public class BaseModel implements IModel, LifecycleObserver {

    protected IRepositoryManager mRepositoryManager;//用于管理网络请求层,以及数据缓存层

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }


    /**
     * 这个onDestroy 是跟随Activity.onDestroy 生命周期 一起销毁
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }

    /**
     *此时 onDestroy 由 Presenter 触发
     */
    @Override
    public void onDestory() {
         mRepositoryManager = null;
    }
}
