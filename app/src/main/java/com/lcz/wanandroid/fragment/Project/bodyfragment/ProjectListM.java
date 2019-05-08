package com.lcz.wanandroid.fragment.Project.bodyfragment;

import com.lcz.wanandroid.base.BaseM;
import com.lcz.wanandroid.callback.MyCallBack;
import com.lcz.wanandroid.fragment.Project.Api.ProjecetApi;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectListBean;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectTabBean;
import com.lcz.wanandroid.utils.BaseObserver;
import com.lcz.wanandroid.utils.HttpUtils;
import com.lcz.wanandroid.utils.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class ProjectListM extends BaseM {

    public void getData(int page,final MyCallBack<ProjectListBean> myCallBack) {
        ProjecetApi apiserver = HttpUtils.getInstance().getApiserver(ProjecetApi.url, ProjecetApi.class);
        apiserver.getList(page).compose(RxUtils.<ProjectListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ProjectListBean projectTabBean) {
                        myCallBack.onSussion(projectTabBean);
                    }
                });
    }
}
