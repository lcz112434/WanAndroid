package com.lcz.wanandroid.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.Serac.SeracBean;
import com.lcz.wanandroid.activity.Serac.SeracP;
import com.lcz.wanandroid.activity.Serac.SeracV;
import com.lcz.wanandroid.base.BaseActivity;
import com.lcz.wanandroid.utils.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearcActivity extends BaseActivity<SeracV, SeracP> implements View.OnClickListener, SeracV {

    @BindView(R.id.serac_rlv)
    FlowLayout seracRlv;
    private ImageView mSearReturn;
    private Toolbar mSeracToobar;

    /**
     * 搜索
     */
    private Button mSercBtn;
    private ArrayList<SeracBean.DataBean> mList;


    @Override
    protected SeracP Inpresneter() {
        return new SeracP();
    }

    @Override
    protected void initView() {
        mSearReturn = (ImageView) findViewById(R.id.sear_return);
        mSeracToobar = (Toolbar) findViewById(R.id.serac_toobar);
        mSeracToobar.setTitle("");
        setSupportActionBar(mSeracToobar);
        mSercBtn = (Button) findViewById(R.id.serc_btn);
        mSercBtn.setOnClickListener(this);

        mSearReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        mpresenter.setData();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_searc;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.serc_btn:

                break;
        }
    }

    @Override
    public void onSussion(final SeracBean bean) {
//        mList.addAll(bean.getData());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<SeracBean.DataBean> data = bean.getData();
                for (int i = 0; i <data.size() ; i++) {
                    String name = data.get(i).getName();
                    TextView textv = (TextView) LayoutInflater.from(SearcActivity.this).inflate(R.layout.layout_tv, null);
                    textv.setTop(18);
                    if (i % 3 == 0) {
                        textv.setBackground(getResources().getDrawable(R.drawable.shape_grale));
                    } else if (i % 2 == 0) {
                        textv.setBackground(getResources().getDrawable(R.drawable.shape_redddd));
                    }
                    textv.setText(name);
                    seracRlv.addView(textv);
                }
            }
        });
    }
}
