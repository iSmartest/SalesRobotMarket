package com.ifree.robot.salesrobotmarket.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.dialog.SelectCarStyleDialog;
import com.ifree.robot.salesrobotmarket.service.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.service.entity.CommonEntity;
import com.ifree.robot.salesrobotmarket.service.presenter.CarTypePresenter;
import com.ifree.robot.salesrobotmarket.service.presenter.TestDrivePresenter;
import com.ifree.robot.salesrobotmarket.service.view.CarTypeView;
import com.ifree.robot.salesrobotmarket.service.view.CommonView;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.RegexpUtils;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class TestDriveActivity extends BaseActivity {
    private CarTypePresenter mCarTypePresenter;
    private TestDrivePresenter mTestDrivePresenter;
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.tv_choose_car)
    TextView tvChooseCar;
    @BindView(R.id.tv_choose_time)
    TextView tvChooseTime;
    @BindView(R.id.tv_test_submit)
    TextView tvTestSubmit;
    @BindView(R.id.tv_test_drive_phone)
    EditText tvTestDrivePhone;
    private final int DATE_DIALOG = 1;
    private int mYear, mMonth, mDay;
    private int id;
    private List<CarTypeEntity.DataBean.CarTypeListBean> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_drive;
    }

    @Override
    protected void initView() {
        hideBack(3);
        setTitleText("预约试驾");
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        mCarTypePresenter = new CarTypePresenter(context);
        mTestDrivePresenter = new TestDrivePresenter(context);
        initTimePicker();
    }

    @Override
    protected void loadData() {
        mCarTypePresenter.onCreate();
        mCarTypePresenter.attachView(mCarTypeView);
        mCarTypePresenter.getQuestCarType(storeId, "加载中...");
    }
    private CarTypeView mCarTypeView = new CarTypeView() {
        @Override
        public void onSuccess(CarTypeEntity mCarTypeEntity) {
            if (mCarTypeEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mCarTypeEntity.getMsg());
                return;
            }
            List<CarTypeEntity.DataBean.CarTypeListBean> carTypeList = mCarTypeEntity.getData().getCarTypeList();
            if (carTypeList != null && !carTypeList.isEmpty() && carTypeList.size() > 0) {
                mList.addAll(carTypeList);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };


    @OnClick({R.id.tv_choose_car, R.id.tv_choose_time, R.id.tv_test_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_car:
                SelectCarStyleDialog selectCarStyleDialog = new SelectCarStyleDialog(context, mList, new SelectCarStyleDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure(int position) {
                        String tx = mList.get(position).getName();
                        id = mList.get(position).getId();
                        tvChooseCar.setText(tx);
                    }
                });
                selectCarStyleDialog.show();
                break;
            case R.id.tv_choose_time:
                showDialog(DATE_DIALOG);
                break;
            case R.id.tv_test_submit:
                String phone = tvTestDrivePhone.getText().toString().trim();
                String time = tvChooseTime.getText().toString();
                if (!RegexpUtils.isMobileNO(phone)) {
                    ToastUtils.makeText(context, "请输入正确的电话号码");
                    return;
                }
                if (time.isEmpty()) {
                    ToastUtils.makeText(context, "请输入试乘时间");
                    return;
                }
                submitTestDriveInfo(phone, time);
                break;
        }
    }

    private void submitTestDriveInfo(String phone, String time) {
        mTestDrivePresenter.onCreate();
        mTestDrivePresenter.attachView(mTestDriveView);
        mTestDrivePresenter.getQuestTestDriveInfo(storeId,id+"",phone,time,"");
    }

    private CommonView mTestDriveView = new CommonView() {
        @Override
        public void onSuccess(CommonEntity mCommonEntity) {
            if (mCommonEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mCommonEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context, mCommonEntity.getMsg());
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
//        long time = System.currentTimeMillis();
//        Date date = new Date(time);
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        tvChooseTime.setText(format1.format(date)); //更新时间

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
