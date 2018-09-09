package com.ifree.robot.salesrobotmarket.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.service.entity.LoginEntity;
import com.ifree.robot.salesrobotmarket.service.presenter.LoginPresenter;
import com.ifree.robot.salesrobotmarket.service.view.LoginView;
import com.ifree.robot.salesrobotmarket.utils.SPUtil;
import com.ifree.robot.salesrobotmarket.utils.StatusBarUtil;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class LoginActivity extends AppCompatActivity {
    private LoginPresenter mLoginPresenter;
    @BindView(R.id.et_user_account)
    EditText mAccount;
    @BindView(R.id.et_user_password)
    EditText mPassword;
    @BindView(R.id.tv_user_login)
    TextView mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.fullScreen(LoginActivity.this);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mLoginPresenter = new LoginPresenter(this);

    }

    @OnClick({R.id.tv_user_login})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_user_login:
                String phone = mAccount.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (phone.isEmpty()){
                    ToastUtils.makeText(LoginActivity.this,"请输入账号");
                    return;
                }
                if (password.isEmpty()){
                    ToastUtils.makeText(LoginActivity.this,"请输入密码");
                    return;
                }
                submit(phone,password);
                break;
        }
    }

    private void submit(String phone, String password) {
        String index = "18337125295";
        mLoginPresenter.onCreate();
        mLoginPresenter.attachView(mLoginView);
        mLoginPresenter.getQuestLogin(phone,password,index,"加载中...");
    }

    private LoginView mLoginView = new LoginView() {
        @Override
        public void onSuccess(LoginEntity mLoginEntity) {
            if (mLoginEntity.getResultCode().equals("1")){
                ToastUtils.makeText(LoginActivity.this,mLoginEntity.getMsg());
                return;
            }
            SPUtil.putString(LoginActivity.this,"storeId",mLoginEntity.getData().getStoreId()+"");
            MyApplication.openActivity(LoginActivity.this,HotCarSelectActivity.class);
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(LoginActivity.this,result);
        }
    };
}
