package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.fragment.Class.Bean.ClassMaxBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李承泽 on 2019/5/3.
 */
public class ClassMaxAdapter extends RecyclerView.Adapter {
    private ArrayList<ClassMaxBean.DataBean> mlist;
    private Context context;

    public void setMlist(ArrayList<ClassMaxBean.DataBean> mlist) {
        this.mlist = mlist;
    }

    public ClassMaxAdapter(ArrayList<ClassMaxBean.DataBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_class_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ClassMaxBean.DataBean dataBean = mlist.get(position);

        viewHolder.tvFenlei.setText(dataBean.getName());
        List<ClassMaxBean.DataBean.ChildrenBean> children = dataBean.getChildren();
        StringBuffer stringBuffer = new StringBuffer();
        for (ClassMaxBean.DataBean.ChildrenBean child : children) {
            stringBuffer.append(child.getName() + " ");
        }
        viewHolder.tvOther.setText(stringBuffer.toString());

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
        return mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_Fenlei)
        TextView tvFenlei;
        @BindView(R.id.tv_other)
        TextView tvOther;
        @BindView(R.id.iv_some)
        ImageView ivSome;

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
