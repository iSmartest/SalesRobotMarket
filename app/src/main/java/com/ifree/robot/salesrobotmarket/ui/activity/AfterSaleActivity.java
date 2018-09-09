package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.ui.fragment.InsuranceFragment;
import com.ifree.robot.salesrobotmarket.ui.fragment.MaintainFragment;
import com.ifree.robot.salesrobotmarket.ui.fragment.RepairFragment;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class AfterSaleActivity extends BaseActivity {
    @BindView(R.id.rg_inspect)
    RadioGroup rgInspect;
    @BindView(R.id.rb_in1)
    RadioButton rbIn1;
    @BindView(R.id.rb_in2)
    RadioButton rbIn2;
    @BindView(R.id.rb_in3)
    RadioButton rbIn3;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.fl_inspect)
    FrameLayout flInspect;
    private ColorStateList csl1, csl2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_after_sale;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        final Bundle bundle = new Bundle();
        LoadEntity.DataBean data = (LoadEntity.DataBean)getIntent().getSerializableExtra(Constant.LOADDATA);
        bundle.putSerializable(Constant.INSPECTDATA,data);
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.app_main_default);
        csl2 = resource.getColorStateList(R.color.black_color);
        changeFragment(RepairFragment.class, R.id.fl_inspect, false, bundle, false);
        rgInspect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_in1:
                        rbIn1.setTextColor(csl1);
                        rbIn2.setTextColor(csl2);
                        rbIn3.setTextColor(csl2);
                        changeFragment(RepairFragment.class, R.id.fl_inspect, false, bundle, false);
                        break;
                    case R.id.rb_in2:
                        rbIn1.setTextColor(csl2);
                        rbIn2.setTextColor(csl1);
                        rbIn3.setTextColor(csl2);
                        changeFragment(MaintainFragment.class, R.id.fl_inspect, false, bundle, false);
                        break;
                    case R.id.rb_in3:
                        rbIn1.setTextColor(csl2);
                        rbIn2.setTextColor(csl2);
                        rbIn3.setTextColor(csl1);
                        changeFragment(InsuranceFragment.class, R.id.fl_inspect, false, bundle, false);
                        break;
                }
            }
        });
    }
}
