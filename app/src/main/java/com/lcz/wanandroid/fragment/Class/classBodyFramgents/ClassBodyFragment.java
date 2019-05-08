package com.lcz.wanandroid.fragment.Class.classBodyFramgents;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.WebActivity;
import com.lcz.wanandroid.adapter.ClassListDapter;
import com.lcz.wanandroid.base.BaseFragment;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.fragment.Class.Bean.ClassListBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ClassBodyFragment extends BaseFragment<ClassBodyListV, ClassBodyListP> implements ClassBodyListV {

    int id;
    @BindView(R.id.body_rlv)
    RecyclerView bodyRlv;
    private ArrayList<ClassListBean.DataBean.DatasBean> mList;
    private ClassListDapter mClassListDapter;

    public ClassBodyFragment(int id) {
        this.id = id;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_class_body;
    }

    @Override
    protected ClassBodyListP iniPresenter() {
        return new ClassBodyListP();
    }

    @Override
    public void initView() {
        super.initView();
        bodyRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mClassListDapter = new ClassListDapter(mList, getContext());
        bodyRlv.setAdapter(mClassListDapter);
        mClassListDapter.setBaseitemclick(new Baseitemclick() {
            @Override
            public void onitemclick(int position) {
                ClassListBean.DataBean.DatasBean datasBean = mList.get(position);
                String link = datasBean.getLink();
                String title = datasBean.getTitle();

                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", link);
                intent.putExtra("name", title);
                intent.putExtra("autor",datasBean.getAuthor());
                intent.putExtra("fenlei",datasBean.getSuperChapterName()+"/"+datasBean.getChapterName());
                intent.putExtra("time",datasBean.getNiceDate());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mpresenter.setData(id);
    }

    @Override
    public void onSussion(ClassListBean bean) {
        mList.addAll(bean.getData().getDatas());
        mClassListDapter.setList(mList);
        mClassListDapter.notifyDataSetChanged();
    }


}
