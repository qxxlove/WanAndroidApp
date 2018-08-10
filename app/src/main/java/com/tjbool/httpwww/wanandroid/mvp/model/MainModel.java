package com.tjbool.httpwww.wanandroid.mvp.model;

import com.tjbool.httpwww.wanandroid.app.base.BaseModel;
import com.tjbool.httpwww.wanandroid.app.config.IRepositoryManager;
import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;
import com.tjbool.httpwww.wanandroid.mvp.contract.MainContract;

import javax.inject.Inject;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {


    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }
}
