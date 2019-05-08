package com.lcz.wanandroid.fragment.WeChat.WechatBodyFragment;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.WeChat.Api.WechatApi;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatListBean;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatTabBean;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class WechatListM extends BaseM {

    public void getData(int page, final MyCallBack<WechatListBean> myCallBack) {
        WechatApi wechatApi = HttpUtils.getInstance().getApiserver(WechatApi.url, WechatApi.class);
        wechatApi.getList(page).compose(RxUtils.<WechatListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WechatListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WechatListBean bean) {
                        myCallBack.onSussion(bean);
                    }
                });
    }
}
