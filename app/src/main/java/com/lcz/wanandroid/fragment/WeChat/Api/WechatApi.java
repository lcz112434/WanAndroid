package com.lcz.wanandroid.fragment.WeChat.Api;

import com.lcz.wanandroid.fragment.WeChat.Bean.WechatListBean;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatTabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public interface WechatApi {
    String url = "https://wanandroid.com/wxarticle/";

    @GET("chapters/json")
    Observable<WechatTabBean> getTab();

    @GET("list/{page}/1/json")
    Observable<WechatListBean> getList(@Path("page") int page);
}
