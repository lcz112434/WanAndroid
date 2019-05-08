package com.lcz.wanandroid.fragment.Class.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.adapter.ClassBodyPageAdapter;
import com.lcz.wanandroid.base.BaseActivity;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;
import com.lcz.wanandroid.fragment.Class.classBodyFramgents.ClassBodyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassBodyActivity extends BaseActivity<ClassBodyV, ClassBodyP> implements ClassBodyV {


    @BindView(R.id.body_return)
    ImageView bodyReturn;
    @BindView(R.id.tv_toobar)
    TextView tvToobar;
    @BindView(R.id.toobar_body)
    Toolbar toobarBody;
    @BindView(R.id.body_tab)
    TabLayout bodyTab;
    @BindView(R.id.body_vp)
    ViewPager bodyVp;
    private int mPosition;

    @Override
    protected ClassBodyP Inpresneter() {
        return new ClassBodyP();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_class_body;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("id", 0);
        toobarBody.setTitle("");
        setSupportActionBar(toobarBody);


    }


    @Override
    protected void initData() {
        super.initData();
        mpresenter.setData2();
    }


    @Override
    public void onSussionMax(ClassMaxBean bean) {
        ClassMaxBean.DataBean dataBean = bean.getData().get(mPosition);
        final String name = dataBean.getName();

        final List<ClassMaxBean.DataBean.ChildrenBean> children = dataBean.getChildren();
        final ArrayList<BaseFragment> baseFragments = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            int id = children.get(i).getId();
            baseFragments.add(new ClassBodyFragment(id));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvToobar.setText(name);
                ClassBodyPageAdapter classBodyPageAdapter = new ClassBodyPageAdapter(getSupportFragmentManager(), children, baseFragments);
                bodyVp.setAdapter(classBodyPageAdapter);
                bodyTab.setupWithViewPager(bodyVp);
            }
        });
    }


    @OnClick(R.id.body_return)
    public void onViewClicked() {
        finish();
    }
}
