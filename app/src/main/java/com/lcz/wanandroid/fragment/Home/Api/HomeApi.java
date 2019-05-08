package com.lcz.wanandroid.fragment.Home.Api;

import com.lcz.wanandroid.fragment.Home.Bean.HomeBannerBean;
import com.lcz.wanandroid.fragment.Home.Bean.HomeListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public interface HomeApi {
    String url="http://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<HomeListBean> getList(@Path("page") int page);

    @GET("banner/json")
    Observable<HomeBannerBean> getBanner();
}
