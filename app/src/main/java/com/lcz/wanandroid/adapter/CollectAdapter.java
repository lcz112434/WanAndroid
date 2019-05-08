package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.base.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李承泽 on 2019/5/6.
 */
public class CollectAdapter extends RecyclerView.Adapter {
    private List<Person> mList;
    private Context context;

    public CollectAdapter(List<Person> list, Context context) {
        mList = list;
        this.context = context;
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
        ViewHolder viewHolder = (ViewHolder) holder;
        Person person = mList.get(position);
        viewHolder.autor.setText(person.getAutor());
        viewHolder.tvTitle.setText(person.getName());
        viewHolder.tvTime.setText(person.getTime());
        viewHolder.classify.setText(person.getFenlei());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBaseitemclick!=null){
                    mBaseitemclick.onitemclick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mLongitenclick!=null){
                    mLongitenclick.itemclick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.autor)
        TextView autor;
        @BindView(R.id.classify)
        TextView classify;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private Baseitemclick mBaseitemclick;

    public void setBaseitemclick(Baseitemclick baseitemclick) {
        mBaseitemclick = baseitemclick;
    }

    public interface  longitenclick{
        void itemclick(int position);
    }
    private longitenclick mLongitenclick;

    public void setLongitenclick(longitenclick longitenclick) {
        mLongitenclick = longitenclick;
    }
}
