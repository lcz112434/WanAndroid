package com.lcz.wanandroid.activity.login;

import com.lcz.wanandroid.activity.Serac.SeracApi;
import com.lcz.wanandroid.activity.Serac.SeracBean;
import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class LoginM extends BaseM {

    public void getData(String name,String psw,final MyCallBack<LoginBean> myCallBack) {
        loginApi classApi = HttpUtils.getInstance().getApiserver(loginApi.url, loginApi.class);
        Observable<LoginBean> getlogin = classApi.getlogin(name,psw);
        getlogin.compose(RxUtils.<LoginBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginBean classMaxBean) {
                        myCallBack.onSussion(classMaxBean);
                    }
                });


    }
}
