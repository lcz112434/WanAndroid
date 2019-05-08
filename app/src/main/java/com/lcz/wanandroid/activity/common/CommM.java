package com.lcz.wanandroid.activity.common;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Class.Api.ClassApi;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class CommM extends BaseM {

    public void getData(final MyCallBack<commBean> myCallBack) {
        commApi classApi = HttpUtils.getInstance().getApiserver(commApi.url, commApi.class);
        Observable<commBean> max = classApi.getComm();
        max.compose(RxUtils.<commBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<commBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(commBean classMaxBean) {
                        myCallBack.onSussion(classMaxBean);
                    }
                });


    }
}
