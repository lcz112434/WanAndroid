package com.lcz.wanandroid.callback;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public interface MyCallBack<o extends Object> {
    void onSussion(o bean);

    void onFail(String str);
}
