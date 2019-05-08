package com.lcz.wanandroid.fragment.Navtion;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Navtion.Api.NavApi;
import com.lcz.wanandroid.fragment.Navtion.Bean.NavBean;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class NationM extends BaseM {

    public void getData(final MyCallBack<NavBean> myCallBack) {
        NavApi api = HttpUtils.getInstance().getApiserver(NavApi.url, NavApi.class);
        api.getNav().compose(RxUtils.<NavBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NavBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NavBean navBean) {
                        myCallBack.onSussion(navBean);
                    }
                });
    }
}
