package com.lcz.wanandroid.activity.login;

import com.lcz.wanandroid.activity.Serac.SeracBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 李承泽 on 2019/5/5.
 */
public interface loginApi {
    String url = "http://yun918.cn/study/public/index.php/";

    @POST("login")
    Observable<LoginBean> getlogin(@Query("username") String name,@Query("password") String psw);



}
