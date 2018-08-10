package com.tjbool.httpwww.wanandroid.app.config;

import android.content.Context;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/8/10.
 * 邮箱：123123@163.com
 */

public interface IRepositoryManager {

    /**
     * 根据传入的Class获取对应的Retrift service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Class<T> service);

    /**
     * 根据传入的Class获取对应的RxCache service
     *
     * @param cache
     * @param <T>
     * @return
     */
    <T> T obtainCacheService(Class<T> cache);

    /**
     * 清理所有缓存
     */
    void clearAllCache();

    Context getContext();
}
