package com.tjbool.httpwww.wanandroid.app.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tjbool.httpwww.wanandroid.MyApplication;
import com.tjbool.httpwww.wanandroid.R;
import com.tjbool.httpwww.wanandroid.app.impl.IPresenter;
import com.tjbool.httpwww.wanandroid.app.impl.IView;
import com.tjbool.httpwww.wanandroid.app.rxlifecycle.FragmentLifecycleable;
import com.tjbool.httpwww.wanandroid.app.utils.T;
import com.tjbool.httpwww.wanandroid.dagger.component.ApplicationComponent;
import com.tjbool.httpwww.wanandroid.mvp.ui.custom.DialogHelper;
import com.tjbool.httpwww.wanandroid.mvp.ui.custom.MultiStateView;
import com.tjbool.httpwww.wanandroid.mvp.ui.custom.SimpleMultiStateView;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * description: BaseFragment
 * autour: TMM
 * date: 2018/7/24 16:08 
 * update: 2018/7/24
 * version: 
*/
public abstract class BaseFragment<P extends IPresenter>  extends Fragment implements IView,FragmentLifecycleable {

    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    protected Context mContext;
    protected View mRootView;
    protected Dialog mLoadingDialog = null;
    Unbinder unbinder;

    @Nullable
    @Inject
    protected P mPresenter;

    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    SimpleMultiStateView mSimpleMultiStateView;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = inflater.inflate(getContentLayout(), container, false);
            unbinder = ButterKnife.bind(this, mRootView);
        }

        mContext = mRootView.getContext();
        mLoadingDialog = DialogHelper.getLoadingDialog(getActivity());
        return mRootView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInjector(MyApplication.getInstance().getApplicationComponent());
        attachView();
        bindView(view, savedInstanceState);
        initStateView();
    }

  
    @Nullable
    @Override
    public View getView() {
        return mRootView;
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onRetry() {

    }

    public  abstract  int   getContentLayout();
    public  abstract  void initInjector(ApplicationComponent appComponent);
    public  abstract  void  bindView(View view, @Nullable Bundle savedInstanceState);

    

    private void initStateView() {
      if (mSimpleMultiStateView == null) {
          return;}
        mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                .setRetryResource(R.layout.view_retry)
                .setLoadingResource(R.layout.view_loading)
                .setNoNetResource(R.layout.view_nonet)
                .build()
                .setonReLoadlistener(new MultiStateView.onReLoadlistener() {
                    @Override
                    public void onReload() {
                        onRetry();
                    }
                });
    }

    /***
     * 开始加载
     */
    @Override
    public void onStartDialogLoading() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showLoadingView();
        }
    }

    /**
     * 加载成功
     */
    @Override
    public void showSuccess() {
        hideLoadingDialog();
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showContent();
        }
    }


    protected void showLoadingDialog() {
        if (mLoadingDialog != null){
            mLoadingDialog.show();
        }

    }

    protected void showLoadingDialog(String str) {
        if (mLoadingDialog != null) {
            TextView tv = (TextView) mLoadingDialog.findViewById(R.id.tv_load_dialog);
            tv.setText(str);
            mLoadingDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())  {
            mLoadingDialog.dismiss();
        }

    }

    @Override
    public void onEndDialogLoading() {

    }


    /**
     * 加载失败
     */
    @Override
    public void showFaild() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showErrorView();
        }
    }

    /**
     * 网络连接失败
     */
    @Override
    public void showNoNet() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showNoNetView();
        }
    }

    /**
     * Toast
     * @param string
     */
    protected void T(String string) {
        T.showShort(MyApplication.getInstance(), string);
    }

   
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
    }

    @NonNull
    @Override
    public Subject<FragmentEvent> provideLifecycleSubject() {
        return lifecycleSubject;
    }
}
