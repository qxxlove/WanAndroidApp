package com.tjbool.httpwww.wanandroid.mvp.contract;

import com.tjbool.httpwww.wanandroid.app.impl.IModel;
import com.tjbool.httpwww.wanandroid.app.impl.IView;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/25.
 * 邮箱：123123@163.com
 */

public interface KnowledgeContract {

    /**对于经常使用的关于UI的方法可以定义到BaseView中,
     * 如显示隐藏进度条,和显示文字消息*/
    interface  View extends IView{
   
    }

    /** Model层定义接口,外部只需关心model返回的数据,
     * 无需关心内部细节,及是否使用缓存*/
    interface  Model extends IModel{
        
    }
}
