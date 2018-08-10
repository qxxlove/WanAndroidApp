package com.tjbool.httpwww.wanandroid.mvp.model.api;


import com.tjbool.httpwww.wanandroid.mvp.model.bean.NewsDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */
public interface NewsApiService {

    @GET("ClientNews")
    Observable<List<NewsDetail>> getNewsDetail(@Query("id") String id,
                                               @Query("action") String action,
                                               @Query("pullNum") int pullNum
    );




}
