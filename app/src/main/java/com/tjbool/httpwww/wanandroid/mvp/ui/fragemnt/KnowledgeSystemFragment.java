package com.tjbool.httpwww.wanandroid.mvp.ui.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tjbool.httpwww.wanandroid.R;
import com.tjbool.httpwww.wanandroid.app.base.BaseFragment;
import com.tjbool.httpwww.wanandroid.dagger.component.ApplicationComponent;
import com.tjbool.httpwww.wanandroid.mvp.contract.KnowledgeContract;
import com.tjbool.httpwww.wanandroid.mvp.model.api.Constants;
import com.tjbool.httpwww.wanandroid.mvp.presenter.KnowledgePresenter;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

public class KnowledgeSystemFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    public static KnowledgeSystemFragment getInstance(String param1, String param2) {
        KnowledgeSystemFragment fragment = new KnowledgeSystemFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    
    @Override
    public int getContentLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, @Nullable Bundle savedInstanceState) {
          showSuccess();
    }
}
