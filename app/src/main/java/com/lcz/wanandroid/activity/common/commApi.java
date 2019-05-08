package com.lcz.wanandroid.activity.common;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 李承泽 on 2019/5/5.
 */
public interface commApi {
    String url = "https://www.wanandroid.com/";

    @GET("friend/json")
    Observable<commBean> getComm();
}
