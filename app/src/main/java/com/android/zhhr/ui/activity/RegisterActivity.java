package com.android.zhhr.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.zhhr.R;
import com.android.zhhr.presenter.RegisterPresenter;
import com.android.zhhr.ui.activity.base.BaseActivity;
import com.android.zhhr.ui.view.IRegisterView;
import com.android.zhhr.utils.IntentUtil;
import com.android.zhhr.utils.ZToast;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {
    @BindView(R.id.et_username)
    EditText username;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.et_password_re)
    EditText passwordRe;
    @BindView(R.id.et_describe)
    EditText describe;
    @BindView(R.id.pb_progress)
    ProgressBar mProgressBar;
    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new RegisterPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.tv_to_login)
    public void toLogin(){
        IntentUtil.toLoginActivity(this);
    }

    @OnClick(R.id.tv_register)
    public void register(){
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter.register(username,password,passwordRe,describe);

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onRegisterSuccess() {
        toLogin();
        finish();
    }

    @Override
    public void onRegisterFailed() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void ShowToast(String t) {
        if(mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(View.GONE);
        }
        ZToast.makeText(RegisterActivity.this,t,1000).show();
    }

    @OnClick(R.id.iv_back_color)
    public void toFinish(){
        finish();
    }

}
