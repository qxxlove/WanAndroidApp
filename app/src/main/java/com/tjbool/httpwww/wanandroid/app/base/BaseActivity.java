package com.tjbool.httpwww.wanandroid.app.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tjbool.httpwww.wanandroid.MyApplication;
import com.tjbool.httpwww.wanandroid.R;
import com.tjbool.httpwww.wanandroid.app.impl.IPresenter;
import com.tjbool.httpwww.wanandroid.app.impl.IView;
import com.tjbool.httpwww.wanandroid.app.rxlifecycle.ActivityLifecycleable;
import com.tjbool.httpwww.wanandroid.dagger.component.ApplicationComponent;
import com.tjbool.httpwww.wanandroid.mvp.ui.custom.DialogHelper;
import com.tjbool.httpwww.wanandroid.mvp.ui.custom.MultiStateView;
import com.tjbool.httpwww.wanandroid.mvp.ui.custom.SimpleMultiStateView;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * description: BaseActivity
 * autour: TMM
 * date: 2018/7/24 16:08 
 * update: 2018/7/24
 * version: 
*/

public abstract class BaseActivity<P extends IPresenter>  extends AppCompatActivity
                             implements IView, ActivityLifecycleable{
    
    protected final String TAG = this.getClass().getSimpleName();

    /**RxJava中BehaviorSubject 消息机制,
        作用：用来发射和接收Activity 的生命周期*/
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject
                                                   = BehaviorSubject.create();

    protected Dialog mLoadingDialog = null;
   
    private Unbinder mUnbinder;

    @Nullable
    @Inject
    protected   P mPresenter;

    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    SimpleMultiStateView mSimpleMultiStateView;

    @NonNull
    @Override
    public Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutResID = initView(savedInstanceState);
            /**如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife*/
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initData(savedInstanceState);
        initInjector(MyApplication.getInstance().getApplicationComponent());
        attachView();

        initStateView();
        mLoadingDialog = DialogHelper.getLoadingDialog(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
        if (mPresenter != null) {
            //释放资源
            mPresenter.onDestory();
        }
        this.mPresenter = null;
    }



    private void attachView() {
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    /**
     *初始化状态视图
     */
    private void initStateView() {
        if (mSimpleMultiStateView == null) {
            return;
        }
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


    /**
     * 重新加载
     */
    @Override
    public void onRetry() {
        
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

    /***
     * 开始加载
     */
    @Override
    public void onStartDialogLoading() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showLoadingView();
        }
    }

    @Override
    public void onEndDialogLoading() {
        
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

    /**
     * 隐藏加载框
     */
    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())  {
            mLoadingDialog.dismiss();
        }

    }


    /**初始化View*/
    public abstract int  initView(@Nullable Bundle savedInstanceState);
    /**初始化数据*/
    public abstract void initData(@Nullable Bundle savedInstanceState);
    /**初始化Dagger*/
    public  abstract  void initInjector(ApplicationComponent appComponent);

}
