package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lcz.wanandroid.R;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.fragment.Project.Bean.ProjectListBean;

import java.util.ArrayList;

/**
 * Created by 李承泽 on 2019/5/3.
 */
public class ProjectListadapter extends RecyclerView.Adapter {
    private ArrayList<ProjectListBean.DataBean.DatasBean> mList;
    private Context context;

    public void setList(ArrayList<ProjectListBean.DataBean.DatasBean> list) {
        mList = list;
    }

    public ProjectListadapter( ArrayList<ProjectListBean.DataBean.DatasBean> list, Context context) {
        mList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_project_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        SharedPreferences none = context.getSharedPreferences("none", Context.MODE_PRIVATE);
        boolean checeke = none.getBoolean("checeke", false);
        ViewHolder viewHolder = (ViewHolder) holder;
        ProjectListBean.DataBean.DatasBean datasBean = mList.get(position);
        viewHolder.tv_autor.setText(datasBean.getAuthor());
        if(checeke){
            Glide.with(context).load(R.drawable.icon_wan).into(viewHolder.pro_iv);
        }else{
            Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolder.pro_iv);
        }
        viewHolder.tv_body.setText(datasBean.getDesc());
        viewHolder.tv_time.setText(datasBean.getNiceDate());
        viewHolder.tv_title.setText(datasBean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBaseitemclick != null) {
                    mBaseitemclick.onitemclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView pro_iv;
        public TextView tv_title;
        public TextView tv_body;
        public TextView tv_time;
        public TextView tv_autor;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.pro_iv = (ImageView) rootView.findViewById(R.id.pro_iv);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_body = (TextView) rootView.findViewById(R.id.tv_body);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_autor = (TextView) rootView.findViewById(R.id.tv_autor);
        }
    }

    private Baseitemclick mBaseitemclick;

    public void setBaseitemclick(Baseitemclick baseitemclick) {
        mBaseitemclick = baseitemclick;
    }
}
