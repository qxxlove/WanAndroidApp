package com.tjbool.httpwww.wanandroid.app.base;

import android.app.Service;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.tjbool.httpwww.wanandroid.app.impl.IModel;
import com.tjbool.httpwww.wanandroid.app.impl.IPresenter;
import com.tjbool.httpwww.wanandroid.app.impl.IView;

import org.simple.eventbus.EventBus;

import dagger.internal.Preconditions;
import io.reactivex.functions.Action;

/**
 * description: BasePresenter
 * autour: TMM
 * date: 2018/7/24 16:09 
 * update: 2018/7/24
 * version: 
*/

public class BasePresenter<M extends IModel,V extends IView>  implements IPresenter ,LifecycleObserver {

    protected M mModel;
    protected V mRootView;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     * @param model
     * @param rootView
     */
    public BasePresenter(M model, V rootView) {
        Preconditions.checkNotNull(model, "%s cannot be null", IModel.class.getName());
        Preconditions.checkNotNull(rootView, "%s cannot be null", IView.class.getName());
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     * @param rootView
     */
    public BasePresenter(V rootView) {
        Preconditions.checkNotNull(rootView, "%s cannot be null", IView.class.getName());
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    /**
     * 注册LiftCycleObserver 给 LifeCycleOwner  
     */
    @Override
    public void onStart() {
        /**将 LifecycleObserver 注册给 LifecycleOwner 后 @OnLifecycleEvent 才可以正常使用*/
        if (mRootView != null && mRootView instanceof LifecycleOwner) {
            /**Presenter 同 mRootView 同生死*/
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
            if (mModel!= null && mModel instanceof LifecycleObserver){
                /**Model 同 mRootView 同生死*/
                ((LifecycleOwner) mRootView).getLifecycle().addObserver((LifecycleObserver) mModel);
            }
        }
        /**如果要使用 Eventbus 请将此方法返回 true*/
        if (useEventBus()) {
            EventBus.getDefault().register(this);}

    }

    /**
     *  此时onDestroy 由 Activity中 触发
     */
    @Override
    public void onDestory() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
       // unDispose();//解除订阅
        if (mModel != null){
            mModel.onDestory();
        }
        this.mModel = null;
        this.mRootView = null;
      //  this.mCompositeDisposable = null;
    }


    /**
     * 
     * 使用场景：只有当 {@code mRootView} 不为 null,并且 {@code mRootView} 实现了 {@link LifecycleOwner} 时,
     * 此方法才会被调用。
     * 所以当您想在 {@link Service} 以及一些自定义 {@link View} 或自定义类中使用 {@code Presenter} 时
     * 您也将不能继续使用 {@link OnLifecycleEvent} 绑定生命周期
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        /**
         * 注意, 如果在这里调用了 {@link #onDestroy()} 方法,
         *         会出现某些地方引用 {@code mModel} 或 {@code mRootView} 为 null 的情况
         *      比如在 {@link RxLifecycle} 终止 {@link Observable} 时,
         *      在 {@link io.reactivex.Observable#doFinally(Action)} 中却引用了 {@code mRootView}
         *           做一些释放资源的操作,此时会空指针
         *           或者如果你声明了多个 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) 时
         *                在其他 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
         *                中引用了 {@code mModel} 或 {@code mRootView} 也可能会出现此情况
         */
        owner.getLifecycle().removeObserver(this);
    }
    


    public boolean useEventBus() {
        return true;
    }

}
