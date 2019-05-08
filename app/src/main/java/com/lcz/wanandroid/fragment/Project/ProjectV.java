package com.lcz.wanandroid.fragment.Project;

import com.lcz.wanandroid.base.BaseV;
import com.lcz.wanandroid.fragment.Home.Bean.HomeListBean;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectTabBean;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public interface ProjectV extends BaseV{
    void onSussion(ProjectTabBean bean);

    void onFail(String str);
}
