package com.lcz.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.LoginActivity;
import com.lcz.wanandroid.fragment.Home.Bean.HomeBannerBean;
import com.lcz.wanandroid.fragment.Home.Bean.HomeListBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 李承泽 on 2019/4/28.
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private ArrayList<HomeListBean.DataBean.DatasBean> mlist;
    private ArrayList<HomeBannerBean.DataBean> mBanner;
    private Context context;
    private ArrayList<String> mList;

    public void setMlist(ArrayList<HomeListBean.DataBean.DatasBean> mlist) {
        this.mlist = mlist;
    }

    public void setBanner(ArrayList<HomeBannerBean.DataBean> banner) {
        mBanner = banner;
    }

    public HomeAdapter(ArrayList<String> list, ArrayList<HomeListBean.DataBean.DatasBean> mlist, ArrayList<HomeBannerBean.DataBean> banner, Context context) {
        this.mlist = mlist;
        mBanner = banner;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_home_banner, null);
            holder = new ViewHolder(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_home_item, null);
            holder = new ViewHolder2(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;

//            Log.d("home", "onBindViewHolder: "+list.toString());
                viewHolder.baner.setImages(mBanner)
                        .setBannerTitles(mList)
                        .setBannerStyle(BannerConfig.TITLE_BACKGROUND)
                        .setBannerAnimation(Transformer.DepthPage)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                HomeBannerBean.DataBean dataBean = (HomeBannerBean.DataBean) path;
                                String imagePath = dataBean.getImagePath();
                                Glide.with(context).load(imagePath).into(imageView);
                            }
                        }).start();

            viewHolder.baner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (mBannitemclick != null) {
                        mBannitemclick.baneronitemclick(position);
                    }
                }
            });
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            int mposition = position;
            if (mBanner.size() > 0) {
                mposition = position - 1;
            }
            HomeListBean.DataBean.DatasBean datasBean = mlist.get(mposition);
            viewHolder2.autor.setText(datasBean.getAuthor());
            viewHolder2.classify.setText(datasBean.getSuperChapterName() + "/" + datasBean.getChapterName());
            viewHolder2.tv_title.setText(datasBean.getTitle());
            viewHolder2.tv_time.setText(datasBean.getNiceDate());


            if (datasBean.isFresh()) {
                viewHolder2.tv_Xin.setVisibility(View.VISIBLE);
            } else {
                viewHolder2.tv_Xin.setVisibility(View.INVISIBLE);
            }
            if (datasBean.getTags().size() > 0) {
                viewHolder2.tv_Fenlei.setVisibility(View.VISIBLE);
                if (viewHolder2.tv_Xin.getVisibility() == View.INVISIBLE) {

                }
            } else {
                viewHolder2.tv_Fenlei.setVisibility(View.INVISIBLE);
            }
            viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemclick != null) {
                        mItemclick.onitemclick(position);
                    }
                }
            });
            viewHolder2.iv_xin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mBanner.size() > 0) {
            return mlist.size() + 1;
        }
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setlist(ArrayList<String> list) {
        mList = list;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner baner;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.baner = (Banner) rootView.findViewById(R.id.baner);
        }

    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView autor;
        public TextView classify;
        public TextView tv_title;
        public TextView tv_time;
        public TextView tv_Fenlei;
        public TextView tv_Xin;
        public ImageView iv_xin;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.autor = (TextView) rootView.findViewById(R.id.autor);
            this.classify = (TextView) rootView.findViewById(R.id.classify);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_Fenlei = (TextView) rootView.findViewById(R.id.tv_Fenlei);
            this.tv_Xin = (TextView) rootView.findViewById(R.id.tv_Xin);
            this.iv_xin = (ImageView) rootView.findViewById(R.id.iv_xin);
        }
    }

    public interface bannitemclick {
        void baneronitemclick(int position);
    }

    public interface itemclick {
        void onitemclick(int position);
    }

    private bannitemclick mBannitemclick;
    private itemclick mItemclick;

    public void setBannitemclick(bannitemclick bannitemclick) {
        mBannitemclick = bannitemclick;
    }

    public void setItemclick(itemclick itemclick) {
        mItemclick = itemclick;
    }
}
