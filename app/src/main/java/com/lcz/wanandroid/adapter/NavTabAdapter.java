package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.fragment.Navtion.Bean.NavBean;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public class NavTabAdapter implements TabAdapter{
    ArrayList<String> mList;

    public NavTabAdapter(ArrayList<String> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public ITabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {

        ITabView.TabTitle build = new ITabView.TabTitle.Builder()
                .setContent(mList.get(position))
                .setTextColor(R.color.red, R.color.yellow)
                .build();

        return build;

    }

    @Override
    public int getBackground(int position) {
        return 0;
    }
    private Baseitemclick mBaseitemclick;

    public void setBaseitemclick(Baseitemclick baseitemclick) {
        mBaseitemclick = baseitemclick;
    }
}
