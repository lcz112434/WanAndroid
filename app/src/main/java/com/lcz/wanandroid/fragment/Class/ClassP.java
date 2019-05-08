package com.lcz.wanandroid.fragment.Class;

import android.util.Log;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class ClassP extends BaseP<ClassV>{

    private ClassM mClassM;

    @Override
    protected void initData() {
        mClassM = new ClassM();
        mList.add(mClassM);
    }
    public void setData(){
        mClassM.getData(new MyCallBack<ClassMaxBean>() {
            @Override
            public void onSussion(ClassMaxBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {
                Log.d("classP", "onFail: "+str);
            }
        });
    }
}
