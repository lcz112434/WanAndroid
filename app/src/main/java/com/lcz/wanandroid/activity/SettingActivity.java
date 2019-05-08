package com.lcz.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.base.Check;
import com.lcz.wanandroid.utils.ACache;
import com.lcz.wanandroid.utils.ChekedUtils;
import com.lcz.wanandroid.utils.CircularAnimUtil;
import com.lcz.wanandroid.utils.Constants;
import com.lcz.wanandroid.utils.ShareUtil;
import com.lcz.wanandroid.utils.SnackbarUtil;
import com.lcz.wanandroid.utils.ToastUtil;

import java.io.File;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SettingActivity";
    private CheckBox mCheckboxSett1;
    private CheckBox mCheckboxSett2;
    private CheckBox mCheckboxSett3;
    private Check mCheck;
    private boolean mCheceke;
    private ImageView mBackIv;
    /**
     * 收藏
     */
    private TextView mTvToobar;
    private TextView mTvLll;
    private Toolbar mToobar;
    /**
     * 106.81KB
     */
    private TextView mTvCache;
    private LinearLayout mClearLl;
    private File cacheFile;
    private LinearLayout mFankuiLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        initView();
    }

    private void initView() {
        mCheckboxSett1 = (CheckBox) findViewById(R.id.checkbox_sett1);
        mCheckboxSett2 = (CheckBox) findViewById(R.id.checkbox_sett2);
        mCheckboxSett3 = (CheckBox) findViewById(R.id.checkbox_sett3);
        mCheckboxSett2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences none = getSharedPreferences("none", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = none.edit();
                if (isChecked) {
                    edit.putBoolean("checeke", isChecked);
                    mCheck = new Check();
                    mCheck.setId(null);
                    mCheck.setCheckle(isChecked);
                    ChekedUtils.getmMyUtils().insert(mCheck);
//                    Log.d(TAG, "mCheckboxSett2 onCheckedChanged:      "+"yes");
                } else {
                    edit.putBoolean("checeke", isChecked);
                    mCheck.setId(null);
                    mCheck.setCheckle(isChecked);
                    ChekedUtils.getmMyUtils().UpData(mCheck);
//                    Log.d(TAG, "mCheckboxSett2 onCheckedChanged:      "+"no");
                }
                edit.commit();
            }
        });

        SharedPreferences none = getSharedPreferences("none", Context.MODE_PRIVATE);
        mCheceke = none.getBoolean("checeke", false);
        Log.d(TAG, "initView: " + mCheceke);


        if (mCheceke) {
            mCheckboxSett2.setChecked(true);
        } else {
            mCheckboxSett2.setChecked(false);
        }


        mCheckboxSett3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showLong("暂不支持该功能,如需帮助请反馈");
                } else {

                }
            }
        });

        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mTvToobar = (TextView) findViewById(R.id.tv_toobar);
        mTvLll = (TextView) findViewById(R.id.tv_lll);
        mToobar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(mToobar);
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent intent = getIntent();
                Check query = ChekedUtils.getmMyUtils().Query();
                boolean checkle = query.getCheckle();
                intent.putExtra("chec",checkle);
                setResult(3,intent);*/
                finish();
            }
        });


        cacheFile = new File(Constants.PATH_CACHE);
        mTvCache = (TextView) findViewById(R.id.tv_cache);
        mTvCache.setText(ACache.getCacheSize(cacheFile));
        mClearLl = (LinearLayout) findViewById(R.id.clear_ll);
        mClearLl.setOnClickListener(this);
        mFankuiLl = (LinearLayout) findViewById(R.id.fankui_ll);
        mFankuiLl.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Intent intent = new Intent(SettingActivity.this, CommonActivity.class);
                CircularAnimUtil.startActivity(this, intent, mTvLll, R.color.blues);
                break;
            case R.id.menu_item2:
                Intent intent2 = new Intent(SettingActivity.this, SearcActivity.class);
                CircularAnimUtil.startActivity(this, intent2, mTvLll, R.color.blues);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.clear_ll:
                clearCache();
                break;
            case R.id.fankui_ll:
                ShareUtil.sendEmail(this, "取消");
                break;
        }
    }

    private void clearCache() {
        ACache.deleteDir(cacheFile);
        mTvCache.setText(ACache.getCacheSize(cacheFile));
    }
}
