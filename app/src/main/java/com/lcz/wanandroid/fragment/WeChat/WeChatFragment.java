package com.lcz.wanandroid.fragment.WeChat;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.adapter.WechatAdapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatTabBean;
import com.lcz.wanandroid.fragment.WeChat.WechatBodyFragment.WechatListFragment;
import com.lcz.wanandroid.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends BaseFragment<WeChatV, WechatP> implements WeChatV {

    private static final String TAG = "WeChatFragment";
    @BindView(R.id.wechat_tab)
    TabLayout wechatTab;
    @BindView(R.id.wechat_scv)
    SearchView wechatScv;
    @BindView(R.id.btn_wechat)
    Button btnWechat;
    @BindView(R.id.wechat_vp)
    ViewPager wechatVp;

    @Override
    protected int initLayout() {
        return R.layout.fragment_we_chat;
    }

    @Override
    protected WechatP iniPresenter() {
        return new WechatP();
    }

    @Override
    public void initView() {
        super.initView();

        wechatScv.setQueryHint("鸿洋带你发现更多干货");
        wechatScv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                Log.d(TAG, "onQueryTextSubmit: " + query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                btnWechat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showShort(newText);
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mpresenter.setData();
    }

    @Override
    public void onSussion(WechatTabBean bean) {
        final List<WechatTabBean.DataBean> data = bean.getData();
        final ArrayList<BaseFragment> baseFragments = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            int id = data.get(i).getId();
            String name = data.get(i).getName();
            baseFragments.add(new WechatListFragment(id, name));
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WechatAdapter wechatAdapter = new WechatAdapter(getChildFragmentManager(), data, baseFragments);
                wechatVp.setAdapter(wechatAdapter);

                wechatTab.setupWithViewPager(wechatVp);

                wechatTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        wechatScv.setQueryHint(tab.getText() + "带你发现更多干货");

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        });
    }


}
