package com.lcz.wanandroid.fragment.Project.bodyfragment;

import android.util.Log;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectListBean;

/**
 * Created by 李承泽 on 2019/5/3.
 */
public class ProjecetListP extends BaseP<ProjectListV> {

    private ProjectListM mProjectListM;

    public void setData(int page) {
        mProjectListM.getData(page, new MyCallBack<ProjectListBean>() {
            @Override
            public void onSussion(ProjectListBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {
                Log.d("ProjecetListP", "onFail: " + str);
            }
        });
    }

    @Override
    protected void initData() {
        mProjectListM = new ProjectListM();
        mList.add(mProjectListM);
    }
}
