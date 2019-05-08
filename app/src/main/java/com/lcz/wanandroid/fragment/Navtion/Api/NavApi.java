package com.lcz.wanandroid.fragment.Navtion.Api;

import com.lcz.wanandroid.fragment.Navtion.Bean.NavBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public interface NavApi {
    String url = "https://www.wanandroid.com/";

    @GET("navi/json")
    Observable<NavBean> getNav();
}
