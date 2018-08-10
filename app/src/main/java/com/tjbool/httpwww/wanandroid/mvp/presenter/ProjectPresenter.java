package com.tjbool.httpwww.wanandroid.mvp.presenter;

import com.tjbool.httpwww.wanandroid.app.base.BasePresenter;
import com.tjbool.httpwww.wanandroid.dagger.scope.ActivityScope;
import com.tjbool.httpwww.wanandroid.mvp.contract.ProjectContract;

import javax.inject.Inject;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

@ActivityScope
public class ProjectPresenter extends BasePresenter<ProjectContract.Model,ProjectContract.View> {

    @Inject
    public ProjectPresenter(ProjectContract.Model model, ProjectContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }
}
