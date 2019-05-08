package com.lcz.wanandroid.fragment.Class;


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
import com.lcz.wanandroid.adapter.ClassMaxAdapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;
import com.lcz.wanandroid.fragment.Class.activity.ClassBodyActivity;
import com.scwang.smartrefresh.header.DeliveryHeader;
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
public class ClassFragment extends BaseFragment<ClassV, ClassP> implements ClassV {

    @BindView(R.id.class_rlv)
    RecyclerView classRlv;
    @BindView(R.id.smart_class)
    SmartRefreshLayout smartClass;
    @BindView(R.id.class_float)
    FloatingActionButton homeFloat;
    private ArrayList<ClassMaxBean.DataBean> mList;
    private ClassMaxAdapter mClassMaxAdapter;

    @Override
    protected int initLayout() {
        return R.layout.fragment_class;
    }

    @Override
    protected ClassP iniPresenter() {
        return new ClassP();
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        classRlv.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<>();
        mClassMaxAdapter = new ClassMaxAdapter(mList, getContext());
        classRlv.setAdapter(mClassMaxAdapter);
        mClassMaxAdapter.setBaseitemclick(new Baseitemclick() {
            @Override
            public void onitemclick(int position) {
                Intent intent = new Intent(getActivity(), ClassBodyActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);

            }
        });

        smartClass.setRefreshHeader(new DeliveryHeader(getContext()));
        smartClass.setRefreshFooter(new ClassicsFooter(getContext()));
        smartClass.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
            }
        });
        smartClass.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
            }
        });
        homeFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classRlv.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        HideTablayout_Float();
    }

    private void HideTablayout_Float() {

        //滑动recyclerView隐藏tablayout
        classRlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    public void onSussion(ClassMaxBean bean) {
        mList.addAll(bean.getData());
        mClassMaxAdapter.setMlist(mList);
        mClassMaxAdapter.notifyDataSetChanged();
    }

}
