package com.ifree.robot.salesrobotmarket.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class ReservationActivity extends BaseActivity {

    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.tv_test_drive)
    TextView tvTextdrive;
    @BindView(R.id.tv_maintain)
    TextView tvMaintain;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(1);
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.transparent));
    }

    @OnClick({R.id.tv_test_drive, R.id.tv_maintain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_test_drive:
                MyApplication.openActivity(context,TestDriveActivity.class);
                break;
            case R.id.tv_maintain:
                MyApplication.openActivity(context,MaintainActivity.class);
                break;
        }
    }
}
