package com.liangxq.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.liangxq.ApiException;
import com.liangxq.callback.BaseResponce;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名：MyMvpDemo
 * 包名：  com.liangxq.utils
 * 文件名：HttpUtils
 * 创建者：liangxq
 * 创建时间：2019/4/29  9:07
 * 描述：TODO
 */
public class HttpUtils {

    //网络判断
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }


    //线程切换的抽取
    public static <T> ObservableTransformer<T, T> rxShcdule() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    //获取我们关心的数据并且转换Data
    public static <T> ObservableTransformer<BaseResponce<T>, T> handResult() {
        return new ObservableTransformer<BaseResponce<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponce<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponce<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponce<T> tBaseResponce) throws Exception {
                        if (tBaseResponce.getErrorCode() == 0) {
                            return create(tBaseResponce.getData());
                        } else {
                            return Observable.error(new ApiException(tBaseResponce.getErrorMsg(), tBaseResponce.getErrorCode()));
                        }
                    }
                });
            }
        };
    }


    //封装数据到Observable
    public static <T> Observable<T> create(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                try {
                    e.onNext(t);
                    e.onComplete();
                } catch (Exception e1) {
                    e.onError(e1);
                }
            }
        });
    }
}
