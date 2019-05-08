package com.lcz.wanandroid.fragment.WeChat;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.WeChat.Api.WechatApi;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatTabBean;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class WechatM extends BaseM {

    public void getData(final MyCallBack<WechatTabBean> myCallBack) {
        WechatApi wechatApi = HttpUtils.getInstance().getApiserver(WechatApi.url, WechatApi.class);
        wechatApi.getTab().compose(RxUtils.<WechatTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WechatTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WechatTabBean bean) {
                        myCallBack.onSussion(bean);
                    }
                });
    }
}
