package com.lcz.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
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
    private int a=0;
    private List<Person> mPeople;
    private CollectAdapter mCollectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        initData();
    }

    private void initData() {
        mPeople = MyUtils.getmMyUtils().QueryAll();
        mCollectAdapter = new CollectAdapter(mPeople, this);
        mRlvCollect.setAdapter(mCollectAdapter);
        mRlvCollect.setLayoutManager(new LinearLayoutManager(this));
        mCollectAdapter.setBaseitemclick(new Baseitemclick() {
            @Override
            public void onitemclick(int position) {
                Person person = mPeople.get(position);
                String name = person.getName();
                String url = person.getUrl();
                Intent intent = new Intent(CollectActivity.this, WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        mCollectAdapter.setLongitenclick(new CollectAdapter.longitenclick() {
            @Override
            public void itemclick(int position) {
                a=position;
            }
        });
         registerForContextMenu(mRlvCollect);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Person person = mPeople.get(a);
                MyUtils.getmMyUtils().Delete(person);
                mPeople.remove(a);
                mCollectAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
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
