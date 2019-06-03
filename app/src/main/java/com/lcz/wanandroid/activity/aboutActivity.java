package com.lcz.wanandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcz.wanandroid.R;
import com.lcz.wanandroid.utils.ToastUtil;

public class aboutActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请作者吃糖 ^-^
     */
    private TextView mTvWechat;
    private ImageView mIvWechat;
    private LinearLayout mAboutLl;
    private ImageView mBackIv;
    private Toolbar mToobarAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();

    }

    private void initView() {
        mTvWechat = (TextView) findViewById(R.id.tv_wechat);
        mTvWechat.setOnClickListener(this);
        mIvWechat = (ImageView) findViewById(R.id.iv_wechat);
        mIvWechat.setOnClickListener(this);
        mAboutLl = (LinearLayout) findViewById(R.id.about_ll);
        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mToobarAbout = (Toolbar) findViewById(R.id.toobar_about);
        setSupportActionBar(mToobarAbout);
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_wechat:
                inipopp();
                break;
            case R.id.iv_wechat:
                ToastUtil.showShort("修改冲突咯！");
                inipopp();
                break;
        }
    }

    private void inipopp() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popwindows_about, null);
        final PopupWindow popupWindow = new PopupWindow(inflate,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAtLocation(mAboutLl, Gravity.CENTER, 0, 0);
        ImageView iv = inflate.findViewById(R.id.pop_iv);
        Glide.with(this).load(R.drawable.lczwechat).into(iv);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
