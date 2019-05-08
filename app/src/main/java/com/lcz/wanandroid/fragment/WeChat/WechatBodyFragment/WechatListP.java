package com.lcz.wanandroid.fragment.WeChat.WechatBodyFragment;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatListBean;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatTabBean;
import com.lcz.wanandroid.fragment.WeChat.WeChatV;
import com.lcz.wanandroid.fragment.WeChat.WechatM;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class WechatListP extends BaseP<WeChatListV>{

    private WechatListM mWechatM;

    public void  setData(int page){
        mWechatM.getData(page,new MyCallBack<WechatListBean>() {
            @Override
            public void onSussion(WechatListBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {

            }
        });
    }
    @Override
    protected void initData() {
        mWechatM = new WechatListM();
        mList.add(mWechatM);
    }
}
