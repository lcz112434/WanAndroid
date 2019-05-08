package com.lcz.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.common.CommP;
import com.lcz.wanandroid.activity.common.CommV;
import com.lcz.wanandroid.activity.common.commBean;
import com.lcz.wanandroid.base.BaseActivity;
import com.lcz.wanandroid.utils.FlowLayout;

import java.util.List;

public class CommonActivity extends BaseActivity<CommV, CommP> implements CommV {


    private ImageView mIvReturn;
    private Toolbar mCommToobar;
    private FlowLayout mFlow;
    private int theme;

    @Override
    protected CommP Inpresneter() {
        return new CommP();
    }

    @Override
    protected void initView() {
        mIvReturn = (ImageView) findViewById(R.id.iv_return);
        mIvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCommToobar = (Toolbar) findViewById(R.id.comm_toobar);
        mCommToobar.setTitle("");
        setSupportActionBar(mCommToobar);
        mFlow = (FlowLayout) findViewById(R.id.flow);


    }

    @Override
    protected void initData() {
        super.initData();
        mpresenter.setData();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_common;
    }

    @Override
    public void onSussion(commBean bean) {
        if (bean.getData().size() > 0) {
            final List<commBean.DataBean> data = bean.getData();
            for (int i = 0; i < data.size(); i++) {
                String name = data.get(i).getName();
                TextView textv = (TextView) LayoutInflater.from(this).inflate(R.layout.layout_tv, null);
                textv.setTop(18);
                if (i % 3 == 0) {
                    textv.setBackground(getResources().getDrawable(R.drawable.shape_grale));
                } else if (i % 2 == 0) {
                    textv.setBackground(getResources().getDrawable(R.drawable.shape_redddd));
                }

                final int finalI = i;
                textv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CommonActivity.this, WebActivity.class);
                        commBean.DataBean dataBean = data.get(finalI);
                        String link = dataBean.getLink();
                        String name1 = dataBean.getName();
                        if (link != null && name1 != null) {

                            intent.putExtra("url", link);
                            intent.putExtra("name", name1);
                            startActivity(intent);

                        }
                    }
                });
                textv.setText(name);
                mFlow.addView(textv);
            }
        }
    }
}
