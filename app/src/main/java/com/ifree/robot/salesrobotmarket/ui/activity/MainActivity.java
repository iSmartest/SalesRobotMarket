package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.FaceRecognitionService;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.ll_main_hot)
    LinearLayout ivHot;
    @BindView(R.id.ll_main_reception)
    LinearLayout ivReception;
    @BindView(R.id.ll_after_sale)
    LinearLayout ivAfterSale;
    @BindView(R.id.ll_main_used_car)
    LinearLayout ivUsedCar;
    @BindView(R.id.ll_main_vip)
    LinearLayout ivVip;
    @BindView(R.id.ll_main_reservation)
    LinearLayout ivReservation;
    @BindView(R.id.ll_main_consultation)
    LinearLayout ivConsultation;
    @BindView(R.id.tv_linshi)
    TextView tvTemp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    //8个控件的点击事件跳转页面
    @OnClick({R.id.ll_main_hot, R.id.ll_main_reception, R.id.ll_after_sale, R.id.ll_main_used_car,
            R.id.ll_main_vip, R.id.ll_main_reservation, R.id.ll_main_consultation, R.id.tv_linshi})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.ll_main_hot://跳转到热车销售页面
                MyApplication.openActivity(context, HotCarActivity.class);
                break;
            case R.id.ll_main_reception://跳转到客服接待页面
                MyApplication.openActivity(context, ServiceReceptionActivity.class);
                break;
            case R.id.ll_after_sale://跳转到售后服务页面
                bundle.putString(Constant.LOAD_TAG,"2");
                MyApplication.openActivity(context, LoadActivity.class,bundle);
                break;

            case R.id.ll_main_used_car://跳转到二手车页面
                MyApplication.openActivity(context, UsedCarActivity.class);
                break;

            case R.id.ll_main_vip://跳转到VIP页面
                bundle.putString(Constant.LOAD_TAG,"1");
                MyApplication.openActivity(context, LoadActivity.class,bundle);
                break;

            case R.id.ll_main_reservation://跳转到预约页面
                MyApplication.openActivity(context, ReservationActivity.class);
                break;

            case R.id.ll_main_consultation://跳转到咨询销售页面
                MyApplication.openActivity(context, RobotConsultActivity.class);
                break;

            case R.id.tv_linshi:
                Intent startIntent = new Intent(context, FaceRecognitionService.class);
                startService(startIntent);
                break;
        }
    }
}

