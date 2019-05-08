package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.WebActivity;
import com.lcz.wanandroid.fragment.Navtion.Bean.NavBean;
import com.lcz.wanandroid.utils.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public class NavBodyAdapter extends RecyclerView.Adapter {
    private List<NavBean.DataBean> mList;
    private Context context;
    private int id;

    public void setList(List<NavBean.DataBean> list) {
        mList = list;
    }

    public NavBodyAdapter(List<NavBean.DataBean> list, Context context) {
        mList = list;
        this.context = context;
    }

    public NavBodyAdapter(int id, ArrayList<NavBean.DataBean> list, Context context) {
        this.id = id;
        mList = list;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_nav_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        NavBean.DataBean dataBean = mList.get(position);
        FlowLayout flow = viewHolder.flow;
        flow.removeAllViews();
//        if (id == dataBean.getCid()) {
            viewHolder.navTv.setText(dataBean.getName());
            final List<NavBean.DataBean.ArticlesBean> articles = dataBean.getArticles();
            for (int i = 0; i < articles.size(); i++) {
//                if (id == articles.get(i).getChapterId()) {
                    TextView textview = (TextView) LayoutInflater.from(context).inflate(R.layout.layout_commtv, null);
                    textview.setText(articles.get(i).getTitle());
                    flow.addView(textview);
//                }
//            }
                if (i % 3 == 0) {
                    textview.setBackground(context.getResources().getDrawable(R.drawable.shape_ge));
                } else if (i % 2 == 0) {
                    textview.setBackground(context.getResources().getDrawable(R.drawable.shape_redddd));
                }
                final int finalI = i;
                textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        String link = articles.get(finalI).getLink();
                        intent.putExtra("url", link);
                        intent.putExtra("name", articles.get(finalI).getTitle());
                        context.startActivity(intent);
                    }
                });
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nav_tv)
        TextView navTv;
        @BindView(R.id.flow)
        FlowLayout flow;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
