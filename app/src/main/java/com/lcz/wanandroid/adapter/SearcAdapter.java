package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lcz.wanandroid.activity.Serac.SeracBean;

import java.util.ArrayList;

/**
 * Created by 李承泽 on 2019/5/5.
 */
public class SearcAdapter extends RecyclerView.Adapter {
    private ArrayList<SeracBean.DataBean> mlist;
    private Context context;

    public void setMlist(ArrayList<SeracBean.DataBean> mlist) {
        this.mlist = mlist;
    }

    public SearcAdapter(ArrayList<SeracBean.DataBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
