package com.lcz.wanandroid.fragment.Project;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.lcz.wanandroid.R;
import com.lcz.wanandroid.adapter.ProjectAdapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectTabBean;
import com.lcz.wanandroid.fragment.Project.bodyfragment.ProjectListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<ProjectV, ProjectP> implements ProjectV {


    private static final String TAG = "ProjectFragment";
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp_project)
    ViewPager vpProject;
    Unbinder unbinder;
    private boolean mBoolean;

    public void setBoolean(boolean aBoolean) {
        mBoolean = aBoolean;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_project;
    }

    @Override
    protected ProjectP iniPresenter() {
        return new ProjectP();
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.layout_divider_vertical));
    }

    @Override
    public void initData() {
        super.initData();
        mpresenter.setData();
    }

    @Override
    public void onSussion(final ProjectTabBean bean) {
        Log.d(TAG, "onSussion: " + bean.getErrorCode());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ProjectTabBean.DataBean> data = bean.getData();
                ArrayList<BaseFragment> fragments = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    ProjectListFragment projectListFragment = new ProjectListFragment(data.get(i).getId());
                    projectListFragment.setB(mBoolean);
                    fragments.add(projectListFragment);
                }
                ProjectAdapter projectAdapter = new ProjectAdapter(getChildFragmentManager(), data, fragments);
                vpProject.setAdapter(projectAdapter);
                tabLayout.setViewPager(vpProject);
            }
        });
    }

    @Override
    public void onFail(String str) {

    }


}
