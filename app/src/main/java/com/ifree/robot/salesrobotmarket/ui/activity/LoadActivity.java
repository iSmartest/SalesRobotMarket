package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoadCodeEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.LoadCodePresenter;
import com.ifree.robot.salesrobotmarket.mvp.presenter.LoadPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.LoadCodeView;
import com.ifree.robot.salesrobotmarket.mvp.view.LoadView;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.RegexpUtils;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class LoadActivity extends BaseActivity {
    private LoadCodePresenter mLoadCodePresenter;
    private LoadPresenter mLoadPresenter;
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_vcode)
    EditText etVcode;
    @BindView(R.id.ll_load)
    LinearLayout llLoad;
    @BindView(R.id.tv_vcode)
    TextView tvVcode;
    @BindView(R.id.tv_load)
    TextView tvLoad;
    private String sessionId = "";
    private TimeCount time;
    private int loadTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_load;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(1);
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        mLoadCodePresenter = new LoadCodePresenter(context);
        mLoadPresenter = new LoadPresenter(context);
        loadTag = getIntent().getIntExtra(Constant.LOAD_TAG, 0);
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.tv_vcode, R.id.tv_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_vcode:
                String phone = etPhone.getText().toString().trim();
                if (RegexpUtils.isMobileNO(phone)) {
                    time.start();
                    getVerificationCode(phone);
                } else {
                    ToastUtils.makeText(context, "请输入正确的电话号码");
                }
                break;
            case R.id.tv_load:
                String phone1 = etPhone.getText().toString().trim();
                String vcode = etVcode.getText().toString().trim();
                if (RegexpUtils.isMobileNO(phone1)) {
                    if (!TextUtils.isEmpty(vcode)) {
                        submitLoad(phone1, vcode);
                    } else {
                        ToastUtils.makeText(context, "请输入验证码");
                    }
                } else {
                    ToastUtils.makeText(context, "请输入正确的电话号码");
                }
        }
    }

    private void submitLoad(String phone1, String vcode) {

        mLoadPresenter.onCreate();
        mLoadPresenter.attachView(mLoadView);
        mLoadPresenter.getQuestLoadLogin(storeId,phone1,vcode,sessionId,"正在登录...");
    }

    private LoadView mLoadView = new LoadView() {
        @Override
        public void onSuccess(LoadEntity mLoadEntity) {
            if (mLoadEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mLoadEntity.getMsg());
                return;
            }
            switch (loadTag) {
                case 1:
                    Intent vipintent = new Intent(context, VipActivity.class);
                    vipintent.putExtra(Constant.LOADDATA, mLoadEntity.getData());
                    startActivity(vipintent);
                    finish();
                    break;
                case 2:
                    Intent inspectintent = new Intent(context, AfterSaleActivity.class);
                    inspectintent.putExtra(Constant.LOADDATA, mLoadEntity.getData());
                    startActivity(inspectintent);
                    finish();
                    break;
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };


    private void getVerificationCode(String phone) {
        mLoadCodePresenter.onCreate();
        mLoadCodePresenter.attachView(mLoadCodeView);
        mLoadCodePresenter.getQuestVerificationCode(storeId,phone,"正在获取...");
    }

    private LoadCodeView mLoadCodeView = new LoadCodeView() {
        @Override
        public void onSuccess(LoadCodeEntity mLoadCodeEntity) {
            if (mLoadCodeEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mLoadCodeEntity.getMsg());
                return;
            }
            sessionId = mLoadCodeEntity.getData().getSessionId();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvVcode.setClickable(false);
            tvVcode.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            tvVcode.setText("重新获取");
            tvVcode.setClickable(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
