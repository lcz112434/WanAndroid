package com.lcz.wanandroid.fragment.Home;

import com.lcz.wanandroid.base.BaseV;
import com.lcz.wanandroid.fragment.Home.Bean.HomeBannerBean;
import com.lcz.wanandroid.fragment.Home.Bean.HomeListBean;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public interface HomeV extends BaseV {
    void onSussion(HomeListBean bean);

    void onFail(String str);

    void onBannerSussion(HomeBannerBean bean);

    void onBannerFail(String str);
}
