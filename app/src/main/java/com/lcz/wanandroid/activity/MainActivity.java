package com.lcz.wanandroid.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.fragment.Class.ClassFragment;
import com.lcz.wanandroid.fragment.Home.HomeFragment;
import com.lcz.wanandroid.fragment.Navtion.NavigationFragment;
import com.lcz.wanandroid.fragment.Project.ProjectFragment;
import com.lcz.wanandroid.fragment.WeChat.WeChatFragment;
import com.lcz.wanandroid.fragment.setting.SettingFragment;
import com.lcz.wanandroid.utils.CircularAnimUtil;
import com.lcz.wanandroid.utils.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity11111";
    /**
     * 首页
     */
    private TextView mTvToobar;
    private Toolbar mToobar;
    /**
     * 首页
     */
    private RadioButton mRbHome;
    /**
     * 知识体系
     */
    private RadioButton mRbClass;
    /**
     * 公众号
     */
    private RadioButton mRbWeChat;
    /**
     * 导航
     */
    private RadioButton mRbNavigation;
    /**
     * 项目
     */
    private RadioButton mRbProject;
    private RadioGroup mRg;
    private NavigationView mNav;
    private DrawerLayout mDl;
    private FrameLayout mFly;
    private HomeFragment mHomeFragment;
    private WeChatFragment mWeChatFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    private ClassFragment mClassFragment;
    private LinearLayout mLl;

    private TextView mTvLll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //asssss
        mHomeFragment = new HomeFragment();
        mWeChatFragment = new WeChatFragment();
        mNavigationFragment = new NavigationFragment();
        mProjectFragment = new ProjectFragment();
        mClassFragment = new ClassFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fly, mHomeFragment);
        transaction.add(R.id.fly, mClassFragment);
        transaction.add(R.id.fly, mWeChatFragment);
        transaction.add(R.id.fly, mNavigationFragment);
        transaction.add(R.id.fly, mProjectFragment);
        transaction.show(mHomeFragment).hide(mClassFragment).hide(mWeChatFragment)
                .hide(mNavigationFragment).hide(mProjectFragment).commit();
    }

    private void initView() {
        mTvToobar = (TextView) findViewById(R.id.tv_toobar);
        mToobar = (Toolbar) findViewById(R.id.toobar);
        mToobar.setTitle("");
        setSupportActionBar(mToobar);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mNav = (NavigationView) findViewById(R.id.nav);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mFly = (FrameLayout) findViewById(R.id.fly);
        mRbHome = (RadioButton) findViewById(R.id.rb_home);
        mRbClass = (RadioButton) findViewById(R.id.rb_class);
        mRbWeChat = (RadioButton) findViewById(R.id.rb_WeChat);
        mRbNavigation = (RadioButton) findViewById(R.id.rb_navigation);
        mRbProject = (RadioButton) findViewById(R.id.rb_project);
        mRg.setVisibility(View.VISIBLE);


        View view = mNav.getHeaderView(0);
        TextView tvlogin = view.findViewById(R.id.tv_login);
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToobar, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        mNav.setItemIconTintList(null);
        initLinster();

        mLl = (LinearLayout) findViewById(R.id.ll);
        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mLl.setX(mNav.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        mTvLll = (TextView) findViewById(R.id.tv_lll);
        float scaleX = mFly.getScaleX();
        float scaleY = mFly.getScaleY();
        Log.d(TAG, "scaleX: " + scaleX + ",scaleY" + scaleY);


//        mNscll = (NestedScrollView) findViewById(R.id.Nscll);
    }

    private void initLinster() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.item_wanAndorid:
                        getSupportFragmentManager().beginTransaction()
                                .show(mHomeFragment)
                                .hide(mClassFragment)
                                .hide(mWeChatFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .commit();
                        mRbHome.setChecked(true);
                        mTvToobar.setText("首页");
                        mRg.setVisibility(View.VISIBLE);
                        mDl.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.item_collect:
                        Intent intent = new Intent(MainActivity.this, CollectActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_setting:
                        Intent intent1 = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.item_about:
                        Intent intent2 = new Intent(MainActivity.this, aboutActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });
        mRbHome.setChecked(true);
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        mTvToobar.setText("首页");
                        getSupportFragmentManager().beginTransaction()
                                .show(mHomeFragment)
                                .hide(mClassFragment)
                                .hide(mWeChatFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .commit();
                        break;
                    case R.id.rb_class:
                        mTvToobar.setText("知识体系");
                        getSupportFragmentManager().beginTransaction()
                                .show(mClassFragment)
                                .hide(mHomeFragment)
                                .hide(mWeChatFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .commit();
                        break;
                    case R.id.rb_WeChat:
                        mTvToobar.setText("公众号");
                        getSupportFragmentManager().beginTransaction()
                                .show(mWeChatFragment)
                                .hide(mClassFragment)
                                .hide(mHomeFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .commit();
                        break;
                    case R.id.rb_navigation:
                        mTvToobar.setText("导航");
                        getSupportFragmentManager().beginTransaction()
                                .show(mNavigationFragment)
                                .hide(mClassFragment)
                                .hide(mWeChatFragment)
                                .hide(mHomeFragment)
                                .hide(mProjectFragment)
                                .commit();
                        break;
                    case R.id.rb_project:
                        mTvToobar.setText("项目");
                        getSupportFragmentManager().beginTransaction()
                                .show(mProjectFragment)
                                .hide(mClassFragment)
                                .hide(mWeChatFragment)
                                .hide(mNavigationFragment)
                                .hide(mHomeFragment)
                                .commit();
                        break;
                }
            }
        });
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
                Intent intent = new Intent(MainActivity.this, CommonActivity.class);
                CircularAnimUtil.startActivity(this, intent, mTvLll, R.color.blues);
                break;
            case R.id.menu_item2:
                Intent intent2 = new Intent(MainActivity.this, SearcActivity.class);
                CircularAnimUtil.startActivity(this, intent2, mTvLll, R.color.blues);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("确定退出玩Android吗")
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
        return false;
    }

}
