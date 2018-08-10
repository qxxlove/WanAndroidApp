package com.tjbool.httpwww.wanandroid.app.impl;

/**
 * description: IView规范
 * autour: TMM
 * date: 2018/7/24 16:14 
 * update: 2018/7/24
 * version: 
*/

public interface IView {

    /**开始加载(界面初始化dialog显示)*/
    void  onStartDialogLoading();
    /**结束加载*/
    void  onEndDialogLoading();

    /**显示请求成功*/
    void showSuccess();
    /**失败重试*/
    void showFaild();
    /**显示当前网络不可用*/
    void showNoNet();
    /**重试*/
    void onRetry();


}
