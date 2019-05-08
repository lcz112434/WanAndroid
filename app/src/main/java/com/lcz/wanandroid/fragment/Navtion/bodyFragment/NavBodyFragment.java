package com.lcz.wanandroid.fragment.Navtion.bodyFragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.adapter.NavBodyAdapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.Navtion.Bean.NavBean;
import com.lcz.wanandroid.fragment.Navtion.NavtionP;
import com.lcz.wanandroid.fragment.Navtion.NavtionV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class NavBodyFragment extends BaseFragment<NavtionV, NavtionP> implements NavtionV {

    @BindView(R.id.nav_body_rlv)
    RecyclerView navBodyRlv;
    Unbinder unbinder;
    private int id;
    private ArrayList<NavBean.DataBean> mList;
    private NavBodyAdapter mNavBodyAdapter;

    public NavBodyFragment(int cid) {
        this.id = cid;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_nav_body;
    }

    @Override
    protected NavtionP iniPresenter() {
        return new NavtionP();
    }

    @Override
    public void initView() {
        super.initView();
        navBodyRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mNavBodyAdapter = new NavBodyAdapter(id,mList, getContext());
        navBodyRlv.setAdapter(mNavBodyAdapter);
        Log.d("NavBodyFragment", "initView: "+id);
    }


    @Override
    public void initData() {
        super.initData();
        mpresenter.setData();
    }


    @Override
    public void onSussion(NavBean bean) {
        mList.addAll(bean.getData());
        mNavBodyAdapter.setList(mList);
        mNavBodyAdapter.notifyDataSetChanged();
    }
}
