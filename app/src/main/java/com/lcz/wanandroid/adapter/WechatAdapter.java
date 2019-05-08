package com.lcz.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public class WechatAdapter extends FragmentPagerAdapter {
    private List<WechatTabBean.DataBean> mtitle;
    private ArrayList<BaseFragment> mList;

    public WechatAdapter(FragmentManager fm, List<WechatTabBean.DataBean> mtitle, ArrayList<BaseFragment> list) {
        super(fm);
        this.mtitle = mtitle;
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
        return mtitle.get(position).getName();
    }
}
