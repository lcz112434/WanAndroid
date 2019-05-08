package com.lcz.wanandroid.fragment.Class;

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
public class ClassM extends BaseM {

    public void getData(final MyCallBack<ClassMaxBean> myCallBack) {
        ClassApi classApi = HttpUtils.getInstance().getApiserver(ClassApi.url, ClassApi.class);
        Observable<ClassMaxBean> max = classApi.getMax();
        max.compose(RxUtils.<ClassMaxBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ClassMaxBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ClassMaxBean classMaxBean) {
                        myCallBack.onSussion(classMaxBean);
                    }
                });


    }
}
