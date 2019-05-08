package com.lcz.wanandroid.fragment.Home;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.fragment.Home.Bean.HomeBannerBean;
import com.lcz.wanandroid.fragment.Home.Bean.HomeListBean;
import com.lcz.wanandroid.callback.MyCallBack;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class HomeP extends BaseP<HomeV> {

    private HomeM mHomeM;

    public void getData(int page) {
        mHomeM.getData(page, new MyCallBack<HomeListBean>() {
            @Override
            public void onSussion(HomeListBean bean) {
                if (mView != null) {
                    mView.onSussion(bean);
                }
            }

            @Override
            public void onFail(String str) {
                if (mView != null) {
                    mView.onFail(str);
                }
            }
        });
        mHomeM.getBanner(new MyCallBack<HomeBannerBean>() {
            @Override
            public void onSussion(HomeBannerBean bean) {
                mView.onBannerSussion(bean);
            }

            @Override
            public void onFail(String str) {
                mView.onBannerFail(str);
            }
        });
    }

    @Override
    protected void initData() {
        mHomeM = new HomeM();
        mList.add(mHomeM);
    }
}
