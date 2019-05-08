package com.lcz.wanandroid.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.base.Person;
import com.lcz.wanandroid.utils.MyUtils;
import com.lcz.wanandroid.utils.ToastUtil;

import java.lang.reflect.Method;

public class WebActivity extends AppCompatActivity {

    private WebView mWeb;
    private TextView mTv;
    private Toolbar mWebToobar;
    private ImageView mWebReturn;
    private String mUrl;
    private String mName;
    private String mFenlei;
    private String mAutor;
    private String mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mWebToobar = (Toolbar) findViewById(R.id.web_toobar);
        setSupportActionBar(mWebToobar);
        mWebToobar.setTitle("");
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mName = intent.getStringExtra("name");
        mAutor = intent.getStringExtra("autor");
        mFenlei = intent.getStringExtra("fenlei");
        mTime = intent.getStringExtra("time");
        if (mUrl != null && mName != null) {
            mWeb = (WebView) findViewById(R.id.web);
            mWeb.loadUrl(mUrl);
            mWeb.setWebViewClient(new WebViewClient());
            mTv.setText(mName);
        }


        mWebReturn = (ImageView) findViewById(R.id.web_return);
        mWebReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void Share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain"); // 分享发送的数据类型
        intent.putExtra(Intent.EXTRA_SUBJECT, "max"); // 分享的主题
        intent.putExtra(Intent.EXTRA_TEXT, "因为信任,所以简单！\nBecause of trust, so simple!"); // 分享的内容
        startActivity(Intent.createChooser(intent, "分享"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.web_Share:
                Share();
                break;
            case R.id.web_Plug:
                if (mUrl != null) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(mUrl);
                    intent.setData(content_url);
                    startActivity(intent);
                }
                break;
            case R.id.web_collect:
                if (mAutor != null && mName != null && mFenlei != null && mTime != null && mUrl != null) {
                    Person person = new Person();
                    person.setId(null);
                    person.setAutor(mAutor);
                    person.setName(mName);
                    person.setFenlei(mFenlei);
                    person.setUrl(mUrl);
                    person.setTime(mTime);
                    MyUtils.getmMyUtils().insert(person);
                }else{
                    ToastUtil.showShort("暂时不支持收藏功能呢~");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }
}
