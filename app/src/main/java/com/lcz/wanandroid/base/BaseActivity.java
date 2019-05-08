package com.lcz.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.lcz.wanandroid.R;

import butterknife.ButterKnife;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public abstract class BaseActivity<V extends BaseV, P extends BaseP> extends AppCompatActivity {
    protected P mpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        ButterKnife.bind(this);
        mpresenter= Inpresneter();
        if (mpresenter != null) {
            mpresenter.bint((V) this);
        }
        initView();
        initData();
        initListener();
    }

    protected abstract P Inpresneter();

    protected void initListener() {

    }

    protected void initData() {
    }

    protected void initView() {

    }

    protected abstract int LayoutId();
}
