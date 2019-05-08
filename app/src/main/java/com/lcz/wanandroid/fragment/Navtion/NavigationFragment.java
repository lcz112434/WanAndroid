package com.lcz.wanandroid.fragment.Navtion;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.adapter.NavBodyAdapter;
import com.lcz.wanandroid.adapter.NavTabAdapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.Navtion.Bean.NavBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment<NavtionV, NavtionP> implements NavtionV {


    @BindView(R.id.nav_tab)
    VerticalTabLayout navTab;
    @BindView(R.id.rlv_nav)
    RecyclerView rlvNav;
    @BindView(R.id.nva_float)
    FloatingActionButton homeFloat;
    private boolean isScroll;


    @Override
    protected int initLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected NavtionP iniPresenter() {
        return new NavtionP();
    }

    @Override
    public void initView() {
        super.initView();
        navTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) rlvNav.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        rlvNav.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) rlvNav.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                navTab.setTabSelected(top);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
                dx : 水平滚动距离
                dy : 垂直滚动距离
                dx > 0 时为手指向左滚动,列表滚动显示右面的内容
                dx < 0 时为手指向右滚动,列表滚动显示左面的内容
                dy > 0 时为手指向上滚动,列表滚动显示下面的内容
                dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) rlvNav.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                //可见的最后一条条目
                //int bottom = layoutManager.findLastVisibleItemPosition();
                if (isScroll) {
                    if (dy > 0) {
                        navTab.setTabSelected(top);
                    }
                }
            }
        });
        homeFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlvNav.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.toobar).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        HideTablayout_Float();
    }

    private void HideTablayout_Float() {

        //滑动recyclerView隐藏tablayout
        rlvNav.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        mpresenter.setData();
    }


    @Override
    public void onSussion(NavBean bean) {
        List<NavBean.DataBean> data = bean.getData();
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getName());
        }
        NavTabAdapter navTabAdapter = new NavTabAdapter(list);
        navTab.setTabAdapter(navTabAdapter);

        rlvNav.setAdapter(new NavBodyAdapter(data, getContext()));
        rlvNav.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
