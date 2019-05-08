package com.lcz.wanandroid.fragment.Project;

import com.lcz.wanandroid.base.BaseP;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectTabBean;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class ProjectP extends BaseP<ProjectV> {

    private ProjectM mProjectM;

    public void setData() {
        mProjectM.getData(new MyCallBack<ProjectTabBean>() {
            @Override
            public void onSussion(ProjectTabBean bean) {
                mView.onSussion(bean);
            }

            @Override
            public void onFail(String str) {
                mView.onFail(str);
            }
        });
    }

    @Override
    protected void initData() {
        mProjectM = new ProjectM();
        mList.add(mProjectM);
    }
}
