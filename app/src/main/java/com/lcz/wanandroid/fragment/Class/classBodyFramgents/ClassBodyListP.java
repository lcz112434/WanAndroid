package com.lcz.wanandroid.fragment.Class.classBodyFramgents;

import android.util.Log;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Class.Bean.ClassListBean;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;
import com.lcz.wanandroid.fragment.Class.ClassM;
import com.lcz.wanandroid.fragment.Class.ClassV;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class ClassBodyListP extends BaseP<ClassBodyListV>{

    private ClassBodyListM mClassM;

    @Override
    protected void initData() {
        mClassM = new ClassBodyListM();
        mList.add(mClassM);
    }
    public void setData(int page){
        mClassM.getData(page,new MyCallBack<ClassListBean>() {
            @Override
            public void onSussion(ClassListBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {
                Log.d("classP", "onFail: "+str);
            }
        });
    }
}
