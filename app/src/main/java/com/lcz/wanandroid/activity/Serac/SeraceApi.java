package com.lcz.wanandroid.activity.Serac;

import com.lcz.wanandroid.activity.common.commBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 李承泽 on 2019/5/5.
 */
public interface SeraceApi {
    String url = "https://www.wanandroid.com/";

    @GET("hotkey/json")
    Observable<SeracBean> getSerac();
}
