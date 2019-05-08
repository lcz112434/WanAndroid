package com.lcz.wanandroid.fragment.WeChat.WechatBodyFragment;


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
import com.lcz.wanandroid.activity.WebActivity;
import com.lcz.wanandroid.adapter.WechatListAdapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatListBean;
import com.scwang.smartrefresh.header.BezierCircleHeader;
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
public class WechatListFragment extends BaseFragment<WeChatListV, WechatListP> implements WeChatListV {

    private static final String TAG = "WechatListFragment";
    @BindView(R.id.wechat_rlv)
    RecyclerView wechatRlv;
    @BindView(R.id.smart_wechat)
    SmartRefreshLayout smartWechat;
    @BindView(R.id.wechat_float)
    FloatingActionButton homeFloat;
    private int id;
    private String name;
    private ArrayList<WechatListBean.DataBean.DatasBean> mList;
    private WechatListAdapter mWechatListAdapter;

    public WechatListFragment(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_wechat_list;
    }

    @Override
    protected WechatListP iniPresenter() {
        return new WechatListP();
    }

    @Override
    public void initView() {
        super.initView();
        wechatRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mWechatListAdapter = new WechatListAdapter(mList, getContext(), name);
        wechatRlv.setAdapter(mWechatListAdapter);

        mWechatListAdapter.setBaseitemclick(new Baseitemclick() {
            @Override
            public void onitemclick(int position) {
                WechatListBean.DataBean.DatasBean datasBean = mList.get(position);
                String url = datasBean.getLink();
                String name = datasBean.getTitle();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("name", name);
                intent.putExtra("autor", datasBean.getAuthor());
                intent.putExtra("fenlei", datasBean.getSuperChapterName() + "/" + datasBean.getChapterName());
                intent.putExtra("time", datasBean.getNiceDate());
                startActivity(intent);
            }
        });

        smartWechat.setRefreshHeader(new BezierCircleHeader(getContext()));
        smartWechat.setRefreshFooter(new ClassicsFooter(getContext()));
        smartWechat.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
            }
        });
        smartWechat.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
            }
        });
        homeFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wechatRlv.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        HideTablayout_Float();
    }

    private void HideTablayout_Float() {

        //滑动recyclerView隐藏tablayout
        wechatRlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    public void onSussion(WechatListBean bean) {
        Log.d(TAG, "onSussion: " + bean.toString());
        mList.addAll(bean.getData().getDatas());
        mWechatListAdapter.setList(mList);
        mWechatListAdapter.notifyDataSetChanged();
    }

}
