package com.lcz.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李承泽 on 2019/4/28.
 */
public class ProjectAdapter extends FragmentPagerAdapter{
    private List<ProjectTabBean.DataBean> mTitile;
    private ArrayList<BaseFragment> mlist;

    public ProjectAdapter(FragmentManager fm, List<ProjectTabBean.DataBean> titile, ArrayList<BaseFragment> mlist) {
        super(fm);
        mTitile = titile;
        this.mlist = mlist;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitile.get(position).getName();
    }
}
