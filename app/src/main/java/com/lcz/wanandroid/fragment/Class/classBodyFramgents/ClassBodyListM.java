package com.lcz.wanandroid.fragment.Class.classBodyFramgents;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Class.Api.ClassApi;
import com.lcz.wanandroid.fragment.Class.Bean.ClassListBean;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class ClassBodyListM extends BaseM {

    public void getData(int page,final MyCallBack<ClassListBean> myCallBack) {
        ClassApi classApi = HttpUtils.getInstance().getApiserver(ClassApi.url, ClassApi.class);
        Observable<ClassListBean> max = classApi.getList(page);
        max.compose(RxUtils.<ClassListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ClassListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ClassListBean classMaxBean) {
                        myCallBack.onSussion(classMaxBean);
                    }
                });
    }
}
