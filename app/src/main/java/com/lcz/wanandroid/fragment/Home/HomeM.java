package com.lcz.wanandroid.fragment.Home;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.fragment.Home.Api.HomeApi;
import com.lcz.wanandroid.fragment.Home.Bean.HomeBannerBean;
import com.lcz.wanandroid.fragment.Home.Bean.HomeListBean;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class HomeM extends BaseM {

    public void getData(int page, final MyCallBack<HomeListBean> myCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HomeApi.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomeApi homeApi = retrofit.create(HomeApi.class);
        Observable<HomeListBean> list = homeApi.getList(page);
        list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeListBean homeListBean) {
                        myCallBack.onSussion(homeListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        myCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBanner(final MyCallBack<HomeBannerBean> myCallBack) {
        HomeApi apiserver = HttpUtils.getInstance().getApiserver(HomeApi.url, HomeApi.class);
        Observable<HomeBannerBean> banner = apiserver.getBanner();
        banner.compose(RxUtils.<HomeBannerBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        myCallBack.onSussion(homeBannerBean);
                    }
                });
    }
}
