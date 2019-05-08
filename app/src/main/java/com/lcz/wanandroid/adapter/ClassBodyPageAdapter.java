package com.lcz.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public class ClassBodyPageAdapter extends FragmentPagerAdapter {
    private List<ClassMaxBean.DataBean.ChildrenBean> mtitls;
    private ArrayList<BaseFragment> mList;

    public ClassBodyPageAdapter(FragmentManager fm, List<ClassMaxBean.DataBean.ChildrenBean> mtitls, ArrayList<BaseFragment> list) {
        super(fm);
        this.mtitls = mtitls;
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mtitls.get(position).getName();
    }
}
