package com.lcz.wanandroid.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public abstract class BaseFragment<V extends BaseV, P extends BaseP> extends Fragment implements BaseV {

    public P mpresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(initLayout(), container, false);
        ButterKnife.bind(this, inflate);
        mpresenter = iniPresenter();
        if (mpresenter != null) {
            mpresenter.bint((V) this);
        }
        initView();
        initData();
        initListener();
        return inflate;
    }

    protected abstract int initLayout();

    protected abstract P iniPresenter();

    public void initListener() {
    }

    public void initData() {
    }

    public void initView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mpresenter.onDestory();
    }
}
