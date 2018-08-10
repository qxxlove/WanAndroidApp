package com.tjbool.httpwww.wanandroid.mvp.contract;

import com.tjbool.httpwww.wanandroid.app.impl.IModel;
import com.tjbool.httpwww.wanandroid.app.impl.IView;
import com.tjbool.httpwww.wanandroid.mvp.model.bean.home.HomeDataBean;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

public interface HomeContract {

    /**对于经常使用的关于UI的方法可以定义到BaseView中,
     * 如显示隐藏进度条,和显示文字消息*/
    interface  View extends IView{

        void  startOnRefresh();
        void  startOnLoadMore();
        void  endOnLoadMore();
        
        void setAdapter(List<HomeDataBean.DataBean.DatasBean> entity);
    }

    /** Model层定义接口,外部只需关心model返回的数据,
     * 无需关心内部细节,及是否使用缓存*/
    interface  Model extends IModel{
        List<HomeDataBean.DataBean.DatasBean> getHomeDataBean();
    }
}
