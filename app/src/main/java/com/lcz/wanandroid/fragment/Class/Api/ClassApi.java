package com.lcz.wanandroid.fragment.Class.Api;

import com.lcz.wanandroid.fragment.Class.Bean.ClassListBean;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 李承泽 on 2019/5/3.
 */
public interface ClassApi {
    String url="https://www.wanandroid.com/";

    @GET("tree/json")
    Observable<ClassMaxBean> getMax();

    @GET("article/list/0/json?")
    Observable<ClassListBean> getList(@Query("cid") int page);
}
