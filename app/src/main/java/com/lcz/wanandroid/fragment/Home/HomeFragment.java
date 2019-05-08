package com.lcz.wanandroid.fragment.Home;


import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.lcz.wanandroid.activity.MainActivity;
import com.lcz.wanandroid.activity.WebActivity;
import com.lcz.wanandroid.adapter.HomeAdapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.Home.Bean.HomeBannerBean;
import com.lcz.wanandroid.fragment.Home.Bean.HomeListBean;
import com.scwang.smartrefresh.header.PhoenixHeader;
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
public class HomeFragment extends BaseFragment<HomeV, HomeP> implements HomeV {

    private static final String TAG = "HomeFragment";
    public int page = 0;
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;
    @BindView(R.id.smart_home)
    SmartRefreshLayout smartHome;
    @BindView(R.id.home_float)
    FloatingActionButton homeFloat;
    private ArrayList<HomeListBean.DataBean.DatasBean> mListBean;
    private ArrayList<HomeBannerBean.DataBean> mBannerList;
    private HomeAdapter mHomeAdapter;
    private ArrayList<String> mList;

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeP iniPresenter() {
        return new HomeP();
    }

    @Override
    public void initView() {
        super.initView();
        mListBean = new ArrayList<>();
        mBannerList = new ArrayList<>();
        mList = new ArrayList<>();
        rlvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeAdapter = new HomeAdapter(mList, mListBean, mBannerList, getContext());
        rlvHome.setAdapter(mHomeAdapter);
        mHomeAdapter.setBannitemclick(new HomeAdapter.bannitemclick() {
            @Override
            public void baneronitemclick(int position) {
                HomeBannerBean.DataBean dataBean = mBannerList.get(position);
                String url = dataBean.getUrl();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("name", dataBean.getTitle());
                startActivity(intent);
            }
        });
        mHomeAdapter.setItemclick(new HomeAdapter.itemclick() {
            @Override
            public void onitemclick(int position) {
                HomeListBean.DataBean.DatasBean datasBean = mListBean.get(position);
                String link = datasBean.getLink();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", link);
                intent.putExtra("name", datasBean.getTitle());
                intent.putExtra("autor", datasBean.getAuthor());
                intent.putExtra("fenlei", datasBean.getSuperChapterName() + "/" + datasBean.getChapterName());
                intent.putExtra("time", datasBean.getNiceDate());

                startActivity(intent);
            }
        });
        smartHome.setRefreshHeader(new PhoenixHeader(getContext()));
        smartHome.setRefreshFooter(new ClassicsFooter(getContext()));
        smartHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
            }
        });
        smartHome.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mBannerList.clear();
                initData();
                mHomeAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });
////点击悬浮按钮回到底部
        homeFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlvHome.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        HideTablayout_Float();
    }

    private void HideTablayout_Float() {

        //滑动recyclerView隐藏tablayout
        rlvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        mpresenter.getData(page);
    }

    @Override
    public void onSussion(HomeListBean bean) {
        mListBean.addAll(bean.getData().getDatas());
        mHomeAdapter.setMlist(mListBean);
        mHomeAdapter.notifyDataSetChanged();
        Log.d(TAG, "onSussion: " + bean.getErrorCode());
    }

    @Override
    public void onFail(String str) {
        Log.d(TAG, "onFail: " + str);
    }

    @Override
    public void onBannerSussion(HomeBannerBean bean) {
        Log.d(TAG, "onBannerSussion: " + bean.getErrorCode());

        for (HomeBannerBean.DataBean dataBean : bean.getData()) {
            mList.add(dataBean.getTitle());
        }
        if (mBannerList.size() < 8) {
            mBannerList.addAll(bean.getData());
        }
        mHomeAdapter.setlist(mList);
        mHomeAdapter.setBanner(mBannerList);
        mHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBannerFail(String str) {
        Log.d(TAG, "onBannerFail: " + str);
    }


}
