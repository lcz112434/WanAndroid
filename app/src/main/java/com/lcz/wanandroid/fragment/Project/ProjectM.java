package com.lcz.wanandroid.fragment.Project;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Project.Api.ProjecetApi;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectTabBean;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class ProjectM extends BaseM {

    public void getData(final MyCallBack<ProjectTabBean> myCallBack) {
        ProjecetApi apiserver = HttpUtils.getInstance().getApiserver(ProjecetApi.url, ProjecetApi.class);
        apiserver.getTab().compose(RxUtils.<ProjectTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ProjectTabBean projectTabBean) {
                        myCallBack.onSussion(projectTabBean);
                    }
                });
    }
}
