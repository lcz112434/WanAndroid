package com.lcz.wanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lcz.wanandroid.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 李承泽 on 2019/4/26.
 */
public class MianPagerAdaper extends FragmentPagerAdapter {
    ArrayList<BaseFragment> mList;

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public MianPagerAdaper(FragmentManager fm, ArrayList<BaseFragment> list) {
        super(fm);
        mList = list;
    }
}
