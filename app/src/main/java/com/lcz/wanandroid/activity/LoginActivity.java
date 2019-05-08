package com.lcz.wanandroid.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lcz.wanandroid.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgReturn;
    /**
     * 请输入用户名
     */
    private EditText mEtLogin;
    /**
     * 请输入密码
     */
    private EditText mEtPsw;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 注册
     */
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }


    private void initView() {
        mImgReturn = (ImageView) findViewById(R.id.img_return);
        mImgReturn.setOnClickListener(this);
        mEtLogin = (EditText) findViewById(R.id.et_login);
        mEtPsw = (EditText) findViewById(R.id.et_psw);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_return:
                finish();
                break;
            case R.id.btn_login:
                break;
            case R.id.btn_register:
                break;
        }
    }
}
