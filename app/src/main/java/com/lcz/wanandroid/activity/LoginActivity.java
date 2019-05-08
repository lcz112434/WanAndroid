package com.lcz.wanandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.activity.login.LoginBean;
import com.lcz.wanandroid.activity.login.LoginP;
import com.lcz.wanandroid.activity.login.LoginV;
import com.lcz.wanandroid.base.BaseActivity;
import com.lcz.wanandroid.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginV, LoginP> implements LoginV {


    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.et_login)
    EditText etLogin;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String mName;
    private String mPsw;

    @Override
    public void onSussion(LoginBean bean) {
        String ret = bean.getRet();
        if (bean.getCode()==200) {
            ToastUtil.showShort("1");
        } else {
            ToastUtil.showShort("2");
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mName = etLogin.getText().toString().trim();
        mPsw = etPsw.getText().toString().trim();
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    protected LoginP Inpresneter() {
        return new LoginP();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.img_return, R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
            case R.id.btn_login:
//                if (mName != null && mPsw != null) {
//                    mpresenter.setData(mName, mPsw);
//                } else {
//                    ToastUtil.showShort("您输入的账号密码不能为空");
//                }
                ToastUtil.showShort("系统更新中... 暂时停用登陆服务 \r\n \r\n 别着急正在努力更新中 (*❦ω❦)");
                break;
            case R.id.btn_register:
                ToastUtil.showShort("系统更新中... 暂时停用注册服务 \r\n \r\n 别着急正在努力更新中 (*❦ω❦)");
                break;
        }
    }
}
