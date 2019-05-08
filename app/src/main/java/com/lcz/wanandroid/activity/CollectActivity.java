package com.lcz.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.adapter.CollectAdapter;
import com.lcz.wanandroid.base.Baseitemclick;
import com.lcz.wanandroid.base.Person;
import com.lcz.wanandroid.utils.CircularAnimUtil;
import com.lcz.wanandroid.utils.MyUtils;

import java.util.List;

public class CollectActivity extends AppCompatActivity {

    /**
     * 收藏
     */
    private TextView mTvToobar;
    private TextView mTvLll;
    private Toolbar mToobar;
    private RecyclerView mRlvCollect;
    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        initData();
    }

    private void initData() {
        final List<Person> people = MyUtils.getmMyUtils().QueryAll();
        CollectAdapter collectAdapter = new CollectAdapter(people, this);
        mRlvCollect.setAdapter(collectAdapter);
        mRlvCollect.setLayoutManager(new LinearLayoutManager(this));
        collectAdapter.setBaseitemclick(new Baseitemclick() {
            @Override
            public void onitemclick(int position) {
                Person person = people.get(position);
                String name = person.getName();
                String url = person.getUrl();
                Intent intent = new Intent(CollectActivity.this, WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mTvToobar = (TextView) findViewById(R.id.tv_toobar);
        mTvLll = (TextView) findViewById(R.id.tv_lll);
        mToobar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(mToobar);
        mRlvCollect = (RecyclerView) findViewById(R.id.rlv_collect);

        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                Intent intent = new Intent(CollectActivity.this, CommonActivity.class);
                CircularAnimUtil.startActivity(this, intent, mTvLll, R.color.blues);
                break;
            case R.id.menu_item2:
                Intent intent2 = new Intent(CollectActivity.this, SearcActivity.class);
                CircularAnimUtil.startActivity(this, intent2, mTvLll, R.color.blues);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
