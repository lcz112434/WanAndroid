package com.lcz.wanandroid.base;

import android.support.v7.view.menu.MenuView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public abstract class BaseP<V extends BaseV> {
    public V mView;
    public ArrayList<BaseM> mList = new ArrayList<>();
    private WeakReference<V> mWeackReferece;

    public void bint(V mView) {
        mWeackReferece = new WeakReference<>(mView);
        this.mView = mWeackReferece.get();
    }

    public BaseP() {
        initData();
    }

    protected abstract void initData();


    public void onDestory() {
        if (mWeackReferece != null) {
            mWeackReferece.clear();
        }
        if (mList.size() > 0 && mList != null) {
            for (BaseM baseM : mList) {
                baseM.onDestory();
            }
        }
    }
}
