package com.lcz.wanandroid.fragment.WeChat;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatTabBean;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class WechatP extends BaseP<WeChatV>{

    private WechatM mWechatM;

    public void  setData(){
        mWechatM.getData(new MyCallBack<WechatTabBean>() {
            @Override
            public void onSussion(WechatTabBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {

            }
        });
    }
    @Override
    protected void initData() {
        mWechatM = new WechatM();
        mList.add(mWechatM);
    }
}
