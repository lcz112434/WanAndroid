package com.lcz.wanandroid.activity.common;

import android.util.Log;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;
import com.lcz.wanandroid.fragment.Class.ClassM;
import com.lcz.wanandroid.fragment.Class.ClassV;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class CommP extends BaseP<CommV>{

    private CommM mClassM;

    @Override
    protected void initData() {
        mClassM = new CommM();
        mList.add(mClassM);
    }
    public void setData(){
        mClassM.getData(new MyCallBack<commBean>() {
            @Override
            public void onSussion(commBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {
                Log.d("classP", "onFail: "+str);
            }
        });
    }
}
