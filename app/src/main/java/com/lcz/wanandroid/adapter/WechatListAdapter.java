package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.LoginActivity;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.fragment.WeChat.Bean.WechatListBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public class WechatListAdapter extends RecyclerView.Adapter {
    private ArrayList<WechatListBean.DataBean.DatasBean> mList;
    private Context context;
    private String name;


    public void setList(ArrayList<WechatListBean.DataBean.DatasBean> list) {
        mList = list;
    }

    public WechatListAdapter(ArrayList<WechatListBean.DataBean.DatasBean> list, Context context, String name) {
        mList = list;
        this.context = context;
        this.name = name;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_wechat_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        WechatListBean.DataBean.DatasBean datasBean = mList.get(position);
        viewHolder.autor.setText(datasBean.getAuthor());
        viewHolder.classify.setText(datasBean.getSuperChapterName()+"/"+name);
        viewHolder.tvTime.setText(datasBean.getNiceDate());
        viewHolder.tvTitle.setText(datasBean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBaseitemclick!=null){
                    mBaseitemclick.onitemclick(position);
                }
            }
        });
        viewHolder.iv_xin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.autor)
        TextView autor;
        @BindView(R.id.classify)
        TextView classify;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_xin)
        ImageView iv_xin;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private Baseitemclick mBaseitemclick;

    public void setBaseitemclick(Baseitemclick baseitemclick) {
        mBaseitemclick = baseitemclick;
    }
}
