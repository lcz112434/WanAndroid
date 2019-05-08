package com.lcz.wanandroid.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class BaseM {
     public CompositeDisposable mCompositeDisposable=  new CompositeDisposable();
    public void onDestory() {
        mCompositeDisposable.clear();
    }
}
