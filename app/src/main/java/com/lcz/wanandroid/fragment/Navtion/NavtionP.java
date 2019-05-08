package com.lcz.wanandroid.fragment.Navtion;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Navtion.Bean.NavBean;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class NavtionP extends BaseP<NavtionV>{
    private NationM mMNationM;

    public void setData(){
        mMNationM.getData(new MyCallBack<NavBean>() {
            @Override
            public void onSussion(NavBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {

            }
        });
    }
    @Override
    protected void initData() {
        mMNationM = new NationM();
        mList.add(mMNationM);
    }
}
