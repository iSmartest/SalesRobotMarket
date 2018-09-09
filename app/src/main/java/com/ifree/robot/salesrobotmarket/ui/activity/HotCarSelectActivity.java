package com.ifree.robot.salesrobotmarket.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.dialog.SelectCarStyleDialog;
import com.ifree.robot.salesrobotmarket.service.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.service.presenter.CarTypePresenter;
import com.ifree.robot.salesrobotmarket.service.view.CarTypeView;
import com.ifree.robot.salesrobotmarket.utils.SPUtil;
import com.ifree.robot.salesrobotmarket.utils.StatusBarUtil;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/6.
 * Description:
 */
public class HotCarSelectActivity extends AppCompatActivity {
    private CarTypePresenter mCarTypePresenter;
    @BindView(R.id.tv_select_hot_car)
    TextView tvSelectCar;
    @BindView(R.id.tv_complete_setting)
    TextView tvComplete;
    private List<CarTypeEntity.DataBean.CarTypeListBean> mList = new ArrayList<>();
    private String storeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_car_select);
        StatusBarUtil.fullScreen(HotCarSelectActivity.this);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    private void initView() {
        storeId = SPUtil.getString(this, "storeId");
        mCarTypePresenter = new CarTypePresenter(this);
    }


    private void loadData() {
        mCarTypePresenter.onCreate();
        mCarTypePresenter.attachView(mCarTypeView);
        mCarTypePresenter.getQuestCarType(storeId, "加载中...");
    }


    private CarTypeView mCarTypeView = new CarTypeView() {
        @Override
        public void onSuccess(CarTypeEntity mCarTypeEntity) {
            if (mCarTypeEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(HotCarSelectActivity.this, mCarTypeEntity.getMsg());
                return;
            }
            List<CarTypeEntity.DataBean.CarTypeListBean> carTypeList = mCarTypeEntity.getData().getCarTypeList();
            if (carTypeList != null && !carTypeList.isEmpty() && carTypeList.size() > 0) {
                mList.addAll(carTypeList);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(HotCarSelectActivity.this, result);
        }
    };


    @OnClick({R.id.tv_complete_setting, R.id.tv_select_hot_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_hot_car:
                SelectCarStyleDialog selectCarStyleDialog = new SelectCarStyleDialog(HotCarSelectActivity.this, mList, new SelectCarStyleDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure(int position) {
                        SPUtil.putString(HotCarSelectActivity.this, "carId", mList.get(position).getId() + "");
                        tvSelectCar.setText(mList.get(position).getName());
                        SPUtil.putString(HotCarSelectActivity.this, "address", mList.get(position).getAddress());
                    }
                });
                selectCarStyleDialog.setCanceledOnTouchOutside(false);
                selectCarStyleDialog.show();
                break;
            case R.id.tv_complete_setting:
                if (tvSelectCar.getText().toString().trim().isEmpty()) {
                    ToastUtils.makeText(this, "请选择车型");
                    return;
                }
                MyApplication.openActivity(HotCarSelectActivity.this, MainActivity.class);
                finish();
                break;
        }
    }
}
