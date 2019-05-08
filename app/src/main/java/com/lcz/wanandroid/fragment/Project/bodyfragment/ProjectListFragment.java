package com.lcz.wanandroid.fragment.Project.bodyfragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.WebActivity;
import com.lcz.wanandroid.adapter.ProjectListadapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.base.Check;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectListBean;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ProjectListFragment extends BaseFragment<ProjectListV, ProjecetListP> implements ProjectListV {

    private static final String TAG = "ProjectListFragment";
    int id;
    @BindView(R.id.rlv_project)
    RecyclerView rlvProject;
    @BindView(R.id.smart_project)
    SmartRefreshLayout smartProject;
    @BindView(R.id.projcet_float)
    FloatingActionButton homeFloat;
    private ArrayList<ProjectListBean.DataBean.DatasBean> mList;
    private ProjectListadapter mProjectListadapter;
    private boolean b;


    public void setB(boolean b) {
        this.b = b;
    }

    @SuppressLint("ValidFragment")
    public ProjectListFragment(int id) {
        this.id = id;
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected ProjecetListP iniPresenter() {
        return new ProjecetListP();
    }


    @Override
    public void initView() {
        super.initView();
        mList = new ArrayList<>();
        rlvProject.setLayoutManager(new LinearLayoutManager(getContext()));
        mProjectListadapter = new ProjectListadapter(mList, getContext());
        rlvProject.setAdapter(mProjectListadapter);
        mProjectListadapter.setBaseitemclick(new Baseitemclick() {
            @Override
            public void onitemclick(int position) {
                ProjectListBean.DataBean.DatasBean datasBean = mList.get(position);
                String url = datasBean.getLink();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("name", datasBean.getTitle());
                intent.putExtra("autor", datasBean.getAuthor());
                intent.putExtra("fenlei", datasBean.getSuperChapterName() + "/" + datasBean.getChapterName());
                intent.putExtra("time", datasBean.getNiceDate());
                startActivity(intent);
            }
        });

        smartProject.setRefreshHeader(new FunGameBattleCityHeader(getContext()));
        smartProject.setRefreshFooter(new ClassicsFooter(getContext()));
        smartProject.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                mList.clear();
                initData();
                refreshlayout.finishRefresh();
            }
        });
        smartProject.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
            }
        });
        homeFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlvProject.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        HideTablayout_Float();
    }

    private void HideTablayout_Float() {

        //滑动recyclerView隐藏tablayout
        rlvProject.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15) {
                        homeFloat.setVisibility(View.GONE);
                        getActivity().findViewById(R.id.rg).setVisibility(View.GONE);
                    } else if (dy < -15) {
                        homeFloat.setVisibility(View.VISIBLE);
                        getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mpresenter.setData(id);
    }

    @Override
    public void onSussion(ProjectListBean bean) {
        mList.addAll(bean.getData().getDatas());
        mProjectListadapter.setList(mList);
//        Log.d(TAG, "onSussion: " + mList.size());
        mProjectListadapter.notifyDataSetChanged();
    }
}
