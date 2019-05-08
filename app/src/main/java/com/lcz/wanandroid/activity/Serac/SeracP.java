package com.lcz.wanandroid.activity.Serac;

import android.util.Log;

import com.lcz.wanandroid.activity.common.CommM;
import com.lcz.wanandroid.activity.common.CommV;
import com.lcz.wanandroid.activity.common.commBean;
import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class SeracP extends BaseP<SeracV>{

    private SeracM mClassM;

    @Override
    protected void initData() {
        mClassM = new SeracM();
        mList.add(mClassM);
    }
    public void setData(){
        mClassM.getData(new MyCallBack<SeracBean>() {
            @Override
            public void onSussion(SeracBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {
                Log.d("classP", "onFail: "+str);
            }
        });
    }
}
