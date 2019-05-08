package com.lcz.wanandroid.activity.login;

import android.util.Log;

import com.lcz.wanandroid.activity.Serac.SeracBean;
import com.lcz.wanandroid.activity.Serac.SeracM;
import com.lcz.wanandroid.activity.Serac.SeracV;
import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class LoginP extends BaseP<LoginV>{

    private LoginM mClassM;

    @Override
    protected void initData() {
        mClassM = new LoginM();
        mList.add(mClassM);
    }
    public void setData(String name,String psw){
        mClassM.getData(name,psw,new MyCallBack<LoginBean>() {
            @Override
            public void onSussion(LoginBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {
                Log.d("classP", "onFail: "+str);
            }
        });
    }
}
