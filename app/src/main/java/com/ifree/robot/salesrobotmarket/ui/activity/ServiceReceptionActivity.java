package com.ifree.robot.salesrobotmarket.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
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

public class ServiceReceptionActivity extends BaseActivity {
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.tv_service3)
    TextView tvService3;
    @BindView(R.id.tv_service2)
    TextView tvService2;
    @BindView(R.id.tv_service1)
    TextView tvService1;
    @BindView(R.id.tv_service5)
    TextView tvService5;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_reception;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(1);
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.transparent));
    }

    @OnClick({R.id.tv_service3, R.id.tv_service2, R.id.tv_service1,  R.id.tv_service5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_service1://跳转接待表页面
                MyApplication.openActivity(context,QuestionActivity.class);
                break;
            case R.id.tv_service2://跳转产品介绍页面
                MyApplication.openActivity(context,CommodityIntroduceActivity.class);
                break;
            case R.id.tv_service3://跳转呼叫服务页面
                ToastUtils.makeText(context,"暂未开通");
                break;
            case R.id.tv_service5://跳转产品展示页面
                MyApplication.openActivity(context,CommodityShowActivity.class);
                break;
        }
    }
}
