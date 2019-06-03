package com.lcz.wanandroid.activity.Serac;

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
public class SeracM extends BaseM {

    public void getData(final MyCallBack<SeracBean> myCallBack) {
        SeraceApi classApi = HttpUtils.getInstance().getApiserver(SeraceApi.url, SeraceApi.class);
        Observable<SeracBean> max = classApi.getSerac();
        max.compose(RxUtils.<SeracBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SeracBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SeracBean classMaxBean) {
                        myCallBack.onSussion(classMaxBean);
                    }
                });


    }
}
