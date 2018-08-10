package com.tjbool.httpwww.wanandroid.mvp.ui.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tjbool.httpwww.wanandroid.R;
import com.tjbool.httpwww.wanandroid.app.base.BaseFragment;
import com.tjbool.httpwww.wanandroid.dagger.component.ApplicationComponent;
import com.tjbool.httpwww.wanandroid.mvp.contract.HomeContract;
import com.tjbool.httpwww.wanandroid.mvp.model.api.Constants;
import com.tjbool.httpwww.wanandroid.mvp.model.bean.home.HomeDataBean;
import com.tjbool.httpwww.wanandroid.mvp.presenter.HomePresenter;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

public class HomeFramgent  extends BaseFragment<HomePresenter>  implements HomeContract.View {

    public static HomeFramgent getInstance(boolean param1, String param2) {
        HomeFramgent fragment = new HomeFramgent();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentLayout() {
        return  R.layout.fragment_home;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }


    @Override
    public void bindView(View view, @Nullable Bundle savedInstanceState) {
        showSuccess();
    }


    @Override
    public void startOnRefresh() {
        
    }

    @Override
    public void startOnLoadMore() {

    }

    @Override
    public void endOnLoadMore() {

    }

    @Override
    public void setAdapter(List<HomeDataBean.DataBean.DatasBean> entity) {

    }
}
