package com.lcz.wanandroid.fragment.Project.Api;

import com.lcz.wanandroid.fragment.Project.Bean.ProjectListBean;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectTabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 李承泽 on 2019/4/28.
 */
public interface ProjecetApi {
    String url = "http://www.wanandroid.com/";

    @GET("project/tree/json")
    Observable<ProjectTabBean> getTab();

    @GET("project/list/1/json?")
    Observable<ProjectListBean> getList(@Query("cid") int page);
}
