package com.ifree.robot.salesrobotmarket.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.CommonEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.MaintenancePresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.CommonView;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class MaintainActivity extends BaseActivity {
    private MaintenancePresenter mMaintenancePresenter;
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.tv_project)
    TextView tvProject;
    @BindView(R.id.tv_time)
    TextView tvChooseTime;
    @BindView(R.id.tv_maintain_submit)
    TextView tvMaintainSubmit;
    @BindView(R.id.tv_number)
    EditText tvNumber;
    @BindView(R.id.tv_phone_number)
    EditText tvPhoneNumber;
    private final int DATE_DIALOG = 1;
    private int mYear, mMonth, mDay;
    private String type = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_maintain;
    }

    @Override
    protected void loadData() {
        initTimePicker();
    }

    @Override
    protected void initView() {
        hideBack(3);
        setTitleText("维修保养");
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        mMaintenancePresenter = new MaintenancePresenter(context);
    }

    @OnClick({R.id.tv_project, R.id.tv_number, R.id.tv_time, R.id.tv_maintain_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_project:
                new AlertDialog.Builder(context).setMessage("性别")
                        .setPositiveButton("维修", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvProject.setText("维修");
                                type = "1";
                            }
                        }).setNegativeButton("保养", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvProject.setText("保养");
                        type = "2";
                    }
                }).show();
                break;
            case R.id.tv_number:
                break;
            case R.id.tv_time:
                showDialog(DATE_DIALOG);
                break;
            case R.id.tv_maintain_submit:
                if (type.isEmpty()) {
                    ToastUtils.makeText(context, "请选择预约项目");
                    return;
                }
                String carNumber = tvNumber.getText().toString().trim();
                if (carNumber.isEmpty()) {
                    ToastUtils.makeText(context, "请输入车牌号");
                    return;
                }
                String date = tvChooseTime.getText().toString();
                if (date.isEmpty()) {
                    ToastUtils.makeText(context, "请选择维修/保养时间");
                    return;
                }
                String phoneNumber = tvPhoneNumber.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    ToastUtils.makeText(context, "请输入手机号");
                    return;
                }
                submitMaintainInfo(carNumber, date, phoneNumber);
                break;
        }
    }

    private void submitMaintainInfo(String carNumber, String date, String phoneNumber) {
        mMaintenancePresenter.onCreate();
        mMaintenancePresenter.attachView(mMaintenceView);
        mMaintenancePresenter.getQuestMaintainInfo(storeId,carNumber,phoneNumber,date,type,"提交中...");
    }

    private CommonView mMaintenceView = new CommonView() {
        @Override
        public void onSuccess(CommonEntity mCommonEntity) {
            if (mCommonEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mCommonEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context, "预约成功");
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);

        }
    };

    private void initTimePicker() {
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    public void display() {
        tvChooseTime.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };
}
